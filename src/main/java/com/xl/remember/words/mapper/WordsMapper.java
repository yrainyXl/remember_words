package com.xl.remember.words.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xl.remember.words.entity.Words;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: WordsMapper
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
public interface WordsMapper extends BaseMapper<Words> {



  @Select("SELECT * FROM words " +
      "WHERE meaning IS NOT NULL " +
      "AND status = 0 " +
      "AND next_review_time < #{currentTime}")
  List<Words> findWordsToReview(@Param("currentTime") LocalDateTime currentTime);
}
