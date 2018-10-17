package com.wuchangi.powerfulcalculator.utils.helper;

/**
 * Created by WuchangI on 2018/10/13.
 */

import com.wuchangi.powerfulcalculator.utils.complex.Expression;
import com.wuchangi.powerfulcalculator.utils.complex.Result;

/**
 * 表达式（字符串形式）计算辅助类
 */
public class ExpressionHelper
{
    private static Expression mExpression = null;

    /**
     * 运算表达式
     * @param expressionString 表达式的字符串形式
     * @return 运算结果,其中第一个字段表示实际返回结果，第二个字段表示表达式是否非法
     */
    public static String[] calculate(String expressionString)
    {
        try
        {
            mExpression = new Expression(expressionString);
            Result result = mExpression.value();

            // 运算结束
            mExpression = null;

            // 运算结果（不一定是数值，有可能是提示语，因为运算表达式可能非法）
            String resultValue = result.val.toString();

            switch(result.getError())
            {
                // 运算表达式正确，resultValue为结果数值
                case 0:
                    return new String[]{resultValue, "false"};

                // 运算表达式非法，resultValue为提示语
                case 1:
                    return new String[]{resultValue, "true"};

                // 运算表达式正确，但强制停止运算
                case 2:
                    return new String[]{"已强制停止运算", "false"};

                // 运算表达式非法，因为向不支持复数的函数中传入复数入参
                case 3:
                    return new String[]{"函数不支持复数", "true"};

                default:
                    throw new Exception();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new String[]{"未知错误", "true"};
        }
    }

    /**
     * 停止运算过程
     */
    public static void stopCalculate()
    {
        if(mExpression != null)
        {
            mExpression.stopEvaluation();
            mExpression = null;
        }
    }
}
