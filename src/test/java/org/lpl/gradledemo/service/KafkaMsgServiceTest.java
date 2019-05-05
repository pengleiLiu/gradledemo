package org.lpl.gradledemo.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.lpl.gradledemo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaMsgServiceTest {

  @Autowired
  private KafkaMsgService kafkaMsgService;

  @Test
  public void testVote() throws InterruptedException {
    Long id = 1L;

    Student student = new Student();
    student.setId(id);
    student.setName("张三");
    student.setDesc("音乐、运动");
    student.setVoteCount(0L);

    kafkaMsgService.vote(student);

    Thread.sleep(10000);
  }
}
