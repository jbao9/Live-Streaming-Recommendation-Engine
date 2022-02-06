package com.laioffer.jupiter.service;

//单独的class用来辨别error发生在什么位置
public class TwitchException extends RuntimeException{
    public TwitchException(String errorMessage) {
        super(errorMessage);
    }

}
