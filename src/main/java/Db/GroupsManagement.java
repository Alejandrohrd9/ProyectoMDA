/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import com.mycompany.tutordocs.Group;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nacho
 */
public class GroupsManagement {
    
    private static Connection con;
    
    public static List<Group> getGroups() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = DatabaseConnection.connection();
        List<Group> groups = new ArrayList<>();
        
        String query = "select * from Groups";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            groups.add(new Group(rs.getInt("idGroup"), rs.getString("Nombre")));
        }
        
        con.close();
        return groups;
    }
    
    
    
}
