package com.keronglianhe.maiyaxinyong.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keronglianhe.maiyaxinyong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WindControlFragment extends Fragment {


    public WindControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wind_control, container, false);
        return view;
    }

}
