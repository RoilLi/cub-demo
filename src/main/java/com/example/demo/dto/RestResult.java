package com.example.demo.dto;

public class RestResult {
    private String returnCode;
    private String returnMsg;
    private Object restData;

    public RestResult() {
    }

    public RestResult(String returnCode, String returnMsg, Object restData) {
        super();
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.restData = restData;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Object getRestData() {
        return restData;
    }

    public void setRestData(Object restData) {
        this.restData = restData;
    }
}
