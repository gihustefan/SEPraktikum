package at.decisionexpert.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * Created by Rainer on 31.05.2016.
 */
@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "File size exceeds limit")
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void handleUploadSizeExceeded() {

    }

}
