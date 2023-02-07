package lk.ijse.cosmeticshop.modelOld;

/*
    @author BUDDINI
    @created 11/29/2022 - 8:16 PM   
*/


import lk.ijse.cosmeticshop.view.tdm.CartDetail;
import lk.ijse.cosmeticshop.dao.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailModel {

    public static boolean saveOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!save(cartDetail)) {
                return false;
            }
        }
        return true;
    }

    public static boolean save(CartDetail cartDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO OrderDetail VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql, cartDetail.getOrderId(), cartDetail.getCode(), cartDetail.getQty(), cartDetail.getUnitPrice());
    }
}
