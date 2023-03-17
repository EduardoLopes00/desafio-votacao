package com.testdbserver.desafiovotacao.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionDTO {
    private HttpStatus statusCode;

    private String message;
}
