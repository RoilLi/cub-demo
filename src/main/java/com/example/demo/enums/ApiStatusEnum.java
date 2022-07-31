package com.example.demo.enums;

import java.io.FileNotFoundException;
import java.util.Arrays;

public enum ApiStatusEnum {

    SUCCESS("00", "成功"),
    FAIL("01", "失敗"),
    ERROR("99", "系統異常");

    private final String code;
    private final String name;

    ApiStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ApiStatusEnum getByCode(String code) throws FileNotFoundException {
        return Arrays.stream(ApiStatusEnum.values())
                .filter(apiStatus -> apiStatus.code.equals(code))
                .findFirst()
                .orElseThrow(FileNotFoundException::new);
    }
}
