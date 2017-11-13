package org.androidtown.sampletab;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment1=new Fragment1();
        fragment2=new Fragment2();
        fragment3=new Fragment3();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,fragment1).commit();

        TabLayout tabs=(TabLayout)findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("통화기록"));
        tabs.addTab(tabs.newTab().setText("스팸키록"));
        tabs.addTab(tabs.newTab().setText("연락처"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos=tab.getPosition();
                Log.d("aaa","선택된 탭:"+pos);

                Fragment selected=null;
                if(pos==0){
                    selected=fragment1;
                }else if(pos==1){
                    selected=fragment2;
                }else if(pos==2){
                    selected=fragment3;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
