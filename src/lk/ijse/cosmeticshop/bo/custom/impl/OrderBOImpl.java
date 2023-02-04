package lk.ijse.cosmeticshop.bo.custom.impl;

/*
    @author BUDDINI
    @created 2/4/2023 - 11:37 AM   
*/


import lk.ijse.cosmeticshop.bo.custom.OrderBO;
import lk.ijse.cosmeticshop.dao.DAOFactory;
import lk.ijse.cosmeticshop.dao.custom.OrderDAO;
import lk.ijse.cosmeticshop.entity.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        return orderDAO.getAll();
    }

    @Override
    public boolean addOrders(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.add(new OrderDTO(dto.getOrderID(), dto.getOrderDate(), dto.getCustomerID()));
    }

    @Override
    public boolean updateOrders(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new OrderDTO(dto.getOrderID(), dto.getOrderDate(), dto.getCustomerID()));
    }

    @Override
    public boolean deleteOrders(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public OrderDTO searchOrders(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.search(id);
    }
}
