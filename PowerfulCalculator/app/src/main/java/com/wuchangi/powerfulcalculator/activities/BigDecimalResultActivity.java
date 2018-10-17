package com.wuchangi.powerfulcalculator.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.wuchangi.powerfulcalculator.R;
import com.wuchangi.powerfulcalculator.base.BaseActivity;

/**
 * 显示两个大数进行某个运算之后所得结果的界面
 */
public class BigDecimalResultActivity extends BaseActivity
{
    @BindView(R.id.tv_big_decimal_result)
    protected TextView mBigDecimalResultTextView;

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
        mBigDecimalResultTextView.setText(result);
    }


    @OnClick(R.id.btn_copy_big_decimal_result)
    public void CopyResult(View v)
    {
        ClipboardManager clipboardManager = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("结果", mBigDecimalResultTextView.getText().toString());
        clipboardManager.setPrimaryClip(clipData);

        Snackbar.make(v, "已复制结果", Snackbar.LENGTH_SHORT).show();
    }


    public static void actionStart(Context context, String result)
    {
        Intent intent = new Intent(context, BigDecimalResultActivity.class);
        intent.putExtra("result", result);
        context.startActivity(intent);
    }
}
