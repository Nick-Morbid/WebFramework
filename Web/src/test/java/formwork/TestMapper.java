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

}
