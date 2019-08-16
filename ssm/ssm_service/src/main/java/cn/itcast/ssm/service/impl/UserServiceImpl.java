package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.UserDao;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
           userInfo = userDao.findByUsername(username);
            System.out.println(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将userInfo对象转为userDetails对象并获得权限
        //处理自己的userInfo对象封装成UserDetails   密码没有进行加密的时候需要加“{noop}”
       // User user=new User(userInfo.getUsername(),userInfo.getPassword(),"{noop}"+getAuthority(userInfo.getRoles()));
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            System.out.println(role.getRoleName());
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception {

        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    //通过用户的id查询角色
    @Override
    public List<Role> findUserByIdAndAllRole(String id) {
        return userDao.findUserByIdAndAllRole(id);
    }

    @Override
    public UserInfo findByid(String id) {
        return userDao.findByid(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }


}
