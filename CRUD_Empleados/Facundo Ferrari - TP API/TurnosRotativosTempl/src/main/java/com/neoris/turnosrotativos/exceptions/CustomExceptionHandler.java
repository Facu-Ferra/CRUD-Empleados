package com.neoris.turnosrotativos.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map (x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        responseBody.put("errors", errors);



        return new ResponseEntity<>(responseBody, headers, status);
    }

        @ExceptionHandler(UsuarioExistenteException.class)
        public ResponseEntity<Object> handleUsuarioExistenteException(UsuarioExistenteException ex, WebRequest request)
        {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("timestamp", new Date());
            responseBody.put("message", ex.getMessage());

            return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
        }


        @ExceptionHandler(EdadInvalidaException.class)
        public ResponseEntity<Object> handleEdadInvalidaException(EdadInvalidaException ex, WebRequest request)
        {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("timestamp", new Date());
            responseBody.put("message", ex.getMessage());

            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(EmpleadoNoEncontradoException.class)
        public ResponseEntity<Object> handleEmpleadoNoEncontradoException(EmpleadoNoEncontradoException ex, WebRequest request)
        {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("timestamp", new Date());
            responseBody.put("message", ex.getMessage());

            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }

    /*@ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException ex, WebRequest request)
    {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("message", ex.getMessage());

        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/


}
