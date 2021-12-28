package dao;

import entity.Course;
import entity.Register;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO{

    @Override
    public boolean add(Course course) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(course);

        transaction.commit();
        session.close();

        if (save != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(Course course) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(course);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, id);
        session.delete(course);
        Course c = session.load(Course.class, id);

        transaction.commit();
        session.close();

        if(c == null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Course search(String s) {
        throw new UnsupportedOperationException("No Supported Yet.");
    }

    @Override
    public ArrayList<Course> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Course";
        Query query = session.createQuery(hql);

        List<Course> list = query.list();
        ArrayList<Course> courses = new ArrayList<>();

        for (Course course : list) {
            courses.add(course);
        }

        transaction.commit();
        session.close();

        return courses;
    }

    @Override
    public String generateCourseIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "SELECT * FROM Course ORDER BY PID DESC LIMIT 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Course.class);
        List<Course> list = sqlQuery.list();
        String id = null;

        for (Course course : list) {
            id = course.getPID();
        }

        transaction.commit();
        session.close();

        if (id != null){
            //if data has in database ,split orderId
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId+1;

            if (tempId <= 9){
                return "CT-000"+tempId;
            }else if (tempId <= 99) {
                return "CT-00" + tempId;
            }else if (tempId <= 999){
                return "CT-0" + tempId;
            }else {
                return "CT-"+tempId;
            }
        }else {
            //if no data in database
            return "CT-0001";
        }
    }

    @Override
    public ArrayList<Course> getCourseDetails(String name) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Course WHERE courseName=:cName";
        Query hqlQuery = session.createQuery(hql);
        Query query = hqlQuery.setParameter("cName", name);

        List<Course> list = query.list();
        ArrayList<Course> courses = new ArrayList<>();

        for (Course course : list) {
            courses.add(course);
        }

        transaction.commit();
        session.close();

        return courses;

    }

    @Override
    public boolean saveRegisterDetails(Register register,String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean b;

        String hql = "UPDATE Course SET registerList =:Rlist WHERE PID = :ID";
        Query query = session.createQuery(hql);
        query.setParameter("Rlist", register);
        query.setParameter("ID", id);

        Course course = session.get(Course.class,id);

        if (course.getPID().equals(id)){
            b = true;
        }else {
            b = false;
        }

        transaction.commit();
        session.close();

        return b;
    }

    @Override
    public boolean updateCourseList(Register register) {
        for (Course temp : register.getCourseList()) {
            /*temp.getRegisterList().add(register);*/
            boolean ifSaved = saveRegisterDetails(register,temp.getPID());
            if (ifSaved){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

}
