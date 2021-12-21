package dao;

import entity.Login;

import java.util.ArrayList;

public interface crudDAO<T , ID> {
    boolean add(T t);
    boolean update(T t);
    boolean delete(ID id);
    T search(ID id);
    ArrayList<T> getAll();
}
