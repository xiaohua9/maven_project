package test;
import learn.entity.Student;
import learn.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/beans.xml")
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void testFindAll(){
        List<Student> list=studentService.findAll();
        for(Student s:list){
            System.out.println(s);
        }
    }
}
