package com.mycompany.tutordocs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author nacho
 */
public class Cuestionario {

    private int id, id_creador;
    private List<Ejercicio> ejercicios;
    private Timestamp date;
    private String title;

    public Cuestionario(int id, int id_creador, List<Ejercicio> ejercicios, Timestamp date, String title) {
        this.id = id;
        this.id_creador = id_creador;
        this.ejercicios = ejercicios;
        this.date = date;
        this.title = title;
    }

    public Cuestionario(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getId_creador() {
        return id_creador;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public String getDate() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
    }

}
