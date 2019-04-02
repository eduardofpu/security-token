package com.security.handler;

import com.security.error.Detail;
import com.security.error.ErrorDetail;
import com.security.error.ErrorValidation;
import com.security.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice // permite acessar as camadas do exceptionhandler
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
// o proprio spring vai se encarregar de ativar o exception handler
    public ResponseEntity<?> handleResourceNotFoundExcepition(ResourceNotFoundException rfnExcepition) {
        Detail rnfDetails = ErrorDetail.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Not found")
                .detail(rfnExcepition.getMessage())
                .developerMessage(rfnExcepition.getClass().getName())
                .field("")
                .fieldMessage("")
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
// o proprio spring vai se encarregar de ativar o exception handler
    public ResponseEntity<?> hadlerMethodArgumentNotValidException(MethodArgumentNotValidException manvExcepition) {
        List<FieldError> fieldErrors = manvExcepition.getBindingResult().getFieldErrors();
        String field = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        Detail rnfDetails = ErrorValidation.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad request")
                .detail("Field validation error")
                .developerMessage(manvExcepition.getClass().getName())
                .field(field)
                .fieldMessage(fieldMessage)
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
    }
}
