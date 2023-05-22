package com.java.basics.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.java.basics.response.Message;

@ControllerAdvice
public class GroupExceptionHandler 
{
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Message> handleMethodArgumentNotvalidException(MethodArgumentNotValidException e)
    {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        List<String> errMsg = errors
                .stream()
                .map(ObjectError-> ObjectError.getDefaultMessage())
                .collect(Collectors.toList());
        Message responseDto = new Message("Exception Occured..!!",errMsg.toString());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(GroupException.class)
    public ResponseEntity<Message> handleEmployeeException(GroupException e)
    {
    	Message responseDto = new Message("Exception Occured..!!",e.getMessage());
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_GATEWAY);
    }

}
