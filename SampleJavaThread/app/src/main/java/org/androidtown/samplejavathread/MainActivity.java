package org.androidtown.samplejavathread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    int value;
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.textView);
    }

    public void onClick(View v){
        tv.setText("결과:"+value);
    }

    protected void onResume(){
        super.onResume();

        running=true;
        Thread thread=new BackgroundThread();
        thread.start();
    }

    protected void onPause(){
        super.onPause();
        running=false;
        value=0;
    }

    private class BackgroundThread extends Thread {
        public void run(){
            while(running){
                try {
                    Thread.sleep(1000);
                    value++;
                    android.util.Log.e("aaa","값:"+value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
