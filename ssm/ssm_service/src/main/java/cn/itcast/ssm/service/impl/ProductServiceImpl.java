package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.ProductDao;
import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl  implements ProductService{

    @Autowired
    private ProductDao producDao;
    @Override
    public List<Product> findAll() throws Exception {
        return producDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        producDao.save(product);
    }

    @Override
    public Product findOnePrtById(String productId) {

        return producDao.findOnePrtById(productId);
    }

    @Override
    public void update(Product product) {
        producDao.update(product);
    }

    @Override
    public void delete(String[] ids) {

        for (String id : ids) {

            producDao.delete(id);
        }
    }


}
