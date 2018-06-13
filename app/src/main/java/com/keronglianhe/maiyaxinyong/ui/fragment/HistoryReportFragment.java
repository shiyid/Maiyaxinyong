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
public class HistoryReportFragment extends Fragment {


    public HistoryReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_credit_enquiry, container, false);

        return root;
    }

    public static HistoryReportFragment newInstance() {

        HistoryReportFragment fragment = new HistoryReportFragment();
        return fragment;
    }
}
