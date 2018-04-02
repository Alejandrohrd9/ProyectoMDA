package Db;


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
            
            String query = "select * from Users WHERE name = ? and password = ?";
            
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
    
    
}
