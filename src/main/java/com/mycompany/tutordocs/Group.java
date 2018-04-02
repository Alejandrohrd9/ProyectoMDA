/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutordocs;

import java.util.List;

/**
 *
 * @author nacho
 */
public class Group {
    
    private int id;
    private String name;
    private List<User> members;
    
    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public List<User> members(){
        return members;
    }
    
    public void addMember(User user){
        
    }
    
    public boolean isMember(User user){
        return false;
    }
    
    public int id(){
        return id;
    }
    
    public String name(){
        return name;
    }
    
}
