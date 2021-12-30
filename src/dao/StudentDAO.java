package dao;

import entity.Register;
import entity.Student;

public interface StudentDAO extends crudDAO<Student,String>{
    String generateStudentIds();
    boolean saveRegisterDetails(Register register,String id);
    int countStudent();
}
