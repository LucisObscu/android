package org.androidtown.sampleservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e=(EditText)findViewById(R.id.editText);
    }

    public void onClick(View v){
        String str=e.getText().toString();
        Intent intent=new Intent(this,MyService.class);
        intent.putExtra("aaa","1111");
        intent.putExtra("bbb","2222");
        startService(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent!=null){
            String aaa=intent.getStringExtra("aaa");
            String bbb=intent.getStringExtra("bbb");

            Toast.makeText(this,"aaa:"+aaa+" ,bbb:"+bbb,
                    Toast.LENGTH_LONG).show();
        }
    }
}
