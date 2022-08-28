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
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author nopal
 */ 
public class cetak_qr extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
public String a,b,c,d,e,f,kode;

//public home datab=null;

    /**
     * Creates new form reg_brands
     */
    public cetak_qr() {
        initComponents();
        kosong();
        combo_kategori();
        combo_lokasi();
        combo_perusahaan();
    }
    
    public void cetak_qr(){   
        try{
            String path="./src/report/cetakQR.jasper";
            HashMap parameter = new HashMap();
            parameter.put("kode_barang",kode);
            parameter.put("kode_kategori",a);
            parameter.put("kode_merk",b);
            parameter.put("kode_tipe",c);
            parameter.put("kode_perusahaan",d);
            parameter.put("kode_lokasi",e);
            parameter.put("kode_dept",f);
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);}
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA"+ex);
        }
        
    }

    
    
    public void combo_kategori(){
        cb_kategori.removeAllItems();
        cb_kategori.addItem("Select All");     
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

    public void combo_merk(){
        String a = cb_kategori.getSelectedItem().toString();
        cb_merk.removeAllItems();
        cb_merk.addItem("Select All");  
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
    

    public void combo_tipe(){
        String a = cb_merk.getSelectedItem().toString();
        cb_tipe.removeAllItems();
        cb_tipe.addItem("Select All");
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
    
    
    public void combo_perusahaan(){
        cb_perusahaan.removeAllItems();
        cb_perusahaan.addItem("Select All");     
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
    
    public void combo_lokasi(){
        cb_lokasi.removeAllItems();
        cb_lokasi.addItem("Select All");     
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
    
    public void combo_dept(){
        String a = cb_perusahaan.getSelectedItem().toString();
        cb_dept.removeAllItems();
        cb_dept.addItem("Select All");  
        String asql="select kode from perusahaan where nama='"+a+"'";  
        try { 
            String b="";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){b=rs.getString("kode");}
            
            String sql = "select nama from department where kode_perusahaan='"+b+"'";           
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql); 
            while (r.next()) {
                cb_dept.addItem(r.getString(1));
            }
        } catch (Exception e) {
        }         
    }
      
    
    protected void kosong(){
        txt_kode.setText("Select All");        
        cb_kategori.setSelectedItem("Select All");
        cb_merk.setSelectedItem("Select All");
        cb_perusahaan.setSelectedItem("Select All");
        cb_dept.setSelectedItem("Select All");
        cb_tipe.setSelectedItem("Select All");
       cb_lokasi.setSelectedItem("Select All");
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_kategori1 = new javax.swing.JLabel();
        txt_kode = new javax.swing.JTextField();
        b_simpan1 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        b_edit = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        b_simpan = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txt_kategori2 = new javax.swing.JLabel();
        cb_perusahaan = new javax.swing.JComboBox<>();
        b_simpan2 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txt_kategori3 = new javax.swing.JLabel();
        cb_dept = new javax.swing.JComboBox<>();
        cb_dept1 = new javax.swing.JComboBox<>();
        b_simpan3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txt_kategori4 = new javax.swing.JLabel();
        cb_lokasi = new javax.swing.JComboBox<>();
        b_simpan4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txt_kategori5 = new javax.swing.JLabel();
        cb_kategori = new javax.swing.JComboBox<>();
        b_simpan5 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        txt_kategori6 = new javax.swing.JLabel();
        cb_merk = new javax.swing.JComboBox<>();
        b_simpan6 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txt_kategori7 = new javax.swing.JLabel();
        cb_tipe = new javax.swing.JComboBox<>();
        b_simpan7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        cb_tipe1 = new javax.swing.JComboBox<>();

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
        jLabel16.setText("                 CETAK QR CODE ITEM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(251, 255, 161));

        txt_kategori1.setBackground(new java.awt.Color(204, 204, 204));
        txt_kategori1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_kategori1.setForeground(new java.awt.Color(9, 10, 54));
        txt_kategori1.setText("        Kode Barang");

        txt_kode.setForeground(new java.awt.Color(9, 10, 54));
        txt_kode.setText("titit");

        b_simpan1.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpan1MouseClicked(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(204, 204, 204));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(231, 238, 126));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_qr_code_24px_2.png"))); // NOI18N
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout b_simpan1Layout = new javax.swing.GroupLayout(b_simpan1);
        b_simpan1.setLayout(b_simpan1Layout);
        b_simpan1Layout.setHorizontalGroup(
            b_simpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_simpan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        b_simpan1Layout.setVerticalGroup(
            b_simpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(txt_kategori1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(b_simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(b_simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kategori1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_kode))
                .addContainerGap())
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
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        b_edit.setBackground(new java.awt.Color(9, 10, 54));
        b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_editMouseClicked(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(204, 204, 204));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(231, 238, 126));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_change_24px.png"))); // NOI18N
        jLabel17.setText("   Refresh");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout b_editLayout = new javax.swing.GroupLayout(b_edit);
        b_edit.setLayout(b_editLayout);
        b_editLayout.setHorizontalGroup(
            b_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_editLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
        );
        b_editLayout.setVerticalGroup(
            b_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        jPanel4.add(b_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 120, -1));

        b_simpan.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpanMouseClicked(evt);
            }
        });

        jLabel22.setBackground(new java.awt.Color(204, 204, 204));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(231, 238, 126));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_qr_code_24px_2.png"))); // NOI18N
        jLabel22.setText("Cetak Semua Barang");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout b_simpanLayout = new javax.swing.GroupLayout(b_simpan);
        b_simpan.setLayout(b_simpanLayout);
        b_simpanLayout.setHorizontalGroup(
            b_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_simpanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        b_simpanLayout.setVerticalGroup(
            b_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel4.add(b_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 200, 50));

        jPanel5.setBackground(new java.awt.Color(251, 255, 161));

        txt_kategori2.setBackground(new java.awt.Color(204, 204, 204));
        txt_kategori2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_kategori2.setForeground(new java.awt.Color(9, 10, 54));
        txt_kategori2.setText("        Kode Perusahaan");

        cb_perusahaan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select All" }));
        cb_perusahaan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_perusahaanItemStateChanged(evt);
            }
        });
        cb_perusahaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_perusahaanActionPerformed(evt);
            }
        });

        b_simpan2.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpan2MouseClicked(evt);
            }
        });

        jLabel25.setBackground(new java.awt.Color(204, 204, 204));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(231, 238, 126));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_qr_code_24px_2.png"))); // NOI18N
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout b_simpan2Layout = new javax.swing.GroupLayout(b_simpan2);
        b_simpan2.setLayout(b_simpan2Layout);
        b_simpan2Layout.setHorizontalGroup(
            b_simpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_simpan2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        b_simpan2Layout.setVerticalGroup(
            b_simpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(txt_kategori2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(cb_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(b_simpan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_perusahaan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(b_simpan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(251, 255, 161));

        txt_kategori3.setBackground(new java.awt.Color(204, 204, 204));
        txt_kategori3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_kategori3.setForeground(new java.awt.Color(9, 10, 54));
        txt_kategori3.setText("        Kode Department");

        cb_dept.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select All" }));
        cb_dept.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_deptItemStateChanged(evt);
            }
        });
        cb_dept.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_deptMouseClicked(evt);
            }
        });
        cb_dept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_deptActionPerformed(evt);
            }
        });

        cb_dept1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select All" }));
        cb_dept1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_dept1ItemStateChanged(evt);
            }
        });
        cb_dept1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_dept1MouseClicked(evt);
            }
        });
        cb_dept1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_dept1ActionPerformed(evt);
            }
        });

        b_simpan3.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpan3MouseClicked(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(204, 204, 204));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(231, 238, 126));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_qr_code_24px_2.png"))); // NOI18N
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout b_simpan3Layout = new javax.swing.GroupLayout(b_simpan3);
        b_simpan3.setLayout(b_simpan3Layout);
        b_simpan3Layout.setHorizontalGroup(
            b_simpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_simpan3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        b_simpan3Layout.setVerticalGroup(
            b_simpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_simpan3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(txt_kategori3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_dept, 0, 209, Short.MAX_VALUE)
                    .addComponent(cb_dept1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(b_simpan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_dept, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_dept1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(b_simpan3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(251, 255, 161));

        txt_kategori4.setBackground(new java.awt.Color(204, 204, 204));
        txt_kategori4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_kategori4.setForeground(new java.awt.Color(9, 10, 54));
        txt_kategori4.setText("        Kode Lokasi");

        cb_lokasi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select All" }));
        cb_lokasi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_lokasiItemStateChanged(evt);
            }
        });
        cb_lokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_lokasiActionPerformed(evt);
            }
        });

        b_simpan4.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpan4MouseClicked(evt);
            }
        });

        jLabel27.setBackground(new java.awt.Color(204, 204, 204));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(231, 238, 126));
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_qr_code_24px_2.png"))); // NOI18N
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout b_simpan4Layout = new javax.swing.GroupLayout(b_simpan4);
        b_simpan4.setLayout(b_simpan4Layout);
        b_simpan4Layout.setHorizontalGroup(
            b_simpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_simpan4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        b_simpan4Layout.setVerticalGroup(
            b_simpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(txt_kategori4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(80, 80, 80)
                .addComponent(cb_lokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(b_simpan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_lokasi, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(b_simpan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(251, 255, 161));

        txt_kategori5.setBackground(new java.awt.Color(204, 204, 204));
        txt_kategori5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_kategori5.setForeground(new java.awt.Color(9, 10, 54));
        txt_kategori5.setText("        Kode Kategori");

        cb_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select All" }));
        cb_kategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_kategoriItemStateChanged(evt);
            }
        });
        cb_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_kategoriActionPerformed(evt);
            }
        });

        b_simpan5.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpan5MouseClicked(evt);
            }
        });

        jLabel28.setBackground(new java.awt.Color(204, 204, 204));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(231, 238, 126));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_qr_code_24px_2.png"))); // NOI18N
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout b_simpan5Layout = new javax.swing.GroupLayout(b_simpan5);
        b_simpan5.setLayout(b_simpan5Layout);
        b_simpan5Layout.setHorizontalGroup(
            b_simpan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_simpan5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        b_simpan5Layout.setVerticalGroup(
            b_simpan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(txt_kategori5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(66, 66, 66)
                .addComponent(cb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(b_simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_kategori, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(b_simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(251, 255, 161));

        txt_kategori6.setBackground(new java.awt.Color(204, 204, 204));
        txt_kategori6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_kategori6.setForeground(new java.awt.Color(9, 10, 54));
        txt_kategori6.setText("        Kode Merk");

        cb_merk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select All" }));
        cb_merk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_merkItemStateChanged(evt);
            }
        });
        cb_merk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_merkMouseClicked(evt);
            }
        });
        cb_merk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_merkActionPerformed(evt);
            }
        });

        b_simpan6.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpan6MouseClicked(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(204, 204, 204));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(231, 238, 126));
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_qr_code_24px_2.png"))); // NOI18N
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout b_simpan6Layout = new javax.swing.GroupLayout(b_simpan6);
        b_simpan6.setLayout(b_simpan6Layout);
        b_simpan6Layout.setHorizontalGroup(
            b_simpan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_simpan6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        b_simpan6Layout.setVerticalGroup(
            b_simpan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(txt_kategori6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(79, 79, 79)
                .addComponent(cb_merk, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(b_simpan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_merk, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(b_simpan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(251, 255, 161));

        txt_kategori7.setBackground(new java.awt.Color(204, 204, 204));
        txt_kategori7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_kategori7.setForeground(new java.awt.Color(9, 10, 54));
        txt_kategori7.setText("        Kode Tipe Barang");

        cb_tipe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select All" }));
        cb_tipe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_tipeItemStateChanged(evt);
            }
        });
        cb_tipe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_tipeMouseClicked(evt);
            }
        });
        cb_tipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tipeActionPerformed(evt);
            }
        });

        b_simpan7.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpan7MouseClicked(evt);
            }
        });

        jLabel30.setBackground(new java.awt.Color(204, 204, 204));
        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(231, 238, 126));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_qr_code_24px_2.png"))); // NOI18N
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout b_simpan7Layout = new javax.swing.GroupLayout(b_simpan7);
        b_simpan7.setLayout(b_simpan7Layout);
        b_simpan7Layout.setHorizontalGroup(
            b_simpan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_simpan7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        b_simpan7Layout.setVerticalGroup(
            b_simpan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        cb_tipe1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select All" }));
        cb_tipe1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_tipe1ItemStateChanged(evt);
            }
        });
        cb_tipe1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_tipe1MouseClicked(evt);
            }
        });
        cb_tipe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tipe1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(txt_kategori7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_tipe1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(cb_tipe, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(b_simpan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_tipe, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_tipe1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(7, 7, 7))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(b_simpan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        
    }//GEN-LAST:event_b_simpanMouseClicked

    private void b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_editMouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_b_editMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        // mengambil ukuran layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();

        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
    }//GEN-LAST:event_formWindowOpened

    private void cb_perusahaanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_perusahaanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_perusahaanItemStateChanged

    private void cb_perusahaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_perusahaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_perusahaanActionPerformed

    private void cb_deptItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_deptItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_deptItemStateChanged

    private void cb_deptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_deptActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cb_deptActionPerformed

    private void cb_lokasiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_lokasiItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_lokasiItemStateChanged

    private void cb_lokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_lokasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_lokasiActionPerformed

    private void cb_kategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_kategoriItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_kategoriItemStateChanged

    private void cb_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_kategoriActionPerformed

    private void cb_merkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_merkItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_merkItemStateChanged

    private void cb_merkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_merkActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cb_merkActionPerformed

    private void cb_tipeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_tipeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tipeItemStateChanged

    private void cb_tipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tipeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cb_tipeActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
        
        kode=txt_kode.getText();
        String kat,mer,tip,per,lok,dep;
        kat=cb_kategori.getSelectedItem().toString();
        mer=cb_merk.getSelectedItem().toString();
        tip=cb_tipe.getSelectedItem().toString();
        per=cb_perusahaan.getSelectedItem().toString();
        lok=cb_lokasi.getSelectedItem().toString();
        dep=cb_dept.getSelectedItem().toString();
        
        String asql="select kode from kategori where nama='"+kat+"'";
        String bsql="select kode from merk where nama='"+mer+"'";
        String csql="select kode from barang where nama='"+tip+"'";
        String dsql="select kode from perusahaan where nama='"+per+"'";
        String esql="select kode from lokasi where nama='"+lok+"'";
        String fsql="select kode from department where nama='"+dep+"'";
        String asu="titit";
        try{
            Statement st = conn.createStatement(); 
            if (kat.equals("Select All")){
                a="*";
            }
            else {
                ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){a=(rs.getString(1));}
            }

             if (mer.equals("Select All")){
                b="*";
            }
            else {
            ResultSet brs = st.executeQuery(bsql); 
            while (brs.next()){b=(brs.getString(1));}
            }
            
             if (tip.equals("Select All")){
                c="*";
            }
            else {
            ResultSet crs = st.executeQuery(csql); 
            while (crs.next()){c=(crs.getString(1));}
            }
             
             if (per.equals("Select All")){
                d="*";
            }
            else {
            ResultSet drs = st.executeQuery(dsql); 
            while (drs.next()){d=(drs.getString(1));}
            }
        
             if (lok.equals("Select All")){
                e="*";
            }
            else {
            ResultSet ers = st.executeQuery(esql); 
            while (ers.next()){e=(ers.getString(1));}
            }
        
             if (dep.equals("Select All")){
                f="*";
            }
            else {
            ResultSet frs = st.executeQuery(fsql); 
            while (frs.next()){f=(frs.getString(1));}
            }
        
        }
        catch (SQLException er){
            JOptionPane.showMessageDialog(null,"data gagal diraih : "+ er);
        }
        
        cetak_qr();
        
    }//GEN-LAST:event_jLabel22MouseClicked

    private void cb_merkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_merkMouseClicked
        // TODO add your handling code here:
        combo_merk();
    }//GEN-LAST:event_cb_merkMouseClicked

    private void cb_deptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_deptMouseClicked
        // TODO add your handling code here:
        combo_dept();
    }//GEN-LAST:event_cb_deptMouseClicked

    private void cb_tipeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_tipeMouseClicked
        // TODO add your handling code here:
        combo_tipe();
    }//GEN-LAST:event_cb_tipeMouseClicked

    private void cb_dept1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_dept1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_dept1ItemStateChanged

    private void cb_dept1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_dept1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_dept1MouseClicked

    private void cb_dept1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_dept1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_dept1ActionPerformed

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel23MouseClicked

    private void b_simpan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_simpan1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b_simpan1MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel25MouseClicked

    private void b_simpan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_simpan2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b_simpan2MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel26MouseClicked

    private void b_simpan3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_simpan3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b_simpan3MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel27MouseClicked

    private void b_simpan4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_simpan4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b_simpan4MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel28MouseClicked

    private void b_simpan5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_simpan5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b_simpan5MouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel29MouseClicked

    private void b_simpan6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_simpan6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b_simpan6MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel30MouseClicked

    private void b_simpan7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_simpan7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b_simpan7MouseClicked

    private void cb_tipe1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_tipe1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tipe1ItemStateChanged

    private void cb_tipe1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_tipe1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tipe1MouseClicked

    private void cb_tipe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tipe1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tipe1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel b_edit;
    private javax.swing.JPanel b_simpan;
    private javax.swing.JPanel b_simpan1;
    private javax.swing.JPanel b_simpan2;
    private javax.swing.JPanel b_simpan3;
    private javax.swing.JPanel b_simpan4;
    private javax.swing.JPanel b_simpan5;
    private javax.swing.JPanel b_simpan6;
    private javax.swing.JPanel b_simpan7;
    private javax.swing.JComboBox<String> cb_dept;
    private javax.swing.JComboBox<String> cb_dept1;
    private javax.swing.JComboBox<String> cb_kategori;
    private javax.swing.JComboBox<String> cb_lokasi;
    private javax.swing.JComboBox<String> cb_merk;
    private javax.swing.JComboBox<String> cb_perusahaan;
    private javax.swing.JComboBox<String> cb_tipe;
    private javax.swing.JComboBox<String> cb_tipe1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel txt_kategori1;
    private javax.swing.JLabel txt_kategori2;
    private javax.swing.JLabel txt_kategori3;
    private javax.swing.JLabel txt_kategori4;
    private javax.swing.JLabel txt_kategori5;
    private javax.swing.JLabel txt_kategori6;
    private javax.swing.JLabel txt_kategori7;
    private javax.swing.JTextField txt_kode;
    // End of variables declaration//GEN-END:variables
}
