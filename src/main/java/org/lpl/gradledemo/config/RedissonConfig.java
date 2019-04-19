package org.lpl.gradledemo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class RedissonConfig {

  private RedissonProperties redissonSettings;

  @Bean(destroyMethod = "shutdown")
  public RedissonClient redissonClient() {
    Config config = new Config();
    config.useSingleServer()
        .setAddress("redis://" + redissonSettings.getAddress());

    return Redisson.create(config);
  }

  @Autowired
  public void setRedissonSettings(RedissonProperties redissonSettings) {
    this.redissonSettings = redissonSettings;
  }
}
