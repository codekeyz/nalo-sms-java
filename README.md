# Nalo SMS-API JAVA SDK

## **Overview**
This is an open source JAVA SDK that allows you to access the Nalo [REST SMS API](http://nalosolutions.com/NALO%20SMS%20HTTp%20Api_current.pdf) from your application. You need to create a Nalo account [here](https://sms.nalosolutions.com/nalosms/signup.php/) in order to use this API.

## Using NaloSMS Library in your application

Do not forget to add internet permission in manifest if already not present
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### Usage
```java
import com.nalo.nalosms.NaloSMS;

// Creating an Instance of NaloSMS using the SMSBuilder
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
                // result 
            }

            @Override
            public void onError(Throwable error) {
                // catch error here
            }
  });
```
