package com.xl.remember.words.config;

import com.xl.remember.words.common.ResultJSON;
import com.xl.remember.words.exception.RepeatedWordException;
import com.xl.remember.words.exception.WordVerifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description: GlobalExceptionHandler
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


  @ExceptionHandler(WordVerifyException.class)
  public ResultJSON handleException(WordVerifyException e) {
    log.error("单词校验异常: {}", e.getMessage());
    return ResultJSON.error(e.getMessage());
  }

  @ExceptionHandler(RepeatedWordException.class)
  public ResultJSON handleException(RepeatedWordException e) {
    log.error("单词重复: {}", e.getMessage());
    return ResultJSON.error(e.getMessage());
  }

}
