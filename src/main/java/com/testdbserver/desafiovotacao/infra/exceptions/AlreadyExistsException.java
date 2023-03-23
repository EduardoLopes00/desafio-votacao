package com.testdbserver.desafiovotacao.infra.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException (String searchData, String reason){
        super(buildMessage(searchData, reason));
    }

    public AlreadyExistsException (String message){
        super(message);
    }

    private static String buildMessage(String searchData, String reason) {
        String message = "The system couldn't complete the action because the item already exists for data " + searchData;

        if (reason != "") {
            message = message + ". " + reason;
        }

        return message;
    }

}
