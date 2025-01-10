/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_13_Query_Insert;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class koneksi { 
    
    static Connection koneksi;
    
    public static Connection getConnection() throws SQLException {
    // Menyiapkan parameter JDBC untuk koneksi ke database
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost/mahasiswa";
    String USER = "root";
    String PASS = "";
    
    if(koneksi == null) {
            koneksi = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        
        return koneksi;
    }
}
