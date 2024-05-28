/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekakhir;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Model {
    String DBurl = "jdbc:mysql://localhost/testing";
    String DBUsername = "root";
    String DBPassword = "";
    Connection koneksi;
    Statement statement;

    public Model() {
        
           try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(DBurl, DBUsername, DBPassword);
            System.out.println("Koneksi Berhasil");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }
    }
    



    
    public String[][] BukuList(){ 
                
        
    try{
            int jmlData = 0;
            
            String data[][] = new String[getBanyakData()][3];
            
            String query = "Select * from Buku"; 
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[jmlData][0] = resultSet.getString("judul");
                data[jmlData][1] = resultSet.getString("jumlah");                
                data[jmlData][2] = resultSet.getString("harga");
                jmlData++;
            }
            return data;
               
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }

public int getBanyakData(){
        int jmlData = 0;
        try{
            statement = koneksi.createStatement();
            String query = "Select * from buku";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){ 
                jmlData++;
            }
            return jmlData;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }

public void beliCos(String judul, int jumlah, int harga, int jumlahBeli){
    int jmlData=0;
        double newJumlah = jumlah - jumlahBeli;
        double total = harga * jumlahBeli; 
        try {
           String query = "Select * from buku WHERE judul= '" + judul + "' "; 
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()){ 
                jmlData++;
            }
           
             if (jmlData==1) {
                query = "UPDATE buku SET jumlah ='" + newJumlah + "' WHERE judul='" + judul +"'";
                statement = (Statement) koneksi.createStatement();
                statement.executeUpdate(query); //execute querynya
                System.out.println("Berhasil diupdate");
                JOptionPane.showMessageDialog(null, "Berhasil Membeli dengan total harga :  Rp" + total);     
             }
             else {
                 JOptionPane.showMessageDialog(null, "Data Tidak Ada");
             }
           
        } catch (Exception sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }

}
 
public void tambahData(String judul, String jumlah, String Harga){
 int jmlData=0;

     double djumlah =Double.parseDouble(jumlah);
     double dharga = Double.parseDouble(Harga);
        try {
           String query = "Select * from buku WHERE judul = '" + judul + "' "; 
           System.out.println(judul + " " + djumlah + " " + dharga);
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()){ 
                jmlData++;
            }
            
            if (jmlData==0) {
                query = "INSERT INTO buku VALUES('"+judul+"','"+djumlah+"','"+dharga+"')";
           
                statement = (Statement) koneksi.createStatement();
                statement.executeUpdate(query); //execute querynya
                System.out.println("Berhasil ditambahkan");
                JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
            }
            else {
                JOptionPane.showMessageDialog(null, "Data sudah ada");
            }
        } catch (Exception sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    

}

public void editData(String judul, String jumlah, String harga){
int jmlData=0;
     double djumlah=Double.parseDouble(jumlah);
     double dharga=Double.parseDouble(harga);

        try {
           String query = "Select * from buku WHERE judul= '" + judul + "' "; 
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()){ 
                jmlData++;
            }
           
             if (jmlData==1) {
                query = "UPDATE buku SET jumlah = '" + djumlah + "', harga = '" + dharga +"' WHERE judul= '" + judul +"'";
                statement = (Statement) koneksi.createStatement();
                statement.executeUpdate(query); //execute querynya
                System.out.println("Berhasil diupdate");
                JOptionPane.showMessageDialog(null, "Data Berhasil diupdate");
             }
             else {
                 JOptionPane.showMessageDialog(null, "Data Tidak Ada");
             }
           
        } catch (Exception sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    
    
}

public void deleteData(String judul) {
        try{
            String query = "DELETE FROM buku WHERE judul = '"+judul+"'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus");
            
        }catch(SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }


}