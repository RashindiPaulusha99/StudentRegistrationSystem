package dao;

import entity.Login;

public interface LoginDAO extends crudDAO<Login,String>{
    boolean searchCorrectUsernameAndPassword(String userName,String password);
}
