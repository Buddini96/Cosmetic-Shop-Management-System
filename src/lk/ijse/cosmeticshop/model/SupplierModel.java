package lk.ijse.cosmeticshop.model;

/*
    @author BUDDINI
    @created 11/27/2022 - 8:11 PM   
*/

import lk.ijse.cosmeticshop.entity.SupplierDTO;
import lk.ijse.cosmeticshop.to.Supplier;
import lk.ijse.cosmeticshop.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static ArrayList<SupplierDTO> getSupplierData() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> suppliersData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM Supplier ORDER BY CAST(SUBSTRING(supplierID, 2) AS UNSIGNED)");
        while (rs.next()){
            suppliersData.add(new SupplierDTO(rs.getString("supplierID"),
                    rs.getString("name"),
                    rs.getString("description")));
        }
        return suppliersData;
    }

    public static boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Supplier VALUES (?, ?, ?)";
        return CrudUtil.execute(sql, supplier.getSupId(), supplier.getName(), supplier.getDescription());
    }

    public static boolean update (Supplier supplier, String supId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Supplier SET Name=?, Description=? WHERE supplierID=?";
        return CrudUtil.execute(sql, supplier.getName(), supplier.getDescription(),supId);
    }

    public static boolean delete (Supplier supplier, String supId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Supplier WHERE supplierID=?";
        return CrudUtil.execute(sql,supplier.getSupId());
    }

    public static Supplier search(String supId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Supplier WHERE supplierID = ?";
        ResultSet result = CrudUtil.execute(sql,supId);

        if(result.next()){
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)
            );
        }
        return null;
    }
}
