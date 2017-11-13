package org.androidtown.exam03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img1,img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1=(ImageView)findViewById(R.id.imageView);
        img2=(ImageView)findViewById(R.id.imageView2);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.button:
                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.INVISIBLE);
                break;
            case R.id.button2:
                img1.setVisibility(View.INVISIBLE);
                img2.setVisibility(View.VISIBLE);
                break;
        }
    }
}
