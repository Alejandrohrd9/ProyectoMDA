
package com.mycompany.tutordocs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author nacho
 */
public class Apuntes {
    private Timestamp date;
    private int id, group_id, user_id;
    private String tema, url, title;

    public Apuntes(Timestamp date, int id, int group_id, int user_id, String tema, String url, String title) {
        this.date = date;
        this.id = id;
        this.group_id = group_id;
        this.user_id = user_id;
        this.tema = tema;
        this.url = url;
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }

    public String getDate() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
    }

    public int getId() {
        return id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTema() {
        return tema;
    }

    public String getUrl() {
        return url;
    }
    
    
}
