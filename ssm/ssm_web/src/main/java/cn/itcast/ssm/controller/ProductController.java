package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/delete")
    public String delete(@RequestParam(name = "ids",required = true) String[] ids){

        productService.delete(ids);

        return "redirect:findAll";
    }

    @RequestMapping("/update")
    public String update(Product product){

    productService.update(product);

        return "redirect:findAll";
    }


    //编辑产品之前先查询出数据进行回写
    @RequestMapping("/findOnePrtById")
    public String findOnePrtById(@RequestParam(name = "id",required = true) String productId, Model model){

     Product product=productService.findOnePrtById(productId);
    model.addAttribute("product",product);
        return "product-update";
    }


    //添加产品
    @RequestMapping("/save")
    public String save(Product product) throws Exception {
        productService.save(product);

        return "redirect:findAll";
    }

    //查询全部产品
    @RequestMapping("/findAll")
    //该注解表示只有拥有ADMIN角色的用户才能访问该方法,jsr-250可以省略前缀ROLE_
   // @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {

        List<Product> productList = productService.findAll();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("product");

        return modelAndView;

    }

}
