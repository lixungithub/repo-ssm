package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    Role findByid(String roleId);

    List<Permission> findRoleByIdAndAllPermission(String roleId);


    void addPermissionToRole(String roleId, String[] permissionIds);

    Role findByIdd(String id);
}
