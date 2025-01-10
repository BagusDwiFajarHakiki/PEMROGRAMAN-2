/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_4_Inheritence;

/**
 *
 * @author Fajar
 */
class Pocong extends Enemy{
    void jump(){
        System.out.println("Pocong loncat-loncat");
    }
    
    @Override
    void attack(){
        System.out.println("Serang pocong");
    }
}
