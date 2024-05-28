/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekakhir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author LENOVO
 */
public class Connector {
    
    String DBurl = "jdbc:mysql://localhost/testing";
    String DBUsername = "root";
    String DBPassword = "";
    Connection koneksi;
    Statement statement;
    
    public Connector() {
        try{
                Class.forName("com.mysql.jdbc.Driver");
                koneksi = (Connection) DriverManager.getConnection(DBurl, DBUsername, DBPassword);
                System.out.println("Koneksi Berhasil");
        }catch(Exception ex){
            System.out.println("Koneksi Gagal");
        }
        
        
        
    }
    
    
    
}   