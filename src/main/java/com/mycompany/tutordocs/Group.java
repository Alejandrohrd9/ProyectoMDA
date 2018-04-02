/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutordocs;

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
    
    public Group(int id, String name) {
        this.id = id;
        this.name = name;
        memberIds = new ArrayList<>();
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
    
}
