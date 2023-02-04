package lk.ijse.cosmeticshop.model;

/*
    @author BUDDINI
    @created 11/29/2022 - 8:28 PM   
*/

import lk.ijse.cosmeticshop.db.DBConnection;
import lk.ijse.cosmeticshop.to.Order;
import lk.ijse.cosmeticshop.to.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {
    public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        try {

            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = OrderModel.save(new Order(placeOrder.getOrderId(), LocalDate.now(), placeOrder.getCustomerId()));
            if (isOrderAdded) {
                boolean isUpdated = ProductModel.updateQty(placeOrder.getOrderDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded = OrderDetailModel.saveOrderDetails(placeOrder.getOrderDetails());
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
