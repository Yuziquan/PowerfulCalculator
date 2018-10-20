## PowerfulCalculator（强力计算器）
[![PowerfulCalculator](https://img.shields.io/badge/CalculateArithmeticExpressionForFloatingpoint-v1.0.0-brightgreen.svg)](
https://github.com/Yuziquan/PowerfulCalculator)
[![license](https://img.shields.io/packagist/l/doctrine/orm.svg)](https://github.com/Yuziquan/PowerfulCalculator/blob/master/LICENSE)

### 一、基本功能

* 支持常见的科学运算；
* 支持常用的常数、函数运算；
* 支持复数运算；
* 支持大数运算；
* 支持进制转换运算；
* 支持数字大写转换运算；
* 支持手机软键盘自由输入模式（上帝模式）；
* 支持运算结果的错误提示；
* 支持输入的表达式的各个不同部分的高亮；
* 支持通过光标移动实时修改表达式；




<br/>

***

### 二、运行效果
#### 1. 计算器主界面

<div align=center>
<img src="https://github.com/Yuziquan/PowerfulCalculator/blob/master/Screenshots/1.png" width=250 height=440 />
</div>



<br/>

***

#### 2. 功能区

<div align=center>
<img src="https://github.com/Yuziquan/PowerfulCalculator/blob/master/Screenshots/2.png" width=250 height=440 />
</div>

<br/>

##### 2.1 大数运算

<div align=center>
<img src="https://github.com/Yuziquan/PowerfulCalculator/blob/master/Screenshots/3.png" width=250 height=440 />
</div>

<br/>

##### 2.2 进制转换

<div align=center>
<img src="https://github.com/Yuziquan/PowerfulCalculator/blob/master/Screenshots/4.png" width=250 height=440 />
</div>

<br/>

##### 2.3 数字大写转换（财务管理）

<div align=center>
<img src="https://github.com/Yuziquan/PowerfulCalculator/blob/master/Screenshots/5.png" width=250 height=440 />
</div>

<br/>

##### 2.4 上帝模式（自由输入模式）

<div align=center>
<img src="https://github.com/Yuziquan/PowerfulCalculator/blob/master/Screenshots/6.png" width=250 height=440 />
</div>

<br/>

***

#### 3. 功能栏
##### 3.1 常数栏

<div align=center>
<img src="https://github.com/Yuziquan/PowerfulCalculator/blob/master/Screenshots/7.png" width=250 height=440 />
</div>

<br/>

##### 3.2 函数栏
<div align=center>
<img src="https://github.com/Yuziquan/PowerfulCalculator/blob/master/Screenshots/8.png" width=250 height=440 />
</div>


<br/>

***

### 三、版本迭代

#### 1. V1.0.0

* 实现了基本功能和界面；

<br/>


<br/>

***

### 四、使用帮助
#### 1. 如何进行科学运算
> 输入表达式即可实时运算，您也可以点击按钮 “=” 进行运算。若您点击“=”进行运算，且结果是有效数字，将会被记录在ans常数中，若要引用上次运算结果即可使用ans常数。

#### 2. 清空、删除、复制运算结果 

> 在主界面分别点击C、DEL、Copy按钮即可。

#### 3. 更多的功能

> 点击主界面右下角按钮即可查看功能区。

#### 4. 如何输入常数栏中的常数和函数栏中的函数

> 在主界面上用手往左滑（或点击主界面菜单最右边的箭头标志），即可弹出常数栏和函数栏。直接点击对应的单元格即可输入对应的常数符号或函数符号。

#### 5. 支持通过光标移动实时修改表达式
> 长按某一函数项对应的单元格，便可弹出一个带有该函数基本用法的提示框。

#### 6. 如何表示其它进制的数

> 使用“⑵⑶⑷...”等符号放置到数的末尾来标识该数的进制即可运算。例如，一个16进制数的例子：FFABC⒃\n。

<br/>

***



### 五、参考资料

#### 1. 运算内核
基于[Iraka](https://github.com/Iraka-C)大神的[Calci-kernel](https://github.com/Iraka-C/Calci-kernel), 并修改部分代码，加入一些自己的自定义函数，辅助该App的开发。

<br/>

#### 2. 计算器App的基本实现逻辑
##### 2.1 基本逻辑和界面参考
基于[王家晔](https://github.com/HK-SHAO)~~大神~~学生开发的[DarkCalculator](https://github.com/HK-SHAO/DarkCalculator)。
<br/>


##### 2.2 改进和优化方面
* 对整个DarkCalculator工程的代码进行重构（重写），删除多余的函数和逻辑，保留核心功能，优化某些部分代码的实现；
* 对整个DarkCalculator工程进行模块分层（在AS等IDE中体现为分包）管理，解耦各个核心模块；
* 将AutofitTextView的引入方式由“DarkCalculator的Java类直接复制引入工程”改为“通过build.gradle文件添加依赖的方式“；
* 依照《阿里巴巴Android开发手册》的标准，对控件、xml文件、变量、资源文件等的命名进行适当的规范化，将整个工程用到的所有常量统一放置到一个常量类中进行管理，所有用到的资源（如字符串值和颜色值）不显示硬编码到xml文件中，而是为每个资源逐一命名并放置到对应的xml文件中进行管理和维护，即strings.xml、colors.xml中；
* 尽量使用Butter Knife代替传统的findViewById，简化代码；
* 尽量使用Lambda表达式，简化代码；
* 依照《阿里巴巴Android开发手册》的规范添加了必要的注释说明，便于阅读代码的人快速理解代码逻辑；
* 对DarkCalculator的主界面运算符一栏的设计进行适当的更改，将原来的“单栏+手指单击/长按”改为“双栏+手指单击”操作。这样一来，虽然点击区域变小了一点，容易误点，但是用户可以看到所有的运算符，且不用花费长按的时间切换另一种操作符，比较方便；
* 将DarkCalculator的“在主界面向右滑动以侧滑菜单的方式显示各大功能区”改为“通过点击主界面右下角悬浮按钮（第三方开源库BoomMenu）以显示各大功能区”，符合Material Design的设计理念，且具有过渡动画，增强了用户体验感；
* 对DarkCalculator原来的几大功能区（大数运算、进制转换、数字大写转换、上帝模式）的布局设计进行适当的改善；
* 对两大功能栏的布局设计进行适当的改善，修复了DarkCalculator不能在一个区域完整显示15个单元格的问题，为带有单位的常数增加了右上角的单位提示（单位为1的常数不进行单位提示）；
