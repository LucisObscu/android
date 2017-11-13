package org.androidtown.samplethreadrunnable;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    ProgressRunnable runnable;
    ProgressBar progressBar;
    boolean isRunning;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler=new Handler();
        runnable=new ProgressRunnable();
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        tv=(TextView)findViewById(R.id.textView);
    }

    public void onStart(){
        super.onStart();

        progressBar.setProgress(0);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i=0;i<20 && isRunning;i++) {
                        Thread.sleep(1000);

                        handler.post(runnable);
                    }
                }catch(Exception e){
                    Log.e("aaa","예외 발생");
                }
            }
        });
        isRunning=true;
        thread.start();
    }

    public void onStop(){
        super.onStop();
        isRunning=false;
    }

    private class ProgressRunnable implements Runnable {
        public void run(){
            progressBar.incrementProgressBy(5);
            if(progressBar.getProgress()== progressBar.getMax()){
                tv.setText("완료");
            }else{
                tv.setText("작업중..."+progressBar.getProgress());
            }
        }
    }
}
