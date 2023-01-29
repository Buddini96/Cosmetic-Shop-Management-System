package lk.ijse.cosmeticshop.dao;

/*
    @author BUDDINI
    @created 1/29/2023 - 11:12 AM   
*/


import lk.ijse.cosmeticshop.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,ITEM,ORDER,ORDER_DETAILS
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return null;
            case ORDER:
                return null;
            case ORDER_DETAILS:
                return null;

            default:
                return null;
        }
    }
}
