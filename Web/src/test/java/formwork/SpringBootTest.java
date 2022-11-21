package formwork;

import com.system.formwork.SystemStarter;
import com.system.formwork.security.encoder.PasswordEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = SystemStarter.class)
public class SpringBootTest {
    @Resource(name = "PasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder(){
        String verity = "123456";
        String encode = passwordEncoder.encode(verity);
        System.out.println(encode);
        System.out.println(passwordEncoder.verity(verity, encode));
        System.out.println(passwordEncoder.verity("123456789", encode));

    }
}
