package org.androidtown.exam04;

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
        e=(EditText)findViewById(R.id.editText2);
    }

    public void onClick(View v){
        String str=e.getText().toString();
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}
