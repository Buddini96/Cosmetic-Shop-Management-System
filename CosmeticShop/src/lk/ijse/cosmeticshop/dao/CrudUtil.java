package lk.ijse.cosmeticshop.dao;

/*
    @author BUDDINI
    @created 11/24/2022 - 12:15 PM   
*/

import lk.ijse.cosmeticshop.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql, Object...args) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = DBConnection.getInstance().getConnection()
                .prepareStatement(sql);

        for (int i = 0; i <args.length; i++) {
            pst.setObject((i+1), args[i]);
        }

        if (sql.startsWith("SELECT") || sql.startsWith("select")){
            return (T) pst.executeQuery();
        }
        return (T)(Boolean)(pst.executeUpdate() > 0);
    }
}
