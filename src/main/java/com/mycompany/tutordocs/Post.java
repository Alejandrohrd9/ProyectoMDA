
package com.mycompany.tutordocs;

import java.sql.Timestamp;

/**
 *
 * @author nacho
 */
public class Post {
    
    private int id, hilo_id, user_id;
    private String message;
    private Timestamp date;

    public Post(int id, int hilo_id, int user_id, String message, Timestamp date) {
        this.id = id;
        this.hilo_id = hilo_id;
        this.user_id = user_id;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getHilo_id() {
        return hilo_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getDate() {
        return date;
    }
    
    
    
}
