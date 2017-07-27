package com.example.a52n.testlogin;

import com.example.a52n.testlogin.allclass.Userclass;

import org.json.JSONObject;

/**
 * Created by Administrartor on 2017/7/27.
 */

public class Service {
    public static  Userclass getuser(String infostring){

        Userclass user=new Userclass();
        System.out.println(infostring);
        try{
            JSONObject job=new JSONObject(infostring);

            user.setUsername(job.getString("username"));
            user.setPassword(job.getString("password"));
            user.setNickname(job.getString("nickname"));
            user.setPhone(job.getString("phone"));
            user.setMoney(job.getString("money"));
            user.setPersonid(job.getString("personid"));
            user.setAddress(job.getString("address"));

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(user.getUsername());
        return user;
    }
}
