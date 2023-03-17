package com.testdbserver.desafiovotacao.infra.exceptions;

public class CannotDeleteException extends RuntimeException {
    public CannotDeleteException(String data, String reason) {
        super("The system couldn't delete the data " + data + " because " + reason);
    }
}
