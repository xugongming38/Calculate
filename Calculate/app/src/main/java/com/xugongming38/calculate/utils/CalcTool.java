package com.xugongming38.calculate.utils;

/**
 * Created by dell on 2017/4/19.
 */

public class CalcTool {
    // 主计算方法参数为一个混合运算字符串
    public static String getCacluteMain(String str) {
        String[] str1 = str.split("[+-]");
        for (int i = 0; i < str1.length; i++) {
            if (str1[i].indexOf("/") > 0 || str1[i].indexOf("*") > 0) {
                str1[i] = getCaculateUnder(str1[i]);
            }
        }
        char stdfc[] = str.toCharArray();
        char stdf[] = new char[50];
        int j = 0;
        for (int i = 0; i < stdfc.length; i++) {
            if (stdfc[i] == '+') {
                stdf[j] = '+';
                j++;

            }
            if (stdfc[i] == '-') {
                stdf[j] = '-';
                j++;

            }
        }
        float stdd[] = new float[50];
        for (int i = 0; i < str1.length; i++)

            if (!str1[i].equals("")) {
                stdd[i] = Float.parseFloat(str1[i]);
            }
        for (int i = 1; i < stdf.length; i++) {
            if (stdf[i - 1] == '+')
                stdd[0] += stdd[i];
            if (stdf[i - 1] == '-')
                stdd[0] -= stdd[i];
        }
        Float d =  Float.valueOf(stdd[0]);
        String result = d.toString();
        return result;
    }

    // 此方法为私有方法只有主方法caculateMain()能调用；此方法用于计算含有“*/”高级字符串
    private static String getCaculateUnder(String str) {

        char stdf[] = new char[50];
        float stdd[] = new float[50];
        String stds[] = str.split("[/*]");
        for (int i = 0; i < stds.length; i++)
            stdd[i] = Float.parseFloat(stds[i]);
        char stdfc[] = str.toCharArray();
        int j = 0, count = 1;
        for (int i = 0; i < stdfc.length; i++) {
            if (stdfc[i] == '/') {
                stdf[j] = '/';
                j++;
                count++;
            }
            if (stdfc[i] == '*') {
                stdf[j] = '*';
                j++;
                count++;
            }
        }
        for (int i = 1; i < count; i++) {
            if (stdf[i - 1] == '/')
                stdd[0] /= stdd[i];
            if (stdf[i - 1] == '*')
                stdd[0] *= stdd[i];
        }
        Float d =  Float.valueOf(stdd[0]);
        String result = d.toString();
        return result;
    }

    // 此函数用于计算包含括号的字符串

    public static String getCaculateHigh(String str) {
        String[] str1;
        str1 = str.split("[(]");
        String str2;
        str2 = getCacluteMain(str1[str1.length - 1]);
        String str3;
        str3 = "(" + str1[str1.length - 1];
        str = str.replace(str3, str2);
        return str;
    }

    // 判断.的位置
    public static boolean isTrue(String str) {
        boolean mFlag = false;
        if (str.length() >= 0) {
            String[] mFrist = str.split("[+-]");
            if (mFrist.length > 0) {

                for (int i = 0; i < mFrist.length; i++) {
                    if (mFrist[i].contains("*") || mFrist[i].contains

                            ("/")) {
                        String[] mSencond = mFrist[i].split("[/*]");
                        for (int j = 0; j < mSencond.length; j++) {
                            if (mSencond[j].indexOf(".") ==

                                    mSencond[j].lastIndexOf(".")) {
                                mFlag = true;
                            } else {
                                mFlag = false;
                                break;
                            }
                        }

                    } else {
                        if (mFrist[i].indexOf(".") == mFrist[i]
                                .lastIndexOf(".")) {
                            mFlag = true;
                        } else {
                            mFlag = false;
                            break;
                        }
                    }
                }
            }

        } else {
            return false;
        }
        return mFlag;

    }
    // 判断结果是否需要清空字符串
    public static boolean isDigitEnd(String str) {
        if (str.contains("=")) {
            return true;
        } else {
            return false;
        }
    }
}