package yuc.learn.java_web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_user")
public class UserPO {

    private long id;

    private String userName;

    private String password;

    private String profile;

}
