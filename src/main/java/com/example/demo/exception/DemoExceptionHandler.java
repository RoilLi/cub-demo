package com.example.demo.exception;

import com.example.demo.dto.RestResult;
import com.example.demo.enums.ApiStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class DemoExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle DLC exceptions.
     *
     * @param ex      ex
     * @param request request
     * @return ResponseEntity
     */
    @ExceptionHandler({DemoException.class})
    public final ResponseEntity<RestResult> handleAMHMCExceptions(DemoException ex, WebRequest request) {
        return new ResponseEntity<>(new RestResult(ApiStatusEnum.FAIL.getCode(), ex.getMessage(), null), HttpStatus.OK);
    }

    /**
     * Handle all exceptions.
     *
     * @param ex      ex
     * @param request request
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<RestResult> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new RestResult(ApiStatusEnum.ERROR.getCode(), ApiStatusEnum.ERROR.getName(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
