package org.androidtown.sampleintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent=getIntent();
        String name=intent.getExtras().getString("bbb");
        String name2=intent.getExtras().getString("ccc");
        Toast.makeText(this,
                "이름:"+name+","+name2, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v){
        Intent intent=new Intent();
        intent.putExtra("aaa","hong");
        setResult(1,intent);
        finish();
    }
}
