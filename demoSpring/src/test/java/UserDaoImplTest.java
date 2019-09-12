import com.learn.dao.UserDao;
import com.learn.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
/*测试spring整合了jdbc的方法*/
public class UserDaoImplTest {
    @Test
    public void testInsert(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDaoImpl");
        User user=new User("iiiq","adaads","nan",12,"武汉","2019-1-1","0794907d-21c4-43c6-873e-c951931c7366.jpg");
        userDao.insert(user);
    }
    @Test
    public void testSelectAll(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDao userDao =(UserDao) applicationContext.getBean("userDaoImpl");
        List<User> users = userDao.selectAll();
        for (User user:users) {
            System.out.println(user);
        }
    }
}
