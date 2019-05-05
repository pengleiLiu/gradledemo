package org.lpl.gradledemo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MsgConsumer {


  private final static String TOPIC = "msg-vote";

  @KafkaListener(topics = {TOPIC})
  public void processMessage(String content) {

    System.out.println("消息被消费" + content);
  }
}
