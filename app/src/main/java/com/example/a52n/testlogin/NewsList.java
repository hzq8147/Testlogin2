package com.example.a52n.testlogin;


import android.support.v4.app.Fragment;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a52n.testlogin.Adapter.newsadapter;
import com.example.a52n.testlogin.Newsclass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsList extends Fragment {

    Context context;
    ListView listview;
    List<Newsclass> newslist;
    String username,password;
    String newsName,newsClass,newsText;
    String newstring;
    int flag;

    int length;
    private ProgressDialog dialog;

    public NewsList(Context context)
    {
        this.context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_list,container,false);

        listview=(ListView)view.findViewById(R.id.newslist);

        dialog=ProgressDialog.show(context,"请稍等...","数据获取中...",true);
//        Intent intent=getIntent();
//        username=intent.getStringExtra("username");
//        password=intent.getStringExtra("password");

        flag=3;

        new Thread(findnews).start();
        return view;
    }
    Runnable findnews=new Runnable() {
        @Override
        public void run() {
            WebService serv=new WebService();
            newstring=serv.executeHttpGet(flag);
//            bitmap=null;
//            try{
//                URL url =new URL("http://192.168.48.46:8080/pic/2.png");
//                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
//                InputStream inputStream=conn.getInputStream();
//                bitmap= BitmapFactory.decodeStream(inputStream);
//            }catch (IOException e){
//                e.printStackTrace();
//            }
            Message message=new Message();
            message.arg1=1;
            handler.sendMessage(message);
        }
    };
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){

           // testtv.setText(newstring);
            if (msg.arg1==1) {
              //  testtv.setImageBitmap(bitmap);
               dialog.dismiss();
                getNewslist();

                new Thread(picrun).start();
            }
            if (msg.arg1==2) {
                newsadapter adapter = new newsadapter(context, newslist);
                listview.setAdapter(adapter);
            }
        }

    };
    public  List<Newsclass> getNewslist(){
        newslist=new ArrayList<>();
        try{
            JSONObject job=new JSONObject(newstring);
            JSONArray jay=job.getJSONArray("list");
            length=jay.length();
            for (int i=0 ;i<length;i++){
                JSONObject tmp=(JSONObject)jay.get(i);

                Newsclass newsone=new Newsclass();
                newsone.setNewsname(tmp.getString("newsname"));
                newsone.setNewsclass(tmp.getString("newsclass"));
                newsone.setContext(tmp.getString("context"));
                newsone.setPicurl(tmp.getString("picurl"));

                newslist.add(newsone);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return newslist;

    }
    Runnable picrun =new Runnable() {

        Bitmap bitmap;
        @Override
        public void run() {
            try {
                for (int i = 0; i < length; i++)
                {
                    bitmap = WebService.getBitmap(newslist.get(i).getPicurl());
                    newslist.get(i).setPic(bitmap);
                    //newspiclist.add(bitmap);
                    Log.v("newspic",i+"       "+length);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            Message msg=new Message();
            msg.arg1=2;
            handler.sendMessage(msg);
        }
    };

}
