
package Db;

import com.mycompany.tutordocs.Hilo;
import com.mycompany.tutordocs.Post;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nacho
 */
public class ForumManagement {
    
    private static Connection con;
    
    public static Connection connection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sURL = "jdbc:mysql://dz8959rne9lumkkw.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/m9gm4nvgikpx1sgj?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(sURL, "b6n359fv0fxrlrt5", "yjte7payqqcnltyg");

        return con;
    }
    
    public static void deletePost(int post_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            con = connection();
            PreparedStatement st = con.prepareStatement("UPDATE hilos_mensajes SET contenido = ? WHERE id = ?");
            st.setString(1, "Mensaje borrado por el autor");
            st.setInt(2, post_id);

            st.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ForumManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deleteThread(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            con = connection();
            PreparedStatement st = con.prepareStatement("DELETE FROM Hilos WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ForumManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createHilo(int user_id, String title, String post, int group_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            con = connection();
            String query = " insert into hilos (id_creador, title, id_group) values (?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, user_id);
            preparedStmt.setString(2, title);
            preparedStmt.setInt(3, group_id);

            preparedStmt.execute();
            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                createPost(user_id, post, generatedKeys.getInt(1));
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(ForumManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createPost(int user_id, String contenido, int hilo_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            con = connection();
            String query = " insert into hilos_mensajes (hilo_id, user_id, contenido) values (?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, hilo_id);
            preparedStmt.setInt(2, user_id);
            preparedStmt.setString(3, contenido);

            preparedStmt.execute();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(ForumManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Post> getPosts(int hilo_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            con = connection();
            String query = "select * from hilos_mensajes where hilo_id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, hilo_id);
            ResultSet result = preparedStmt.executeQuery();

            List<Post> post = new ArrayList<>();

            while (result.next()) {
                post.add(new Post(result.getInt("id"), result.getInt("hilo_id"), result.getInt("user_id"), result.getString("contenido"), result.getTimestamp("date")));
            }

            con.close();
            return post;

        } catch (SQLException ex) {
            Logger.getLogger(ForumManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public static List<Hilo> getHilos(int id_group) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            con = connection();
            String query = "select * from hilos where id_group = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id_group);
            ResultSet result = preparedStmt.executeQuery();

            List<Hilo> hilos = new ArrayList<>();

            while (result.next()) {
                hilos.add(new Hilo(result.getInt("id"), result.getInt("id_group"), result.getInt("id_creador"), result.getString("title"), result.getTimestamp("date")));
            }

            con.close();
            return hilos;

        } catch (SQLException ex) {
            Logger.getLogger(ForumManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
