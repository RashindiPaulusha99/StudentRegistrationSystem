package dao;

import dao.custom.CourseDAOImpl;
import dao.custom.LoginDAOImpl;
import dao.custom.RegisterDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        LOGIN,STUDENT,REGISTER,COURSE,QUERY
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case REGISTER:
                return new RegisterDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
