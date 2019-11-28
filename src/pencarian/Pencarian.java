/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pencarian;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author adib
 */
public class Pencarian {
    static final String jdbc = "com.mysql.jdbc.Driver";
    static final String db = "jdbc:mysql://localhost/uts_pemrograman_database";
    static final String user = "root";
    static final String pass = "";
    static Connection con;
    static Statement st;
    static ResultSet rs;
    static String[] hcari = new String[10];
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        koneksi();
        try {
            tampil();
            pencarian();
            hapus();
        } catch (Exception e) {
        }
    }
    
    static void koneksi(){
        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(db,user,pass);
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("salah");
        }
    }
    
    static void pencarian(){
        System.out.println("pencariannya");
            String cari = JOptionPane.showInputDialog("pencarian");
            String sql = "select * from produk where nama_produk like '%"+cari+"%'";
        try {
             rs = st.executeQuery(sql);
            
             int no=1;
            while(rs.next()){
                System.out.print(no+"   |");
                 System.out.print(rs.getString(2)+"   |");
                System.out.println(rs.getString(3));
                hcari[no]=rs.getString(1);
                no++;
            }
            
        } catch (Exception e) {
        }
    }
    static void hapus(){
        String pcari = JOptionPane.showInputDialog("hapus data");
        
        try {
        if(hcari[Integer.valueOf(pcari)]==null){
            System.out.println("data tidak valid");
        }else{
        String sql_del = "delete from produk where id="+hcari[Integer.valueOf(pcari)];   
            PreparedStatement p = con.prepareStatement(sql_del);
            p.execute();
            System.out.println("berhasil");
        }
        } catch (Exception e) {
            System.out.println(hcari[Integer.valueOf(pcari)]);
        }
        
    }
    
    static void tampil(){
        System.out.println("datanya");
    String sql ="select * from produk";
        try {
            rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.print(rs.getString(1)+"  |");
                System.out.print(rs.getString(2)+"   |");
                System.out.println(rs.getString(3));
                
            }
            System.out.println("------------------------------------------");
        } catch (Exception e) {
        }
    
    }
    
}
