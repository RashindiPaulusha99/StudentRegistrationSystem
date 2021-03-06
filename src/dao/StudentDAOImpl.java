package dao;

import dao.custom.StudentDAO;
import entity.Register;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(student);

        transaction.commit();
        session.close();

        if (save != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);
        session.delete(student);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Student search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);

        transaction.commit();
        session.close();

        return student;
    }

    @Override
    public ArrayList<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Student";
        Query query = session.createQuery(hql);

        List<Student> list = query.list();
        ArrayList<Student> students = new ArrayList<>();

        for (Student student : list) {
            students.add(student);
        }

        transaction.commit();
        session.close();

        return students;
    }

    @Override
    public String generateStudentIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "SELECT * FROM Student ORDER BY sId DESC LIMIT 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Student.class);
        List<Student> list = sqlQuery.list();
        String id = null;

        for (Student student : list) {
            id = student.getsId();
        }

        transaction.commit();
        session.close();

        if (id != null){
            //if data has in database ,split orderId
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId+1;

            if (tempId <= 9){
                return "ST-000"+tempId;
            }else if (tempId <= 99) {
                return "ST-00" + tempId;
            }else if (tempId <= 999){
                return "ST-0" + tempId;
            }else {
                return "ST-"+tempId;
            }
        }else {
            //if no data in database
            return "ST-0001";
        }
    }

    @Override
    public boolean saveRegisterDetails(Register register, String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean b;

        String hql = "UPDATE Student SET registerList =:Rlist WHERE sId = :ID";
        Query query = session.createQuery(hql);
        query.setParameter("Rlist", register);
        query.setParameter("ID", id);

        Student student = session.get(Student.class,id);

        if (student.getsId().equals(id)){
            b = true;
        }else {
            b = false;
        }

        transaction.commit();
        session.close();

        return b;
    }

    @Override
    public int countStudent() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Student";
        Query query = session.createQuery(hql);

        List<Student> list = query.list();

        int count = 0;

        for (Student student : list) {
            count = count+1;
        }

        transaction.commit();
        session.close();

        return count;
    }
}
