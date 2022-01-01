package bo;

import bo.custom.LoginBO;
import dao.DAOFactory;
import dao.custom.LoginDAO;
import dto.LoginDTO;
import entity.Login;

public class LoginBOImpl implements LoginBO {

    private LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);

    @Override
    public boolean saveLogin(LoginDTO loginDTO) {
        return loginDAO.add(new Login(loginDTO.getUserId(),loginDTO.getUserName(),loginDTO.getPassword(),loginDTO.getName()));
    }

    @Override
    public boolean updateLogin(LoginDTO loginDTO) {
        return loginDAO.update(new Login(loginDTO.getUserId(),loginDTO.getUserName(),loginDTO.getPassword(),loginDTO.getName()));
    }

    @Override
    public LoginDTO SearchLogin(String id) {
        Login login = loginDAO.search(id);
        return new LoginDTO(login.getUserId(),login.getUserName(),login.getPassword(),login.getName());
    }
}
