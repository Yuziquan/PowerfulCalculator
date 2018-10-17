package com.wuchangi.powerfulcalculator.adapter;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by WuchangI on 2018/10/13.
 */


/**
 * 右边侧滑菜单的两个Tab页的适配器
 */
public class MyPageAdapter extends PagerAdapter
{
    /**
     * 存放两个Tab页的视图view的列表
     */
    private List<View> mPageViewList;

    public MyPageAdapter(List<View> pageViewList)
    {
        super();
        this.mPageViewList = pageViewList;
    }


    @Override
    public int getCount()
    {
        return mPageViewList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = mPageViewList.get(position);
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(mPageViewList.get(position));
    }
}
