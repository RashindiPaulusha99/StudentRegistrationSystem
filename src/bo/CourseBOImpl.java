package bo;

import bo.custom.CourseBO;
import dao.DAOFactory;
import dao.custom.CourseDAO;
import dto.CourseDTO;
import dto.RegisterDTO;
import entity.Course;
import entity.Register;

import java.util.ArrayList;

public class CourseBOImpl implements CourseBO {

    private CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean saveCourse(CourseDTO courseDTO) {
        return courseDAO.add(new Course(courseDTO.getPID(),courseDTO.getCourseName(),courseDTO.getDuration(),courseDTO.getFee()));
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) {
        return courseDAO.update(new Course(courseDTO.getPID(),courseDTO.getCourseName(),courseDTO.getDuration(),courseDTO.getFee()));
    }

    @Override
    public boolean deleteCourse(String id) {
        return courseDAO.delete(id);
    }

    @Override
    public CourseDTO searchCourse(String id) {
        Course course = courseDAO.search(id);
        return new CourseDTO(course.getPID(),course.getCourseName(),course.getDuration(),course.getFee());
    }

    @Override
    public ArrayList<CourseDTO> getCourses() {
        ArrayList<Course> all = courseDAO.getAll();
        ArrayList<CourseDTO> allDetails = new ArrayList<>();
        for (Course course : all) {
            allDetails.add(new CourseDTO(course.getPID(),course.getCourseName(),course.getDuration(),course.getFee()));
        }
        return allDetails;
    }

    @Override
    public String generateCourseIds() {
        String ids = courseDAO.generateCourseIds();
        return ids;
    }

    @Override
    public ArrayList<CourseDTO> getCourseDetails(String name) {
        ArrayList<Course> courseDetails = courseDAO.getCourseDetails(name);
        ArrayList<CourseDTO> allDetails = new ArrayList<>();
        for (Course course : courseDetails) {
            allDetails.add(new CourseDTO(course.getPID(),course.getCourseName(),course.getDuration(),course.getFee()));
        }
        return allDetails;
    }

    @Override
    public boolean saveRegisterDetails(RegisterDTO register, String id) {
        Register register1 = new Register(register.getrId(),register.getDate(),register.getPayment());
        return courseDAO.saveRegisterDetails(register1,id);
    }

    @Override
    public int countCourses() {
        int count = courseDAO.countCourses();
        return count;
    }
}
