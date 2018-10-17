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
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.wuchangi.powerfulcalculator.R;
import com.wuchangi.powerfulcalculator.base.BaseActivity;
import com.wuchangi.powerfulcalculator.utils.constant.Constants;
import me.grantland.widget.AutofitHelper;

/**
 * 阿拉伯数字转换成大写数字界面
 */
public class CapitalDecimalActivity extends BaseActivity
{

    @BindView(R.id.et_arab_num)
    protected EditText mArabNumEditText;

    @BindView(R.id.tv_capital_num)
    protected TextView mCapitalNumTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital_decimal);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("数字大写转换(财务管理)");
        }

        ButterKnife.bind(this);

        initView();
    }


    /**
     * 界面初始化
     */
    private void initView()
    {
        AutofitHelper.create(mArabNumEditText);
        mArabNumEditText.requestFocus();

        AutofitHelper.create(mCapitalNumTextView).setMaxLines(6);

        mArabNumEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String arabNumString = s.toString();

                if(arabNumString.length() == 0 | arabNumString.equals("."))
                {
                    mCapitalNumTextView.setText("...");
                    return;
                }

                mCapitalNumTextView.setText(arabNumTransToCapitalNum(arabNumString));
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }


    /**
     * 将阿拉伯数字转换成对应的大写数字
     * @param arabNumString 表示阿拉伯数字的字符串
     * @return 表示大写数字的字符串
     */
    private String arabNumTransToCapitalNum(String arabNumString)
    {
        // 小数点的位置
        int dotIndex = arabNumString.indexOf(".");

        // 如果arabNumString为小数
        if(dotIndex != -1)
        {
            if(arabNumString.substring(dotIndex, arabNumString.length()).length() > 7)
            {
                return "小数点后不能超过六位";
            }
        }

        if(Double.parseDouble(arabNumString) > 10E51)
        {
            return "数值太大，无法转换";
        }

        // arabNumString的整数部分
        String integerPart = "";

        // arabNumString的小数点后部分
        String floatPart = "";

        // 如果arabNumString为小数
        if(dotIndex != -1)
        {
            integerPart = arabNumString.substring(0, dotIndex);
            floatPart = arabNumString.substring(dotIndex + 1);
        }
        else
        {
            integerPart = arabNumString;
        }

        // 转换之后得到的数字的大写形式（钱款格式）
        StringBuilder capitalNumStringBuilder = new StringBuilder();

        for(int i = 0; i < integerPart.length(); i++)
        {
            int number = Integer.parseInt(String.valueOf(integerPart.charAt(i)));
            capitalNumStringBuilder.append(Constants.CAPITAL_NUMBERS[number]);
            capitalNumStringBuilder.append(Constants.MONEY_UNITS[integerPart.length() - i + 5]);
        }

        // 如果arabNumString的小数点后有数字
        if(floatPart.length() >= 1)
        {
            for(int i = 0; i < floatPart.length(); i++)
            {
                int number = Integer.parseInt(String.valueOf(floatPart.charAt(i)));
                capitalNumStringBuilder.append(Constants.CAPITAL_NUMBERS[number]);
                capitalNumStringBuilder.append(Constants.MONEY_UNITS[5 - i]);
            }
        }
        else
        {
            capitalNumStringBuilder.append("整");
        }

        return capitalNumStringBuilder.toString();
    }


    @OnClick(R.id.btn_copy_capital_num)
    public void CopyResult(View v)
    {
        ClipboardManager clipboardManager = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("转换结果", mCapitalNumTextView.getText().toString());
        clipboardManager.setPrimaryClip(clipData);

        Snackbar.make(v, "已复制转换结果", Snackbar.LENGTH_SHORT).show();
    }


    public static void actionStart(Context context)
    {
        context.startActivity(new Intent(context, CapitalDecimalActivity.class));
    }

}
