package com.xl.remember.words.exception;

/**
 * Description: WordVerifyException
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
public class WordVerifyException extends RuntimeException{
  public WordVerifyException() {
    super("单词校验失败，请输入正确的单词！");
  }


}
