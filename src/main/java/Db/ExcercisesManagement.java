/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import com.mycompany.tutordocs.CarpetaEjercicio;
import com.mycompany.tutordocs.Ejercicio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yisus95
 */
public class ExcercisesManagement {
    private static Connection con;

    public static List<Ejercicio> getAllExcercisesByIdExcerciseFolder (int idExcercisesFolder)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        List<Ejercicio> excercises = new ArrayList<>();
        
        String selectId = "select * from excercises where idExcercisesFolder = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1, idExcercisesFolder);
        ResultSet rs = preparedStmt.executeQuery();
        
        while (rs.next()) {
            excercises.add(new Ejercicio(rs.getInt("id"), rs.getString("link"), rs.getInt("idExcercisesFolder"), rs.getString("calification"),rs.getString("comments"), rs.getInt("idUser")));
        }
        
        con.close();
        return excercises;
    }
    
    public static List<CarpetaEjercicio> getExcercisesFolder (int group_id)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        List<CarpetaEjercicio> excercises = new ArrayList<>();
        
        String selectId = "select * from excercises_folder where idGroup = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1, group_id);
        ResultSet rs = preparedStmt.executeQuery();
        
        while (rs.next()) {
            excercises.add(new CarpetaEjercicio(rs.getInt("id"), rs.getString("title"), rs.getString("link"), rs.getString("idGroup")));
        }
        
        con.close();
        return excercises;
    }
    
    public static String getLink(int idUser, int idExcercisesFolder)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        
        String selectId = "select link from excercises where idUser = ? and idExcercisesFolder = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1, idUser);
        preparedStmt.setInt(2, idExcercisesFolder);
        ResultSet rs = preparedStmt.executeQuery();
        String link = "No enviado";
        while (rs.next()) {
            link = rs.getString("link");
        }
        
        con.close();
        return link;
    }
    
    public static String getLinkExcerciseFolder(int idUser)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        
        String selectId = "select link from excercises_folder where id = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1, idUser);
        ResultSet rs = preparedStmt.executeQuery();
        String link = "No enviado";
        while (rs.next()) {
            link = rs.getString("link");
        }
        
        con.close();
        return link;
    }
    
    public static String getCalification(int idUser)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        
        String selectId = "select calification from excercises where idUser = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1, idUser);
        ResultSet rs = preparedStmt.executeQuery();
        String calification = "No enviado";
        while (rs.next()) {
            calification = rs.getString("calification");
        }
        
        con.close();
        return calification;
    }
    
    public static String getComments(int idUser)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        
        String selectId = "select comments from excercises where idUser = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1, idUser);
        ResultSet rs = preparedStmt.executeQuery();
        String comments = "No enviado";
        while (rs.next()) {
            comments = rs.getString("comments");
        }
        
        con.close();
        return comments;
    }
    
    public static void insertExcerciseFolder(String title, String link, int idGroup, int idUser) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        con = DatabaseConnection.connection();
        
        String query = "insert into excercises_folder (title, link, idGroup, idUser) values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, title);
        ps.setString(2, link);
        ps.setInt(3, idGroup);
        ps.setInt(4, idUser);
        ps.execute();
        
        con.close();
    }
    
    public static void insertExcercise(String link, int idExcercisesFolder, String calification, String comments, int idUser) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        
        String query = "insert into excercises (link, idExcercisesFolder, calification, comments, idUser) values (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, link);
        ps.setInt(2, idExcercisesFolder);
        ps.setString(3, calification);
        ps.setString(4, comments);
        ps.setInt(5, idUser);
        ps.execute();

        con.close();
    }
    
    public static void updateExcercise(int idExcerciseFolder, String calification, String comments, int idUser) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            con = DatabaseConnection.connection();

            String query = "UPDATE excercises SET calification = ?, comments = ? WHERE idExcercisesFolder = ? AND idUser = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, calification);
            preparedStmt.setString(2, comments);
            preparedStmt.setInt(3, idExcerciseFolder);
            preparedStmt.setInt(4, idUser);
            preparedStmt.execute();
            con.close();
        } catch (SQLException e) {
            con.close();
            System.out.println(e);
        }
    }

    public static void updateExcercise(String link, int idExcerciseFolder, String calification, String comments, int idUser) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            con = DatabaseConnection.connection();

            String query = "UPDATE excercises SET link = ? , calification = ?, comments = ? WHERE idExcercisesFolder = ? AND idUser = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, link);
            preparedStmt.setString(2, calification);
            preparedStmt.setString(3, comments);
            preparedStmt.setInt(4, idExcerciseFolder);
            preparedStmt.setInt(5, idUser);
            preparedStmt.execute();
            con.close();
        } catch (SQLException e) {
            con.close();
            System.out.println(e);
        }
    }
}
