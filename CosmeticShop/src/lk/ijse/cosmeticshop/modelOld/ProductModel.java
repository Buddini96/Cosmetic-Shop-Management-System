package lk.ijse.cosmeticshop.modelOld;

/*
    @author BUDDINI
    @created 11/26/2022 - 8:37 PM   
*/


import lk.ijse.cosmeticshop.model.ProductDTO;
import lk.ijse.cosmeticshop.view.tdm.CartDetail;
import lk.ijse.cosmeticshop.view.tdm.Product;
import lk.ijse.cosmeticshop.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel {

    public static ArrayList<ProductDTO> getProductData() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> productsData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM Product ORDER BY CAST(SUBSTRING(productCode, 2) AS UNSIGNED)");
        while (rs.next()){
            productsData.add(new ProductDTO(rs.getString("productCode"),
                    rs.getString("Description"),
                    rs.getDouble("Unitprice"),
                    rs.getInt("qtyOnHand")));
        }
        return productsData;
    }
    public static boolean save(Product product) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Product VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, product.getCode(), product.getDescription(), product.getPrice(), product.getQtyOnHand());
    }

    public static boolean update (Product product, String code) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Product SET Description=?, Unitprice=?, qtyOnHand=? WHERE productCode=?";
        return CrudUtil.execute(sql, product.getDescription(), product.getPrice(),product.getQtyOnHand(), code);
    }

    public static Product search(String code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Product WHERE productCode = ?";
        ResultSet result = CrudUtil.execute(sql,code);

        if(result.next()){
            return new Product(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4)
            );
        }
        return null;
    }

    public static boolean delete (Product product, String code) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Product WHERE productCode=?";
        return CrudUtil.execute(sql,product.getCode());
    }

    public static ArrayList<String> loadProductCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT productCode FROM Product";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!updateQty(new Product(cartDetail.getCode(), cartDetail.getDescription(), cartDetail.getUnitPrice(), cartDetail.getQty()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(Product product) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Product SET qtyOnHand = qtyOnHand - ? WHERE productCode = ?";
        return CrudUtil.execute(sql, product.getQtyOnHand(), product.getCode());
    }
}
