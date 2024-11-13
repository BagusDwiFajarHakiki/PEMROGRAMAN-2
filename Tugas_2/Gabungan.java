/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas_2;

/**
 *
 * @author Fajar
 */
public class Gabungan {
    public static void main(String[] args) {
        MasterDosen Dosen = new MasterDosen();
        MasterMahasiswa Mahasiswa = new MasterMahasiswa();
        MasterMatkul Matkul = new MasterMatkul();
        
        System.out.println("+==================================================================================+");
        System.out.println("| Mahasiswa         : " + Mahasiswa.NIM + " - "    + Mahasiswa.bagus + "                          |");
        System.out.println("| Dosen Wali        : " + Dosen.NIDN    + " - "    + Dosen.fatra + "                  |");
        System.out.println("| Fakultas          : " + Mahasiswa.fakultas + "                                        |");
        System.out.println("| Jurusan           : " + Mahasiswa.jurusan  + "                                       |");
        System.out.println("| Jenis Kelamin     : " + Mahasiswa.gender   + "                                                |");
        System.out.println("+==================================================================================+");
        System.out.println("|              Kartu Rencana Studi (KRS) - Semester Gasal 2024 - 2025               |");
        System.out.println("+==================================================================================+");
        System.out.println("| No |        Nama Mata Kuliah            |               Nama Dosen               |");
        System.out.println("+==================================================================================+");
        System.out.println("|  1 | " + Matkul.bina + "                   | "     + Dosen.wempi + "    | ");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|  2 | " + Matkul.bing + "                     | "   + Dosen.winda + "      | ");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|  3 | " + Matkul.Pem2 + "                      | "  + Dosen.fauzan + "     | ");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|  4 | " + Matkul.TBA  + "          | "              + Dosen.huda + " | ");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|  5 | " + Matkul.stat + "        | "                + Dosen.vita + "       | ");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|  6 | " + Matkul.data + "                  | "      + Dosen.tito + "                   | ");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|  7 | " + Matkul.AOK  + " | "                       + Dosen.imam + "              | ");
        System.out.println("+==================================================================================+");
    }
}
