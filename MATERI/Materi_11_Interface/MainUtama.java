/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_11_Interface;

import java.util.Scanner;

/**
 *
 * @author Fajar
 */
public class MainUtama {
    public static void main(String[] args) {
        Phone Redmi8Pro = new Xiaomi();
        PhoneUser bagus = new PhoneUser(Redmi8Pro);
        
        bagus.turnOnThePhone();
        
        Scanner input = new Scanner(System.in);
        String aksi = null;
        
        while(true){
            System.out.println("==============APLIKASI INTERFACE==============");
            System.out.println("[1] Nyalakan HP");
            System.out.println("[2] Matikan HP");
            System.out.println("[3] Tambah volume");
            System.out.println("[4] Kecilkan volume");
            System.out.println("[0] Keluar");
            System.out.println("Pilih Aksi");
            
            aksi = input.nextLine();
            
            if(aksi.equals("1")){
                bagus.turnOnThePhone();
            }else if(aksi.equals("2")){
                bagus.turnOffThePhone();
            }else if(aksi.equals("3")){
                bagus.MakePhoneLouder();
            }else if(aksi.equals("4")){
                bagus.MakePhoneSilent();
            }else if(aksi.equals("0")){
                System.exit(0);
            }else{
                System.out.println("Aksi tidak ada...");
            }
        }
    }
}
