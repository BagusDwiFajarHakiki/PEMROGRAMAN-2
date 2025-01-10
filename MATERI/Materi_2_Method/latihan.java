/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_2_Method;

/**
 *
 * @author Fajar
 */
public class latihan {
    int p = 5;
    int l = 2;
    int L;
    
    //Method Prosedur - void
    void luas() {
        L = p * l;
        System.out.println(L);
    }
    
    //Method Fungsi - tipe data
    int Luas() {
        L = p * l;
        
        return L;
    }

    public static void main(String[] args) {
        //Method Prosedur
        latihan Pp = new latihan();
        Pp.luas();
        
        //Method Fungsi
        System.out.println(Pp.Luas());
    }
        
}
