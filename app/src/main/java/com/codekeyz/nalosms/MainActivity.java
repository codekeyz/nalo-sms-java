package com.codekeyz.nalosms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.nalo.nalosms.NaloSMS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NaloSMS nalosms = new NaloSMS.SMSBuilder()
                .withAuthCred("your-username", "!@msweet")
                .setType(NaloSMS.Type.FLASH_MESSAGE_ISO_88559_1)
                .setMessage("Hey Geek, am Precious Keyz")
                .setSource("Tampico")
                .setDestination("+23324XX61XXX", "023XXXAAA", "0554XXX83X")
                .withDeliveryReport(true)
                .build();

        nalosms.send(new NaloSMS.Callback() {
            @Override
            public void onResult(String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable error) {
                Log.d("Error", "", error);
            }
        });
    }

}
