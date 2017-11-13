package org.androidtown.samplescrollview;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ImageView imageView;
    BitmapDrawable bitmap;
    boolean b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView=(ScrollView)findViewById(R.id.scrollView);
        imageView=(ImageView)findViewById(R.id.imageView);
        scrollView.setHorizontalScrollBarEnabled(true);
    }

    public void onClick(View v){
        Resources res=getResources();
        if(b==false){
            bitmap=(BitmapDrawable)res.getDrawable(R.drawable.image02);
            b=true;
        }else{
            bitmap=(BitmapDrawable)res.getDrawable(R.drawable.image01);
            b=false;
        }
        int width=bitmap.getIntrinsicWidth();
        int height=bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width=width;
        imageView.getLayoutParams().height=height;
    }
}
