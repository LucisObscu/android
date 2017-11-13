package org.androidtown.pagesliding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean isPageOpen=false;

    Animation leftAnim,rightAnim;
    LinearLayout page;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page=(LinearLayout)findViewById(R.id.page);
        b=(Button)findViewById(R.id.button);

        leftAnim= AnimationUtils.loadAnimation(this,R.anim.translate_left);
        rightAnim= AnimationUtils.loadAnimation(this,R.anim.translate_right);

        SlidingPageAnimationListener animAnimation=new SlidingPageAnimationListener();
        leftAnim.setAnimationListener(animAnimation);
        rightAnim.setAnimationListener(animAnimation);
    }

    public void onClick(View v){
        if(isPageOpen){
            page.startAnimation(rightAnim);
        }else{
            page.setVisibility(View.VISIBLE);
            page.startAnimation(leftAnim);
        }
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                page.setVisibility(View.INVISIBLE);
                b.setText(" Open ");
                isPageOpen=false;
            }else{
                b.setText(" Close ");
                isPageOpen=true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
