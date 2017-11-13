package org.androidtown.samplefragment2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewerFragment extends Fragment {
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(
                R.layout.fragment_viewer,container,false);

        imageView=(ImageView)rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void setImage(int image) {
        imageView.setImageResource(image);
    }
}
