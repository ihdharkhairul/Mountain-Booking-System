/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes_hikerbest.model;

/**
 *
 * @author V16_E
 */
public abstract class BaseEntity {
    private int id;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public abstract String getInfo();
}
