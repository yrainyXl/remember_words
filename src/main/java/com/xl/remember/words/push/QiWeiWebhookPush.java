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

  //todo 改为数据库
  private static final String WEBHOOK_URL =
      "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=1591a444-b260-4a74-94d1-5ee8c3351fdb";

  public static void pushMessage(List<Words> wordsList) {
    String url = WEBHOOK_URL;
    HttpResponse httpResponse = HttpRequest.post(WEBHOOK_URL)
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

  public static void main(String[] args) {
    List<Words> wordsList = new ArrayList<>();
    wordsList.add(new Words(1l,"apple", "苹果", "apple", "apple", 0, 0, null, null, null));
    wordsList.add(new Words(1l,"words", "单词", "apple", "apple", 0, 0, null, null, null));
    pushMessage(wordsList);
  }
}
