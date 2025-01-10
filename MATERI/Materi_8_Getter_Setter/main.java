/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_8_Getter_Setter;

/**
 *
 * @author Fajar
 */
public class main {
    public static void main(String[] args) {
        user pengguna = new user();
        pengguna.setUsername("bagus");
        pengguna.setPassword("hebat");
        
        System.out.println("Username : " + pengguna.getUsername());
        System.out.println("Password : " + pengguna.getPassword());
    }
}
