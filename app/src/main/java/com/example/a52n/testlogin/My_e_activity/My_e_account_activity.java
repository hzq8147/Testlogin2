package com.example.a52n.testlogin.My_e_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a52n.testlogin.R;
import com.example.a52n.testlogin.allclass.Userclass;


public class My_e_account_activity extends AppCompatActivity {

    Userclass user;
    Button back_btn;
    TextView usernametv,nicknametv,phonetv,addresstv,personidtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_e_account);
        Intent intent =getIntent();
        user=(Userclass)intent.getSerializableExtra("user");

        usernametv=(TextView)findViewById(R.id.usernametv);
        nicknametv=(TextView)findViewById(R.id.nicknametv);
        phonetv=(TextView)findViewById(R.id.phonetv);
        addresstv=(TextView)findViewById(R.id.addresstv);
        personidtv=(TextView)findViewById(R.id.personidtv);

        usernametv.setText(user.getUsername());
        nicknametv.setText(user.getNickname());
        phonetv.setText(user.getPhone());
        addresstv.setText(user.getAddress());
        personidtv.setText(user.getPersonid());

        back_btn=(Button)findViewById(R.id.btn_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                My_e_account_activity.this.finish();
            }
        });
    }
}
