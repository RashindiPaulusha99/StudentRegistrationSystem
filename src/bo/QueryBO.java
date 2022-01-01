package bo;

import dto.RegisterDetailDTO;

import java.util.ArrayList;

public interface QueryBO extends SuperBO{
    ArrayList<RegisterDetailDTO> getDetails();
}
