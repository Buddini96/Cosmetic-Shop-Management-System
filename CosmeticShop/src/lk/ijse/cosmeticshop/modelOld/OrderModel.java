package lk.ijse.cosmeticshop.modelOld;

/*
    @author BUDDINI
    @created 11/29/2022 - 8:15 PM   
*/

import lk.ijse.cosmeticshop.model.OrderDTO;
import lk.ijse.cosmeticshop.view.tdm.Order;
import lk.ijse.cosmeticshop.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {

    public static ArrayList<OrderDTO> getOrdersData() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> ordersData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM Orders");
        while (rs.next()){
            ordersData.add(new OrderDTO(rs.getString("orderID"),
                    rs.getString("orderDate"),
                    rs.getString("customerID")));
        }
        return ordersData;
    }

    public static boolean save(Order order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Orders VALUES(?, ?, ?)";
        return CrudUtil.execute(sql, order.getOrderId(), order.getDate(), order.getCustomerId());
    }

    public boolean update(Order order, String id) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Customer SET orderDate=?, customerID=? WHERE orderID=?";
        return CrudUtil.execute(sql, order.getDate(), order.getCustomerId(), order.getCustomerId(), id);
    }


    public static boolean delete(Order order, String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Orders WHERE orderID=?";
        return CrudUtil.execute(sql,order.getOrderId());

    }
    public Order search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Orders WHERE orderID = ?";
        ResultSet rst = CrudUtil.execute(sql,id);
        if (rst.next()) {
            return new Order(rst.getString(1), rst.getString(2), rst.getString(3));
        }
        return null;
    }

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT orderID FROM Orders ORDER BY orderID DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "O00" + id;
        }
        return "O001";

    }

    public static ArrayList loadOrderIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT orderID FROM Orders";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()){
            idList.add(result.getString(1));
        }
        return idList;
    }
}
