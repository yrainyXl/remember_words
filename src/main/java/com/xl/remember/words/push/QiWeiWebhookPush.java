package com.xl.remember.words.push;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xl.remember.words.entity.Words;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: QiWeiWebhook
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
@UtilityClass
@Slf4j
public class QiWeiWebhookPush {

  public static void pushMessage(List<Words> wordsList,String webhookUrl) {
    HttpResponse httpResponse = HttpRequest.post(webhookUrl)
        .body(makeBody(wordsList))
        .header("Content-Type", "application/json")
        .execute();

    if (httpResponse.isOk()) {
      log.info("企微推送成功");
    } else {
      log.error("企微推送失败");
    }
  }

  public static String makeBody(List<Words> wordsList){
    JSONObject obj = JSONUtil.createObj();
    obj.set("msgtype", "markdown");
    StringBuilder str = new StringBuilder();
    str.append("#### 单词推送 \uD83D\uDCDA\n"
        + "\n"
        + "---------\n");
    for (Words words : wordsList) {
      str.append("\n" + " ").append(words.getWord()).append(" ：").append(words.getMeaning());
    }
    obj.set("markdown", JSONUtil.createObj().set("content", str.toString()));
    return obj.toString();
  }

}
