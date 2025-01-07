/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package program;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Fajar
 */
public final class JamApps extends javax.swing.JFrame implements Runnable{
    
    Timer timer;
    int delay = 0;
    int period = 1000;
    
    int status = 0;
    
    //stopwatch
    int milidetik = 0;
    int detik = 0;
    int menit = 0;
    int jam = 0;
    boolean state = true;
    
    //timer
    boolean timerstart = false;
    boolean timerpause = false;
    
    //dragged
    int xx, yy;

    Thread th;

    /**
     * Creates new form JamApps
     */
    public JamApps() {
        initComponents();
        setComboBox();
        startJam();
    }
    
    public void setComboBox(){
         for(int i = 0; i < 24; i++){
             cmbjam.getItemAt(i);
         }
         for(int i = 0; i <60; i++){
             cmbmenit.getItemAt(i);
         }
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
                     int d = now.get(Calendar.SECOND);
                     
                     lbljam.setText(String.format("%02d:%02d:%02d", j, m, d));
                     
                     Date tanggalSekarang = new Date();
                     SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                     String tanggal = format.format(tanggalSekarang);
                     lbltanggal.setText("Saat Ini : " + tanggal);
                     
                     SimpleDateFormat hariformat = new SimpleDateFormat("EEEE");
                     String hari = hariformat.format(tanggalSekarang);
                     lblhari.setText(hari);
                 }
             }
         },delay, period);
     }
     
     public static void playmusic(String location){
        try {
            File musicPath = new File(location);
            
            if(musicPath.exists())
            {
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     
     public void run(){
         try {
             while(true){
                 Thread.sleep(1000);
                 Calendar now = Calendar.getInstance();
                 int j = now.get(Calendar.HOUR_OF_DAY);
                 int m = now.get(Calendar.MINUTE);
                 int d = now.get(Calendar.SECOND);
                 
                 int jam = Integer.valueOf(cmbjam.getSelectedItem().toString());
                 int menit = Integer.valueOf(cmbmenit.getSelectedItem().toString());
                 
                 waktualarm.setText(String.format("%02d:%02d", jam, menit));
                 
                 if(btnswtich.getText().equals("ON"))
                 {
                     alarmaktif.setText("Alarm Aktif");
                 if((jam == j)&&(menit == m)&&(d == 0))
                 {
                     String audio = "src\\program\\sound\\sound.wav";
                     playmusic(audio);
                     status = 0;
                     ImageIcon icon = new ImageIcon("src\\program\\icon\\add+.png");
                     btnaddalarm.setIcon(icon);
                     cmbjam.setEnabled(true);
                     cmbmenit.setEnabled(true);
                     btnswtich.setVisible(true);
                     th.stop();
                 }
                 }
                 else
                 {
                     alarmaktif.setText("Alarm Mati");
                     th.stop();
                 }
         } 
     }catch (InterruptedException ex) {
     }catch (NumberFormatException ex){
     }catch (UnsupportedOperationException ex){
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
        bottom = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Alarm_tab = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cmbjam = new javax.swing.JComboBox<>();
        cmbmenit = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        waktualarm = new javax.swing.JLabel();
        btnswtich = new javax.swing.JButton();
        btnaddalarm = new javax.swing.JLabel();
        alarmaktif = new javax.swing.JLabel();
        Jam_tab = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbljam = new javax.swing.JLabel();
        lbltanggal = new javax.swing.JLabel();
        lblhari = new javax.swing.JLabel();
        Stopwatch_tab = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblstopwatch = new javax.swing.JLabel();
        btnplaysw = new javax.swing.JLabel();
        btnpausesw = new javax.swing.JLabel();
        btnstopsw = new javax.swing.JLabel();
        Timer_tab = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtjam = new javax.swing.JTextField();
        txtmenit = new javax.swing.JTextField();
        txtdetik = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnplaytimer = new javax.swing.JLabel();
        btnpausetimer = new javax.swing.JLabel();
        btnstoptimer = new javax.swing.JLabel();
        paneljamtimer = new javax.swing.JPanel();
        jamm = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        menitt = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        detikk = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setBackground(new java.awt.Color(204, 204, 204));

        bottom.setBackground(new java.awt.Color(51, 51, 51));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/alarm.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Alarm");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/jam.png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Jam");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Stopwatch");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/stopwatch.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/timer.png"))); // NOI18N
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Timer");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
        bottom.setLayout(bottomLayout);
        bottomLayout.setHorizontalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(bottomLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        bottomLayout.setVerticalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bottomLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17))
                    .addGroup(bottomLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13))
                    .addGroup(bottomLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Alarm_tab.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Alarm");

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setText("Jam");

        cmbjam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        cmbmenit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel6.setText("Menit");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbjam, 0, 228, Short.MAX_VALUE)
                    .addComponent(cmbmenit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbmenit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        waktualarm.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        waktualarm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        waktualarm.setText("00:00");

        btnswtich.setText("OFF");
        btnswtich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnswtichMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(waktualarm, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnswtich, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(waktualarm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnswtich)
                .addGap(17, 17, 17))
        );

        btnaddalarm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnaddalarm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/add+.png"))); // NOI18N
        btnaddalarm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnaddalarmMouseClicked(evt);
            }
        });

        alarmaktif.setForeground(new java.awt.Color(255, 255, 255));
        alarmaktif.setText("Next Alarm");

        javax.swing.GroupLayout Alarm_tabLayout = new javax.swing.GroupLayout(Alarm_tab);
        Alarm_tab.setLayout(Alarm_tabLayout);
        Alarm_tabLayout.setHorizontalGroup(
            Alarm_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Alarm_tabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Alarm_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Alarm_tabLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnaddalarm))
                    .addGroup(Alarm_tabLayout.createSequentialGroup()
                        .addGroup(Alarm_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(alarmaktif))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Alarm_tabLayout.setVerticalGroup(
            Alarm_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Alarm_tabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(alarmaktif)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(btnaddalarm)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab1", Alarm_tab);

        Jam_tab.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Jam");

        lbljam.setFont(new java.awt.Font("Segoe Print", 1, 36)); // NOI18N
        lbljam.setForeground(new java.awt.Color(255, 255, 255));
        lbljam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbljam.setText("00:00:00");

        lbltanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbltanggal.setForeground(new java.awt.Color(255, 255, 255));
        lbltanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltanggal.setText("Saat ini : dd/mm/yyyy");

        lblhari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblhari.setForeground(new java.awt.Color(255, 255, 255));
        lblhari.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblhari.setText("Hari");

        javax.swing.GroupLayout Jam_tabLayout = new javax.swing.GroupLayout(Jam_tab);
        Jam_tab.setLayout(Jam_tabLayout);
        Jam_tabLayout.setHorizontalGroup(
            Jam_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jam_tabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jam_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbljam, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(lbltanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Jam_tabLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblhari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Jam_tabLayout.setVerticalGroup(
            Jam_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jam_tabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lbljam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltanggal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblhari)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", Jam_tab);

        Stopwatch_tab.setBackground(new java.awt.Color(51, 51, 51));
        Stopwatch_tab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Stopwatch");
        Stopwatch_tab.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        lblstopwatch.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblstopwatch.setForeground(new java.awt.Color(255, 255, 255));
        lblstopwatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblstopwatch.setText("00:00:00.00");
        Stopwatch_tab.add(lblstopwatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 105, 288, -1));

        btnplaysw.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnplaysw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/play.png"))); // NOI18N
        btnplaysw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnplayswMouseClicked(evt);
            }
        });
        Stopwatch_tab.add(btnplaysw, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        btnpausesw.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnpausesw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/pause.png"))); // NOI18N
        btnpausesw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnpauseswMouseClicked(evt);
            }
        });
        Stopwatch_tab.add(btnpausesw, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 105, -1));

        btnstopsw.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnstopsw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/stop.png"))); // NOI18N
        btnstopsw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnstopswMouseClicked(evt);
            }
        });
        Stopwatch_tab.add(btnstopsw, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 105, -1));

        jTabbedPane1.addTab("tab3", Stopwatch_tab);

        Timer_tab.setBackground(new java.awt.Color(51, 51, 51));
        Timer_tab.setPreferredSize(new java.awt.Dimension(300, 360));
        Timer_tab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Timer");
        Timer_tab.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        txtjam.setBackground(new java.awt.Color(102, 102, 102));
        txtjam.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtjam.setForeground(new java.awt.Color(255, 255, 255));
        txtjam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtjam.setText("00");
        Timer_tab.add(txtjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 100, 50, 50));

        txtmenit.setBackground(new java.awt.Color(102, 102, 102));
        txtmenit.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtmenit.setForeground(new java.awt.Color(255, 255, 255));
        txtmenit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmenit.setText("05");
        Timer_tab.add(txtmenit, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 100, 50, 50));

        txtdetik.setBackground(new java.awt.Color(102, 102, 102));
        txtdetik.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtdetik.setForeground(new java.awt.Color(255, 255, 255));
        txtdetik.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdetik.setText("00");
        Timer_tab.add(txtdetik, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 50, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Jam");
        Timer_tab.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 67, 50, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Menit");
        Timer_tab.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 67, 50, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Detik");
        Timer_tab.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 67, 50, -1));

        btnplaytimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnplaytimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/play.png"))); // NOI18N
        btnplaytimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnplaytimerMouseClicked(evt);
            }
        });
        Timer_tab.add(btnplaytimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        btnpausetimer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnpausetimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/pause.png"))); // NOI18N
        btnpausetimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnpausetimerMouseClicked(evt);
            }
        });
        Timer_tab.add(btnpausetimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 105, -1));

        btnstoptimer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnstoptimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program/icon/stop.png"))); // NOI18N
        btnstoptimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnstoptimerMouseClicked(evt);
            }
        });
        Timer_tab.add(btnstoptimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 105, -1));

        paneljamtimer.setBackground(new java.awt.Color(51, 51, 51));

        jamm.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jamm.setForeground(new java.awt.Color(255, 255, 255));
        jamm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jamm.setText("00");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(":");

        menitt.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        menitt.setForeground(new java.awt.Color(255, 255, 255));
        menitt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menitt.setText("00");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText(":");

        detikk.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        detikk.setForeground(new java.awt.Color(255, 255, 255));
        detikk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detikk.setText("00");

        javax.swing.GroupLayout paneljamtimerLayout = new javax.swing.GroupLayout(paneljamtimer);
        paneljamtimer.setLayout(paneljamtimerLayout);
        paneljamtimerLayout.setHorizontalGroup(
            paneljamtimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneljamtimerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jamm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menitt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detikk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneljamtimerLayout.setVerticalGroup(
            paneljamtimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneljamtimerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneljamtimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneljamtimerLayout.createSequentialGroup()
                        .addGroup(paneljamtimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jamm)
                            .addComponent(detikk))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneljamtimerLayout.createSequentialGroup()
                        .addGroup(paneljamtimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(paneljamtimerLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(paneljamtimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(menitt)
                                    .addComponent(jLabel8))))
                        .addGap(20, 20, 20))))
        );

        Timer_tab.add(paneljamtimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 162, -1, 59));

        jTabbedPane1.addTab("tab4", Timer_tab);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 300, 460));

        jMenuBar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jMenuBar1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar1MouseDragged(evt);
            }
        });
        jMenuBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuBar1MousePressed(evt);
            }
        });

        jMenu1.setText("Menu");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
        
        btnplaysw.setVisible(true);
        btnpausesw.setVisible(false);
        btnstopsw.setVisible(false);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
        
        btnplaysw.setVisible(true);
        btnpausesw.setVisible(false);
        btnstopsw.setVisible(false);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
        
        btnplaytimer.setVisible(true);
        btnpausetimer.setVisible(false);
        btnstoptimer.setVisible(false);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
        
        btnplaytimer.setVisible(true);
        btnpausetimer.setVisible(false);
        btnstoptimer.setVisible(false);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void btnaddalarmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaddalarmMouseClicked
        // TODO add your handling code here:
        try {
        if(status == 0)
        {
            status = 1;
            btnswtich.setVisible(false);
            cmbjam.setEnabled(false);
            cmbmenit.setEnabled(false);
            ImageIcon icon = new ImageIcon("src\\program\\icon\\hapus.png");
            btnaddalarm.setIcon(icon);
            th = new Thread (this);
            th.start();
        }
        else
        {
            status = 0;
            btnswtich.setVisible(true);
            cmbjam.setEnabled(true);
            cmbmenit.setEnabled(true);
            ImageIcon icon = new ImageIcon("src\\program\\icon\\add+.png");
            btnaddalarm.setIcon(icon);
        }
        } catch (UnsupportedOperationException ex) {
        }
    }//GEN-LAST:event_btnaddalarmMouseClicked

    private void btnplayswMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnplayswMouseClicked
        // TODO add your handling code here:
        btnplaysw.setVisible(false);
        btnpausesw.setVisible(true);
        btnstopsw.setVisible(true);
        
        ImageIcon icon = new ImageIcon("src\\program\\icon\\pause.png");
        btnpausesw.setIcon(icon);
        
        state = true;
        
        Thread t = new Thread()
        {
            public void run()
            {
                for(;;)
                {
                    if(state == true)
                    {
                        try {
                            sleep(1);
                            
                            if(milidetik>450)
                            {
                                milidetik = 0;
                                detik++;
                            }
                            if(detik>60)
                            {
                                milidetik = 0;
                                detik = 0;
                                menit++;
                            }
                            if(menit>60)
                            {
                                milidetik = 0;
                                detik = 0;
                                menit = 0;
                                jam++;
                            }
                            
                            milidetik++;
                            
                            lblstopwatch.setText(String.format("%02d:%02d:%02d.%03d", jam, menit, detik, milidetik));
                        } catch (Exception e) {
                        }
                    }
                }
            }
        };
    t.start();
    }//GEN-LAST:event_btnplayswMouseClicked

    private void btnstopswMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnstopswMouseClicked
        // TODO add your handling code here:
        btnplaysw.setVisible(true);
        btnpausesw.setVisible(false);
        btnstopsw.setVisible(false);
        
        state = false;
        
        jam = 0;
        menit = 0;
        detik = 0;
        milidetik = 0;
        
        lblstopwatch.setText("00:00:00.00");
    }//GEN-LAST:event_btnstopswMouseClicked

    private void btnpauseswMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpauseswMouseClicked
        // TODO add your handling code here:
        if(state == false)
        {
            ImageIcon icon = new ImageIcon("src\\program\\icon\\pause.png");
            btnpausesw.setIcon(icon);
            
            state = true;
        
        Thread t = new Thread()
        {
            public void run()
            {
                for(;;)
                {
                    if(state == true)
                    {
                        try {
                            sleep(1);
                            
                            if(milidetik>450)
                            {
                                milidetik = 0;
                                detik++;
                            }
                            if(detik>60)
                            {
                                milidetik = 0;
                                detik = 0;
                                menit++;
                            }
                            if(menit>60)
                            {
                                milidetik = 0;
                                detik = 0;
                                menit = 0;
                                jam++;
                            }
                            
                            milidetik++;
                            
                            lblstopwatch.setText(String.format("%02d:%02d:%02d.%03d", jam, menit, detik, milidetik));
                        } catch (Exception e) {
                        }
                    }
                }
            }
        };
    t.start();
        }
        else
        {
            ImageIcon icon = new ImageIcon("src\\program\\icon\\play.png");
            btnpausesw.setIcon(icon);
            state = false;
        }
    }//GEN-LAST:event_btnpauseswMouseClicked

    private void btnplaytimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnplaytimerMouseClicked
        // TODO add your handling code here:
        btnplaytimer.setVisible(false);
        btnpausetimer.setVisible(true);
        btnstoptimer.setVisible(true);
        
            ImageIcon icon = new ImageIcon("src\\program\\icon\\pause.png");
            btnpausetimer.setIcon(icon);
            
            timerpause = false;
            
        if(!timerstart){
            timerstart = true;
            timer = new Timer();
            TimerTask task = new TimerTask() {
            int jam = Integer.parseInt(txtjam.getText());
            int menit = Integer.parseInt(txtmenit.getText());
            int detik = Integer.parseInt(txtdetik.getText());
          
            int total = jam * 3600 + menit * 60 + detik;
            @Override
            public void run() {
                total--;
                if(total>0)
                {
                  int j = total/3600;
                  int m = total/60;
                  int d = total%60;
                  jamm.setText(String.format("%02d", j));
                  menitt.setText(String.format("%02d", m));
                  detikk.setText(String.format("%02d", d));
                  txtjam.setEnabled(false);
                  txtmenit.setEnabled(false);
                  txtdetik.setEnabled(false);
                }
                if(total==0)
                {
                  String audio = "src\\program\\sound\\sound.wav";
                  playmusic(audio);
                  txtjam.setEnabled(true);
                  txtmenit.setEnabled(true);
                  txtdetik.setEnabled(true);
                  detikk.setText(String.format("00"));
                  timer.cancel();
                  timerstart = false;
                  
                  btnplaytimer.setVisible(true);
                  btnpausetimer.setVisible(false);
                  btnstoptimer.setVisible(false);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
        }
    }//GEN-LAST:event_btnplaytimerMouseClicked

    private void btnpausetimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpausetimerMouseClicked
        // TODO add your handling code here:
        if(timerpause)
        {
            ImageIcon icon = new ImageIcon("src\\program\\icon\\pause.png");
            btnpausetimer.setIcon(icon);
            
            timerpause = false;
            timer = new Timer();
            TimerTask task = new TimerTask() 
            {
             int jam = Integer.parseInt(jamm.getText());
             int menit = Integer.parseInt(menitt.getText());
             int detik = Integer.parseInt(detikk.getText());
          
             int total = jam * 3600 + menit * 60 + detik;
            @Override
            public void run() 
            {
                if(!timerpause)
                {
                total--;
                if(total>=0)
                {
                  int j = total/3600;
                  int m = total/60;
                  int d = total%60;
                  //lbltimer.setText(String.format("%02d:%02d:%02d", j, m, d));
                  jamm.setText(String.format("%02d", j));
                  menitt.setText(String.format("%02d", m));
                  detikk.setText(String.format("%02d", d));
                  txtjam.setEnabled(false);
                  txtmenit.setEnabled(false);
                  txtdetik.setEnabled(false);
                }
                if(total==0)
                {
                  String audio = "src\\program\\sound\\sound.wav";
                  playmusic(audio);
                  txtjam.setEnabled(true);
                  txtmenit.setEnabled(true);
                  txtdetik.setEnabled(true);
                  detikk.setText(String.format("00"));
                  timer.cancel();
                  timerstart = false;
                  
                  btnplaytimer.setVisible(true);
                  btnpausetimer.setVisible(false);
                  btnstoptimer.setVisible(false);
                }
                }
            }
            };
            timer.scheduleAtFixedRate(task, 0, 1000);
        }
        else
        {
            ImageIcon icon = new ImageIcon("src\\program\\icon\\play.png");
            btnpausetimer.setIcon(icon);
            
            timerpause = true;
            timer.cancel();
        }
    }//GEN-LAST:event_btnpausetimerMouseClicked

    private void btnstoptimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnstoptimerMouseClicked
        // TODO add your handling code here:
        btnplaytimer.setVisible(true);
        btnpausetimer.setVisible(false);
        btnstoptimer.setVisible(false);
        
        timer.cancel();
        timerstart = false;
        jamm.setText("00");
        menitt.setText("00");
        detikk.setText("00");
        
        txtjam.setEnabled(true);
        txtmenit.setEnabled(true);
        txtdetik.setEnabled(true);
    }//GEN-LAST:event_btnstoptimerMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuBar1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - yy);
    }//GEN-LAST:event_jMenuBar1MouseDragged

    private void jMenuBar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_jMenuBar1MousePressed

    private void btnswtichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnswtichMouseClicked
        // TODO add your handling code here:
        if(btnswtich.getText().equals("ON"))
        {
            btnswtich.setText("OFF");
        }
        else
        {
            btnswtich.setText("ON");
        }
    }//GEN-LAST:event_btnswtichMouseClicked

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
            java.util.logging.Logger.getLogger(JamApps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JamApps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JamApps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JamApps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JamApps().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Alarm_tab;
    private javax.swing.JPanel Jam_tab;
    private javax.swing.JPanel Stopwatch_tab;
    private javax.swing.JPanel Timer_tab;
    private javax.swing.JLabel alarmaktif;
    private javax.swing.JPanel background;
    private javax.swing.JPanel bottom;
    private javax.swing.JLabel btnaddalarm;
    private javax.swing.JLabel btnpausesw;
    private javax.swing.JLabel btnpausetimer;
    private javax.swing.JLabel btnplaysw;
    private javax.swing.JLabel btnplaytimer;
    private javax.swing.JLabel btnstopsw;
    private javax.swing.JLabel btnstoptimer;
    private javax.swing.JButton btnswtich;
    private javax.swing.JComboBox<String> cmbjam;
    private javax.swing.JComboBox<String> cmbmenit;
    private javax.swing.JLabel detikk;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jamm;
    private javax.swing.JLabel lblhari;
    private javax.swing.JLabel lbljam;
    private javax.swing.JLabel lblstopwatch;
    private javax.swing.JLabel lbltanggal;
    private javax.swing.JLabel menitt;
    private javax.swing.JPanel paneljamtimer;
    private javax.swing.JTextField txtdetik;
    private javax.swing.JTextField txtjam;
    private javax.swing.JTextField txtmenit;
    private javax.swing.JLabel waktualarm;
    // End of variables declaration//GEN-END:variables
}
