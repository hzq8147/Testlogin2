package com.example.a52n.testlogin.My_e_activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a52n.testlogin.Login;
import com.example.a52n.testlogin.R;
import com.example.a52n.testlogin.WebService;
import com.example.a52n.testlogin.allclass.Userclass;

import static com.example.a52n.testlogin.Service.getuser;

public class My_e_money_activity extends AppCompatActivity {

    Userclass user;
    Button back_btn,add_btn;
    TextView moneytv;
    String infostring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_e_money);

        Intent intent =getIntent();
        user=(Userclass)intent.getSerializableExtra("user");
        moneytv=(TextView)findViewById(R.id.my_money);
        add_btn=(Button)findViewById(R.id.btn_add_money);

        new Thread(getinfo).start();

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_add =new Intent(My_e_money_activity.this,My_e_money_add.class);
                intent_add.putExtra("user",user);
                My_e_money_activity.this.finish();
                startActivity(intent_add);

            }
        });


        back_btn=(Button)findViewById(R.id.btn_back2);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                My_e_money_activity.this.finish();
            }
        });
    }
    Handler handler= new Handler(){
        public void handleMessage(Message msg){
            if (msg.arg1==1) {
                user=getuser(infostring);
                moneytv.setText(user.getMoney());
            }
            else
            {
                Toast toast=Toast.makeText(My_e_money_activity.this,"获取个人信息失败，请稍候再试",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                My_e_money_activity.this.finish();
            }
        }
    };
    Runnable getinfo=new Runnable() {

        @Override
        public void run() {
            infostring="";
            WebService serv=new WebService();
            infostring=serv.getinfo(user.getUsername());
            Message msg=new Message();
            if (infostring.equals("Fail")){
                msg.arg1=2;
            }
            {
                msg.arg1=1;
            }
            handler.sendMessage(msg);

        }
    };
}
