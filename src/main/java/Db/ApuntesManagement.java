package Db;

import com.dropbox.core.DbxException;
import com.mycompany.tutordocs.Apuntes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nacho
 */
public class ApuntesManagement {

    private static Connection con;

    public static Connection connection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sURL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11226149";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(sURL, "sql11226149", "7rv3GI1RjU");

        return con;
    }

    public static List<Apuntes> getApuntesFromGroup(int group_id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        List<Apuntes> apuntes = new ArrayList<>();

        String selectId = "select * from Apuntes where group_id = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1, group_id);
        ResultSet rs = preparedStmt.executeQuery();

        while (rs.next()) {
            apuntes.add(new Apuntes(rs.getTimestamp("date"), rs.getInt("id"), rs.getInt("group_id"), rs.getInt("user_id"), rs.getString("tema"), rs.getString("url"), rs.getString("title")));
        }

        con.close();
        return apuntes;
    }

    public static void deleteApunte(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, DbxException {
        try {
            con = DatabaseConnection.connection();
            String selectedUrl = "select url from Apuntes where id = ?";
            PreparedStatement preparedSelectStmt = con.prepareStatement(selectedUrl);
            preparedSelectStmt.setInt(1, id);
            ResultSet rs = preparedSelectStmt.executeQuery();
            while (rs.next()) {
                String a = rs.getString("url");
                Dropbox dropBox = new Dropbox();
                dropBox.deleteFile(rs.getString("url"));
                String query = "DELETE FROM Apuntes WHERE id = ?";
                PreparedStatement preparedDeleteStmt = con.prepareStatement(query);
                preparedDeleteStmt.setInt(1, id);
                preparedDeleteStmt.executeUpdate();
            }
            con.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println(e);
        }
    }

}
