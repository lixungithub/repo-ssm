package cn.itcast.ssm.controller;


import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findById")
    //通过id查询角色所对象的权限
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id){

        ModelAndView modelAndView=new ModelAndView();
      Role role= roleService.findByIdd(id);
      modelAndView.addObject("role",role);
      modelAndView.setViewName("role-show");
        return modelAndView;
    }


    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name="ids",required = true) String[] permissionIds){

        roleService.addPermissionToRole(roleId,permissionIds);
        System.out.println(permissionIds);

        return "redirect:findAll";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) {

        System.out.println("我是你爸爸");

        ModelAndView modelAndView = new ModelAndView();
        Role role = roleService.findByid(roleId);
        List<Permission> permissionList = roleService.findRoleByIdAndAllPermission(roleId);

        modelAndView.addObject("role", role);
        modelAndView.addObject("permissionList", permissionList);

        modelAndView.setViewName("role-permission-add");

        return modelAndView;
    }


    @RequestMapping("/save")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll";
    }


    //查询所有的角色
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView();

        List<Role> roleList = roleService.findAll();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;

    }
}
