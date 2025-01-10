/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_2_Method;

/**
 *
 * @author Fajar
 */

//Parameter berfungsi agar kita bisa membuat lebih dari 1 input data

public class PP_Parameter {
    //Method Prosedur
    public void luas(int p, int l){
        int L = p* l;
        System.out.println(L);
    }
    
    //Method Fungsi
    public int keliling(int p, int l){
        int K = 2 * (p + l);
        
        return K;
    }
    
    public static void main(String[] args) {
        //Method Prosedur
        PP_Parameter PPP = new PP_Parameter();
        System.out.print("Luas = ");
        PPP.luas(15, 6);
        System.out.print("Luas = ");
        PPP.luas(40, 2);
        
        //Method Fungsi
        System.out.println("Keliling = " + PPP.keliling(20, 4));
        System.out.println("Keliling = " + PPP.keliling(5, 8));
    }
}
