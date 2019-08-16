package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserInfo> findAll(int page,int size) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    //用户详情的查询
    UserInfo findById(String id);

    List<Role> findUserByIdAndAllRole(String id);

    //根据id查询用户
    UserInfo findByid(String id);

    void addRoleToUser(String userId, String[] roleIds);
}
