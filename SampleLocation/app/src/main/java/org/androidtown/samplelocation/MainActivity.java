package org.androidtown.samplelocation;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        LocationManager manager=(LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        GPSListener gpsListener=new GPSListener();
        long minTime=10000;
        float minDistance=0;

        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime,minDistance,gpsListener);
//        manager.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER,
//                minTime,minDistance,gpsListener);
        Toast.makeText(getApplicationContext(),"위치 서비스 시작",
                Toast.LENGTH_SHORT).show();

        Location location=manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        Location location=manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        String msg="위도:"+location.getLatitude()+"\n경도:"+location.getLongitude();
        Log.e("aaa",msg);

        tv.setText(msg);
    }

    private class GPSListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            Double latitude=location.getLatitude();
            Double longitude=location.getLongitude();

            String msg="위도:"+latitude+"\n경도:"+longitude;
            Log.e("GPSListener",msg);

            tv.setText("내 위치:"+latitude+", "+longitude);
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}
