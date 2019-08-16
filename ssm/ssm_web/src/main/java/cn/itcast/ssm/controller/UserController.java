package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/addRoleToUser")
   public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required =true) String[] roleIds){

        userService.addRoleToUser(userId,roleIds);
        return  "redirect:/user/findById?id="+userId;

   }

    //通过id查询用户以及可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String id){
        ModelAndView modelAndView=new ModelAndView();

        //查询用户
        UserInfo user=userService.findByid(id);
    //查询用户可以添加的角色
      List<Role> roleList= userService.findUserByIdAndAllRole(id);
      modelAndView.addObject("user",user);
      modelAndView.addObject("roleList",roleList);
      modelAndView.setViewName("user-role-add");

        return modelAndView;
    }


    //通过id用户详情查询

    @RequestMapping("findById")
    public ModelAndView findById( @RequestParam(name = "id",required = true) String id) {
        System.out.println(id);
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        List<Role> roles = userInfo.getRoles();
        for (Role role : roles) {
            for (Permission permission : role.getPermissions()) {
                System.out.println(userInfo.getUsername()+":"+role.getRoleName()+":"+permission.getPermissionName());
            }
        }


        modelAndView.addObject("userInfo", userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }


    //添加一个用户
    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='tom'")
    public String save(UserInfo userInfo) throws Exception {

        userService.save(userInfo);

        return "redirect:findAll";
    }

   /* @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView=new ModelAndView();

     List<UserInfo> userList= userService.findAll();

     modelAndView.addObject("userList",userList);
     modelAndView.setViewName("user-list");
     return modelAndView;
    }*/


    //分页查询用户
    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "3") int size) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        List<UserInfo> userList = userService.findAll(page, size);

        //pageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(userList);

        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("user-page-list");
        return modelAndView;
    }
}
