/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Cristian
 */
public class MessageManagement {
        
    private static Connection con;
    
        public static void createMessage(String data, int iduser,int idtheme) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "insert into messages (createdAt,idtheme,data,iduser) values(?,?,?,?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        long createdAt = System.currentTimeMillis() % 1000;
        preparedStmt.setLong(1,createdAt);
        preparedStmt.setInt(2,idtheme);
        preparedStmt.setString(3, data);
        preparedStmt.setInt(4,iduser);

        preparedStmt.execute();
        con.close();
    }
    
}
