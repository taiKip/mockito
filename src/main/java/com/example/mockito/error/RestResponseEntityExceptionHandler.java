package com.example.mockito.error;

import com.example.mockito.post.PostNotFoundException;
import com.example.mockito.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler
extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException exception,
                                                             WebRequest webRequest){
ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,
        exception.getMessage());
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorMessage> duplicateException(DuplicateException exception,WebRequest webRequest){
ErrorMessage errorMessage =new ErrorMessage(HttpStatus.CONFLICT,exception.getMessage());
return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }
@ExceptionHandler(PostNotFoundException.class)
public ResponseEntity<ErrorMessage> postNotFoundException(PostNotFoundException exception,WebRequest webRequest){
        ErrorMessage errorMessage =new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
}

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(),error.getDefaultMessage());

        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
