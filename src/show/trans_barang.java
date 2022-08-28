/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package show;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import koneksi.koneksi;
import java.util.Date;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author nopal
 */

public class trans_barang extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
String Status="";
SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
Date date = Calendar.getInstance().getTime();  
String strdate = dateformat.format(date);  

SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm");
Date time = Calendar.getInstance().getTime();  
String strtime = timeformat.format(time);  

    /**
     * Creates new form trans_barang
     */
    public trans_barang() {
        initComponents();
        aktif();
        combo();
        combo4();
        combo5();
        combo6();
    }
    
    protected void aktif(){
        txt_kode.requestFocus();
        b_edit.setVisible(false);
        r_jual.setEnabled(false);
        r_rusak.setEnabled(false);
        r_Musnah.setEnabled(false);
        r_service.setEnabled(false);
        r_available.setSelected(true);
    };
    
    public void combo(){ 
        cb_kategori.removeAllItems();
        cb_kategori.addItem(" - Pilih Kategori - ");     
        try {            
            String sql = "select nama from kategori";           
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql); 
            while (r.next()) {
                cb_kategori.addItem(r.getString(1));
            }
        } catch (Exception e) {
        }         
    }
    
    public void combo2(){
        String a = cb_kategori.getSelectedItem().toString();
        cb_merk.removeAllItems();
        cb_merk.addItem(" - Pilih Brands/Merk - ");  
        String asql="select kode from kategori where nama='"+a+"'";  
        try { 
            String b="";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){b=rs.getString("kode");}
            
            String sql = "select nama from merk where kode_kategori='"+b+"'";           
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql); 
            while (r.next()) {
                cb_merk.addItem(r.getString(1));
            }
        } catch (Exception e) {
        }         
    }
    
    public void combo3(){
        String a = cb_merk.getSelectedItem().toString();
        cb_tipe.removeAllItems();
        cb_tipe.addItem(" - Pilih Tipe Barang - ");  
        String asql="select kode from merk where nama='"+a+"'";  
        try { 
            String b="";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){b=rs.getString("kode");}
            
            String sql = "select nama from barang where kode_merk='"+b+"'";           
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql); 
            while (r.next()) {
                cb_tipe.addItem(r.getString(1));
            }
        } catch (Exception e) {
        }         
    }
    public void combotest(){
        String a = cb_merk.getSelectedItem().toString();
        cb_tipe.removeAllItems();
        String c= cb_tipe.getSelectedItem().toString();
        String asql="select kode from merk where nama='"+a+"'";  
        try { 
            String b="";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){b=rs.getString("kode");}
            
            String sql = "select nama from barang where kode_merk='"+b+"' and nama='%"+c+"%'";           
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql); 
            while (r.next()) {
                cb_tipe.addItem(r.getString(1));
            }
        } catch (Exception e) {
        }         
    }
    
    public void combo4(){
        cb_vendor.removeAllItems();
        cb_vendor.addItem(" - Pilih Vendor - ");     
        try {            
            String sql = "select nama from vendor";           
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql); 
            while (r.next()) {
                cb_vendor.addItem(r.getString(1));
            }
        } catch (Exception e) {
        }         
    }
    
    public void combo5(){
        cb_perusahaan.removeAllItems();
        cb_perusahaan.addItem(" - Pilih Perusahaan - ");     
        try {            
            String sql = "select nama from perusahaan";           
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql); 
            while (r.next()) {
                cb_perusahaan.addItem(r.getString(1));
            }
        } catch (Exception e) {
        }         
    }
    
    public void combo6(){
        cb_lokasi.removeAllItems();
        cb_lokasi.addItem(" - Pilih Lokasi - ");     
        try {            
            String sql = "select nama from lokasi";           
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql); 
            while (r.next()) {
                cb_lokasi.addItem(r.getString(1));
            }
        } catch (Exception e) {
        }         
    }
    
    public void combo7(){
        String a = cb_perusahaan.getSelectedItem().toString();
        cb_department.removeAllItems();
        cb_department.addItem(" - Pilih Department - ");  
        String asql="select kode from perusahaan where nama='"+a+"'";  
        try { 
            String b="";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){b=rs.getString("kode");}
            
            String sql = "select nama from department where kode_perusahaan='"+b+"'";           
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql); 
            while (r.next()) {
                cb_department.addItem(r.getString(1));
            }
        } catch (Exception e) {
        }         
    }
    
    protected void radio(){
        if (r_available.isSelected()){
            Status = "Available";
        }
        else if (r_service.isSelected()){
            Status = "Di Service";
        }
        else if (r_rusak.isSelected()){
            Status = "Rusak";
        }
        else if (r_Musnah.isSelected()){
            Status = "Di Musnahkan";
        }
        else if (r_jual.isSelected()){
            Status = "Di Jual";
        }
    }
    
    public void getDataid(String id){
        b_simpan.setVisible(false);
        b_edit.setVisible(true);
        b_edit.setVisible(true);
        r_jual.setEnabled(true);
        r_rusak.setEnabled(true);
        r_Musnah.setEnabled(true);
        r_service.setEnabled(true);
        r_available.setSelected(true);
        txt_kode.setEditable(false);
        jLabel16.setText("                                                 Ubah Data Barang");        
        txt_kode.setText(home.id_trans_barang);
        String a = txt_kode.getText();
        try{
            String sql="select trans_barang.kode, trans_barang.keterangan, trans_barang.status,trans_barang.harga,trans_barang.no_po,barang.nama,department.nama,kategori.nama,lokasi.nama, merk.nama,perusahaan.nama,vendor.nama "
                    + "from trans_barang inner join barang on trans_barang.kode_barang=barang.kode "
                    + "join department on trans_barang.kode_dept=department.kode "
                    + "join kategori on trans_barang.kode_kategori=kategori.kode "
                    + "join lokasi on trans_barang.kode_lokasi=lokasi.kode "
                    + "join merk on trans_barang.kode_merk=merk.kode "
                    + "join perusahaan on trans_barang.kode_perusahaan=perusahaan.kode "
                    + "join vendor on trans_barang.kode_vendor=vendor.kode "
                    + "where trans_barang.kode='"+a+"'";
            
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql); 
            
            while (rs.next()){
             txt_kode.setText(rs.getString("trans_barang.kode"));
             cb_kategori.addItem(rs.getString("kategori.nama"));cb_kategori.setSelectedItem(rs.getString("kategori.nama")); 
             cb_merk.addItem(rs.getString("merk.nama"));cb_merk.setSelectedItem(rs.getString("merk.nama")); 
             cb_tipe.addItem(rs.getString("barang.nama"));cb_tipe.setSelectedItem(rs.getString("barang.nama")); 
             cb_perusahaan.addItem(rs.getString("perusahaan.nama"));cb_perusahaan.setSelectedItem(rs.getString("perusahaan.nama")); 
             cb_department.addItem(rs.getString("department.nama"));cb_department.setSelectedItem(rs.getString("department.nama")); 
             cb_lokasi.addItem(rs.getString("lokasi.nama"));cb_lokasi.setSelectedItem(rs.getString("lokasi.nama")); 
             cb_vendor.addItem(rs.getString("vendor.nama"));cb_vendor.setSelectedItem(rs.getString("vendor.nama")); 
             txt_po.setText(rs.getString("trans_barang.no_po"));
             txt_harga.setText(rs.getString("trans_barang.harga"));       
             String d = rs.getString("trans_barang.status");
             
             if (d.equals("Available")){r_available.setSelected(true);}
             else if (d.equals("Di Service")){r_service.setSelected(true);}
             else if (d.equals("Di Musnahkan")){r_Musnah.setSelected(true);}
             else if (d.equals("Di Jual")){r_jual.setSelected(true);}
             else if (d.equals("Di Mutasi")){r_available.setSelected(true);}
             else if (d.equals("Rusak")){r_rusak.setSelected(true);}
             
             if (r_Musnah.isSelected() || r_jual.isSelected()){
             r_service.setEnabled(false);
             r_available.setEnabled(false);
             r_Musnah.setEnabled(false);
             r_jual.setEnabled(false);
             r_rusak.setEnabled(false);
             b_edit.setVisible(false);
                }
             else {
            r_service.setEnabled(true);
            r_available.setEnabled(true);
                }
             
             txt_keterangan.setText(rs.getString("trans_barang.keterangan"));
                      }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,"data Barang gagal dipanggil : "+ e);
            }
    }
    
    protected void kosong(){
        txt_kode.setText("");
        txt_po.setText("");
        txt_harga.setText("");
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_a = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_kode = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cb_merk = new javax.swing.JComboBox<>();
        cb_tipe = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cb_kategori = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_keterangan = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        b_edit = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        b_simpan = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cb_lokasi = new javax.swing.JComboBox<>();
        cb_department = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        cb_perusahaan = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cb_vendor = new javax.swing.JComboBox<>();
        txt_harga = new javax.swing.JTextField();
        txt_po = new javax.swing.JTextField();
        p_status = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        r_available = new javax.swing.JRadioButton();
        r_service = new javax.swing.JRadioButton();
        r_jual = new javax.swing.JRadioButton();
        r_Musnah = new javax.swing.JRadioButton();
        r_rusak = new javax.swing.JRadioButton();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(254, 255, 230));

        jPanel2.setBackground(new java.awt.Color(9, 10, 54));

        jLabel16.setBackground(new java.awt.Color(204, 204, 204));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(231, 238, 126));
        jLabel16.setText("                                              Tambah Data Barang");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(251, 255, 161));

        txt_kode.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_kode.setForeground(new java.awt.Color(9, 10, 54));
        txt_kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(204, 204, 204));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(9, 10, 54));
        jLabel10.setText("       Kode Assets");

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(9, 10, 54));
        jLabel11.setText("       Merk/ Brands");

        cb_merk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_merk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_merkMouseClicked(evt);
            }
        });

        cb_tipe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_tipe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_tipeMouseClicked(evt);
            }
        });
        cb_tipe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cb_tipeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cb_tipeKeyTyped(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(204, 204, 204));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(9, 10, 54));
        jLabel21.setText("            Tipe Barang");

        jLabel25.setBackground(new java.awt.Color(204, 204, 204));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(9, 10, 54));
        jLabel25.setText("            Kategori Barang");

        cb_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_kategori.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                cb_kategoriComponentHidden(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(204, 204, 204));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(9, 10, 54));
        jLabel19.setText("      Keterangan");

        txt_keterangan.setColumns(20);
        txt_keterangan.setRows(5);
        jScrollPane1.setViewportView(txt_keterangan);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cb_merk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cb_tipe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cb_kategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_merk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_tipe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(52, 52, 52))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        jPanel4.setBackground(new java.awt.Color(254, 255, 230));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(9, 10, 54));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });

        jLabel24.setBackground(new java.awt.Color(204, 204, 204));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(231, 238, 126));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_back_24px.png"))); // NOI18N
        jLabel24.setText("   Kembali");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        jPanel15.setBackground(new java.awt.Color(162, 34, 39));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(204, 204, 204));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(231, 238, 126));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_trash_can_24px.png"))); // NOI18N
        jLabel23.setText("   Hapus");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 110, -1));

        b_edit.setBackground(new java.awt.Color(9, 10, 54));
        b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_editMouseClicked(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(204, 204, 204));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(231, 238, 126));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        jLabel17.setText("   Ubah");

        javax.swing.GroupLayout b_editLayout = new javax.swing.GroupLayout(b_edit);
        b_edit.setLayout(b_editLayout);
        b_editLayout.setHorizontalGroup(
            b_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_editLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
        );
        b_editLayout.setVerticalGroup(
            b_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        jPanel4.add(b_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, -1, -1));

        b_simpan.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpanMouseClicked(evt);
            }
        });

        jLabel22.setBackground(new java.awt.Color(204, 204, 204));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(231, 238, 126));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_24px.png"))); // NOI18N
        jLabel22.setText("   Simpan");

        javax.swing.GroupLayout b_simpanLayout = new javax.swing.GroupLayout(b_simpan);
        b_simpan.setLayout(b_simpanLayout);
        b_simpanLayout.setHorizontalGroup(
            b_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_simpanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        b_simpanLayout.setVerticalGroup(
            b_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        jPanel4.add(b_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 110, -1));

        jPanel5.setBackground(new java.awt.Color(251, 255, 161));

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(9, 10, 54));
        jLabel12.setText("       Perusahaan");

        jLabel13.setBackground(new java.awt.Color(204, 204, 204));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(9, 10, 54));
        jLabel13.setText("       Lokasi");

        cb_lokasi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cb_department.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_department.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_departmentMouseClicked(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(204, 204, 204));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(9, 10, 54));
        jLabel26.setText("            Department");

        cb_perusahaan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cb_lokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cb_department, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cb_perusahaan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_lokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_department, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        jPanel6.setBackground(new java.awt.Color(251, 255, 161));

        jLabel14.setBackground(new java.awt.Color(204, 204, 204));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(9, 10, 54));
        jLabel14.setText("       Nama Vendor");

        jLabel15.setBackground(new java.awt.Color(204, 204, 204));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(9, 10, 54));
        jLabel15.setText("       NO. PO");

        jLabel27.setBackground(new java.awt.Color(204, 204, 204));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(9, 10, 54));
        jLabel27.setText("              Harga");

        cb_vendor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txt_harga.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_harga.setForeground(new java.awt.Color(9, 10, 54));
        txt_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargaActionPerformed(evt);
            }
        });

        txt_po.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_po.setForeground(new java.awt.Color(9, 10, 54));
        txt_po.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_poActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_po, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cb_vendor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_vendor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_po, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        p_status.setBackground(new java.awt.Color(251, 255, 161));

        jLabel18.setBackground(new java.awt.Color(204, 204, 204));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(9, 10, 54));
        jLabel18.setText("       Status Barang");

        jPanel8.setBackground(new java.awt.Color(251, 255, 161));

        r_available.setBackground(new java.awt.Color(251, 255, 161));
        button_a.add(r_available);
        r_available.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        r_available.setForeground(new java.awt.Color(9, 10, 54));
        r_available.setText("Available");
        r_available.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_availableActionPerformed(evt);
            }
        });

        r_service.setBackground(new java.awt.Color(251, 255, 161));
        button_a.add(r_service);
        r_service.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        r_service.setForeground(new java.awt.Color(9, 10, 54));
        r_service.setText("Di Service");
        r_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_serviceActionPerformed(evt);
            }
        });

        r_jual.setBackground(new java.awt.Color(251, 255, 161));
        button_a.add(r_jual);
        r_jual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        r_jual.setForeground(new java.awt.Color(9, 10, 54));
        r_jual.setText("Di Jual");

        r_Musnah.setBackground(new java.awt.Color(251, 255, 161));
        button_a.add(r_Musnah);
        r_Musnah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        r_Musnah.setForeground(new java.awt.Color(9, 10, 54));
        r_Musnah.setText("Di Musnahkan");

        r_rusak.setBackground(new java.awt.Color(251, 255, 161));
        button_a.add(r_rusak);
        r_rusak.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        r_rusak.setForeground(new java.awt.Color(9, 10, 54));
        r_rusak.setText("Rusak");
        r_rusak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_rusakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(r_available)
                .addGap(18, 18, 18)
                .addComponent(r_service)
                .addGap(18, 18, 18)
                .addComponent(r_rusak)
                .addGap(18, 18, 18)
                .addComponent(r_jual)
                .addGap(18, 18, 18)
                .addComponent(r_Musnah)
                .addContainerGap(143, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(r_available)
                    .addComponent(r_jual)
                    .addComponent(r_service)
                    .addComponent(r_Musnah)
                    .addComponent(r_rusak))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_statusLayout = new javax.swing.GroupLayout(p_status);
        p_status.setLayout(p_statusLayout);
        p_statusLayout.setHorizontalGroup(
            p_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_statusLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_statusLayout.setVerticalGroup(
            p_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_statusLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(p_statusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(p_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jPanel16MouseClicked

    private void b_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_simpanMouseClicked
        // TODO add your handling code here:
        String sql="Insert into trans_barang values(?,?,?,?,?,?,?,?,?,?,?,?)";
        String sql2="Insert into history_barang values(?,?,?,?,?,?,?)";
        
        String asql="select kode from kategori where nama='"+cb_kategori.getSelectedItem().toString()+"'";
        String bsql="select kode from merk where nama='"+cb_merk.getSelectedItem().toString()+"'";
        String csql="select kode from barang where nama='"+cb_tipe.getSelectedItem().toString()+"'";
        String dsql="select kode from perusahaan where nama='"+cb_perusahaan.getSelectedItem().toString()+"'";
        String esql="select kode from lokasi where nama='"+cb_lokasi.getSelectedItem().toString()+"'";
        String fsql="select kode from department where nama='"+cb_department.getSelectedItem().toString()+"'";
        String gsql="select kode from vendor where nama='"+cb_vendor.getSelectedItem().toString()+"'";
        
        try{
            
            Statement st = conn.createStatement(); 
            
            String a="";            
            ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){a=(rs.getString(1));}
            
            String b="";
            ResultSet brs = st.executeQuery(bsql); 
            while (brs.next()){b=(brs.getString(1));}
            
            String c="";
            ResultSet crs = st.executeQuery(csql); 
            while (crs.next()){c=(crs.getString(1));}
            
            String d="";
            ResultSet drs = st.executeQuery(dsql); 
            while (drs.next()){d=(drs.getString(1));}
            
            String e="";
            ResultSet ers = st.executeQuery(esql); 
            while (ers.next()){e=(ers.getString(1));}
            
            String f="";
            ResultSet frs = st.executeQuery(fsql); 
            while (frs.next()){f=(frs.getString(1));}
            
            String g="";
            ResultSet grs = st.executeQuery(gsql); 
            while (grs.next()){g=(grs.getString(1));}
            
            long harga = Long.parseLong(txt_harga.getText());
            
            PreparedStatement stat=conn.prepareStatement(sql);
            stat.setString(1,txt_kode.getText());
            stat.setString(2,a);
            stat.setString(3,b);
            stat.setString(4,c);
            stat.setString(5,d);
            stat.setString(6,e);
            stat.setString(7,f);
            stat.setString(8,g); 
            stat.setString(9,txt_po.getText());
            stat.setLong(10,harga);
            stat.setString(11,"Available");
            stat.setString(12,txt_keterangan.getText());
            stat.executeUpdate();
            
            PreparedStatement astat=conn.prepareStatement(sql2);
            astat.setString(1,txt_kode.getText());
            astat.setString(2,d);
            astat.setString(3,e);
            astat.setString(4,f);
            astat.setString(5,"Available");
            astat.setString(6,strdate);
            astat.setString(7,strtime);
            astat.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Data barang Berhasil Disimpan");
            kosong();
            txt_kode.requestFocus();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan : "+ e);
        }
    }//GEN-LAST:event_b_simpanMouseClicked

    private void b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_editMouseClicked
        // TODO add your handling code here:
        String k=txt_kode.getText();
        txt_kode.setEditable(false);        
        String sql="update trans_barang set kode_kategori=?, kode_merk=?, kode_barang=?, kode_perusahaan=?, "
                + "kode_lokasi=?, kode_dept=?, kode_vendor=?, no_po=?, harga=?, status=?, keterangan=? "
                + "where kode='"+k+"'";
        String sql2="Insert into history_barang values(?,?,?,?,?,?,?)";
        
        String asql="select kode from kategori where nama='"+cb_kategori.getSelectedItem().toString()+"'";
        String bsql="select kode from merk where nama='"+cb_merk.getSelectedItem().toString()+"'";
        String csql="select kode from barang where nama='"+cb_tipe.getSelectedItem().toString()+"'";
        String dsql="select kode from perusahaan where nama='"+cb_perusahaan.getSelectedItem().toString()+"'";
        String esql="select kode from lokasi where nama='"+cb_lokasi.getSelectedItem().toString()+"'";
        String fsql="select kode from department where nama='"+cb_department.getSelectedItem().toString()+"'";
        String gsql="select kode from vendor where nama='"+cb_vendor.getSelectedItem().toString()+"'";    
        
        try{
            Statement st = conn.createStatement(); 
            
            String a="";            
            ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){a=(rs.getString(1));}
            
            String b="";
            ResultSet brs = st.executeQuery(bsql); 
            while (brs.next()){b=(brs.getString(1));}
            
            String c="";
            ResultSet crs = st.executeQuery(csql); 
            while (crs.next()){c=(crs.getString(1));}
            
            String d="";
            ResultSet drs = st.executeQuery(dsql); 
            while (drs.next()){d=(drs.getString(1));}
            
            String e="";
            ResultSet ers = st.executeQuery(esql); 
            while (ers.next()){e=(ers.getString(1));}
            
            String f="";
            ResultSet frs = st.executeQuery(fsql); 
            while (frs.next()){f=(frs.getString(1));}
            
            String g="";
            ResultSet grs = st.executeQuery(gsql); 
            while (grs.next()){g=(grs.getString(1));}
            
            long harga = Long.parseLong(txt_harga.getText());
            
            radio();
            
            PreparedStatement stat=conn.prepareStatement(sql); 
            stat.setString(1,a);
            stat.setString(2,b);
            stat.setString(3,c);
            stat.setString(4,d);
            stat.setString(5,e);
            stat.setString(6,f);
            stat.setString(7,g);
            stat.setString(8,txt_po.getText());
            stat.setLong(9,harga);
            stat.setString(10,Status);
            stat.setString(11,txt_keterangan.getText());
            stat.executeUpdate();
            
            PreparedStatement astat=conn.prepareStatement(sql2);
            astat.setString(1,txt_kode.getText());
            astat.setString(2,d);
            astat.setString(3,e);
            astat.setString(4,f);
            astat.setString(5,Status);
            astat.setString(6,strdate);
            astat.setString(7,strtime);
            astat.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Data Barang berhasil diubah");
            kosong();
            this.setVisible(false);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,"data gagal disimpan : "+ e);
        }
    }//GEN-LAST:event_b_editMouseClicked

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        // TODO add your handling code here:
        String a = txt_kode.getText();
        //String b = txt_nama.getText();
        int ok = JOptionPane.showConfirmDialog(null,"Hapus Data "+a+"?","konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from trans_barang where kode='"+a+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data Barang berhasil dihapus");
                kosong();
                this.setVisible(false);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog (null,"data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_jPanel15MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        // mengambil ukuran layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();

        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
    }//GEN-LAST:event_formWindowOpened

    private void txt_kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodeActionPerformed

    private void txt_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargaActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargaActionPerformed

    private void txt_poActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_poActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_poActionPerformed

    private void r_availableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_availableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_availableActionPerformed

    private void cb_merkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_merkMouseClicked
        // TODO add your handling code here:
        combo2();
    }//GEN-LAST:event_cb_merkMouseClicked

    private void cb_tipeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_tipeMouseClicked
        // TODO add your handling code here:
        combo3();
    }//GEN-LAST:event_cb_tipeMouseClicked

    private void cb_departmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_departmentMouseClicked
        // TODO add your handling code here:
        combo7();
    }//GEN-LAST:event_cb_departmentMouseClicked

    private void cb_kategoriComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_cb_kategoriComponentHidden
        // TODO add your handling code here:
        combo2();
    }//GEN-LAST:event_cb_kategoriComponentHidden

    private void cb_tipeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_tipeKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cb_tipeKeyTyped

    private void cb_tipeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_tipeKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cb_tipeKeyPressed

    private void r_serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_serviceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_serviceActionPerformed

    private void r_rusakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_rusakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_rusakActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel b_edit;
    private javax.swing.JPanel b_simpan;
    private javax.swing.ButtonGroup button_a;
    private javax.swing.JComboBox<String> cb_department;
    private javax.swing.JComboBox<String> cb_kategori;
    private javax.swing.JComboBox<String> cb_lokasi;
    private javax.swing.JComboBox<String> cb_merk;
    private javax.swing.JComboBox<String> cb_perusahaan;
    private javax.swing.JComboBox<String> cb_tipe;
    private javax.swing.JComboBox<String> cb_vendor;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel p_status;
    private javax.swing.JRadioButton r_Musnah;
    private javax.swing.JRadioButton r_available;
    private javax.swing.JRadioButton r_jual;
    private javax.swing.JRadioButton r_rusak;
    private javax.swing.JRadioButton r_service;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextArea txt_keterangan;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_po;
    // End of variables declaration//GEN-END:variables
}
