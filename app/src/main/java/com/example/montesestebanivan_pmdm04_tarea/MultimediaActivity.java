package com.example.montesestebanivan_pmdm04_tarea;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;

public class MultimediaActivity extends AppCompatActivity {

    String ficheroPruebaVideoWebm = "https://upload.wikimedia.org/wikipedia/commons/8/87/Schlossbergbahn.webm";
    String ficheroPruebaAutioMp3= "https://www.soundjay.com/nature/rain-02.mp3";
    VideoView videoView = null;
    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        confEventos();
    }

    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        if (videoView != null) {
            videoView.stopPlayback();
            videoView = null;
        }
    }



    private void confEventos(){

        Button btPlay = findViewById(R.id.btPlay);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPlay();
            }
        });

        Button btStop = findViewById(R.id.btStop);
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickStop();
            }
        });

    }

    private void clickPlay(){

        String str = ((EditText)findViewById(R.id.etUrl)).getText().toString();

        if( comprobarUrl(str)){

            if (!str.startsWith("http")) {
                str = "https://" + str;
            }

            if ( isVideoOrAudio(str) ){

                lanzarVideo(str);

            }else{

                lanzarAudio(str);

            }

        }else{

            Toast t = Toast.makeText(getApplicationContext(),R.string.url_fail,Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
        }
    }

    private void clickStop(){

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if (videoView != null){
            videoView.stopPlayback();
            videoView = null;
        }
    }

    private boolean comprobarUrl(String str){
        String tempString = str;

        if (!str.startsWith("http")) {
            tempString = "https://" + tempString;
        }

        try {
            new URL(tempString).toURI();
            return Patterns.WEB_URL.matcher(tempString).matches();
        } catch (MalformedURLException | URISyntaxException e) {
            //e.printStackTrace();
            return false;
        }
    }


    private boolean isVideoOrAudio(String str){

        String[] aVideo = {"3gp","mp4","webm","mkv"};
        String s = "";

        String[] aStrArchivo = str.split("/");
        String[] aStrExt = (aStrArchivo[aStrArchivo.length-1]).split("\\.");
        String ext = aStrExt[aStrExt.length-1].toLowerCase();

       boolean bol =  Arrays.stream(aVideo)
                .anyMatch( e -> ext.equals(e));

        return Arrays.stream(aVideo)
                .anyMatch( e -> ext.equals(e));

    }

    private void lanzarVideo(String str){
    //https://www.geeksforgeeks.org/complete-guide-on-how-to-build-a-video-player-in-android/
        videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(str));
        //videoView.setVideoURI(Uri.parse(ficheroPruebaVideoWebm));
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                Toast t = Toast.makeText(getApplicationContext(),R.string.fin_video,Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
            }
        });
        mediaController.show(10000);
        videoView.start();

    }

    private void lanzarAudio(String str){
    //https://developer.android.com/guide/topics/media/mediaplayer#java
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build());

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            //Cuando el recurso esté preparado (prepare o prepareAsync) se lanza este evento
            public void onPrepared(MediaPlayer player) {
                player.start();
            }
        });

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener(){
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.w("audio","Error: en lanzarAudio setOnErrorListener");
                return false;
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });


        try {
            mediaPlayer.setDataSource(str);
            //mediaPlayer.setDataSource(ficheroPruebaAutioMp3);
            Toast t = Toast.makeText(getApplicationContext(),R.string.cargando_audio,Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
            mediaPlayer.prepareAsync();

        } catch (IOException e) {
            Log.w("audio","Error: en lanzarAudio");
            Toast t = Toast.makeText(getApplicationContext(),R.string.error_audio,Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
            e.printStackTrace();
        }


    }

}//Fin
