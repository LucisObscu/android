package org.androidtown.exam06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
    }

    public void onClick(View v){
        if(e1.getText().toString().equals("") || e2.getText().toString().equals("")){
            Toast.makeText(this,"아이디 또는 비밀번호 입력하세요!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent(this,MenuActivity.class);
        startActivity(intent);
        e1.setText("");
        e2.setText("");
    }
}
