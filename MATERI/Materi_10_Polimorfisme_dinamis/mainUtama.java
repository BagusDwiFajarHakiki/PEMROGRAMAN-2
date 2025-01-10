/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_10_Polimorfisme_dinamis;

/**
 *
 * @author Fajar
 */
public class mainUtama {
    public static void main(String[] args) {
        
        bangunDatar bd = new bangunDatar();
        persegi Persegi = new persegi(5);
        segitiga Segitiga = new segitiga(5, 10);
        lingkaran Lingkaran = new lingkaran(10);
        
        bd.luas();
        bd.keliling();
        
        System.out.println("Luas Persegi = " + Persegi.luas());
        System.out.println("Keliling Persegi = " + Persegi.keliling());
        System.out.println("Luas Segitiga = " + Segitiga.luas());
        System.out.println("Luas Lingkaran = " + Lingkaran.luas());
        System.out.println("Keliling Lingkaran = " + Lingkaran.keliling());
    }
}
