package org.lpl.gradledemo.web.controller;

import org.lpl.gradledemo.service.impl.CacheDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("redis")
public class RedisController {

  @Autowired
  private CacheDataServiceImpl cacheDataService;

  @RequestMapping("lockTest")
  @ResponseBody
  public Integer count() {
    return cacheDataService.count();
  }

  @RequestMapping("getTotal")
  @ResponseBody
  public Integer getTotal() {

    return cacheDataService.getCount();
  }

}
