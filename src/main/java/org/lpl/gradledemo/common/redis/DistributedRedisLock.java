package org.lpl.gradledemo.common.redis;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class DistributedRedisLock {

  private final static RedissonClient redissonClient = RedissonManger.getRedisson();
  private final static String LOCK_TITLE = "redisLock_";

  public static void getLock(String lockName) {

    String key = LOCK_TITLE + lockName;
    RLock lock = redissonClient.getLock(key);

    lock.lock(2, TimeUnit.SECONDS);
    System.err.println("=====lock==" + Thread.currentThread().getName());

  }

  public static void unLock(String lockName) {
    String key = LOCK_TITLE + lockName;
    RLock lock = redissonClient.getLock(key);

    lock.unlock();

    System.err.println("=====unlock======" + Thread.currentThread().getName());

  }




}
