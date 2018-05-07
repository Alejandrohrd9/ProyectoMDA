package com.mycompany.tutordocs;

public class Ejercicio {

    private final int id, idExcercisesFolder, idUser;
    private final String calification, comments, link;

    public Ejercicio(int id, String link,int idExcercisesFolder, String calification, String comments, int idUser) {
        this.id = id;
        this.idExcercisesFolder = idExcercisesFolder;
        this.calification= calification;
        this.comments= comments;
        this.idUser= idUser;
        this.link= link;
    }

    public int getId() {
        return id;
    }
    
    public String getLink() {
        return link;
    }

    public int getIdExcercisesFolder() {
        return idExcercisesFolder;
    }
    public String getCalification() {
        return calification;
    }
    
    public String getComments() {
        return comments;
    }
    
    public int getIdUser() {
        return idUser;
    }
}
