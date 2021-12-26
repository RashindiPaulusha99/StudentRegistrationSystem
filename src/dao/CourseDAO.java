package dao;

import entity.Course;

import java.util.List;

public interface CourseDAO extends crudDAO<Course,String>{

    String generateCourseIds();

}
