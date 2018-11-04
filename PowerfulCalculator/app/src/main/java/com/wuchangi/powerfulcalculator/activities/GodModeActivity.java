package com.wuchangi.powerfulcalculator.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.wuchangi.powerfulcalculator.R;
import com.wuchangi.powerfulcalculator.base.BaseActivity;
import com.wuchangi.powerfulcalculator.utils.constant.Constants;
import com.wuchangi.powerfulcalculator.utils.helper.ExpressionHelper;
import me.grantland.widget.AutofitHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 上帝模式界面
 */
public class GodModeActivity extends BaseActivity
{

    @BindView(R.id.et_god_mode_expression_input)
    protected EditText mGodModeExpressionInputEditText;

    @BindView(R.id.tv_god_mode_expression_result)
    protected TextView mGodModeExpressionResultTextView;

    /**
     * 当前是否在运算中
     */
    private static boolean sIsCalculating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_god_mode);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("上帝模式");
        }

        ButterKnife.bind(this);

        initView();
    }


    /**
     * 界面初始化
     */
    private void initView()
    {
        initEditText();
    }


    /**
     * 初始化表达式输入框
     */
    private void initEditText()
    {
        AutofitHelper.create(mGodModeExpressionInputEditText).setMinTextSize(20).setMaxLines(1);

        mGodModeExpressionInputEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (TextUtils.isEmpty(s))
                {
                    mGodModeExpressionResultTextView.setText("");
                    return;
                }

                if (!sIsCalculating)
                {

                    calcExpresssion(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                for (Matcher matcher = Constants.OPERATORS_PATTERN.matcher(s); matcher.find(); )
                {
                    s.setSpan(new ForegroundColorSpan(0xff81d4fa), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                for (Matcher matcher = Pattern.compile("x").matcher(s); matcher.find(); )
                {
                    s.setSpan(new ForegroundColorSpan(0xfff48fb1), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                for (Matcher matcher = Constants.CONSTANTS_KEYWORDS1_PATTERN.matcher(s); matcher.find(); )
                {
                    s.setSpan(new ForegroundColorSpan(0xfffff59d), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                for (Matcher matcher = Constants.CONSTANTS_KEYWORDS2_PATTERN.matcher(s); matcher.find(); )
                {
                    s.setSpan(new ForegroundColorSpan(0xfffff59d), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                for (Matcher matcher = Constants.FUNCTIONS_KEYWORDS_PATTERN.matcher(s); matcher.find(); )
                {
                    s.setSpan(new ForegroundColorSpan(0xffa5d6a7), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }


                int startIndex = mGodModeExpressionInputEditText.getSelectionStart();

                if (startIndex >= 2 && s.toString().substring(startIndex - 2, startIndex).equals("()"))
                {
                    startIndex--;
                }

                mGodModeExpressionInputEditText.setSelection(startIndex);
            }
        });
    }




    /**
     * 计算用户输入的表达式
     *
     * @param isSave 是否保存运算结果
     */
    private void calcExpresssion(boolean isSave)
    {
        mGodModeExpressionResultTextView.setText("运算中...");

        sIsCalculating = true;

        String expression = mGodModeExpressionInputEditText.getText().toString();

        new Thread(() ->
        {
            String[] result = ExpressionHelper.calculate(expression);

            GodModeActivity.this.runOnUiThread(() ->
            {
                // 运算表达式语法正确
                if (result[1].equals("false"))
                {
                    if (isSave)
                    {
                        // 保存运算结果
                        Constants.slastAnswerValue = result[0];
                    }

                    // 数值太大，跳转到大数运算结果界面查看结果
                    if (result[0].getBytes().length > 1000)
                    {
                        BigDecimalResultActivity.actionStart(GodModeActivity.this, result[0]);
                        return;
                    }
                    else
                    {
                        mGodModeExpressionResultTextView.setText(result[0]);
                    }

                }
                else
                {
                    mGodModeExpressionResultTextView.setText(result[0]);
                }

                sIsCalculating = false;
            });

        }).start();

    }

    public static void actionStart(Context context)
    {
        context.startActivity(new Intent(context, GodModeActivity.class));
    }
}
