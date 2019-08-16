package cn.itcast.ssm.controller;


import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OedersController {

    @Autowired
    private OrdersService ordersService;


    @RequestMapping("findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        System.out.println(id);
        ModelAndView modelAndView=new ModelAndView();
       Orders orders=ordersService.findById(id);
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }

    //查询所有，未分页
  /*  @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {

        ModelAndView modelAndView=new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        modelAndView.addObject("ordersList",ordersList);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }*/


  @RequestMapping("/findAll")
  @Secured("ROLE_ADMIN")          //注意：在使用@Secured注解时不可以省略"ROLE"前缀
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "3") Integer size) throws Exception{
      ModelAndView modelAndView=new ModelAndView();
      List<Orders> ordersList = ordersService.findAll(page, size);
      //pageInfo就是一个分页bean
      PageInfo pageInfo=new PageInfo(ordersList);
      modelAndView.addObject("pageInfo",pageInfo);
      modelAndView.setViewName("orders-page-list");
      return modelAndView;
    }
}
