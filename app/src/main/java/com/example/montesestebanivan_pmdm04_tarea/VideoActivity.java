package com.example.montesestebanivan_pmdm04_tarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_fixed);
    }

    public void comenzarGrabacion(View view){
        CheckBox cbCalidad = findViewById(R.id.cbCalidad);
        EditText etDuracion = findViewById(R.id.etDuracion);
        int cad = 0;
        int dur = 5;

        if (cbCalidad.isChecked()){cad = 1;}

        if (etDuracion.getText().toString().matches("\\d+")){
            dur = Integer.parseInt(etDuracion.getText().toString());
        }

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, cad);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, dur);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }




}//Fin