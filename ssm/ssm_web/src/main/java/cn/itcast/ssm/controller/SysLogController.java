package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.SysLog;
import cn.itcast.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;


    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView();
      List<SysLog> sysLogList= sysLogService.findAll();
      modelAndView.addObject("sysLogList",sysLogList);
      modelAndView.setViewName("syslog-list");
      return modelAndView;
    }
}
