package org.lpl.gradledemo.service;

import org.lpl.gradledemo.common.Page;
import org.lpl.gradledemo.domain.Student;

public interface CacheDataService {

  public int count();

  public int getCount();

  /****************************sortset*********************/

  /**
   * 分页查询排行
   *
   * @param PageIndex 当前页数
   * @param pageSize 一页数量
   */
  public Page<Student> listStudent(Integer PageIndex, Integer pageSize);

  /**
   * 添加用户
   *
   * @param student 学生
   * @param score 分数
   */
  public void saveStudent(Student student, Double score);

  /**
   * 增加用户指定评分
   *
   * @param student 学生
   */
  public double incrVote(Student student, Double score);

  /**
   * 删除指定用户
   *
   * @param student 学生
   */
  public void removeStudent(Student student);

  /****************************sortset*********************/
}