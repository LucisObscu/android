package org.androidtown.samplefragment2;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements ListFragment.ImageSelectionCallback{
    ListFragment listFragment;
    ViewerFragment viewerFragment;

    int[] images={R.drawable.dream01,R.drawable.dream02};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager=getFragmentManager();
        listFragment=(ListFragment)manager.findFragmentById(R.id.listFragment);
        viewerFragment=(ViewerFragment)manager.findFragmentById(R.id.viewerFragment);
    }


    @Override
    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }
}
