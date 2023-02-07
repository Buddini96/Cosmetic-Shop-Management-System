package lk.ijse.cosmeticshop.bo.custom;

/*
    @author BUDDINI
    @created 2/6/2023 - 10:34 PM   
*/


import lk.ijse.cosmeticshop.bo.SuperBO;
import lk.ijse.cosmeticshop.model.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {
    ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;
}
