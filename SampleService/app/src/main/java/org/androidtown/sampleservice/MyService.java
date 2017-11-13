package org.androidtown.sampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("aaa","onCreate() 실행");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("aaa","onStartCommand() 실행");

        if(intent==null){
            return Service.START_STICKY;
        }else{
            String aaa=intent.getStringExtra("aaa");
            String bbb=intent.getStringExtra("bbb");

            Log.e("aaa","aaa:"+aaa+",bbb:"+bbb);

            for(int i=0;i<5;i++){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){}
                Log.e("aaa","대기:"+i+"초");
            }

            Intent showIntent=new Intent(getApplicationContext(),MainActivity.class);
            showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP);
            showIntent.putExtra("aaa","show");
            showIntent.putExtra("bbb",bbb+" 서버메세지");
            startActivity(showIntent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
