package com.wuchangi.powerfulcalculator.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.wuchangi.powerfulcalculator.R;
import com.wuchangi.powerfulcalculator.base.BaseActivity;

/**
 * 关于界面
 */
public class AboutActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("关于");
        }
    }

    public static void actionStart(Context context)
    {
        context.startActivity(new Intent(context, AboutActivity.class));
    }
}
