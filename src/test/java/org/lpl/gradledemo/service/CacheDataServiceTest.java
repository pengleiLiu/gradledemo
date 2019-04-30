package org.lpl.gradledemo.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lpl.gradledemo.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheDataServiceTest {

  @Autowired
  private CacheDataService cacheDataService;

  private static final Logger logger = LoggerFactory.getLogger(CacheDataServiceTest.class);

  private static final int clientTotal = 2000;

  private static final int threadTotal = 50;

  @Test
  public void concurrentTest() throws InterruptedException {

    ExecutorService executorService = Executors.newCachedThreadPool();

    final Semaphore semaphore = new Semaphore(threadTotal);
    final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

    for (int i = 0; i < clientTotal; i++) {
      final int index = i;
      executorService.execute(() -> {
        Thread.currentThread().setName("我的线程池" + index);
        try {
          semaphore.acquire();
          cacheDataService.count();
          semaphore.release();
        } catch (Exception e) {
          logger.warn("当前线程是否被中断:{},name:{}", Thread.currentThread().isInterrupted(),
              Thread.currentThread().getName());
          logger.error("exception", e);
        }
        countDownLatch.countDown();
      });
    }
    countDownLatch.await();
    executorService.shutdown();
    logger.info("count:{}", cacheDataService.getCount());
  }

  @Test
  public void testSaveStudent() {

    Student student = new Student();


  }
}
