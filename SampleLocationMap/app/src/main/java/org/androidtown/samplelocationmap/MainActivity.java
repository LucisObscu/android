package org.androidtown.samplelocationmap;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions myLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment=(SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.e("aaa","구글맵 준비");
                map=googleMap;
                map.setMyLocationEnabled(true);
            }
        });
        MapsInitializer.initialize(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(map!=null)
            map.setMyLocationEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(map!=null)
            map.setMyLocationEnabled(true);
    }

    public void onClick(View v){
        LocationManager manager=(LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        GPSListener gpsListener=new GPSListener();
        long minTime=10000;
        float minDistance=0;

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                minTime,minDistance,gpsListener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                minTime,minDistance,gpsListener);
        Toast.makeText(getApplicationContext(),"위치 서비스 시작",
                Toast.LENGTH_SHORT).show();

        Location location=manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        String msg="위도:"+location.getLatitude()+"\n경도:"+location.getLongitude();
        Log.e("aaa",msg);

        if(myLocationMarker==null){
            myLocationMarker=new MarkerOptions();
            myLocationMarker.position(new LatLng(location.getLatitude(),location.getLongitude()));
            myLocationMarker.title("● 내 위치\n");
            myLocationMarker.snippet("● GPS로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(
                    R.drawable.mylocation));
            map.addMarker(myLocationMarker);
        }else{
            myLocationMarker.position(new LatLng(location.getLatitude(),
                    location.getLongitude()));
        }
    }

    private class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Double latitude=location.getLatitude();
            Double longitude=location.getLongitude();

            String msg="위도:"+latitude+"\n경도:"+longitude;
            Log.e("GPSListener",msg);
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

            LatLng curPoint=new LatLng(latitude,longitude);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,15));
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
