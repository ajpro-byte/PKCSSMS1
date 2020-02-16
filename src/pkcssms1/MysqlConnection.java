/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkcssms1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author use
 */
public class MysqlConnection {
     Connection conn=null;
    public static Connection ConnectDB(){
                      try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:Mysql://localhost:3307/db_inventory","root","1234");
  //JOptionPane.showMessageDialog(null,"connected");
            return conn;
            
        }
        catch(ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            return null;
            
        }
    }
}
