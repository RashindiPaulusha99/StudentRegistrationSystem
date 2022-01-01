package dao;

import dto.RegisterDetailDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO{

    @Override
    public ArrayList<RegisterDetailDTO> getDetails() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT r.rId,r.date,r.payment,c.PID,c.courseName,c.duration,c.fee FROM Course c LEFT JOIN Register r ON c.PID = r.course.PID";
        Query query = session.createQuery(hql);

        ArrayList<RegisterDetailDTO> details = new ArrayList<>();

        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            details.add(new RegisterDetailDTO(
                    (String) objects[0],
                    (String) objects[1],
                    (String) objects[2],
                    (String) objects[3],
                    (String) objects[4],
                    (String) objects[5],
                    (double) objects[6]
            ));
        }

        transaction.commit();
        session.close();

        return details;
    }
}
