package lk.ijse.cosmeticshop.bo.custom.impl;

/*
    @author BUDDINI
    @created 2/8/2023 - 10:56 PM   
*/


import lk.ijse.cosmeticshop.bo.custom.QueryBO;
import lk.ijse.cosmeticshop.dao.DAOFactory;
import lk.ijse.cosmeticshop.dao.custom.QueryDAO;
import lk.ijse.cosmeticshop.model.PlaceOrderDTO;

import java.sql.SQLException;

public class QueryBOImpl implements QueryBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    public boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws SQLException, ClassNotFoundException {
        return queryDAO.placeOrder();
    }

}
