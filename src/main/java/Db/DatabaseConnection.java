package Db;

import com.mycompany.tutordocs.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static Connection con;

    public static Connection connection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sURL = "jdbc:mysql://dz8959rne9lumkkw.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/m9gm4nvgikpx1sgj?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(sURL, "b6n359fv0fxrlrt5", "yjte7payqqcnltyg");

        return con;
    }

    public static User getUser(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = connection();

        String query = "select * from Users WHERE idUsers = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, id);

        ResultSet result = preparedStmt.executeQuery();
        User user = null;

        if (result.next()) {
            user = new User(result.getString("name"), result.getString("surname"), result.getString("email"), result.getString("usertype"), result.getString("username"), result.getString("password"), id, result.getString("image"));
            user.setImage(result.getString("image"));
        }

        con.close();
        return user;
    }
    
    public static void updateProfile(int id, String name, String username, String email, String image) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = connection();
        String query = "update Users set name = ?, surname = ?, email = ?, image = ? WHERE idUsers = ? ";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, name);
        preparedStmt.setString(2, username);
        preparedStmt.setString(3, email);
        preparedStmt.setString(4, image);
        preparedStmt.setInt(5, id);

        preparedStmt.executeUpdate();

        con.close();
    }

    public static String getUsername(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = connection();

        String query = "select username from Users WHERE idUsers = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet result = preparedStmt.executeQuery();
        String name = "";

        if (result.next()) {
            name = result.getString("username");
        }

        con.close();
        return name;
    }

    public static int getId(String name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = connection();

        String query = "select idUsers from Users WHERE username = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, name);
        ResultSet result = preparedStmt.executeQuery();
        int id = 0;

        if (result.next()) {
            id = result.getInt("idUsers");
        }

        con.close();
        return id;
    }

    public static boolean validateUser(String name, String pass) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        con = connection();
        String query = "select * from Users WHERE username = ? and password = ?";

        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, name);
        preparedStmt.setString(2, pass);
        ResultSet result = preparedStmt.executeQuery();

        boolean exists = result.next();
        con.close();

        return exists;

    }

    public static void registerUser(String name, String surname, String username, String password, String email, String type){
        try {
            con = DatabaseConnection.connection();
            
            String query = "insert into Users (name,surname,username,password,email,usertype) values (?,?,?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, surname);
            preparedStmt.setString(3, username);
            preparedStmt.setString(4, password);
            preparedStmt.setString(5, email);
            preparedStmt.setString(6, type);
            
            preparedStmt.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static User getUserInfo(String name) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException{

        int id = getId(name);
        
        con = DatabaseConnection.connection();
        
        String query = "select * from Users WHERE idUsers = ?";

        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, id);
        
        ResultSet result = preparedStmt.executeQuery();
        User user = null;
        if (result.next()) {
                user = new User(result.getString("name"), result.getString("surname"), result.getString("email"), result.getString("usertype"), result.getString("username"), "", id, result.getString("image"));
        }
        con.close();
        return user;
    }

}
