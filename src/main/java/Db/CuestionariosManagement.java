package Db;

import com.mycompany.tutordocs.Cuestionario;
import com.mycompany.tutordocs.Ejercicio;
import com.mycompany.tutordocs.Exercise;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nacho
 */
public class CuestionariosManagement {

    private static Connection con;

    public static Connection connection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sURL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11226149?useUnicode=true&characterEnconding=UTF-8";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(sURL, "sql11226149", "7rv3GI1RjU");

        return con;
    }

    public static List<Cuestionario> getGroupCuestionarios(int group_id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        List<Cuestionario> cuestionarios = new ArrayList<>();

        String selectId = "select * from cuestionarios where id_Grupo = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1, group_id);
        ResultSet rs = preparedStmt.executeQuery();

        while (rs.next()) {
            cuestionarios.add(new Cuestionario(rs.getInt("id"), rs.getInt("creador"), null, rs.getTimestamp("fecha"), rs.getString("titulo")));
        }

        con.close();
        return cuestionarios;
    }

    public static void insertCuestionario(String title, Timestamp date, int creator_id, int group_id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "insert into cuestionarios (titulo,fecha,creador,id_Grupo) values(?,?,?,?)";

        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, title);
        preparedStmt.setTimestamp(2, date);
        preparedStmt.setInt(3, creator_id);
        preparedStmt.setInt(4, group_id);
        preparedStmt.execute();
        con.close();
    }

    public static String idQuestionnaire(String name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "select id from cuestionarios WHERE titulo = ?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, name);
        ResultSet result = preparedStmt.executeQuery();
        String id = "";
        if (result.next()) {
            id = result.getString("id");
        }
        con.close();
        return id;
    }

    public static void insertQuestion(String titulo, String pregunta1, String pregunta2, String pregunta3, int correcta, String idF, int puntuacion) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "insert into preguntas (titulo,pregunta1,pregunta2,pregunta3,correcta,id_formulario,puntuacion) values(?,?,?,?,?,?,?)";

        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, titulo);
        preparedStmt.setString(2, pregunta1);
        preparedStmt.setString(3, pregunta2);
        preparedStmt.setString(4, pregunta3);
        preparedStmt.setInt(5, correcta);
        preparedStmt.setString(6, idF);
        preparedStmt.setInt(7, puntuacion);
        preparedStmt.execute();
        con.close();
    }

    public static void insertRegistro(int id_cuestionario, int id_usuario, double nota) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "insert into registro (id_cuestionario,id_usuario,nota,fecha) values(?,?,?,?)";
        Date date = new Date();
        Timestamp sq = new Timestamp(date.getTime());
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, id_cuestionario);
        preparedStmt.setInt(2, id_usuario);
        preparedStmt.setDouble(3, nota);
        preparedStmt.setTimestamp(4, sq);
        preparedStmt.execute();
        con.close();

    }

    public static void removeQuestion(String idF) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();

        String selectId = "select * from preguntas where id_formulario=?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setString(1, idF);
        ResultSet rs = preparedStmt.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");

        }

        String query = "delete from  preguntas where id = ?";
        PreparedStatement preparedStmt1 = con.prepareStatement(query);
        preparedStmt1.setInt(1, id);
        preparedStmt1.execute();

        con.close();
    }

    public static void removeQuestionnaire(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        String query = "delete from cuestionarios where id = ?";
        PreparedStatement preparedStmt1 = con.prepareStatement(query);
        preparedStmt1.setInt(1, id);
        preparedStmt1.execute();

        con.close();
    }

    public static List<Exercise> getEjerciciosByCuestionario(int idCuestionario) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        List<Exercise> cuestionario = new ArrayList<>();

        String selectId = "select * from preguntas where id_formulario = ?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setInt(1, idCuestionario);
        ResultSet rs = preparedStmt.executeQuery();

        while (rs.next()) {
            cuestionario.add(new Exercise(rs.getInt("id"), rs.getInt("id_formulario"), rs.getInt("correcta"),rs.getString("titulo"), rs.getString("pregunta1"), rs.getString("pregunta2"), rs.getString("pregunta3"), rs.getInt("puntuacion")));
        }

        con.close();
        return cuestionario;
    }

    /*public static List<Ejercicio> getQuestions(String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        con = DatabaseConnection.connection();
        List<Ejercicio> preguntas = new ArrayList<>();

        String selectId = "select * from preguntas where id_formulario=?";
        PreparedStatement preparedStmt = con.prepareStatement(selectId);
        preparedStmt.setString(1, id);
        ResultSet rs = preparedStmt.executeQuery();

        while (rs.next()) {
            preguntas.add(new Ejercicio(rs.getInt("id"), Integer.parseInt(rs.getString("id_formulario")), rs.getInt("correcta"), rs.getNString("titulo"), rs.getString("pregunta1"), rs.getString("pregunta2"), rs.getString("pregunta3")));
        }

        con.close();
        return preguntas;
    }*/
}
