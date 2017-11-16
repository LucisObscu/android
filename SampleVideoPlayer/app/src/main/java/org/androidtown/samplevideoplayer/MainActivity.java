package org.androidtown.samplevideoplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    String URL="http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView=(VideoView)findViewById(R.id.videoView);

        MediaController mc=new MediaController(this);
        videoView.setMediaController(mc);

        videoView.setVideoURI(Uri.parse(URL));
        videoView.requestFocus();
    }

    public void onClick(View v){
        videoView.start();
    }
}
