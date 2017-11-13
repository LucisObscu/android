package org.androidtown.samplegridview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class SingerActivity extends AppCompatActivity {
    TextView txtName,txtMobile,txtAge;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer);

        txtName=(TextView)findViewById(R.id.txtName);
        txtMobile=(TextView)findViewById(R.id.txtMobile);
        txtAge=(TextView)findViewById(R.id.txtAge);
        img=(ImageView)findViewById(R.id.imageView2);

        Intent intent=getIntent();
        txtName.setText(intent.getStringExtra("name"));
        txtMobile.setText(intent.getStringExtra("mobile"));
        txtAge.setText(intent.getStringExtra("age"));
        img.setImageResource(Integer.parseInt(intent.getStringExtra("resId")));
    }
}
