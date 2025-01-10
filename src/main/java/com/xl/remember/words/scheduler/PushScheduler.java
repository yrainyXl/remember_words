package com.xl.remember.words.scheduler;

import cn.hutool.core.collection.CollectionUtil;
import com.xl.remember.words.entity.Words;
import com.xl.remember.words.push.QiWeiWebhookPush;
import com.xl.remember.words.service.WordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: PushScheduler
 *
 * @Author: xulei
 * Date: 2025/1/9
 **/
@Slf4j
@Component
public class PushScheduler {

  @Autowired
  WordsService wordsService;

  @Value("${webhook.url}")
  String webhookUrl;


//  @Scheduled(cron = "0 0 10,18 * * ?")
  @Scheduled(cron = "0 0/30 * * * ?")
  public void pushWords() {
    //1、获取需要推送的单词
    log.info("start get words");
    List<Words> needReviewWordsList = wordsService.getNeedReviewWordsList();
    if(CollectionUtil.isNotEmpty(needReviewWordsList)){
      log.info("start push words");
      QiWeiWebhookPush.pushMessage(needReviewWordsList,webhookUrl);
      //更新状态
      wordsService.updateWordsReviewStatus(needReviewWordsList);
    }
    log.info("end push words");
  }
}
