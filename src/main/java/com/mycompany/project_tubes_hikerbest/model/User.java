/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes_hikerbest.model;

/**
 *
 * @author V16_E
 */
public class User {
    private int id;
    private String nama;
    private String email;
    private String password;
    private String noHp;
    private String role;

    public User() {
    }

    public User(int id, String nama, String email, String password, String noHp, String role) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.noHp = noHp;
        this.role = role;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNoHp() { return noHp; }
    public void setNoHp(String noHp) { this.noHp = noHp; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
