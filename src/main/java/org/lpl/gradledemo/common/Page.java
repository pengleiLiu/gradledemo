package org.lpl.gradledemo.common;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

  private Integer pageIndex;

  private Integer pageSize;

  private Integer pageTotal;

  private Integer total;

  private List<T> list;

  public Integer getPageIndex() {
    return pageIndex;
  }

  public void setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getPageTotal() {
    int divide = total / pageSize;
    int mod = total % pageSize;
    return mod > 0 ? divide + 1 : divide;
  }

  public void setPageTotal(Integer pageTotal) {
    this.pageTotal = pageTotal;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }
}
