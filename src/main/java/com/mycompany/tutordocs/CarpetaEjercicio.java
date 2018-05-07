/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutordocs;

/**
 *
 * @author Yisus95
 */
public class CarpetaEjercicio {
    
    private final int id;
    private final String link, title, idGroup;

    public CarpetaEjercicio(int id, String title, String link, String idGroup) {
        this.id = id;
        this.link = link;
        this.title= title;
        this.idGroup= idGroup;
    }

    public int getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
    public String getTitle() {
        return title;
    }
    
    public String getIdGroup() {
        return idGroup;
    }
}
