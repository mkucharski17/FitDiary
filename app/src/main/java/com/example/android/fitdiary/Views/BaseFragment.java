package com.example.android.fitdiary.Views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }

    protected void close() {
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();
    }
}
