        package com.mycompany.tutordocs;

/**
 *
 * @author nacho
 */
public class User {
    private String name, surname,  email, type, username, pass, image;
    private int id;

    public User(String name, String surname, String email, String type, String username, String pass, int id, String image) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
        this.username = username;
        this.pass = pass;
        this.id = id;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }
    
    public String typeUser(){
        return type;
    }

    public String getPass() {
        return pass;
    }

    public int getId() {
        return id;
    }
    
    
    
    public User(String name, String pass, int id){
        this.name = name;
        this.pass = pass;
        this.id = id;
    }
    
    public String username(){
        return username;
    }
    
    public int id(){
        return id;
    }
}
