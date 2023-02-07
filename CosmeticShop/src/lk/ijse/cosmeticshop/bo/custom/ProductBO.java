package lk.ijse.cosmeticshop.bo.custom;

/*
    @author BUDDINI
    @created 1/30/2023 - 10:11 PM   
*/


import lk.ijse.cosmeticshop.bo.SuperBO;
import lk.ijse.cosmeticshop.model.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;

    boolean addProduct(ProductDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteProduct(String id) throws SQLException, ClassNotFoundException;

    ProductDTO searchProduct(String id) throws SQLException, ClassNotFoundException;
}
