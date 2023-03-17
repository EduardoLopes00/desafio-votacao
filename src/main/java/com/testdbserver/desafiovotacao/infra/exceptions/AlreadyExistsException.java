package com.testdbserver.desafiovotacao.infra.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException (String searchData){
        super("The system couldn't complete the action because the item already exists for data " + searchData);
    }

}
