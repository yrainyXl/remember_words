package com.xl.remember.words.service.impl;

import static com.xl.remember.words.constant.ReviewTimeEnum.FIRST_REVIEW;
import static com.xl.remember.words.constant.WordsStatus.LEARNING;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xl.remember.words.entity.Words;
import com.xl.remember.words.exception.RepeatedWordException;
import com.xl.remember.words.exception.WordVerifyException;
import com.xl.remember.words.mapper.WordsMapper;
import com.xl.remember.words.translate.BaiDuTranslateService;
import com.xl.remember.words.service.WordsService;
import com.xl.remember.words.utils.ReviewTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: WordsServiceImpl
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
@Service
@Slf4j
public class WordsServiceImpl implements WordsService {

  @Autowired
  WordsMapper wordsMapper;

  @Autowired
  BaiDuTranslateService baiDuTranslateService;


  @Override
  public Words getWordsById(String wordsId) {
    return wordsMapper.selectById(Long.parseLong(wordsId));
  }

  @Override
  public Words getWordsByWord(String word) {
    LambdaQueryWrapper<Words> query = new LambdaQueryWrapper<>();
    query.eq(Words::getWord, word);
    return wordsMapper.selectOne(query);
  }


  /**
   * todo 改进：
   * 拆分单词表，将用户信息从单词表中分离出来，每个单词只需记录一次
   * @param words
   */
  @Override
  public void insertWords(String words) {
    //单词校验，只能是英文字母
    if (!words.matches("[a-zA-Z]+")) {
      throw new WordVerifyException();
    }
    if(getWordsByWord(words) != null){
      throw new RepeatedWordException();
    }
    Words newWords = new Words();
    LocalDateTime now = LocalDateTime.now();
    newWords.setWord(words);
    newWords.setReviewCount(FIRST_REVIEW.getReviewCount());
    newWords.setCreateTime(now);
    newWords.setUpdateTime(now);
    newWords.setStatus(LEARNING.getStatus());
    newWords.setNextReviewTime(ReviewTimeUtil.getNextReviewTime(now,FIRST_REVIEW.getReviewCount()));
    //插入到数据库
    wordsMapper.insert(newWords);
    translate(newWords);
    log.info("插入单词成功: {}", newWords);
  }

  @Override
  public List<Words> getNeedReviewWordsList() {
    return wordsMapper.findWordsToReview(LocalDateTime.now());
  }

  @Override
  public void updateWordsReviewStatus(List<Words> wordsList) {
    for (Words words : wordsList) {
      int reviewCount = words.getReviewCount()+1;
      words.setReviewCount(reviewCount );
      if (reviewCount == 4) {
        words.setStatus(1);
      }else {
        words.setNextReviewTime(ReviewTimeUtil.getNextReviewTime(LocalDateTime.now(), reviewCount));
      }
      wordsMapper.updateById(words);
    }
  }


  public void translate(Words words) {
    new Thread(() -> {
      String meaning = baiDuTranslateService.translateToChinese(words.getWord());
      if (StringUtils.isNotEmpty(meaning)) {
        words.setMeaning(meaning);
        wordsMapper.updateById(words);
      }
    }).start();
  }
}
