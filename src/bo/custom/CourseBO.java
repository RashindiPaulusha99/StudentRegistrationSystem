package bo.custom;

import bo.SuperBO;
import dto.CourseDTO;
import dto.RegisterDTO;

import java.util.ArrayList;

public interface CourseBO extends SuperBO {
    boolean saveCourse(CourseDTO courseDTO);
    boolean updateCourse(CourseDTO courseDTO);
    boolean deleteCourse(String id);
    CourseDTO searchCourse(String id);
    ArrayList<CourseDTO> getCourses();
    String generateCourseIds();
    ArrayList<CourseDTO> getCourseDetails(String name);
    boolean saveRegisterDetails(RegisterDTO register, String id);
    int countCourses();
}
