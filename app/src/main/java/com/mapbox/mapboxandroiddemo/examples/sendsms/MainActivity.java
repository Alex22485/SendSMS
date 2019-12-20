package com.mapbox.mapboxandroiddemo.examples.sendsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    String msg, phoneNo;
    EditText EditText, PhoneN;
    Button SendBtbn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS))
            {
            }
            else
                {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

        EditText = findViewById(R.id.EditText);
        PhoneN = findViewById(R.id.PhoneN);
        SendBtbn = findViewById(R.id.SendBtbn);

        SendBtbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMessage();
            }
        });




    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[],int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
            {

                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(this,"Thank for permissing",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(this,"idiot",Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    protected void sendTextMessage(){

        msg =EditText.getText().toString();
        phoneNo=PhoneN.getText().toString();

        SmsManager smsManager= SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo,null,msg,null,null);
        Toast.makeText(this,"Sent!",Toast.LENGTH_LONG).show();


    }
}







