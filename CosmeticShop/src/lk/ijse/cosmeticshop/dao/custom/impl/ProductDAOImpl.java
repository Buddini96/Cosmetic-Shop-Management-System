package lk.ijse.cosmeticshop.dao.custom.impl;

/*
    @author BUDDINI
    @created 1/29/2023 - 11:15 AM   
*/


import lk.ijse.cosmeticshop.dao.CrudUtil;
import lk.ijse.cosmeticshop.dao.custom.ProductDAO;
import lk.ijse.cosmeticshop.model.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public ArrayList<ProductDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> products = new ArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Product ORDER BY CAST(SUBSTRING(productCode, 2) AS UNSIGNED)");
        while (rst.next()){
            ProductDTO product = new ProductDTO(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4));
            products.add(product);
        }
        return products;
    }

    @Override
    public boolean add(ProductDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Product VALUES (?, ?, ?, ?)", entity.getProductCode(), entity.getDescription(), entity.getUnitprice(), entity.getQtyOnHand());

    }

    @Override
    public boolean update(ProductDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Product SET Description=?, Unitprice=?, qtyOnHand=? WHERE productCode=?", entity.getDescription(), entity.getUnitprice(), entity.getQtyOnHand(), entity.getProductCode());

    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Product WHERE productCode=?", code);
    }

    @Override
    public ProductDTO search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Product WHERE productCode = ?", code + "");
        if (rst.next()) {
            return new ProductDTO(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4));
        }
        return null;
    }

    public ArrayList<String> loadProductCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT productCode FROM Product";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }
}
