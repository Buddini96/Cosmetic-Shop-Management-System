package lk.ijse.cosmeticshop.bo;

/*
    @author BUDDINI
    @created 1/29/2023 - 11:22 AM   
*/


import lk.ijse.cosmeticshop.bo.custom.CustomerBO;
import lk.ijse.cosmeticshop.bo.custom.impl.*;
import lk.ijse.cosmeticshop.dao.DAOFactory;
import lk.ijse.cosmeticshop.dao.SuperDAO;
import lk.ijse.cosmeticshop.dao.custom.impl.CustomerDAOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER,PRODUCT,ORDER,ORDER_DETAILS,DELIVERY_COMPANY,EMPLOYEE,SUPPLIER,QUERYBO
    }

    public SuperBO getBO(BOFactory.BOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case DELIVERY_COMPANY:
                return new DCompanyBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_DETAILS:
                return new OrderDetailBOImpl();
            case QUERYBO:
                return new QueryBOImpl();

            default:
                return null;
        }
    }

}
