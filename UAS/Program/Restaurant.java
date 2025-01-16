/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Program;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fajar
 */
public class Restaurant extends javax.swing.JFrame {

    int delay = 0;
    int period = 1000;
    int x = 0;
    int total = 0;
    int no = 1;
    
    //dragged
    int xx, yy;
    /**
     * Creates new form Restaurant
     */
    public Restaurant() throws SQLException {
        initComponents();
        tabmakanan.setVisible(true);
        tabminuman.setVisible(false);
        tablaporan.setVisible(false);
        btnbayar.setEnabled(false);
        tabpenjualan.setVisible(false);
        startJam();
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data_resto","root","");
        
        tabel();
        
        jpmenu.setBackground(new Color(212,212,220));
        jplaporan.setBackground(new Color(51,153,255));
        jpdata.setBackground(new Color(51,153,255));
        jpexit.setBackground(new Color(51,153,255));
    }
    
    public boolean qtyIsZero(int qty) {
        if (qty == 0) {
            JOptionPane.showMessageDialog(null, "Tolong Isi Jumlah Pesanan diatas !!!");
            return false;
        }
        else if(kasir.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nama kasir kosong!!!");
            return false;
        }
        else if(pembeli.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nama pembeli kosong!!!");
            return false;
        }
        return true;
    }
    
    public void startJam(){
         Timer timer = new Timer();
         timer.schedule(new TimerTask() {
             @Override
             public void run() {
                 while(true){
                     Calendar now = Calendar.getInstance();
                     int j = now.get(Calendar.HOUR_OF_DAY);
                     int m = now.get(Calendar.MINUTE);
                     //int d = now.get(Calendar.SECOND);
                     
                     jam.setText(String.format("%02d:%02d", j, m));
                     
                     Date tanggalSekarang = new Date();
                     SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                     String tanggal = format.format(tanggalSekarang);
                     date.setText(tanggal);
                 }
             }
         },delay, period);
     }
    
    public void struk(){
        txtarea.setText("=============     Good Resto      =============\n"
                + "Waktu\t: " + jam.getText() + "\tTanggal : " + date.getText() + "\n"
                + "Pembeli\t: "  + pembeli.getText() + "\n"
                + "Kasir\t: " + kasir.getText() +"\n"
                + "========================================\n"
                + "Nama Menu:\t\t" + "Jumlah\t" + "Harga\n");
    }
    
    public void reset() {
        total = 0;
        x = 0;
        btntotal.setEnabled(true);
        jspin1.setValue(0);
        jspin2.setValue(0);
        jspin3.setValue(0);
        jspin4.setValue(0);
        jspin5.setValue(0);
        jspin6.setValue(0);
        jspin01.setValue(0);
        jspin02.setValue(0);
        jspin03.setValue(0);
        jspin04.setValue(0);
        jspin05.setValue(0);
        jspin06.setValue(0);
        txtarea.setText(" ");
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
        cb4.setEnabled(true);
        cb5.setEnabled(true);
        cb6.setEnabled(true);
        cb01.setEnabled(true);
        cb02.setEnabled(true);
        cb03.setEnabled(true);
        cb04.setEnabled(true);
        cb05.setEnabled(true);
        cb06.setEnabled(true);
        cb1.setSelected(false);
        cb2.setSelected(false);
        cb3.setSelected(false);
        cb4.setSelected(false);
        cb5.setSelected(false);
        cb6.setSelected(false);
        cb01.setSelected(false);
        cb02.setSelected(false);
        cb03.setSelected(false);
        cb04.setSelected(false);
        cb05.setSelected(false);
        cb06.setSelected(false);
    }
    
    public void tabel(){
       DefaultTableModel tbl = new DefaultTableModel();
       tbl.addColumn("No.");
       tbl.addColumn("Kasir");
       tbl.addColumn("Pembeli");
       tbl.addColumn("Menu");
       tbl.addColumn("Jumlah");
       tbl.addColumn("Harga");
       
        try {
            //int no = 1;
            Statement st = (Statement) koneksi.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM penjualan");
            
            while(rs.next()){
                tbl.addRow(new Object[] {
                    rs.getString("No"),
                    rs.getString("Kasir"),
                    rs.getString("Pembeli"),
                    rs.getString("Menu"),
                    rs.getString("Jumlah"),
                    rs.getString("Harga")
                });
                tabelpenjualan.setModel(tbl);
            }    
        } catch (Exception e) {
        }
    }
    
    void cari(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No.");
        tbl.addColumn("Kasir"); 
        tbl.addColumn("Pembeli");
        tbl.addColumn("Menu");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Harga");
        
        DefaultTableModel tblkosong = new DefaultTableModel();
        tblkosong.setColumnIdentifiers(new String[] {"No", "Kasir", "Pembeli", "Menu", "Jumlah", "Harga"});
       
       try {
           String cari = txtcari.getText().trim();
           if(cari.isEmpty()){
               JOptionPane.showMessageDialog(null, "kolom pencarian kosong!!!");
           }
           else{
            String data = "SELECT * FROM penjualan WHERE Kasir LIKE '" + cari + "%'" 
                                                 + "OR Pembeli LIKE '" + cari + "%'";
            Connection con =  (Connection) koneksi.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(data);
            
            boolean dataditemukan = false;
            
            while(rs.next()){
                tbl.addRow(new Object[] {
                    rs.getString("No"),
                    rs.getString("Kasir"),
                    rs.getString("Pembeli"),
                    rs.getString("Menu"),
                    rs.getString("Jumlah"),
                    rs.getString("Harga")
                });
                tabelpenjualan.setModel(tbl);
                
                dataditemukan = true;
            }  
            if(!dataditemukan){
                tabelpenjualan.setModel(tblkosong);
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!!!");
            }
            }  
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        sidemenu = new javax.swing.JPanel();
        jpmenu = new javax.swing.JPanel();
        txtmenu = new javax.swing.JLabel();
        jplaporan = new javax.swing.JPanel();
        laporan = new javax.swing.JLabel();
        jpdata = new javax.swing.JPanel();
        datapenjualan = new javax.swing.JLabel();
        jpexit = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jam = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pembeli = new javax.swing.JTextField();
        kasir = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        backmenu = new javax.swing.JPanel();
        bottom = new javax.swing.JPanel();
        btnmakan = new javax.swing.JButton();
        btnminum = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        tabmakanan = new javax.swing.JPanel();
        panedaftar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ayampanggang = new javax.swing.JLabel();
        jspin1 = new javax.swing.JSpinner();
        cb1 = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        nasigoreng = new javax.swing.JLabel();
        jspin2 = new javax.swing.JSpinner();
        cb2 = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        seafood = new javax.swing.JLabel();
        jspin3 = new javax.swing.JSpinner();
        cb3 = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        spageti = new javax.swing.JLabel();
        jspin4 = new javax.swing.JSpinner();
        cb4 = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        steak = new javax.swing.JLabel();
        jspin5 = new javax.swing.JSpinner();
        cb5 = new javax.swing.JCheckBox();
        jLabel22 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        salad = new javax.swing.JLabel();
        jspin6 = new javax.swing.JSpinner();
        cb6 = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        tabminuman = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        capucino = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jspin01 = new javax.swing.JSpinner();
        cb01 = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        coldbrew = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        cb02 = new javax.swing.JCheckBox();
        jspin02 = new javax.swing.JSpinner();
        jPanel15 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        matcha = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        cb03 = new javax.swing.JCheckBox();
        jspin03 = new javax.swing.JSpinner();
        jPanel16 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        esteh = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        cb04 = new javax.swing.JCheckBox();
        jspin04 = new javax.swing.JSpinner();
        jPanel17 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jusjeruk = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        cb05 = new javax.swing.JCheckBox();
        jspin05 = new javax.swing.JSpinner();
        jPanel18 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        smoothies = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        cb06 = new javax.swing.JCheckBox();
        jspin06 = new javax.swing.JSpinner();
        rincian = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtarea = new javax.swing.JTextArea();
        jLabel42 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnbayar = new javax.swing.JButton();
        btntotal = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        tablaporan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnupdatepaloran = new javax.swing.JButton();
        txtpembelilaporan = new javax.swing.JTextField();
        txtkasirlaporan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btndeletelaporan = new javax.swing.JButton();
        btnresetlaporan = new javax.swing.JButton();
        tabpenjualan = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelpenjualan = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        btncari = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        btndelete = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        txtkasir = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtpembeli = new javax.swing.JTextField();
        btnrefresh = new javax.swing.JButton();
        txtno = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        delall = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setPreferredSize(new java.awt.Dimension(1109, 550));

        sidemenu.setBackground(new java.awt.Color(51, 153, 255));
        sidemenu.setPreferredSize(new java.awt.Dimension(160, 550));
        sidemenu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                sidemenuMouseDragged(evt);
            }
        });
        sidemenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sidemenuMousePressed(evt);
            }
        });

        txtmenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtmenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtmenu.setText("MENU");
        txtmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtmenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpmenuLayout = new javax.swing.GroupLayout(jpmenu);
        jpmenu.setLayout(jpmenuLayout);
        jpmenuLayout.setHorizontalGroup(
            jpmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpmenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpmenuLayout.setVerticalGroup(
            jpmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpmenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtmenu, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        laporan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        laporan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        laporan.setText("LAPORAN");
        laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jplaporanLayout = new javax.swing.GroupLayout(jplaporan);
        jplaporan.setLayout(jplaporanLayout);
        jplaporanLayout.setHorizontalGroup(
            jplaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(laporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jplaporanLayout.setVerticalGroup(
            jplaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(laporan, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        datapenjualan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        datapenjualan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        datapenjualan.setText("DATA PENJUALAN");
        datapenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datapenjualanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpdataLayout = new javax.swing.GroupLayout(jpdata);
        jpdata.setLayout(jpdataLayout);
        jpdataLayout.setHorizontalGroup(
            jpdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpdataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datapenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpdataLayout.setVerticalGroup(
            jpdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpdataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datapenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        exit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("KELUAR");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpexitLayout = new javax.swing.GroupLayout(jpexit);
        jpexit.setLayout(jpexitLayout);
        jpexitLayout.setHorizontalGroup(
            jpexitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpexitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpexitLayout.setVerticalGroup(
            jpexitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpexitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jam.setText("Jam");

        date.setText("Date");

        jLabel2.setText("Nama Pembeli");

        jLabel3.setText("Nama Kasir");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("G Resto");

        javax.swing.GroupLayout sidemenuLayout = new javax.swing.GroupLayout(sidemenu);
        sidemenu.setLayout(sidemenuLayout);
        sidemenuLayout.setHorizontalGroup(
            sidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jplaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpdata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpexit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidemenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addGroup(sidemenuLayout.createSequentialGroup()
                        .addComponent(date)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jam))
                    .addComponent(pembeli, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kasir, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sidemenuLayout.createSequentialGroup()
                        .addGroup(sidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sidemenuLayout.setVerticalGroup(
            sidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidemenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jam)
                    .addComponent(date))
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jpmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jplaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpexit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pembeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        bottom.setBackground(new java.awt.Color(255, 255, 255));

        btnmakan.setText("MAKANAN");
        btnmakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnmakanMouseClicked(evt);
            }
        });

        btnminum.setText("MINUMAN");
        btnminum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnminumMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
        bottom.setLayout(bottomLayout);
        bottomLayout.setHorizontalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnmakan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(btnminum, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        bottomLayout.setVerticalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnminum, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnmakan, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap())
        );

        panel.setBackground(new java.awt.Color(255, 255, 255));

        tabmakanan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DAFTAR MAKANAN");

        javax.swing.GroupLayout panedaftarLayout = new javax.swing.GroupLayout(panedaftar);
        panedaftar.setLayout(panedaftarLayout);
        panedaftarLayout.setHorizontalGroup(
            panedaftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panedaftarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panedaftarLayout.setVerticalGroup(
            panedaftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panedaftarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/ayampanggang.jpg"))); // NOI18N

        ayampanggang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ayampanggang.setText("Ayam Panggang");

        cb1.setText("Add");
        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });

        jLabel18.setText("Harga : Rp. 25.000");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ayampanggang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jspin1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb1))
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ayampanggang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb1)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        jPanel6.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/nasigoreng.png"))); // NOI18N

        nasigoreng.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nasigoreng.setText("Nasi Goreng");

        cb2.setText("Add");
        cb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb2ActionPerformed(evt);
            }
        });

        jLabel19.setText("Harga : Rp. 15.000");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nasigoreng, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jspin2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb2))
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nasigoreng)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb2)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        jPanel7.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/seafood.png"))); // NOI18N

        seafood.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        seafood.setText("Seafood");

        cb3.setText("Add");
        cb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb3ActionPerformed(evt);
            }
        });

        jLabel20.setText("Harga : Rp. 20.000");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(seafood, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jspin3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb3))
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seafood)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb3)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspin3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        jPanel8.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/spaghetti.jpg"))); // NOI18N

        spageti.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        spageti.setText("Spaghetti");

        cb4.setText("Add");
        cb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb4ActionPerformed(evt);
            }
        });

        jLabel21.setText("Harga : Rp. 15.000");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spageti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jspin4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb4))
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spageti)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb4)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspin4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        jPanel9.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/steak.jpg"))); // NOI18N

        steak.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        steak.setText("Steak");

        cb5.setText("Add");
        cb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb5ActionPerformed(evt);
            }
        });

        jLabel22.setText("Harga : Rp. 35.000");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(steak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jspin5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb5))
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(steak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb5)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspin5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        jPanel10.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/salad.jpg"))); // NOI18N

        salad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        salad.setText("Chicken Salad");

        cb6.setText("Add");
        cb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb6ActionPerformed(evt);
            }
        });

        jLabel23.setText("Harga : Rp. 20.000");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jspin6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb6))
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb6)
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspin6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout tabmakananLayout = new javax.swing.GroupLayout(tabmakanan);
        tabmakanan.setLayout(tabmakananLayout);
        tabmakananLayout.setHorizontalGroup(
            tabmakananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabmakananLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabmakananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panedaftar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tabmakananLayout.createSequentialGroup()
                        .addGroup(tabmakananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabmakananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabmakananLayout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabmakananLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabmakananLayout.setVerticalGroup(
            tabmakananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabmakananLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panedaftar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabmakananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabmakananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabminuman.setBackground(new java.awt.Color(255, 255, 255));
        tabminuman.setPreferredSize(new java.awt.Dimension(612, 476));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DAFTAR MINUMAN");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/cappucino.png"))); // NOI18N

        capucino.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        capucino.setText("Cappuccino");

        jLabel31.setText("Harga : Rp. 8.000");

        cb01.setText("Add");
        cb01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb01ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jspin01, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb01))
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(capucino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(capucino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb01)
                        .addContainerGap())
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspin01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        jPanel14.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/coldbrew.png"))); // NOI18N

        coldbrew.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        coldbrew.setText("Cold Brew");

        jLabel33.setText("Harga : Rp. 8.000");

        cb02.setText("Add");
        cb02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb02ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jspin02, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb02))
                    .addComponent(coldbrew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(coldbrew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb02)
                        .addContainerGap())
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jspin02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        jPanel15.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/matchalatte.png"))); // NOI18N

        matcha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        matcha.setText("Matcha Latte");

        jLabel35.setText("Harga : Rp. 10.000");

        cb03.setText("Add");
        cb03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb03ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jspin03, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb03))
                    .addComponent(matcha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(matcha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb03)
                        .addContainerGap())
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jspin03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        jPanel16.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/esteh.jpg"))); // NOI18N

        esteh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        esteh.setText("Es Teh");

        jLabel37.setText("Harga : Rp. 4.000");

        cb04.setText("Add");
        cb04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb04ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(esteh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jspin04, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb04)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(esteh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb04)
                        .addContainerGap())
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jspin04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        jPanel17.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/jusjeruk.jpg"))); // NOI18N

        jusjeruk.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jusjeruk.setText("Jus Jeruk");

        jLabel39.setText("Harga : Rp. 6.000");

        cb05.setText("Add");
        cb05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb05ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jusjeruk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jspin05, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb05)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jusjeruk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb05)
                        .addContainerGap())
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jspin05, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        jPanel18.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/smoothies.jpg"))); // NOI18N

        smoothies.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        smoothies.setText("Smoothies");

        jLabel41.setText("Harga : Rp. 12.000");

        cb06.setText("Add");
        cb06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb06ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(smoothies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jspin06, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb06)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(smoothies)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb06)
                        .addContainerGap())
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jspin06, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout tabminumanLayout = new javax.swing.GroupLayout(tabminuman);
        tabminuman.setLayout(tabminumanLayout);
        tabminumanLayout.setHorizontalGroup(
            tabminumanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabminumanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabminumanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tabminumanLayout.createSequentialGroup()
                        .addGroup(tabminumanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabminumanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabminumanLayout.createSequentialGroup()
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabminumanLayout.createSequentialGroup()
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabminumanLayout.setVerticalGroup(
            tabminumanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabminumanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabminumanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabminumanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabminuman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabmakanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabminuman, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
            .addComponent(tabmakanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        rincian.setBackground(new java.awt.Color(255, 255, 255));

        txtarea.setEditable(false);
        txtarea.setColumns(20);
        txtarea.setRows(5);
        txtarea.setFocusable(false);
        jScrollPane1.setViewportView(txtarea);

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("RINCIAN PEMBAYARAN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnbayar.setText("BAYAR");
        btnbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbayarActionPerformed(evt);
            }
        });

        btntotal.setText("TOTAL");
        btntotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntotalActionPerformed(evt);
            }
        });

        btnreset.setText("RESET");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btntotal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnreset, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnbayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btntotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout rincianLayout = new javax.swing.GroupLayout(rincian);
        rincian.setLayout(rincianLayout);
        rincianLayout.setHorizontalGroup(
            rincianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rincianLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rincianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        rincianLayout.setVerticalGroup(
            rincianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rincianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kasir", "Pembeli", "Menu", "Jumlah", "Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel);
        if (tabel.getColumnModel().getColumnCount() > 0) {
            tabel.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabel.getColumnModel().getColumn(5).setCellRenderer(null);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LAPORAN PENJUALAN HARIAN");

        btnupdatepaloran.setText("Update");
        btnupdatepaloran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdatepaloranActionPerformed(evt);
            }
        });

        jLabel13.setText("Pembeli");

        jLabel17.setText("Kasir");

        btndeletelaporan.setText("Delete");
        btndeletelaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletelaporanActionPerformed(evt);
            }
        });

        btnresetlaporan.setText("Reset");
        btnresetlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetlaporanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tablaporanLayout = new javax.swing.GroupLayout(tablaporan);
        tablaporan.setLayout(tablaporanLayout);
        tablaporanLayout.setHorizontalGroup(
            tablaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tablaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablaporanLayout.createSequentialGroup()
                        .addComponent(btndeletelaporan)
                        .addGap(18, 18, 18)
                        .addComponent(btnupdatepaloran)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnresetlaporan)
                        .addGap(18, 18, 18)
                        .addGroup(tablaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtkasirlaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(tablaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(txtpembelilaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        tablaporanLayout.setVerticalGroup(
            tablaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(tablaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tablaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnupdatepaloran)
                    .addComponent(txtpembelilaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtkasirlaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndeletelaporan)
                    .addComponent(btnresetlaporan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabelpenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kasir", "Pembeli", "Menu", "Jumlah", "Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelpenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpenjualanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelpenjualan);
        if (tabelpenjualan.getColumnModel().getColumnCount() > 0) {
            tabelpenjualan.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("DATA PENJUALAN");

        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        jLabel14.setText("Kasir");

        jLabel15.setText("Pembeli");

        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        txtno.setEnabled(false);

        jLabel30.setText("No");

        delall.setText("Delete All");
        delall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabpenjualanLayout = new javax.swing.GroupLayout(tabpenjualan);
        tabpenjualan.setLayout(tabpenjualanLayout);
        tabpenjualanLayout.setHorizontalGroup(
            tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabpenjualanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabpenjualanLayout.createSequentialGroup()
                        .addComponent(btncari)
                        .addGap(18, 18, 18)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnrefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                        .addGroup(tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGap(18, 18, 18)
                        .addGroup(tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtkasir, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabpenjualanLayout.createSequentialGroup()
                        .addComponent(btndelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnupdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delall))
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabpenjualanLayout.setVerticalGroup(
            tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabpenjualanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btncari)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnrefresh))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabpenjualanLayout.createSequentialGroup()
                        .addGroup(tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtkasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpembeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabpenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btndelete)
                    .addComponent(btnupdate)
                    .addComponent(delall))
                .addContainerGap())
        );

        javax.swing.GroupLayout backmenuLayout = new javax.swing.GroupLayout(backmenu);
        backmenu.setLayout(backmenuLayout);
        backmenuLayout.setHorizontalGroup(
            backmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backmenuLayout.createSequentialGroup()
                .addGroup(backmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rincian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(backmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tablaporan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(backmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabpenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backmenuLayout.setVerticalGroup(
            backmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rincian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backmenuLayout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(backmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tablaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(backmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(backmenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabpenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(sidemenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidemenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnmakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmakanMouseClicked
        // TODO add your handling code here:
        tabmakanan.setVisible(true);
        tabminuman.setVisible(false);
    }//GEN-LAST:event_btnmakanMouseClicked

    private void btnminumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminumMouseClicked
        // TODO add your handling code here:
        tabmakanan.setVisible(false);
        tabminuman.setVisible(true);
    }//GEN-LAST:event_btnminumMouseClicked

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin1.getValue().toString());
        
            if (qtyIsZero(qty) && cb1.isSelected()) {
                x++;
                if (x == 1) {
                    struk();
                }
                int harga = qty * 25000;
                total += harga;
                txtarea.setText(txtarea.getText() + x + ". " + ayampanggang.getText() + "\tx" + jspin1.getValue() + "\tRp. " + harga + "\n");

                DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
                String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),ayampanggang.getText(),String.valueOf(jspin1.getValue()),Integer.toString(harga)};
                
                tblmodel.addRow(data);

            } else {
                cb1.setSelected(false);
            }
    }//GEN-LAST:event_cb1ActionPerformed

    private void cb01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb01ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin01.getValue().toString());
        if (qtyIsZero(qty) && cb01.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 8000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + capucino.getText() + "\tx" + jspin01.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),capucino.getText(),String.valueOf(jspin01.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb01.setSelected(false);
        }
    }//GEN-LAST:event_cb01ActionPerformed

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
        jpmenu.setBackground(new Color(51,153,255));
        jplaporan.setBackground(new Color(51,153,255));
        jpdata.setBackground(new Color(51,153,255));
        jpexit.setBackground(new Color(212,212,220));
    }//GEN-LAST:event_exitMouseClicked

    private void cb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb2ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin2.getValue().toString());
        if (qtyIsZero(qty) && cb2.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 15000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + nasigoreng.getText() + "\tx" + jspin2.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),nasigoreng.getText(),String.valueOf(jspin2.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb2.setSelected(false);
        }
    }//GEN-LAST:event_cb2ActionPerformed

    private void cb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb3ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin3.getValue().toString());
        if (qtyIsZero(qty) && cb3.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 20000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + seafood.getText() + "\t\tx" + jspin3.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),seafood.getText(),String.valueOf(jspin3.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb3.setSelected(false);
        }
    }//GEN-LAST:event_cb3ActionPerformed

    private void cb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb4ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin4.getValue().toString());
        if (qtyIsZero(qty) && cb4.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 15000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + spageti.getText() + "\t\tx" + jspin4.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),spageti.getText(),String.valueOf(jspin4.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb4.setSelected(false);
        }
    }//GEN-LAST:event_cb4ActionPerformed

    private void cb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb5ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin5.getValue().toString());
        if (qtyIsZero(qty) && cb5.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 35000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + steak.getText() + "\t\tx" + jspin5.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),steak.getText(),String.valueOf(jspin5.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb5.setSelected(false);
        }
    }//GEN-LAST:event_cb5ActionPerformed

    private void cb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb6ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin6.getValue().toString());
        if (qtyIsZero(qty) && cb6.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 20000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + salad.getText() + "\tx" + jspin6.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),salad.getText(),String.valueOf(jspin6.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb6.setSelected(false);
        }
    }//GEN-LAST:event_cb6ActionPerformed

    private void cb02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb02ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin02.getValue().toString());
        if (qtyIsZero(qty) && cb02.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 8000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + coldbrew.getText() + "\t\tx" + jspin02.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),coldbrew.getText(),String.valueOf(jspin02.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb02.setSelected(false);
        }
    }//GEN-LAST:event_cb02ActionPerformed

    private void cb03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb03ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin03.getValue().toString());
        if (qtyIsZero(qty) && cb03.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 10000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + matcha.getText() + "\tx" + jspin03.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),matcha.getText(),String.valueOf(jspin03.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb03.setSelected(false);
        }
    }//GEN-LAST:event_cb03ActionPerformed

    private void cb04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb04ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin04.getValue().toString());
        if (qtyIsZero(qty) && cb04.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 4000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + esteh.getText() + "\t\tx" + jspin04.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),esteh.getText(),String.valueOf(jspin04.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb04.setSelected(false);
        }
    }//GEN-LAST:event_cb04ActionPerformed

    private void cb05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb05ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin05.getValue().toString());
        if (qtyIsZero(qty) && cb05.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 6000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + jusjeruk.getText() + "\t\tx" + jspin05.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),jusjeruk.getText(),String.valueOf(jspin05.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb05.setSelected(false);
        }
    }//GEN-LAST:event_cb05ActionPerformed

    private void cb06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb06ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(jspin06.getValue().toString());
        if (qtyIsZero(qty) && cb06.isSelected()) {
           
            x++;
            if (x == 1) {
                struk();
            }
            int harga = qty * 12000;
            total += harga;
            txtarea.setText(txtarea.getText() + x + ". " + smoothies.getText() + "  \tx" + jspin06.getValue() + "\tRp. " + harga + "\n");

            DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
            String data[] = {Integer.toString(no++),kasir.getText(),pembeli.getText(),smoothies.getText(),String.valueOf(jspin06.getValue()),Integer.toString(harga)};
                
            tblmodel.addRow(data);
            
        } else {
            cb06.setSelected(false);
        }
    }//GEN-LAST:event_cb06ActionPerformed

    private void btnbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbayarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
        if(tblmodel.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "Pembayaran gagal!!!");
        }else{
            String kasir,pembeli,menu,jumlah,harga;
            try{
            Connection con = (Connection) koneksi.getConnection();
            
            for(int i = 0; i < tblmodel.getRowCount(); i++){
                kasir = tblmodel.getValueAt(i, 1).toString();
                pembeli = tblmodel.getValueAt(i, 2).toString();
                menu = tblmodel.getValueAt(i, 3).toString();
                jumlah = tblmodel.getValueAt(i, 4).toString();
                harga = tblmodel.getValueAt(i, 5).toString();
                
                String data = "INSERT INTO penjualan(Kasir,Pembeli,Menu,Jumlah,Harga) VALUES (?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(data);
                
                pst.setString(1, kasir);
                pst.setString(2, pembeli);
                pst.setString(3, menu);
                pst.setString(4, jumlah);
                pst.setString(5, harga);
                
                pst.execute();
            }
            JOptionPane.showMessageDialog(this, "Pembayaran berhasil!!!");
            tblmodel.setRowCount(0);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
            }
        }
        reset();
        tabel();
        btnbayar.setEnabled(false);
        btntotal.setEnabled(true);
    }//GEN-LAST:event_btnbayarActionPerformed

    private void btntotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntotalActionPerformed
        // TODO add your handling code here:
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "Anda Belum memilih Menu Pesanan  !!!");
        } else {
            txtarea.setText(txtarea.getText()
                    + "\n========================================\n"
                    + " Total     : \t\t\tRp. " + total + "\n"
                    + "=============    Terima Kasih     =============\n"
            );
            btntotal.setEnabled(false);
            cb1.setEnabled(false);
            cb2.setEnabled(false);
            cb3.setEnabled(false);
            cb4.setEnabled(false);
            cb5.setEnabled(false);
            cb6.setEnabled(false);
            cb01.setEnabled(false);
            cb02.setEnabled(false);
            cb03.setEnabled(false);
            cb04.setEnabled(false);
            cb05.setEnabled(false);
            cb06.setEnabled(false);
            btnbayar.setEnabled(true);
        }
    }//GEN-LAST:event_btntotalActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        // TODO add your handling code here:
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "Anda Belum memilih Menu Pesanan  !!!");
        } else {
            reset();
        }
    }//GEN-LAST:event_btnresetActionPerformed

    private void laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanMouseClicked
        // TODO add your handling code here:
        tablaporan.setVisible(true);
        tabpenjualan.setVisible(false);
        tabmakanan.setVisible(false);
        tabminuman.setVisible(false);
        bottom.setVisible(false);
        rincian.setVisible(false);
        jpmenu.setBackground(new Color(51,153,255));
        jplaporan.setBackground(new Color(212,212,220));
        jpdata.setBackground(new Color(51,153,255));
        jpexit.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_laporanMouseClicked

    private void datapenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datapenjualanMouseClicked
        // TODO add your handling code here:
        tabpenjualan.setVisible(true);
        tablaporan.setVisible(false);
        tabmakanan.setVisible(false);
        tabminuman.setVisible(false);
        bottom.setVisible(false);
        rincian.setVisible(false);
        jpmenu.setBackground(new Color(51,153,255));
        jplaporan.setBackground(new Color(51,153,255));
        jpdata.setBackground(new Color(212,212,220));
        jpexit.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_datapenjualanMouseClicked

    private void tabelpenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpenjualanMouseClicked
        // TODO add your handling code here:
        int row = tabelpenjualan.rowAtPoint(evt.getPoint());
        String no = tabelpenjualan.getValueAt(row, 0).toString();
        txtno.setText(no);
        String kasir = tabelpenjualan.getValueAt(row, 1).toString();
        txtkasir.setText(kasir);
        String pembeli = tabelpenjualan.getValueAt(row, 2).toString();
        txtpembeli.setText(pembeli);
    }//GEN-LAST:event_tabelpenjualanMouseClicked

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        try {
            String data = "UPDATE penjualan SET No ='" + txtno.getText()
                                                + "',Kasir ='" + txtkasir.getText()
                                                + "',Pembeli ='" + txtpembeli.getText()
                                                + "'WHERE No ='" + txtno.getText() + "'";
            Connection con = (Connection) koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(data);
            pst.execute();
            
        DefaultTableModel tblModel = (DefaultTableModel)tabelpenjualan.getModel();
        if(tabelpenjualan.getSelectedRowCount()==1){
                      
            tblModel.setValueAt(txtkasir.getText(), tabelpenjualan.getSelectedRow(), 1);
            tblModel.setValueAt(txtpembeli.getText(), tabelpenjualan.getSelectedRow(), 2);
            
            
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!!!");
        }else{
            if(tabelpenjualan.getRowCount()==0){
                JOptionPane.showMessageDialog(this, "Tabel kosong!!!");
            }else{
                JOptionPane.showMessageDialog(this, "Harap pilih satu data!!!");
                 }
             }

        } catch (Exception e) {
        }
        txtno.setText("");
        txtkasir.setText("");
        txtpembeli.setText("");
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        try {
            String data = "DELETE FROM penjualan WHERE No ='" + txtno.getText()+ "'";
            Connection con = (Connection) koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(data);
            pst.execute();
            
            DefaultTableModel tblModel = (DefaultTableModel)tabelpenjualan.getModel();
        
        if(tabelpenjualan.getSelectedRowCount()==1){
            tblModel.removeRow(tabelpenjualan.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Berhasil dihapus");
        }else{
            if(tabelpenjualan.getRowCount()==0){
                JOptionPane.showMessageDialog(this, "Tabel kosong!!!");
            }else{
                JOptionPane.showMessageDialog(this, "Harap pilih satu data!!!");
                 }
             }
        
        } catch (Exception e) {
        }
        txtno.setText("");
        txtkasir.setText("");
        txtpembeli.setText("");
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        cari();
        txtno.setText("");
        txtkasir.setText("");
        txtpembeli.setText("");
    }//GEN-LAST:event_btncariActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // TODO add your handling code here:
        tabel();
        txtno.setText("");
        txtkasir.setText("");
        txtpembeli.setText("");
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void delallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delallActionPerformed
        // TODO add your handling code here:
        try {
            String data = "TRUNCATE TABLE penjualan";
            Connection con = (Connection) koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(data);
            pst.execute();
            
            DefaultTableModel tblkosong = new DefaultTableModel();
            tblkosong.setColumnIdentifiers(new String[] {"No", "Kasir", "Pembeli", "Menu", "Jumlah", "Harga"});
            
            JOptionPane.showMessageDialog(null, "Berhasil dihapus");
       
            tabelpenjualan.setModel(tblkosong);
        } catch (Exception e) {
        }
        txtno.setText("");
        txtkasir.setText("");
        txtpembeli.setText("");
    }//GEN-LAST:event_delallActionPerformed

    private void txtmenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmenuMouseClicked
        // TODO add your handling code here:
        tabmakanan.setVisible(true);
        tabminuman.setVisible(false);
        bottom.setVisible(true);
        rincian.setVisible(true);
        tablaporan.setVisible(false);
        tabpenjualan.setVisible(false);
        jpmenu.setBackground(new Color(212,212,220));
        jplaporan.setBackground(new Color(51,153,255));
        jpdata.setBackground(new Color(51,153,255));
        jpexit.setBackground(new Color(51,153,255));
        txtmenu.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_txtmenuMouseClicked

    private void btnupdatepaloranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdatepaloranActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        int i = tabel.getSelectedRow();
        if(i >= 0){
            model.setValueAt(txtkasirlaporan.getText(), i, 1);
            model.setValueAt(txtpembelilaporan.getText(), i, 2);
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!!!");
            txtkasirlaporan.setText("");
            txtpembelilaporan.setText("");
            }else{
            if(tabel.getRowCount()==0){
                JOptionPane.showMessageDialog(this, "Tabel kosong!!!");
            }else{
                JOptionPane.showMessageDialog(this, "Harap pilih satu data!!!");
                 }
             }
    }//GEN-LAST:event_btnupdatepaloranActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        int row = tabel.getSelectedRow();
        txtkasirlaporan.setText(model.getValueAt(row, 1).toString());
        txtpembelilaporan.setText(model.getValueAt(row, 2).toString());
    }//GEN-LAST:event_tabelMouseClicked

    private void btndeletelaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletelaporanActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tblmodel = (DefaultTableModel) tabel.getModel();
        
        if(tabel.getSelectedRowCount()==1){
            tblmodel.removeRow(tabel.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Berhasil dihapus");
        }else{
            if(tabel.getRowCount()==0){
                JOptionPane.showMessageDialog(this, "Tabel kosong!!!");
            }else{
                JOptionPane.showMessageDialog(this, "Harap pilih satu data!!!");
                }
        }
    }//GEN-LAST:event_btndeletelaporanActionPerformed

    private void btnresetlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetlaporanActionPerformed
        // TODO add your handling code here:
        txtkasirlaporan.setText("");
        txtpembelilaporan.setText("");
    }//GEN-LAST:event_btnresetlaporanActionPerformed

    private void sidemenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidemenuMousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_sidemenuMousePressed

    private void sidemenuMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidemenuMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - yy);
    }//GEN-LAST:event_sidemenuMouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Restaurant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Restaurant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Restaurant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Restaurant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Restaurant().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Restaurant.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ayampanggang;
    private javax.swing.JPanel background;
    private javax.swing.JPanel backmenu;
    private javax.swing.JPanel bottom;
    private javax.swing.JButton btnbayar;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btndeletelaporan;
    private javax.swing.JButton btnmakan;
    private javax.swing.JButton btnminum;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnresetlaporan;
    private javax.swing.JButton btntotal;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton btnupdatepaloran;
    private javax.swing.JLabel capucino;
    private javax.swing.JCheckBox cb01;
    private javax.swing.JCheckBox cb02;
    private javax.swing.JCheckBox cb03;
    private javax.swing.JCheckBox cb04;
    private javax.swing.JCheckBox cb05;
    private javax.swing.JCheckBox cb06;
    private javax.swing.JCheckBox cb1;
    private javax.swing.JCheckBox cb2;
    private javax.swing.JCheckBox cb3;
    private javax.swing.JCheckBox cb4;
    private javax.swing.JCheckBox cb5;
    private javax.swing.JCheckBox cb6;
    private javax.swing.JLabel coldbrew;
    private javax.swing.JLabel datapenjualan;
    private javax.swing.JLabel date;
    private javax.swing.JButton delall;
    private javax.swing.JLabel esteh;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jam;
    private javax.swing.JPanel jpdata;
    private javax.swing.JPanel jpexit;
    private javax.swing.JPanel jplaporan;
    private javax.swing.JPanel jpmenu;
    private javax.swing.JSpinner jspin01;
    private javax.swing.JSpinner jspin02;
    private javax.swing.JSpinner jspin03;
    private javax.swing.JSpinner jspin04;
    private javax.swing.JSpinner jspin05;
    private javax.swing.JSpinner jspin06;
    private javax.swing.JSpinner jspin1;
    private javax.swing.JSpinner jspin2;
    private javax.swing.JSpinner jspin3;
    private javax.swing.JSpinner jspin4;
    private javax.swing.JSpinner jspin5;
    private javax.swing.JSpinner jspin6;
    private javax.swing.JLabel jusjeruk;
    private javax.swing.JTextField kasir;
    private javax.swing.JLabel laporan;
    private javax.swing.JLabel matcha;
    private javax.swing.JLabel nasigoreng;
    private javax.swing.JPanel panedaftar;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField pembeli;
    private javax.swing.JPanel rincian;
    private javax.swing.JLabel salad;
    private javax.swing.JLabel seafood;
    private javax.swing.JPanel sidemenu;
    private javax.swing.JLabel smoothies;
    private javax.swing.JLabel spageti;
    private javax.swing.JLabel steak;
    private javax.swing.JTable tabel;
    private javax.swing.JTable tabelpenjualan;
    private javax.swing.JPanel tablaporan;
    private javax.swing.JPanel tabmakanan;
    private javax.swing.JPanel tabminuman;
    private javax.swing.JPanel tabpenjualan;
    private javax.swing.JTextArea txtarea;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtkasir;
    private javax.swing.JTextField txtkasirlaporan;
    private javax.swing.JLabel txtmenu;
    private javax.swing.JTextField txtno;
    private javax.swing.JTextField txtpembeli;
    private javax.swing.JTextField txtpembelilaporan;
    // End of variables declaration//GEN-END:variables
}
