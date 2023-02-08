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
        CUSTOMER,PRODUCT,ORDER,ORDER_DETAILS,DELIVERY_COMPANY,EMPLOYEE,SUPPLIER,QUERY
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case DELIVERY_COMPANY:
                return new DCompanyDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case QUERY:
                return new QueryDAOImpl();

            default:
                return null;
        }
    }
}
