package com.example.montesestebanivan_pmdm04_tarea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


        if (findViewById(R.id.idFrameLayout) != null) {
            if (savedInstanceState != null) {
                return;
            }

           getSupportFragmentManager()
                   .beginTransaction()
                   .replace(R.id.idFrameLayout, new Config())
                   .commit();
        }

    }
}