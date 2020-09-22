package yuc.learn.java_web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import yuc.learn.java_web.pojo.po.UserPO;

public interface UserMapper extends BaseMapper<UserPO> {

    @Update("update tb_user set `password`=#{password} where id=#{id}")
    int updatePasswordById(@Param("password") String password, @Param("id") long id);

}
