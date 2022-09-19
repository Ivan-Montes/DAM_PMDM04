package com.example.montesestebanivan_pmdm04_tarea;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearListenerBotones();
    }

    private void crearListenerBotones(){

        Button btn01 = findViewById(R.id.btn01);
        btn01.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,ConfigActivity.class);
            startActivity(intent);
        });

        Button btn02 = findViewById(R.id.btn02);
        btn02.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MultimediaActivity.class);
            startActivity(intent);
        });


        Button btn03 = findViewById(R.id.btn03);
        btn03.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),VideoActivity.class);
            startActivity(intent);
        });
    }
}