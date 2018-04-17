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
        String sURL = "jdbc:mysql://dz8959rne9lumkkw.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/m9gm4nvgikpx1sgj?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(sURL, "b6n359fv0fxrlrt5", "yjte7payqqcnltyg");

        return con;
    }
    
    public static void insertApunte(int group_id, int user_id, String url, String title) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = DatabaseConnection.connection();
        
        String query = "insert into Apuntes (group_id, user_id, url, title) values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, group_id);
        ps.setInt(2, user_id);
        ps.setString(3, url);
        ps.setString(4, title);

        ps.execute();
        
        con.close();
        
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
