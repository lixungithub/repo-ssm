package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.itcast.ssm.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String username) throws Exception;


    //查询所有的用户
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    //添加一个用户
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    //用户的详情查询
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(property ="id",column ="id" ),
            @Result(property ="username",column ="username" ),
            @Result(property ="email",column ="email" ),
            @Result(property ="password",column ="password" ),
            @Result(property ="phoneNum",column ="phoneNum" ),
            @Result(property ="status",column ="status" ),
            //用户与角色属于多对多关系
            @Result(property ="roles",column ="id",javaType = List.class ,many = @Many(select = "cn.itcast.ssm.dao.RoleDao.findById"))
    })
    UserInfo findById(String id);

    //查询用户可以添加的角色
    @Select(" select * from role where id not in(select roleId from users_role where userId=#{id}) ")
    List<Role> findUserByIdAndAllRole(String id);


    @Select("select * from users where id=#{id}")
    UserInfo findByid(String id);

    @Insert("insert into users_role (userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
