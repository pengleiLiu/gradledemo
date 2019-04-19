package org.lpl.gradledemo.common;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.redisson.spring.transaction.RedissonTransactionManager;

public class RedissonDemo {

  private RedissonClient redissonClient;

  public RedissonDemo() {
    init();
  }

  public void init() {

    Config config = new Config();
    //
    config.setTransportMode(TransportMode.EPOLL);
    config.useClusterServers()
        // use "rediss://" for SSL connection
        .addNodeAddress("redis://127.0.0.1:7181");

    redissonClient = Redisson.create(config);
  }

  public void testLock() {

    RLock lock = redissonClient.getLock("foobar"); // 1.获得锁对象实例
    lock.lock(); // 2.获取分布式锁
    try {
      // do sth.
    } finally {
      lock.unlock(); // 3.释放锁
    }
  }

}
