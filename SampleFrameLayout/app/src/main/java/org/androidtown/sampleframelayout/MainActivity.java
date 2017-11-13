package org.androidtown.sampleframelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img1,img2;
    boolean b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        img1=(ImageView)findViewById(R.id.imageView);
        img2=(ImageView)findViewById(R.id.imageView2);
    }

    public void onClick(View v){
        if(b==false){
            img1.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.VISIBLE);
            b=true;
        }else{
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.INVISIBLE);
            b=false;
        }
    }
}
