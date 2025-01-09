package com.xl.remember.words.translate;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xl.remember.words.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.StringJoiner;

/**
 * Description: BaiDuTranslateService
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
@Service
@Slf4j
public class BaiDuTranslateService {

  @Value("${translate.baidu.appId}")
  private String appId ;

  @Value("${translate.baidu.secret}")
  private String appKey ;

  @Value("${translate.baidu.url}")
  private String url ;



  public String translateToChinese(String queryWords) {

    int salt = new Random().nextInt();
    String sign = appId + queryWords + salt + appKey ;
    HttpResponse response = HttpRequest.get(url)
        .form("q", queryWords)
        .form("from", "en")
        .form("to", "zh")
        .form("appid", appId)
        .form("salt", salt)
        .form("sign", MD5Utils.getMD5Str(sign))
        .execute();
    log.info("response:{}", response.body());
    if (response.isOk() ) {
      String body = response.body();
      JSONObject jsonObject = JSONUtil.parseObj(body);
      if(jsonObject.containsKey("trans_result")){
        log.info("翻译成功:{}", jsonObject.getJSONArray("trans_result"));
        JSONArray resultArray = jsonObject.getJSONArray("trans_result");
        StringJoiner str = new StringJoiner(" , ");
        for (Object result : resultArray) {
          JSONObject resultJson = (JSONObject) result;
          str.add(resultJson.getStr("dst"));
        }
        return str.toString();
      }else {
        log.info("翻译失败:{}", jsonObject);
      }
    }
    return "";
  }



}
