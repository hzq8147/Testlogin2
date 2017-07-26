package com.example.a52n.testlogin.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a52n.testlogin.Newsclass;
import com.example.a52n.testlogin.R;

import java.util.List;

import static android.R.attr.bitmap;

/**
 * Created by Administrartor on 2017/7/25.
 */

public class newsadapter extends BaseAdapter {

    private Context context;
    private List<Newsclass> list;
    private LayoutInflater listcontainer;
    public class ListitemView{
        public TextView newsname;

        public  TextView newsclass;

        public  TextView ncontext;

        public ImageView newspic;
    }
    public newsadapter(Context context, List<Newsclass> list){
        this.list=list;
        this.context=context;
    }
    public  int getCount(){
        return list.size();
    }
    public Object getItem(int position){
        return position;
    }
    public long getItemId(int positon){
        return positon;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ListitemView  listitemView;
        if (view==null){
            listitemView=new ListitemView();
            view=View.inflate(context,R.layout.news_item,null);

            listitemView.newsname=(TextView) view.findViewById(R.id.newsname);
            listitemView.newsclass=(TextView)view.findViewById(R.id.newsclass);
            listitemView.ncontext=(TextView)view.findViewById(R.id.newscontext);
            listitemView.newspic=(ImageView)view.findViewById(R.id.newsimg);
            //pic
            view.setTag(listitemView);
        }else{
            listitemView=(ListitemView)view.getTag();
        }
        listitemView.newsname.setText(list.get(position).getNewsname());
        listitemView.newsclass.setText(list.get(position).getNewsclass());
        listitemView.ncontext.setText(list.get(position).getContext());
        listitemView.newspic.setImageBitmap(list.get(position).getPic());
        //pic
        return view;
    }
}
