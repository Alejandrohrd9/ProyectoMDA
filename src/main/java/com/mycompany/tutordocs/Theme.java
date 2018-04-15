/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutordocs;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Cristian
 */
public class Theme {

    private List<Message> messages;
    private String name;
    private int creator;
    int idtheme;

    public Theme(String name, int creator, int idtheme) {
        this.name = name;
        this.creator = creator;
        this.idtheme = idtheme;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getName() {
        return name;
    }

    public int getCreator() {
        return creator;
    }

    public int getIdtheme() {
        return idtheme;
    }
}
