package com.example.a52n.testlogin;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a52n.testlogin.Adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class Menu extends AppCompatActivity implements ViewPager.OnPageChangeListener,View.OnClickListener{

    private ViewPager viewpager;
    private List<View> views= new ArrayList<>();

    private Fragment f1,f2,f3,f4;
    private LinearLayout lle,llnew,lllife,llabout;
    private ImageView ive,ivnew,ivlife,ivabout,ivcurrent;
    private MyPagerAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        madapter=new MyPagerAdapter(getSupportFragmentManager(),Menu.this);

        viewpager=(ViewPager)findViewById(R.id.vpager);
        viewpager.setAdapter(madapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(this);
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
        //initFragment1();


    }
//    private void initFragment1(){
//        FragmentTransaction  transaction =getSupportFragmentManager().beginTransaction();
//        if (f1==null){
//            f1=new Fragment1("first");
//            transaction.add(R.id.mainframe,f1);
//        }
//        hideFragment(transaction);
//        transaction.show(f1);
//        transaction.commit();
//
//    }
//    private void initFragment2(){
//        FragmentTransaction  transaction =getSupportFragmentManager().beginTransaction();
//        if (f2==null){
//            f2=new NewsList(Menu.this);
//            transaction.add(R.id.mainframe,f2);
//        }
//        hideFragment(transaction);
//        transaction.show(f2);
//        transaction.commit();
//
//    }
//    private void initFragment3(){
//        FragmentTransaction  transaction =getSupportFragmentManager().beginTransaction();
//        if (f3==null){
//            f3=new Fragment1("third");
//            transaction.add(R.id.mainframe,f3);
//        }
//        hideFragment(transaction);
//        transaction.show(f3);
//        transaction.commit();
//
//    }
//    private void initFragment4(){
//        FragmentTransaction  transaction =getSupportFragmentManager().beginTransaction();
//        if (f4==null){
//            f4=new Fragment1("4th");
//            transaction.add(R.id.mainframe,f4);
//        }
//        hideFragment(transaction);
//        transaction.show(f4);
//        transaction.commit();
//
//    }
//    private void hideFragment(FragmentTransaction transaction){
//        if(f1 != null){
//            transaction.hide(f1);
//        }
//        if(f2 != null){
//            transaction.hide(f2);
//        }
//        if(f3 != null){
//            transaction.hide(f3);
//        }
//        if(f4!=null){
//            transaction.hide(f4);
//        }
//    }
    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state==2){
            switch (viewpager.getCurrentItem()){
                case 0:
                    changeTab(R.id.lle);
                    break;
                case 1:
                    changeTab(R.id.llnews);
                    break;
                case 2:
                    changeTab(R.id.lllife);
                    break;
                case 3:
                    changeTab(R.id.llabout);

            }
        }

    }

    public void onClick(View v){
        changeTab(v.getId());
    }
    private void changeTab(int id){
        ivcurrent.setSelected(false);
        switch (id){
            case R.id.lle:
                viewpager.setCurrentItem(0);
                //initFragment1();
                ive.setSelected(true);
                ivcurrent=ive;
                break;
            case R.id.llnews:

                viewpager.setCurrentItem(1);
                //initFragment2();
                ivnew.setSelected(true);
                ivcurrent=ivnew;
                break;
            case R.id.lllife:
                //initFragment3();

                viewpager.setCurrentItem(2);
                ivlife.setSelected(true);
                ivcurrent=ivlife;
                break;
            case R.id.llabout:

                viewpager.setCurrentItem(3);
            //    initFragment4();
                ivabout.setSelected(true);
                ivcurrent=ivabout;
                break;
            default:
                break;
        }
    }

}
