/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Materi_11_Interface;

/**
 *
 * @author Fajar
 */
public class Xiaomi implements Phone{

    private int volume;
    private boolean isPowerOn;
    
    public Xiaomi(){
        this.volume = 50;
    }
    
    @Override
    public void powerOn() {
        isPowerOn = true;
        System.out.println("HP menyala...");
        System.out.println("Selamat datang di Xiaomi...");
        System.out.println("");
    }

    @Override
    public void powerOff() {
        isPowerOn = false;
        System.out.println("HP dimatikan...");
    }

    @Override
    public void volumeUp() {
        if(isPowerOn){
            if(this.volume == MAX_VOLUME){
                System.out.println("Volume full");
                System.out.println("Sudah " + this.volume + "%");
            }else{
                this.volume += 10;
                System.out.println("Volume sekarang = " + this.volume);
            }
        }else{
            System.out.println("HP masih mati...");
        }
    }

    @Override
    public void volumeDown() {
        if(isPowerOn){
            if(this.volume == MIN_VOLUME){
                System.out.println("Volume silent");
                System.out.println("Sudah " + this.volume + "%");
            }else{
                this.volume -= 10;
                System.out.println("Volume sekarang = " + this.volume);
            }
        }else{
            System.out.println("HP masih mati...");
        }
    }
    
}
