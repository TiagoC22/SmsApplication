package com.rodpil.smsstat;

/**
 * Created by Tiago on 25/05/2018.
 */

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private ArrayList<HashMap<String, String>> contactList;
    private Context context;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED  ||
                    checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED   ) {
                Log.d("PERMISSION", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS};
                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            }
        }
        ContentResolver resolver = getContentResolver();
        Contact contact = new Contact(getApplicationContext(),resolver);

        if (Fichiers.checkIfExists(this.getApplicationContext(),"contacts.json")){
            Log.i("a","b");
        } else {
            Log.i("a","b");
            contact.execute();
        }
        this.context = getApplicationContext();
        showFragment(new Menu());
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
