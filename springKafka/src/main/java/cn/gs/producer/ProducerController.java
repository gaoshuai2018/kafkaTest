package cn.gs.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 生产者
 * @author: gaoshuai
 * @date: 2019/9/6 18:03
 */
@RequestMapping(value = "/kafka")
@Controller
public class ProducerController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/producer", method = RequestMethod.GET)
    public void consume(String value) {
        System.out.println("发送消息：" + value);
        ListenableFuture listenableFuture = kafkaTemplate.send("test", value);
        //发送成功回调
        SuccessCallback<SendResult<String, String>> successCallback = result -> {
            //成功业务逻辑
            System.out.println("onSuccess");
        };
        //发送失败回调
        FailureCallback failureCallback = ex -> {
            //失败业务逻辑
            System.out.println("onFailure");
        };
        listenableFuture.addCallback(successCallback, failureCallback);
    }
}