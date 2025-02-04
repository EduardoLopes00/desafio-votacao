package com.testdbserver.desafiovotacao.infra.configurations;

import com.testdbserver.desafiovotacao.infra.exceptions.AlreadyExistsException;
import com.testdbserver.desafiovotacao.infra.exceptions.CannotDeleteException;
import com.testdbserver.desafiovotacao.infra.exceptions.InvalidDataException;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.web.DTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> NotFoundException(NotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDTO(HttpStatus.NOT_FOUND, e.getMessage())
        );
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ExceptionDTO> InvalidDataException(InvalidDataException e){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionDTO> AuthenticationException(AuthenticationException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ExceptionDTO(HttpStatus.UNAUTHORIZED, e.getMessage())
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionDTO> AccessDeniedException(AccessDeniedException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ExceptionDTO(HttpStatus.FORBIDDEN, e.getMessage())
        );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> AlreadyExistsException(AlreadyExistsException e){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())
        );
    }

    @ExceptionHandler(CannotDeleteException.class)
    public ResponseEntity<ExceptionDTO> BadRequestException(CannotDeleteException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ExceptionDTO(HttpStatus.FORBIDDEN, e.getMessage())
        );
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ExceptionDTO> BadRequestException(BadRequest e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }
}
