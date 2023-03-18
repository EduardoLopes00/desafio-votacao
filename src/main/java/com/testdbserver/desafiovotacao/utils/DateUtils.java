package com.testdbserver.desafiovotacao.utils;

import java.util.Date;

public class DateUtils {
    public static int second = 1000;
    public static int minute = second * 60;

    public static int hour = minute * 60;
    public static int day = hour * 24;
    public static int week = day * 7;

    public static Date now() {
        return new Date();
    }

    public static Date addTime(int addingTime) {
        return new Date(new Date().getTime() + addingTime);
    }

    public static Date addTime(int addingTime, Date date) {
        return new Date(date.getTime() + addingTime);
    }
}
