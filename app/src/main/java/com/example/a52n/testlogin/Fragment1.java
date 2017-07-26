package com.example.a52n.testlogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrartor on 2017/7/26.
 */

public class Fragment1  extends Fragment {

    private TextView tv;
    private String name;

    public Fragment1(String fName){
        this.name = fName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment,container,false);
        tv = (TextView) view.findViewById(R.id.textv);
        tv.setText(name);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("我变了-" + name);
            }
        });
        return view;
    }
}
