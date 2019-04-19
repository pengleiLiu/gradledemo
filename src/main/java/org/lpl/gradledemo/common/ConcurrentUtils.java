//package org.lpl.gradledemo.common;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Semaphore;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class ConcurrentUtils {
//
//  private static final Logger logger = LoggerFactory.getLogger(ConcurrentUtils.class);
//
//  private static final int clientTotal = 500;
//
//  private static final int threadTotal = 200;
//
//  public static void concurrentTest() throws InterruptedException {
//
//    ExecutorService executorService = Executors.newCachedThreadPool();
//
//    final Semaphore semaphore = new Semaphore(threadTotal);
//    final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
//
//    for (int i = 0; i < clientTotal; i++) {
//      executorService.execute(() -> {
//        try {
//          semaphore.acquire();
//          //dosth
//          semaphore.release();
//        } catch (Exception e) {
//          logger.error("exception");
//        }
//        countDownLatch.countDown();
//      });
//    }
//    countDownLatch.await();
//    executorService.shutdown();
//
//  }
//}
