package com.example.a52n.testlogin;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;


public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button logbtn;

    private TextView regbtn;

   // private TextView infotv;

    String username,password;
    private int flag;

    EditText ed_username,ed_password;

    private ProgressDialog dialog;

    private int logok=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ed_username=(EditText) findViewById(R.id.username);
        ed_password=(EditText)findViewById(R.id.password);

        logbtn=(Button)findViewById(R.id.log);
        regbtn=(TextView)findViewById(R.id.reg);

        //infotv=(TextView)findViewById(R.id.webreturn);

        logbtn.setOnClickListener(this);
        regbtn.setOnClickListener(this);
    }
    public void onClick(View v){

        switch(v.getId()){
            case R.id.log:
                if(!checkNetwork()){
                    Toast toast = Toast.makeText(Login.this,"没联网",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    break;
                }
                dialog=new ProgressDialog(this);
                dialog.setTitle("进行中");
                dialog.setMessage("正在登陆...");
                dialog.setCancelable(false);
                dialog.show();
                flag=1;

                new Thread(logthread).start();

                break;
            case R.id.reg:
                if(!checkNetwork()){
                    Toast toast = Toast.makeText(Login.this,"没联网",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    break;
                }
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
//                if(!checkNetwork()){
//                    Toast toast = Toast.makeText(Login.this,"没联网",Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.CENTER,0,0);
//                    toast.show();
//                    break;
//                }
//                dialog=new ProgressDialog(this);
//                dialog.setTitle("进行中");
//                dialog.setMessage("正在注册...");
//                dialog.setCancelable(false);
//                dialog.show();
//                flag=2;
//                new Thread(MyThread).start();
                break;
        }
    }
    private Handler handler =new Handler(){
        public void handleMessage(Message msg){
            if (msg.arg1==2){

                Toast toast=Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
//            if(info.equals("2")){
//                Toast toast=Toast.makeText(Login.this,"用户"+username+"注册成功",Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER,0,0);
//                toast.show();
//
//            }
            dialog.dismiss();
            if(msg.arg1==1) {

                Intent intent = new Intent(Login.this, Menu.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);

                startActivity(intent);
                Login.this.finish();
            }
        }
    };
    Runnable logthread =new Runnable() {
        @Override
        public void run() {
            username=ed_username.getText().toString();
            password=ed_password.getText().toString();
            WebService serv=new WebService();
            if(serv.Login(username,password)) {
                Message message = new Message();
                message.arg1=1;
                handler.sendMessage(message);
            }
            else
            {
                Message message = new Message();
                message.arg1=2;
                handler.sendMessage(message);


            }
        }
    };

//    public  class MyThread implements Runnable{
//        @Override
//        public void run() {
//
//
//
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            });
//        }
//    }

    private boolean checkNetwork(){
        ConnectivityManager connManager=(ConnectivityManager)getSystemService(Context
                .CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo()!=null){
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}
