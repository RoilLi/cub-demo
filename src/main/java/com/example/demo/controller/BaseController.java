package com.example.demo.controller;


import com.example.demo.dto.RestResult;
import com.example.demo.enums.ApiStatusEnum;

public class BaseController {

    public RestResult buildSuccessResult() {
        return new RestResult(ApiStatusEnum.SUCCESS.getCode(), ApiStatusEnum.SUCCESS.getName(), null);
    }

    public RestResult buildErrorResult() {
        return new RestResult(ApiStatusEnum.FAIL.getCode(), ApiStatusEnum.FAIL.getName(), null);
    }

    public RestResult buildResult(String returnCode, String returnMsg, Object restData) {
        return new RestResult(returnCode, returnMsg, restData);
    }

    public RestResult buildSuccessResult(String returnMsg, Object restData) {
        return new RestResult(ApiStatusEnum.SUCCESS.getCode(), returnMsg, restData);
    }

    public RestResult buildErrorResult(String returnMsg, Object restData) {
        return new RestResult(ApiStatusEnum.FAIL.getCode(), returnMsg, restData);
    }

    public RestResult buildSuccessResult(Object restData) {
        return new RestResult(ApiStatusEnum.SUCCESS.getCode(), ApiStatusEnum.SUCCESS.getName(), restData);
    }

    public RestResult buildErrorResult(Object restData) {
        return new RestResult(ApiStatusEnum.FAIL.getCode(), ApiStatusEnum.FAIL.getName(), restData);
    }
}
