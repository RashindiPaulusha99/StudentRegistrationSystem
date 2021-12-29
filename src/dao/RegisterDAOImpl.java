package dao;

import entity.Login;
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

public class RegisterDAOImpl implements RegisterDAO{
    @Override
    public String generateRegisterIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "SELECT * FROM Register ORDER BY rId DESC LIMIT 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Register.class);
        List<Register> list = sqlQuery.list();
        String id = null;

        for (Register register : list) {
            id = register.getrId();
        }

        transaction.commit();
        session.close();

        if (id != null){
            //if data has in database ,split orderId
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId+1;

            if (tempId <= 9){
                return "RE-000"+tempId;
            }else if (tempId <= 99) {
                return "RE-00" + tempId;
            }else if (tempId <= 999){
                return "RE-0" + tempId;
            }else {
                return "RE-"+tempId;
            }
        }else {
            //if no data in database
            return "RE-0001";
        }
    }

    @Override
    public boolean add(Register register) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(register);

        transaction.commit();
        session.close();

        if (save != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(Register register) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Register search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Register register = session.get(Register.class, id);

        transaction.commit();
        session.close();

        return register;
    }


    @Override
    public Register searchRegisterObject(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Register WHERE student= :studentObject";
        Query hqlQuery = session.createQuery(hql);
        Query query = hqlQuery.setParameter("studentObject", student);

        List<Register> list = query.list();

        Register register = session.load(Register.class, list.get(0).getrId());

        transaction.commit();
        session.close();

        return register;
    }

    @Override
    public ArrayList<Register> getAll() {
        return null;
    }
}
