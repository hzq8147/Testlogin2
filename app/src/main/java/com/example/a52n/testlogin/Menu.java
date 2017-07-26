package com.example.a52n.testlogin;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class Menu extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewpager;
    private List<View> views= new ArrayList<>();

    private Fragment f1,f2,f3,f4;
    private LinearLayout lle,llnew,lllife,llabout;
    private ImageView ive,ivnew,ivlife,ivabout,ivcurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        initView();

    }
    private void initView(){

        lle=(LinearLayout)findViewById(R.id.lle);
        llnew=(LinearLayout)findViewById(R.id.llnews);
        lllife=(LinearLayout)findViewById(R.id.lllife);
        llabout=(LinearLayout)findViewById(R.id.llabout);

        lle.setOnClickListener(this);
        llnew.setOnClickListener(this);
        lllife.setOnClickListener(this);
        llabout.setOnClickListener(this);

        ive=(ImageView)findViewById(R.id.ive);
        ivnew=(ImageView)findViewById(R.id.ivnews);
        ivlife=(ImageView)findViewById(R.id.ivlife);
        ivabout=(ImageView)findViewById(R.id.ivabout);

        ive.setSelected(true);
        ivcurrent=ive;
        initFragment1();


    }
    private void initFragment1(){
        FragmentTransaction  transaction =getSupportFragmentManager().beginTransaction();
        if (f1==null){
            f1=new Fragment1("first");
            transaction.add(R.id.mainframe,f1);
        }
        hideFragment(transaction);
        transaction.show(f1);
        transaction.commit();

    }
    private void initFragment2(){
        FragmentTransaction  transaction =getSupportFragmentManager().beginTransaction();
        if (f2==null){
            f2=new NewsList(Menu.this);
            transaction.add(R.id.mainframe,f2);
        }
        hideFragment(transaction);
        transaction.show(f2);
        transaction.commit();

    }
    private void initFragment3(){
        FragmentTransaction  transaction =getSupportFragmentManager().beginTransaction();
        if (f3==null){
            f3=new Fragment1("third");
            transaction.add(R.id.mainframe,f3);
        }
        hideFragment(transaction);
        transaction.show(f3);
        transaction.commit();

    }
    private void initFragment4(){
        FragmentTransaction  transaction =getSupportFragmentManager().beginTransaction();
        if (f4==null){
            f4=new Fragment1("4th");
            transaction.add(R.id.mainframe,f4);
        }
        hideFragment(transaction);
        transaction.show(f4);
        transaction.commit();

    }
    private void hideFragment(FragmentTransaction transaction){
        if(f1 != null){
            transaction.hide(f1);
        }
        if(f2 != null){
            transaction.hide(f2);
        }
        if(f3 != null){
            transaction.hide(f3);
        }
        if(f4!=null){
            transaction.hide(f4);
        }
    }
    public void onClick(View v){
        changeTab(v.getId());
    }
    private void changeTab(int id){
        ivcurrent.setSelected(false);
        switch (id){
            case R.id.lle:
                initFragment1();
                ive.setSelected(true);
                ivcurrent=ive;
                break;
            case R.id.llnews:
                initFragment2();
                ivnew.setSelected(true);
                ivcurrent=ivnew;
                break;
            case R.id.lllife:
                initFragment3();
                ivlife.setSelected(true);
                ivcurrent=ivlife;
                break;
            case R.id.llabout:
                initFragment4();
                ivabout.setSelected(true);
                ivcurrent=ivabout;
                break;
            default:
                break;
        }
    }
}
