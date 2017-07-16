package com.example.marc.servicecamera2;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 99;
    public static final int MY_PERMISSIONS_SEND_SMS = 99;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 99;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 99;
    Intent service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("______________marclog: ", "onCreate() in activity");

        Calendar cal = Calendar.getInstance();

        service = new Intent(getBaseContext(), CapPhoto.class);
        Log.d("______________marclog: ", "just created service");

        //cal.add(Calendar.SECOND, 15);
        //TAKE PHOTO EVERY 15 SECONDS
        //PendingIntent pintent = PendingIntent.getService(this, 0, service, 0);
        //AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
        //        60*60*1000, pintent);
        Log.d("______________marclog: ", "about to call startService");
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
            }
        }
        startService(service);
        Log.d("______________marclog: ", "just called startService");
    }
}
