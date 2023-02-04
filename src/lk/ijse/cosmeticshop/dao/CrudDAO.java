package lk.ijse.cosmeticshop.dao;

/*
    @author BUDDINI
    @created 1/29/2023 - 8:57 AM   
*/


import org.apache.poi.hssf.record.formula.functions.T;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean add(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    T search(String id) throws SQLException, ClassNotFoundException;


}
