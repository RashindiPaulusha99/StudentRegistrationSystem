package bo;

import bo.custom.RegisterBO;
import dao.DAOFactory;
import dao.custom.RegisterDAO;
import dto.CourseDTO;
import dto.RegisterDTO;
import dto.StudentDTO;
import embeded.Name;
import entity.Course;
import entity.Register;
import entity.Student;

import java.util.ArrayList;

public class RegisterBOImpl implements RegisterBO {

    private RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTER);

    @Override
    public boolean saveRegister(RegisterDTO registerDTO) {
        Name name = new Name(registerDTO.getStudent().getFirstName(),registerDTO.getStudent().getMiddleName(),registerDTO.getStudent().getLastName());
        Student student = new Student(registerDTO.getStudent().getsId(),name,registerDTO.getStudent().getDOB(),registerDTO.getStudent().getAge(),registerDTO.getStudent().getGender(),registerDTO.getStudent().getAddress(),registerDTO.getStudent().getPhoneNO(),registerDTO.getStudent().getEmail(),registerDTO.getStudent().getRegisterList());
        Course course = new Course(registerDTO.getCourse().getPID(),registerDTO.getCourse().getCourseName(),registerDTO.getCourse().getDuration(),registerDTO.getCourse().getFee());
        return registerDAO.add(new Register(registerDTO.getrId(),registerDTO.getDate(),registerDTO.getPayment(),student,course));
    }

    @Override
    public boolean updateRegister(RegisterDTO registerDTO) {
        Name name = new Name(registerDTO.getStudent().getFirstName(),registerDTO.getStudent().getMiddleName(),registerDTO.getStudent().getLastName());
        Student student = new Student(registerDTO.getStudent().getsId(),name,registerDTO.getStudent().getDOB(),registerDTO.getStudent().getAge(),registerDTO.getStudent().getGender(),registerDTO.getStudent().getAddress(),registerDTO.getStudent().getPhoneNO(),registerDTO.getStudent().getEmail(),registerDTO.getStudent().getRegisterList());
        Course course = new Course(registerDTO.getCourse().getPID(),registerDTO.getCourse().getCourseName(),registerDTO.getCourse().getDuration(),registerDTO.getCourse().getFee());
        return registerDAO.update(new Register(registerDTO.getrId(),registerDTO.getDate(),registerDTO.getPayment(),student,course));
    }

    @Override
    public boolean deleteRegister(String id) {
        return registerDAO.delete(id);
    }

    @Override
    public RegisterDTO searchRegister(String id) {
        Register register = registerDAO.search(id);
        StudentDTO studentDTO = new StudentDTO(register.getStudentDetails().getsId(),register.getStudentDetails().getName().getFirstName(),register.getStudentDetails().getName().getMiddleName(),register.getStudentDetails().getName().getLastName(),register.getStudentDetails().getDOB(),register.getStudentDetails().getAge(),register.getStudentDetails().getGender(),register.getStudentDetails().getAddress(),register.getStudentDetails().getPhoneNO(),register.getStudentDetails().getEmail(),register.getStudentDetails().getRegisterList());
        CourseDTO courseDTO = new CourseDTO(register.getCourse().getPID(),register.getCourse().getCourseName(),register.getCourse().getDuration(),register.getCourse().getFee());
        return new RegisterDTO(register.getrId(),register.getDate(),register.getPayment(),studentDTO,courseDTO);
    }

    @Override
    public ArrayList<RegisterDTO> getRegister() {
        ArrayList<Register> all = registerDAO.getAll();
        ArrayList<RegisterDTO> allDetails = new ArrayList<>();
        for (Register register : all) {
            StudentDTO studentDTO = new StudentDTO(register.getStudentDetails().getsId(),register.getStudentDetails().getName().getFirstName(),register.getStudentDetails().getName().getMiddleName(),register.getStudentDetails().getName().getLastName(),register.getStudentDetails().getDOB(),register.getStudentDetails().getAge(),register.getStudentDetails().getGender(),register.getStudentDetails().getAddress(),register.getStudentDetails().getPhoneNO(),register.getStudentDetails().getEmail(),register.getStudentDetails().getRegisterList());
            CourseDTO courseDTO = new CourseDTO(register.getCourse().getPID(),register.getCourse().getCourseName(),register.getCourse().getDuration(),register.getCourse().getFee());
            allDetails.add(new RegisterDTO(register.getrId(),register.getDate(),register.getPayment(),studentDTO,courseDTO));
        }
        return allDetails;
    }

    @Override
    public String generateRegisterIds() {
        String ids = registerDAO.generateRegisterIds();
        return ids;
    }

    @Override
    public RegisterDTO searchRegisterObject(StudentDTO student) {
        Name name = new Name(student.getFirstName(),student.getMiddleName(),student.getLastName());
        Register register = registerDAO.searchRegisterObject(new Student(student.getsId(),name,student.getDOB(),student.getAge(),student.getGender(),student.getAddress(),student.getPhoneNO(),student.getEmail(),student.getRegisterList()));
        StudentDTO studentDTO = new StudentDTO(register.getStudentDetails().getsId(),register.getStudentDetails().getName().getFirstName(),register.getStudentDetails().getName().getMiddleName(),register.getStudentDetails().getName().getLastName(),register.getStudentDetails().getDOB(),register.getStudentDetails().getAge(),register.getStudentDetails().getGender(),register.getStudentDetails().getAddress(),register.getStudentDetails().getPhoneNO(),register.getStudentDetails().getEmail(),register.getStudentDetails().getRegisterList());
        CourseDTO courseDTO = new CourseDTO(register.getCourse().getPID(),register.getCourse().getCourseName(),register.getCourse().getDuration(),register.getCourse().getFee());
        return new RegisterDTO(register.getrId(),register.getDate(),register.getPayment(),studentDTO,courseDTO);
    }
}
