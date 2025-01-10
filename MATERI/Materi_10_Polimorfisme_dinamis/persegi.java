/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_10_Polimorfisme_dinamis;

/**
 *
 * @author Fajar
 */
public class persegi extends bangunDatar{
    int sisi;
    
    public persegi(int sisi){
        this.sisi = sisi;
    }
    
    @Override
    public float luas(){
        return this.sisi * this.sisi;
    }
    
    @Override
    public float keliling(){
        return this.sisi * 4;
    }
}
