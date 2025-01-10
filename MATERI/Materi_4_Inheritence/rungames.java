/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_4_Inheritence;

/**
 *
 * @author Fajar
 */
public class rungames {
    public static void main(String[] args) {
        Enemy moster = new Enemy();
        Zombie zumbi = new Zombie();
        Pocong poci = new Pocong();
        Burung garuda = new Burung();
        
        moster.attack();
        System.out.println("Nama    : " + zumbi.name);
        System.out.println("HP      : " + zumbi.hp);
        System.out.println("DPS     : " + zumbi.attackPoin);
        
        zumbi.walk();
        zumbi.attack();
        
        poci.jump();
        poci.attack();
        
        garuda.fly();
        garuda.jump();
        garuda.walk();
        garuda.attack();
        
    }
}
