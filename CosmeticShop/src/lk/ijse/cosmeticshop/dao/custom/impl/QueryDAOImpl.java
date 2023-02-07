package lk.ijse.cosmeticshop.dao.custom.impl;

/*
    @author BUDDINI
    @created 1/29/2023 - 11:17 AM   
*/


import lk.ijse.cosmeticshop.dao.custom.OrderDAO;
import lk.ijse.cosmeticshop.dao.custom.OrderDetailDAO;
import lk.ijse.cosmeticshop.dao.custom.ProductDAO;
import lk.ijse.cosmeticshop.dao.custom.QueryDAO;
import lk.ijse.cosmeticshop.db.DBConnection;
import lk.ijse.cosmeticshop.model.OrderDTO;
import lk.ijse.cosmeticshop.model.PlaceOrderDTO;
import lk.ijse.cosmeticshop.modelOld.OrderDetailModel;
import lk.ijse.cosmeticshop.modelOld.OrderModel;
import lk.ijse.cosmeticshop.modelOld.ProductModel;

import java.sql.SQLException;
import java.time.LocalDate;

public class QueryDAOImpl implements QueryDAO {
    public static boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws SQLException, ClassNotFoundException {
        try {

            DBConnection.getInstance().getConnection().setAutoCommit(false);
            OrderDAO orderDAO = new OrderDAOImpl();
            ProductDAO productDAO = new ProductDAOImpl();
            OrderDetailDAO orderDetailDAO = new OrderDetailsDAOImpl();

            boolean isOrderAdded = orderDAO.save(new OrderDTO(placeOrderDTO.getOrderID(), LocalDate.now(), placeOrderDTO.getCustomerID()));
            if (isOrderAdded) {
                boolean isUpdated = productDAO.updateQty(placeOrderDTO.getOrderDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded = orderDetailDAO.saveOrderDetails(placeOrderDTO.getOrderDetails());
                    if (isOrderDetailAdded) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
