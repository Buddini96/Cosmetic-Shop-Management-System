package lk.ijse.cosmeticshop.dao.custom;

/*
    @author BUDDINI
    @created 2/7/2023 - 10:36 PM   
*/


import lk.ijse.cosmeticshop.dao.SuperDAO;
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

public interface QueryDAO extends SuperDAO {
    public  boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws SQLException, ClassNotFoundException;

    public boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException;

    public boolean saveOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException;
}
