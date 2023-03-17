package com.testdbserver.desafiovotacao.infra.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String searchData) {
        super("Item not found for ID " + searchData);
    }

    public NotFoundException(String searchData1,  String searchData2) {
        super("Item not found for datas: " + searchData1 + " and " + searchData2);
    }
}
