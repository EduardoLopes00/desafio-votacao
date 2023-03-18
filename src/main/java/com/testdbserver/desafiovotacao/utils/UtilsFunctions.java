package com.testdbserver.desafiovotacao.utils;

public class UtilsFunctions {
    public static String removeDotsHyphensSpacesFromString(String value){
        return value.trim().replaceAll("[()\\s-]+", "");
    }
}
