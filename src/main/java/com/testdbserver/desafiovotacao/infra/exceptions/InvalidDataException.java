package com.testdbserver.desafiovotacao.infra.exceptions;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String dataTitle, String dataValue) {
        super("You inserted an invalid value(" + dataValue + ") for the field " + dataTitle);
    }

    public InvalidDataException(String dataTitle, String dataValue, String reason) {
        super("You inserted an invalid value(" + dataValue + ") for the field " + dataTitle +". The reason is: " + reason);
    }
}
