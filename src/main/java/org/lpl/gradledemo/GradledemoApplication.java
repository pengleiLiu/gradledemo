package org.lpl.gradledemo;


import org.lpl.gradledemo.config.RedissonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class GradledemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(GradledemoApplication.class, args);
  }

}
