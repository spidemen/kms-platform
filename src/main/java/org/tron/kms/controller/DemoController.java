package org.tron.kms.controller;


import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/demo")
public class DemoController {

  @ResponseBody //json
  @GetMapping("demo")
  public Map getJson() {

    Map map = new HashMap();
    map.put("data", "Hello World");

    return map;

  }

}
