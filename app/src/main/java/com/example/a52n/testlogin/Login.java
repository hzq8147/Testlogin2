package com.example.a52n.testlogin;


import android.app.ActivityManager;
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

import com.example.a52n.testlogin.allclass.Userclass;

import junit.framework.Test;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button logbtn;

    private Userclass uesr;
    private TextView regbtn;

   // private TextView infotv;

    String username,password;
    private int flag;

    Userclass user;
    String infostring;
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

                username=ed_username.getText().toString();
                password=ed_password.getText().toString();
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

            dialog.dismiss();

//            if(info.equals("2")){
//                Toast toast=Toast.makeText(Login.this,"用户"+username+"注册成功",Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER,0,0);
//                toast.show();
//
//            }
            if(msg.arg1==1) {
                dialog.setMessage("请稍等...");
                dialog.setCancelable(false);
                dialog.show();
                new Thread(getinfo).start();

            }
            if (msg.arg1==2){

                Toast toast=Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
            if (msg.arg1==3){

                Toast toast=Toast.makeText(Login.this,"连接服务器失败",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
            if(msg.arg1==4){

                Toast toast=Toast.makeText(Login.this,"请将用户名和密码填写完整",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
            if(msg.arg1==5){
                user =Service.getuser(infostring);
                Intent intent = new Intent(Login.this, Menu.class);
                intent.putExtra("user",user);
                startActivity(intent);
                Login.this.finish();
            }
            if (msg.arg1==6){
                Toast toast=Toast.makeText(Login.this,"获取个人信息失败，请稍候再试",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        }
    };

    Runnable logthread =new Runnable() {
        @Override
        public void run() {
            Message message = new Message();
            if(username.equals("") || password.equals("")){
                message.arg1=4;
                handler.sendMessage(message);
            }
            else {
                WebService serv = new WebService();
                message.arg1 = serv.Login(username, password);
                handler.sendMessage(message);
            }

        }
    };
    Runnable getinfo=new Runnable() {

        @Override
        public void run() {
            infostring="";
            WebService serv=new WebService();
            infostring=serv.getinfo(username);
            Message msg=new Message();
            if (infostring.equals("Fail")){
            msg.arg1=6;
            }
            {
                msg.arg1=5;
            }
            handler.sendMessage(msg);

        }
    };

    private boolean checkNetwork(){
        ConnectivityManager connManager=(ConnectivityManager)getSystemService(Context
                .CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo()!=null){
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}
