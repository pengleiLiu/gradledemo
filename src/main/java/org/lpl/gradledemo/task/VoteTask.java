package org.lpl.gradledemo.task;


import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 */
@Component
public class VoteTask {

  private static final Logger logger = LoggerFactory.getLogger(VoteTask.class);

  private static final AtomicInteger count = new AtomicInteger();

  @Scheduled(cron = "0/10 * * * * ?")
  public void voteTask() {
    count.addAndGet(1);
    logger.info("定时任务执行第:{}次", count.get());

  }
}
