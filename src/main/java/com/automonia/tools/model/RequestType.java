package com.automonia.tools.model;

/**
 * @作者 温腾
 * @创建时间 2019年01月26日 22:47
 */
public enum RequestType implements WTEnum {

    GET(1, "get"),

    POST(2, "post");


    private Integer value;

    private String message;

    RequestType(Integer value, String message) {
        this.value = value;
        this.message = message;
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
