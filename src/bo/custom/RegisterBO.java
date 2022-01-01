package bo.custom;

import bo.SuperBO;
import dto.RegisterDTO;
import dto.StudentDTO;

import java.util.ArrayList;

public interface RegisterBO extends SuperBO {
    boolean saveRegister(RegisterDTO registerDTO);
    boolean updateRegister(RegisterDTO registerDTO);
    boolean deleteRegister(String id);
    RegisterDTO searchRegister(String id);
    ArrayList<RegisterDTO> getRegister();
    String generateRegisterIds();
    RegisterDTO searchRegisterObject(StudentDTO student);
}
