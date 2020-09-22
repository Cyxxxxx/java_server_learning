package yuc.learn.java_web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yuc.learn.java_web.mapper.UserMapper;
import yuc.learn.java_web.pojo.po.UserPO;
import yuc.learn.java_web.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
}
