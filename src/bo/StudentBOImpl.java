package bo;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.RegisterDTO;
import dto.StudentDTO;
import embeded.Name;
import entity.Course;
import entity.Register;
import entity.Student;

import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    private StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        Name name = new Name(studentDTO.getFirstName(),studentDTO.getMiddleName(),studentDTO.getLastName());
        return studentDAO.add(new Student(studentDTO.getsId(),name,studentDTO.getDOB(),studentDTO.getAge(),studentDTO.getGender(),studentDTO.getAddress(),studentDTO.getPhoneNO(),studentDTO.getEmail()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        Name name = new Name(studentDTO.getFirstName(),studentDTO.getMiddleName(),studentDTO.getLastName());
        return studentDAO.update(new Student(studentDTO.getsId(),name,studentDTO.getDOB(),studentDTO.getAge(),studentDTO.getGender(),studentDTO.getAddress(),studentDTO.getPhoneNO(),studentDTO.getEmail()));
    }

    @Override
    public boolean deleteStudent(String id) {
        return studentDAO.delete(id);
    }

    @Override
    public ArrayList<StudentDTO> getStudent() {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getsId(),student.getName().getFirstName(),student.getName().getMiddleName(),student.getName().getLastName(),student.getDOB(),student.getAge(),student.getGender(),student.getAddress(),student.getPhoneNO(),student.getEmail(),student.getRegisterList()));
        }
        return allStudent;
    }

    @Override
    public StudentDTO searchStudent(String id) {
        Student student = studentDAO.search(id);
        System.out.println(student);
        StudentDTO studentDTO = new StudentDTO(student.getsId(), student.getName().getFirstName(), student.getName().getMiddleName(), student.getName().getLastName(), student.getDOB(), student.getAge(), student.getGender(), student.getAddress(), student.getPhoneNO(), student.getEmail(), student.getRegisterList());
        return studentDTO;
    }

    @Override
    public String generateStudentIds() {
        String ids = studentDAO.generateStudentIds();
        return ids;
    }

    @Override
    public boolean saveRegisterDetails(RegisterDTO register, String id) {
        Name name = new Name(register.getStudent().getFirstName(),register.getStudent().getMiddleName(),register.getStudent().getLastName());
        Student student = new Student(register.getStudent().getsId(),name,register.getStudent().getDOB(),register.getStudent().getAge(),register.getStudent().getGender(),register.getStudent().getAddress(),register.getStudent().getPhoneNO(),register.getStudent().getEmail(),register.getStudent().getRegisterList());
        Course course = new Course(register.getCourse().getPID(),register.getCourse().getCourseName(),register.getCourse().getDuration(),register.getCourse().getFee());
        Register register1 = new Register(register.getrId(),register.getDate(),register.getPayment(),student,course);
        return  studentDAO.saveRegisterDetails(register1,id);
    }

    @Override
    public int countStudent() {
        int count = studentDAO.countStudent();
        return count;
    }
}
