/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes_hikerbest.model;

/**
 *
 * @author V16_E
 */
public class Gunung extends BaseEntity {
    private String nama, lokasi, deskripsi, status;
    private int ketinggian;
    private double harga;

    public Gunung() {
    }

    public Gunung(int id, String nama, String lokasi, int ketinggian, double harga, String deskripsi, String status) {
        setId(id);
        this.nama = nama;
        this.lokasi = lokasi;
        this.ketinggian = ketinggian;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.status = status;
    }

    //dari BaseEntity
    @Override
    public String getInfo() {
        return "Gunung: " + nama + " | Lokasi: " + lokasi + " | Ketinggian: " + ketinggian + " mdpl";
    }

    //toString
    @Override
    public String toString() {
        return nama + " (" + lokasi + ")";
    }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getLokasi() { return lokasi; }
    public void setLokasi(String lokasi) { this.lokasi = lokasi; }

    public int getKetinggian() { return ketinggian; }
    public void setKetinggian(int ketinggian) { this.ketinggian = ketinggian; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
