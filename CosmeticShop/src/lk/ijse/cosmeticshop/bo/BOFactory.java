package lk.ijse.cosmeticshop.bo;

/*
    @author BUDDINI
    @created 1/29/2023 - 11:22 AM   
*/


import lk.ijse.cosmeticshop.bo.custom.CustomerBO;
import lk.ijse.cosmeticshop.bo.custom.impl.CustomerBOImpl;
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
        CUSTOMER,ITEM,ORDER,ORDER_DETAILS
    }

    public SuperBO getBO(BOFactory.BOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
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
