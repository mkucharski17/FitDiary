package com.example.android.fitdiary.Views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/*
* Base Fragment - created because o of number of uses the same close method
* */

public class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }

    protected void close() {
        FragmentManager manager = getFragmentManager();
        assert manager != null;
        manager.popBackStack();
    }
}
