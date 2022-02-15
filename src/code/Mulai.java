package code;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.JOptionPane;
import java.awt.event.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ACER
 */
public class Mulai extends javax.swing.JFrame implements Runnable {

    Thread t;
    boolean kanan = true;
    boolean kiri = false;
    boolean berjalan = true;
    int x, y, a, b, c, d, e, f, g, h, i, j, k, l;
    
    static String hasil1 = "";
    static String hasil2 = "";
    static String kesimpulann = "";
    static String string1 = "";
    static String string2 = "";
    
    public void setString1(String kata){
        string1 = kata;
    }
    
    public String getString1(){
        return string1;
    }
    public void setString2(String kata){
        string2 = kata;
    }
    
    public String getString2(){
        return string2;
    }
    public void setHasil1(String hasil){
        hasil1 = hasil;
    }
    
    public String getHasil1(){
        return hasil1;
    }
    
    public void setHasil2(String hasil){
        hasil2 = hasil;
    }
    
    public String getHasil2(){
        return hasil2;
    }
    
    public void setKesimpulan(String kesimpulan){
        kesimpulann = kesimpulan;
    }
    
    public String getKesimpulan(){
        return kesimpulann;
    }
    /**
     * Creates new form Home
     */
    public Mulai() {
        super("Aplikasi Soundex");
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        
        x = jLabel2.getX();
        y = jLabel2.getY();
        a = jLabel3.getX();
        b = jLabel3.getY();
        c = jLabel4.getX();
        d = jLabel4.getY();
        e = jLabel5.getX();
        f = jLabel5.getY();
        g = jLabel6.getX();
        h = jLabel6.getY();
        i = jLabel8.getX();
        j = jLabel8.getY();
        k = jLabel9.getX();
        l = jLabel9.getY();
        t = new Thread(this);
        t.start();
        
        if(jTFString1.getText().equals("") && jTFString2.getText().equals("")){
            setKosong();
        }
        jBPeriksa.addActionListener((java.awt.event.ActionEvent evt) -> {
            if(jTFString1.getText().equals("") || jTFString2.getText().equals("")){
                if(jTFString1.getText().equals("") && jTFString2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "String 1 dan String 2 belum ada masukan!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(jTFString1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "String 1 belum ada masukan!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "String 2 belum ada masukan!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                setKosong();
            }
            else{
                Mulai mulai = new Mulai();
                
                String[] hasil1 = soundexx(jTFString1.getText());
                String[] huruf1 = soundexxx(jTFString1.getText());
                String[] hasil2 = soundexx(jTFString2.getText());
                String[] huruf2 = soundexxx(jTFString2.getText());
                String soundex1 = soundex(jTFString1.getText());
                String soundex2 = soundex(jTFString2.getText());
                String tampung1 = "";
                String tampung2 = "";
                for (int i = 0; i<huruf1.length ;i++ ) {
                    if (huruf1[i] == null) {
                            i += huruf1.length;
                    }
                    else{
                            tampung1 += huruf1[i] + " = " + hasil1[i] + "\n";
                    }
                }
                for (int i = 0; i<huruf2.length ;i++ ) {
                    if (huruf2[i] == null) {
                            i += huruf2.length;
                    }
                    else{
                            tampung2 += huruf2[i] + " = " + hasil2[i] + "\n";
                    }
                }
                
                mulai.setHasil1(tampung1 + "\nHasil menjadi: " + soundex1);
                mulai.setHasil2(tampung2 + "\nHasil menjadi: " + soundex2);
                
                if(soundex1.equals(soundex2)){
                    mulai.setKesimpulan("Kode soundex sama dikarenakan string 1 dan string 2\nmemiliki kedekatan/kemiripan kata.");
                }else{
                    mulai.setKesimpulan("Kode soundex berbeda dikarenakan string 1 dan string 2\ntidak memiliki kedekatan/kemiripan kata.");
                }
                mulai.setString1(jTFString1.getText());
                mulai.setString2(jTFString2.getText());
                Hasil hasilnya = new Hasil();
                hasilnya.setVisible(true);
                this.dispose();
            }
    
        }); 
    }

    public void setKosong(){
        jTFString1.setText("");
        jTFString2.setText("");
    }
        
        private static String getCode(char c){
	  switch(c){
	    case 'B': case 'F': case 'P': case 'V':
	      return "1";
	    case 'C': case 'G': case 'J': case 'K':
	    case 'Q': case 'S': case 'X': case 'Z':
	      return "2";
	    case 'D': case 'T':
	      return "3";
	    case 'L':
	      return "4";
	    case 'M': case 'N':
	      return "5";
	    case 'R':
	      return "6";
	    default:
	      return "";
	  }
	}
	 
	public static String soundex(String s){
	  String code, previous, soundex;
	  code = s.toUpperCase().charAt(0) + "";
	  previous = "7";
	  for(int i = 1;i < s.length();i++){
	    String current = getCode(s.toUpperCase().charAt(i));
	    if(current.length() > 0 && !current.equals(previous)){
	      code = code + current;
	    }
	    previous = current;
	  }
	  soundex = (code + "0000").substring(0, 4);
	  return soundex;
	}

	public static String[] soundexx(String s){
	  String code, previous, soundex;
	  String[] proses = new String[4];
	  proses[0] = s.toUpperCase().charAt(0) + "";
	  previous = "7";
	  int a = 1;
	  for(int i = 1;i < s.length();i++){
	    String current = getCode(s.toUpperCase().charAt(i));
	    if(current.length() > 0 && !current.equals(previous)){
	    	proses[a] = current;
	    	a++;
	    }
	    if(a == proses.length) {
	    	break;
	    }
	    previous = current;
	  }
	  return proses;
	}

	public static String[] soundexxx(String s){
	  String code, previous, soundex;
	  String[] proses = new String[4];
	  proses[0] = s.toUpperCase().charAt(0) + "";
	  previous = "7";
	  int a = 1;
	  for(int i = 1;i < s.length();i++){
	    String current = getCode(s.toUpperCase().charAt(i));
	    if(current.length() > 0 && !current.equals(previous)){
	    	proses[a] = s.toUpperCase().charAt(i) + "";
	    	a++;
	    }
	    if(a == proses.length) {
	    	break;
	    }
	    previous = current;
	  }
	  return proses;
	}
                
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTFString1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFString2 = new javax.swing.JTextField();
        jBPeriksa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(484, 471));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(484, 471));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Muhammad Rohfi");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        jLabel4.setText("Naufal Syarif");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel9.setText("|");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, -1, 30));

        jLabel3.setText("Kelompok Menuju Sukses");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel8.setText("|");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, -1, 30));

        jLabel5.setText("Muhammad Rizky.R");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, -1, 10));

        jLabel6.setText("Osa Nurul Aditya");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 440, -1, 10));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("String 1");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        jTFString1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFString1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFString1KeyTyped(evt);
            }
        });
        jPanel1.add(jTFString1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 170, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("String 2");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, -1, -1));

        jTFString2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFString2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFString2KeyTyped(evt);
            }
        });
        jPanel1.add(jTFString2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 171, 40));

        jBPeriksa.setBackground(new java.awt.Color(255, 255, 255));
        jBPeriksa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jBPeriksa.setText("Periksa");
        jPanel1.add(jBPeriksa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 90, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pict/back.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 90));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pict/informasi.png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 7, -1, 70));

        jLabel13.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 20)); // NOI18N
        jLabel13.setText("INPUT KATA");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, 50));

        jLabel14.setText("Input kata yang ingin anda bandingkan,");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        jLabel15.setText("setelah anda klik tombol periksa maka akan menemukan hasil Soundex ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jLabel16.setText("dan terdapat kesimpulan dari perbandingan kata yang anda input");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pict/abstract.jpg"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 490, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Kode Soundex adalah kode yang terdiri dari\nkombinasi 1 huruf dan 3 angka,"
                + " nol (0) akan ditambahkan\ndi akhir angka jika diperlukan untuk menghasilkan 4 karakter kode,\napabila kode sudah mencapai 4 karakter yaitu 1 huruf dan\n3 angka maka huruf setelahnya diabaikan."
                + "", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jTFString1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFString1KeyTyped
        // TODO add your handling code here:
        char input = evt.getKeyChar(), backspace = KeyEvent.VK_BACK_SPACE;
        Object object = evt;
        InputEvent evento = (InputEvent) object;
        
        /*
         * User tidak dapat memasukkan selain karakter huruf
         */
        if (input >= 'a' && input <= 'z' || input >= 'A' && input <= 'Z' || input == backspace)
        {
            jTFString1.setEditable(true);
        }
        else
        {
            {
                JOptionPane.showMessageDialog(
                       this, 
                       "Input String 1 harus huruf!", 
                       "Warning", 
                       JOptionPane.ERROR_MESSAGE);
                evento.consume();
            }
        }      
    }//GEN-LAST:event_jTFString1KeyTyped

    private void jTFString2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFString2KeyTyped
        // TODO add your handling code here:
        char input = evt.getKeyChar(), backspace = KeyEvent.VK_BACK_SPACE;
        Object object = evt;
        InputEvent evento = (InputEvent) object;
        
        /*
         * User tidak dapat memasukkan selain karakter huruf
         */
        if (input >= 'a' && input <= 'z' || input >= 'A' && input <= 'Z' || input == backspace)
        {
            jTFString2.setEditable(true);
        }
        else
        {
            {
                JOptionPane.showMessageDialog(
                       this, 
                       "Input String 2 harus huruf!", 
                       "Warning", 
                       JOptionPane.ERROR_MESSAGE);
                evento.consume();
            }
        } 
    }//GEN-LAST:event_jTFString2KeyTyped

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBPeriksa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFString1;
    private javax.swing.JTextField jTFString2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
       while(true){
            if(berjalan){
                if(a >= jPanel1.getWidth()-10){
                    kanan = false;
                    kiri = true;
                }
                if(kiri){
                    x--;
                    a--;
                    c--;
                    e--;
                    g--;
                    i--;
                    k--;
                    jLabel2.setLocation(x,y);
                    jLabel3.setLocation(a,b);
                    jLabel4.setLocation(c,d);
                    jLabel5.setLocation(e,f);
                    jLabel6.setLocation(g,h);
                    jLabel8.setLocation(i,j);
                    jLabel9.setLocation(k,l);
                }
                if(a <= 5){
                    kanan = true;
                    kiri = false;
                }
                if(kanan){
                    x++;
                    a++;
                    c++;
                    e++;
                    g++;
                    i++;
                    k++;
                    jLabel2.setLocation(x,y);
                    jLabel3.setLocation(a,b);
                    jLabel4.setLocation(c,d);
                    jLabel5.setLocation(e,f);
                    jLabel6.setLocation(g,h);
                    jLabel8.setLocation(i,j);
                    jLabel9.setLocation(k,l);
                }
                try{
                    Thread.sleep(10);
                }catch(InterruptedException ex){
                    JOptionPane.showMessageDialog(null, ex, "Warning", JOptionPane.ERROR_MESSAGE);
                }
                repaint();
            }
        }
    }
}
