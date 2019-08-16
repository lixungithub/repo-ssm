package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll() throws Exception;


    void save(Product product) throws Exception;

    Product findOnePrtById(String productId);


    void update(Product product);

    void delete(String[] ids);
}
