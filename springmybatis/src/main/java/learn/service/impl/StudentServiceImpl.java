package learn.service.impl;

import learn.dao.StudentDao;
import learn.entity.Student;
import learn.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    public List<Student> findAll() {
        return studentDao.findAll();
    }
}
