package org.androidtown.sampleintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.textView);
    }

    public void onClick(View v){
        Intent intent=new Intent(
                getApplicationContext(),MenuActivity.class);
        intent.putExtra("bbb","kim");
        intent.putExtra("ccc","park");
        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101){
            Toast.makeText(this,
                    "요청코드:"+requestCode, Toast.LENGTH_SHORT).show();
            if(resultCode==1){
                String name=data.getExtras().getString("aaa");
                tv.setText("응답코드:"+resultCode+", name:"+name);
            }
        }
    }
}
