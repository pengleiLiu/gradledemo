package org.lpl.gradledemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMsgServiceImpl {

  private final static Logger logger = LoggerFactory.getLogger(KafkaMsgServiceImpl.class);

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage() {

    String topicName = "";
    String data = "";

    kafkaTemplate.send("", "");

  }
}
