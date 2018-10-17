package com.wuchangi.powerfulcalculator.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.wuchangi.powerfulcalculator.R;
import com.wuchangi.powerfulcalculator.base.BaseActivity;

/**
 * 显示两个大数进行某个运算之后所得结果的界面
 */
public class BigDecimalResultActivity extends BaseActivity
{
    @BindView(R.id.tv_big_decimal_result)
    protected TextView bigDecimalResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_decimal_result);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("运算结果");
        }

        ButterKnife.bind(this);

        displayResult();
    }

    /**
     * 显示运算结果
     */
    private void displayResult()
    {
        String result = getIntent().getStringExtra("result");
        bigDecimalResultTextView.setText(result);
    }


    public static void actionStart(Context context, String result)
    {
        Intent intent = new Intent(context, BigDecimalResultActivity.class);
        intent.putExtra("result", result);
        context.startActivity(intent);
    }
}
