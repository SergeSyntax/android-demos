package com.sergway.fragmentintoduction;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.myContainer);

        Bundle bundle = new Bundle();
        bundle.putString("edttext", "From Activity");

//        fragment.setArguments(bundle);
//        fragment.setArguments(bundle);

        if (fragment == null) {
            fragment = new MainFragment();
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .add(R.id.myContainer, fragment).commit();
        }
    }
}