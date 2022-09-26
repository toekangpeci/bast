/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import groovyjarjarantlr.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import show.cetak_qr;
import java.util.HashMap;


/**
 *
 * @author PAKUAN-IT
 */
public class sql {
    


    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO = Charset.forName("ISO-8859-1");
    public String a;
    
    
    
    public void getPara(String para) {
        show.cetak_qr sikil = new show.cetak_qr();        
     //   sikil.para = this;
        a=para;
        System.out.println(para);
        System.out.println(a);

    }
    
    show.cetak_qr sikil=new show.cetak_qr();
    
    public String para_kode= "SELECT\n" +
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
"     INNER JOIN `barang` barang ON trans_barang.`kode_barang` = barang.`kode` \n"+
"      where trans_barang.kode in ("+a+");";
   
     public String select_all= "SELECT\n" +
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
"     INNER JOIN `barang` barang ON trans_barang.`kode_barang` = barang.`kode`";
      
     
     
     
            
             
      
 
     
//    byte[] bytes = a.getBytes(StandardCharsets.UTF_8);
//
//    public String select_all = new String(bytes, StandardCharsets.UTF_8);
       

    

    
}
