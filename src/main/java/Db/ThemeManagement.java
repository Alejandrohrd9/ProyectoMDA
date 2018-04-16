/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import com.mycompany.tutordocs.Message;
import com.mycompany.tutordocs.Theme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristian
 */
public class ThemeManagement {

    private static Connection con;

    public static List<Message> getMessagesByTheme(int idtheme) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();

        List<Message> messages = new ArrayList<>();
        String query = "select * from messages where idtheme = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, idtheme);

        ResultSet result = preparedStmt.executeQuery();
        while (result.next()) {
            messages.add(new Message(result.getInt("idtheme"),
                    result.getString("data"), result.getLong("createdAt"),
                    result.getInt("iduser")));
        }

        con.close();
        return messages;
    }

    public static void createTheme(String name, int iduser) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "insert into themes (name,iduser) values(?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, name);
        preparedStmt.setInt(2, iduser);

        preparedStmt.execute();
        con.close();
    }

    public static void deleteTheme(int idtheme) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "delete from themes where idthemes = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, idtheme);

        preparedStmt.execute();
        con.close();
    }

    public static List<Theme> getAllThemes() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        List<Theme> theme = new ArrayList<>();
        String query = "select * from themes";
        PreparedStatement preparedStmt = con.prepareStatement(query);

        ResultSet result = preparedStmt.executeQuery();
        while (result.next()) {
            theme.add(new Theme(result.getString("name"), result.getInt("iduser"), result.getInt("idthemes")));
        }

        con.close();
        return theme;
    }

    public static Theme getThemeByID(int idtheme) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "select * from themes where idthemes = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, idtheme);

        ResultSet result = preparedStmt.executeQuery();
        Theme theme = new Theme(result.getString("name"), result.getInt("iduser"), result.getInt("idthemes"));
        con.close();
        return theme;
    }

}
