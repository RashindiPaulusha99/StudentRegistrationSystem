package dao;

import entity.Student;

public interface StudentDAO extends crudDAO<Student,String>{
    String generateStudentIds();
}
