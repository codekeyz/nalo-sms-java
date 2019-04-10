package com.codekeyz.nalosms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nalo.nalosms.NaloSMS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NaloSMS nalosms = new NaloSMS.Builder(this)
                .withAuth("", "")
                .setType(NaloSMS.Type.FLASH_MESSAGE_ISO_88559_1)
                .setMessage("")
                .setSource("")
                .withDeliveryReport(true)
                .build();

        nalosms.send(new NaloSMS.Callback() {
            @Override
            public void onResult() {

            }

            @Override
            public void onError(String error) {

            }
        });
    }

}
