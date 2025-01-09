package com.xl.remember.words.constant;

/**
 * Description: WordsStatus
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
public enum WordsStatus {
  LEARNING(0, "学习中"),
  LEARNED(1, "已学习");

  WordsStatus(int status, String desc) {
    this.status = status;
    this.desc = desc;
  }

  public int getStatus() {
    return status;
  }
  private int status;
  private String desc;
}
