package cn.gs.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @description: 消费者
 * @author: gaoshuai
 * @date: 2019/9/6 18:03
 */
@Component
public class SpringKafkaConsumer {
    /**
     * 实时获取kafka数据(生产一条，监听生产topic自动消费一条)
     *
     * @param record 数据
     */
    @KafkaListener(topics = {"${kafka.consumer.topic}"})
    public void listen(ConsumerRecord<String, String> record) {
        String value = record.value();
        System.out.println("收到消息：" + value);
    }

}