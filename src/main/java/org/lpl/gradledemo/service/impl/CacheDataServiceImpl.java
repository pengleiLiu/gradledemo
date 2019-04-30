package org.lpl.gradledemo.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.lpl.gradledemo.common.GsonUtils;
import org.lpl.gradledemo.common.Page;
import org.lpl.gradledemo.common.PageUtils;
import org.lpl.gradledemo.common.PageUtils.PageBuilder;
import org.lpl.gradledemo.domain.Student;
import org.lpl.gradledemo.service.CacheDataService;
import org.redisson.api.RLock;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheDataServiceImpl implements CacheDataService {


  private static final Logger logger = LoggerFactory.getLogger(CacheDataServiceImpl.class);

  @Autowired
  private RedissonClient redissonClient;

  private Integer count = 0;

  private static final String LOCK_KEY = "test";

  @Override
  public int count() {

    RLock lock = redissonClient.getLock(LOCK_KEY);

    try {

      lock.lock(1000, TimeUnit.MILLISECONDS);

      //do your business
      logger.warn(Thread.currentThread().getName() + "获取锁");
      count++;

    } catch (Exception e) {

      logger.error("获取锁异常", e);

    } finally {

      if (!Thread.currentThread().isInterrupted()) {
        logger.warn(Thread.currentThread().getName() + "释放锁");
        lock.unlock();
      }
    }
    return count;
  }


  @Override
  public int getCount() {
    return count;
  }

  private static final String STUDENT_VOTE_SORTSET = "studentVote";

  @Override
  public Page<Student> listStudent(Integer pageIndex, Integer pageSize) {

    PageUtils pageUtil = new PageBuilder().pageIndex(pageIndex).pageSize(pageSize).build();

    RScoredSortedSet<Student> scoredSortedSet = redissonClient
        .getScoredSortedSet(STUDENT_VOTE_SORTSET);

    Collection<ScoredEntry<Student>> scoredEntries = scoredSortedSet
        .entryRange(pageUtil.getPageStart(), pageUtil.getPageEnd());

    List<Student> listStudent = new ArrayList<>();

    scoredEntries.parallelStream().forEach(entry -> {
      Student student = entry.getValue();
      student.setVoteCount(new Double(entry.getScore()).longValue());
      listStudent.add(student);
    });

    Page<Student> page = new Page<>();

    page.setList(listStudent);

    return page;
  }

  @Override
  public void saveStudent(Student student, Double score) {

    logger.info("保存student信息:{},分数:{}", GsonUtils.toJson(student), score);

    RScoredSortedSet<Student> scoredSortedSet = redissonClient
        .getScoredSortedSet(STUDENT_VOTE_SORTSET);
    scoredSortedSet.add(score, student);

  }

  @Override
  public double incrVote(Student student, Double score) {

    logger.info("用户:{}更新票数:{}", GsonUtils.toJson(student), score);

    RScoredSortedSet<Student> scoredSortedSet = redissonClient
        .getScoredSortedSet(STUDENT_VOTE_SORTSET);

    //scoredSortedSet
    return scoredSortedSet.addScore(student, score);
  }

  public void removeStudent(Student student) {

    logger.info("删除用户:{}", GsonUtils.toJson(student));

    RScoredSortedSet<Student> scoredSortedSet = redissonClient
        .getScoredSortedSet(STUDENT_VOTE_SORTSET);
    scoredSortedSet.remove(student);
  }

}
