package org.androidtown.lifecycle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e=(EditText)findViewById(R.id.editText2);
        Log.e("aaa","onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("aaa","onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("aaa","onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("aaa","onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("aaa","onPause()");
        SharedPreferences pref=getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("name",e.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("aaa","onResume()");
        SharedPreferences pref=getSharedPreferences("pref",Activity.MODE_PRIVATE);
        if(pref!=null && pref.contains("name")){
            String name=pref.getString("name","");
            e.setText(name);
        }
    }
}
