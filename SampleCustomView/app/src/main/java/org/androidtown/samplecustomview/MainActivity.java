package org.androidtown.samplecustomview;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Point> list=new ArrayList<>();
    int[][] omok=new int[11][11];
    boolean check,victory_check;
    LinearLayout container;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomView myView=new CustomView(this);
        container=(LinearLayout)findViewById(R.id.container);
        container.addView(myView);

        tv=(TextView)findViewById(R.id.textView);
    }

    private class CustomView extends View{
        Paint paint;
        int x,y;

        public CustomView(Context context) {
            super(context);

            paint=new Paint();
            paint.setColor(Color.RED);
        }

        public void init(Canvas canvas){
            paint.setColor(Color.YELLOW);
            paint.setARGB(255,250,200,100);
            canvas.drawRect(0,0,getWidth(),getHeight(),paint);

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(3);
            for(int i=1;i<=10;i++){
                canvas.drawLine(i*100,100,i*100,1000,paint);
                canvas.drawLine(100,i*100,1000,i*100,paint);
            }
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            init(canvas);

//            paint.setTextSize(50);
//            canvas.drawText("x:"+x,200,1200,paint);
//            canvas.drawText("y:"+y,200,1300,paint);

            paint.setAntiAlias(true);

            for(int i=0;i<list.size();i++){
                if(i%2==0){
                    paint.setColor(Color.BLACK);
                }else{
                    paint.setColor(Color.WHITE);
                }
                Point p=list.get(i);
                canvas.drawCircle(p.x*100,p.y*100,50,paint);
            }
            victory();
        }

        public void victory(){
            int cnt=0,cnt2=0,cnt3=0,cnt4=0;
            int doll;

            if(check==true)doll=1;
            else doll=2;

            for(int i=1;i<=10;i++){
                if(omok[i][x]==doll){  //세로 흑승리 판별
                    cnt++;
                    if(cnt==5){
                        victory_check=true;
                    }else if(cnt > 5){
                        victory_check=false;
                    }
                }else{
                    cnt=0;
                }
                if(omok[y][i]==doll){  //가로 흑승리 판별
                    cnt2++;
                    if(cnt2==5){
                        victory_check=true;
                    }else if(cnt2 > 5){
                        victory_check=false;
                    }
                }else{
                    cnt2=0;
                }
                int a=0,b=0;
                if(x>y){
                    a=x-y;
                }else{
                    b=y-x;
                }
                if(a+i<11 && b+i<11 && omok[b+i][a+i]==doll){  //대각선 흑승리 판별
                    cnt3++;
                    if(cnt3==5) {
                        victory_check = true;
                    }else if(cnt3 > 5){
                        victory_check=false;
                    }
                }else{
                    cnt3=0;
                }

                if(x+y-i>0 && x+y-i<11 &&omok[i][x+y-i]==doll){   //역대각선 흑승리 판별
                    cnt4++;
                    if(cnt4==5){
                        victory_check=true;
                    }else if(cnt4 > 5){
                        victory_check=false;
                    }
                }else{
                    cnt4=0;
                }
            }
            if(victory_check==true && check==true){
                tv.setText("흑 승리!");
            }else if(victory_check==true && check==false){
                tv.setText("백 승리!");
            }

            if(victory_check==true){
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("오목")
                        .setMessage("계속하시겠습니까?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int pos) {
                                list.clear();
                                for(int i=0;i<=10;i++){
                                    for(int j=0;j<=10;j++){
                                        omok[i][j]=0;
                                    }
                                }
                                victory_check=false;
                                tv.setText("게임 다시 시작!");
                                invalidate();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                x=(int)(event.getX()+50)/100;
                y=(int)(event.getY()+50)/100;

                if(x<1 || y<1 || x>10 || y>10){
                    if(x==0 && y==0){

                    }else{
                        tv.setText("범위 벗어남!");
                    }
                    return false;
                }

                for(int i=0;i<list.size();i++){
                    Point po=list.get(i);
                    if(po.x==x && po.y==y){
                        tv.setText("놓은 자리입니다.");
                        return false;
                    }
                }
                if(check==false) {
                    omok[y][x] = 1;
                    check=true;
                    tv.setText("백 차례입니다.");
                }else {
                    omok[y][x] = 2;
                    check=false;
                    tv.setText("흑 차례입니다.");
                }

                Point p=new Point(x,y);
                list.add(p);

                invalidate();   //onDraw() 호출
            }
            return super.onTouchEvent(event);
        }
    }
}
