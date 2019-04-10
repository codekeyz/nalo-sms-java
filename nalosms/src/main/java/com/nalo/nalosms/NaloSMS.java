package com.nalo.nalosms;

import android.content.Context;
import android.support.annotation.NonNull;

public class NaloSMS {

    private static final String SMS_BASE_URL = "https://api.nalosolutions.com/bulksms/?";
    private final NaloMessage naloMessage;


    private NaloSMS(@NonNull NaloMessage naloMessage) {
        this.naloMessage = naloMessage;
    }

    public void send(Callback callback) {

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
        void onResult();

        void onError(String error);
    }

    public static class Builder {
        private Context context;
        private NaloMessage naloMessage;

        public Builder(@NonNull Context context) {
            this.context = context;
            this.naloMessage = new NaloMessage();
        }

        @NonNull
        public Builder withAuth(String username, String password) {
            this.naloMessage.username = username;
            this.naloMessage.password = password;
            return this;
        }

        @NonNull
        public Builder setMessage(String message) {
            this.naloMessage.message = message;
            return this;
        }

        @NonNull
        public Builder setDestination(String... destination) {
            this.naloMessage.destination = destination;
            return this;
        }

        @NonNull
        public Builder setSource(String source) {
            this.naloMessage.source = source;
            return this;
        }


        @NonNull
        public Builder setType(NaloSMS.Type type) {
            this.naloMessage.type = type.getValue();
            return this;
        }

        @NonNull
        public Builder withDeliveryReport(boolean required) {
            this.naloMessage.dlr = required ? "1" : "0";
            return this;
        }

        public NaloSMS build() {
            return new NaloSMS(naloMessage);
        }

    }
}
