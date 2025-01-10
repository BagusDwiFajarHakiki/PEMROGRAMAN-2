/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_12_Connect_Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Fajar
 */
public class Data_Main {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mahasiswa","root","");
            
            JOptionPane.showMessageDialog(null, "Berhasil terhubung ke database!!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal terhubung ke database!!!");
        }
    }
    }
