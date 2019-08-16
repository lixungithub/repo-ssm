package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface ProductDao {

    //根据id查询产品
    @Select("select * from product where id=#{id}")
    Product findById(String id);

    //查询所有的产品信息
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;

    @Select(" select * from product where id=#{productId}")
    Product findOnePrtById(String productId);

    @Update(" update product set  productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id =#{id} ")
    void update( Product product);

    @Delete("delete from product where id=#{id}")
    void delete(String id);
}
