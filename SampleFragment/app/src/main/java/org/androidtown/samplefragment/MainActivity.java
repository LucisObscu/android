package org.androidtown.samplefragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment=(MainFragment)getFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment=new MenuFragment();
    }

    public void onFragmentChanged(int i) {
        if(i==0){
            getFragmentManager().beginTransaction().replace(R.id.container,menuFragment).commit();
        }else{
            getFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
        }
    }
}
