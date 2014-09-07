package fujimoto.yoshi.orderscanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.microsoft.windowsazure.mobileservices.*;

import java.net.MalformedURLException;
import java.util.List;


public class MyActivity extends Activity {

    public MobileServiceClient mClient;
    MobileServiceTable<Orders> mOrdersTable;
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACe9a695770a9f38fcbf1b7ff439a7cc7e";
    public static final String AUTH_TOKEN = "{{ 906e9eabcb8eb79be576687f8294e755 }}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
                            try {
                     mClient = new MobileServiceClient(
                            "https://orderup2.azure-mobile.net/", // Replace with the above Site URL
                            "BXfpcXcechDNSfdcEErRaVRpSKQXbu53",           // replace with the Application Key
                            getApplicationContext());
                    System.out.println("Yes");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println("Failed");
                }


            mOrdersTable.execute(new TableQueryCallback<Orders>() {
                public void onCompleted(List<Orders> result,
                                        int count,
                                        Exception exception,
                                        ServiceFilterResponse response) {
                    if (exception == null) {
                        for (Orders item : result) {
                            Toast.makeText(getApplicationContext(), "chicken", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(getApplicationContext(),  exception.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    }



