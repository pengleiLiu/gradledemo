package org.lpl.gradledemo.common.redis;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

public class RedissonManger {

  private final static String atomicName = "genId_";

  private static Config config = new Config();

  private static RedissonClient redisson = null;


  public static void init() {
//    config.setTransportMode(TransportMode.NIO);
//    config.useClusterServers()
//        // use "rediss://" for SSL connection
//        .addNodeAddress("redis://127.0.0.1:6379");
//
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");

    redisson = Redisson.create(config);

    RAtomicLong atomicLong = redisson.getAtomicLong(atomicName);
    atomicLong.set(1);
  }

  public static RedissonClient getRedisson() {
    return redisson;
  }

  public static Long nextID() {
    RAtomicLong atomicLong = getRedisson().getAtomicLong(atomicName);
    atomicLong.incrementAndGet();
    return atomicLong.get();
  }

}
