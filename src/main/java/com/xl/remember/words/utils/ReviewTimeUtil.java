package com.xl.remember.words.utils;

import com.xl.remember.words.constant.ReviewTimeEnum;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

/**
 * Description: ReviewTimeUtil
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
@UtilityClass
public class ReviewTimeUtil {

  public static LocalDateTime getNextReviewTime(LocalDateTime time, int reviewCount) {
    ReviewTimeEnum reviewTimeEnum = getReviewTimeEnum(reviewCount);
    if (reviewTimeEnum != null) {
      return time.plusMinutes(reviewTimeEnum.getNextReviewInterval());
    }
    return null;
  }

  private ReviewTimeEnum getReviewTimeEnum(int reviewCount) {
    switch(reviewCount ){
      case 0:
        return ReviewTimeEnum.FIRST_REVIEW;
      case 1:
        return ReviewTimeEnum.SECOND_REVIEW;
      case 2:
        return ReviewTimeEnum.THIRD_REVIEW;
      case 3:
        return ReviewTimeEnum.FOURTH_REVIEW;
      default:
        return null;
    }
  }
}
