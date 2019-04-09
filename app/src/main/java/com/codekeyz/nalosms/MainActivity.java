package com.codekeyz.nalosms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nalo.nalosms.NaloMessage;
import com.nalo.nalosms.NaloSMS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NaloSMS nalosms = new NaloMessage.Builder()
                .withUsernameAndPassword("", "")
                .setMessage("")
                .setSource("")
                .setType(1)
                .withDeliveryReport(true)
                .build();

        nalosms.send();
    }

}
