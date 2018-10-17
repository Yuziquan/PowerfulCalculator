package com.wuchangi.powerfulcalculator.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.*;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.wuchangi.powerfulcalculator.R;
import com.wuchangi.powerfulcalculator.adapter.GridViewAdapter;
import com.wuchangi.powerfulcalculator.adapter.MyPageAdapter;
import com.wuchangi.powerfulcalculator.utils.constant.Constants;
import com.wuchangi.powerfulcalculator.utils.helper.ExpressionHelper;
import me.grantland.widget.AutofitHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 计算器主界面
 */
public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.tv_clear_all)
    protected TextView mClearAllTextView;

    @BindView(R.id.tv_delete)
    protected TextView mDeleteTextView;

    @BindView(R.id.tv_copy)
    protected TextView mCopyTextView;

    @BindView(R.id.grid_view_numeric_menu)
    protected GridView mNumericGridViewMenu;

    @BindView(R.id.tv_add)
    protected TextView mAddTextView;

    @BindView(R.id.tv_sub)
    protected TextView mSubTextView;

    @BindView(R.id.tv_mul)
    protected TextView mMulTextView;

    @BindView(R.id.tv_div)
    protected TextView mDivTextView;

    @BindView(R.id.tv_percent)
    protected TextView mPercentTextView;

    @BindView(R.id.tv_power)
    protected TextView mPowerTextView;

    @BindView(R.id.tv_degree)
    protected TextView mDegreeTextView;

    @BindView(R.id.tv_factorial)
    protected TextView mFactorialTextView;

    @BindView(R.id.tv_square_root)
    protected TextView mSquareRootTextView;

    @BindView(R.id.tv_infinity)
    protected TextView mInfinityTextView;

    @BindView(R.id.tv_imaginary_num_unit)
    protected TextView mImaginaryNumUnitTextView;

    @BindView(R.id.tv_double_brace)
    protected TextView mDoubleBraceTextView;

    @BindView(R.id.tv_comma)
    protected TextView mCommaTextView;

    @BindView(R.id.tv_x_parameter)
    protected TextView mXParameterTextView;

    @BindView(R.id.iv_drawer_left_arrow)
    protected ImageView drawerRightImageView;

    @BindView(R.id.drawer)
    protected DrawerLayout mDrawer;

    @BindView(R.id.tab_bar)
    protected TabLayout mTabBar;

    @BindView(R.id.view_pager)
    protected ViewPager mViewPager;

    private BoomMenuButton mBoomMenuButton;

    @BindView(R.id.et_expression_input)
    protected EditText mExpressionInputEditText;

    @BindView(R.id.tv_expression_result)
    protected TextView mExpressionResultTextView;

    /**
     * 当前是否在运算中
     */
    private static boolean sIsCalculating = false;

    /**
     * tab页
     */
    private List<View> mPageViewList = new ArrayList<>();

    private SharedPreferences  mSharedPreferences;

    private MenuItem mGodMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        ButterKnife.bind(this);

        initView();
    }


    /**
     * 界面初始化
     */
    private void initView()
    {
        initTransparentStatusBar();
        initEditText();
        initNumericMenu();
        initBMB();
        initPageViews();
        initTabPages();
    }


    /**
     * 初始化透明状态栏
     */
    private void initTransparentStatusBar()
    {
        //实现透明状态栏效果
        if (Build.VERSION.SDK_INT >= 21)
        {
            View decorView = getWindow().getDecorView();

            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            decorView.setSystemUiVisibility(option);

            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setTitle("科学计算");
        }

    }


    /**
     * 初始化表达式输入框
     */
    private void initEditText()
    {
        AutofitHelper.create(mExpressionInputEditText).setMinTextSize(20).setMaxLines(1);

        mExpressionInputEditText.addTextChangedListener(new TextWatcher()
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
                    mExpressionResultTextView.setText("");
                    return;
                }

                if (!sIsCalculating)
                {
                    mExpressionResultTextView.setText("运算中...");
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


                int startIndex = mExpressionInputEditText.getSelectionStart();

                if (startIndex >= 2 && s.toString().substring(startIndex - 2, startIndex).equals("()"))
                {
                    startIndex--;
                }

                mExpressionInputEditText.setSelection(startIndex);
            }
        });
    }


    /**
     * 初始化数字菜单
     */
    private void initNumericMenu()
    {
        mNumericGridViewMenu.setNumColumns(3);

        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, Arrays.asList(Constants.NUMERIC_MENU_ITEM_TITLES), null, R.layout.cell_numeric);

        mNumericGridViewMenu.setAdapter(gridViewAdapter);

        mNumericGridViewMenu.setOnItemClickListener((parent, view, position, id) ->
        {
            if (Constants.NUMERIC_MENU_ITEM_TITLES[position].equals("="))
            {
                if (sIsCalculating)
                {
                    Snackbar.make(view, "请等待当前运算完成", Snackbar.LENGTH_SHORT).setAction("停止运算", v ->
                    {
                        ExpressionHelper.stopCalculate();
                        sIsCalculating = false;
                    }).show();

                    return;
                }

                mExpressionResultTextView.setText("运算中...");

                // 用户若点击"="按键，则保存此次运算结果
                calcExpresssion(true);
                return;
            }

            String inputString = Constants.NUMERIC_MENU_ITEM_TITLES[position];
            modifyExpressionInputEditView(inputString);
        });
    }


    /**
     * 初始化主界面右下角的BMB菜单按钮
     */
    private void initBMB()
    {
        mBoomMenuButton = findViewById(R.id.bmb_menu);

        for (int i = 0; i < mBoomMenuButton.getPiecePlaceEnum().pieceNumber(); i++)
        {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder().listener(index -> handleBMBItemsClick(index)).normalImageRes(Constants.BMB_MENU_ITEM_ICON_IDS[i]).normalText(Constants.BMB_MENU_ITEM_TITLES[i]).normalColor(Constants.BMB_MENU_ITEM_BG_COLORS[i]);

            mBoomMenuButton.addBuilder(builder);
        }
    }


    /**
     * 初始化两个Tab页的网格布局
     */
    private void initPageViews()
    {
        GridView constantsPageView = new GridView(this);
        GridView functionsPageView = new GridView(this);

        constantsPageView.setNumColumns(3);
        functionsPageView.setNumColumns(3);

        GridViewAdapter constantsGridViewAdapter = new GridViewAdapter(this, Arrays.asList(Constants.CONSTANT_SYMBOLS), Arrays.asList(Constants.CONSTANT_DESCRIPTIONS), R.layout.cell_constant);

        GridViewAdapter functionsGridViewAdapter = new GridViewAdapter(this, Arrays.asList(Constants.FUNCTION_SYMBOLS), Arrays.asList(Constants.FUNCTION_DESCRIPTIONS), R.layout.cell_function);

        constantsPageView.setAdapter(constantsGridViewAdapter);
        functionsPageView.setAdapter(functionsGridViewAdapter);

        constantsPageView.setOnItemClickListener((parent, view, position, id) ->
        {
            modifyExpressionInputEditView(Constants.CONSTANT_SYMBOLS[position]);
        });


        functionsPageView.setOnItemClickListener((parent, view, position, id) ->
        {
            modifyExpressionInputEditView(Constants.FUNCTION_SYMBOLS[position].equals("gamma") ? "Γ": Constants.FUNCTION_SYMBOLS[position]+"()");
        });


        functionsPageView.setOnItemLongClickListener((parent, view, position, id) ->
        {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
            dialogBuilder.setTitle(Constants.FUNCTION_SYMBOLS[position]);
            dialogBuilder.setMessage(Constants.FUNCTIONS_HELP_MAP.get(Constants.FUNCTION_SYMBOLS[position]));
            dialogBuilder.setPositiveButton("确定", null);
            dialogBuilder.show();

            return true;
        });

        mPageViewList.add(constantsPageView);
        mPageViewList.add(functionsPageView);
    }


    /**
     * Tab页初始化
     */
    private void initTabPages()
    {
        MyPageAdapter myPageAdapter = new MyPageAdapter(mPageViewList);
        mViewPager.setAdapter(myPageAdapter);

        mTabBar.setupWithViewPager(mViewPager);
        mTabBar.getTabAt(0).setText("常数");
        mTabBar.getTabAt(1).setText("函数");



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                if (position == 0)
                {
                    mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.END);
                }
                else
                {
                    mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN, GravityCompat.END);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });


    }


    /**
     * 修改表达式输入框的文字
     */
    private void modifyExpressionInputEditView(String str)
    {
        int startIndex = mExpressionInputEditText.getSelectionStart();
        int endIndex = mExpressionInputEditText.getSelectionEnd();

        if (startIndex == endIndex)
        {
            mExpressionInputEditText.getText().insert(startIndex, str);
        }
        else
        {
            mExpressionInputEditText.getText().replace(startIndex, endIndex, str);
        }
    }


    /**
     * 处理输入表达式的清空事件
     */
    private void handleClearAll()
    {
        ExpressionHelper.stopCalculate();
        mExpressionInputEditText.setText("");
    }


    /**
     * 处理输入表达式的删除事件
     */
    private void handleDelete()
    {
        Editable editable = mExpressionInputEditText.getText();

        int startIndex = mExpressionInputEditText.getSelectionStart();
        int endIndex = mExpressionInputEditText.getSelectionEnd();

        if (startIndex == endIndex)
        {
            if (startIndex == 0)
            {
                return;
            }

            editable.delete(startIndex - 1, startIndex);
        }
        else
        {
            editable.delete(startIndex, endIndex);
        }
    }


    /**
     * 处理输入表达式运算结果的复制事件
     */
    private void handleCopy(View v)
    {
        ClipboardManager clipboardManager = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("运算结果", mExpressionResultTextView.getText().toString());
        clipboardManager.setPrimaryClip(clipData);

        Snackbar.make(v, "已复制运算结果", Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 处理界面上的所有点击事件
     */
    @OnClick({
            R.id.tv_clear_all, R.id.tv_delete, R.id.tv_copy, R.id.iv_drawer_left_arrow, R.id.tv_add, R.id.tv_sub, R.id.tv_mul, R.id.tv_div, R.id.tv_percent, R.id.tv_power, R.id.tv_degree, R.id.tv_factorial, R.id.tv_square_root, R.id.tv_infinity, R.id.tv_imaginary_num_unit, R.id.tv_double_brace, R.id.tv_comma, R.id.tv_x_parameter
    })
    public void handleAllClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tv_clear_all:
                handleClearAll();
                break;

            case R.id.tv_delete:
                handleDelete();
                break;

            case R.id.tv_copy:
                handleCopy(v);
                break;

            case R.id.iv_drawer_left_arrow:
                mDrawer.openDrawer(Gravity.END);
                break;

            case R.id.tv_add:
                modifyExpressionInputEditView("+");
                break;

            case R.id.tv_sub:
                modifyExpressionInputEditView("-");
                break;

            case R.id.tv_mul:
                modifyExpressionInputEditView("×");
                break;

            case R.id.tv_div:
                modifyExpressionInputEditView("÷");
                break;

            case R.id.tv_percent:
                modifyExpressionInputEditView("%");
                break;

            case R.id.tv_power:
                modifyExpressionInputEditView("^");
                break;

            case R.id.tv_degree:
                modifyExpressionInputEditView("°");
                break;

            case R.id.tv_factorial:
                modifyExpressionInputEditView("!");
                break;

            case R.id.tv_square_root:
                modifyExpressionInputEditView("√");
                break;

            case R.id.tv_infinity:
                modifyExpressionInputEditView("∞");
                break;

            case R.id.tv_imaginary_num_unit:
                modifyExpressionInputEditView("i");
                break;

            case R.id.tv_double_brace:
                modifyExpressionInputEditView("()");
                break;

            case R.id.tv_comma:
                modifyExpressionInputEditView(",");
                break;

            case R.id.tv_x_parameter:
                modifyExpressionInputEditView("x");
                break;

        }
    }


    /**
     * 设置每个BMB菜单按钮的监听事件
     *
     * @param index 每个BMB菜单按钮的索引
     */
    private void handleBMBItemsClick(int index)
    {
        switch (index)
        {
            case 0:
                BigDecimalActivity.actionStart(this);
                break;

            case 1:
                RadixConversionActivity.actionStart(this);
                break;

            case 2:
                CapitalDecimalActivity.actionStart(this);
                break;

            case 3:
                GodModeActivity.actionStart(this);
                break;

            case 4:
                HelpActivity.actionStart(this);
                break;

            case 5:
                AboutActivity.actionStart(this);
                break;

            case 6:
                finish();
                break;
        }
    }



    /**
     * 使得EditText有光标且不弹出软键盘
     */
    private void hideSoftKeyboardWithCursor()
    {
        mExpressionInputEditText.requestFocus();
        mExpressionInputEditText.requestFocusFromTouch();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        int currentVersion = Build.VERSION.SDK_INT;

        // 4.0以上和4.2以上方法名有所改变
        String methodName = null;
        if (currentVersion >= 16)
        {
            // 4.2以上
            methodName = "setShowSoftInputOnFocus";
        }
        else if (currentVersion >= 14)
        {
            // 4.0以上
            methodName = "setSoftInputShownOnFocus";
        }


        Class<EditText> cls = EditText.class;
        Method setShowSoftInputOnFocus;
        try
        {
            setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(mExpressionInputEditText, false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        inputMethodManager.hideSoftInputFromWindow(mExpressionInputEditText.getWindowToken(), 0);
    }


    /**
     * 使得EditText有光标且弹出软键盘
     */
    private void displaySoftKeyboardWithCursor()
    {
        mExpressionInputEditText.requestFocus();
        mExpressionInputEditText.requestFocusFromTouch();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        int currentVersion = Build.VERSION.SDK_INT;

        // 4.0以上和4.2以上方法名有所改变
        String methodName = null;
        if (currentVersion >= 16)
        {
            // 4.2以上
            methodName = "setShowSoftInputOnFocus";
        }
        else if (currentVersion >= 14)
        {
            // 4.0以上
            methodName = "setSoftInputShownOnFocus";
        }


        Class<EditText> cls = EditText.class;
        Method setShowSoftInputOnFocus;
        try
        {
            setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(mExpressionInputEditText, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        inputMethodManager.showSoftInput(mExpressionInputEditText, InputMethodManager.SHOW_FORCED);
    }


    /**
     * 计算用户输入的表达式
     *
     * @param isSave 是否保存运算结果
     */
    private void calcExpresssion(boolean isSave)
    {
        sIsCalculating = true;

        String expression = mExpressionInputEditText.getText().toString();

        new Thread(() ->
        {
            String[] result = ExpressionHelper.calculate(expression);

            MainActivity.this.runOnUiThread(() ->
            {
                // 运算表达式语法正确
                if (result[1].equals("false"))
                {
                    if (isSave)
                    {
                        // 保存运算结果
                        Constants.lastAnswerValue = result[0];
                    }

                    // 数值太大，跳转到大数运算结果界面查看结果
                    if (result[0].getBytes().length > 1000)
                    {
                        BigDecimalResultActivity.actionStart(MainActivity.this, result[0]);
                        return;
                    }
                    else
                    {
                        mExpressionResultTextView.setText(result[0]);
                    }

                }
                else
                {
                    mExpressionResultTextView.setText(result[0]);
                }

                sIsCalculating = false;
            });

        }).start();

    }

}
