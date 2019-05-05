package org.lpl.gradledemo.service;

import java.util.Date;
import org.lpl.gradledemo.common.GsonUtils;
import org.lpl.gradledemo.common.Message;
import org.lpl.gradledemo.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMsgService {

  private final static Logger logger = LoggerFactory.getLogger(KafkaMsgService.class);

  private final static String TOPIC = "msg-vote";

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void vote(Student student) {

    //转化为string
    String data = GsonUtils.toJson(student);

    Message message = new Message();
    message.setId(System.currentTimeMillis());
    message.setMsg(data);
    message.setSendTime(new Date());

    //异步进行投票
    //kafkaTemplate.sen
    kafkaTemplate.send(TOPIC, GsonUtils.toJson(message));
  }
}
