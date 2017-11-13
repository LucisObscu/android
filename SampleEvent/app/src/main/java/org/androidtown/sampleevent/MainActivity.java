package org.androidtown.sampleevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.textView);
        View view=findViewById(R.id.view1);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action=motionEvent.getAction();
                float x=motionEvent.getX();
                float y=motionEvent.getY();

                if(action==MotionEvent.ACTION_DOWN){
                    tv.append("손가락 눌림:"+x+","+y+"\n");
                }else if(action==MotionEvent.ACTION_MOVE){
                    tv.append("손가락 이동:"+x+","+y+"\n");
                }else if(action==MotionEvent.ACTION_UP){
                    tv.append("손가락 뗌:"+x+","+y+"\n");
                }
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Toast.makeText(this,"Back 눌렀음",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
