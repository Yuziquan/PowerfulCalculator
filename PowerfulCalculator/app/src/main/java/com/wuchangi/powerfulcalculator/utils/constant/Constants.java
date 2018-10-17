package com.wuchangi.powerfulcalculator.utils.constant;

import android.graphics.Color;
import com.wuchangi.powerfulcalculator.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by WuchangI on 2018/10/11.
 */

/**
 * 该类放置整个应用使用到的常量
 */
public class Constants
{
    /**
     * 保存的上次运算的结果值
     */
    public static String lastAnswerValue = null;

    /**
     * BMB菜单项的图标id
     */
    public static final int[] BMB_MENU_ITEM_ICON_IDS = {R.drawable.big_num_icon, R.drawable.radix_icon, R.drawable.money_icon
            , R.drawable.god_icon, R.drawable.help_icon, R.drawable.about_icon, R.drawable.exit_icon};


    /**
     * BMB菜单项的标题
     */
    public static final String[] BMB_MENU_ITEM_TITLES = {"大数计算", "进制转换", "数字大写转换(财务管理)", "上帝模式"
    , "帮助", "关于", "退出本应用"};


    /**
     * BMB菜单项的背景色
     */
    public static final int[] BMB_MENU_ITEM_BG_COLORS = {Color.parseColor("#FFFF00"), Color.parseColor("#007FFF")
            , Color.parseColor("#00FFFF"), Color.parseColor("#FF0000"), Color.parseColor("#7F00FF")
            , Color.parseColor("#00FF7F"), Color.parseColor("#FF7F00")};

    /**
     * 数字菜单项的标题
     */
    public static final String[] NUMERIC_MENU_ITEM_TITLES = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "="
            ,"A", "B", "C", "D", "E", "F", "⑵", "⑶", "⑷", "⑸", "⑹", "⑺", "⑻", "⑼", "⑽", "⑾", "⑿", "⒀", "⒁"
            , "⒂", "⒃"};


    /**
     * 常量符号
     */
    public static final String[] CONSTANT_SYMBOLS = {"ans", "reg", "π", "e", "F", "h", "ћ", "γ", "φ", "c", "N", "R", "K"
            , "k", "G", "Φ", "true", "false", "me", "mn", "mp"};

    /**
     * 对于常量的相关说明
     */
    public static final String[] CONSTANT_DESCRIPTIONS = {"上次运算结果", "寄存器", "圆周率", "自然底数"
            , "法拉第常数(C/mol)", "普朗克常数(J·s)", "约化普朗克常数(J·s)", "欧拉常数", "黄金分割率", "光速(m/s)"
            , "阿伏伽德罗常数(/mol)", "理想气体常数(J/(mol·K))", "辛钦常数", "玻尔兹曼常数(J/K)", "万有引力常数(N·m2/kg2)"
            , "磁通量量子(Wb)", "真", "假", "电子质量(kg)", "质子质量(kg)", "中子质量(kg)"};

    /**
     * 函数符号
     */
    public static final String[] FUNCTION_SYMBOLS = {"sqrt", "cbrt", "root", "rand", "randInt", "abs", "lg", "ln", "log"
            , "min", "max", "fact", "sin", "cos", "tan", "asin", "acos", "atan", "re", "im", "arg", "norm", "reg", "conj"
            , "diff", "sum", "lim", "eval", "fzero", "integ", "exp", "gcd", "lcm", "perm", "comb", "gamma", "round", "floor"
            , "ceil", "sign", "remn", "prime", "isPrime", "prec", "base"};


    /**
     * 对于函数的相关说明
     */
    public static final String[] FUNCTION_DESCRIPTIONS = {"平方根", "立方根", "开方", "随机复数", "随机整数"
            , "绝对值", "常用对数", "自然对数", "对数", "最小", "最大", "阶乘", "正弦", "余弦", "正切", "反正弦", "反余弦"
            , "反正切", "实部", "虚部", "辐角", "模长", "寄存", "共轭复数", "导函数", "累加求和", "极限", "求值", "函数零点"
            , "定积分", "e底指数", "最大公约", "最小公倍", "排列", "组合", "伽玛函数", "四舍五入", "向下取整", "向上取整", "取正负号"
            , "取余", "质数", "判断质数", "输出精度", "输出进制"};


    /**
     * 运算符的正则表达式
     */
    public static final Pattern OPERATORS_PATTERN = Pattern.compile("[+\\-×÷%^°!√∞i(),x]");

    /**
     * 常量名关键字的正则表达式1
     */
    public static final Pattern CONSTANTS_KEYWORDS1_PATTERN = Pattern.compile("\\b(" + "ans|reg|true|false|me|mn|mp" + ")\\b");

    /**
     * 常量名关键字的正则表达式2
     */
    public static final Pattern CONSTANTS_KEYWORDS2_PATTERN = Pattern.compile("[πeFhћγφcNRkGΦ]");


    /**
     * 函数符号关键字的正则表达式
     */
    public static final Pattern FUNCTIONS_KEYWORDS_PATTERN = Pattern.compile("\\b(" + "sqrt|cbrt|root|rand|randInt|lg|ln|log|abs|min|max|fact|"
            + "sin|cos|tan|asin|acos|atan|re|im|arg|norm|reg|conj|diff|"
            + "sum|lim|eval|fzero|integ|exp|gcd|lcm|perm|comb|round|floor|"
            + "ceil|sign|gamma|remn|prime|isPrime|prec|base|Γ" + ")\\b");


    /**
     * 阿拉伯数字的大写
     */
    public static final char[] CAPITAL_NUMBERS = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};

    /**
     * 钱款单位
     */
    public static final char[] MONEY_UNITS = {
            '纳', '微', '毫', '厘', '分', '角', '元', '拾', '佰', '仟', '萬', '拾', '佰', '仟', '億',
            '拾', '佰', '仟', '兆', '拾', '佰', '仟', '京', '拾', '佰', '仟', '垓', '拾', '佰', '仟',
            '杼', '拾', '佰', '仟', '穰', '拾', '佰', '仟', '溝', '拾', '佰', '仟', '澗', '拾', '佰',
            '仟', '正', '拾', '佰', '仟', '載', '拾', '佰', '仟', '極', '拾', '佰', '仟'};


    /**
     * 常数符号以及对应的常数值
     */
    public static final Map<String, String> CONSTANTS_VALUE_MAP = new HashMap<String, String>()
    {
        {
            put("ans", "0.0");           // 上次运算过程的结果（上次按了“=”后的结果）
            put("F", "9.64853399E4");    // 法拉第常数（C/mol）
            put("h", "6.62606876E-34");  // 普朗克常数（J·s）
            put("ћ", "1.0545718E-34");   // 约化普朗克常数（J·s）
            put("γ", "0.5772156649");    // 欧拉常数
            put("φ", "0.61803398875");   // 黄金分割率
            put("c", "299792458");       // 光速（m/s）
            put("N", "6.0221409E23");    // 阿伏伽德罗常数（/mol）
            put("R", "8.3144621");       // 理想气体常数（J/(mol·K)）
            put("K", "2.6854520010");    // 辛钦常数
            put("k", "1.38064852E-23");  // 玻尔兹曼常数（J/K）
            put("G", "6.67E-11");        // 万有引力常数（N·m2/kg2）
            put("Φ", "2.067833636E-15"); // 磁通量量子（Wb）
            put("true", "1");            // 真
            put("false", "0");           // 假
            put("me", "9.10938188E-31"); // 电子质量（kg）
            put("mn", "1.67262158E-27"); // 质子质量（kg）
            put("mp", "1.67492716E-27"); // 中子质量（kg）
        }
    };








    /**
     * 函数符号以及对应的使用说明
     */
    public static final Map<String, String> FUNCTIONS_HELP_MAP = new HashMap<String, String>()
    {
        {
            put("sqrt", "sqrt函数返回输入参数的平方根，需要1个参数");
            put("cbrt", "cbrt函数返回输入参数的立方根，需要1个参数");
            put("root", "root函数返回输入参数的根，需要2个参数，第1个参数为所开方的数，第2个是开方的次数");
            put("rand", "rand函数返回一个随机复数，不需要参数，但也可以输入一个使其缩放的参数");
            put("randInt", "randInt函数返回一个输入参数指定范围的随机整数，需要2个参数以指定随机整数的范围");
            put("abs", "abs函数返回输入参数的绝对值，但不支持复数，建议使用norm函数，需要1个参数");
            put("lg", "lg函数返回输入参数以10为底输入参数的对数，需要1个参数");
            put("ln", "ln函数返回输入参数以自然底数为底输入参数的对数，需要1个参数");
            put("log", "log函数返回输入参数的对数，需要2个参数，第1个参数为底数，第2个参数为真数");
            put("min", "min函数返回两个输入参数中最小的参数，需要2个参数");
            put("max", "max函数返回两个输入参数中最大的参数，需要2个参数");
            put("fact", "fact函数返回输入参数的阶乘，支持大数运算，需要1个参数");
            put("sin", "sin函数返回输入参数的正弦，需要1个参数");
            put("cos", "cos函数返回输入参数的余弦，需要1个参数");
            put("tan", "tan函数返回输入参数的正切，需要1个参数");
            put("asin", "asin函数返回输入参数的反正弦，需要1个参数");
            put("acos", "acos函数返回输入参数的反余弦，需要1个参数");
            put("atan", "atan函数返回输入参数的反正切，需要1个参数");
            put("re", "re函数返回输入参数的实部，需要1个参数");
            put("im", "im函数返回输入参数的虚部，需要1个参数");
            put("arg", "arg函数返回输入参数的辐角，需要1个参数");
            put("norm", "norm函数返回输入参数的模长，需要1个参数");
            put("reg", "reg函数返回输入参数自身，用于暂时的存储一个值，需要的时候使用reg即可返回寄存的值，需要1个参数");
            put("conj", "conj函数返回输入参数的共轭，需要1个参数");
            put("diff", "diff函数返回输入参数导函数的值，至少需要2个参数，第1个参数为携带变量x的函数，第2个参数为x的值，如果你需要设置求导方向，你也可以将其输入为第3个参数");
            put("sum", "sum函数返回输入参数累加求和的值，需要3个参数，第1个参数为携带变量x的函数，第2个参数为累加的开始值，第3个参数为累加的结束值");
            put("lim", "lim函数返回给定函数在某一点或无穷处的极限，至少需要2个参数，你也可以输入3个参数，第1个参数为携带变量x的函数，第2个参数为变量x趋近的值，第3个为求极限的方向值");
            put("eval", "eval函数返回给定函数变量x为某个值时函数的值，需要2个参数，第1个参数为携带变量x的函数，第2个参数为变量x的值");
            put("fzero", "fzero函数返回给定函数值为0时变量x的值，至少需要1个参数，第1个参数为携带变量x的函数，如果计算时间太长或者计算失败，你可以尝试估算函数值为0时x的值然后输入为第2个参数作为寻找函数值为0时的初始值，这样可以增加运算速度和成功率");
            put("integ", "integ函数返回输入参数的定积分，需要3个参数，第1个参数为携带变量x的函数，第2个参数为积分的下限，第3个参数为积分的上限");
            put("exp", "exp函数返回以自然底数为底输入参数次幂的值，需要1个参数");
            put("gcd", "gcd函数返回输入的两个参数的最大公约数，需要2个参数");
            put("lcm", "lcm函数返回输入的两个参数的最小公倍数，需要2个参数");
            put("perm", "perm函数返回输入参数的排列数量，需要2个参数，第1个参数为互异元素的数量，第2个参数为取出的数量");
            put("comb", "comb函数返回输入参数的组合数量，需要2个参数，第1个参数为互异元素的数量，第2个参数为取出的数量");
            put("gamma", "gamma函数返回输入参数的欧拉第二积分，需要1个参数");
            put("round", "round函数返回输入参数四舍五入的值，至少需要1个参数，也可以输入2个参数，第1个参数为需要四舍五入的值，第2个是需要保留的小数位");
            put("floor", "floor函数返回输入参数向下取整的值，需要1个参数");
            put("ceil", "ceil函数返回输入参数向上取整的值，需要1个参数");
            put("sign", "sign函数返回输入参数的正负性，若为正数返回1，若为0返回0，若为负数返回-1，需要1个参数");
            put("remn", "remn函数返回输入参数的余数，需要2个参数，第1个参数为被除数，第2个参数为余数");
            put("prime", "prime函数返回第输入参数个的质数，需要1个参数");
            put("isPrime", "isPrime函数返回输入参数是否为质数，需要1个参数");
            put("prec", "prec函数用于设置输出的精度，需要1个参数，当没有给参数时将输出的精度设置为默认");
            put("base", "base函数用于设置输出的进制，需要1个参数，当没有给参数时将输出的进制设置为默认");
        }
    };





}
