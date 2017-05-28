package com.xugongming38.calculate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xugongming38.calculate.utils.CalcTool;


public class MyFragment extends Fragment {

    View view;

    // 数字按钮
    private Button mBtn_1;
    private Button mBtn_2;
    private Button mBtn_3;
    private Button mBtn_4;
    private Button mBtn_5;
    private Button mBtn_6;
    private Button mBtn_7;
    private Button mBtn_8;
    private Button mBtn_9;
    private Button mBtn_0;
    private Button mBtn_add;
    private Button mBtn_sub; // 减
    private Button mBtn_multiply; // 乘
    private Button mBtn_divide; // 除
    private Button mBtn_del;// 删除一个
    private Button mBtn_equals; // 等于
    private Button mBtn_point; // 点
    private Button mBtn_clean; // 清除
    private TextView mEdt_play; // 显示运算过程
    private TextView mTv_result;// 显示结果

    // 显示输入的字符串
    StringBuffer mStr_display = new StringBuffer();
    // 暂存运算结果
    String mStr_result = "";
    // 存放运算符
    String mStr_Oper = "";
    // 单个字符
    char mStr_char;
    // 存放结果
    double mValue;
    String mContext;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my, container, false);
        findView();
        // Inflate the layout for this fragment
        return view;
    }

    /**
     * 寻找控件ID
     */
    private void findView() {
        mBtn_0 = (Button) view.findViewById(R.id.btn_0);
        mBtn_1 = (Button) view.findViewById(R.id.btn_1);
        mBtn_2 = (Button) view.findViewById(R.id.btn_2);
        mBtn_3 = (Button) view.findViewById(R.id.btn_3);
        mBtn_4 = (Button) view.findViewById(R.id.btn_4);
        mBtn_5 = (Button) view.findViewById(R.id.btn_5);
        mBtn_6 = (Button) view.findViewById(R.id.btn_6);
        mBtn_7 = (Button) view.findViewById(R.id.btn_7);
        mBtn_8 = (Button) view.findViewById(R.id.btn_8);
        mBtn_9 = (Button) view.findViewById(R.id.btn_9);
        mBtn_add = (Button) view.findViewById(R.id.btn_calc_add);
        mBtn_sub = (Button) view.findViewById(R.id.btn_sub);
        mBtn_multiply = (Button) view.findViewById(R.id.btn_multiply);
        mBtn_divide = (Button) view.findViewById(R.id.btn_divide);
        mBtn_del = (Button) view.findViewById(R.id.btn_del);
        mBtn_equals = (Button) view.findViewById(R.id.btn_equal);
        mBtn_point = (Button) view.findViewById(R.id.btn_point);
        mBtn_clean = (Button) view.findViewById(R.id.btn_clear);
        mEdt_play = (TextView) view.findViewById(R.id.edt_calc);
        mEdt_play.setText("");
        mTv_result = (TextView) view.findViewById(R.id.txt_calc);


        BtnOnClick btn_OnClick = new BtnOnClick();
        mBtn_0.setOnClickListener(btn_OnClick);
        mBtn_1.setOnClickListener(btn_OnClick);
        mBtn_2.setOnClickListener(btn_OnClick);
        mBtn_3.setOnClickListener(btn_OnClick);
        mBtn_4.setOnClickListener(btn_OnClick);
        mBtn_5.setOnClickListener(btn_OnClick);
        mBtn_6.setOnClickListener(btn_OnClick);
        mBtn_7.setOnClickListener(btn_OnClick);
        mBtn_8.setOnClickListener(btn_OnClick);
        mBtn_9.setOnClickListener(btn_OnClick);
        mBtn_add.setOnClickListener(btn_OnClick);
        mBtn_sub.setOnClickListener(btn_OnClick);
        mBtn_multiply.setOnClickListener(btn_OnClick);
        mBtn_divide.setOnClickListener(btn_OnClick);
        mBtn_point.setOnClickListener(btn_OnClick);
        mBtn_equals.setOnClickListener(btn_OnClick);
        mBtn_clean.setOnClickListener(btn_OnClick);
        mBtn_del.setOnClickListener(btn_OnClick);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    class BtnOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 如果mStr_display 这个StringBuffer对象存在
            if (mStr_display != null) {
                // 如果mStr_display里面包含有"="则实例化StringBuffer重新为mStr_display赋值
                if (mStr_display.indexOf("=") != -1) {
                    mStr_display = new StringBuffer();
                    // 显示的字符串只含有运算结果
                    mStr_display.append(mValue);
                    // 如果显示的字符串是以".0"结尾的则删除后".0"
                    if (mStr_display.toString().endsWith(".0")) {
                        mStr_display.delete(mStr_display.length() - 2,
                                mStr_display.length());
                    }
                    mEdt_play.setText(mStr_display.toString());

                }
                // 如果显示的字符串是"无穷大","负无穷","错误数据"则实例化StringBuffer重新为mStr_display赋值
                if (mStr_display.toString().equals("无穷大")
                        || mStr_display.toString().equals("负无穷")
                        || mStr_display.toString().equals("错误数据")) {
                    // 新建一个新的字符串根据其它点击的按钮事件进行相应的操作
                    mStr_display = new StringBuffer();
                    // 在输入框内显示输入的内容
                    mEdt_play.setText(mStr_display.toString());
                }
                // 根据被点按钮的Id响应相应按钮的事件
                switch (v.getId()) {
                    // 点击0按钮的id事件
                    case R.id.btn_0:
                        mStr_display.append("0");
                        mEdt_play.setText(mStr_display.toString());
                        break;
                    // 点击1按钮的id事件
                    case R.id.btn_1:
                    /*
                     * 判断是否需要清空 如果之前已经运算过了 再次输入 则认为是一次新的计算过程 表示显示的内容
                     */
                        if (mStr_display.length() != 0) {
                            // 调用CalcTool的isDigitEnd方法 判断是否有等号如果有等号则清空
                            if (CalcTool.isDigitEnd(mStr_result)) {
                                // 清空输入框内的内容
                                mStr_display = new StringBuffer();
                                // 在清空后的输入框内显示输入的数字1
                                mStr_display.append("1");
                                // 将运算的结果清空
                                mStr_result = "";
                            } else {
                                // 在输入框内显示的内容后面追加1
                                mStr_display.append("1");
                            }
                        } else {
                            // 在输入框内显示的内容后面追加1
                            mStr_display.append("1");
                        }
                        // 在输入框内显示已经输入的字符串
                        mEdt_play.setText(mStr_display.toString());

                        break;
                    // 点击2按钮的id事件
                    case R.id.btn_2:
                    /*
                     * 判断是否需要清空 如果之前已经运算过了 再次输入 则认为是一次新的计算过程 表示在输入数字时输入框内已经存在有内用
                     */
                        if (mStr_display.length() != 0) {
                            // 调用CalcTool的isDigitEnd方法 判断是否有等号如果有等号则清空
                            if (CalcTool.isDigitEnd(mStr_result)) {
                                // 清空输入框内的内容
                                mStr_display = new StringBuffer();
                                // 在清空后的输入框内显示输入的数字2
                                mStr_display.append("2");
                                // 将暂存的结果设置为空，虽然在显示结果的文本框内显示但是与正在进行的新的运算已经没没有关系
                                mStr_result = "";
                            } else {
                                // 在输入框内显示的内容后面追加2
                                mStr_display.append("2");
                            }
                        } else {
                            // 在输入框显示的内容后面追加2
                            mStr_display.append("2");
                        }
                        // 在输入框内显示已存在和新输入的内容
                        mEdt_play.setText(mStr_display.toString());
                        break;
                    // 点击3按钮的id事件
                    case R.id.btn_3:
                        // 判断是否需要清空 如果之前已经运算过了 再次输入 则认为是一次新的计算过程
                        if (mStr_display.length() != 0) {// 表示有内容
                            // 调用CalcTool的isDigitEnd方法 判断是否有等号如果有等号则清空
                            if (CalcTool.isDigitEnd(mStr_result)) {
                                // 清空输入框内的内容
                                mStr_display = new StringBuffer();
                                mStr_display.append("3");
                                // 将暂存的结果设置为空，虽然在显示结果的文本框内显示但是与正在进行的新的运算已经没有关系
                                mStr_result = "";
                            } else {
                                // 在输入框内显示的内容后面追加3
                                mStr_display.append("3");
                            }
                        } else {
                            // 在输入框内显示的内容后面追加3
                            mStr_display.append("3");
                        }
                        // 在输入框内显示已存在和新输入的内容
                        mEdt_play.setText(mStr_display.toString());
                        break;
                    // 点击4按钮的id事件
                    case R.id.btn_4:
                        // 判断是否需要清空 如果之前已经运算过了 再次输入 则认为是一次新的计算过程
                        if (mStr_display.length() != 0) {// 表示有内容
                            // 调用CalcTool的isDigitEnd方法 判断是否有等号如果有等号则清空
                            if (CalcTool.isDigitEnd(mStr_result)) {
                                // 清空输入框内的内容
                                mStr_display = new StringBuffer();
                                mStr_display.append("4");
                                // 将暂存的结果设置为空，虽然在显示结果的文本框内显示但是与正在进行的新的运算已经没有关系
                                mStr_result = "";
                            } else {
                                // 在输入框内显示的内容后面追加4
                                mStr_display.append("4");
                            }
                        } else {
                            // 在输入框内显示的内容后面追加4
                            mStr_display.append("4");
                        }
                        // 在输入框内显示已存在和新输入的内容
                        mEdt_play.setText(mStr_display.toString());
                        break;
                    // 点击5按钮的id事件
                    case R.id.btn_5:
                        // 判断是否需要清空 如果之前已经运算过了 再次输入 则认为是一次新的计算过程
                        if (mStr_display.length() != 0) {// 表示有内容
                            // 调用CalcTool的isDigitEnd方法 判断是否有等号如果有等号则清空
                            if (CalcTool.isDigitEnd(mStr_result)) {
                                // 清空输入框内的内容
                                mStr_display = new StringBuffer();
                                // 在输入框内显示的内容后面追加5
                                mStr_display.append("5");
                                // 将暂存的结果设置为空，虽然在显示结果的文本框内显示但是与正在进行的新的运算已经没有关系
                                mStr_result = "";
                            } else {// 追加5
                                // 在输入框内显示的内容后面追加5
                                mStr_display.append("5");
                            }
                        } else {
                            // 在输入框内显示的内容后面追加5
                            mStr_display.append("5");
                        }
                        // 在输入框内显示已存在和新输入的内容
                        mEdt_play.setText(mStr_display.toString());
                        break;
                    // 点击6按钮的id事件
                    case R.id.btn_6:
                        // 判断是否需要清空 如果之前已经运算过了 再次输入 则认为是一次新的计算过程
                        if (mStr_display.length() != 0) {// 表示有内容
                            // 调用CalcTool的isDigitEnd方法 判断是否有等号如果有等号则清空
                            if (CalcTool.isDigitEnd(mStr_result)) {
                                // 清空输入框内的内容
                                mStr_display = new StringBuffer();
                                mStr_display.append("6");
                                // 将暂存的结果设置为空，虽然在显示结果的文本框内显示但是与正在进行的新的运算已经没有关系
                                mStr_result = "";
                            } else {
                                // 在输入框内显示的内容后面追加6
                                mStr_display.append("6");
                            }
                        } else {
                            // 在输入框内显示的内容后面追加6
                            mStr_display.append("6");
                        }
                        // 在输入框内显示已存在和新输入的内容
                        mEdt_play.setText(mStr_display.toString());
                        break;
                    // 点击7按钮的id事件
                    case R.id.btn_7:
                        // 判断是否需要清空 如果之前已经运算过了 再次输入 则认为是一次新的计算过程
                        if (mStr_display.length() != 0) {// 表示有内容
                            // 调用CalcTool的isDigitEnd方法 判断是否有等号如果有等号则清空
                            if (CalcTool.isDigitEnd(mStr_result)) {
                                // 清空输入框内的内容
                                mStr_display = new StringBuffer();

                                mStr_display.append("7");
                                // 将暂存的结果设置为空，虽然在显示结果的文本框内显示但是与正在进行的新的运算已经没有关系
                                mStr_result = "";
                            } else {// 追加7
                                mStr_display.append("7");
                            }
                        } else {// 追加7
                            mStr_display.append("7");
                        }
                        // 在输入框内显示已存在和新输入的内容
                        mEdt_play.setText(mStr_display.toString());
                        break;
                    // 点击8按钮的id事件
                    case R.id.btn_8:
                        // 判断是否需要清空 如果之前已经运算过了 再次输入 则认为是一次新的计算过程
                        if (mStr_display.length() != 0) {// 表示有内容
                            // 调用CalcTool的isDigitEnd方法 判断是否有等号如果有等号则清空
                            if (CalcTool.isDigitEnd(mStr_result)) {
                                // 清空输入框内的内容
                                mStr_display = new StringBuffer();
                                mStr_display.append("8");
                                // 将暂存的结果设置为空，虽然在显示结果的文本框内显示但是与正在进行的新的运算已经没有关系
                                mStr_result = "";
                            } else {// 追加8
                                mStr_display.append("8");
                            }
                        } else {// 追加8
                            mStr_display.append("8");
                        }
                        // 在输入框内显示已存在和新输入的内容
                        mEdt_play.setText(mStr_display.toString());
                        break;
                    // 点击9按钮的id事件
                    case R.id.btn_9:
                        // 判断是否需要清空 如果之前已经运算过了 再次输入 则认为是一次新的计算过程
                        if (mStr_display.length() != 0) {// 表示有内容
                            // 调用CalcTool的isDigitEnd方法 判断是否有等号如果有等号则清空
                            if (CalcTool.isDigitEnd(mStr_result)) {
                                // 清空输入框内的内容
                                mStr_display = new StringBuffer();
                                mStr_display.append("9");
                                // 将暂存的结果设置为空，虽然在显示结果的文本框内显示但是与正在进行的新的运算已经没有关系
                                mStr_result = "";
                            } else {// 追加9
                                mStr_display.append("9");
                            }
                        } else {// 追加9
                            mStr_display.append("9");
                        }
                        // 在输入框内显示已存在和新输入的内容
                        mEdt_play.setText(mStr_display.toString());
                        break;
                    case R.id.btn_calc_add:// 符号键"+"单击事件
                        mStr_result = "";
                        // 判断是否是以"/0"结尾的
                        if (mStr_display.toString().endsWith("/0")) {
                            //如果是以"/0"结尾的就提示
                            Toast.makeText(getActivity(), "除数不能为0",
                                    Toast.LENGTH_SHORT).show();
                            mStr_display.delete(mStr_display.length() - 2,
                                    mStr_display.length());
                            mEdt_play.setText(mStr_display.toString());
                        } else {
                            mStr_Oper = "+";
                            // 如果显示的不为空
                            if (!(mStr_display.toString() == "")) {
                                // 得到最后一个位置的字符,字符可能是数字也可能是运算符
                                mStr_char = mStr_display.charAt(mStr_display
                                        .length() - 1);
                                // 判断最后一位是否是数字
                                if (Character.isDigit(mStr_char)) {
                                    mStr_display.append(mStr_Oper);
                                    mEdt_play.setText(mStr_display.toString());
                                }
                            }
                        }
                        break;
                    case R.id.btn_sub:// 符号键"-"单击事件
                        mStr_result = "";
                        // 考虑是否是"/0"结尾的
                        if (mStr_display.toString().endsWith("/0")) {
                            // 如果是则显示"除数不能为0"的Toast提示
                            Toast.makeText(getActivity(), "除数不能为0",
                                    Toast.LENGTH_SHORT).show();
                            // 如果是以"/0"结尾则删除"/0"
                            mStr_display.delete(mStr_display.length() - 2,
                                    mStr_display.length());

                            mEdt_play.setText(mStr_display.toString());
                        } else {
                            mStr_Oper = "-";
                            // 如果显示的内容不为空
                            if (mStr_display.length() != 0) {
                                // 得到最后一个位置的字符,字符可能是数字也可能是运算符
                                mStr_char = mStr_display.charAt(mStr_display
                                        .length() - 1);
                                // 判断最后一位是否是数字
                                if (Character.isDigit(mStr_char)) {

                                    mStr_display.append(mStr_Oper);
                                    mEdt_play.setText(mStr_display.toString());
                                }

                            }
                            if (mStr_display.length() == 0) {
                                mStr_display.append("-");
                                mEdt_play.setText(mStr_display.toString());
                            }

                        }
                        break;
                    case R.id.btn_multiply:// 符号键"*"单击事件
                        mStr_result = "";
                        // 考虑是否是/0结尾的
                        if (mStr_display.toString().endsWith("/0")) {
                            // 如果是则
                            Toast.makeText(getActivity(), "除数不能为0",
                                    Toast.LENGTH_SHORT).show();
                            mStr_display.delete(mStr_display.length() - 2,
                                    mStr_display.length());
                            mEdt_play.setText(mStr_display.toString());
                        } else {
                            mStr_Oper = "*";
                            if (mStr_display.toString() != "") {
                                // 得到最后一个位置的字符
                                mStr_char = mStr_display.charAt(mStr_display
                                        .length() - 1);
                                // 如果得到的最后一位是数字
                                if (Character.isDigit(mStr_char)) {
                                    // 在已存在的内容后面追加"*"
                                    mStr_display.append("*");

                                    mEdt_play.setText(mStr_display.toString());
                                }
                            }
                        }

                        break;
                    case R.id.btn_divide:// 符号键"/"单击事件
                        mStr_result = "";
                        // 考虑是否是/0结尾的
                        if (mStr_display.toString().endsWith("/0")) {
                            // 如果是则显示"除数不能为0”的Toast提示
                            Toast.makeText(getActivity(), "除数不能为0",
                                    Toast.LENGTH_SHORT).show();
                            // 如果显示的字符串包含有"/0"则删除"/0"
                            mStr_display.delete(mStr_display.length() - 2,
                                    mStr_display.length());
                            mEdt_play.setText(mStr_display.toString());
                        } else {
                            mStr_Oper = "/";
                            if (mStr_display.toString() != "") {
                                mStr_char = mStr_display.charAt(mStr_display
                                        .length() - 1);// 得到最后一个位置的字符
                                if (Character.isDigit(mStr_char)) {
                                    mStr_display.append(mStr_Oper);
                                    mEdt_play.setText(mStr_display.toString());
                                }
                            }
                        }

                        break;

                    case R.id.btn_point:// 符号键"."单击事件
                        if (mStr_display.length() == 0) {
                            mStr_display.append("0.");
                            mEdt_play.setText(mStr_display.toString());
                        } else {
                            if (mStr_display.toString().endsWith("+")
                                    || mStr_display.toString().endsWith("-")
                                    || mStr_display.toString().endsWith("*")
                                    || mStr_display.toString().endsWith("/")) {
                                mStr_display.append("0.");
                                mEdt_play.setText(mStr_display.toString());
                            } else if (mStr_display.toString().contains(".")
                                    && !mStr_display.toString().contains("+")
                                    && !mStr_display.toString().contains("-")
                                    && !mStr_display.toString().contains("*")
                                    && !mStr_display.toString().contains("/")) {
                                mEdt_play.setText(mStr_display.toString());
                                // 如果一个. 返回true 再次 按 . 返回false
                            } else {
                                mStr_display.append(".");
                                mEdt_play.setText(mStr_display.toString());
                            }
                            // 当前有 . 如果再次按. 就删除掉原来的.
                            if (!CalcTool.isTrue(mStr_display.toString())) {
                                mStr_display
                                        .deleteCharAt(mStr_display.length() - 1);
                            }

                        }

                        break;
                    case R.id.btn_equal:// 符号键"="单击事件
                        if (mStr_display.length() != 0) {// 如果串不为空
                            // 如果串中拥有+-*/运算符
                            if (mStr_display.toString().indexOf("+") != -1
                                    || mStr_display.toString().indexOf("-") != -1
                                    || mStr_display.toString().indexOf("*") != -1
                                    || mStr_display.toString().indexOf("/") != -1) {
                                // 判断结尾是否是+-*/.
                                // 判断结尾是否是数字
                                if (mStr_display.toString().charAt(
                                        mStr_display.length() - 1) != '+'
                                        && mStr_display.toString().charAt(
                                        mStr_display.length() - 1) != '-'
                                        && mStr_display.toString().charAt(
                                        mStr_display.length() - 1) != '*'
                                        && mStr_display.toString().charAt(
                                        mStr_display.length() - 1) != '/'
                                        && mStr_display.toString().charAt(
                                        mStr_display.length() - 1) != '.') {

                                    try {
                                        mValue = Double.parseDouble(CalcTool
                                                .getCacluteMain(mStr_display
                                                        .toString()));
                                        mStr_display = new StringBuffer();
                                        if (mValue >= Float.MAX_VALUE) {
                                            mStr_display.append("无穷大");
                                        } else if (mValue <= -Float.MAX_VALUE) {
                                            mStr_display.append("负无穷");
                                        } else {
                                            mStr_display.append("=");
                                            mStr_display.append(mValue);
                                            if (mStr_display.toString().endsWith(
                                                    ".0")) {
                                                mStr_display.delete(mStr_display
                                                                .toString().length() - 2,
                                                        mStr_display.toString()
                                                                .length());

                                            }
                                        }
                                    } catch (NumberFormatException e) {
                                        mStr_display = new StringBuffer();
                                        mStr_display.append("错误数据");
                                    }
                                    mStr_result = mStr_display.toString();
                                    mTv_result.setText(mStr_display.toString());

                                }
                            }
                        }
                        break;

                    case R.id.btn_clear:// 清空按钮事件
                        mStr_display = new StringBuffer();
                        mStr_result = "";
                        mEdt_play.setText("");
                        mTv_result.setText("");
                        break;

                    case R.id.btn_del:// 退格按钮事件
                        if (mStr_display.length() != 0) {
                            mStr_display.deleteCharAt(mStr_display.length() - 1);
                            mEdt_play.setText(mStr_display.toString());
                        }
                        if (mStr_display.length() == 0) {
                            mEdt_play.setText("");
                        }
                        break;

                }
            }

        }

    }
}

