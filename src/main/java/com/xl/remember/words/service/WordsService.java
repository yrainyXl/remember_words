package com.xl.remember.words.service;

import com.xl.remember.words.entity.Words;

import java.util.List;

/**
 * Description: WordsService
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
public interface WordsService {

  Words getWordsById(String wordsId);

  Words getWordsByWord(String word);

  void insertWords(String words);

  List<Words> getNeedReviewWordsList();

  void updateWordsReviewStatus(List<Words> wordsList);
}
