package yuc.learn.java_web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yuc.learn.java_web.dao.UserDAO;
import yuc.learn.java_web.pojo.po.UserPO;
import yuc.learn.java_web.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserDAO, UserPO> implements UserService {
}
