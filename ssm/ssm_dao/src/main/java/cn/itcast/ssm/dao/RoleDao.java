package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    //根据用户的id查询所对应的角色
   // @Select("select * from role where id in(select roleId from uesrs_role where userId=#{userId})")
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    List<Role> findRoleByUserId(String userId);

    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",many = @Many(select = "cn.itcast.ssm.dao.PermissionDao.findPerByRoleId"))
    })
    List<Role> findById(String id);

    //查询所有的角色
    @Select("select * from role")
    List<Role> findAll();

    @Insert(" insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc}) ")
    void save(Role role);

    //通过id查询角色
    @Select("select * from role where id=#{roleId}")
    Role findByid(String roleId);

    //查询角色可以添加的权限
    @Select(" select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId}) ")
    List<Permission> findRoleByIdAndAllPermission(String roleId);

    //给角色添加权限
    @Insert(" insert into role_permission (permissionId,roleId) values (#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    @Select("select * from role where id=#{id} ")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property ="permissions",column = "id",javaType =List.class,many = @Many(select = "cn.itcast.ssm.dao.PermissionDao.findPerByRoleId"))
    })
    Role findByIdd(String id);
}
