package org.androidtown.sampleimageanimation;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    ImageSwitcher switcher;
    Handler handler=new Handler();
    ImageThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switcher=(ImageSwitcher)findViewById(R.id.switcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setBackgroundColor(Color.YELLOW);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        });
    }

    public void onClick(View v){
        switcher.setVisibility(View.VISIBLE);
        thread=new ImageThread();
        thread.start();
    }

    private class ImageThread extends Thread{
        final int imageId[]={
                R.drawable.emo_im_crying,
                R.drawable.emo_im_happy,
                R.drawable.emo_im_laughing,
                R.drawable.emo_im_sad,
                R.drawable.emo_im_surprised,
        };
        int i=0;

        public void run(){
            while(true){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        switcher.setImageResource(imageId[i]);
                    }
                });

                i++;
                if(i==imageId.length)
                    i=0;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
