package dao;

import java.util.ArrayList;

public interface crudDAO<T , ID> extends SuperDAO{
    boolean add(T t);
    boolean update(T t);
    boolean delete(ID id);
    T search(ID id);
    ArrayList<T> getAll();
}
