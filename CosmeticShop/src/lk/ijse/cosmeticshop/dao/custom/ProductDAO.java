package lk.ijse.cosmeticshop.dao.custom;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:46 PM   
*/


import lk.ijse.cosmeticshop.dao.CrudDAO;
import lk.ijse.cosmeticshop.dao.CrudUtil;
import lk.ijse.cosmeticshop.model.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO extends CrudDAO<ProductDTO> {
    public ArrayList<String> loadProductCodes() throws SQLException, ClassNotFoundException;
}
