package dao;

import dto.RegisterDetailDTO;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO{
    ArrayList<RegisterDetailDTO> getDetails();
}
