package com.example.a52n.testlogin.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.a52n.testlogin.Fragment1;
import com.example.a52n.testlogin.NewsList;

import java.util.List;

/**
 * Created by Administrartor on 2017/7/26.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT=4;
    private Fragment1 f1=null;
    private NewsList f2=null;
    private Fragment1 f3=null;
    private Fragment1 f4=null;

    Context context;
    public MyPagerAdapter( FragmentManager fm,Context context){
        super(fm);
        f1=new Fragment1("first");
        f2=new NewsList(context);
        f3=new Fragment1("third");
        f4=new Fragment1("4th");
    }
    public int getCount(){
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
    public Fragment getItem(int position){
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=f1;
                break;
            case 1:
                fragment=f2;
                break;
            case 2:
                fragment=f3;
                break;
            case 3:
                fragment=f4;
                break;
        }
        return fragment;
    }
}
