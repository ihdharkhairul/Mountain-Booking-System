/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes_hikerbest.model;

/**
 *
 * @author V16_E
 */
public class Booking extends BaseEntity {
    private String namaPendaki;
    private String namaGunung;
    private String tanggalNaik;
    private int jumlahOrang;
    private double totalHarga;
    private String status;
    private String catatan;

    public Booking() {
    }

    public Booking(int id, String namaPendaki, String namaGunung, String tanggalNaik,
                   int jumlahOrang, double totalHarga, String status, String catatan) {
        setId(id);
        this.namaPendaki = namaPendaki;
        this.namaGunung = namaGunung;
        this.tanggalNaik = tanggalNaik;
        this.jumlahOrang = jumlahOrang;
        this.totalHarga = totalHarga;
        this.status = status;
        this.catatan = catatan;
    }

    // Override dari BaseEntity
    @Override
    public String getInfo() {
        return "Booking: " + namaPendaki + " | Gunung: " + namaGunung + " | Tanggal: " + tanggalNaik + " | Status: " + status;
    }

    // Override toString
    @Override
    public String toString() {
        return namaPendaki + " - " + namaGunung + " (" + status + ")";
    }

    public String getNamaPendaki() { return namaPendaki; }
    public void setNamaPendaki(String namaPendaki) { this.namaPendaki = namaPendaki; }

    public String getNamaGunung() { return namaGunung; }
    public void setNamaGunung(String namaGunung) { this.namaGunung = namaGunung; }

    public String getTanggalNaik() { return tanggalNaik; }
    public void setTanggalNaik(String tanggalNaik) { this.tanggalNaik = tanggalNaik; }

    public int getJumlahOrang() { return jumlahOrang; }
    public void setJumlahOrang(int jumlahOrang) { this.jumlahOrang = jumlahOrang; }

    public double getTotalHarga() { return totalHarga; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCatatan() { return catatan; }
    public void setCatatan(String catatan) { this.catatan = catatan; }
}