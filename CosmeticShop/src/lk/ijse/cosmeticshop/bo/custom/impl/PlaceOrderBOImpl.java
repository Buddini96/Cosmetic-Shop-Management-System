package lk.ijse.cosmeticshop.bo.custom.impl;

/*
    @author BUDDINI
    @created 2/6/2023 - 10:35 PM   
*/


import lk.ijse.cosmeticshop.bo.custom.PlaceOrderBO;
import lk.ijse.cosmeticshop.dao.DAOFactory;
import lk.ijse.cosmeticshop.dao.custom.CustomerDAO;
import lk.ijse.cosmeticshop.dao.custom.OrderDAO;
import lk.ijse.cosmeticshop.dao.custom.OrderDetailDAO;
import lk.ijse.cosmeticshop.dao.custom.ProductDAO;
import lk.ijse.cosmeticshop.model.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> entityTypeData = productDAO.getAll();
        ArrayList<ProductDTO> dtoTypeData= new ArrayList<>();
        for (ProductDTO p : entityTypeData) {
            dtoTypeData.add(new ProductDTO(p.getProductCode(),p.getDescription(),p.getUnitprice(),p.getQtyOnHand()));
        }
        return dtoTypeData;
    }
}
