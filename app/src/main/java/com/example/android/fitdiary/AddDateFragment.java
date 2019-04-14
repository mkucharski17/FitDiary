package com.example.android.fitdiary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDateFragment extends Fragment {
    private CallBack click;
    private EditText text;
    private Button ok;


    public AddDateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        click = (CallBack)getArguments().getSerializable("dupa");
        View v = inflater.inflate(R.layout.fragment_add_date,container,false);
        text = v.findViewById(R.id.text);
        ok = v.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                String slowo = text.getText().toString();
                click.onCallBack(slowo);
                close();
            }
        });
        return v;
    }

    public void close(){
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();
    }

    public interface CallBack{
        public void onCallBack(String s);
    }

}




