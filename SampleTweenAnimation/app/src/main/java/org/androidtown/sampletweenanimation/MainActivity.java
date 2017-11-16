package org.androidtown.sampletweenanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        Animation anim= AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.scale);
        v.startAnimation(anim);
    }

    public void onClick2(View v){
        Animation anim= AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.scale2);
        v.startAnimation(anim);
    }
}
