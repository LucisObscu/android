package org.androidtown.samplelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.jar.Attributes;

public class SingerItemView  extends LinearLayout{
    TextView textView,textView2,textView3;
    ImageView imageView;

    public SingerItemView(Context context){
        super(context);

        LayoutInflater inflater=(LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item,this,true);
        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        imageView=(ImageView)findViewById(R.id.imageView);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setMobile(String mobile){
        textView2.setText(mobile);
    }

    public void setAge(int age){
        textView3.setText(age+"");
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
}
