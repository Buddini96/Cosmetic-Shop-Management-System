package lk.ijse.cosmeticshop.bo.custom.impl;

/*
    @author BUDDINI
    @created 2/8/2023 - 10:56 PM   
*/


import lk.ijse.cosmeticshop.bo.custom.QueryBO;
import lk.ijse.cosmeticshop.dao.DAOFactory;
import lk.ijse.cosmeticshop.dao.custom.OrderDAO;
import lk.ijse.cosmeticshop.dao.custom.OrderDetailDAO;
import lk.ijse.cosmeticshop.dao.custom.ProductDAO;
import lk.ijse.cosmeticshop.dao.custom.QueryDAO;
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

public class QueryBOImpl implements QueryBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    public  boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws SQLException, ClassNotFoundException {
        try {

            DBConnection.getInstance().getConnection().setAutoCommit(false);
            OrderDAO orderDAO = new OrderDAOImpl();
            ProductDAO productDAO = new ProductDAOImpl();
            OrderDetailDAO orderDetailDAO = new OrderDetailsDAOImpl();

            boolean isOrderAdded = orderDAO.add(new OrderDTO(placeOrderDTO.getOrderID(), LocalDate.now(), placeOrderDTO.getCustomerID()));
            if (isOrderAdded) {
                boolean isUpdated = updateQty(placeOrderDTO.getOrderDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded = saveOrderDetails(placeOrderDTO.getOrderDetails());
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

    public  boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        for (CartDetail cartDetail : cartDetails) {
            if (!productDAO.update(new ProductDTO(cartDetail.getCode(), cartDetail.getDescription(), cartDetail.getUnitPrice(), cartDetail.getQty()))) {
                return false;
            }
        }
        return true;
    }

    public  boolean saveOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        OrderDetailsDAOImpl orderDetailsDAO = new OrderDetailsDAOImpl();
        for (CartDetail cartDetail : cartDetails) {
            if (!orderDetailsDAO.add(new OrderDetailDTO(cartDetail.getOrderId(), cartDetail.getCode(), cartDetail.getQty(), cartDetail.getUnitPrice()))) {
                return false;
            }
        }
        return true;
    }

}
