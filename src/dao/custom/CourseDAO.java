package dao.custom;

import dao.crudDAO;
import entity.Course;
import entity.Register;

import java.util.ArrayList;

public interface CourseDAO extends crudDAO<Course,String> {

    String generateCourseIds();
    ArrayList<Course> getCourseDetails(String name);
    boolean saveRegisterDetails(Register register,String id);
    int countCourses();
}
