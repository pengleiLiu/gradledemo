package org.lpl.gradledemo.service.impl;

import java.util.concurrent.TimeUnit;
import org.lpl.gradledemo.config.RedissonConfig;
import org.lpl.gradledemo.service.CacheDataService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheDataServiceImpl implements CacheDataService {


  private static final Logger logger = LoggerFactory.getLogger(CacheDataServiceImpl.class);

  @Autowired
  private RedissonConfig redissonConfig;

  private Integer count = 0;

  private static final String LOCK_KEY = "test";

  @Override
  public void rushPurchase() {

    RLock lock = redissonConfig.redissonClient().getLock(LOCK_KEY);

    try {

      lock.lock(500, TimeUnit.MICROSECONDS);

      boolean res = lock.tryLock(10000, 500, TimeUnit.MICROSECONDS);

      if (res) {
        //do your business
        logger.warn(Thread.currentThread().getName() + "获取锁");
        count++;
        //System.err.println(count);
        //logger.warn("count:{}", count);
      }

    } catch (InterruptedException e) {
      logger.error("获取锁异常", e);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void count() {
    count++;
    //System.err.println(count);
    //logger.warn("count:{}", count);
  }

  @Override
  public int getCount() {
    return count;
  }

}
