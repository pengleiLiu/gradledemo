package org.lpl.gradledemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GradledemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(GradledemoApplication.class, args);
  }

}
