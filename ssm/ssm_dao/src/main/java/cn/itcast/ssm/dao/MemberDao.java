package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {

    //通过id查询会员
    @Select("select * from member where id=#{id}")
    Member findById(String id) throws Exception;
}
