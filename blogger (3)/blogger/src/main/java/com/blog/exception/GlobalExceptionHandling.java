package com.blog.exception;

import com.blog.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice//which means any exception occurs in your controller layer it will
                // advice to surpress that so it is calls controllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler{//after extending REEH it becomes global catch
  @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(
       ResourceNotFoundException exception, //this is specific exception only handle resourcenotfound exception like
     //arthmetic excep,null pointer exception


        WebRequest webRequest


        ){//exception caught here

    ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));//ot gives true it shows client
      //if you give false it is not shows client error
    return  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);//once caught he error message ti returns the msg
            //the message in error details in this time,msg,disc

        }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(//global exception handles here otherthan resourcefound excep it can handles
            Exception exception,

            WebRequest webRequest


    ){//exception caught here

        ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);//once caught he error message ti returns the msg
        //the message in error details in this time,msg,disc

    }







}

