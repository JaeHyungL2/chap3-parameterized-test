package com.ohgiraffers.parameterized.section01.param;

import java.time.Month;

public class DateValidator {

    //받아온 매개변수 Month가 1월~12월 사이인지 확인하고, 맞다면 True반환,
    //틀리면 false를 반환.

    public static boolean isCollect(Month month){

        int monthValue = month.getValue(); //getvalue는 숫자로바꾸는?

        return monthValue <= 1 && monthValue <=12;
    }

    public static int getLastDayOf(Month month) {

        return month.maxLength();

    }
}
