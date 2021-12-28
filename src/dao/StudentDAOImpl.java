package dao;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{

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
        Student s = session.load(Student.class, id);

        transaction.commit();
        session.close();

        if(s == null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Student search(String s) {
        throw new UnsupportedOperationException("No Supported Yet.");
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
}
