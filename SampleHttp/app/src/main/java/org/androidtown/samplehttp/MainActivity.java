package org.androidtown.samplehttp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText)findViewById(R.id.editText);
        tv=(TextView)findViewById(R.id.textView);
    }

    public void onClick(View v){
        ConnectThread thread=new ConnectThread();
        thread.start();
    }

    private class ConnectThread extends Thread{
        public void run(){
            final String output=request();
            Log.e("aaa",output);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    tv.setText(output);
                }
            });
        }

        private String request() {
            StringBuilder output=new StringBuilder();
            String urlStr="http://openapi.haeundae.go.kr/openapi-data/service/rest/cyclelane/getCyclelaneList?_type=json&serviceKey=3mA3eUWjMG8fAimBaUlxXOO0VLWcJKZme0Ohi7eo0TPe3c7ZaHgxawOXuAVL4Ry5jlaD0L3avIAHZuZ1me0DeA%3D%3D";
            try {
                URL url=new URL(urlStr);
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                if(conn!=null){
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    BufferedReader reader=new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String line=null;
                    while(true){
                        line=reader.readLine();
                        if(line==null)break;

                        output.append(line+"\n");
                    }
                    reader.close();
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return output.toString();
        }
    }
}
