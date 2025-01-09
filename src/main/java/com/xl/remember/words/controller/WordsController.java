package com.xl.remember.words.controller;

import com.xl.remember.words.common.ResultJSON;
import com.xl.remember.words.entity.Words;
import com.xl.remember.words.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: WordController
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
@RestController
@RequestMapping("words")
public class WordsController {

  @Autowired
  WordsService wordsService;

  @PostMapping
  public ResultJSON addWord(@RequestParam("word") String word) {
    wordsService.insertWords(word);
    return ResultJSON.ok();
  }

  @GetMapping
  public Words getWordBYId(@RequestParam("word") String wordId) {
    return wordsService.getWordsById(wordId);
  }
}
