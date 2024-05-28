/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekakhir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author LENOVO
 */
public class Controller {
     CostumerScreen CostumerScreen;
     Model Model;
     Login Login = new Login();
     Register Register = new Register();
     AdminScreen AdminScreen = new AdminScreen();
     Connector konek = new Connector();
     
     private String dataNamaCos;
     private int hargaCos;
     private int jumCos;
     private String dataNamaAdm;

    public Controller(CostumerScreen CostumerScreen, Model Model) {
        Register.setVisible(false);
        Login.setVisible(false);
        AdminScreen.setVisible(false);
        Login.setLocationRelativeTo(null);
        Register.setLocationRelativeTo(null);
        AdminScreen.setLocationRelativeTo(null);
        this.CostumerScreen = CostumerScreen;
        this.Model = Model;
        
        CostumerScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
        CostumerScreen.setLocationRelativeTo(null);
        AdminScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Login.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        if (Model.getBanyakData()!=0) {
            String data[][] = Model.BukuList();
            CostumerScreen.tabel.setModel((new JTable(data, CostumerScreen.namaKolom)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "Belum Ada Buku");
        }
        
        
        CostumerScreen.btnLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                CostumerScreen.setVisible(false);
                Login.setVisible(true);
                
            }
        
        });
        
        AdminScreen.RegBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                AdminScreen.setVisible(false);
                Register.setVisible(true);
            }
        
        });
        
        AdminScreen.btnBack.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                AdminScreen.setVisible(false);
                String dataBuku[][] = Model.BukuList();
                CostumerScreen.tabel.setModel((new JTable(dataBuku, CostumerScreen.namaKolom)).getModel());
                CostumerScreen.setVisible(true);
            }
        
        });
        
        
        Login.LogBtn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
          try{
              if(Login.getInUser() == null || Login.getInUser().trim().isEmpty()){
              throw new IllegalArgumentException("Username is empty");
              }
              if(Login.getInPass() == null || Login.getInPass().trim().isEmpty()){
              throw new IllegalArgumentException("Password is empty");
              }
              
               String query = "SELECT password FROM operator WHERE username = '"+ Login.getInUser() +"'";
               konek.statement = konek.koneksi.createStatement();
               ResultSet result = konek.statement.executeQuery(query);
               if(result.next()){
                   String pw = result.getString("password");
                   if(!pw.equals(Login.getInPass())){
                       JOptionPane.showMessageDialog(new JFrame(), "Password Salah");
                   }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Berhasil Login");
                        Login.setVisible(false);
                        Login.InPass.setText("");
                        Login.InUser.setText("");
                        String dataBuku[][] = Model.BukuList();
                        AdminScreen.tabel.setModel((new JTable(dataBuku, AdminScreen.namaKolom)).getModel());
                        AdminScreen.setVisible(true);
                        if (Model.getBanyakData()!=0) {
                        String data[][] = Model.BukuList();
                         AdminScreen.tabel.setModel((new JTable(data, AdminScreen.namaKolom)).getModel());
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Belum Ada Buku");
                          }
                   }
               }else{
                   JOptionPane.showMessageDialog(new JFrame(), "Username Tidak Ditemukan");
               }
               konek.statement.close();
               
          }catch(Exception error){
              JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
        
        
        }
         }
        });
        
        Login.BBtn.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {
          Login.setVisible(false);
          Login.InPass.setText("");
          Login.InUser.setText("");
          CostumerScreen.setVisible(true);
      }     
        
        });
        
        Register.RegBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
              if(Register.getInRegU() == null || Register.getInRegU().trim().isEmpty()){
              throw new IllegalArgumentException("Username is empty");
              }
              if(Register.getInRegP() == null || Register.getInRegP().trim().isEmpty()){
              throw new IllegalArgumentException("Password is empty");
              }
              
               String query = "SELECT username FROM operator WHERE username = '"+ Register.getInRegU() +"'";
               konek.statement = konek.koneksi.createStatement();
               ResultSet result = konek.statement.executeQuery(query);
               if(result.next()){
                   JOptionPane.showMessageDialog(new JFrame(), "Username Telah Digunakan");
               }else{
                   String InQuery = "INSERT INTO operator (username, password) VALUES ('"+Register.getInRegU()+"', '" + Register.getInRegP()+ "')";
                   konek.statement = konek.koneksi.createStatement();
                   konek.statement.executeUpdate(InQuery);
                   JOptionPane.showMessageDialog(new JFrame(), "Register Berhasil");
               }
               konek.statement.close();
               
          }catch(Exception error){
              JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
          }
        }
        });
        
        Register.BBtn.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {
          Register.setVisible(false);
          Register.InRegP.setText("");
          Register.InRegU.setText("");
          AdminScreen.setVisible(true);
      }     
        
        });
        
        CostumerScreen.btnUpdate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String judul = dataNamaCos;
                int jumlah = jumCos;
                int harga = hargaCos;
                int jumlahBeli = Integer.parseInt(CostumerScreen.getJumlah());
                
                Model.beliCos(judul, jumlah, harga, jumlahBeli);        
                String dataBuku[][] = Model.BukuList();
                CostumerScreen.tabel.setModel((new JTable(dataBuku, CostumerScreen.namaKolom)).getModel());
            }
     
        });
        
        CostumerScreen.tabel.addMouseListener(new MouseAdapter(){    
        public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int baris = CostumerScreen.tabel.getSelectedRow();

                dataNamaCos = CostumerScreen.tabel.getValueAt(baris, 0).toString();
                jumCos = Integer.parseInt(CostumerScreen.tabel.getValueAt(baris, 1).toString());
                hargaCos= Integer.parseInt(CostumerScreen.tabel.getValueAt(baris,2).toString());
                    
                CostumerScreen.theJudul.setText(dataNamaCos);
        }
        
    
    });
        
      AdminScreen.tabel.addMouseListener(new MouseAdapter(){    
        public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int baris = AdminScreen.tabel.getSelectedRow();

                dataNamaAdm = AdminScreen.tabel.getValueAt(baris, 0).toString();
    }
        
    });
      
      AdminScreen.btnAdd.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent ae) {
                String judul = AdminScreen.getTheJudul();
                String jumlah = AdminScreen.getTfJumlah();
                String harga = AdminScreen.getTheHarga();
                Model.tambahData(judul, jumlah, harga);
                
            String dataBuku[][] = Model.BukuList();
            AdminScreen.tabel.setModel((new JTable(dataBuku, AdminScreen.namaKolom)).getModel());
            }
          
      });
      
      AdminScreen.btnUpdate.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent ae) {
                String judul = AdminScreen.getTheJudul();
                String jumlah = AdminScreen.getTfJumlah();
                String harga = AdminScreen.getTheHarga();
                Model.editData(judul, jumlah, harga);
                
            String dataBuku[][] = Model.BukuList();
            AdminScreen.tabel.setModel((new JTable(dataBuku, AdminScreen.namaKolom)).getModel());
            }
          
      });
      
      AdminScreen.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Hapus Buku " + dataNamaAdm + "?", "Pilih : ", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    Model.deleteData(dataNamaAdm);
                     String dataBuku[][] = Model.BukuList();
                     AdminScreen.tabel.setModel((new JTable(dataBuku, AdminScreen.namaKolom)).getModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                }
            }
        });
    
}
}