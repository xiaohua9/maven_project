package learn.dao;
import learn.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
/*数据库操作接口*/
@Repository
public interface StudentDao {
    public List<Student> findAll();
}
