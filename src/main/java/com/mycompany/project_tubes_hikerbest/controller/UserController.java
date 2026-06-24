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
import com.mycompany.project_tubes_hikerbest.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserController implements ICrudController<User> {

    @Override
    public List<User> getAll() { return getAllUsers(); }

    @Override
    public boolean tambah(User u) { return tambahUser(u); }

    @Override
    public boolean update(User u) { return updateUser(u); }

    @Override
    public boolean hapus(int id) { return hapusUser(id); }

    @Override
    public User getById(int id) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("no_hp"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Ambil semua user
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User u = new User(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("no_hp"),
                    rs.getString("role")
                );
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Tambah user baru
    public boolean tambahUser(User user) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users (nama, email, password, no_hp, role) VALUES (?,?,?,?,?)"
            );
            ps.setString(1, user.getNama());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getNoHp());
            ps.setString(5, user.getRole());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update user
    public boolean updateUser(User user) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE users SET nama=?, email=?, no_hp=? WHERE id=?"
            );
            ps.setString(1, user.getNama());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getNoHp());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hapus user
    public boolean hapusUser(int id) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cari user berdasarkan email (buat ambil id pas klik tabel)
    public User getUserByEmail(String email) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("no_hp"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Login: cek email + password, return User kalau cocok, null kalau gagal
    public User login(String email, String password) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("no_hp"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Register user baru, role selalu "user"
    public boolean register(String nama, String email, String password, String noHp) {
        User u = new User();
        u.setNama(nama);
        u.setEmail(email);
        u.setPassword(password);
        u.setNoHp(noHp);
        u.setRole("user");
        return tambahUser(u);
    }

    public int getTotalUser() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) as total FROM users");
            if (rs.next()) return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
