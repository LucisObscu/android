package org.androidtown.sampleaudioplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String AUDIO_URL="http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";
    MediaPlayer mediaPlayer;
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) throws IOException {
        killMediaPlayer();

        mediaPlayer=new MediaPlayer();
        mediaPlayer.setDataSource(AUDIO_URL);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    public void onClick2(View v){
        if(mediaPlayer!=null){
            pos=mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
    }
    public void onClick3(View v){
        if(mediaPlayer!=null && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
            mediaPlayer.seekTo(pos);
        }

    }

    private void killMediaPlayer() {
        if(mediaPlayer!=null)
            mediaPlayer.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }
}
