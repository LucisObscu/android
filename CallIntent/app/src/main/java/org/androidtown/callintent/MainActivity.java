package org.androidtown.callintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e=(EditText)findViewById(R.id.editText);
    }

    public void onClick(View v){
        String data=e.getText().toString();
        Intent intent=new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:"+data));
        startActivity(intent);
    }
}
