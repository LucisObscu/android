package org.androidtown.samplethreadanimation;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ArrayList<Drawable> drawableList=new ArrayList<>();
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=(ImageView)findViewById(R.id.imageView);

        Resources res=getResources();

        drawableList.add(res.getDrawable(R.drawable.emo_im_laughing));
        drawableList.add(res.getDrawable(R.drawable.emo_im_crying));
        drawableList.add(res.getDrawable(R.drawable.emo_im_happy));
        drawableList.add(res.getDrawable(R.drawable.emo_im_sad));
        drawableList.add(res.getDrawable(R.drawable.emo_im_surprised));

        AnimThread thread=new AnimThread();
        thread.start();
    }

    private class AnimThread extends Thread{
        public void run(){
            int index=0;
            while(true){
                index++;
                if(index>4)index=0;

                final Drawable drawable=drawableList.get(index);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
