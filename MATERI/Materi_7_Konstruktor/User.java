/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_7_Konstruktor;

/**
 *
 * @author Fajar
 */
public class User {
    String username;
    String password;
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public static void main(String[] args) {
        User petani = new User("Petani", "ngopi");
        System.out.println("Username : " + petani.username);
        System.out.println("Password : " + petani.password);
    }
}
