/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes_hikerbest.controller;

/**
 *
 * @author V16_E
 */
import java.util.List;

public interface ICrudController<T> {
    List<T> getAll();
    boolean tambah(T entity);
    boolean update(T entity);
    boolean hapus(int id);
    T getById(int id);
}
