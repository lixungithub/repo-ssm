package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    //添加权限
    @RequestMapping("/save")
    public String save(Permission permission){
        System.out.println(permission);
        permissionService.save(permission);
        return "redirect:findAll";
    }


    //查询所有权限
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView();
         List<Permission> permissionList=permissionService.findAll();
         modelAndView.addObject("permissionList",permissionList);
         modelAndView.setViewName("permission-list");
         return modelAndView;
    }
}
