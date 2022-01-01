package bo;

import dao.DAOFactory;
import dao.QueryDAO;
import dto.RegisterDetailDTO;

import java.util.ArrayList;

public class QueryBOImpl implements QueryBO{

    private QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public ArrayList<RegisterDetailDTO> getDetails() {
        ArrayList<RegisterDetailDTO> details = queryDAO.getDetails();
        return details;
    }
}
