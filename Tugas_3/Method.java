/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas_3;

/**
 *
 * @author Fajar
 */
public class Method {
    String nama;
    double nilai;
    
    public String Nama(String nama){
        this.nama = nama; 
        
        return nama;
    }
    
    public double Nilai(double nilai){
        this.nilai = nilai;
        
        return nilai;
    }
    
    public void Keterangan() {
        if (nilai >= 70) {
            System.out.println("Lulus");
        } else {
            System.out.println("Tidak Lulus");
        }
    }
    
    public static void main(String[] args) {
        Method hasil = new Method();
        
        System.out.println("Nama       = " + hasil.Nama("Bagus"));
        System.out.println("Nilai      = " + hasil.Nilai(60));
        System.out.print(  "Keterangan = ");
        hasil.Keterangan();
        System.out.println("");
        System.out.println("Nama       = " + hasil.Nama("Dwi"));
        System.out.println("Nilai      = " + hasil.Nilai(90));
        System.out.print(  "Keterangan = ");
        hasil.Keterangan();
    }
}
