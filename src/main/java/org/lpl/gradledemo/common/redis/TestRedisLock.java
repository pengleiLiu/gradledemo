package org.lpl.gradledemo.common.redis;

public class TestRedisLock {

  private static void testLock() {

    RedissonManger.init();

    for (int i = 0; i < 10; i++) {
      Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            String key = "testlpl";
            DistributedRedisLock.getLock(key);
            Thread.sleep(1000);
            System.err.println("======获得锁后进行相应的操作======");
            DistributedRedisLock.unLock(key);
            System.err.println("=============================");
          } catch (InterruptedException e) {

          }
        }
      });
      t.start();
    }
  }

  public static void main(String[] args) {
    testLock();
  }

}
