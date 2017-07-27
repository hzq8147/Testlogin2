package com.example.a52n.testlogin.My_e_activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a52n.testlogin.Login;
import com.example.a52n.testlogin.R;
import com.example.a52n.testlogin.WebService;
import com.example.a52n.testlogin.allclass.Userclass;

public class My_e_money_add extends AppCompatActivity {

    EditText moneyaddtv;

    Userclass user;
    Button btn_add;
    Button btn_back;
    String moneystr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_e_money_add);
        moneyaddtv=(EditText)findViewById(R.id.edit_money_add);
        btn_add=(Button)findViewById(R.id.btn_money_add);
        btn_back=(Button)findViewById(R.id.btn_back3);
        Intent intent =getIntent();
        user=(Userclass) intent.getSerializableExtra("user");

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                My_e_money_add.this.finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moneystr=moneyaddtv.getText().toString();
                DecimalFormat df=new DecimalFormat("0.00");
                Double moneydou= Double.parseDouble(moneystr);
                moneystr=df.format(moneydou);
                System.out.println(moneystr);
                new Thread(addmoneyth).start();

            }
        });


    }
    Handler handler=new Handler(){
        public  void handleMessage(Message msg){
            if(msg.arg1==1){

                Toast toast=Toast.makeText(My_e_money_add.this,"充值成功",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                My_e_money_add.this.finish();
            }
            else
            {
                Toast toast=Toast.makeText(My_e_money_add.this,"充值失败",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        }
    };
    Runnable addmoneyth =new Runnable() {
        @Override
        public void run() {
            WebService serv=new WebService();
            Message msg=new Message();
            if(serv.addmoney(user.getUsername(),moneystr)){
                msg.arg1=1;
            }
            else
                msg.arg1=2;
            handler.sendMessage(msg);

        }
    };
}
