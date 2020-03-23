import com.jiay.user.Application;
import com.jiay.user.model.request.LoginRequest;
import com.jiay.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestDB {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        try {
            LoginRequest request = new LoginRequest();
            request.setMobile("13566778899");
            request.setPassword("1");
            System.out.println(userService.login(request));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
