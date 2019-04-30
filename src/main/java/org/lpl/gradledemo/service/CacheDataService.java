package org.lpl.gradledemo.service;

import org.lpl.gradledemo.common.Page;
import org.lpl.gradledemo.domain.Student;

public interface CacheDataService {

  public int count();

  public int getCount();

  public Page<Student> listStudent();

  public void saveStudent(Student student);

}
