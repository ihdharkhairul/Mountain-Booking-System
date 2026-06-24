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
import com.mycompany.project_tubes_hikerbest.model.Gunung;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GunungController implements ICrudController<Gunung> {

    @Override
    public List<Gunung> getAll() { return getAllGunung(); }

    @Override
    public boolean tambah(Gunung g) { return tambahGunung(g); }

    @Override
    public boolean update(Gunung g) { return updateGunung(g); }

    @Override
    public boolean hapus(int id) { return hapusGunung(id); }

    @Override
    public Gunung getById(int id) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM gunung WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Gunung(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("lokasi"),
                    rs.getInt("ketinggian"),
                    rs.getDouble("harga"),
                    rs.getString("deskripsi"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Gunung> getAllGunung() {
        List<Gunung> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM gunung");
            while (rs.next()) {
                list.add(new Gunung(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("lokasi"),
                    rs.getInt("ketinggian"),
                    rs.getDouble("harga"),
                    rs.getString("deskripsi"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Gunung> cariGunung(String keyword) {
        List<Gunung> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM gunung WHERE nama LIKE ? OR lokasi LIKE ?"
            );
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Gunung(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("lokasi"),
                    rs.getInt("ketinggian"),
                    rs.getDouble("harga"),
                    rs.getString("deskripsi"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Gunung getGunungByNama(String nama) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM gunung WHERE nama=?");
            ps.setString(1, nama);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Gunung(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("lokasi"),
                    rs.getInt("ketinggian"),
                    rs.getDouble("harga"),
                    rs.getString("deskripsi"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean tambahGunung(Gunung g) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO gunung (nama, lokasi, ketinggian, harga, deskripsi, status) VALUES (?,?,?,?,?,?)"
            );
            ps.setString(1, g.getNama());
            ps.setString(2, g.getLokasi());
            ps.setInt(3, g.getKetinggian());
            ps.setDouble(4, g.getHarga());
            ps.setString(5, g.getDeskripsi());
            ps.setString(6, g.getStatus() == null ? "Aktif" : g.getStatus());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateGunung(Gunung g) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE gunung SET nama=?, lokasi=?, ketinggian=?, harga=?, deskripsi=? WHERE id=?"
            );
            ps.setString(1, g.getNama());
            ps.setString(2, g.getLokasi());
            ps.setInt(3, g.getKetinggian());
            ps.setDouble(4, g.getHarga());
            ps.setString(5, g.getDeskripsi());
            ps.setInt(6, g.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusGunung(int id) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM gunung WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTotalGunung() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) as total FROM gunung");
            if (rs.next()) return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
