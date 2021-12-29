package dao;

import entity.Register;
import entity.Student;

public interface RegisterDAO extends crudDAO<Register,String>{
    String generateRegisterIds();
    Register searchRegisterObject(Student student);
}
