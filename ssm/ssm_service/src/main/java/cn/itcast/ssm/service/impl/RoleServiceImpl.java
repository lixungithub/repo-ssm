package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.RoleDao;
import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findByid(String roleId) {
        return roleDao.findByid(roleId);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String roleId) {
        return roleDao.findRoleByIdAndAllPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public Role findByIdd(String id) {
        return roleDao.findByIdd(id);
    }


}
