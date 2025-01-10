/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_2_Method;

/**
 *
 * @author Fajar
 */

//Static berfungsi agar kita tidak perlu membuat objek baru

public class PP_Static {
    //Method Prosedur
    public static void luas(int p, int l){
        int L = p*l;
        System.out.println(L);
    }
    
    //Method Fungsi
    public static int keliling(int p, int l){
        int K = 2 * (p + l);
        
        return K;
    }
    
    public static void main(String[] args) {
        luas(10,5);
        System.out.println(keliling(10,6));
    }
}
