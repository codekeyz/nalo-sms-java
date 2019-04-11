package com.nalo.nalosms;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NaloSMS {

    private static final String SMS_BASE_URL = "https://api.nalosolutions.com/";
    private final NaloMessage naloMessage;
    private NaloSMSService smsService;
    private static Retrofit retrofit;


    private NaloSMS(@NonNull NaloMessage naloMessage) {
        this.naloMessage = naloMessage;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SMS_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        this.smsService = retrofit.create(NaloSMSService.class);
    }

    public void send(@NonNull final Callback callback) {
        this.smsService.sendSMS(
                this.naloMessage.username,
                this.naloMessage.password,
                this.naloMessage.source,
                this.naloMessage.message,
                this.naloMessage.destination,
                this.naloMessage.type,
                this.naloMessage.dlr).enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onResult(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public enum Type {

        PLAIN_TEXT_GSM_3_38(0),
        PLAIN_TEXT_ISO_88559_1(5),
        FLASH_MESSAGE_GSM_3_38(1),
        FLASH_MESSAGE_ISO_88559_1(7),
        RESERVED(3),
        UNICODE(2),
        UNICODE_FLASH(6),
        WAP_PUSH(4);


        private int _value;

        Type(int Value) {
            this._value = Value;
        }

        private int getValue() {
            return _value;
        }
    }

    public interface Callback {
        void onResult(String result);

        void onError(Throwable error);
    }

    public static class SMSBuilder {
        private NaloMessage naloMessage;

        public SMSBuilder() {
            this.naloMessage = new NaloMessage();
        }

        @NonNull
        public SMSBuilder withAuthCred(String username, String password) {
            this.naloMessage.username = username;
            this.naloMessage.password = password;
            return this;
        }

        @NonNull
        public SMSBuilder setMessage(String message) {
            this.naloMessage.message = message;
            return this;
        }

        @NonNull
        public SMSBuilder setDestination(String... destination) {
            this.naloMessage.destination = TextUtils.join(",", destination);
            return this;
        }

        @NonNull
        public SMSBuilder setSource(String source) {
            this.naloMessage.source = source;
            return this;
        }


        @NonNull
        public SMSBuilder setType(NaloSMS.Type type) {
            this.naloMessage.type = type.getValue();
            return this;
        }

        @NonNull
        public SMSBuilder withDeliveryReport(boolean required) {
            this.naloMessage.dlr = required ? "1" : "0";
            return this;
        }

        public NaloSMS build() {
            return new NaloSMS(this.naloMessage);
        }

    }
}
