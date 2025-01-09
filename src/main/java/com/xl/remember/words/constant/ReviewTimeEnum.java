package com.xl.remember.words.constant;

import lombok.Data;

/**
 * Description: ReviewTimeEnum
 * 记忆曲线复习时间：
 * 第1次复习：学习后5-30分钟内；
 * 第2次复习：1天后；
 * 第3次复习：1周后；
 * 第4次复习：1个月后。
 * @Author: xulei
 * Date: 2025/1/7
 **/
public enum ReviewTimeEnum {
  FIRST_REVIEW(0, 30),
  SECOND_REVIEW(1, 1440),
  THIRD_REVIEW(2, 10080),
  FOURTH_REVIEW(3, 43200);

  /**
   * 复习次数
   */
  private Integer reviewCount;

  /**
   * 下次复习时间间隔 单位：分钟
   */
  private Integer nextReviewInterval;

  private ReviewTimeEnum(Integer reviewCount, Integer nextReviewInterval) {
    this.reviewCount = reviewCount;
    this.nextReviewInterval = nextReviewInterval;
  }



  public Integer getReviewCount() {
    return reviewCount;
  }

  public Integer getNextReviewInterval() {
    return nextReviewInterval;
  }
}
