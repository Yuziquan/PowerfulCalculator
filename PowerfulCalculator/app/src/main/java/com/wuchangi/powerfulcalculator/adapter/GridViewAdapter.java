package com.wuchangi.powerfulcalculator.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.wuchangi.powerfulcalculator.R;

import java.util.List;

/**
 * Created by WuchangI on 2018/10/13.
 */

public class GridViewAdapter extends BaseAdapter
{
    /**
     * 存放所有单元格中的主标题的列表
     */
    private List<String> mTitleList = null;

    /**
     * 存放所有单元格中的小标题的列表（可能为空）
     */
    private List<String> mSubtitleList = null;

    private LayoutInflater mLayoutInflater;

    private int mLayoutId;


    public GridViewAdapter(Context context, List<String> titleList, List<String> subtitleList, int layoutId)
    {
        mLayoutInflater = LayoutInflater.from(context);
        mTitleList = titleList;
        mSubtitleList = subtitleList;
        mLayoutId = layoutId;
    }


    @Override
    public int getCount()
    {
        return mTitleList.size();
    }


    @Override
    public Object getItem(int position)
    {
        return mTitleList.get(position);
    }


    @Override
    public long getItemId(int position)
    {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;

        if(convertView == null)
        {
            convertView = mLayoutInflater.inflate(mLayoutId, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.titleTextView = convertView.findViewById(R.id.tv_title);

            if(mSubtitleList != null)
            {
                viewHolder.subTitleTextView = convertView.findViewById(R.id.tv_subtitle);
            }

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleTextView.setText(mTitleList.get(position));

        if(mSubtitleList != null)
        {
            viewHolder.subTitleTextView.setText(mSubtitleList.get(position));
        }

        return convertView;
    }

    class ViewHolder
    {
        TextView titleTextView;
        TextView subTitleTextView;
    }

}
