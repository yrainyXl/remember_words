package com.xl.remember.words.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Description: Words
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Words {

  private Long id;
  private String word;
  private String meaning;
  private String example;
  private String sentence;

  //0代表记忆中，1代表已掌握
  private int status;
  private int reviewCount;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  private LocalDateTime nextReviewTime;




}
