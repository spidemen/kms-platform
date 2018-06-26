package com.nbclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p class="detail">
 * 功能:数据库监控
 * </p>
 *
 * @author BaoWeiwei
 * @ClassName Database controller.
 * @Version V1.0.
 * @date 2017.06.16 10:36:16
 */
@Controller
@RequestMapping("/database")
public class DatabaseController{
    @GetMapping(value = "/monitoring")
    public ModelAndView databaseMonitoring(){
        return new ModelAndView("database/monitoring");
    }
}
