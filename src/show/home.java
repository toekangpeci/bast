/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package show;

import java.awt.Color;
import java.awt.Font; 
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Aditya Naufal
 */
public class home extends javax.swing.JFrame {
    int int_data_list,int_home_list,int_items_list, int_trans_list, int_report_list = 0;
    private DefaultTableModel tabmode,tabmode2;
    private Connection conn = new koneksi().connect();
    public static String id_perusahaan,id_department,id_lokasi,id_vendor,id_kategori,id_brands,id_tipe,id_user,id_trans_barang = "";
    public reg_company dataa=null;


    /**
     * Creates new form from very far home
     */
    
    public home() {
        initComponents();
        coloring(); coloring2(); coloring3();
        
    }
    
        public void combo_perusahaan(){
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
        
        public void combo_perusahaan1(){
            cb_perusahaan1.removeAllItems();
            cb_perusahaan1.addItem(" - Pilih Perusahaan - ");     
            try {            
                String sql = "select nama from perusahaan";           
                Statement s = conn.createStatement();
                ResultSet r = s.executeQuery(sql); 
                while (r.next()) {
                    cb_perusahaan1.addItem(r.getString(1));
                }
            } catch (Exception e) {
            }         
        }    
        
        public void combo_department(){
        String a = cb_perusahaan1.getSelectedItem().toString();
        cb_department.removeAllItems();
        cb_department.addItem(" - Pilih Department - ");  
        String asql="select kode from perusahaan where nama='"+a+"'";  
        try { 
            String b="";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){b=rs.getString("kode");}
            
            String sql = "select kode, nama from department where kode_perusahaan='"+b+"'";           
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql); 
            while (r.next()) {
                cb_department.addItem(r.getString(1)+" - "+r.getString(2));
            }
        } catch (Exception e) {
        }         
    }  
        
        public void combo_lokasi(){
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
        
        public void combo_kategori(){
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
    
        public void cetak(){
        try{
            String path="../src/report/item.history.jasper";
            HashMap parameter = new HashMap();
            String a = id_trans_barang;
            parameter.put("id_trans_barang",a);
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);
        }
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA"+ex);
        }
    }
        
        public void combo_merk(){
            cb_merk.removeAllItems();
            cb_merk.addItem(" - Pilih Merk - ");     
            try {            
                String sql = "select nama from merk";           
                Statement s = conn.createStatement();
                ResultSet r = s.executeQuery(sql); 
                while (r.next()) {
                    cb_merk.addItem(r.getString(1));
                }
            } catch (Exception e) {
            }         
        }    
        
        public void combo_vendor(){
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
        
        public void cetak_report_all(){
        try{
            String path="../src/report/item.all.jasper";
            HashMap parameter = new HashMap();
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);
        }
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA"+ex);
        }
    }
        
        public void cetak_report_perusahaan(){
            String asql="select kode from perusahaan where nama='"+cb_perusahaan.getSelectedItem().toString()+"'";  
        try{
            String a="";
            String path="../src/report/item.perusahaan.jasper";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){a=rs.getString("kode");}
            HashMap parameter = new HashMap();
            parameter.put("id_perusahaan",a);
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);
        }
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA"+ex);
        }
    }
        
        public void cetak_report_lokasi(){
            String asql="select kode from lokasi where nama='"+cb_lokasi.getSelectedItem().toString()+"'";  
        try{
            String a="";
            String path="../src/report/item.lokasi.jasper";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){a=rs.getString("kode");}
            int b = Integer.parseInt(a);
            HashMap parameter = new HashMap();
            parameter.put("id_lokasi",b);
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);
        }
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA"+ex);
        }
    }
        
        public void cetak_report_kategori(){
            String asql="select kode from kategori where nama='"+cb_kategori.getSelectedItem().toString()+"'";  
        try{
            String a="";
            String path="../src/report/item.kategori.jasper";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){a=rs.getString("kode");}
            int b = Integer.parseInt(a);
            HashMap parameter = new HashMap();
            parameter.put("id_kategori",b);
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);
        }
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA"+ex);
        }
    }
        
        
        public void cetak_report_merk(){
            String asql="select kode from merk where nama='"+cb_merk.getSelectedItem().toString()+"'";  
        try{
            String a="";
            String path="../src/report/item.merk.jasper";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){a=rs.getString("kode");}
            int b = Integer.parseInt(a);
            HashMap parameter = new HashMap();
            parameter.put("id_merk",b);
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);
        }
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA"+ex);
        }
    }
        
        
        public void cetak_report_vendor(){
            String asql="select kode from vendor where nama='"+cb_vendor.getSelectedItem().toString()+"'";  
        try{
            String a="";
            String path="../src/report/item.vendor.jasper";
            Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(asql); 
            while (rs.next()){a=rs.getString("kode");}
            int b = Integer.parseInt(a);
            HashMap parameter = new HashMap();
            parameter.put("id_vendor",b);
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);
        }
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA"+ex);
        }
    }
        
         public void cetak_report_department(){
        try{
            String a=cb_department.getSelectedItem().toString().replaceAll("[A-Z]","").replaceAll("[a-z]","").replace(" ","").replace("-","");
            String path="../src/report/item.department.jasper";
            int b = Integer.parseInt(a);
            HashMap parameter = new HashMap();
            parameter.put("id_dept",b);
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);
        }
        catch (Exception ex){
                JOptionPane.showMessageDialog(null,"DOKUMEN TIDAK ADA"+ex);
        }
    }
        
        
    
    
protected void tab_perusahaan(){
        Object[] Baris = {"kode","Nama Perusahaan","Alamat Perusahaan","Keterangan"};
        tabmode = new DefaultTableModel(null,Baris);
        String cariitem=txt_perusahaan.getText();
        try {
            String sql="SELECT*FROM perusahaan where (kode like '%"+cariitem+"%' or nama like '%"+cariitem+"%' "
                    + "or alamat like '%"+cariitem +"%') order by kode asc";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4)
            });
            }
            tab_perusahaan.setModel(tabmode);
            tab_perusahaan.setDefaultEditor(Object.class, null);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"data gagal ditemukan, Kode : "+ e);
        }    
    }

public void tab_department(){
        Object[] Baris = {"kode","Nama Department","Perusahaan","Keterangan"};
        tabmode = new DefaultTableModel(null,Baris);
        String cariitem=txt_department.getText();
        try {
            String sql="SELECT department.kode, department.nama, perusahaan.nama, department.keterangan "
                    + "FROM department inner join perusahaan on department.kode_perusahaan=perusahaan.kode "
                    + "where (perusahaan.nama like '%"+cariitem+"%' or department.nama like'%"+cariitem+"%') order by department.kode asc" ;
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4)
            });
            }
            tab_department.setModel(tabmode);
            tab_department.setDefaultEditor(Object.class, null);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"data gagal ditemukan, Kode : "+ e);
        }    
    }

protected void tab_lokasi(){
        Object[] Baris = {"kode","Nama Lokasi","Detail Lokasi","Keterangan"};
        tabmode = new DefaultTableModel(null,Baris);
        String cariitem=txt_lokasi.getText();
        try {
            String sql="SELECT*FROM lokasi where (kode like '%"+cariitem+"%' or nama like '%"+cariitem+"%' "
                    + "or detail like '%"+cariitem +"%') order by kode asc";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4)
            });
            }
            tab_lokasi.setModel(tabmode);
            tab_lokasi.setDefaultEditor(Object.class, null);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"data gagal ditemukan, Kode : "+ e);
        }    
    }

protected void tab_vendor(){
        Object[] Baris = {"kode","Nama","Bidang","CP","Alamat","Phone","Email","Keterangan"};
        tabmode = new DefaultTableModel(null,Baris);
        String cariitem=txt_vendor.getText();
        try {
            String sql="SELECT * FROM vendor where (kode like '%"+cariitem+"%' or nama like '%"+cariitem+"%' "
                    + "or bidang like '%"+cariitem +"%') order by kode asc";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4),
                hasil.getString(5),
                hasil.getString(6),
                hasil.getString(7),
                hasil.getString(8)        
            });
            }
            tab_vendor.setModel(tabmode);
            tab_vendor.setDefaultEditor(Object.class, null);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"data gagal ditemukan, Kode : "+ e);
        }    
    }

protected void tab_kategori(){
        Object[] Baris = {"kode","Nama Kategori","Keterangan"};
        tabmode = new DefaultTableModel(null,Baris);
        String cariitem=txt_kategori.getText();
        try {
            String sql="SELECT * FROM kategori where (kode like '%"+cariitem+"%' or nama like '%"+cariitem+"%')";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3)
            });
            }
            tab_kategori.setModel(tabmode);
            tab_kategori.setDefaultEditor(Object.class, null);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"data gagal ditemukan, Kode : "+ e);
        }    
    }

public void tab_brands(){
        Object[] Baris = {"kode","Nama Brands/Merk","Kategori","Keterangan"};
        tabmode = new DefaultTableModel(null,Baris);
        String cariitem=txt_brands.getText();
        try {
            String sql="SELECT merk.kode, merk.nama, kategori.nama, merk.keterangan "
                    + "FROM merk inner join kategori on merk.kode_kategori=kategori.kode "
                    + "where (merk.nama like '%"+cariitem+"%' or kategori.nama like'%"+cariitem+"%') order by merk.kode asc" ;
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4)
            });
            }
            tab_brands.setModel(tabmode);
            tab_brands.setDefaultEditor(Object.class, null);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"data gagal ditemukan, Kode : "+ e);
        }    
    }

public void tab_tipe(){
        Object[] Baris = {"kode","Nama Tipe","Kategori","Brands/Merk","Keterangan"};
        tabmode = new DefaultTableModel(null,Baris);
        String cariitem=txt_tipe.getText();
        try {
            String sql="SELECT barang.kode, barang.nama,kategori.nama,"
                    + "merk.nama, barang.keterangan "
                    + "FROM barang inner join kategori on barang.kode_kategori=kategori.kode join merk on barang.kode_merk=merk.kode "
                    + "where (barang.nama like '%"+cariitem+"%' or kategori.nama like'%"+cariitem+"%' or merk.nama like'%"+cariitem+"%') order by barang.kode asc" ;
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4),
                hasil.getString(5)
            });
            }
            tab_tipe.setModel(tabmode);
            tab_tipe.setDefaultEditor(Object.class, null);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"data gagal ditemukan, Kode : "+ e);
        }    
    }

protected void tab_user(){
        Object[] Baris = {"User ID","Nama","Level Akses"};
        tabmode = new DefaultTableModel(null,Baris);
        String cariitem=txt_user.getText();
        try {
            String sql="SELECT * FROM user where (jabatan like '%"+cariitem+"%' or nama like '%"+cariitem+"%')";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(4)
            });
            }
            tab_user.setModel(tabmode);
            tab_user.setDefaultEditor(Object.class, null);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"data gagal ditemukan, Kode : "+ e);
        }    
    }

protected void tab_transaksi(){
        Object[] Baris = {"Kode Assets","Kategori","Merk","Tipe","Perusahaan","Lokasi","Dept","Status"};
        tabmode = new DefaultTableModel(null,Baris);
        String cariitem=txt_transaksi.getText();
        try {
            String sql="SELECT trans_barang.kode, kategori.nama, merk.nama, barang.nama, perusahaan.nama, lokasi.nama, department.nama, trans_barang.status"
                    + " FROM trans_barang join kategori on trans_barang.kode_kategori=kategori.kode"
                    + " join merk on trans_barang.kode_merk=merk.kode"
                    + " join barang on trans_barang.kode_barang=barang.kode"
                    + " join perusahaan on trans_barang.kode_perusahaan=perusahaan.kode"
                    + " join lokasi on trans_barang.kode_lokasi=lokasi.kode"
                    + " join department on trans_barang.kode_dept=department.kode "
                    + "where (trans_barang.kode like '%"+cariitem+"%' or barang.nama like '%"+cariitem+"%')";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4),
                hasil.getString(5),
                hasil.getString(6),
                hasil.getString(7),
                hasil.getString(8)        
            });
            }
            tab_transaksi.setModel(tabmode);
            tab_transaksi.setDefaultEditor(Object.class, null);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"data gagal ditemukan, Kode : "+ e);
        }    
    }

    private void coloring(){
        if (int_data_list==1){
            menu_data_list.setVisible(true);
            menu_2.setBackground(new Color(67,131,113));
            menu_data_list.setBackground(new Color(67,131,113));
        }      
        else if (int_data_list==0){
            menu_data_list.setVisible(false);
            menu_2.setBackground(new Color(32,74,86));
            menu_data_list.setBackground(new Color(32,74,86));
        }         
    }
    
    private void coloring2(){
         if (int_home_list==1){
            menu_1.setBackground(new Color(67,131,113));
        }
          else if (int_home_list==0){         
            menu_1.setBackground(new Color(32,74,86));
        }    
    }
    
    private void coloring3(){
        if (int_items_list==1){
            menu_items_list.setVisible(true);
            menu_3.setBackground(new Color(67,131,113));
            menu_items_list.setBackground(new Color(67,131,113));
        }      
        else if (int_items_list==0){
            menu_items_list.setVisible(false);
            menu_3.setBackground(new Color(32,74,86));
            menu_items_list.setBackground(new Color(32,74,86));
        }         
    }    

    private void coloring4(){
         if (int_trans_list==1){
            menu_4.setBackground(new Color(67,131,113));
        }
          else if (int_trans_list==0){         
            menu_4.setBackground(new Color(32,74,86));
        }    
    }
    
    private void coloring5(){
         if (int_report_list==1){
            menu_5.setBackground(new Color(67,131,113));
        }
          else if (int_report_list==0){         
            menu_5.setBackground(new Color(32,74,86));
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

        jPanel1 = new javax.swing.JPanel();
        p_sidebar = new javax.swing.JPanel();
        menu_1 = new javax.swing.JPanel();
        menu_home = new javax.swing.JLabel();
        menu_2 = new javax.swing.JPanel();
        menu_data = new javax.swing.JLabel();
        menu_data_list = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        menu_3 = new javax.swing.JPanel();
        menu_items = new javax.swing.JLabel();
        menu_items_list = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        menu_5 = new javax.swing.JPanel();
        menu_report = new javax.swing.JLabel();
        menu_4 = new javax.swing.JPanel();
        menu_trans1 = new javax.swing.JLabel();
        p_utama = new javax.swing.JPanel();
        p_home = new javax.swing.JPanel();
        p_perusahaan_header9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        p_perusahaan_main9 = new javax.swing.JPanel();
        b_edit = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        p_vendor = new javax.swing.JPanel();
        p_perusahaan_header3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        p_perusahaan_main3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tab_vendor = new javax.swing.JTable();
        txt_vendor = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        vendor_b_tambah = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        vendor_b_hapus = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        vendor_b_cari = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        vendor_b_edit = new javax.swing.JLabel();
        p_perusahaan = new javax.swing.JPanel();
        p_perusahaan_header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        p_perusahaan_main = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_perusahaan = new javax.swing.JTable();
        txt_perusahaan = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        perusahaan_b_tambah = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        perusahaan_b_hapus = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        perusahaan_b_edit = new javax.swing.JLabel();
        p_department = new javax.swing.JPanel();
        p_perusahaan_header1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        p_perusahaan_main1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tab_department = new javax.swing.JTable();
        txt_department = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        department_b_tambah = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        department_b_hapus = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        Department_b_edit = new javax.swing.JLabel();
        p_lokasi = new javax.swing.JPanel();
        p_perusahaan_header2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        p_perusahaan_main2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tab_lokasi = new javax.swing.JTable();
        txt_lokasi = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        lokasi_b_tambah = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lokasi_b_hapus = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lokasi_b_cari = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lokasi_b_edit = new javax.swing.JLabel();
        p_kategori = new javax.swing.JPanel();
        p_kategori_header = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        p_kategori_main = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tab_kategori = new javax.swing.JTable();
        txt_kategori = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        kategori_b_tambah = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        kategori_b_hapus = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        kategori_b_cari = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        kategori_b_edit = new javax.swing.JLabel();
        p_brands = new javax.swing.JPanel();
        p_perusahaan_header4 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        p_perusahaan_main4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tab_brands = new javax.swing.JTable();
        txt_brands = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        brands_b_tambah = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        brands_b_hapus = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        brands_b_cari = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        brands_b_edit = new javax.swing.JLabel();
        p_user = new javax.swing.JPanel();
        p_perusahaan_header6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        p_perusahaan_main6 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tab_user = new javax.swing.JTable();
        txt_user = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        user_b_tambah = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        user_b_hapus = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        user_b_cari = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        user_b_edit = new javax.swing.JLabel();
        p_tipe = new javax.swing.JPanel();
        p_perusahaan_header5 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        p_perusahaan_main5 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tab_tipe = new javax.swing.JTable();
        txt_tipe = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        tipe_b_tambah = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        tipe_b_hapus = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        tipe_b_cari = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        tipe_b_edit = new javax.swing.JLabel();
        p_transaksi = new javax.swing.JPanel();
        p_perusahaan_header7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        p_perusahaan_main7 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tab_transaksi = new javax.swing.JTable();
        txt_transaksi = new javax.swing.JTextField();
        jPanel39 = new javax.swing.JPanel();
        transaksi_b_tambah = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        transaksi_b_cari = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        transaksi_b_edit = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        transaksi_b_edit1 = new javax.swing.JLabel();
        p_reports = new javax.swing.JPanel();
        p_perusahaan_header8 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        p_perusahaan_main8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        tipe_b_cari2 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        cb_perusahaan = new javax.swing.JComboBox<>();
        jPanel48 = new javax.swing.JPanel();
        tipe_b_cari3 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        cb_lokasi = new javax.swing.JComboBox<>();
        jPanel50 = new javax.swing.JPanel();
        tipe_b_cari5 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        cb_kategori = new javax.swing.JComboBox<>();
        jPanel51 = new javax.swing.JPanel();
        tipe_b_cari6 = new javax.swing.JLabel();
        cb_merk = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        tipe_b_cari7 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        cb_vendor = new javax.swing.JComboBox<>();
        jPanel53 = new javax.swing.JPanel();
        tipe_b_cari8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        cb_perusahaan1 = new javax.swing.JComboBox<>();
        jPanel49 = new javax.swing.JPanel();
        tipe_b_cari4 = new javax.swing.JLabel();
        cb_department = new javax.swing.JComboBox<>();
        jPanel54 = new javax.swing.JPanel();
        tipe_b_cari9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p_sidebar.setBackground(new java.awt.Color(32, 74, 86));

        menu_1.setBackground(new java.awt.Color(32, 74, 86));

        menu_home.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        menu_home.setForeground(new java.awt.Color(231, 238, 126));
        menu_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_home_40px_1.png"))); // NOI18N
        menu_home.setText("        H O M E");
        menu_home.setToolTipText("");
        menu_home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_homeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menu_1Layout = new javax.swing.GroupLayout(menu_1);
        menu_1.setLayout(menu_1Layout);
        menu_1Layout.setHorizontalGroup(
            menu_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(menu_home)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menu_1Layout.setVerticalGroup(
            menu_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu_1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(menu_home)
                .addContainerGap())
        );

        menu_2.setBackground(new java.awt.Color(32, 74, 86));

        menu_data.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        menu_data.setForeground(new java.awt.Color(231, 238, 126));
        menu_data.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_data_recovery_40px.png"))); // NOI18N
        menu_data.setText("   MANAGEMENT");
        menu_data.setToolTipText("");
        menu_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_dataMouseClicked(evt);
            }
        });

        menu_data_list.setBackground(new java.awt.Color(32, 74, 86));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(231, 238, 126));
        jLabel5.setText("Perusahaan");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(231, 238, 126));
        jLabel6.setText("Departement");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(231, 238, 126));
        jLabel7.setText("Lokasi");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(231, 238, 126));
        jLabel8.setText("Vendor");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jLabel8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel8KeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(231, 238, 126));
        jLabel10.setText("User");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jLabel10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel10KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout menu_data_listLayout = new javax.swing.GroupLayout(menu_data_list);
        menu_data_list.setLayout(menu_data_listLayout);
        menu_data_listLayout.setHorizontalGroup(
            menu_data_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_data_listLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menu_data_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menu_data_listLayout.setVerticalGroup(
            menu_data_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_data_listLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menu_2Layout = new javax.swing.GroupLayout(menu_2);
        menu_2.setLayout(menu_2Layout);
        menu_2Layout.setHorizontalGroup(
            menu_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(menu_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_data_list, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menu_data, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menu_2Layout.setVerticalGroup(
            menu_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu_data)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_data_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menu_3.setBackground(new java.awt.Color(32, 74, 86));

        menu_items.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        menu_items.setForeground(new java.awt.Color(231, 238, 126));
        menu_items.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        menu_items.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_new_product_40px.png"))); // NOI18N
        menu_items.setText("       I T E M S");
        menu_items.setToolTipText("");
        menu_items.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_itemsMouseClicked(evt);
            }
        });

        menu_items_list.setBackground(new java.awt.Color(32, 74, 86));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(231, 238, 126));
        jLabel21.setText("Kategori");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(231, 238, 126));
        jLabel22.setText("Merks/ Brands");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(231, 238, 126));
        jLabel23.setText("Tipe");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menu_items_listLayout = new javax.swing.GroupLayout(menu_items_list);
        menu_items_list.setLayout(menu_items_listLayout);
        menu_items_listLayout.setHorizontalGroup(
            menu_items_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_items_listLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menu_items_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                .addContainerGap())
        );
        menu_items_listLayout.setVerticalGroup(
            menu_items_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_items_listLayout.createSequentialGroup()
                .addComponent(jLabel21)
                .addGap(5, 5, 5)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menu_3Layout = new javax.swing.GroupLayout(menu_3);
        menu_3.setLayout(menu_3Layout);
        menu_3Layout.setHorizontalGroup(
            menu_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(menu_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_items_list, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menu_3Layout.createSequentialGroup()
                        .addComponent(menu_items)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        menu_3Layout.setVerticalGroup(
            menu_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu_3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(menu_items)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_items_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menu_5.setBackground(new java.awt.Color(32, 74, 86));

        menu_report.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        menu_report.setForeground(new java.awt.Color(231, 238, 126));
        menu_report.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        menu_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_graph_report_40px.png"))); // NOI18N
        menu_report.setText("    R E P O R T S");
        menu_report.setToolTipText("");
        menu_report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_reportMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menu_5Layout = new javax.swing.GroupLayout(menu_5);
        menu_5.setLayout(menu_5Layout);
        menu_5Layout.setHorizontalGroup(
            menu_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_5Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(menu_report)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        menu_5Layout.setVerticalGroup(
            menu_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu_report, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        menu_4.setBackground(new java.awt.Color(32, 74, 86));

        menu_trans1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        menu_trans1.setForeground(new java.awt.Color(231, 238, 126));
        menu_trans1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        menu_trans1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_transaction_list_40px.png"))); // NOI18N
        menu_trans1.setText("   TRANSACTION");
        menu_trans1.setToolTipText("");
        menu_trans1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_trans1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menu_4Layout = new javax.swing.GroupLayout(menu_4);
        menu_4.setLayout(menu_4Layout);
        menu_4Layout.setHorizontalGroup(
            menu_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(menu_trans1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menu_4Layout.setVerticalGroup(
            menu_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu_4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(menu_trans1)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout p_sidebarLayout = new javax.swing.GroupLayout(p_sidebar);
        p_sidebar.setLayout(p_sidebarLayout);
        p_sidebarLayout.setHorizontalGroup(
            p_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_sidebarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menu_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(menu_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_sidebarLayout.setVerticalGroup(
            p_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_sidebarLayout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(menu_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menu_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(p_sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 740));

        p_utama.setLayout(new java.awt.CardLayout());

        p_home.setBackground(new java.awt.Color(254, 255, 230));

        p_perusahaan_header9.setBackground(new java.awt.Color(5, 32, 56));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(231, 238, 126));
        jLabel19.setText("HOME / HOME");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(231, 238, 126));
        jLabel20.setText("Tampilan awal/ dashboard/ pintasan ke menu yang dituju.");

        javax.swing.GroupLayout p_perusahaan_header9Layout = new javax.swing.GroupLayout(p_perusahaan_header9);
        p_perusahaan_header9.setLayout(p_perusahaan_header9Layout);
        p_perusahaan_header9Layout.setHorizontalGroup(
            p_perusahaan_header9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header9Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_perusahaan_header9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_perusahaan_header9Layout.setVerticalGroup(
            p_perusahaan_header9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header9Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_perusahaan_main9.setBackground(new java.awt.Color(254, 255, 230));

        b_edit.setBackground(new java.awt.Color(9, 10, 54));
        b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_editMouseClicked(evt);
            }
        });

        jLabel42.setBackground(new java.awt.Color(204, 204, 204));
        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(231, 238, 126));
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        jLabel42.setText("C O M I N G   T O   Y O U   S O O N  .  .  .  .");
        jLabel42.setIconTextGap(8);

        javax.swing.GroupLayout b_editLayout = new javax.swing.GroupLayout(b_edit);
        b_edit.setLayout(b_editLayout);
        b_editLayout.setHorizontalGroup(
            b_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_editLayout.createSequentialGroup()
                .addGap(0, 233, Short.MAX_VALUE)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        b_editLayout.setVerticalGroup(
            b_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p_perusahaan_main9Layout = new javax.swing.GroupLayout(p_perusahaan_main9);
        p_perusahaan_main9.setLayout(p_perusahaan_main9Layout);
        p_perusahaan_main9Layout.setHorizontalGroup(
            p_perusahaan_main9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_perusahaan_main9Layout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addComponent(b_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
        );
        p_perusahaan_main9Layout.setVerticalGroup(
            p_perusahaan_main9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_main9Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(b_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_homeLayout = new javax.swing.GroupLayout(p_home);
        p_home.setLayout(p_homeLayout);
        p_homeLayout.setHorizontalGroup(
            p_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_perusahaan_header9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_perusahaan_main9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_homeLayout.setVerticalGroup(
            p_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_homeLayout.createSequentialGroup()
                .addComponent(p_perusahaan_header9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_perusahaan_main9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_home, "p_home");

        p_vendor.setBackground(new java.awt.Color(254, 255, 230));
        p_vendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p_vendorMouseExited(evt);
            }
        });

        p_perusahaan_header3.setBackground(new java.awt.Color(5, 32, 56));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(231, 238, 126));
        jLabel15.setText("MANAGEMENT / VENDOR");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(231, 238, 126));
        jLabel16.setText("Tambah, Ubah Dan Hapus Data Vendor Didalam Database Aplikasi ___________");

        javax.swing.GroupLayout p_perusahaan_header3Layout = new javax.swing.GroupLayout(p_perusahaan_header3);
        p_perusahaan_header3.setLayout(p_perusahaan_header3Layout);
        p_perusahaan_header3Layout.setHorizontalGroup(
            p_perusahaan_header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_perusahaan_header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_perusahaan_header3Layout.setVerticalGroup(
            p_perusahaan_header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header3Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_perusahaan_main3.setBackground(new java.awt.Color(254, 255, 230));
        p_perusahaan_main3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p_perusahaan_main3MouseExited(evt);
            }
        });

        tab_vendor.setBackground(new java.awt.Color(254, 255, 230));
        tab_vendor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tab_vendor.setForeground(new java.awt.Color(5, 32, 56));
        tab_vendor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_vendor.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tab_vendor.setOpaque(false);
        tab_vendor.setRowHeight(25);
        tab_vendor.setSelectionBackground(new java.awt.Color(5, 32, 56));
        tab_vendor.setSelectionForeground(new java.awt.Color(231, 238, 126));
        tab_vendor.setShowHorizontalLines(false);
        tab_vendor.setShowVerticalLines(false);
        tab_vendor.getTableHeader().setReorderingAllowed(false);
        tab_vendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_vendorMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tab_vendor);

        txt_vendor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_vendor.setForeground(new java.awt.Color(5, 32, 56));
        txt_vendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vendorActionPerformed(evt);
            }
        });
        txt_vendor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_vendorKeyPressed(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(67, 131, 113));

        vendor_b_tambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        vendor_b_tambah.setForeground(new java.awt.Color(231, 238, 126));
        vendor_b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_24px.png"))); // NOI18N
        vendor_b_tambah.setText("TAMBAH");
        vendor_b_tambah.setIconTextGap(8);
        vendor_b_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendor_b_tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vendor_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vendor_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel19.setBackground(new java.awt.Color(162, 34, 39));

        vendor_b_hapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        vendor_b_hapus.setForeground(new java.awt.Color(231, 238, 126));
        vendor_b_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_trash_can_24px.png"))); // NOI18N
        vendor_b_hapus.setText("   HAPUS");
        vendor_b_hapus.setIconTextGap(8);
        vendor_b_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendor_b_hapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vendor_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vendor_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(5, 32, 56));

        vendor_b_cari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        vendor_b_cari.setForeground(new java.awt.Color(231, 238, 126));
        vendor_b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        vendor_b_cari.setText("CARI/REFRESH");
        vendor_b_cari.setIconTextGap(8);
        vendor_b_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendor_b_cariMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vendor_b_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vendor_b_cari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(5, 32, 56));

        vendor_b_edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        vendor_b_edit.setForeground(new java.awt.Color(231, 238, 126));
        vendor_b_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        vendor_b_edit.setText("     EDIT");
        vendor_b_edit.setIconTextGap(8);
        vendor_b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendor_b_editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vendor_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vendor_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p_perusahaan_main3Layout = new javax.swing.GroupLayout(p_perusahaan_main3);
        p_perusahaan_main3.setLayout(p_perusahaan_main3Layout);
        p_perusahaan_main3Layout.setHorizontalGroup(
            p_perusahaan_main3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_perusahaan_main3Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(p_perusahaan_main3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p_perusahaan_main3Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(p_perusahaan_main3Layout.createSequentialGroup()
                        .addComponent(txt_vendor, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))))
        );
        p_perusahaan_main3Layout.setVerticalGroup(
            p_perusahaan_main3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_main3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(p_perusahaan_main3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_vendor)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_vendorLayout = new javax.swing.GroupLayout(p_vendor);
        p_vendor.setLayout(p_vendorLayout);
        p_vendorLayout.setHorizontalGroup(
            p_vendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_perusahaan_header3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_perusahaan_main3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_vendorLayout.setVerticalGroup(
            p_vendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_vendorLayout.createSequentialGroup()
                .addComponent(p_perusahaan_header3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_perusahaan_main3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_vendor, "p_vendor");

        p_perusahaan.setBackground(new java.awt.Color(254, 255, 230));
        p_perusahaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p_perusahaanMouseExited(evt);
            }
        });

        p_perusahaan_header.setBackground(new java.awt.Color(5, 32, 56));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(231, 238, 126));
        jLabel1.setText("MANAGEMENT / PERUSAHAAN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(231, 238, 126));
        jLabel2.setText("Tambah, Ubah Dan Hapus Data Perusahaan Didalam Database Aplikasi ___________");

        javax.swing.GroupLayout p_perusahaan_headerLayout = new javax.swing.GroupLayout(p_perusahaan_header);
        p_perusahaan_header.setLayout(p_perusahaan_headerLayout);
        p_perusahaan_headerLayout.setHorizontalGroup(
            p_perusahaan_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_headerLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_perusahaan_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_perusahaan_headerLayout.setVerticalGroup(
            p_perusahaan_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_headerLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_perusahaan_main.setBackground(new java.awt.Color(254, 255, 230));
        p_perusahaan_main.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p_perusahaan_mainMouseExited(evt);
            }
        });

        tab_perusahaan.setBackground(new java.awt.Color(254, 255, 230));
        tab_perusahaan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tab_perusahaan.setForeground(new java.awt.Color(5, 32, 56));
        tab_perusahaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_perusahaan.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tab_perusahaan.setOpaque(false);
        tab_perusahaan.setRowHeight(25);
        tab_perusahaan.setSelectionBackground(new java.awt.Color(5, 32, 56));
        tab_perusahaan.setSelectionForeground(new java.awt.Color(231, 238, 126));
        tab_perusahaan.setShowHorizontalLines(false);
        tab_perusahaan.setShowVerticalLines(false);
        tab_perusahaan.getTableHeader().setReorderingAllowed(false);
        tab_perusahaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_perusahaanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab_perusahaan);

        txt_perusahaan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_perusahaan.setForeground(new java.awt.Color(5, 32, 56));
        txt_perusahaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_perusahaanActionPerformed(evt);
            }
        });
        txt_perusahaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_perusahaanKeyPressed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(67, 131, 113));

        perusahaan_b_tambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        perusahaan_b_tambah.setForeground(new java.awt.Color(231, 238, 126));
        perusahaan_b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_24px.png"))); // NOI18N
        perusahaan_b_tambah.setText("TAMBAH");
        perusahaan_b_tambah.setIconTextGap(8);
        perusahaan_b_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perusahaan_b_tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(perusahaan_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(perusahaan_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(162, 34, 39));

        perusahaan_b_hapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        perusahaan_b_hapus.setForeground(new java.awt.Color(231, 238, 126));
        perusahaan_b_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_trash_can_24px.png"))); // NOI18N
        perusahaan_b_hapus.setText("   HAPUS");
        perusahaan_b_hapus.setIconTextGap(8);
        perusahaan_b_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perusahaan_b_hapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(perusahaan_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(perusahaan_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(5, 32, 56));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(231, 238, 126));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        jLabel9.setText("CARI/REFRESH");
        jLabel9.setIconTextGap(8);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(5, 32, 56));

        perusahaan_b_edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        perusahaan_b_edit.setForeground(new java.awt.Color(231, 238, 126));
        perusahaan_b_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        perusahaan_b_edit.setText("     EDIT");
        perusahaan_b_edit.setIconTextGap(8);
        perusahaan_b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perusahaan_b_editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(perusahaan_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(perusahaan_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p_perusahaan_mainLayout = new javax.swing.GroupLayout(p_perusahaan_main);
        p_perusahaan_main.setLayout(p_perusahaan_mainLayout);
        p_perusahaan_mainLayout.setHorizontalGroup(
            p_perusahaan_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_perusahaan_mainLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(p_perusahaan_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p_perusahaan_mainLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(p_perusahaan_mainLayout.createSequentialGroup()
                        .addComponent(txt_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))))
        );
        p_perusahaan_mainLayout.setVerticalGroup(
            p_perusahaan_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_mainLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(p_perusahaan_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_perusahaan)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_perusahaanLayout = new javax.swing.GroupLayout(p_perusahaan);
        p_perusahaan.setLayout(p_perusahaanLayout);
        p_perusahaanLayout.setHorizontalGroup(
            p_perusahaanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_perusahaan_header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_perusahaan_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_perusahaanLayout.setVerticalGroup(
            p_perusahaanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaanLayout.createSequentialGroup()
                .addComponent(p_perusahaan_header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_perusahaan_main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_perusahaan, "p_perusahaan");

        p_department.setBackground(new java.awt.Color(254, 255, 230));

        p_perusahaan_header1.setBackground(new java.awt.Color(5, 32, 56));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(231, 238, 126));
        jLabel3.setText("MANAGEMENT / Department");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(231, 238, 126));
        jLabel4.setText("Tambah, Ubah Dan Hapus Data Department Didalam Database Aplikasi ___________");

        javax.swing.GroupLayout p_perusahaan_header1Layout = new javax.swing.GroupLayout(p_perusahaan_header1);
        p_perusahaan_header1.setLayout(p_perusahaan_header1Layout);
        p_perusahaan_header1Layout.setHorizontalGroup(
            p_perusahaan_header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_perusahaan_header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_perusahaan_header1Layout.setVerticalGroup(
            p_perusahaan_header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_perusahaan_main1.setBackground(new java.awt.Color(254, 255, 230));

        tab_department.setBackground(new java.awt.Color(254, 255, 230));
        tab_department.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tab_department.setForeground(new java.awt.Color(5, 32, 56));
        tab_department.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_department.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tab_department.setOpaque(false);
        tab_department.setRowHeight(25);
        tab_department.setSelectionBackground(new java.awt.Color(5, 32, 56));
        tab_department.setSelectionForeground(new java.awt.Color(231, 238, 126));
        tab_department.setShowHorizontalLines(false);
        tab_department.setShowVerticalLines(false);
        tab_department.getTableHeader().setReorderingAllowed(false);
        tab_department.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_departmentMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tab_department);

        txt_department.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_department.setForeground(new java.awt.Color(5, 32, 56));
        txt_department.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_departmentActionPerformed(evt);
            }
        });
        txt_department.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_departmentKeyPressed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(67, 131, 113));

        department_b_tambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        department_b_tambah.setForeground(new java.awt.Color(231, 238, 126));
        department_b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_24px.png"))); // NOI18N
        department_b_tambah.setText("TAMBAH");
        department_b_tambah.setIconTextGap(8);
        department_b_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                department_b_tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(department_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(department_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(162, 34, 39));

        department_b_hapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        department_b_hapus.setForeground(new java.awt.Color(231, 238, 126));
        department_b_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_trash_can_24px.png"))); // NOI18N
        department_b_hapus.setText("   HAPUS");
        department_b_hapus.setIconTextGap(8);
        department_b_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                department_b_hapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(department_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(department_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(5, 32, 56));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(231, 238, 126));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        jLabel12.setText("CARI/REFRESH");
        jLabel12.setIconTextGap(8);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(5, 32, 56));

        Department_b_edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Department_b_edit.setForeground(new java.awt.Color(231, 238, 126));
        Department_b_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        Department_b_edit.setText("     EDIT");
        Department_b_edit.setIconTextGap(8);
        Department_b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Department_b_editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Department_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Department_b_edit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p_perusahaan_main1Layout = new javax.swing.GroupLayout(p_perusahaan_main1);
        p_perusahaan_main1.setLayout(p_perusahaan_main1Layout);
        p_perusahaan_main1Layout.setHorizontalGroup(
            p_perusahaan_main1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_main1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(p_perusahaan_main1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(p_perusahaan_main1Layout.createSequentialGroup()
                        .addComponent(txt_department, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        p_perusahaan_main1Layout.setVerticalGroup(
            p_perusahaan_main1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_main1Layout.createSequentialGroup()
                .addGroup(p_perusahaan_main1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_department, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_departmentLayout = new javax.swing.GroupLayout(p_department);
        p_department.setLayout(p_departmentLayout);
        p_departmentLayout.setHorizontalGroup(
            p_departmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_perusahaan_header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_perusahaan_main1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_departmentLayout.setVerticalGroup(
            p_departmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_departmentLayout.createSequentialGroup()
                .addComponent(p_perusahaan_header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(p_perusahaan_main1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_department, "p_department");

        p_lokasi.setBackground(new java.awt.Color(254, 255, 230));

        p_perusahaan_header2.setBackground(new java.awt.Color(5, 32, 56));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(231, 238, 126));
        jLabel13.setText("MANAGEMENT / LOKASI");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(231, 238, 126));
        jLabel14.setText("Tambah, Ubah Dan Hapus Data Lokasi Didalam Database Aplikasi ___________");

        javax.swing.GroupLayout p_perusahaan_header2Layout = new javax.swing.GroupLayout(p_perusahaan_header2);
        p_perusahaan_header2.setLayout(p_perusahaan_header2Layout);
        p_perusahaan_header2Layout.setHorizontalGroup(
            p_perusahaan_header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_perusahaan_header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_perusahaan_header2Layout.setVerticalGroup(
            p_perusahaan_header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_perusahaan_main2.setBackground(new java.awt.Color(254, 255, 230));

        tab_lokasi.setBackground(new java.awt.Color(254, 255, 230));
        tab_lokasi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tab_lokasi.setForeground(new java.awt.Color(5, 32, 56));
        tab_lokasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_lokasi.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tab_lokasi.setOpaque(false);
        tab_lokasi.setRowHeight(25);
        tab_lokasi.setSelectionBackground(new java.awt.Color(5, 32, 56));
        tab_lokasi.setSelectionForeground(new java.awt.Color(231, 238, 126));
        tab_lokasi.setShowHorizontalLines(false);
        tab_lokasi.setShowVerticalLines(false);
        tab_lokasi.getTableHeader().setReorderingAllowed(false);
        tab_lokasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_lokasiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tab_lokasi);

        txt_lokasi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_lokasi.setForeground(new java.awt.Color(5, 32, 56));
        txt_lokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lokasiActionPerformed(evt);
            }
        });
        txt_lokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_lokasiKeyPressed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(67, 131, 113));

        lokasi_b_tambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lokasi_b_tambah.setForeground(new java.awt.Color(231, 238, 126));
        lokasi_b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_24px.png"))); // NOI18N
        lokasi_b_tambah.setText("TAMBAH");
        lokasi_b_tambah.setIconTextGap(8);
        lokasi_b_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lokasi_b_tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lokasi_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lokasi_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(162, 34, 39));

        lokasi_b_hapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lokasi_b_hapus.setForeground(new java.awt.Color(231, 238, 126));
        lokasi_b_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_trash_can_24px.png"))); // NOI18N
        lokasi_b_hapus.setText("   HAPUS");
        lokasi_b_hapus.setIconTextGap(8);
        lokasi_b_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lokasi_b_hapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lokasi_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lokasi_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(5, 32, 56));

        lokasi_b_cari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lokasi_b_cari.setForeground(new java.awt.Color(231, 238, 126));
        lokasi_b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        lokasi_b_cari.setText("CARI/REFRESH");
        lokasi_b_cari.setIconTextGap(8);
        lokasi_b_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lokasi_b_cariMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lokasi_b_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lokasi_b_cari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(5, 32, 56));

        lokasi_b_edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lokasi_b_edit.setForeground(new java.awt.Color(231, 238, 126));
        lokasi_b_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        lokasi_b_edit.setText("     EDIT");
        lokasi_b_edit.setIconTextGap(8);
        lokasi_b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lokasi_b_editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lokasi_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lokasi_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p_perusahaan_main2Layout = new javax.swing.GroupLayout(p_perusahaan_main2);
        p_perusahaan_main2.setLayout(p_perusahaan_main2Layout);
        p_perusahaan_main2Layout.setHorizontalGroup(
            p_perusahaan_main2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_perusahaan_main2Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(p_perusahaan_main2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p_perusahaan_main2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(p_perusahaan_main2Layout.createSequentialGroup()
                        .addComponent(txt_lokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))))
        );
        p_perusahaan_main2Layout.setVerticalGroup(
            p_perusahaan_main2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_main2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(p_perusahaan_main2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_lokasi)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_lokasiLayout = new javax.swing.GroupLayout(p_lokasi);
        p_lokasi.setLayout(p_lokasiLayout);
        p_lokasiLayout.setHorizontalGroup(
            p_lokasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_perusahaan_header2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_perusahaan_main2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_lokasiLayout.setVerticalGroup(
            p_lokasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_lokasiLayout.createSequentialGroup()
                .addComponent(p_perusahaan_header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_perusahaan_main2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_lokasi, "p_lokasi");

        p_kategori.setBackground(new java.awt.Color(254, 255, 230));

        p_kategori_header.setBackground(new java.awt.Color(5, 32, 56));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(231, 238, 126));
        jLabel24.setText("MANAGEMENT / KATEGORI BARANG");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(231, 238, 126));
        jLabel25.setText("Tambah, Ubah Dan Hapus Data Kategori Barang Didalam Database Aplikasi ___________");

        javax.swing.GroupLayout p_kategori_headerLayout = new javax.swing.GroupLayout(p_kategori_header);
        p_kategori_header.setLayout(p_kategori_headerLayout);
        p_kategori_headerLayout.setHorizontalGroup(
            p_kategori_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_kategori_headerLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_kategori_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_kategori_headerLayout.setVerticalGroup(
            p_kategori_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_kategori_headerLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_kategori_main.setBackground(new java.awt.Color(254, 255, 230));

        tab_kategori.setBackground(new java.awt.Color(254, 255, 230));
        tab_kategori.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tab_kategori.setForeground(new java.awt.Color(5, 32, 56));
        tab_kategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_kategori.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tab_kategori.setOpaque(false);
        tab_kategori.setRowHeight(25);
        tab_kategori.setSelectionBackground(new java.awt.Color(5, 32, 56));
        tab_kategori.setSelectionForeground(new java.awt.Color(231, 238, 126));
        tab_kategori.setShowHorizontalLines(false);
        tab_kategori.setShowVerticalLines(false);
        tab_kategori.getTableHeader().setReorderingAllowed(false);
        tab_kategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_kategoriMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tab_kategori);

        txt_kategori.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_kategori.setForeground(new java.awt.Color(5, 32, 56));
        txt_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kategoriActionPerformed(evt);
            }
        });
        txt_kategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_kategoriKeyPressed(evt);
            }
        });

        jPanel23.setBackground(new java.awt.Color(67, 131, 113));

        kategori_b_tambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kategori_b_tambah.setForeground(new java.awt.Color(231, 238, 126));
        kategori_b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_24px.png"))); // NOI18N
        kategori_b_tambah.setText("TAMBAH");
        kategori_b_tambah.setIconTextGap(8);
        kategori_b_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kategori_b_tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kategori_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kategori_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel24.setBackground(new java.awt.Color(162, 34, 39));

        kategori_b_hapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kategori_b_hapus.setForeground(new java.awt.Color(231, 238, 126));
        kategori_b_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_trash_can_24px.png"))); // NOI18N
        kategori_b_hapus.setText("   HAPUS");
        kategori_b_hapus.setIconTextGap(8);
        kategori_b_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kategori_b_hapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kategori_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kategori_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel25.setBackground(new java.awt.Color(5, 32, 56));

        kategori_b_cari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kategori_b_cari.setForeground(new java.awt.Color(231, 238, 126));
        kategori_b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        kategori_b_cari.setText("CARI/REFRESH");
        kategori_b_cari.setIconTextGap(8);
        kategori_b_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kategori_b_cariMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kategori_b_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kategori_b_cari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel26.setBackground(new java.awt.Color(5, 32, 56));

        kategori_b_edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kategori_b_edit.setForeground(new java.awt.Color(231, 238, 126));
        kategori_b_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        kategori_b_edit.setText("     EDIT");
        kategori_b_edit.setIconTextGap(8);
        kategori_b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kategori_b_editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kategori_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kategori_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p_kategori_mainLayout = new javax.swing.GroupLayout(p_kategori_main);
        p_kategori_main.setLayout(p_kategori_mainLayout);
        p_kategori_mainLayout.setHorizontalGroup(
            p_kategori_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_kategori_mainLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(p_kategori_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p_kategori_mainLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(p_kategori_mainLayout.createSequentialGroup()
                        .addComponent(txt_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))))
        );
        p_kategori_mainLayout.setVerticalGroup(
            p_kategori_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_kategori_mainLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(p_kategori_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_kategori)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_kategoriLayout = new javax.swing.GroupLayout(p_kategori);
        p_kategori.setLayout(p_kategoriLayout);
        p_kategoriLayout.setHorizontalGroup(
            p_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_kategori_header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_kategori_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_kategoriLayout.setVerticalGroup(
            p_kategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_kategoriLayout.createSequentialGroup()
                .addComponent(p_kategori_header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_kategori_main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_kategori, "p_kategori");

        p_brands.setBackground(new java.awt.Color(254, 255, 230));

        p_perusahaan_header4.setBackground(new java.awt.Color(5, 32, 56));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(231, 238, 126));
        jLabel26.setText("MANAGEMENT / BRANDS & MERK");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(231, 238, 126));
        jLabel27.setText("Tambah, Ubah Dan Hapus Data Brands/Merk Didalam Database Aplikasi ___________");

        javax.swing.GroupLayout p_perusahaan_header4Layout = new javax.swing.GroupLayout(p_perusahaan_header4);
        p_perusahaan_header4.setLayout(p_perusahaan_header4Layout);
        p_perusahaan_header4Layout.setHorizontalGroup(
            p_perusahaan_header4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_perusahaan_header4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_perusahaan_header4Layout.setVerticalGroup(
            p_perusahaan_header4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header4Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_perusahaan_main4.setBackground(new java.awt.Color(254, 255, 230));

        tab_brands.setBackground(new java.awt.Color(254, 255, 230));
        tab_brands.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tab_brands.setForeground(new java.awt.Color(5, 32, 56));
        tab_brands.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_brands.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tab_brands.setOpaque(false);
        tab_brands.setRowHeight(25);
        tab_brands.setSelectionBackground(new java.awt.Color(5, 32, 56));
        tab_brands.setSelectionForeground(new java.awt.Color(231, 238, 126));
        tab_brands.setShowHorizontalLines(false);
        tab_brands.setShowVerticalLines(false);
        tab_brands.getTableHeader().setReorderingAllowed(false);
        tab_brands.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_brandsMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tab_brands);

        txt_brands.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_brands.setForeground(new java.awt.Color(5, 32, 56));
        txt_brands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_brandsActionPerformed(evt);
            }
        });
        txt_brands.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_brandsKeyPressed(evt);
            }
        });

        jPanel27.setBackground(new java.awt.Color(67, 131, 113));

        brands_b_tambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        brands_b_tambah.setForeground(new java.awt.Color(231, 238, 126));
        brands_b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_24px.png"))); // NOI18N
        brands_b_tambah.setText("TAMBAH");
        brands_b_tambah.setIconTextGap(8);
        brands_b_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brands_b_tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(brands_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(brands_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel28.setBackground(new java.awt.Color(162, 34, 39));

        brands_b_hapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        brands_b_hapus.setForeground(new java.awt.Color(231, 238, 126));
        brands_b_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_trash_can_24px.png"))); // NOI18N
        brands_b_hapus.setText("   HAPUS");
        brands_b_hapus.setIconTextGap(8);
        brands_b_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brands_b_hapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(brands_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(brands_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel29.setBackground(new java.awt.Color(5, 32, 56));

        brands_b_cari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        brands_b_cari.setForeground(new java.awt.Color(231, 238, 126));
        brands_b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        brands_b_cari.setText("CARI/REFRESH");
        brands_b_cari.setIconTextGap(8);
        brands_b_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brands_b_cariMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(brands_b_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(brands_b_cari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel30.setBackground(new java.awt.Color(5, 32, 56));

        brands_b_edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        brands_b_edit.setForeground(new java.awt.Color(231, 238, 126));
        brands_b_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        brands_b_edit.setText("     EDIT");
        brands_b_edit.setIconTextGap(8);
        brands_b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brands_b_editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(brands_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(brands_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p_perusahaan_main4Layout = new javax.swing.GroupLayout(p_perusahaan_main4);
        p_perusahaan_main4.setLayout(p_perusahaan_main4Layout);
        p_perusahaan_main4Layout.setHorizontalGroup(
            p_perusahaan_main4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_perusahaan_main4Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(p_perusahaan_main4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p_perusahaan_main4Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(p_perusahaan_main4Layout.createSequentialGroup()
                        .addComponent(txt_brands, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))))
        );
        p_perusahaan_main4Layout.setVerticalGroup(
            p_perusahaan_main4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_main4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(p_perusahaan_main4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_brands)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_brandsLayout = new javax.swing.GroupLayout(p_brands);
        p_brands.setLayout(p_brandsLayout);
        p_brandsLayout.setHorizontalGroup(
            p_brandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_perusahaan_header4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_perusahaan_main4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_brandsLayout.setVerticalGroup(
            p_brandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_brandsLayout.createSequentialGroup()
                .addComponent(p_perusahaan_header4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_perusahaan_main4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_brands, "p_brands");

        p_user.setBackground(new java.awt.Color(254, 255, 230));

        p_perusahaan_header6.setBackground(new java.awt.Color(5, 32, 56));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(231, 238, 126));
        jLabel17.setText("MANAGEMENT / USER");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(231, 238, 126));
        jLabel18.setText("Tambah, Ubah Dan Hapus Data Pengguna Yang Dapat Mengakses Aplikasi ___________");

        javax.swing.GroupLayout p_perusahaan_header6Layout = new javax.swing.GroupLayout(p_perusahaan_header6);
        p_perusahaan_header6.setLayout(p_perusahaan_header6Layout);
        p_perusahaan_header6Layout.setHorizontalGroup(
            p_perusahaan_header6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header6Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_perusahaan_header6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_perusahaan_header6Layout.setVerticalGroup(
            p_perusahaan_header6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header6Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_perusahaan_main6.setBackground(new java.awt.Color(254, 255, 230));

        tab_user.setBackground(new java.awt.Color(254, 255, 230));
        tab_user.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tab_user.setForeground(new java.awt.Color(5, 32, 56));
        tab_user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_user.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tab_user.setOpaque(false);
        tab_user.setRowHeight(25);
        tab_user.setSelectionBackground(new java.awt.Color(5, 32, 56));
        tab_user.setSelectionForeground(new java.awt.Color(231, 238, 126));
        tab_user.setShowHorizontalLines(false);
        tab_user.setShowVerticalLines(false);
        tab_user.getTableHeader().setReorderingAllowed(false);
        tab_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_userMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tab_user);

        txt_user.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_user.setForeground(new java.awt.Color(5, 32, 56));
        txt_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_userActionPerformed(evt);
            }
        });
        txt_user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_userKeyPressed(evt);
            }
        });

        jPanel35.setBackground(new java.awt.Color(67, 131, 113));

        user_b_tambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        user_b_tambah.setForeground(new java.awt.Color(231, 238, 126));
        user_b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_24px.png"))); // NOI18N
        user_b_tambah.setText("TAMBAH");
        user_b_tambah.setIconTextGap(8);
        user_b_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_b_tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(user_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel36.setBackground(new java.awt.Color(162, 34, 39));

        user_b_hapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        user_b_hapus.setForeground(new java.awt.Color(231, 238, 126));
        user_b_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_trash_can_24px.png"))); // NOI18N
        user_b_hapus.setText("   HAPUS");
        user_b_hapus.setIconTextGap(8);
        user_b_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_b_hapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(user_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel37.setBackground(new java.awt.Color(5, 32, 56));

        user_b_cari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        user_b_cari.setForeground(new java.awt.Color(231, 238, 126));
        user_b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        user_b_cari.setText("CARI/REFRESH");
        user_b_cari.setIconTextGap(8);
        user_b_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_b_cariMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(user_b_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_b_cari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel38.setBackground(new java.awt.Color(5, 32, 56));

        user_b_edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        user_b_edit.setForeground(new java.awt.Color(231, 238, 126));
        user_b_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        user_b_edit.setText("     EDIT");
        user_b_edit.setIconTextGap(8);
        user_b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_b_editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(user_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p_perusahaan_main6Layout = new javax.swing.GroupLayout(p_perusahaan_main6);
        p_perusahaan_main6.setLayout(p_perusahaan_main6Layout);
        p_perusahaan_main6Layout.setHorizontalGroup(
            p_perusahaan_main6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_perusahaan_main6Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(p_perusahaan_main6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p_perusahaan_main6Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(p_perusahaan_main6Layout.createSequentialGroup()
                        .addComponent(txt_user, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))))
        );
        p_perusahaan_main6Layout.setVerticalGroup(
            p_perusahaan_main6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_main6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(p_perusahaan_main6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_user)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_userLayout = new javax.swing.GroupLayout(p_user);
        p_user.setLayout(p_userLayout);
        p_userLayout.setHorizontalGroup(
            p_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_perusahaan_header6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_perusahaan_main6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_userLayout.setVerticalGroup(
            p_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_userLayout.createSequentialGroup()
                .addComponent(p_perusahaan_header6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_perusahaan_main6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_user, "p_user");

        p_tipe.setBackground(new java.awt.Color(254, 255, 230));

        p_perusahaan_header5.setBackground(new java.awt.Color(5, 32, 56));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(231, 238, 126));
        jLabel28.setText("MANAGEMENT / TIPE BARANG");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(231, 238, 126));
        jLabel29.setText("Tambah, Ubah Dan Hapus Data Tipe Barang Didalam Database Aplikasi ___________");

        javax.swing.GroupLayout p_perusahaan_header5Layout = new javax.swing.GroupLayout(p_perusahaan_header5);
        p_perusahaan_header5.setLayout(p_perusahaan_header5Layout);
        p_perusahaan_header5Layout.setHorizontalGroup(
            p_perusahaan_header5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_perusahaan_header5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel28))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_perusahaan_header5Layout.setVerticalGroup(
            p_perusahaan_header5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header5Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_perusahaan_main5.setBackground(new java.awt.Color(254, 255, 230));

        tab_tipe.setBackground(new java.awt.Color(254, 255, 230));
        tab_tipe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tab_tipe.setForeground(new java.awt.Color(5, 32, 56));
        tab_tipe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_tipe.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tab_tipe.setOpaque(false);
        tab_tipe.setRowHeight(25);
        tab_tipe.setSelectionBackground(new java.awt.Color(5, 32, 56));
        tab_tipe.setSelectionForeground(new java.awt.Color(231, 238, 126));
        tab_tipe.setShowHorizontalLines(false);
        tab_tipe.setShowVerticalLines(false);
        tab_tipe.getTableHeader().setReorderingAllowed(false);
        tab_tipe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_tipeMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tab_tipe);

        txt_tipe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_tipe.setForeground(new java.awt.Color(5, 32, 56));
        txt_tipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tipeActionPerformed(evt);
            }
        });
        txt_tipe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tipeKeyPressed(evt);
            }
        });

        jPanel31.setBackground(new java.awt.Color(67, 131, 113));

        tipe_b_tambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_tambah.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_24px.png"))); // NOI18N
        tipe_b_tambah.setText("TAMBAH");
        tipe_b_tambah.setIconTextGap(8);
        tipe_b_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel32.setBackground(new java.awt.Color(162, 34, 39));

        tipe_b_hapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_hapus.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_trash_can_24px.png"))); // NOI18N
        tipe_b_hapus.setText("   HAPUS");
        tipe_b_hapus.setIconTextGap(8);
        tipe_b_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_hapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel33.setBackground(new java.awt.Color(5, 32, 56));

        tipe_b_cari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_cari.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        tipe_b_cari.setText("CARI/REFRESH");
        tipe_b_cari.setIconTextGap(8);
        tipe_b_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_cariMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_cari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel34.setBackground(new java.awt.Color(5, 32, 56));

        tipe_b_edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_edit.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        tipe_b_edit.setText("     EDIT");
        tipe_b_edit.setIconTextGap(8);
        tipe_b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p_perusahaan_main5Layout = new javax.swing.GroupLayout(p_perusahaan_main5);
        p_perusahaan_main5.setLayout(p_perusahaan_main5Layout);
        p_perusahaan_main5Layout.setHorizontalGroup(
            p_perusahaan_main5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_perusahaan_main5Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(p_perusahaan_main5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p_perusahaan_main5Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(p_perusahaan_main5Layout.createSequentialGroup()
                        .addComponent(txt_tipe, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))))
        );
        p_perusahaan_main5Layout.setVerticalGroup(
            p_perusahaan_main5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_main5Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(p_perusahaan_main5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_tipe)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_tipeLayout = new javax.swing.GroupLayout(p_tipe);
        p_tipe.setLayout(p_tipeLayout);
        p_tipeLayout.setHorizontalGroup(
            p_tipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_perusahaan_header5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_perusahaan_main5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_tipeLayout.setVerticalGroup(
            p_tipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_tipeLayout.createSequentialGroup()
                .addComponent(p_perusahaan_header5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_perusahaan_main5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_tipe, "p_tipe");

        p_transaksi.setBackground(new java.awt.Color(254, 255, 230));

        p_perusahaan_header7.setBackground(new java.awt.Color(5, 32, 56));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(231, 238, 126));
        jLabel30.setText("TRANSAKSI / DATA BARANG");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(231, 238, 126));
        jLabel31.setText("Tambah, Ubah Dan Hapus Data Barang Didalam Database Aplikasi ___________");

        javax.swing.GroupLayout p_perusahaan_header7Layout = new javax.swing.GroupLayout(p_perusahaan_header7);
        p_perusahaan_header7.setLayout(p_perusahaan_header7Layout);
        p_perusahaan_header7Layout.setHorizontalGroup(
            p_perusahaan_header7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header7Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_perusahaan_header7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_perusahaan_header7Layout.setVerticalGroup(
            p_perusahaan_header7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header7Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_perusahaan_main7.setBackground(new java.awt.Color(254, 255, 230));

        tab_transaksi.setBackground(new java.awt.Color(254, 255, 230));
        tab_transaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tab_transaksi.setForeground(new java.awt.Color(5, 32, 56));
        tab_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_transaksi.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tab_transaksi.setOpaque(false);
        tab_transaksi.setRowHeight(25);
        tab_transaksi.setSelectionBackground(new java.awt.Color(5, 32, 56));
        tab_transaksi.setSelectionForeground(new java.awt.Color(231, 238, 126));
        tab_transaksi.setShowHorizontalLines(false);
        tab_transaksi.setShowVerticalLines(false);
        tab_transaksi.getTableHeader().setReorderingAllowed(false);
        tab_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_transaksiMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tab_transaksi);

        txt_transaksi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_transaksi.setForeground(new java.awt.Color(5, 32, 56));
        txt_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_transaksiActionPerformed(evt);
            }
        });
        txt_transaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_transaksiKeyPressed(evt);
            }
        });

        jPanel39.setBackground(new java.awt.Color(67, 131, 113));

        transaksi_b_tambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        transaksi_b_tambah.setForeground(new java.awt.Color(231, 238, 126));
        transaksi_b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_24px.png"))); // NOI18N
        transaksi_b_tambah.setText("TAMBAH");
        transaksi_b_tambah.setIconTextGap(8);
        transaksi_b_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaksi_b_tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(transaksi_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(transaksi_b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel41.setBackground(new java.awt.Color(5, 32, 56));

        transaksi_b_cari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        transaksi_b_cari.setForeground(new java.awt.Color(231, 238, 126));
        transaksi_b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        transaksi_b_cari.setText("CARI/REFRESH");
        transaksi_b_cari.setIconTextGap(8);
        transaksi_b_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaksi_b_cariMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(transaksi_b_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(transaksi_b_cari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel42.setBackground(new java.awt.Color(5, 32, 56));

        transaksi_b_edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        transaksi_b_edit.setForeground(new java.awt.Color(231, 238, 126));
        transaksi_b_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_24px_1.png"))); // NOI18N
        transaksi_b_edit.setText("     EDIT");
        transaksi_b_edit.setIconTextGap(8);
        transaksi_b_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaksi_b_editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(transaksi_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(transaksi_b_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel43.setBackground(new java.awt.Color(5, 32, 56));

        transaksi_b_edit1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        transaksi_b_edit1.setForeground(new java.awt.Color(231, 238, 126));
        transaksi_b_edit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Medical_History_24px.png"))); // NOI18N
        transaksi_b_edit1.setText(" HISTORY");
        transaksi_b_edit1.setIconTextGap(8);
        transaksi_b_edit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaksi_b_edit1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(transaksi_b_edit1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(transaksi_b_edit1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p_perusahaan_main7Layout = new javax.swing.GroupLayout(p_perusahaan_main7);
        p_perusahaan_main7.setLayout(p_perusahaan_main7Layout);
        p_perusahaan_main7Layout.setHorizontalGroup(
            p_perusahaan_main7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_perusahaan_main7Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(p_perusahaan_main7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p_perusahaan_main7Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(p_perusahaan_main7Layout.createSequentialGroup()
                        .addComponent(txt_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))))
        );
        p_perusahaan_main7Layout.setVerticalGroup(
            p_perusahaan_main7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_main7Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(p_perusahaan_main7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(p_perusahaan_main7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_transaksi)
                        .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_transaksiLayout = new javax.swing.GroupLayout(p_transaksi);
        p_transaksi.setLayout(p_transaksiLayout);
        p_transaksiLayout.setHorizontalGroup(
            p_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_perusahaan_header7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_perusahaan_main7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_transaksiLayout.setVerticalGroup(
            p_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_transaksiLayout.createSequentialGroup()
                .addComponent(p_perusahaan_header7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_perusahaan_main7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_transaksi, "p_transaksi");

        p_reports.setBackground(new java.awt.Color(254, 255, 230));

        p_perusahaan_header8.setBackground(new java.awt.Color(5, 32, 56));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(231, 238, 126));
        jLabel32.setText("R E P O R T S");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(231, 238, 126));
        jLabel33.setText("Mencetak data - data yang sudah terekam masuk kedalam database ___________");

        javax.swing.GroupLayout p_perusahaan_header8Layout = new javax.swing.GroupLayout(p_perusahaan_header8);
        p_perusahaan_header8.setLayout(p_perusahaan_header8Layout);
        p_perusahaan_header8Layout.setHorizontalGroup(
            p_perusahaan_header8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header8Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(p_perusahaan_header8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32))
                .addContainerGap(548, Short.MAX_VALUE))
        );
        p_perusahaan_header8Layout.setVerticalGroup(
            p_perusahaan_header8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_header8Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        p_perusahaan_main8.setBackground(new java.awt.Color(254, 255, 230));

        jPanel2.setBackground(new java.awt.Color(254, 255, 230));

        jPanel3.setBackground(new java.awt.Color(254, 255, 230));

        jLabel35.setBackground(new java.awt.Color(32, 74, 86));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(32, 74, 86));
        jLabel35.setText("CETAK SEMUA DATA BARANG ");

        jPanel47.setBackground(new java.awt.Color(5, 32, 56));

        tipe_b_cari2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_cari2.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_cari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        tipe_b_cari2.setText("C E T A K");
        tipe_b_cari2.setIconTextGap(8);
        tipe_b_cari2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_cari2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_cari2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_cari2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        jLabel36.setBackground(new java.awt.Color(32, 74, 86));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(32, 74, 86));
        jLabel36.setText("CETAK BARANG PER-PERUSAHAAN");

        cb_perusahaan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cb_perusahaan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel48.setBackground(new java.awt.Color(5, 32, 56));

        tipe_b_cari3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_cari3.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_cari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        tipe_b_cari3.setText("C E T A K");
        tipe_b_cari3.setIconTextGap(8);
        tipe_b_cari3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_cari3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_cari3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_cari3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        jLabel38.setBackground(new java.awt.Color(32, 74, 86));
        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(32, 74, 86));
        jLabel38.setText("CETAK BARANG PER-LOKASI");

        cb_lokasi.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cb_lokasi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel50.setBackground(new java.awt.Color(5, 32, 56));

        tipe_b_cari5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_cari5.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_cari5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        tipe_b_cari5.setText("C E T A K");
        tipe_b_cari5.setIconTextGap(8);
        tipe_b_cari5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_cari5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_cari5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_cari5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel39.setBackground(new java.awt.Color(32, 74, 86));
        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(32, 74, 86));
        jLabel39.setText("CETAK BARANG PER-KATEGORI BARANG");

        cb_kategori.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cb_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel51.setBackground(new java.awt.Color(5, 32, 56));

        tipe_b_cari6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_cari6.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_cari6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        tipe_b_cari6.setText("C E T A K");
        tipe_b_cari6.setIconTextGap(8);
        tipe_b_cari6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_cari6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_cari6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_cari6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        cb_merk.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cb_merk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel40.setBackground(new java.awt.Color(32, 74, 86));
        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(32, 74, 86));
        jLabel40.setText("CETAK BARANG PER-MERK BARANG");

        jPanel52.setBackground(new java.awt.Color(5, 32, 56));

        tipe_b_cari7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_cari7.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_cari7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        tipe_b_cari7.setText("C E T A K");
        tipe_b_cari7.setIconTextGap(8);
        tipe_b_cari7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_cari7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_cari7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_cari7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel41.setBackground(new java.awt.Color(32, 74, 86));
        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(32, 74, 86));
        jLabel41.setText("CETAK BARANG PER-VENDOR/SUPPLIER");

        cb_vendor.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cb_vendor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel53.setBackground(new java.awt.Color(5, 32, 56));

        tipe_b_cari8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_cari8.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_cari8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        tipe_b_cari8.setText("C E T A K");
        tipe_b_cari8.setIconTextGap(8);
        tipe_b_cari8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_cari8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_cari8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_cari8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_open_box_95px_2.png"))); // NOI18N

        jLabel34.setBackground(new java.awt.Color(32, 74, 86));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(32, 74, 86));
        jLabel34.setText("B A R A N G ");

        jLabel37.setBackground(new java.awt.Color(32, 74, 86));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(32, 74, 86));
        jLabel37.setText("CETAK BARANG PER-DEPARTMENT");

        cb_perusahaan1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cb_perusahaan1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel49.setBackground(new java.awt.Color(5, 32, 56));

        tipe_b_cari4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_cari4.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_cari4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        tipe_b_cari4.setText("C E T A K");
        tipe_b_cari4.setIconTextGap(8);
        tipe_b_cari4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_cari4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_cari4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_cari4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        cb_department.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cb_department.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Department -" }));
        cb_department.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_departmentMouseClicked(evt);
            }
        });
        cb_department.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_departmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel34)))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_lokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_merk, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_vendor, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_perusahaan1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_department, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(235, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 90, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_perusahaan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(cb_department, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_perusahaan1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_lokasi, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_kategori, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_merk, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_vendor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );

        jPanel54.setBackground(new java.awt.Color(5, 32, 56));

        tipe_b_cari9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipe_b_cari9.setForeground(new java.awt.Color(231, 238, 126));
        tipe_b_cari9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px.png"))); // NOI18N
        tipe_b_cari9.setText("C E T A K QR");
        tipe_b_cari9.setIconTextGap(8);
        tipe_b_cari9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipe_b_cari9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipe_b_cari9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipe_b_cari9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(236, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(217, 217, 217))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout p_perusahaan_main8Layout = new javax.swing.GroupLayout(p_perusahaan_main8);
        p_perusahaan_main8.setLayout(p_perusahaan_main8Layout);
        p_perusahaan_main8Layout.setHorizontalGroup(
            p_perusahaan_main8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_perusahaan_main8Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        p_perusahaan_main8Layout.setVerticalGroup(
            p_perusahaan_main8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_perusahaan_main8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_reportsLayout = new javax.swing.GroupLayout(p_reports);
        p_reports.setLayout(p_reportsLayout);
        p_reportsLayout.setHorizontalGroup(
            p_reportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_perusahaan_header8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p_perusahaan_main8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_reportsLayout.setVerticalGroup(
            p_reportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_reportsLayout.createSequentialGroup()
                .addComponent(p_perusahaan_header8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_perusahaan_main8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        p_utama.add(p_reports, "p_report");

        jPanel1.add(p_utama, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 1120, 740));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menu_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_dataMouseClicked
        // TODO add your handling code here:
            int_data_list=1;
            int_home_list=0;
            int_items_list=0;
            int_trans_list=0;
            int_report_list=0;
            coloring();
            coloring2();
            coloring3();
            coloring4();
            coloring5();
    }//GEN-LAST:event_menu_dataMouseClicked

    private void menu_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_homeMouseClicked
        // TODO add your handling code here:
        
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_home");
   
        
            int_data_list=0;
            int_home_list=1;  
            int_items_list=0;
            int_trans_list=0;
            int_report_list=0;
            coloring();
            coloring2();
            coloring3();
            coloring4();
            coloring5();
    }//GEN-LAST:event_menu_homeMouseClicked

    private void txt_perusahaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_perusahaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_perusahaanActionPerformed

    private void perusahaan_b_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perusahaan_b_tambahMouseClicked
        // TODO add your handling code here:
        reg_company pp = new reg_company();
        pp.setVisible(true);
    }//GEN-LAST:event_perusahaan_b_tambahMouseClicked

    private void perusahaan_b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perusahaan_b_editMouseClicked
        // TODO add your handling code here:
        reg_company pp = new reg_company();
        pp.dataa = this;
        pp.getDataid(id_perusahaan);
        pp.setVisible(true);
    }//GEN-LAST:event_perusahaan_b_editMouseClicked

    private void tab_perusahaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_perusahaanMouseClicked
        // TODO add your handling code here:
        int bar=tab_perusahaan.getSelectedRow();
        String a = tabmode.getValueAt(bar,0).toString();
        id_perusahaan=a;
        
    }//GEN-LAST:event_tab_perusahaanMouseClicked

    private void perusahaan_b_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perusahaan_b_hapusMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        String a = id_perusahaan;
         if (a==null){
            JOptionPane.showMessageDialog(rootPane, "Harap Pilih Data Terlebih Dahulu."); 
        }
         else
         {
              int ok = JOptionPane.showConfirmDialog(null,"Hapus Data "+a+"?","konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from perusahaan where kode='"+a+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data perusahaan berhasil dihapus");
                tab_perusahaan();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog (null,"data gagal dihapus"+e);
            }
         }
         }
       
    }//GEN-LAST:event_perusahaan_b_hapusMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        tab_perusahaan();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void txt_perusahaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_perusahaanKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tab_perusahaan();
        }
    }//GEN-LAST:event_txt_perusahaanKeyPressed

    private void tab_departmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_departmentMouseClicked
        // TODO add your handling code here:
        int bar=tab_department.getSelectedRow();
        String a = tabmode.getValueAt(bar,0).toString();
        id_department=a;
    }//GEN-LAST:event_tab_departmentMouseClicked

    private void txt_departmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_departmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_departmentActionPerformed

    private void txt_departmentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_departmentKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tab_department();
        }
    }//GEN-LAST:event_txt_departmentKeyPressed

    private void department_b_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_department_b_tambahMouseClicked
        // TODO add your handling code here:
        reg_department pp = new reg_department();
        pp.setVisible(true);
    }//GEN-LAST:event_department_b_tambahMouseClicked

    private void department_b_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_department_b_hapusMouseClicked
        // TODO add your handling code here:
        String a = id_department;
        int ok = JOptionPane.showConfirmDialog(null,"Hapus Data "+a+"?","konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from department where kode='"+a+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data department berhasil dihapus");
                tab_department();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog (null,"data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_department_b_hapusMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        tab_department();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void Department_b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Department_b_editMouseClicked
        // TODO add your handling code here:
        reg_department pp = new reg_department();
        //pp.datab = this;
        pp.getDataid(id_department);
        pp.setVisible(true);
    }//GEN-LAST:event_Department_b_editMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_perusahaan");
        tab_perusahaan();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_department");
        tab_department();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void tab_lokasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_lokasiMouseClicked
        // TODO add your handling code here:
        int bar=tab_lokasi.getSelectedRow();
        String a = tabmode.getValueAt(bar,0).toString();
        id_lokasi=a;
    }//GEN-LAST:event_tab_lokasiMouseClicked

    private void txt_lokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lokasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lokasiActionPerformed

    private void txt_lokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lokasiKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tab_lokasi();
        }
    }//GEN-LAST:event_txt_lokasiKeyPressed

    private void lokasi_b_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lokasi_b_tambahMouseClicked
        // TODO add your handling code here:
        reg_lokasi pp = new reg_lokasi();
        pp.setVisible(true);
    }//GEN-LAST:event_lokasi_b_tambahMouseClicked

    private void lokasi_b_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lokasi_b_hapusMouseClicked
        // TODO add your handling code here:
        String a = id_lokasi;
        int ok = JOptionPane.showConfirmDialog(null,"Hapus Data "+a+"?","konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from lokasi where kode='"+a+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data lokasi berhasil dihapus");
                tab_lokasi();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog (null,"data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_lokasi_b_hapusMouseClicked

    private void lokasi_b_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lokasi_b_cariMouseClicked
        // TODO add your handling code here:
        tab_lokasi();
    }//GEN-LAST:event_lokasi_b_cariMouseClicked

    private void lokasi_b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lokasi_b_editMouseClicked
        // TODO add your handling code here:
        reg_lokasi pp = new reg_lokasi();
        pp.getDataid(id_lokasi);
        pp.setVisible(true);
    }//GEN-LAST:event_lokasi_b_editMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_lokasi");
        tab_lokasi();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void tab_vendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_vendorMouseClicked
        // TODO add your handling code here:
        int bar=tab_vendor.getSelectedRow();
        String a = tabmode.getValueAt(bar,0).toString();
        id_vendor=a;
    }//GEN-LAST:event_tab_vendorMouseClicked

    private void txt_vendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vendorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_vendorActionPerformed

    private void txt_vendorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vendorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tab_vendor();
        }
    }//GEN-LAST:event_txt_vendorKeyPressed

    private void vendor_b_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_b_tambahMouseClicked
        // TODO add your handling code here:
        reg_vendor pp = new reg_vendor();
        pp.setVisible(true);
    }//GEN-LAST:event_vendor_b_tambahMouseClicked

    private void vendor_b_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_b_hapusMouseClicked
        // TODO add your handling code here:
        String a = id_vendor;
        if (a==null){
            JOptionPane.showMessageDialog(rootPane, "Harap Pilih Data Terlebih Dahulu."); 
        }
        else {
        int ok = JOptionPane.showConfirmDialog(null,"Hapus Data "+a+"?","konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from vendor where kode='"+a+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data vendor berhasil dihapus");
                tab_lokasi();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog (null,"data gagal dihapus"+e);
            }
        }
        }
        
    }//GEN-LAST:event_vendor_b_hapusMouseClicked

    private void vendor_b_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_b_cariMouseClicked
        // TODO add your handling code here:
        tab_vendor();
    }//GEN-LAST:event_vendor_b_cariMouseClicked

    private void vendor_b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_b_editMouseClicked
        // TODO add your handling code here:
        reg_vendor pp = new reg_vendor();
        pp.getDataid(id_vendor);
        pp.setVisible(true);
    }//GEN-LAST:event_vendor_b_editMouseClicked

    private void jLabel8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel8KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabel8KeyPressed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_vendor");
        tab_vendor();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_kategori");
        tab_kategori();
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_brands");
        tab_brands();
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_tipe");
        tab_tipe();
    }//GEN-LAST:event_jLabel23MouseClicked

    private void menu_itemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_itemsMouseClicked
        // TODO add your handling code here:
            int_data_list=0;
            int_home_list=0;  
            int_items_list=1;
            int_trans_list=0;
            int_report_list=0;
            coloring();
            coloring2();
            coloring3();
            coloring4();
            coloring5();
    }//GEN-LAST:event_menu_itemsMouseClicked

    private void tab_kategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_kategoriMouseClicked
        // TODO add your handling code here:
        int bar=tab_kategori.getSelectedRow();
        String a = tabmode.getValueAt(bar,0).toString();
        id_kategori=a;
    }//GEN-LAST:event_tab_kategoriMouseClicked

    private void txt_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kategoriActionPerformed

    private void txt_kategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kategoriKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tab_kategori();
        }
    }//GEN-LAST:event_txt_kategoriKeyPressed

    private void kategori_b_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategori_b_tambahMouseClicked
        // TODO add your handling code here:
        reg_kategori pp = new reg_kategori();
        pp.setVisible(true);
    }//GEN-LAST:event_kategori_b_tambahMouseClicked

    private void kategori_b_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategori_b_hapusMouseClicked
        // TODO add your handling code here:
        String a = id_kategori;
        int ok = JOptionPane.showConfirmDialog(null,"Hapus Data "+a+"?","konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from kategori where kode='"+a+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data kategori berhasil dihapus");
                tab_kategori();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog (null,"data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_kategori_b_hapusMouseClicked

    private void kategori_b_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategori_b_cariMouseClicked
        // TODO add your handling code here:
        tab_kategori();
    }//GEN-LAST:event_kategori_b_cariMouseClicked

    private void kategori_b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategori_b_editMouseClicked
        // TODO add your handling code here:
        reg_kategori pp = new reg_kategori();
        pp.getDataid(id_kategori);
        pp.setVisible(true);
    }//GEN-LAST:event_kategori_b_editMouseClicked

    private void tab_brandsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_brandsMouseClicked
        // TODO add your handling code here:
        int bar=tab_brands.getSelectedRow();
        String a = tabmode.getValueAt(bar,0).toString();
        id_brands=a;
    }//GEN-LAST:event_tab_brandsMouseClicked

    private void txt_brandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_brandsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_brandsActionPerformed

    private void txt_brandsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_brandsKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tab_brands();
        }
    }//GEN-LAST:event_txt_brandsKeyPressed

    private void brands_b_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brands_b_tambahMouseClicked
        // TODO add your handling code here:
        reg_brands pp = new reg_brands();
        pp.setVisible(true);
    }//GEN-LAST:event_brands_b_tambahMouseClicked

    private void brands_b_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brands_b_hapusMouseClicked
        // TODO add your handling code here:
        String a = id_brands;
        int ok = JOptionPane.showConfirmDialog(null,"Hapus Data "+a+"?","konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from merk where kode='"+a+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data brands/merk berhasil dihapus");
                tab_brands();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog (null,"data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_brands_b_hapusMouseClicked

    private void brands_b_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brands_b_cariMouseClicked
        // TODO add your handling code here:
        tab_brands();
    }//GEN-LAST:event_brands_b_cariMouseClicked

    private void brands_b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brands_b_editMouseClicked
        // TODO add your handling code here:
        reg_brands pp = new reg_brands();
        pp.getDataid(id_brands);
        pp.setVisible(true);
    }//GEN-LAST:event_brands_b_editMouseClicked

    private void tab_tipeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_tipeMouseClicked
        // TODO add your handling code here:
        int bar=tab_tipe.getSelectedRow();
        String a = tabmode.getValueAt(bar,0).toString();
        id_tipe=a;
    }//GEN-LAST:event_tab_tipeMouseClicked

    private void txt_tipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tipeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tipeActionPerformed

    private void txt_tipeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tipeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tab_tipe();
        }
    }//GEN-LAST:event_txt_tipeKeyPressed

    private void tipe_b_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_tambahMouseClicked
        // TODO add your handling code here:
        reg_tipe pp = new reg_tipe();
        pp.setVisible(true);
    }//GEN-LAST:event_tipe_b_tambahMouseClicked

    private void tipe_b_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_hapusMouseClicked
        // TODO add your handling code here:
        String a = id_tipe;
        int ok = JOptionPane.showConfirmDialog(null,"Hapus Data "+a+"?","konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from barang where kode='"+a+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data tipe barang berhasil dihapus");
                tab_tipe();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog (null,"data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_tipe_b_hapusMouseClicked

    private void tipe_b_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_cariMouseClicked
        // TODO add your handling code here:
        tab_tipe();
    }//GEN-LAST:event_tipe_b_cariMouseClicked

    private void tipe_b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_editMouseClicked
        // TODO add your handling code here:
        reg_tipe pp = new reg_tipe();
        pp.getDataid(id_tipe);
        pp.setVisible(true);
    }//GEN-LAST:event_tipe_b_editMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_user");
        tab_user();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10KeyPressed

    private void tab_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_userMouseClicked
        // TODO add your handling code here:
        int bar=tab_user.getSelectedRow();
        String a = tabmode.getValueAt(bar,0).toString();
        id_user=a;
    }//GEN-LAST:event_tab_userMouseClicked

    private void txt_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_userActionPerformed

    private void txt_userKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_userKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tab_user();
        }
    }//GEN-LAST:event_txt_userKeyPressed

    private void user_b_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_b_tambahMouseClicked
        // TODO add your handling code here:
        reg_user pp = new reg_user();
        pp.setVisible(true);
    }//GEN-LAST:event_user_b_tambahMouseClicked

    private void user_b_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_b_hapusMouseClicked
        // TODO add your handling code here:
        String a = id_user;
        int ok = JOptionPane.showConfirmDialog(null,"Hapus Data "+a+"?","konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from user where id='"+a+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data user berhasil dihapus");
                tab_user();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog (null,"data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_user_b_hapusMouseClicked

    private void user_b_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_b_cariMouseClicked
        // TODO add your handling code here:
        tab_user();
    }//GEN-LAST:event_user_b_cariMouseClicked

    private void user_b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_b_editMouseClicked
        // TODO add your handling code here:
        reg_user pp = new reg_user();
        pp.getDataid(id_user);
        pp.setVisible(true);
    }//GEN-LAST:event_user_b_editMouseClicked

    private void tab_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_transaksiMouseClicked
        // TODO add your handling code here:
        int bar=tab_transaksi.getSelectedRow();
        String a = tabmode.getValueAt(bar,0).toString();
        id_trans_barang=a;
    }//GEN-LAST:event_tab_transaksiMouseClicked

    private void txt_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_transaksiActionPerformed

    private void txt_transaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_transaksiKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tab_transaksi();
        }
    }//GEN-LAST:event_txt_transaksiKeyPressed

    private void transaksi_b_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksi_b_tambahMouseClicked
        // TODO add your handling code here:
        trans_barang pp = new trans_barang();
        pp.setVisible(true);
    }//GEN-LAST:event_transaksi_b_tambahMouseClicked

    private void transaksi_b_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksi_b_cariMouseClicked
        // TODO add your handling code here:
        tab_transaksi();
    }//GEN-LAST:event_transaksi_b_cariMouseClicked

    private void transaksi_b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksi_b_editMouseClicked
        // TODO add your handling code here:
        trans_barang pp = new trans_barang();
        pp.getDataid(id_trans_barang);
        pp.setVisible(true);
    }//GEN-LAST:event_transaksi_b_editMouseClicked

    private void menu_trans1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_trans1MouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_transaksi");
        tab_transaksi();
        int_data_list=0;
            int_home_list=0;  
            int_items_list=0;
            int_trans_list=1;
            int_report_list=0;
        coloring();
        coloring2();
        coloring3();
        coloring4();
        coloring5();
    }//GEN-LAST:event_menu_trans1MouseClicked

    private void transaksi_b_edit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksi_b_edit1MouseClicked
        // TODO add your handling code here:
        cetak();
    }//GEN-LAST:event_transaksi_b_edit1MouseClicked

    private void tipe_b_cari2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_cari2MouseClicked
        // TODO add your handling code here:
        cetak_report_all();
    }//GEN-LAST:event_tipe_b_cari2MouseClicked

    private void tipe_b_cari3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_cari3MouseClicked
        // TODO add your handling code here:
        cetak_report_perusahaan();
        
    }//GEN-LAST:event_tipe_b_cari3MouseClicked

    private void tipe_b_cari5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_cari5MouseClicked
        // TODO add your handling code here:
        cetak_report_lokasi();
    }//GEN-LAST:event_tipe_b_cari5MouseClicked

    private void tipe_b_cari6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_cari6MouseClicked
        // TODO add your handling code here:
        cetak_report_kategori();
    }//GEN-LAST:event_tipe_b_cari6MouseClicked

    private void tipe_b_cari7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_cari7MouseClicked
        // TODO add your handling code here:
        cetak_report_merk();
    }//GEN-LAST:event_tipe_b_cari7MouseClicked

    private void tipe_b_cari8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_cari8MouseClicked
        // TODO add your handling code here:
        cetak_report_vendor();
    }//GEN-LAST:event_tipe_b_cari8MouseClicked

    private void menu_reportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_reportMouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)p_utama.getLayout();
        card.show(p_utama, "p_report");
        combo_perusahaan(); combo_lokasi(); combo_kategori(); combo_vendor(); combo_merk(); combo_perusahaan1(); 
            int_data_list=0;
            int_home_list=0;  
            int_items_list=0;
            int_trans_list=0;
            int_report_list=1;
        coloring();
        coloring2();
        coloring3();
        coloring4();
        coloring5();
    }//GEN-LAST:event_menu_reportMouseClicked

    private void tipe_b_cari4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_cari4MouseClicked
        // TODO add your handling code here:
        cetak_report_department();
    }//GEN-LAST:event_tipe_b_cari4MouseClicked

    private void cb_departmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_departmentMouseClicked
        // TODO add your handling code here:
  combo_department();
    }//GEN-LAST:event_cb_departmentMouseClicked

    private void cb_departmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_departmentActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cb_departmentActionPerformed

    private void b_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_editMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b_editMouseClicked

    private void p_perusahaan_main3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_perusahaan_main3MouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_p_perusahaan_main3MouseExited

    private void p_vendorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_vendorMouseExited
        // TODO add your handling code here:
        tab_vendor();
        id_vendor=null;
    }//GEN-LAST:event_p_vendorMouseExited

    private void p_perusahaanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_perusahaanMouseExited
        // TODO add your handling code here:
        tab_perusahaan();
        id_perusahaan=null;
    }//GEN-LAST:event_p_perusahaanMouseExited

    private void p_perusahaan_mainMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_perusahaan_mainMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_p_perusahaan_mainMouseExited

    private void tipe_b_cari9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipe_b_cari9MouseClicked
        // TODO add your handling code here:
        cetak_qr pp = new cetak_qr();
        pp.setVisible(true);
    }//GEN-LAST:event_tipe_b_cari9MouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Department_b_edit;
    private javax.swing.JPanel b_edit;
    private javax.swing.JLabel brands_b_cari;
    private javax.swing.JLabel brands_b_edit;
    private javax.swing.JLabel brands_b_hapus;
    private javax.swing.JLabel brands_b_tambah;
    private javax.swing.JComboBox<String> cb_department;
    private javax.swing.JComboBox<String> cb_kategori;
    private javax.swing.JComboBox<String> cb_lokasi;
    private javax.swing.JComboBox<String> cb_merk;
    private javax.swing.JComboBox<String> cb_perusahaan;
    private javax.swing.JComboBox<String> cb_perusahaan1;
    private javax.swing.JComboBox<String> cb_vendor;
    private javax.swing.JLabel department_b_hapus;
    private javax.swing.JLabel department_b_tambah;
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
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel kategori_b_cari;
    private javax.swing.JLabel kategori_b_edit;
    private javax.swing.JLabel kategori_b_hapus;
    private javax.swing.JLabel kategori_b_tambah;
    private javax.swing.JLabel lokasi_b_cari;
    private javax.swing.JLabel lokasi_b_edit;
    private javax.swing.JLabel lokasi_b_hapus;
    private javax.swing.JLabel lokasi_b_tambah;
    private javax.swing.JPanel menu_1;
    private javax.swing.JPanel menu_2;
    private javax.swing.JPanel menu_3;
    private javax.swing.JPanel menu_4;
    private javax.swing.JPanel menu_5;
    private javax.swing.JLabel menu_data;
    private javax.swing.JPanel menu_data_list;
    private javax.swing.JLabel menu_home;
    private javax.swing.JLabel menu_items;
    private javax.swing.JPanel menu_items_list;
    private javax.swing.JLabel menu_report;
    private javax.swing.JLabel menu_trans1;
    private javax.swing.JPanel p_brands;
    private javax.swing.JPanel p_department;
    private javax.swing.JPanel p_home;
    private javax.swing.JPanel p_kategori;
    private javax.swing.JPanel p_kategori_header;
    private javax.swing.JPanel p_kategori_main;
    private javax.swing.JPanel p_lokasi;
    private javax.swing.JPanel p_perusahaan;
    private javax.swing.JPanel p_perusahaan_header;
    private javax.swing.JPanel p_perusahaan_header1;
    private javax.swing.JPanel p_perusahaan_header2;
    private javax.swing.JPanel p_perusahaan_header3;
    private javax.swing.JPanel p_perusahaan_header4;
    private javax.swing.JPanel p_perusahaan_header5;
    private javax.swing.JPanel p_perusahaan_header6;
    private javax.swing.JPanel p_perusahaan_header7;
    private javax.swing.JPanel p_perusahaan_header8;
    private javax.swing.JPanel p_perusahaan_header9;
    private javax.swing.JPanel p_perusahaan_main;
    private javax.swing.JPanel p_perusahaan_main1;
    private javax.swing.JPanel p_perusahaan_main2;
    private javax.swing.JPanel p_perusahaan_main3;
    private javax.swing.JPanel p_perusahaan_main4;
    private javax.swing.JPanel p_perusahaan_main5;
    private javax.swing.JPanel p_perusahaan_main6;
    private javax.swing.JPanel p_perusahaan_main7;
    private javax.swing.JPanel p_perusahaan_main8;
    private javax.swing.JPanel p_perusahaan_main9;
    private javax.swing.JPanel p_reports;
    private javax.swing.JPanel p_sidebar;
    private javax.swing.JPanel p_tipe;
    private javax.swing.JPanel p_transaksi;
    private javax.swing.JPanel p_user;
    private javax.swing.JPanel p_utama;
    private javax.swing.JPanel p_vendor;
    private javax.swing.JLabel perusahaan_b_edit;
    private javax.swing.JLabel perusahaan_b_hapus;
    private javax.swing.JLabel perusahaan_b_tambah;
    private javax.swing.JTable tab_brands;
    private javax.swing.JTable tab_department;
    private javax.swing.JTable tab_kategori;
    private javax.swing.JTable tab_lokasi;
    private javax.swing.JTable tab_perusahaan;
    private javax.swing.JTable tab_tipe;
    private javax.swing.JTable tab_transaksi;
    private javax.swing.JTable tab_user;
    private javax.swing.JTable tab_vendor;
    private javax.swing.JLabel tipe_b_cari;
    private javax.swing.JLabel tipe_b_cari2;
    private javax.swing.JLabel tipe_b_cari3;
    private javax.swing.JLabel tipe_b_cari4;
    private javax.swing.JLabel tipe_b_cari5;
    private javax.swing.JLabel tipe_b_cari6;
    private javax.swing.JLabel tipe_b_cari7;
    private javax.swing.JLabel tipe_b_cari8;
    private javax.swing.JLabel tipe_b_cari9;
    private javax.swing.JLabel tipe_b_edit;
    private javax.swing.JLabel tipe_b_hapus;
    private javax.swing.JLabel tipe_b_tambah;
    private javax.swing.JLabel transaksi_b_cari;
    private javax.swing.JLabel transaksi_b_edit;
    private javax.swing.JLabel transaksi_b_edit1;
    private javax.swing.JLabel transaksi_b_tambah;
    private javax.swing.JTextField txt_brands;
    private javax.swing.JTextField txt_department;
    private javax.swing.JTextField txt_kategori;
    private javax.swing.JTextField txt_lokasi;
    private javax.swing.JTextField txt_perusahaan;
    private javax.swing.JTextField txt_tipe;
    private javax.swing.JTextField txt_transaksi;
    private javax.swing.JTextField txt_user;
    private javax.swing.JTextField txt_vendor;
    private javax.swing.JLabel user_b_cari;
    private javax.swing.JLabel user_b_edit;
    private javax.swing.JLabel user_b_hapus;
    private javax.swing.JLabel user_b_tambah;
    private javax.swing.JLabel vendor_b_cari;
    private javax.swing.JLabel vendor_b_edit;
    private javax.swing.JLabel vendor_b_hapus;
    private javax.swing.JLabel vendor_b_tambah;
    // End of variables declaration//GEN-END:variables
}
