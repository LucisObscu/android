package org.androidtown.samplecustomviewimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomViewImage myView=new CustomViewImage(this);
        setContentView(myView);
    }

    private class CustomViewImage extends View{
        Bitmap cacheBitmap;
        Canvas cacheCanvas;
        Paint paint;

        public CustomViewImage(Context context) {
            super(context);
            paint=new Paint();
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            cacheBitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
            cacheCanvas=new Canvas();
            cacheCanvas.setBitmap(cacheBitmap);

            cacheCanvas.drawColor(Color.WHITE);
            paint.setColor(Color.RED);
            cacheCanvas.drawRect(100,100,200,200,paint);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            if(cacheBitmap!=null){
                canvas.drawBitmap(cacheBitmap,0,0,null);
            }
        }
    }
}
