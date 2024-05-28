/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekakhir;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class CostumerScreen extends JFrame{
    
    JLabel lTitle = new JLabel("Pembelian Buku");
    JLabel lJudul = new JLabel("Judul");
    JLabel theJudul= new JLabel("");
    JLabel lJbeli = new JLabel("Jumlah Beli");
    JLabel lJbayar = new JLabel("Jumlah Bayar");
    JLabel theJbayar = new JLabel("");
    JTextField tfJbeli = new JTextField();
    
    JTable tabel;
    DefaultTableModel dtm;
    JScrollPane scrollPane;
    Object namaKolom[] = {"Judul", "Jumlah Stock", "Harga"};
    
    JButton btnLogin = new JButton("Login Admin");
    JButton btnUpdate = new JButton("Beli");

    public CostumerScreen () {
        dtm = new DefaultTableModel(namaKolom, 0);
        tabel = new JTable(dtm);
        scrollPane = new JScrollPane(tabel);
        
        setTitle("Data Pembelian Buku");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(550, 600);
        
        add(lTitle);
        lTitle.setBounds(225, 5, 100, 50);
        
        add(scrollPane);
        scrollPane.setBounds(20, 50, 500, 300);
        
        add(btnLogin);
        btnLogin.setBounds(20, 350, 200, 20);
        
        add(lJudul);
        lJudul.setBounds(150, 400, 200, 20);
        
        add(theJudul);
        theJudul.setBounds(135, 425, 200, 20);
        
        add(lJbeli);
        lJbeli.setBounds(300, 400, 200, 20);
        
        add(tfJbeli);
        tfJbeli.setBounds(285, 425, 100, 20);
        
        add(btnUpdate);
        btnUpdate.setBounds(200, 475, 100, 20);
    }

    
       
         public String getJumlah(){
        return tfJbeli.getText();
    }
    
    
}