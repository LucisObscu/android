package org.androidtown.exam06;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btnMenu1:
                intent=new Intent(this,AAAActivity.class);
                startActivity(intent);
                break;
            case R.id.btnMenu2:
                intent=new Intent(this,BBBActivity.class);
                startActivity(intent);
                break;
            case R.id.btnMenu3:
                intent=new Intent(this,CCCActivity.class);
                startActivity(intent);
                break;
        }
    }
}
