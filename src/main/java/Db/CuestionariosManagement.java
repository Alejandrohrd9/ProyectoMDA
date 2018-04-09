package Db;

import com.mycompany.tutordocs.Apuntes;
import com.mycompany.tutordocs.Cuestionario;
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
public class CuestionariosManagement {

    private static Connection con;

    public static Connection connection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sURL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11226149";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(sURL, "sql11226149", "7rv3GI1RjU");

        return con;
    }
    
    public static List<Cuestionario> getGroupCuestionarios (int group_id)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        List<Cuestionario> cuestionarios = new ArrayList<>();
        
        String selectId = "select * from Cuestionarios where group_id = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1,group_id);
        ResultSet rs = preparedStmt.executeQuery();
        
        while (rs.next()) {
            cuestionarios.add(new Cuestionario(rs.getInt("id"), rs.getInt("id_creador"), null, rs.getTimestamp("date"),rs.getString("title")));
        }
        
        con.close();
        return cuestionarios;
    }

}
