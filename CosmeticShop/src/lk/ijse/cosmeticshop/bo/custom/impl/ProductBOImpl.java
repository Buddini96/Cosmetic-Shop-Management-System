package lk.ijse.cosmeticshop.bo.custom.impl;

/*
    @author BUDDINI
    @created 1/30/2023 - 10:30 PM   
*/


import lk.ijse.cosmeticshop.bo.custom.ProductBO;
import lk.ijse.cosmeticshop.dao.DAOFactory;
import lk.ijse.cosmeticshop.dao.custom.ProductDAO;
import lk.ijse.cosmeticshop.model.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        return productDAO.getAll();
    }

    @Override
    public boolean addProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.add(new ProductDTO(dto.getProductCode(), dto.getDescription(), dto.getUnitprice(), dto.getQtyOnHand()));
    }

    @Override
    public boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.update(new ProductDTO(dto.getProductCode(), dto.getDescription(), dto.getUnitprice(), dto.getQtyOnHand()));
    }

    @Override
    public boolean deleteProduct(String id) throws SQLException, ClassNotFoundException {
        return productDAO.delete(id);
    }

    @Override
    public ProductDTO searchProduct(String id) throws SQLException, ClassNotFoundException {
        return productDAO.search(id);
    }
}
