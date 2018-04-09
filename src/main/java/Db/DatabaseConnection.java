package Db;


import com.mycompany.tutordocs.User;
import java.net.URISyntaxException;
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
        String sURL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11226149";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(sURL, "sql11226149", "7rv3GI1RjU");
        
        return con;
    }
    
    public static User getUser(int id)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = connection();
        
        String query = "select * from Users WHERE idUsers = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, id);
        
        ResultSet result = preparedStmt.executeQuery();
        User user = null;
        
        if(result.next()){
            user = new User(result.getString("name"), result.getString("surname"), result.getString("email"), result.getString("usertype"), result.getString("username"), result.getString("password"), id);
        }
        
        return user;
    }
    
    public static String getUsername(int id)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = connection();
        
        String query = "select username from Users WHERE idUsers = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet result = preparedStmt.executeQuery();
        String name = "";
        
        if(result.next()){
            name = result.getString("username");
        }
        
        con.close();
        return name;
    }
    
    public static int getId(String name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = connection();
        
        String query = "select idUsers from Users WHERE username = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, name);
        ResultSet result = preparedStmt.executeQuery();
        int id = 0;
        
        if(result.next()){
            id = result.getInt("idUsers");
        }
        
        con.close();
        return id;
    }
    
    public static boolean validateUser(String name, String pass) throws ClassNotFoundException, InstantiationException{
        try {
            try {
                con = connection();
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String query = "select * from Users WHERE username = ? and password = ?";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, pass);
            ResultSet result = preparedStmt.executeQuery();
            
            boolean exists = result.next();
            con.close();

            return exists;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static void registerUser(String name, String surname, String username, String password, String email, String type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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

    }
    
}
