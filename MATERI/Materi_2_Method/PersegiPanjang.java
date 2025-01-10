/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_2_Method;

/**
 *
 * @author Fajar
 */
public class PersegiPanjang {
    int p = 10;
    int l = 5;
    
    //Method Prosedur
    public void luas(){
        int L = p * l;
        System.out.println(L);
    }
    
    //Method Fungsi
    public int keliling(){
        int K = 2 * (p + l);
        
        return K;
    }
    
    public static void main(String[] args) {
        //Method Prosedur
        PersegiPanjang PP = new PersegiPanjang();
                System.out.print("Luas = ");
                PP.luas();

        //Method Fungsi
        System.out.println("Keliling = " + PP.keliling());
    }
    
}
