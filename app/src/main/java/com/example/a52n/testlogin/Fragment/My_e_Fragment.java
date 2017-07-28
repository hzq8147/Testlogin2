package com.example.a52n.testlogin.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a52n.testlogin.My_e_activity.My_e_account_activity;
import com.example.a52n.testlogin.My_e_activity.My_e_money_activity;
import com.example.a52n.testlogin.R;
import com.example.a52n.testlogin.allclass.Userclass;


/**
 * Created by Administrartor on 2017/7/26.
 */

public class My_e_Fragment  extends Fragment implements View.OnClickListener{

    Intent intent;
    TextView testv;
    Userclass user=new Userclass();
    public My_e_Fragment(Userclass user){
        this.user=user;
}

    private ImageView my_e_money;
    private ImageView my_e_account;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.menu_frag_my,container,false);
        my_e_account=(ImageView)view.findViewById(R.id.my_e_account);
        my_e_money=(ImageView)view.findViewById(R.id.my_e_money);

        testv=(TextView)view.findViewById(R.id.testtv);

        my_e_account.setOnClickListener(this);
        my_e_money.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.my_e_account:
                intent=new Intent(getContext(),My_e_account_activity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                break;
            case R.id.my_e_money:
                intent=new Intent(getContext(),My_e_money_activity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                break;
        }
    }
}
