package com.example.android.fitdiary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDateFragment extends Fragment {
    EditText text;
    Button ok;


    public AddDateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_date,container,false);
        text = v.findViewById(R.id.text);
        ok = v.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                String slowo = text.getText().toString();


            }
        });
        return v;
    }

    public interface callBack{
        public void onCallBack();
    }

}




