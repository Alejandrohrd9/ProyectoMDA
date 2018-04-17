/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import com.mycompany.tutordocs.Group;
import com.mycompany.tutordocs.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    public static void registerMember(int userId, int groupId) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = DatabaseConnection.connection();
        
        String query = "insert into Miembros (idUser, idGroup) values (?,?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        
        preparedStmt.setInt(1, userId);
        preparedStmt.setInt(2, groupId);
        
        preparedStmt.execute();
        con.close();
        
    }
    
    public static Group getGroupById(int id)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = DatabaseConnection.connection();
        
        String query = "select * from Groups where idGroup = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet result = preparedStmt.executeQuery();
        Group group = null;
        
        if(result.next()){
            group = new Group(id, result.getString("Nombre"));
        }
        
        con.close();
        getMembers(group);
        return group;
    }
    
    public static void getMembers(Group group)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = DatabaseConnection.connection();
        
        String query = "select * from Miembros where idGroup = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, group.id());
        ResultSet result = preparedStmt.executeQuery();
        while (result.next()) {
            int idUser = result.getInt("idUser");
            group.addMemberId(idUser);
        }
        
        con.close();
    }
    
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
        
        getMembers(groups);
        return groups;
    }
    
    public static void getMembers(List<Group> groups) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = DatabaseConnection.connection();
        
        String query = "select * from Miembros";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int idGroup = rs.getInt("idGroup");
            int idUser = rs.getInt("idUser");
            
            for (Group group : groups) {
                if(idGroup == group.id()){
                    group.addMemberId(idUser);
                }
            }
        }
        con.close();
    }
    
    public static void removeMember(String group,String user)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = DatabaseConnection.connection();
        
        String selectId = "select * from Miembros where idGroup = ? and idUser = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1, Integer.parseInt(group));
        preparedStmt.setInt(2, Integer.parseInt(user));
        ResultSet rs = preparedStmt.executeQuery();
        int id=0;
        while (rs.next()) {
            id = rs.getInt("id");
        
        }
        
        String query = "delete from Miembros where id = ?";
        PreparedStatement preparedStmt1 = con.prepareStatement(query);
        preparedStmt1.setInt(1, id);
        preparedStmt1.execute();

        con.close();
    }
    
    public static void updateGroupName(String name, String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            con = DatabaseConnection.connection();

            String query = "UPDATE Groups SET Nombre = ? WHERE idGroup = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, id);
            preparedStmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            con.close();
            System.out.println(e);
        }
    }
    
    public static void createGroup(String nombre) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "insert into Groups (Nombre) values (?)";
        PreparedStatement preparedStmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, nombre);
        preparedStmt.execute();
        con.close();
    }
    

    
}
