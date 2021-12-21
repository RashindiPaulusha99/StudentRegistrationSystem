package dao;

import entity.Login;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO{

    @Override
    public boolean add(Login login) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(login);

        transaction.commit();
        session.close();

        if (save != null){
            return true;
        }else {
            return true;
        }

    }

    @Override
    public boolean update(Login login) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(login);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String s) {
        throw new UnsupportedOperationException("No Supported Yet.");
    }

    @Override
    public Login search(String userName) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Login WHERE userName= :name";
        Query hqlQuery = session.createQuery(hql);
        Query query = hqlQuery.setParameter("name", userName);

        List<Login> list = query.list();

        Login load = session.load(Login.class, list.get(0).getUserId());

        transaction.commit();
        session.close();

        return load;
    }

    @Override
    public ArrayList<Login> getAll() {
        throw new UnsupportedOperationException("No Supported Yet.");
    }

    @Override
    public boolean searchCorrectUsernameAndPassword(String userName, String password) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Login WHERE userName= :name AND password= :userPassword";
        Query hqlQuery = session.createQuery(hql);
        hqlQuery.setParameter("name", userName);
        hqlQuery.setParameter("userPassword", password);

        boolean b;



        if (hqlQuery.executeUpdate()>0){
            b = true;
        }else {
            b = false;
        }

        transaction.commit();
        session.close();

        return b;
    }
}
