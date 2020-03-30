package com.zkwp.administration.service;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;


@Service
public class IssueService {
    private Logger logger = LoggerFactory.getLogger(IssueService.class);


    @Autowired
    AipContentCensor client;

    public void checkIssue(){

    }


    public JSONObject checkWord() {

     /*   //敏感词汇
        String resp;
        try {            //防止出现特殊符号，制造异常
            resp = contentCensorClient.antiSpam(URLDecoder.decode(text, "UTF-8"), options).toString();
        } catch (UnsupportedEncodingException e) {
            return false;
        }        //标识审核是否通过的结果所在未知
        int len = resp.lastIndexOf("m\":") + 3;
        String spam = resp.substring(len, len + 1);
        //System.out.println(spam);
        if (spam.equals("0")) {
            return true;
        } else {
            return false;
        }*/

        //图片
        client.antiPorn("path");
        return null;
    }


}
