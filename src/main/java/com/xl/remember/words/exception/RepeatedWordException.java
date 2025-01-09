package com.xl.remember.words.exception;

/**
 * Description: RepeatedWordException
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
public class RepeatedWordException extends RuntimeException{
    public RepeatedWordException() {
        super("单词已记录！");
    }
}
