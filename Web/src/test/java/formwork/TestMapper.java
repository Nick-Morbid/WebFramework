package formwork;

import com.system.formwork.SystemStarter;
import com.system.formwork.constant.impl.Role;
import com.system.formwork.entity.pojo.User;
import com.system.formwork.mapper.UserMapper;
import com.system.formwork.service.UserService;
import com.system.formwork.util.EnumUtil;
import com.system.formwork.util.IdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 用来测试DAO层
 * */
@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = SystemStarter.class)
public class TestMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private IdUtil idUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void save(){
        userService.save(new User().setId(idUtil.getId()).setName("Jerry").setPassword(passwordEncoder.encode("123456")).setRole(Role.NORMAL));
    }

    @Autowired
    private EnumUtil enumUtil;

    @Resource
    private UserMapper userMapper;

    @Test
    public void get(){
//        System.out.println(enumUtil.getEnumByCode(Role.class, 0));
        System.out.println(userMapper.selectById("26619226"));
        System.out.println(passwordEncoder.matches("1234567", userService.getById("25341815").getPassword()));
    }
}
