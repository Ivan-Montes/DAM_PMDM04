package com.example.montesestebanivan_pmdm04_tarea;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearListenerBotones(getApplicationContext());
    }

    private void crearListenerBotones(Context context){

        Button btn01 = findViewById(R.id.btn01);
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ConfigActivity.class);
                startActivity(intent);
            }
        });

        Button btn02 = findViewById(R.id.btn02);
        btn02.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),MultimediaActivity.class);
                startActivity(intent);
            }
        });


        Button btn03 = findViewById(R.id.btn03);
        btn03.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),VideoActivity.class);
                startActivity(intent);
            }
        });
    }
}