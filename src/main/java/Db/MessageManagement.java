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

    public static void createMessage(String data, int iduser, int idtheme) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "insert into messages (createdAt,idtheme,data,iduser) values(?,?,?,?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        long createdAt = System.currentTimeMillis() % 1000;
        preparedStmt.setLong(1, createdAt);
        preparedStmt.setInt(2, idtheme);
        preparedStmt.setString(3, data);
        preparedStmt.setInt(4, iduser);

        preparedStmt.execute();
        con.close();
    }

    public static void deleteMessage(int idmessage) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "delete from messages where idmessages=?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, idmessage);
        preparedStmt.execute();
        con.close();
    }
    public static void updateMessage(int idmessage, String text) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "update messages set data=? where idmessages=?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, text);
        preparedStmt.setInt(2, idmessage);
        preparedStmt.execute();
        con.close();
    }

}
