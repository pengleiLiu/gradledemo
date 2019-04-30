package org.lpl.gradledemo.domain;

import java.io.Serializable;

public class Student implements Serializable {

  private static final long serialVersionUID = -6928757132456603543L;

  private Long id;

  private String name;

  private String desc;

  private Long voteCount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Long getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(Long voteCount) {
    this.voteCount = voteCount;
  }
}
