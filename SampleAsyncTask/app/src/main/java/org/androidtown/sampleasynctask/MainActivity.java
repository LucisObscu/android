package org.androidtown.sampleasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    BackgroundTask task;
    TextView tv;
    int value;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.textView);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                task=new BackgroundTask();
                task.execute(100);
                break;
            case R.id.button2:
                task.cancel(true);
                break;
        }
    }

    private class BackgroundTask extends AsyncTask<Integer,Integer,Integer>{
        @Override
        protected void onPreExecute() {
            value=0;
            progressBar.setProgress(value);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while(isCancelled()==false){
                value++;
                if(value>=100)break;
                else publishProgress(value);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return value;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            tv.setText("현재 값:"+values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            progressBar.setProgress(0);
            tv.setText("완료");
        }

        @Override
        protected void onCancelled() {
            progressBar.setProgress(0);
            tv.setText("취소");
        }
    }
}
