package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravllerDao {

    //先通过订单id查询中间表查出游客的id,再查询游客表
    @Select("select * from traveller where id in(select travellerId from order_traveller  where orderId=#{ordersId})")
    List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
