/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes_hikerbest.controller;

/**
 *
 * @author V16_E
 */
import com.mycompany.project_tubes_hikerbest.database.DatabaseConnection;
import com.mycompany.project_tubes_hikerbest.model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingController {

    public List<Booking> getAllBooking() {
        List<Booking> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM booking ORDER BY id DESC");
            while (rs.next()) {
                list.add(new Booking(
                    rs.getInt("id"),
                    rs.getString("nama_pendaki"),
                    rs.getString("nama_gunung"),
                    rs.getString("tanggal_naik"),
                    rs.getInt("jumlah_orang"),
                    rs.getDouble("total_harga"),
                    rs.getString("status"),
                    rs.getString("catatan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean tambahBooking(Booking b) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO booking (nama_pendaki, nama_gunung, tanggal_naik, tanggal_turun, jumlah_orang, total_harga, status, catatan) VALUES (?,?,?,?,?,?,?,?)"
            );
            ps.setString(1, b.getNamaPendaki());
            ps.setString(2, b.getNamaGunung());
            ps.setString(3, b.getTanggalNaik());
            ps.setString(4, b.getTanggalNaik()); // tanggal turun = tanggal naik sementara
            ps.setInt(5, b.getJumlahOrang());
            ps.setDouble(6, b.getTotalHarga());
            ps.setString(7, b.getStatus() == null ? "Menunggu" : b.getStatus());
            ps.setString(8, b.getCatatan());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStatusBooking(int id, String status) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE booking SET status=? WHERE id=?");
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusBooking(int id) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM booking WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Booking getBookingById(int id) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM booking WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Booking(
                    rs.getInt("id"),
                    rs.getString("nama_pendaki"),
                    rs.getString("nama_gunung"),
                    rs.getString("tanggal_naik"),
                    rs.getInt("jumlah_orang"),
                    rs.getDouble("total_harga"),
                    rs.getString("status"),
                    rs.getString("catatan")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalBooking() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) as total FROM booking");
            if (rs.next()) return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalPending() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) as total FROM booking WHERE status='Menunggu'");
            if (rs.next()) return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Booking> getBookingTerbaru(int limit) {
        List<Booking> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM booking ORDER BY id DESC LIMIT ?"
            );
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Booking(
                    rs.getInt("id"),
                    rs.getString("nama_pendaki"),
                    rs.getString("nama_gunung"),
                    rs.getString("tanggal_naik"),
                    rs.getInt("jumlah_orang"),
                    rs.getDouble("total_harga"),
                    rs.getString("status"),
                    rs.getString("catatan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}