package org.androidtown.exam09;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    public void setBrith(String brith){
        textView2.setText(brith);
    }

    public void setTel(String tel){
        textView3.setText(tel+"");
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
}
