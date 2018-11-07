package com.test.ahsan.app.view.activities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import java.util.ArrayList;

public class SplashActivity extends Activity {

    public final static int MULTIPLE_PERMISSIONS = 10;

    String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions();
        } else {
            startMainActivity();
        }
    }

    public void startMainActivity() {
//        new Handler().postDelayed(() -> {
//            startActivity(new Intent(SplashActivity.this, LoginActivity.class)
//                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
//            finish();
//        }, 2000);
    }


    private void checkPermissions() {
        int result;
        ArrayList<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getApplicationContext(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
        } else startMainActivity();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MULTIPLE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permissions granted.
                startMainActivity();
            } else {
                // no permissions granted.
                Toast.makeText(getApplicationContext(), "All Permissions are Compulsory", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
