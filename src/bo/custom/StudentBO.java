package bo.custom;

import bo.SuperBO;
import dto.RegisterDTO;
import dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO studentDTO);
    boolean updateStudent(StudentDTO studentDTO);
    boolean deleteStudent(String id);
    ArrayList<StudentDTO> getStudent();
    StudentDTO searchStudent(String id);
    String generateStudentIds();
    boolean saveRegisterDetails(RegisterDTO register, String id);
    int countStudent();
}
