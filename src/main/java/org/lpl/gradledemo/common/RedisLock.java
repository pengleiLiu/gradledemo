package org.lpl.gradledemo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisLock {

  @Autowired
  private StringRedisTemplate redisTemplate;


  public boolean lock(String key, String value) {

    redisTemplate.opsForZSet();
    return false;
  }
}
