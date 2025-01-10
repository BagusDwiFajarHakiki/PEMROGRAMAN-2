/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_6_Keyword_This;

/**
 *
 * @author Fajar
 */
public class This {
    String username;
    String password;
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public static void main(String[] args) {
        This user = new This();
        user.setUsername("Petani");
        user.setPassword("Istirahat");
        System.out.println("Username : " + user.username);
        System.out.println("Password : " + user.password);
    }
}
