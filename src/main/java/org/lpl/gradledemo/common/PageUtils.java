package org.lpl.gradledemo.common;


public class PageUtils {

  private Integer pageStart;

  private Integer pageEnd;

  private PageUtils() {

  }

  private PageUtils(Integer pageIndex, Integer pageSize) {
    this.setPageStart(pageIndex >= 0 ? (pageIndex - 1) * pageSize : 0);
    this.setPageEnd(pageSize);
  }

  public Integer getPageStart() {
    return pageStart;
  }

  public void setPageStart(Integer pageStart) {
    this.pageStart = pageStart;
  }

  public Integer getPageEnd() {
    return pageEnd;
  }

  public void setPageEnd(Integer pageEnd) {
    this.pageEnd = pageEnd;
  }


  public static class PageBuilder {

    private Integer pageIndex;

    private Integer pageSize;

    public PageBuilder() {
    }


    public PageBuilder pageIndex(Integer pageIndex) {
      this.pageIndex = pageIndex;
      return this;
    }

    public PageBuilder pageSize(Integer pageSize) {
      this.pageSize = pageSize;
      return this;
    }

    public PageUtils build() {
      return new PageUtils(this.pageIndex, this.pageSize);
    }

  }
}
