/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutordocs;

/**
 *
 * @author Cristian
 */
public class Message {

    private int idTheeme;
    private long createtAt;
    private int idCreator;
    private int idMessage;
    private String data;

    public Message(int idMessage,int idTheeme, String data, long createtAt,int idCreator) {
        this.idMessage = idMessage;
        this.idTheeme = idTheeme;
        this.data = data;
        this.createtAt = createtAt;
        this.idCreator = idCreator;
    }

    public int getIdTheeme() {
        return idTheeme;
    }

    public void setIdTheeme(int idTheeme) {
        this.idTheeme = idTheeme;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public long getCreatetAt() {
        return createtAt;
    }

    public void setCreatetAt(int createtAt) {
        this.createtAt = createtAt;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdCreator() {
        return idCreator;
    }
}
