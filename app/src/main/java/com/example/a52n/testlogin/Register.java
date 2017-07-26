package com.example.a52n.testlogin;

import android.app.ActivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    Button btn_reg;
    EditText ed_username,ed_password,ed_nickname,ed_phone;
    String reg_username,reg_password,reg_nickname,reg_phone;
    int flag=2;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ed_username=(EditText)findViewById(R.id.edit_username);
        ed_password=(EditText)findViewById(R.id.edit_password);
        ed_nickname=(EditText)findViewById(R.id.edit_nickname);
        ed_phone=(EditText)findViewById(R.id.edit_phone);
        btn_reg=(Button)findViewById(R.id.btn_reg);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg_username=ed_username.getText().toString();
                reg_password=ed_password.getText().toString();
                reg_nickname=ed_nickname.getText().toString();
                reg_phone=ed_phone.getText().toString();

                new Thread(regthread).start();


            }
        });
    }
    Handler handler=new Handler(){
        public void handleMessage(Message msg){

            switch (msg.arg1){
                case 1:
                    Toast toast = Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    Register.this.finish();
                    break;
                case 2:
                    toast = Toast.makeText(Register.this, "注册失败，用户名已存在", Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    break;
                case 3:
                    toast = Toast.makeText(Register.this, "请您填写信息完整", Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    break;
            }

        }
    };
    Runnable regthread =new Runnable() {
        @Override
        public void run() {
            WebService serv=new WebService();
            try{

                    Message msg=new Message();

                    msg.arg1=serv.Regeister(reg_username,reg_password,reg_nickname,reg_phone);
                    handler.sendMessage(msg);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    };
}
