package com.example.a52n.testlogin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrartor on 2017/7/24.
 * Editor:52N
 */
/*
    Flag:
    1.登陆
    2.注册
 */

public class WebService {
    private static  String IP ="59.110.156.44:8080/Testlogin";
    private static int RESULT_OK=1;
    //register flag=2
    private static int RESULT_REG_USER_EXIST=2;
    private static int RESULT_REG_NOFILL=3;
    //login flag=1
    private static int RESULT_CONNECT_FAILED=3;
    private static int RESULT_USERNAME_WRONG=2;
    //newslist flag=3
    //getinfo flag=4
    private static String RESULT_GETINFO_FAILED="Fail";


    private String username,password,nickname,phone,personid,address;
    //addmoney
    private String addmoney;
    public int Regeister(String username,String password, String nickname,String phone,String personid,String address){
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.phone=phone;
        this.personid=personid;
        this.address=address;

        if (username.length()==0 || password.length()==0 || nickname.length()==0 || phone.length()==0 || personid.length()==0 || address.length()==0 ){
            return RESULT_REG_NOFILL;
        }
        String ok="default";
        int flag=2;
        try {
             ok = executeHttpGet(flag);
            Log.v("ok:",ok);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (ok.equals("2")) {
            return RESULT_OK;
        }
        else
            return RESULT_REG_USER_EXIST;
    }
    public int  Login(String username,String password){
        this.username=username;
        this.password=password;
        String ok="default";
        int flag=1;

        try{
            ok=executeHttpGet(flag);
            Log.v("ok:",ok);
            if (ok.equals("1")){
                return RESULT_OK;
            }
            if (ok.equals("0")){
                return RESULT_USERNAME_WRONG;
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return RESULT_CONNECT_FAILED;

    }
    public String getinfo(String username){
        this.username=username;

        int flag=4;

        String infostring="";
        try{
            infostring=executeHttpGet(flag);
            return infostring;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
            return RESULT_GETINFO_FAILED;
    }
    public Boolean addmoney(String username,String addmoney){
        this.username=username;
        this.addmoney=addmoney;
        String ok="default";

        int flag=5;
        try{
            ok=executeHttpGet(flag);
            if (ok.equals("1")){
                return true;
            }
            if (ok.equals("Fail")||ok.equals("0"))
                return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public String executeHttpGet(int flag){
        HttpURLConnection conn =null;
        InputStream is =null;

        try{
            String path="";
            switch(flag){
                case 1:
                    path = "http://" + IP + "/Loglet";
                    path = path + "?username=" + username + "&password=" + password;
                    break;
                case 2:
                    path = "http://" + IP + "/Reglet";
                    path = path + "?username=" + username + "&password=" + password+"&nickname="+nickname+"&phone="+phone+"&personid="+personid+"&address="+address;
                    break;
                case 3:
                    path = "http://" + IP + "/Newslet";
                    break;
                case 4:
                    path = "http://" + IP + "/Getinfolet";
                    path = path + "?username="+username;
                    break;
                case 5:
                    path = "http://" + IP + "/Addmoneylet";
                    path = path + "?username="+username+"&money_add=" +addmoney;
                    break;

            }

            conn=(HttpURLConnection)new URL(path).openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Charset","UTF-8");

            if(conn.getResponseCode()==200){
                is=conn.getInputStream();
                return parseInfo(is);
            }
            return "Fail";
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            if(conn!=null){
                conn.disconnect();
            }
            if (is != null) {
                try{
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    private static String parseInfo(InputStream inputStream)throws  Exception{
        byte[] data= read(inputStream);
        return new String (data,"UTF-8");
    }
    private static  byte[] read(InputStream instream) throws  Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte [] buffer =new byte[1024];
        int len=0;
        while((len=instream.read(buffer))!= -1){
            outputStream.write(buffer,0,len);
        }
        instream.close();
        return outputStream.toByteArray();
    }

    public static Bitmap getBitmap(String path) {
        Bitmap bitmap=null;
        try{
            URL url =new URL(path);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            InputStream inputStream=conn.getInputStream();
            bitmap=BitmapFactory.decodeStream(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }
}
