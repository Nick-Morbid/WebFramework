package formwork;

import com.system.formwork.SystemStarter;
import com.system.formwork.constant.impl.Role;
import com.system.formwork.entity.pojo.User;
import com.system.formwork.mapstruct.UserConvertor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = SystemStarter.class)
public class SpringBootTest {

    @Autowired
    private UserConvertor userConvertor;

    @Test
    public void test01(){
        System.out.println(userConvertor.UserToUserVo(new User().setId("123").setName("Nick").setRole(Role.ADMIN)));
    }

}
