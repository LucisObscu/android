package org.androidtown.sampleimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout=(LinearLayout)findViewById(R.id.mainLayout);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        ImageView imgView=new ImageView(this);
        imgView.setImageResource(R.drawable.ic_launcher_background);
        imgView.setAdjustViewBounds(true);

        layout.addView(imgView,params);
    }
}
