package lk.ijse.cosmeticshop.bo.custom;

/*
    @author BUDDINI
    @created 2/4/2023 - 11:37 AM   
*/


import lk.ijse.cosmeticshop.bo.SuperBO;
import lk.ijse.cosmeticshop.entity.CustomerDTO;
import lk.ijse.cosmeticshop.entity.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException;

    boolean addOrders(OrderDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateOrders(OrderDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteOrders(String id) throws SQLException, ClassNotFoundException;

    OrderDTO searchOrders(String id) throws SQLException, ClassNotFoundException;
}
