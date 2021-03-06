/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutordocs;

import Db.DatabaseConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nacho
 */
public class Group {
    
    private int id;
    private String name;
    private List<Integer> memberIds;
    private String description;
    
    public Group(int id, String name,String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        memberIds = new ArrayList<>();
        
    }
    
    public List<String> getMembers() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        List<String> members = new ArrayList<>();
        
        for (Integer member : memberIds) {
            members.add(DatabaseConnection.getUsername(member));
        }
        
        return members;
    }
    
    public void addMemberId(int memberId){
        if(!memberIds.contains(memberId))memberIds.add(memberId);
    }
    
    public boolean isMember(User user){
        if(memberIds.contains(user.id()))return true;
        return false;
    }
    
    public int id(){
        return id;
    }
    
    public String name(){
        return name;
    }
    
    public String description(){
        return description;
    }
    
}
