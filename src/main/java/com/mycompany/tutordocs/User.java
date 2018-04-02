/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutordocs;

/**
 *
 * @author nacho
 */
public class User {
    private String name, surname,  email, type, username, pass;
    private int id;

    public User(String name, String surname, String email, String type, String username, String pass, int id) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
        this.username = username;
        this.pass = pass;
        this.id = id;
    }
    
    public User(String name, String pass, int id){
        this.name = name;
        this.pass = pass;
        this.id = id;
    }
    
    public int id(){
        return id;
    }
}
