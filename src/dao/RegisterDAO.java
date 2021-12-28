package dao;

import entity.Register;

public interface RegisterDAO extends crudDAO<Register,String>{
    String generateRegisterIds();
}
