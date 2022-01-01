package bo.custom;

import bo.SuperBO;
import dto.LoginDTO;

public interface LoginBO extends SuperBO {
    boolean saveLogin(LoginDTO loginDTO);
    boolean updateLogin(LoginDTO loginDTO);
    LoginDTO SearchLogin(String id);
}
