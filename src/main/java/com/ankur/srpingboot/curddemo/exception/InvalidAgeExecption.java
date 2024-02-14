package com.ankur.srpingboot.curddemo.exception;

public class InvalidAgeExecption extends  RuntimeException{

    String message;

    InvalidAgeExecption(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
