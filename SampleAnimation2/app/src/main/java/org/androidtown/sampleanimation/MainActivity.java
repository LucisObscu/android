package org.androidtown.sampleanimation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView swingImage,waterImage,skyImage;
    Animation shakeAnimation,waterAnimation,flowAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swingImage=(ImageView)findViewById(R.id.swingImage);
        shakeAnimation= AnimationUtils.loadAnimation(this,R.anim.shake);
        swingImage.setAnimation(shakeAnimation);

        waterImage=(ImageView)findViewById(R.id.waterImage);
        waterAnimation= AnimationUtils.loadAnimation(this,R.anim.drop);
        waterImage.setAnimation(waterAnimation);

        skyImage=(ImageView)findViewById(R.id.skyImage);
        flowAnimation= AnimationUtils.loadAnimation(this,R.anim.flow);
        skyImage.setAnimation(flowAnimation);

        Resources res=getResources();
        Bitmap bitmap= BitmapFactory.decodeResource(res,R.drawable.sky_background);

        int w=bitmap.getWidth();
        int h=bitmap.getHeight();

        ViewGroup.LayoutParams params=skyImage.getLayoutParams();
        params.width=w;
        params.height=h;

        skyImage.setImageBitmap(bitmap);
        //flowAnimation.setAnimationListener(new AnimationAdapter());
    }

    private class AnimationAdapter implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            Toast.makeText(getApplicationContext(),"배경 애니메이션 시작",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Toast.makeText(getApplicationContext(),"배경 애니메이션 시작",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Toast.makeText(getApplicationContext(),"onWindowFocusChanged:"+hasFocus,
                Toast.LENGTH_SHORT).show();

        if(hasFocus){
            shakeAnimation.start();
        }else{
            shakeAnimation.reset();
        }
    }


}
