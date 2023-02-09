package lk.ijse.cosmeticshop.bo.custom;

/*
    @author BUDDINI
    @created 2/8/2023 - 10:54 PM   
*/


import lk.ijse.cosmeticshop.bo.SuperBO;
import lk.ijse.cosmeticshop.dao.custom.OrderDAO;
import lk.ijse.cosmeticshop.dao.custom.OrderDetailDAO;
import lk.ijse.cosmeticshop.dao.custom.ProductDAO;
import lk.ijse.cosmeticshop.dao.custom.impl.OrderDAOImpl;
import lk.ijse.cosmeticshop.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.cosmeticshop.dao.custom.impl.ProductDAOImpl;
import lk.ijse.cosmeticshop.db.DBConnection;
import lk.ijse.cosmeticshop.model.OrderDTO;
import lk.ijse.cosmeticshop.model.OrderDetailDTO;
import lk.ijse.cosmeticshop.model.PlaceOrderDTO;
import lk.ijse.cosmeticshop.model.ProductDTO;
import lk.ijse.cosmeticshop.view.tdm.CartDetail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface QueryBO extends SuperBO {
    public  boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws SQLException, ClassNotFoundException;

    public  boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException;

    public boolean saveOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException;
}
