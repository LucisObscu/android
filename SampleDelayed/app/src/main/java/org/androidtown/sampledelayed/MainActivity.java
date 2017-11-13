package org.androidtown.sampledelayed;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.textView);
    }

    public void onClick(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("원격 요청")
                .setMessage("데이터를 요청하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RequestHandler handler=new RequestHandler();
                        handler.sendEmptyMessageDelayed(0,20);
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
        tv.setText("원격 데이터 요청중...");
    }

    private class RequestHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            for(int i=0;i<5;i++){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){}
                tv.setText("원격 데이터 요청 완료");
            }
        }
    }
}
