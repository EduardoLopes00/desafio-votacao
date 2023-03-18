package com.testdbserver.desafiovotacao.utils;

import java.util.Date;

public class UtilsFunctions {
    public static String removeDotsHyphensSpacesFromString(String value){
        return value.trim().replaceAll("[()\\s-]+", "");
    }


}
