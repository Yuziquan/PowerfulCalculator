package com.wuchangi.powerfulcalculator.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.wuchangi.powerfulcalculator.R;
import com.wuchangi.powerfulcalculator.base.BaseActivity;

import java.math.BigInteger;

/**
 * 进制转换界面
 */
public class RadixConversionActivity extends BaseActivity
{
    @BindView(R.id.et_original_num)
    protected EditText mOriginalNumEditText;

    @BindView(R.id.seekbar_original_num_radix)
    protected SeekBar mOriginalNumRadixSeekbar;

    @BindView(R.id.tv_original_num_radix)
    protected TextView mOriginalNumRadixTextView;

    @BindView(R.id.tv_result_num)
    protected TextView mResultNumTextView;

    @BindView(R.id.seekbar_result_num_radix)
    protected SeekBar mResultNumRadixSeekbar;

    @BindView(R.id.tv_result_num_radix)
    protected TextView mResultNumRadixTextView;

    @BindView(R.id.btn_copy_result)
    protected Button mCopyResultButton;

    private int mOriginalNumRadixProgress = 10;

    private int mResultNumRadixProgress = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radix_conversion);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);

        initSeekBars();
        initOriginNumEditText();
    }


    /**
     * 为两个SeekBar绑定监听器
     */
    private void initSeekBars()
    {
        mOriginalNumRadixSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                mOriginalNumRadixProgress = progress + 2;
                mOriginalNumRadixTextView.setText(mOriginalNumRadixProgress + " 进制");

                String convertResult = "...";

                try
                {
                    convertResult = new BigInteger(mOriginalNumEditText.getText().toString(),
                            mOriginalNumRadixProgress).toString(mResultNumRadixProgress);
                }
                catch (Exception e)
                {

                }

                mResultNumTextView.setText(convertResult);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        mResultNumRadixSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                mResultNumRadixProgress = progress + 2;
                mResultNumRadixTextView.setText(mResultNumRadixProgress + " 进制");

                String convertResult = "...";

                try
                {
                    convertResult = new BigInteger(mOriginalNumEditText.getText().toString(),
                            mOriginalNumRadixProgress).toString(mResultNumRadixProgress);
                }
                catch(Exception e)
                {

                }

                mResultNumTextView.setText(convertResult);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    /**
     * 为mOriginalNumEditText绑定监听器
     */
    private void initOriginNumEditText()
    {
        mOriginalNumEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String convertResult = new BigInteger(s.toString(),
                        mOriginalNumRadixProgress).toString(mResultNumRadixProgress);

                mResultNumTextView.setText(convertResult);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }


    /**
     * 复制运算结果到剪贴板
     */
    @OnClick(R.id.btn_copy_result)
    public void CopyResult(View v)
    {
        ClipboardManager clipboardManager = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("转换结果", mResultNumTextView.getText().toString());
        clipboardManager.setPrimaryClip(clipData);

        Snackbar.make(v, "已复制转换结果", Snackbar.LENGTH_SHORT).show();
    }



    public static void actionStart(Context context)
    {
        context.startActivity(new Intent(context, RadixConversionActivity.class));
    }

}
