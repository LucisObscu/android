package org.androidtown.sampleandroidsocket;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editId,editPass;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editId=(EditText)findViewById(R.id.editText);
        editPass=(EditText)findViewById(R.id.editText2);
        tv=(TextView)findViewById(R.id.textView);
    }

    public void onClick(View v){
        ConnectThread thread=new ConnectThread();
        thread.start();
    }

    private class ConnectThread extends Thread{
        Handler handler=new Handler();

        public void run(){
            try {
                Socket client = new Socket("210.119.12.120",5001);

                ObjectOutputStream oos=new ObjectOutputStream(
                        client.getOutputStream());
                ObjectInputStream ois=new ObjectInputStream(
                        client.getInputStream());

                String id=editId.getText().toString();
                String pass=editPass.getText().toString();
                oos.writeObject(id+"|"+pass+"|");
                oos.flush();

                String data=(String)ois.readObject();
                int check=Integer.parseInt(data);
                if(check==0){
                    tv.setText("로그인 실패");
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("aaa","로그인 성공");
                            tv.setText("로그인 성공");
                            Toast.makeText(getApplicationContext(),
                                    "로그인 성공",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
