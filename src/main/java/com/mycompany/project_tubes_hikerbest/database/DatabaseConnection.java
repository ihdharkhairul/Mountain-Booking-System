/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes_hikerbest.database;

/**
 *
 * @author V16_E
 */
import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hikerbest_db?useSSL=false&serverTimezone=Asia/Jakarta";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                initializeDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void initializeDatabase() {
        try (Statement stmt = connection.createStatement()) {

            // === TABEL GUNUNG ===
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS gunung(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nama VARCHAR(100) NOT NULL," +
                "lokasi VARCHAR(100) NOT NULL," +
                "ketinggian INT NOT NULL," +
                "harga DOUBLE NOT NULL," +
                "deskripsi TEXT," +
                "status VARCHAR(20) DEFAULT 'Aktif'" +
                ")"
            );

            stmt.execute(
                "CREATE TABLE IF NOT EXISTS booking(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nama_pendaki VARCHAR(100) NOT NULL," +
                "nama_gunung VARCHAR(100) NOT NULL," +
                "tanggal_naik VARCHAR(20) NOT NULL," +
                "tanggal_turun VARCHAR(20) NOT NULL," +
                "jumlah_orang INT NOT NULL," +
                "total_harga DOUBLE NOT NULL," +
                "status VARCHAR(20) DEFAULT 'Menunggu'," +
                "catatan TEXT," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")"
            );

            stmt.execute(
                "CREATE TABLE IF NOT EXISTS users(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nama VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) NOT NULL UNIQUE," +
                "password VARCHAR(100) NOT NULL," +
                "no_hp VARCHAR(20)," +
                "role VARCHAR(20) DEFAULT 'user'" +
                ")"
            );
            
            ResultSet rsAdmin = stmt.executeQuery("SELECT COUNT(*) as cnt FROM users WHERE role='admin'");
            if (rsAdmin.next() && rsAdmin.getInt("cnt") == 0) {
               stmt.execute("INSERT INTO users (nama, email, password, no_hp, role) VALUES " +
               "('Admin HikerBest', 'admin@hikerbest.com', 'admin123', '081234567890', 'admin')");
            }

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as cnt FROM gunung");
            if (rs.next() && rs.getInt("cnt") == 0) {
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Burangrang','Bandung Barat',2064,10000,'Gunung Burangrang menawarkan trek yang cukup menantang dengan pemandangan Gunung Tangkuban Perahu.','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Cikuray','Garut',2821,15000,'Gunung Cikuray dikenal dengan lautan awan dan sunrise yang memukau.','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Puntang','Bandung',2223,15000,'Gunung Puntang berada di kawasan hutan pinus dengan udara yang sangat segar.','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Gede','Cianjur',2958,28000,'Salah satu taman nasional paling populer di Indonesia dengan keanekaragaman hayati tinggi.','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Papandayan','Garut',2665,20000,'Gunung Papandayan terkenal dengan kawah aktif yang masih mengepul serta hutan mati yang indah.','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Galunggung','Tasikmalaya',2168,20000,'Gunung Galunggung terkenal dengan danau kawah yang berwarna tosca indah.','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Malabar','Bandung',2343,12000,'Gunung Malabar dikelilingi perkebunan teh Malabar yang indah dengan pemandangan Situ Cileunca.','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Manglayang','Bandung',1818,10000,'Gunung yang cocok untuk pemula dengan akses mudah dari Kota Bandung.','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Putri','Bandung Barat',2080,10000,'Gunung Putri atau Gunung Lembang merupakan salah satu tujuan hiking populer dekat Bandung','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Salak','Bogor',2211,20000,'Gunung Salak dikenal dengan jalur yang licin dan berkabut lebat.','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Ciremai','Kuningan',3078,25000,'Gunung Ciremai adalah gunung tertinggi di Jawa Barat dengan ketinggian 3.078 mdpl. Memiliki kawah aktif dan pemandangan yang menakjubkan.','Aktif')");
                stmt.execute("INSERT INTO gunung (nama,lokasi,ketinggian,harga,deskripsi,status) VALUES ('Bangkok','Purwakarta',975,15000,'Gunung bangkok menawarkan pemandangan ikonik waduk jatiluhur dari ketinggian dengan jalur pendakian berbatu yang menantang','Aktif')");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}