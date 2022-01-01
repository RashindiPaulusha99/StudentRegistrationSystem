package bo;

import dao.custom.CourseDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        if (boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        LOGIN,STUDENT,REGISTER,COURSE,QUERY
    }

    public SuperBO getBO(BOTypes types){
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case REGISTER:
                return new RegisterBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case COURSE:
                return new CourseBOImpl();
            case QUERY:
                return new QueryBOImpl();
            default:
                return null;
        }
    }
}
