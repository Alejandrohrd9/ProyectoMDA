
package com.mycompany.tutordocs;

import java.sql.Timestamp;

/**
 *
 * @author nacho
 */
public class Hilo {
    
    private int id, id_group, id_user;
    private String title;
    private Timestamp date;

    public Hilo(int id, int id_group, int id_user, String title, Timestamp date) {
        this.id = id;
        this.id_group = id_group;
        this.id_user = id_user;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getId_group() {
        return id_group;
    }

    public int getId_user() {
        return id_user;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getDate() {
        return date;
    }
    
    
}
