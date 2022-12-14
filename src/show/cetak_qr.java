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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import table.popup_transbarang;
import classes.sql;

/**
 *
 * @author nopal
 */ 
public class cetak_qr extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
public String a,b,c,d,e,f,x,kode,kode_barang,para,query;
HashMap<String,String> param = new HashMap<>();
    String kat,mer,tip,per,lok,dep;
    int count=0;
       
        

 String siqil = "SELECT\n" +
        "     barang.`kode` AS barang_kode,\n" +
        "     barang.`nama` AS barang_nama,\n" +
        "     trans_barang.`kode` AS trans_barang_kode,\n" +
        "     trans_barang.`kode_kategori` AS trans_barang_kode_kategori,\n" +
        "     trans_barang.`kode_merk` AS trans_barang_kode_merk,\n" +
        "     trans_barang.`kode_barang` AS trans_barang_kode_barang,\n" +
        "     trans_barang.`kode_perusahaan` AS trans_barang_kode_perusahaan,\n" +
        "     trans_barang.`kode_lokasi` AS trans_barang_kode_lokasi,\n" +
        "     trans_barang.`kode_dept` AS trans_barang_kode_dept,\n" +
        "     trans_barang.`kode_vendor` AS trans_barang_kode_vendor,\n" +
        "     department.`kode` AS department_kode,\n" +
        "     department.`nama` AS department_nama,\n" +
        "     kategori.`kode` AS kategori_kode,\n" +
        "     kategori.`nama` AS kategori_nama,\n" +
        "     lokasi.`kode` AS lokasi_kode,\n" +
        "     lokasi.`nama` AS lokasi_nama,\n" +
        "     merk.`kode` AS merk_kode,\n" +
        "     merk.`nama` AS merk_nama,\n" +
        "     perusahaan.`kode` AS perusahaan_kode,\n" +
        "     perusahaan.`nama` AS perusahaan_nama\n" +
        "FROM\n" +
        "     `trans_barang` trans_barang INNER JOIN `perusahaan` perusahaan ON trans_barang.`kode_perusahaan` = perusahaan.`kode`\n" +
        "     INNER JOIN `department` department ON trans_barang.`kode_dept` = department.`kode`\n" +
        "     INNER JOIN `merk` merk ON trans_barang.`kode_merk` = merk.`kode`\n" +
        "     INNER JOIN `lokasi` lokasi ON trans_barang.`kode_lokasi` = lokasi.`kode`\n" +
        "     INNER JOIN `kategori` kategori ON trans_barang.`kode_kategori` = kategori.`kode`\n" +
        "     INNER JOIN `barang` barang ON trans_barang.`kode_barang` = barang.`kode` ";

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
    
    public void itemTerpilih(String kode_barang) {
        popup_transbarang pop = new popup_transbarang();        
        pop.cetak_qr = this;
        txt_kode.setText(kode_barang);
    }
    
    
   
   public void cetak_qr_para_code(){
         para=txt_kode.getText();        
         
         if ((txt_kode.getText().equals("Select All")) || (txt_kode.getText().equals("")) ){
                x=siqil;
         }
         else { x = siqil+" where trans_barang.kode in ("+para+");"; }
         
        try{             
            String path="../src/report/cetakQR_all.jrxml";
            JasperDesign jd2=JRXmlLoader.load(path);
            HashMap parameter = new HashMap<>();
            JRDesignQuery newQuery2 = new JRDesignQuery();
            newQuery2.setText(x); 
            jd2.setQuery(newQuery2);
        
            JasperReport jr2=JasperCompileManager.compileReport(jd2);
            JasperPrint print2 = JasperFillManager.fillReport(jr2,null,conn);            
            JasperViewer.viewReport(print2,false);    
        }
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA : "+ex);
        }
//        System.out.println(Query2);
        
    }
   
   public void cetak_qr_all(){

        try{             
            String path="../src/report/cetakQR_all.jrxml";
            JasperDesign jd2=JRXmlLoader.load(path);
            HashMap parameter = new HashMap<>();
            JRDesignQuery newQuery2 = new JRDesignQuery();
            newQuery2.setText(query); 
            jd2.setQuery(newQuery2);
        
            JasperReport jr2=JasperCompileManager.compileReport(jd2);
            JasperPrint print2 = JasperFillManager.fillReport(jr2,null,conn);            
            JasperViewer.viewReport(print2,false);    
        }
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA : "+ex);
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
    
    public void para_1(){
         kat=cb_kategori.getSelectedItem().toString();
         String asql="select kode from kategori where nama='"+kat+"'";
    try{         
            Statement st = conn.createStatement(); 
            if (kat.equals("Select All")){}
            else {
            ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){a=(rs.getString(1));}
            if (count==0){query=query+"where trans_barang.kode_kategori='"+a+"'"; count=count+1;} else {query=siqil+" trans_barang.kode_kategori='"+a+"'";}
            }}
            catch (SQLException er){
            JOptionPane.showMessageDialog(null,"data gagal diraih : "+ er);
            }
    }
    
    public void para_2(){
         mer=cb_merk.getSelectedItem().toString();
         String bsql="select kode from merk where nama='"+mer+"'";
    try{
            Statement st = conn.createStatement(); 
            if (mer.equals("Select All")){}
            else {
            ResultSet brs = st.executeQuery(bsql); 
            while (brs.next()){b=(brs.getString(1));}
            if (count==0){query=query+"where trans_barang.kode_merk='"+b+"'"; count=count+1;} else {query=query+" and trans_barang.kode_merk='"+b+"'";}
            }}
            catch (SQLException er){
            JOptionPane.showMessageDialog(null,"data gagal diraih : "+ er);
            }
    }
    
    public void para_3(){
         tip=cb_tipe.getSelectedItem().toString();
         String csql="select kode from barang where nama='"+tip+"'";
    try{
             Statement st = conn.createStatement();
              if (tip.equals("Select All")){}
            else {
            ResultSet crs = st.executeQuery(csql); 
            while (crs.next()){c=(crs.getString(1));}
            if (count==0){query=query+"where trans_barang.kode_barang='"+c+"'"; count=count+1;} else {query=query+" and trans_barang.kode_barang='"+c+"'";}
            }}
    
            catch (SQLException er){
            JOptionPane.showMessageDialog(null,"data gagal diraih : "+ er);
            }
    }
    
    public void para_4(){
         per=cb_perusahaan.getSelectedItem().toString();
         String dsql="select kode from perusahaan where nama='"+per+"'";
        try{
             Statement st = conn.createStatement();
        if (per.equals("Select All")){}
            else {
            ResultSet drs = st.executeQuery(dsql); 
            while (drs.next()){d=(drs.getString(1));}
            if (count==0){query=query+"where trans_barang.kode_perusahaan='"+d+"'"; count=count+1;} else {query=query+" and trans_barang.kode_perusahaan='"+d+"'";}
            }}
        catch (SQLException er){
            JOptionPane.showMessageDialog(null,"data gagal diraih : "+ er);
            }   
    }
    
        public void para_5(){
             lok=cb_lokasi.getSelectedItem().toString();
              String esql="select kode from lokasi where nama='"+lok+"'";
            try{
                Statement st = conn.createStatement();
        if (lok.equals("Select All")){}
            else {
            ResultSet ers = st.executeQuery(esql); 
            while (ers.next()){e=(ers.getString(1));}
            if (count==0){query=query+"where trans_barang.kode_lokasi='"+e+"'"; count=count+1;} else {query=query+" and trans_barang.kode_lokasi='"+e+"'";}
            }}
              catch (SQLException er){
            JOptionPane.showMessageDialog(null,"data gagal diraih : "+ er);
            } 
        }
        
        public void para_6(){
            dep=cb_dept.getSelectedItem().toString();
            String fsql="select kode from department where nama='"+dep+"'";
        try{
            Statement st = conn.createStatement();
            if (dep.equals("Select All")){}
            else {
            ResultSet frs = st.executeQuery(fsql); 
            while (frs.next()){f=(frs.getString(1));}
            if (count==0){query=query+"where trans_barang.kode_dept='"+f+"'"; count=count+1;} else {query=query+" and trans_barang.kode_dept='"+f+"'";}
            }
        }
        catch (SQLException er){
            JOptionPane.showMessageDialog(null,"data gagal diraih : "+ er);
            } 
        }
    
    
    
//tempat sampah nya nopal
 
    //        classes.sql p_code = new classes.sql(); 
  //       para=txt_kode.getText();
//        p_code.a=txt_kode.getText();
        
//        classes.sql sikil = new classes.sql();
//        sikil.a = para;
//        sikil.getPara(para); 
//        String Query2 = p_code.para_kode;
    
    
    //        byte[] bytes = Query.getBytes(StandardCharsets.UTF_8);
//
//        String query_utf8 = new String(bytes, StandardCharsets.UTF_8);
    
    
//    public void combo_tipe(){
//        String a = cb_merk.getSelectedItem().toString();
//        cb_tipe.removeAllItems();
//        cb_tipe.addItem("Select All");  
//        String asql="select kode from merk where nama='"+a+"'";  
//        try { 
//            String b="";
//            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
//            while (rs.next()){b=rs.getString("kode");}
//            
//            String sql = "select nama from barang where kode_merk='"+b+"'";           
//            Statement s = conn.createStatement();
//            ResultSet r = s.executeQuery(sql); 
//            while (r.next()) {
//                cb_tipe.addItem(r.getString(1));
//            }
//        } catch (Exception e) {
//        }         
//    }
    
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
        b_simpan9 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
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
        jPanel8 = new javax.swing.JPanel();
        txt_kategori3 = new javax.swing.JLabel();
        cb_dept = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        txt_kategori4 = new javax.swing.JLabel();
        cb_lokasi = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        txt_kategori5 = new javax.swing.JLabel();
        cb_kategori = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        txt_kategori6 = new javax.swing.JLabel();
        cb_merk = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        txt_kategori7 = new javax.swing.JLabel();
        cb_tipe = new javax.swing.JComboBox<>();

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
                .addGap(50, 50, 50)
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
        txt_kode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_kodeFocusGained(evt);
            }
        });

        b_simpan1.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpan1MouseClicked(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(204, 204, 204));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(231, 238, 126));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
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

        b_simpan9.setBackground(new java.awt.Color(9, 10, 54));
        b_simpan9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_simpan9MouseClicked(evt);
            }
        });

        jLabel32.setBackground(new java.awt.Color(204, 204, 204));
        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(231, 238, 126));
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_qr_code_24px_2.png"))); // NOI18N
        jLabel32.setIconTextGap(0);
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout b_simpan9Layout = new javax.swing.GroupLayout(b_simpan9);
        b_simpan9.setLayout(b_simpan9Layout);
        b_simpan9Layout.setHorizontalGroup(
            b_simpan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_simpan9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        );
        b_simpan9Layout.setVerticalGroup(
            b_simpan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(txt_kategori1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_simpan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_kategori1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kode)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b_simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_simpan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(txt_kategori2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(cb_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_perusahaan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(txt_kategori3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_dept, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_dept, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(txt_kategori4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(80, 80, 80)
                .addComponent(cb_lokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_lokasi, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(txt_kategori5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(66, 66, 66)
                .addComponent(cb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(txt_kategori6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(79, 79, 79)
                .addComponent(cb_merk, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_merk, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(txt_kategori7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(cb_tipe, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_tipe, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_kategori7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        // TODO add your handling code here: trans_barang.kode_dept,department.nama
        
        
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
        // TODO add your handling code here
        query=siqil;
        para_1();
        para_2();
        para_3();
        para_4();
        para_5();
        para_6();
        
        cetak_qr_all();
        count=0;
        System.out.println(query);
        
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

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        // TODO add your handling code here:
        popup_transbarang a = new popup_transbarang();
        a.cetak_qr = this;           
        a.setVisible(true);   
        a.tab_transaksi();
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel23MouseClicked

    private void b_simpan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_simpan1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b_simpan1MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        // TODO add your handling code here:
//        if (txt_kode.getText().equals("Select All")){
//        cetak_qr_all();
//        }else {
////        query= txt_kode.getText().replaceAll(","," OR trans_barang.kode = ");

        cetak_qr_para_code();
        x="";
//        }
    }//GEN-LAST:event_jLabel32MouseClicked

    private void b_simpan9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_simpan9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b_simpan9MouseClicked

    private void txt_kodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_kodeFocusGained
        // TODO add your handling code here
        
    }//GEN-LAST:event_txt_kodeFocusGained

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel b_edit;
    private javax.swing.JPanel b_simpan;
    private javax.swing.JPanel b_simpan1;
    private javax.swing.JPanel b_simpan9;
    private javax.swing.JComboBox<String> cb_dept;
    private javax.swing.JComboBox<String> cb_kategori;
    private javax.swing.JComboBox<String> cb_lokasi;
    private javax.swing.JComboBox<String> cb_merk;
    private javax.swing.JComboBox<String> cb_perusahaan;
    private javax.swing.JComboBox<String> cb_tipe;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel32;
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
