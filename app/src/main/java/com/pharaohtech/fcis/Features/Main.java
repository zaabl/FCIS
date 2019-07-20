package com.pharaohtech.fcis.Features;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pharaohtech.fcis.Announcements.Announcement;
import com.pharaohtech.fcis.R;

public class Main extends AppCompatActivity {

    Fragment navigatedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigatedFragment = new Announcement();
        setFragment(navigatedFragment);
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();

    }
}
