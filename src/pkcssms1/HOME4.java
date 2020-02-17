/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkcssms1;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author User
 */
public class HOME4 extends javax.swing.JFrame {

    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form HOME4
     */
    
    public HOME4() {
        initComponents();
          
conn = MysqlConnection.ConnectDB();
        pnl_hide();
        seticon();
        refresh_item();
        CurrentDate();
        empCount();
        //remove table header
        tbl_cus.getTableHeader().setUI(null);
        tbl_cus.setBackground(Color.white);
        jTable1.getTableHeader().setUI(null);
        jTable1.setBackground(Color.white);
        jTable2.getTableHeader().setUI(null);
        jTable2.setBackground(Color.white);
        //disable table edit
        tbl_customer.setDefaultEditor(Object.class, null);
        tbl_item.setDefaultEditor(Object.class, null);
        tbl_supplier.setDefaultEditor(Object.class, null);
        tbl_cashier.setDefaultEditor(Object.class, null);
        tbl_sales.setDefaultEditor(Object.class, null);
        jTable2.setDefaultEditor(Object.class, null);
        AutoCompleteDecorator.decorate(jComboBox2);
    }
    
    
     public void dashboard(){
        //product list
        refresh_item();
        empCount();
        //supplier list
        refresh_suppliers();
        supplierCount();
        //daily product sales
//        try{
//            String sql = "SELECT * FROM tbl_sales WHERE Date like ?";
//            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
//            pst.setString(1, "%" + s_date.getText()+ "%");
//            rs = (ResultSet) pst.executeQuery();
//            tbl_sales.setModel(DbUtils.resultSetToTableModel(rs));
//            d_sale_price.setText(Double.toString(total_report_Sales()));
//            d_sales.setText(Integer.toString(total_sales_summary()));
//            }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
        refresh_sales();
        Daily_transation();
        
            //daily dump product sales
            try{
            String sql = "SELECT * FROM tbl_dump_sale WHERE Date like ?";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_dump_sale.setModel(DbUtils.resultSetToTableModel(rs));
            d_dump_price.setText(Double.toString(total_dump_price()));
            d_dump_pur.setText(Integer.toString(total_dump_qty()));
            }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
             
             //daily product return
             try{
            String sql = "SELECT * FROM tbl_return_history WHERE Date like ?";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));
            returnCount();
            jLabel167.setText(Integer.toString(dashboard_return_qty()));
            }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
             
             //daily dump product
             try{
             String sql = "SELECT * FROM tbl_dump WHERE Date like ?";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_dump.setModel(DbUtils.resultSetToTableModel(rs));
            dumpCount();
            d_sales.setText(Integer.toString(dashboard_dump_qty()));
            }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
    }
    
public void setColor_req(JPanel panel)
{
panel.setBackground(new java.awt.Color(0,204,102));
}
public void resetColor_req(JPanel panel)
{
panel.setBackground(new java.awt.Color(255,255,255));
}
  
 private void supplierCount() {
        int row = tbl_supplier.getRowCount();
        sup_count.setText(String.valueOf(row));
        d_supplier.setText(String.valueOf(row));
    }

public void refresh_supplier()
{
try{
        String sql="SELECT DISTINCT Supplier FROM tbl_supplier Where Status='" + "Active"+ "' ";
        pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while(rs.next()){
        String name =rs.getString("Supplier");
        p_sup.addItem(name);
        }
        }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }

}
    public void count_request(){
      try {
            String sql = "SELECT COUNT(*) AS total FROM tbl_stock_request";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
             rs = pst.executeQuery();
        while(rs.next()){
        int count =rs.getInt("total");
        if (count==0){
        jLabel85.setText("");
       resetColor_req(request);
        }else{
        setColor_req(request);
        jLabel85.setText(String.valueOf(count));
        }
        }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    
    }
    
    
 public void pnl_hide(){
       login.setVisible(true);
       HomeMenu.setVisible(false);
    }
    
          String imgpath = null;
    public void CurrentDate() {

        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("h:mm aa");
                SimpleDateFormat sid = new SimpleDateFormat("hmmss");
                s_time.setText(s.format(d));
                
                SimpleDateFormat st = new SimpleDateFormat("MMM-dd-yyyy");
                SimpleDateFormat stid = new SimpleDateFormat("MMMddyyyy");
                SimpleDateFormat stt = new SimpleDateFormat("MMM/yyyy");
                SimpleDateFormat sttt = new SimpleDateFormat("yyyy");
                //yearnow.setText(sttt.format(d));
               // monthnow.setText(stt.format(d));
                s_date.setText(st.format(d));
                id.setText(sid.format(d)+stid.format(d));
                s_month.setText(stt.format(d));
                s_year.setText(sttt.format(d));
                
                
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.S aa");
//                String formattedDate = dateFormat.format(new Date()).toString();
//                System.out.println(formattedDate);
            }
        }).start();

    }
    private void empCount() {
        int row = tbl_item.getRowCount();
        prd_count.setText(String.valueOf(row));
        d_prd.setText(String.valueOf(row));
    }
    private void returnCount() {
        int row = tbl_purchase_list.getRowCount();
        d_return.setText(String.valueOf(row));
    }
    private void dumpCount() {
        int row = tbl_dump.getRowCount();
        d_dump.setText(String.valueOf(row));
    }
    
     private void customerCount() {
        int row = tbl_customer.getRowCount();
        cus_count.setText(String.valueOf(row));
    }
    
     public void refresh_item() {
         
        try {
            
            String sql = "SELECT ProductID,ProductDescription,Quantity,OriginalPrice,ValuesSRP,Sale,Supplier FROM tbl_items ORDER BY ProductID DESC ";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tbl_item.setModel(DbUtils.resultSetToTableModel(rs));

            //refresh category
            String sqll = "SELECT DISTINCT ProductName FROM tbl_items ORDER BY ProductID DESC ";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sqll);
            rs = pst.executeQuery();
            tbl_itemcategory.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
    
     
      public void refresh_suppliers() {

        try {

            String sql = "SELECT * FROM tbl_supplier ORDER BY SupplierID DESC";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_supplier.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
     public void refresh_stock_request() {

        try {

            String sql = "SELECT ProductID,ProductDescription,Quantity,OriginalPrice,ValuesSRP,Sale,Supplier FROM tbl_stock_request ORDER BY ProductID DESC";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tbl_item.setModel(DbUtils.resultSetToTableModel(rs));
            String sql1 = "SELECT ProductCategory FROM tbl_stock_request ORDER BY ProductID DESC";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            tbl_itemcategory.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
     public void refresh_cashier() {

        try {

            String sql = "SELECT * FROM tbl_cashier";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_cashier.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
      public void refresh_customer() {

        try {

            String sql = "SELECT Fullname,CustomerStatus FROM tbl_customer where CommitmentStatus = '" +"Active"+ "'";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_cus.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
     public void refresh_admin() {

        try {

            String sql = "SELECT AccountID,Name,Username,Address,ContactNo,Status FROM tbl_admin ";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_admin.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
     public void refresh_purcshse_list() {

        try {

            String sql = "SELECT * FROM tbl_customer_purchase ORDER BY Date DESC";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
      public void refresh_dump_item() {

        try {

            String sql = "SELECT * FROM tbl_dump ORDER BY Date DESC";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_dump.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
      
      
     public void refresh_dump_sale() {

        try {

            String sql = "SELECT * FROM tbl_dump_sale";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_dump_sale.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
      
      
     public void refresh_purcshse_history() {

        try {

            String sql = "SELECT * FROM tbl_return_history ORDER BY Date  DESC";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
     public void refresh_user() {

        try {

            String sql = "SELECT AccountID,Name,Username,Address,ContactNo,Status FROM tbl_user ";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_user.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
    
     

      public void refresh_sales() {

        try {

            String sql = "SELECT ProductID,ProductName,ProductDescription,Price,Quantity,TotalPrice,Date,Time,StaffName FROM tbl_sales Time ORDER BY Date DESC";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_sales.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
    
       public void refresh_logs() {
        try {
            //DateAdded,Supplier,ProductID,Description,Category,Quantity,Value,Price,Sale
            String sql = "SELECT * FROM tbl_login_history ORDER BY DateLogin DESC";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_loginhis.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
       public void loggedin(){
            
        try {
            String sql = "SELECT * FROM tbl_admin where Username =? and Password =? and Type=?";
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, txtusername.getText());
            pst.setString(2, txtpassword.getText());
             pst.setString(3, (String) type1.getSelectedItem());
            
            rs = pst.executeQuery();
            if (rs.next()) {
                
                //admin Login history          
                try{
                    
                     pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_admin WHERE Username= '" + txtusername.getText() + "' and Password= '" + txtpassword.getText() + "' ");
            rs = pst.executeQuery();
            if (rs.next()) {
                String add3 = rs.getString("AccountID");
                accid.setText(add3);
                String add4 = rs.getString("Name");
                user.setText(add4);
                String add5 = rs.getString("type");
                acctype.setText(add5);
                            //adding admin history
                 try{
                 String sql1 = "Insert into tbl_login_history (AccountID, Name, Username, Type, DateLogin, TimeLogin) values (?,?,?,?,?,?)";
               
               pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);
               
               pst.setString(1, add3);
               pst.setString(2, add4);
               pst.setString(3, txtusername.getText());
               pst.setString(4, (String) type1.getSelectedItem());
               pst.setString(5, s_date.getText());
               pst.setString(6, s_time.getText());
               
               pst.execute();
              // JOptionPane.showMessageDialog(null, "New Admin Login History Recorded");
             
                }catch(SQLException x) {
                    JOptionPane.showMessageDialog(null, x);
                }

                   //end of rs.next
                       }else{
            JOptionPane.showMessageDialog(null, "No Admin Data Found!!!");
            }
            
            //end of admin search
                }catch(SQLException x) {
                    JOptionPane.showMessageDialog(null, x);
                }
                        
                       
                         JOptionPane.showMessageDialog(null, "Admin Logged In!");
                        
                        
        login.setVisible(false);
        HomeMenu.setVisible(true);
        Sales.setVisible(false);
        Stocks.setVisible(false);
        
        Supplier.setVisible(false);
        
        ItemList.setVisible(false);
        dashboard.setVisible(true);
         
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
         count_request();
         dashboard();
         jLabel94.setText("");
          resetColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
        setColor_manage(pnl_dashboard);
                         
                   //this.dispose();
            } else {

                try {
                       
                    String sql1 = "SELECT * FROM tbl_user where Username =? and Password =? and Type=?";
                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                    pst.setString(1, txtusername.getText());
                    pst.setString(2, txtpassword.getText());
                    pst.setString(3, (String) type1.getSelectedItem());
                   

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        
                        
                          //user Login history          
                try{
                    
                     pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_user WHERE Username= '" + txtusername.getText() + "' and Password= '" + txtpassword.getText() + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                String add3 = rs.getString("AccountID");
                accid.setText(add3);
                String add4 = rs.getString("Name");
                user.setText(add4);
                String add5 = rs.getString("type");
                acctype.setText(add5);
            //adding admin history
                 try{
                 String sql2 = "Insert tbl_login_history (AccountID, Name, Username, Type, DateLogin, TimeLogin) values (?,?,?,?,?,?)";
               
               pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql2);
               
               pst.setString(1, add3);
               pst.setString(2, add4);
               pst.setString(3, txtusername.getText());
               pst.setString(4, (String) type1.getSelectedItem());
               pst.setString(5, s_date.getText());
               pst.setString(6, s_time.getText());
               
               pst.execute();
               //JOptionPane.showMessageDialog(null, "New User Login History Recorded");
             
                }catch(SQLException x) {
                    JOptionPane.showMessageDialog(null, x);
                }
                        
            }//end of if rs.next
            //end try catch of try user log history
            }catch(SQLException x) {
                    JOptionPane.showMessageDialog(null, x);
                }
                  
                     
                         
                         
                         try{
                       
                         JOptionPane.showMessageDialog(null, "User Logged In!");
                         }catch(Exception e){
                         }
                         login.setVisible(false);
        HomeMenu.setVisible(true);
        Sales.setVisible(false);
        Stocks.setVisible(false);
        ItemList.setVisible(false);
        dashboard.setVisible(true);
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
        resetColor_req(request);
        jLabel85.setText("");
        //count_request();
        jLabel94.setText("");
        dashboard();
        resetColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
        setColor_manage(pnl_dashboard);
                                           } else {
                       
                         try{
                        
                         JOptionPane.showMessageDialog(null, "Incorect ID or Password!", "WARNING!", JOptionPane.ERROR_MESSAGE);
                         }catch(Exception e){
                         }

                    }
                } catch (SQLException x) {
                    JOptionPane.showMessageDialog(null, x);
                }
            }
        } catch (SQLException x) {
            JOptionPane.showMessageDialog(null, x);
        }

    }
    
      
      public void refresh_tbladmin() {

        try {

            String sql = "SELECT AccountId,Name,Username,Address,ContactNo,Type,Status FROM tbl_admin";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_admin.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
      
      public void refresh_customerlist() {

        try {

            String sql = "SELECT * FROM tbl_customer";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_customer.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
      
     public void refresh_tbluser() {

        try {
            String sql = "SELECT AccountID,Name,Username,Address,ContactNo,Type,Status FROM tbl_user";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tbl_user.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     //count
     
    public int total_qty() {
        int rowscount = tbl_cashier.getRowCount();
        int sum = 0;
        for (int i = 0; i < rowscount; i++) {
            sum = sum + Integer.parseInt(tbl_cashier.getValueAt(i, 4).toString());
        }
        return sum;

    }
    public int total_dump_qty() {
        int rowscount = tbl_dump_sale.getRowCount();
        int sum = 0;
        for (int i = 0; i < rowscount; i++) {
            sum = sum + Integer.parseInt(tbl_dump_sale.getValueAt(i, 5).toString());
        }
        return sum;

    }
    
     public double total_price() {
        double total=0;
                for (int e=0; e<tbl_cashier.getRowCount();e++)
                {
                    double amount= Double.parseDouble((String) tbl_cashier.getValueAt( e, 5).toString());
                    total+=amount;
                }
                totprice.setText(String.valueOf(total));
        return total;
     }
     
     public double total_dump_price() {
        double total=0;
                for (int e=0; e<tbl_dump_sale.getRowCount();e++)
                {
                    double amount= Double.parseDouble((String) tbl_dump_sale.getValueAt( e, 6).toString());
                    total+=amount;
                }
                dump_tprice.setText(String.valueOf(total));
                d_dump_price.setText(String.valueOf(total));
        return total;
     }
     
     public int total_bqty() {
        int rowscount = tbl_cashier.getRowCount();
        int sum = 0;
        for (int i = 0; i < rowscount; i++) {
            sum = sum + Integer.parseInt(tbl_cashier.getValueAt(i, 4).toString());
        }
        return sum;

    }
     public double total_bprice() {
        double total=0;
                for (int e=0; e<tbl_cashier.getRowCount();e++)
                {
                    double amount= Double.parseDouble((String) tbl_cashier.getValueAt( e, 3).toString());
                    total+=amount;
                }
                totprice.setText(String.valueOf(total));
        return total;
     }
     
     //clear
     public void clear_ans(){
    na1.setText("");
    na2.setText("");
    na3.setText("");
    }
     public void changepass_clear(){
    cuname.setText("");
    ccpass.setText("");
    cnpass.setText("");
    crpass.setText("");
    } 
     public void user_clear(){
    AID.setText("[Click to Generate ID]");
    name.setText("");
    Uname.setText("");
    pword.setText("");
    rpword.setText("");
    add.setText("");
    con.setText("");
    a1.setText("");
    a2.setText("");
    a3.setText("");
    atype.setSelectedItem("-Select Type-");
    q1.setSelectedItem("- Select Question -");
    q2.setSelectedItem("- Select Question -");
    q3.setSelectedItem("- Select Question -");
//      uimage.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(uimage.getWidth(), uimage.getHeight(), Image.SCALE_DEFAULT)));
//    A_Profile.setText("Browse");
    }

     public void item_clear(){
         p_id.setText("[Click To Auto Generate ID]");
         p_name.setText("");
         p_des.setText("");
         p_cat.setText("");
         p_quan.setText("");
         p_org.setText("");
         p_val.setText("");
         p_sale.setText("");
         p_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(p_image.getWidth(), p_image.getHeight(), Image.SCALE_DEFAULT)));
         p_image.setText("Select Image");
          refresh_supplier();
     }
   
 public void clear_supplier(){
 sup_id.setText("");
        supp_name.setText("");
        supp_address.setText("");
        supp_contact.setText("");
        sup_remarks.setText("");
        sup_email.setText("");
 }
     
 public void clear_customer(){
        cus_id.setText("");
        cus_name.setText("");
        cus_age.setText("");
        cus_street.setText("");
        cus_city.setText("");
        cus_contact.setText("");
        cus_email.setText("");
        cus_walkin.setSelected(false);
        cus_regular.setSelected(false);
        buttonGroup1.clearSelection();
 }
 
 public void dump_clear(){
   //jTextField6.setText("");
        d_id.setText("");
        d_name.setText("");
        d_des.setText("");
        d_cat.setText("");
        d_qty.setText("");
        d_qty1.setText("");
        d_price.setText("");
        d_total.setText("0.00");
 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        accid = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        acctype = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        HomeMenu = new javax.swing.JPanel();
        Menu = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        pnl_stock = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnl_sales = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pnl_transactions = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pnl_logs = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        pnl_accounts = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        pnl_logout = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        pnl_report = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        pnl_dashboard = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        Dashboard = new javax.swing.JPanel();
        Logs = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_loginhis = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        Accounts = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel48 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jLabel49 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabel68 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        addaccount = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        AID = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        Uname = new javax.swing.JTextField();
        pword = new javax.swing.JPasswordField();
        rpword = new javax.swing.JPasswordField();
        atype = new javax.swing.JComboBox();
        add = new javax.swing.JTextField();
        con = new javax.swing.JTextField();
        contact2 = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        password1 = new javax.swing.JLabel();
        hdgfd = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        contact5 = new javax.swing.JLabel();
        q1 = new javax.swing.JComboBox();
        a1 = new javax.swing.JTextField();
        contact7 = new javax.swing.JLabel();
        q2 = new javax.swing.JComboBox();
        a2 = new javax.swing.JTextField();
        contact12 = new javax.swing.JLabel();
        q3 = new javax.swing.JComboBox();
        a3 = new javax.swing.JTextField();
        contact10 = new javax.swing.JLabel();
        contact11 = new javax.swing.JLabel();
        contact9 = new javax.swing.JLabel();
        contact8 = new javax.swing.JLabel();
        contact3 = new javax.swing.JLabel();
        contact6 = new javax.swing.JLabel();
        aupdate = new javax.swing.JButton();
        aclear = new javax.swing.JButton();
        accountlist = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_user = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_admin = new javax.swing.JTable();
        jLabel53 = new javax.swing.JLabel();
        auclear = new javax.swing.JButton();
        aact = new javax.swing.JButton();
        uact = new javax.swing.JButton();
        uuclear = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        adminid = new javax.swing.JLabel();
        manageacc = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        cuname = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        ccpass = new javax.swing.JPasswordField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        crpass = new javax.swing.JPasswordField();
        cnpass = new javax.swing.JPasswordField();
        contact17 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        nq1 = new javax.swing.JLabel();
        contact20 = new javax.swing.JLabel();
        contact19 = new javax.swing.JLabel();
        na1 = new javax.swing.JTextField();
        contact14 = new javax.swing.JLabel();
        nq2 = new javax.swing.JLabel();
        contact16 = new javax.swing.JLabel();
        contact13 = new javax.swing.JLabel();
        na2 = new javax.swing.JTextField();
        contact21 = new javax.swing.JLabel();
        nq3 = new javax.swing.JLabel();
        contact24 = new javax.swing.JLabel();
        contact23 = new javax.swing.JLabel();
        na3 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        Report = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_sales = new javax.swing.JTable();
        jLabel70 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        yearly = new javax.swing.JCheckBox();
        daily = new javax.swing.JCheckBox();
        monthly = new javax.swing.JCheckBox();
        report_item = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        report_sales = new javax.swing.JLabel();
        rdel = new javax.swing.JLabel();
        r_date = new com.toedter.calendar.JDateChooser();
        jLabel135 = new javax.swing.JLabel();
        ItemList = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_item = new javax.swing.JTable();
        i_search1 = new javax.swing.JTextField();
        st_prdnew = new javax.swing.JButton();
        prd_count = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        request = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbl_itemcategory = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox();
        Stocks = new javax.swing.JPanel();
        pnl_cat_sel = new java.awt.Panel();
        pnl_cat_search = new java.awt.Panel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton17 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel141 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel36 = new javax.swing.JPanel();
        p_mes_header = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        p_id = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        p_name = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        p_des = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        p_cat = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        p_quan = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        p_org = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        p_val = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        p_sale = new javax.swing.JTextField();
        p_add = new javax.swing.JButton();
        p_clear = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        p_image = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        p_sup = new javax.swing.JComboBox<String>();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        p_cb = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        Supplier = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        supp_search = new javax.swing.JTextField();
        sup_count = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        pnl_supp_update = new java.awt.Panel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        sup_id = new javax.swing.JTextField();
        supp_name = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        supp_address = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        sup_email = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        sup_remarks = new javax.swing.JTextArea();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        supp_contact = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_supplier = new javax.swing.JTable();
        Sales = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_cashier = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jLabel119 = new javax.swing.JLabel();
        totprice = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        totqty = new javax.swing.JTextField();
        s_cash = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        s_change = new javax.swing.JTextField();
        s_pnlprd = new javax.swing.JPanel();
        s_barcode = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        s_stck = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        s_total = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        s_des = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        s_price = new javax.swing.JTextField();
        s_add = new javax.swing.JButton();
        s_qty = new javax.swing.JTextField();
        s_prd = new javax.swing.JTextField();
        s_eq = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        v_id = new javax.swing.JLabel();
        s_year = new javax.swing.JLabel();
        s_month = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        s_search = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        pnl_cus1 = new java.awt.Panel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_cus = new javax.swing.JTable();
        pnl_cus = new java.awt.Panel();
        v_date = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        Transactions = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl_purchase_list = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        today = new javax.swing.JCheckBox();
        jLabel114 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel130 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jLabel144 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        d_id = new javax.swing.JLabel();
        d_name = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        d_des = new javax.swing.JLabel();
        d_cat = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        d_qty = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        d_price = new javax.swing.JTextField();
        d_qty1 = new javax.swing.JTextField();
        jLabel139 = new javax.swing.JLabel();
        d_total = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        d_qty2 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        Dump_list = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbl_dump = new javax.swing.JTable();
        Dump_Sale = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbl_dump_sale = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        jLabel128 = new javax.swing.JLabel();
        dump_tprice = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        dump_tqty = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        today_sold = new javax.swing.JCheckBox();
        jLabel151 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        dashboard = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel149 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel148 = new javax.swing.JLabel();
        d_prd = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel154 = new javax.swing.JLabel();
        d_return = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel157 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel158 = new javax.swing.JLabel();
        d_supplier = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        d_dump_price = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel163 = new javax.swing.JLabel();
        d_dump_pur = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel168 = new javax.swing.JLabel();
        d_dump = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        d_dump_qty = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        d_sale_price = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel174 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        d_sales = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        customer = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        cus_count = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        pnl_cus_update = new java.awt.Panel();
        jButton9 = new javax.swing.JButton();
        cus_email = new javax.swing.JTextField();
        cus_id = new javax.swing.JTextField();
        cus_age = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        cus_name = new javax.swing.JTextField();
        cus_street = new javax.swing.JTextField();
        cus_city = new javax.swing.JTextField();
        cus_contact = new javax.swing.JTextField();
        cus_gender = new javax.swing.JComboBox<String>();
        jLabel77 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        cus_regular = new javax.swing.JCheckBox();
        cus_walkin = new javax.swing.JCheckBox();
        jButton10 = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_customer = new javax.swing.JTable();
        login = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        type1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        jLabel44 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        s_time = new javax.swing.JLabel();
        s_date = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel134 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sales & Supplies Inventory System");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 110));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/user.png"))); // NOI18N
        jToolBar1.add(jLabel11);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setText("  User's Name:  ");
        jToolBar1.add(jLabel17);

        user.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        user.setForeground(new java.awt.Color(0, 0, 102));
        user.setText("--------------------");
        jToolBar1.add(user);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 102));
        jLabel15.setText("  AccountID:  ");
        jToolBar1.add(jLabel15);

        accid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        accid.setForeground(new java.awt.Color(0, 0, 102));
        accid.setText("---------------------");
        jToolBar1.add(accid);

        jLabel107.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(0, 0, 102));
        jLabel107.setText("  Account Type:  ");
        jToolBar1.add(jLabel107);

        acctype.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        acctype.setForeground(new java.awt.Color(0, 0, 102));
        acctype.setText("--------------------");
        jToolBar1.add(acctype);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 708, 1240, 30));

        jPanel1.setLayout(new java.awt.CardLayout());

        HomeMenu.setMinimumSize(new java.awt.Dimension(1240, 460));
        HomeMenu.setName(""); // NOI18N
        HomeMenu.setPreferredSize(new java.awt.Dimension(1240, 460));
        HomeMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu.setBackground(new java.awt.Color(255, 255, 255));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Menu.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1240, 10));

        pnl_stock.setBackground(new java.awt.Color(255, 255, 255));
        pnl_stock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_stock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_stockMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_stockMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_stockMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnl_stockMousePressed(evt);
            }
        });
        pnl_stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pnl_stockKeyReleased(evt);
            }
        });
        pnl_stock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_stock.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 49, 70, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Stocks");
        pnl_stock.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 100, 30));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/full-items-inside-a-shopping-bag.png"))); // NOI18N
        pnl_stock.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 100, 30));

        Menu.add(pnl_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 100, 70));

        pnl_sales.setBackground(new java.awt.Color(255, 255, 255));
        pnl_sales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_sales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_salesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_salesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_salesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnl_salesMousePressed(evt);
            }
        });
        pnl_sales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_sales.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Sales");
        pnl_sales.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 41, 100, 30));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/shopping-cart.png"))); // NOI18N
        pnl_sales.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, 30));

        Menu.add(pnl_sales, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 100, 70));

        pnl_transactions.setBackground(new java.awt.Color(255, 255, 255));
        pnl_transactions.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_transactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_transactionsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_transactionsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_transactionsMouseExited(evt);
            }
        });
        pnl_transactions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_transactions.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 90, -1));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Transactions");
        pnl_transactions.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, 90, 28));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/bankbook.png"))); // NOI18N
        pnl_transactions.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, -1));

        Menu.add(pnl_transactions, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 110, 70));

        pnl_logs.setBackground(new java.awt.Color(255, 255, 255));
        pnl_logs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_logs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_logsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_logsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_logsMouseExited(evt);
            }
        });
        pnl_logs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pnl_logsKeyReleased(evt);
            }
        });
        pnl_logs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_logs.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, -1));

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Logs");
        pnl_logs.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 90, 28));

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/list.png"))); // NOI18N
        pnl_logs.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, 4, 100, 40));

        Menu.add(pnl_logs, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 90, 70));

        pnl_accounts.setBackground(new java.awt.Color(255, 255, 255));
        pnl_accounts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_accounts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_accountsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_accountsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_accountsMouseExited(evt);
            }
        });
        pnl_accounts.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Accounts");
        pnl_accounts.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 100, 28));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_accounts.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, -1));

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/settings.png"))); // NOI18N
        pnl_accounts.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 100, -1));

        Menu.add(pnl_accounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 100, 70));

        pnl_logout.setBackground(new java.awt.Color(255, 255, 255));
        pnl_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_logoutMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnl_logoutMousePressed(evt);
            }
        });
        pnl_logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Logout");
        pnl_logout.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 90, 28));

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_logout.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, -1));

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/012-insert-memory-card.png"))); // NOI18N
        pnl_logout.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 4, 80, 40));

        Menu.add(pnl_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 90, 70));

        pnl_report.setBackground(new java.awt.Color(255, 255, 255));
        pnl_report.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_reportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_reportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_reportMouseExited(evt);
            }
        });
        pnl_report.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel66.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Report");
        pnl_report.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 100, 28));

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_report.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, -1));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/sale-report.png"))); // NOI18N
        pnl_report.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, -1));

        Menu.add(pnl_report, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 100, 70));

        pnl_dashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnl_dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_dashboardMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnl_dashboardMousePressed(evt);
            }
        });
        pnl_dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel137.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel137.setText("Dashboard");
        pnl_dashboard.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 100, 30));

        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel140.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/statistics.png"))); // NOI18N
        pnl_dashboard.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, 30));

        Menu.add(pnl_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, 70));

        HomeMenu.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 80));

        Dashboard.setLayout(new java.awt.CardLayout());

        Logs.setBackground(new java.awt.Color(255, 255, 255));
        Logs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                LogsKeyReleased(evt);
            }
        });
        Logs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_loginhis.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_loginhis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_loginhisKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_loginhis);

        Logs.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 950, 430));

        jLabel65.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel65.setText("Account Logs");
        Logs.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 40));

        Dashboard.add(Logs, "card5");

        Accounts.setBackground(new java.awt.Color(255, 255, 255));
        Accounts.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setFloatable(false);
        jToolBar2.setToolTipText("");
        jToolBar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToolBar2MouseClicked(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("  Add Account  ");
        jLabel48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });
        jToolBar2.add(jLabel48);
        jToolBar2.add(jSeparator4);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("  Account List   ");
        jLabel49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });
        jToolBar2.add(jLabel49);
        jToolBar2.add(jSeparator5);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText("  Manage Account");
        jLabel68.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel68MouseClicked(evt);
            }
        });
        jToolBar2.add(jLabel68);

        Accounts.add(jToolBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 30));

        jPanel15.setLayout(new java.awt.CardLayout());

        addaccount.setBackground(new java.awt.Color(255, 255, 255));
        addaccount.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel56.setText("Add Account");
        addaccount.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 160, 29));

        AID.setEditable(false);
        AID.setBackground(new java.awt.Color(255, 255, 255));
        AID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AID.setText("[Click to Generate ID]");
        AID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AIDMouseClicked(evt);
            }
        });
        addaccount.add(AID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 300, 30));

        name.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addaccount.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 300, 30));

        Uname.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addaccount.add(Uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 300, 30));
        addaccount.add(pword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 300, 30));
        addaccount.add(rpword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 300, 30));

        atype.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        atype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Select Type-", "Admin", "User" }));
        addaccount.add(atype, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 300, 30));

        add.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addaccount.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 300, 30));

        con.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addaccount.add(con, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 300, 30));

        contact2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact2.setText("Contact:");
        addaccount.add(contact2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, -1, 20));

        address.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        address.setText("Address:");
        addaccount.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, -1, 20));

        password.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        password.setText("Repeat Password:");
        addaccount.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, 20));

        type.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        type.setText("Type:");
        addaccount.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        password1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        password1.setText("Password:");
        addaccount.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, 20));

        hdgfd.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        hdgfd.setText("Username:");
        addaccount.add(hdgfd, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jLabel51.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel51.setText("Name:");
        addaccount.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));

        jLabel52.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel52.setText("AccountID:");
        addaccount.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        contact.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        contact.setForeground(new java.awt.Color(51, 51, 51));
        contact.setText("Account Security");
        addaccount.add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, -1, 40));

        contact5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact5.setText("Personal Verification Question 1");
        addaccount.add(contact5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, -1, 20));

        q1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        q1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select Question -", "What is your favorite color?", "What is your pet's name?", "What is your favorite foor?", "What is your favorite movie?", "What is your first girlfriend's/boyfriend's name?", "What is your favorite song?", "What is your favorite book?", "What is your favorite city?", "What is your favorite state?" }));
        addaccount.add(q1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 370, 30));

        a1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        addaccount.add(a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 370, 30));

        contact7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact7.setText("Personal Verification Question 2");
        addaccount.add(contact7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, -1, 20));

        q2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        q2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select Question -", "What is your childhood nickname?", "What is the name of your favorite childhood friend?", "What is the name of your first teacher?", "Who is your favorite historical person?", "What's the name of a college you applied to but didn't attend?", "What year did you graduate from high school?", "When is your parents wedding anniversary?", "What counrty  you always dream of vacationing in?" }));
        addaccount.add(q2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, 370, 30));

        a2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        addaccount.add(a2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 260, 370, 30));

        contact12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact12.setText("Personal Verification Question 3");
        addaccount.add(contact12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, -1, 20));

        q3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        q3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select Question -", "What was the name of your first school?", "What's the name of the hosipital in which you were born?", "What's the nickname of your oldest child?", "What is the middle name of your father?", "What is the name of your favorite childhood cuddly toy?", "Who was your first roommate?", "What is the maiden name of your grandmother?", "What is your favorite childhood programme?" }));
        addaccount.add(q3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 370, 30));

        a3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        addaccount.add(a3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 370, 30));

        contact10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact10.setText("Answere:");
        addaccount.add(contact10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 370, -1, 30));

        contact11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact11.setText("Question:");
        addaccount.add(contact11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, -1, 30));

        contact9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact9.setText("Answere:");
        addaccount.add(contact9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, -1, 30));

        contact8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact8.setText("Question:");
        addaccount.add(contact8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, -1, 30));

        contact3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact3.setText("Answere:");
        addaccount.add(contact3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, -1, 30));

        contact6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact6.setText("Question:");
        addaccount.add(contact6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, 30));

        aupdate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        aupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/list.png"))); // NOI18N
        aupdate.setText("Save");
        aupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aupdateActionPerformed(evt);
            }
        });
        addaccount.add(aupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 410, 130, 30));

        aclear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        aclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/eraser.png"))); // NOI18N
        aclear.setText("Clear");
        aclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aclearActionPerformed(evt);
            }
        });
        addaccount.add(aclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(899, 410, 130, 30));

        jPanel15.add(addaccount, "card2");

        accountlist.setBackground(new java.awt.Color(255, 255, 255));
        accountlist.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_user.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_userMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_user);

        accountlist.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 97, 600, 370));

        tbl_admin.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_adminMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_admin);

        accountlist.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 610, 370));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel53.setText("USER ACCOUNT");
        accountlist.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 70, -1, 20));

        auclear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        auclear.setText("Clear Selection");
        accountlist.add(auclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 120, -1));

        aact.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        aact.setText("Activate");
        aact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aactActionPerformed(evt);
            }
        });
        accountlist.add(aact, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, -1));

        uact.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        uact.setText("Activate");
        uact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uactActionPerformed(evt);
            }
        });
        accountlist.add(uact, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 70, 100, -1));

        uuclear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        uuclear.setText("Clear Selection");
        uuclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uuclearActionPerformed(evt);
            }
        });
        accountlist.add(uuclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 70, 120, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel54.setText("ACCOUNT LIST");
        accountlist.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel55.setText("ADMINISTRATOR ACCOUNT");
        accountlist.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        adminid.setForeground(new java.awt.Color(255, 255, 255));
        accountlist.add(adminid, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 190, 20));

        jPanel15.add(accountlist, "card3");

        manageacc.setBackground(new java.awt.Color(255, 255, 255));
        manageacc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel92.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(0, 51, 102));
        jLabel92.setText("Change Password");
        manageacc.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(51, 51, 51));
        jLabel88.setText("A strong password helps your account more secure.");
        manageacc.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, 20));

        cuname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        manageacc.add(cuname, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 220, -1));

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setText("Username:");
        manageacc.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setText("Current Password:");
        manageacc.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        ccpass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        manageacc.add(ccpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 220, -1));

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel89.setText("New Password:");
        manageacc.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel90.setText("Repeat Password:");
        manageacc.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        crpass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        manageacc.add(crpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 220, -1));

        cnpass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        manageacc.add(cnpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 220, -1));

        contact17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact17.setForeground(new java.awt.Color(102, 102, 102));
        contact17.setText("Personal Verification Question 1");
        manageacc.add(contact17, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, 20));

        jLabel86.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 51, 102));
        jLabel86.setText("Update Account Security");
        manageacc.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));

        nq1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nq1.setText("Question");
        manageacc.add(nq1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, -1, 20));

        contact20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact20.setText("Question:");
        manageacc.add(contact20, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, -1, 20));

        contact19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact19.setText("Answere:");
        manageacc.add(contact19, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, -1, 20));

        na1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        manageacc.add(na1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 400, -1));

        contact14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact14.setForeground(new java.awt.Color(102, 102, 102));
        contact14.setText("Personal Verification Question 2");
        manageacc.add(contact14, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, -1, 20));

        nq2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nq2.setText("Question");
        manageacc.add(nq2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, -1, 20));

        contact16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact16.setText("Question:");
        manageacc.add(contact16, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, -1, 20));

        contact13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact13.setText("Answere:");
        manageacc.add(contact13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, -1, 20));

        na2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        manageacc.add(na2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 400, -1));

        contact21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact21.setForeground(new java.awt.Color(102, 102, 102));
        contact21.setText("Personal Verification Question 3");
        manageacc.add(contact21, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, -1, 20));

        nq3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nq3.setText("Question");
        manageacc.add(nq3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 260, -1, 20));

        contact24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact24.setText("Question:");
        manageacc.add(contact24, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, -1, 20));

        contact23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contact23.setText("Answere:");
        manageacc.add(contact23, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, -1, 20));

        na3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        manageacc.add(na3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, 400, -1));

        jButton18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/reload.png"))); // NOI18N
        jButton18.setText("Save");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        manageacc.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 100, 30));

        jButton19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/eraser.png"))); // NOI18N
        jButton19.setText("Clear");
        manageacc.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 110, 30));

        jButton20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/eraser.png"))); // NOI18N
        jButton20.setText("Clear");
        manageacc.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 330, 120, 30));

        jButton21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/reload.png"))); // NOI18N
        jButton21.setText("Save");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        manageacc.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, 110, 30));

        jPanel15.add(manageacc, "card4");

        Accounts.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1240, 470));

        Dashboard.add(Accounts, "card6");

        Report.setBackground(new java.awt.Color(255, 255, 255));
        Report.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_sales.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_sales.setUpdateSelectionOnSort(false);
        jScrollPane7.setViewportView(tbl_sales);

        jPanel17.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1220, 340));

        jLabel70.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel70.setText("SALES REPORT");
        jPanel17.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 30));

        jButton22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton22.setText("Filter");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, 30));

        yearly.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        yearly.setText("  YEARLY  ");
        yearly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearlyActionPerformed(evt);
            }
        });
        jPanel17.add(yearly, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, -1, 30));

        daily.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        daily.setText("  DAILY  ");
        daily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dailyActionPerformed(evt);
            }
        });
        jPanel17.add(daily, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, 30));

        monthly.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        monthly.setText("  MONTHLY  ");
        monthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthlyActionPerformed(evt);
            }
        });
        jPanel17.add(monthly, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, -1, 30));

        report_item.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        report_item.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        report_item.setText("00");
        jPanel17.add(report_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 430, 60, 30));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel72.setText("TOTAL ITEM");
        jPanel17.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 430, -1, 30));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel73.setText("TOTAL SALES");
        jPanel17.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 430, -1, 30));

        report_sales.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        report_sales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        report_sales.setText("00");
        jPanel17.add(report_sales, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 430, 170, 30));

        rdel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel17.add(rdel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 190, 10));

        r_date.setDateFormatString("MMM-dd-yyyy");
        jPanel17.add(r_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 20, 300, 30));

        jLabel135.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel135.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer(1).png"))); // NOI18N
        jLabel135.setText(" PRINT REPORT");
        jLabel135.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel135.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel135MouseClicked(evt);
            }
        });
        jPanel17.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 130, 50));

        jTabbedPane2.addTab("   Sales Report   ", jPanel17);

        Report.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 500));

        Dashboard.add(Report, "card7");

        ItemList.setBackground(new java.awt.Color(255, 255, 255));
        ItemList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ItemListKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ItemListKeyReleased(evt);
            }
        });
        ItemList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(null);

        tbl_item.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_item.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbl_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_itemMouseClicked(evt);
            }
        });
        tbl_item.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_itemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_itemKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_item);

        ItemList.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 950, 459));

        i_search1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        i_search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i_search1ActionPerformed(evt);
            }
        });
        i_search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                i_search1KeyReleased(evt);
            }
        });
        ItemList.add(i_search1, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 17, 502, 30));

        st_prdnew.setBackground(new java.awt.Color(204, 204, 204));
        st_prdnew.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        st_prdnew.setForeground(new java.awt.Color(51, 51, 51));
        st_prdnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/add-file(1).png"))); // NOI18N
        st_prdnew.setText("New Product");
        st_prdnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                st_prdnewActionPerformed(evt);
            }
        });
        ItemList.add(st_prdnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 10, 180, 40));

        prd_count.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        prd_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prd_count.setText("00");
        ItemList.add(prd_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 20, 90, 30));

        jLabel45.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/search-engine(1).png"))); // NOI18N
        ItemList.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 40));

        jLabel46.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel46.setText("Count List:");
        ItemList.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 20, -1, 30));

        request.setBackground(new java.awt.Color(255, 255, 255));
        request.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        request.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout requestLayout = new javax.swing.GroupLayout(request);
        request.setLayout(requestLayout);
        requestLayout.setHorizontalGroup(
            requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        requestLayout.setVerticalGroup(
            requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        ItemList.add(request, new org.netbeans.lib.awtextra.AbsoluteConstraints(784, 10, -1, -1));
        ItemList.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(804, 10, 20, 10));

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ItemList.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(784, 20, 200, 30));

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ItemList.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(834, 20, 110, 30));

        jLabel113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer(1).png"))); // NOI18N
        jLabel113.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel113MouseClicked(evt);
            }
        });
        ItemList.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(1036, 20, -1, -1));

        jScrollPane13.setBorder(null);

        tbl_itemcategory.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_itemcategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbl_itemcategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_itemcategoryMouseClicked(evt);
            }
        });
        tbl_itemcategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_itemcategoryKeyPressed(evt);
            }
        });
        jScrollPane13.setViewportView(tbl_itemcategory);

        ItemList.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 270, 420));

        jComboBox2.setEditable(true);
        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });
        ItemList.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 270, 40));

        Dashboard.add(ItemList, "card8");

        Stocks.setBackground(new java.awt.Color(255, 255, 255));
        Stocks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_cat_sel.setBackground(new java.awt.Color(102, 102, 102));
        pnl_cat_sel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_cat_search.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(jTable2);

        pnl_cat_search.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 200, 90));

        pnl_cat_sel.add(pnl_cat_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 190, 90));

        jButton17.setText("CANCEL");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        pnl_cat_sel.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 80, -1));

        pnl_cat_sel.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 260, 30));

        jButton23.setText("OK");
        jButton23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton23MouseClicked(evt);
            }
        });
        pnl_cat_sel.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 90, -1));

        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel141.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        jLabel141.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel141.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel141MouseClicked(evt);
            }
        });
        pnl_cat_sel.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 40, 20));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add-file (1).png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        pnl_cat_sel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 40, 20));

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });
        pnl_cat_sel.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 260, 30));

        buttonGroup2.add(jCheckBox1);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        pnl_cat_sel.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 30, 30));

        buttonGroup2.add(jCheckBox2);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        pnl_cat_sel.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 30, 30));

        jPanel36.setBackground(new java.awt.Color(204, 204, 204));

        p_mes_header.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p_mes_header, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_mes_header, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pnl_cat_sel.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 30));

        Stocks.add(pnl_cat_sel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 310, 160));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setText("ProductID");
        Stocks.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, 30));

        p_id.setEditable(false);
        p_id.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        p_id.setText("[Click To Auto Generate ID]");
        p_id.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p_idMouseClicked(evt);
            }
        });
        p_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_idActionPerformed(evt);
            }
        });
        Stocks.add(p_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 260, 30));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setForeground(new java.awt.Color(0, 102, 51));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Insert Product");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel32)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Stocks.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 300, 40));

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setText("Product Name");
        Stocks.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, 30));

        p_name.setEditable(false);
        p_name.setBackground(new java.awt.Color(255, 255, 255));
        p_name.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_name.setText(" ");
        p_name.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p_nameMouseClicked(evt);
            }
        });
        Stocks.add(p_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 260, 30));

        jLabel34.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel34.setText("Supplier");
        Stocks.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, 30));

        p_des.setEditable(false);
        p_des.setBackground(new java.awt.Color(255, 255, 255));
        p_des.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_des.setText(" ");
        p_des.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p_des.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p_desMouseClicked(evt);
            }
        });
        Stocks.add(p_des, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 260, 30));

        jLabel35.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel35.setText("ProductD Description");
        Stocks.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, 30));

        p_cat.setEditable(false);
        p_cat.setBackground(new java.awt.Color(255, 255, 255));
        p_cat.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_cat.setText(" ");
        p_cat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p_cat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p_catMouseClicked(evt);
            }
        });
        Stocks.add(p_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 260, 30));

        jLabel36.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel36.setText("Product Category");
        Stocks.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, 30));

        p_quan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_quan.setText(" ");
        Stocks.add(p_quan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 260, 30));

        jLabel37.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel37.setText("Quantity");
        Stocks.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 70, 30));

        p_org.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_org.setText(" ");
        Stocks.add(p_org, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 260, 30));

        jLabel38.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel38.setText("Orginal Price");
        Stocks.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, 30));

        p_val.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_val.setText(" ");
        Stocks.add(p_val, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 260, 30));

        jLabel39.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel39.setText("Values(SRP)");
        Stocks.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, -1, 30));

        jLabel40.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel40.setText("Sale");
        Stocks.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, -1, 30));

        p_sale.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_sale.setText(" ");
        Stocks.add(p_sale, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, 260, 30));

        p_add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        p_add.setText("Add");
        p_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_addActionPerformed(evt);
            }
        });
        Stocks.add(p_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 150, 40));

        p_clear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        p_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/eraser.png"))); // NOI18N
        p_clear.setText("Clear");
        p_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_clearActionPerformed(evt);
            }
        });
        Stocks.add(p_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, 100, 40));

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(102, 102, 102));
        jPanel12.setForeground(new java.awt.Color(0, 102, 51));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("IMAGE ITEM");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jLabel41)
                .addContainerGap(194, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, -1));

        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        p_image.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        p_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p_image.setText("Select Image");
        p_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51)));
        p_image.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p_imageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_image, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_image, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 39, 430, 360));

        Stocks.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 430, 400));

        id.setForeground(new java.awt.Color(255, 255, 255));
        Stocks.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 250, 20));

        Stocks.add(p_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 260, 30));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("+ New Supplier");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 20));

        Stocks.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 110, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Click category textbox to select options");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        Stocks.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 260, 10));

        p_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_cbActionPerformed(evt);
            }
        });
        Stocks.add(p_cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("View Supplier List");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        Stocks.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 100, -1));

        Dashboard.add(Stocks, "card3");

        Supplier.setBackground(new java.awt.Color(255, 255, 255));
        Supplier.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/search-engine(1).png"))); // NOI18N
        Supplier.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 60, 30));

        supp_search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        supp_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                supp_searchKeyReleased(evt);
            }
        });
        Supplier.add(supp_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 380, 30));

        sup_count.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sup_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sup_count.setText("00");
        Supplier.add(sup_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 40, 90, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Total List");
        Supplier.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 40, 80, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Clear Selection");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Supplier.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 150, 30));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("Deactivate");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        Supplier.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 150, 30));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        Supplier.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 150, 20));

        pnl_supp_update.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(0, 153, 0));
        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/add-user (1).png"))); // NOI18N
        jLabel101.setText(" UPDATE SUPPLIER");
        pnl_supp_update.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 280, -1));

        jLabel102.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(102, 0, 0));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("X");
        jLabel102.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel102MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel102MousePressed(evt);
            }
        });
        pnl_supp_update.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 30, -1));

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel103.setText("SupplierID");
        pnl_supp_update.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 20));

        sup_id.setEditable(false);
        sup_id.setBackground(new java.awt.Color(255, 255, 255));
        sup_id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pnl_supp_update.add(sup_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 260, 30));

        supp_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pnl_supp_update.add(supp_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 260, 30));

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel104.setText("Supplier");
        pnl_supp_update.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 30));

        supp_address.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        supp_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supp_addressActionPerformed(evt);
            }
        });
        pnl_supp_update.add(supp_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 260, 30));

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel106.setText("Address");
        pnl_supp_update.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 30));

        sup_email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pnl_supp_update.add(sup_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 260, 30));

        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel108.setText("Email");
        pnl_supp_update.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, 20));

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel109.setText("Remarks");
        pnl_supp_update.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        sup_remarks.setColumns(20);
        sup_remarks.setRows(5);
        jScrollPane11.setViewportView(sup_remarks);

        pnl_supp_update.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 260, 100));

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/reload.png"))); // NOI18N
        jButton11.setText("Update");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        pnl_supp_update.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 130, 30));

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/eraser.png"))); // NOI18N
        jButton12.setText("Clear");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        pnl_supp_update.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 120, 30));

        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel110.setText("Contact Number");
        pnl_supp_update.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 90, 30));

        supp_contact.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pnl_supp_update.add(supp_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 260, 30));

        Supplier.add(pnl_supp_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 370, 450));

        tbl_supplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_supplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_supplierMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_supplierMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_supplier);

        Supplier.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 70, 1180, 440));

        Dashboard.add(Supplier, "card9");

        Sales.setBackground(new java.awt.Color(255, 255, 255));
        Sales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setText("Void");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Sales.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 140, 30));

        tbl_cashier.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_cashier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_cashierMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_cashier);

        Sales.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 850, 210));

        jButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/buy.png"))); // NOI18N
        jButton5.setText("Close Order");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Sales.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 450, 170, 40));

        jLabel119.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel119.setText("Total Price:");
        Sales.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 460, -1, 30));

        totprice.setEditable(false);
        totprice.setBackground(new java.awt.Color(255, 255, 255));
        totprice.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totprice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totprice.setText("0.00");
        Sales.add(totprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 460, 150, 30));

        jLabel120.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel120.setText("Total QTY:");
        Sales.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 460, -1, 30));

        totqty.setEditable(false);
        totqty.setBackground(new java.awt.Color(255, 255, 255));
        totqty.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totqty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totqty.setText("00");
        Sales.add(totqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 460, 100, 30));

        s_cash.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        s_cash.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s_cash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s_cashKeyReleased(evt);
            }
        });
        Sales.add(s_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 280, 320, 60));

        jLabel121.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel121.setText("Cash:");
        Sales.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 250, 40, 20));

        jLabel122.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel122.setText("Change:");
        Sales.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 350, 70, 30));

        s_change.setEditable(false);
        s_change.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        s_change.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Sales.add(s_change, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 380, 320, 60));

        s_pnlprd.setBackground(new java.awt.Color(247, 247, 247));
        s_pnlprd.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        s_pnlprd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s_barcode.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_barcode.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        s_barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                s_barcodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s_barcodeKeyReleased(evt);
            }
        });
        s_pnlprd.add(s_barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 230, 30));

        jPanel21.setBackground(new java.awt.Color(0, 0, 0));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel79.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("PRODUCT INFORMATION");
        jPanel21.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, -1, 41));

        s_pnlprd.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 400, -1));

        jLabel47.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel47.setText("Descrition:");
        s_pnlprd.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 30));

        jLabel117.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel117.setText("Quantity");
        s_pnlprd.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, -1, 30));

        s_stck.setEditable(false);
        s_stck.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_stck.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s_stck.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        s_pnlprd.add(s_stck, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 60, 30));

        jLabel115.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel115.setText("Barcode:");
        s_pnlprd.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        s_total.setEditable(false);
        s_total.setBackground(new java.awt.Color(255, 255, 255));
        s_total.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_total.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        s_pnlprd.add(s_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 300, 30));

        jPanel20.setBackground(new java.awt.Color(51, 51, 51));

        jLabel78.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("PRODUCT SALES");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addComponent(jLabel78)
                .addGap(119, 119, 119))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        s_pnlprd.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 370, -1));

        s_des.setEditable(false);
        s_des.setBackground(new java.awt.Color(255, 255, 255));
        s_des.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_des.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        s_pnlprd.add(s_des, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 300, 30));

        jLabel43.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel43.setText("Product:");
        s_pnlprd.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        jLabel118.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel118.setText("Total");
        s_pnlprd.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 40, 30));

        s_price.setEditable(false);
        s_price.setBackground(new java.awt.Color(255, 255, 255));
        s_price.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_price.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        s_pnlprd.add(s_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 300, 30));

        s_add.setBackground(new java.awt.Color(0, 102, 0));
        s_add.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_add.setForeground(new java.awt.Color(255, 255, 255));
        s_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/plus.png"))); // NOI18N
        s_add.setText("  Add");
        s_add.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        s_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_addActionPerformed(evt);
            }
        });
        s_pnlprd.add(s_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 170, 30));

        s_qty.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_qty.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        s_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s_qtyKeyReleased(evt);
            }
        });
        s_pnlprd.add(s_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 300, 30));

        s_prd.setEditable(false);
        s_prd.setBackground(new java.awt.Color(255, 255, 255));
        s_prd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_prd.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        s_pnlprd.add(s_prd, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 300, 30));

        s_eq.setForeground(new java.awt.Color(255, 255, 255));
        s_pnlprd.add(s_eq, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 70, 20));

        jLabel125.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel125.setText("Price");
        s_pnlprd.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, 30));

        Sales.add(s_pnlprd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 850, 230));

        v_id.setForeground(new java.awt.Color(255, 255, 255));
        Sales.add(v_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 100, 20));

        s_year.setForeground(new java.awt.Color(255, 255, 255));
        s_year.setText("jLabel12");
        Sales.add(s_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 480, -1, -1));

        s_month.setForeground(new java.awt.Color(255, 255, 255));
        s_month.setText("s_month");
        Sales.add(s_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, -1, -1));

        jPanel6.setBackground(new java.awt.Color(247, 247, 247));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));

        jLabel80.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Customer Info");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(jLabel80)
                .addGap(119, 119, 119))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 340, 40));

        jLabel124.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel124.setText("Price");
        jPanel6.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, 30));

        jLabel123.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel123.setText("Customer");
        jPanel6.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 30));

        s_search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        s_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s_searchKeyReleased(evt);
            }
        });
        jPanel6.add(s_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 290, 30));

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 153, 51));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/user (2).png"))); // NOI18N
        jLabel58.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel58MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 30, 30));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setFocusable(false);
        jLabel59.setRequestFocusEnabled(false);
        jLabel59.setVerifyInputWhenFocusTarget(false);
        jPanel6.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 140, 30));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("Status:");
        jLabel60.setFocusable(false);
        jLabel60.setRequestFocusEnabled(false);
        jLabel60.setVerifyInputWhenFocusTarget(false);
        jPanel6.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 30));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 0, 153));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("View  Customer List");
        jLabel62.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel62.setDoubleBuffered(true);
        jLabel62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel62MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 120, 20));

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(153, 0, 0));
        jLabel105.setText("Select customer first before proceeding to product sales!");
        jPanel6.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 290, 20));

        jLabel127.setForeground(new java.awt.Color(153, 0, 0));
        jLabel127.setText("Note:");
        jPanel6.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 50, 20));

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));

        tbl_cus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_cus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_cusMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbl_cus);

        javax.swing.GroupLayout pnl_cus1Layout = new javax.swing.GroupLayout(pnl_cus1);
        pnl_cus1.setLayout(pnl_cus1Layout);
        pnl_cus1Layout.setHorizontalGroup(
            pnl_cus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
        );
        pnl_cus1Layout.setVerticalGroup(
            pnl_cus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );

        jPanel6.add(pnl_cus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        Sales.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 340, 230));

        pnl_cus.setBackground(new java.awt.Color(153, 153, 255));
        pnl_cus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Sales.add(pnl_cus, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 230, 320, -1));

        v_date.setForeground(new java.awt.Color(255, 255, 255));
        v_date.setText("jLabel126");
        Sales.add(v_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, 70, -1));

        jButton14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/bill.png"))); // NOI18N
        jButton14.setText("Receipt");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        Sales.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 450, 140, 40));

        Dashboard.add(Sales, "card2");

        Transactions.setBackground(new java.awt.Color(255, 255, 255));
        Transactions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_purchase_list.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_purchase_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_purchase_listMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbl_purchase_list);

        jPanel10.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 57, 1210, 400));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        jPanel10.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 20, 360, 30));

        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/search-engine(1).png"))); // NOI18N
        jPanel10.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        today.setBackground(new java.awt.Color(221, 221, 221));
        today.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        today.setText("  TODAY'S PURCHASE");
        today.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayActionPerformed(evt);
            }
        });
        jPanel10.add(today, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 150, 30));

        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 255, 255));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel114.setText("Total List");
        jPanel10.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 20, 70, 30));

        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 255, 255));
        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel116.setText("00");
        jPanel10.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 20, 70, 30));

        jLabel126.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(0, 0, 153));
        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel126.setText("View Item Return History");
        jLabel126.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel126.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel126MouseClicked(evt);
            }
        });
        jPanel10.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 460, 150, -1));

        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel131.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer(1).png"))); // NOI18N
        jLabel131.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel131.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel131MouseClicked(evt);
            }
        });
        jPanel10.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 40, 30));

        jTabbedPane1.addTab("   Customer List Purchase   ", jPanel10);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel129.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel129.setText("00");
        jPanel16.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, 70, 30));

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        jPanel16.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 470, 30));

        jLabel130.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/search-engine(1).png"))); // NOI18N
        jPanel16.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jButton13.setBackground(new java.awt.Color(226, 226, 226));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton13.setForeground(new java.awt.Color(51, 51, 51));
        jButton13.setText("Dump Report");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 440, 110, 30));

        jButton15.setBackground(new java.awt.Color(153, 0, 0));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Sell Dump Item");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 440, 120, 30));

        jPanel18.setBackground(new java.awt.Color(234, 232, 232));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setBackground(new java.awt.Color(204, 204, 204));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });
        jPanel19.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 250, 40));

        jLabel144.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel144.setForeground(new java.awt.Color(255, 255, 255));
        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel144.setText("Buyer:");
        jPanel19.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 40));

        jPanel18.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, 40));

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel99.setText("Product ID:");
        jPanel18.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 30));

        d_id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        d_id.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel18.add(d_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 200, 30));

        d_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        d_name.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel18.add(d_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 180, 30));

        jLabel132.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel132.setText("Product Name:");
        jPanel18.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 30));

        jLabel133.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel133.setText("Product Description:");
        jPanel18.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 30));

        d_des.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        d_des.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel18.add(d_des, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 150, 30));

        d_cat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        d_cat.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel18.add(d_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 160, 30));

        jLabel136.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel136.setText("Product Category:");
        jPanel18.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 30));
        jPanel18.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 222, 280, 10));

        d_qty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        d_qty.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel18.add(d_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 110, 30));

        jLabel138.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel138.setText("Price Sale");
        jPanel18.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 60, 20));

        d_price.setEditable(false);
        d_price.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                d_priceMouseClicked(evt);
            }
        });
        jPanel18.add(d_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 90, 30));

        d_qty1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        d_qty1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        d_qty1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                d_qty1KeyReleased(evt);
            }
        });
        jPanel18.add(d_qty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 100, 30));

        jLabel139.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 20)); // NOI18N
        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel139.setText("X");
        jPanel18.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 60, 40));

        d_total.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        d_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_total.setText("0.00");
        d_total.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel18.add(d_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 240, 40));

        jLabel143.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel143.setText("QTY");
        jPanel18.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 30, 30));

        jLabel145.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel145.setText("Total QTY:");
        jPanel18.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 80, 30));

        jLabel146.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel146.setText("TOTAL:");
        jPanel18.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 50, 40));

        d_qty2.setForeground(new java.awt.Color(234, 232, 232));
        jPanel18.add(d_qty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 60, 30));

        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable1);

        panel1.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 300, 120));

        jPanel18.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 300, 120));

        jPanel16.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 320, 420));

        jPanel22.setLayout(new java.awt.CardLayout());

        Dump_list.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(51, 51, 51));

        jLabel147.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        jLabel147.setForeground(new java.awt.Color(255, 255, 255));
        jLabel147.setText("DUMP ITEM'S LIST");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(655, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel147, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        Dump_list.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 30));

        tbl_dump.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_dump.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dumpMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tbl_dump);

        Dump_list.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 30, 890, 360));

        jPanel22.add(Dump_list, "card2");

        Dump_Sale.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_dump_sale.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane15.setViewportView(tbl_dump_sale);

        Dump_Sale.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 30, 890, 330));

        jPanel27.setBackground(new java.awt.Color(51, 51, 51));

        jLabel128.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(255, 255, 255));
        jLabel128.setText("DUMP ITEM'S SELL");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(661, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel128, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        Dump_Sale.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 30));

        dump_tprice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dump_tprice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dump_tprice.setText("00");
        dump_tprice.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Dump_Sale.add(dump_tprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 360, 130, 30));

        jLabel153.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel153.setText("Total Price");
        Dump_Sale.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 360, 90, 30));

        dump_tqty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dump_tqty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dump_tqty.setText("00");
        dump_tqty.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Dump_Sale.add(dump_tqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 130, 30));

        jLabel155.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel155.setText("Total QTY");
        Dump_Sale.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 90, 30));

        today_sold.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        today_sold.setText("  TODAYS DUMP SOLD");
        today_sold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                today_soldActionPerformed(evt);
            }
        });
        Dump_Sale.add(today_sold, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 170, 30));

        jPanel22.add(Dump_Sale, "card3");

        jPanel16.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 890, 390));

        jLabel151.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel151.setText("Count List");
        jPanel16.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 90, 30));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/reload.png"))); // NOI18N
        jButton6.setText("Switch Sell");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 180, 30));

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/eraser.png"))); // NOI18N
        jButton16.setText("Clear");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 440, -1, 30));

        jTabbedPane1.addTab("  Damage Item  ", jPanel16);

        Transactions.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 510));

        Dashboard.add(Transactions, "card4");

        dashboard.setBackground(new java.awt.Color(255, 255, 255));
        dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel23MouseClicked(evt);
            }
        });
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel149.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(51, 51, 51));
        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel149.setText("PRODUCT LISTED");
        jPanel23.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 160, 30));

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel148.setBackground(new java.awt.Color(0, 102, 0));
        jLabel148.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel148.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product count.png"))); // NOI18N
        jPanel14.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 80));

        jPanel23.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 100));

        d_prd.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        d_prd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_prd.setText("00");
        jPanel23.add(d_prd, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 160, 70));

        dashboard.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 290, 120));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel25.setBackground(new java.awt.Color(204, 0, 204));
        jPanel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel25MouseClicked(evt);
            }
        });
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel154.setBackground(new java.awt.Color(0, 102, 0));
        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel154.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/return.png"))); // NOI18N
        jPanel25.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 80));

        jPanel24.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 100));

        d_return.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        d_return.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_return.setText("00");
        jPanel24.add(d_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 160, 70));

        jLabel160.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(51, 51, 51));
        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel160.setText("PURCHASE RETURN LIST");
        jPanel24.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 160, 30));

        jLabel161.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel161.setForeground(new java.awt.Color(51, 51, 51));
        jLabel161.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel161.setText("PURCHASE RETURN QTY");
        jPanel24.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 160, 30));

        jLabel167.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel167.setText("00");
        jPanel24.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 160, 70));

        dashboard.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, 460, 120));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel157.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel157.setForeground(new java.awt.Color(51, 51, 51));
        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel157.setText("SUPPLIER LISTED");
        jPanel28.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 160, 30));

        jPanel29.setBackground(new java.awt.Color(204, 255, 204));
        jPanel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel29MouseClicked(evt);
            }
        });
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel158.setBackground(new java.awt.Color(0, 102, 0));
        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel158.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/users-group.png"))); // NOI18N
        jPanel29.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 80));

        jPanel28.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 100));

        d_supplier.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        d_supplier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_supplier.setText("00");
        jPanel28.add(d_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 160, 70));

        dashboard.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 290, 120));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        d_dump_price.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        d_dump_price.setForeground(new java.awt.Color(51, 51, 51));
        d_dump_price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_dump_price.setText("0.00");
        jPanel30.add(d_dump_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 130, 30));

        jPanel31.setBackground(new java.awt.Color(255, 51, 51));
        jPanel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel31MouseClicked(evt);
            }
        });
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel163.setBackground(new java.awt.Color(0, 102, 0));
        jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel163.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/damage.png"))); // NOI18N
        jPanel31.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 80));

        jPanel30.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 100));

        d_dump_pur.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        d_dump_pur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_dump_pur.setText("00");
        jPanel30.add(d_dump_pur, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 160, -1));

        jLabel165.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel165.setForeground(new java.awt.Color(51, 51, 51));
        jLabel165.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel165.setText("DUMP PURCHASE");
        jPanel30.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 160, 30));

        jLabel179.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel179.setForeground(new java.awt.Color(51, 51, 51));
        jLabel179.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel179.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cuba-peso-currency-symbol.png"))); // NOI18N
        jPanel30.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 30, 30));

        dashboard.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 290, 120));

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel32MouseClicked(evt);
            }
        });
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel33.setBackground(new java.awt.Color(204, 204, 204));
        jPanel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel33MouseClicked(evt);
            }
        });
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel168.setBackground(new java.awt.Color(0, 102, 0));
        jLabel168.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel168.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/to-do-list.png"))); // NOI18N
        jPanel33.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 80));

        jPanel32.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 100));

        d_dump.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        d_dump.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_dump.setText("00");
        jPanel32.add(d_dump, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 160, 70));

        jLabel170.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel170.setForeground(new java.awt.Color(51, 51, 51));
        jLabel170.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel170.setText("DUMP LISTED");
        jPanel32.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 160, 30));

        jLabel171.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel171.setForeground(new java.awt.Color(51, 51, 51));
        jLabel171.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel171.setText("DUMP QUANTITY");
        jPanel32.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 160, 30));

        d_dump_qty.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        d_dump_qty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_dump_qty.setText("00");
        jPanel32.add(d_dump_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 160, 70));

        dashboard.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 460, 120));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        d_sale_price.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        d_sale_price.setForeground(new java.awt.Color(51, 51, 51));
        d_sale_price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_sale_price.setText("10028.45");
        jPanel34.add(d_sale_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 130, 30));

        jPanel35.setBackground(new java.awt.Color(204, 204, 0));
        jPanel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel35MouseClicked(evt);
            }
        });
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel174.setBackground(new java.awt.Color(0, 102, 0));
        jLabel174.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel174.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sales.png"))); // NOI18N
        jPanel35.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 80));

        jPanel34.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 100));

        jLabel176.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel176.setForeground(new java.awt.Color(51, 51, 51));
        jLabel176.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel176.setText("PRODUCT SALES");
        jPanel34.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 160, 30));

        jLabel177.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel177.setForeground(new java.awt.Color(51, 51, 51));
        jLabel177.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel177.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cuba-peso-currency-symbol.png"))); // NOI18N
        jPanel34.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 30, 30));

        d_sales.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        d_sales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel34.add(d_sales, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 40, 160, 40));

        dashboard.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 290, 120));

        jLabel152.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel152.setForeground(new java.awt.Color(51, 51, 51));
        jLabel152.setText("III");
        dashboard.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, 80));

        jLabel178.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel178.setForeground(new java.awt.Color(51, 51, 51));
        jLabel178.setText("DASHBOARD");
        dashboard.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, 80));

        Dashboard.add(dashboard, "card11");

        customer.setBackground(new java.awt.Color(255, 255, 255));
        customer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customer.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 420, 30));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setText("Deactivate");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        customer.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 150, 30));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setText("Clear Selection");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        customer.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 150, 30));

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/search-engine(1).png"))); // NOI18N
        customer.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 20, 40, -1));

        cus_count.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cus_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cus_count.setText("00");
        customer.add(cus_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 20, 90, 30));

        jLabel64.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel64.setText("Total List:");
        customer.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 20, -1, 30));

        jLabel75.setBackground(new java.awt.Color(255, 255, 255));
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        customer.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, 110, 17));

        pnl_cus_update.setBackground(new java.awt.Color(247, 247, 247));
        pnl_cus_update.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/eraser.png"))); // NOI18N
        jButton9.setText("Clear");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        pnl_cus_update.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 130, -1));
        pnl_cus_update.add(cus_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 270, 30));

        cus_id.setEditable(false);
        cus_id.setBackground(new java.awt.Color(255, 255, 255));
        pnl_cus_update.add(cus_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 270, 30));
        pnl_cus_update.add(cus_age, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 110, 30));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 153, 0));
        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/user (1).png"))); // NOI18N
        jLabel76.setText("UPDATE CUSTOMER");
        pnl_cus_update.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 310, 60));
        pnl_cus_update.add(cus_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 270, 30));
        pnl_cus_update.add(cus_street, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 270, 30));
        pnl_cus_update.add(cus_city, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 270, 30));
        pnl_cus_update.add(cus_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 270, 30));

        cus_gender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        pnl_cus_update.add(cus_gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 140, 30));

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel77.setText("Age");
        pnl_cus_update.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setText("Custumer ID");
        pnl_cus_update.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 30));

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel82.setText("Fullname");
        pnl_cus_update.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel83.setText("Customer Status");
        pnl_cus_update.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, 20));

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel84.setText("Gender");
        pnl_cus_update.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, 10));

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel93.setText("Street");
        pnl_cus_update.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, 30));

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel96.setText("City");
        pnl_cus_update.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, 30));

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel97.setText("Contact");
        pnl_cus_update.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, 30));

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel98.setText("Email");
        pnl_cus_update.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, 30));

        buttonGroup1.add(cus_regular);
        cus_regular.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cus_regular.setText("Regular");
        cus_regular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cus_regularActionPerformed(evt);
            }
        });
        pnl_cus_update.add(cus_regular, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, -1, -1));

        buttonGroup1.add(cus_walkin);
        cus_walkin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cus_walkin.setText("Walk In");
        cus_walkin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cus_walkinActionPerformed(evt);
            }
        });
        pnl_cus_update.add(cus_walkin, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, -1, -1));

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/reload.png"))); // NOI18N
        jButton10.setText("Update");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        pnl_cus_update.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 130, -1));

        jLabel100.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(102, 0, 0));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel100.setText("X");
        jLabel100.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel100MouseClicked(evt);
            }
        });
        pnl_cus_update.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 30, -1));

        jLabel111.setBackground(new java.awt.Color(247, 247, 247));
        jLabel111.setForeground(new java.awt.Color(247, 247, 247));
        jLabel111.setText("jLabel111");
        pnl_cus_update.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 90, -1));

        customer.add(pnl_cus_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 370, 470));

        tbl_customer.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_customerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_customerMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_customerMousePressed(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_customer);

        customer.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1220, 460));

        Dashboard.add(customer, "card11");

        HomeMenu.add(Dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1240, 520));

        jPanel1.add(HomeMenu, "card3");

        login.setBackground(new java.awt.Color(255, 255, 255));
        login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setText("LOGIN");
        login.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, -1, -1));

        txtusername.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtusernameKeyReleased(evt);
            }
        });
        login.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, 350, 40));

        type1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        type1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "User" }));
        type1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type1ActionPerformed(evt);
            }
        });
        login.add(type1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 350, 38));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        login.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 140, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Forgot Password?");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        login.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, -1, 27));
        login.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));
        login.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        txtpassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpasswordKeyReleased(evt);
            }
        });
        login.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, 350, 40));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/password.png"))); // NOI18N
        login.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, -1, -1));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/login.png"))); // NOI18N
        login.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, -1, -1));

        jPanel1.add(login, "card2");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, 596));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu_icon/clock.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        s_time.setBackground(new java.awt.Color(255, 255, 255));
        s_time.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        s_time.setForeground(new java.awt.Color(255, 255, 255));
        s_time.setText("00:00 AM");
        jPanel5.add(s_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        s_date.setBackground(new java.awt.Color(255, 255, 255));
        s_date.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        s_date.setForeground(new java.awt.Color(255, 255, 255));
        s_date.setText("dd/mm/yyyy");
        jPanel5.add(s_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, 50));

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkcssms1/switcher_bottom_off.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkcssms1/019-x-button.png"))); // NOI18N
        jLabel57.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 40, 30));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 80, 30));

        jLabel142.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(7, 188, 188));
        jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel142.setText("SALES & SUPPLIES INVENTORY SYSTEM");
        jPanel5.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 460, 30));
        jPanel5.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 390, 10));

        jLabel134.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(252, 26, 26));
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel134.setText("D.T.C SOFTWARE");
        jPanel5.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 570, 70));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 110));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void type1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_type1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_admin WHERE Username= '" + txtusername.getText() + "' and Password= '" + txtpassword.getText() + "' ");
            rs = pst.executeQuery();

            if (rs.next()) {
                String add3 = rs.getString("Status");
                if(add3.contentEquals("Active")){
                    loggedin();
                    //jLabel11.setIcon().getDefaultToolkit().getImage(getClass().getResource("tile.png")));

                }else if(add3.contentEquals("Inactive")){
                    JOptionPane.showMessageDialog(null, "Account deactivated");

                }
            }else{
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_user WHERE Username= '" + txtusername.getText() + "' and Password= '" + txtpassword.getText() + "' ");
                rs = pst.executeQuery();

                if (rs.next()) {
                    String add3 = rs.getString("Status");
                    if(add3.contentEquals("Active")){
                        loggedin();

                    }else if(add3.contentEquals("Inactive")){
                         JOptionPane.showMessageDialog(null, "Account deactivated");

                    }
                }else{
                   
                         JOptionPane.showMessageDialog(null, "Incorrect Username/Password Match!!!");
                }
            }
        }catch(SQLException x) {
            JOptionPane.showMessageDialog(null, x);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        new Forgot_Pass().setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void txtpasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try{
                pst = (PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_admin WHERE Username= '" + txtusername.getText() + "' and Password= '" + txtpassword.getText() + "' ");
                rs = pst.executeQuery();

                if (rs.next()) {
                    String add3 = rs.getString("Status");
                    if(add3.contentEquals("Active")){
                        loggedin();
                    }else if(add3.contentEquals("Inactive")){
                    JOptionPane.showMessageDialog(null, "Account deactivated");
                    }
                    
                }else{
                    pst = (PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_user WHERE Username= '" + txtusername.getText() + "' and Password= '" + txtpassword.getText() + "' ");
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        String add3 = rs.getString("Status");
                        if(add3.contentEquals("Active")){
                            loggedin();
                        }else if(add3.contentEquals("Inactive")){
                         JOptionPane.showMessageDialog(null, "Account deactivated");
                        }
                    }else{
                         JOptionPane.showMessageDialog(null, "Incorrect Username/Password Match!!!");
                    }
                }
            }catch(SQLException x) {
                JOptionPane.showMessageDialog(null, x);
            }
        }
    }//GEN-LAST:event_txtpasswordKeyReleased

    private void pnl_stockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_stockMouseClicked
        // TODO add your handling code here:
        login.setVisible(false);
        Sales.setVisible(false);
        Stocks.setVisible(false);
        ItemList.setVisible(true);
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
        addaccount.setVisible(false);
        accountlist.setVisible(false);
        Supplier.setVisible(false);
        customer.setVisible(false);
        dashboard.setVisible(false);
         category_drop();
        refresh_item();
          item_clear();
        empCount();
        p_sup.removeAllItems();
        jLabel94.setText("");
    }//GEN-LAST:event_pnl_stockMouseClicked

    private void pnl_stockMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_stockMouseEntered
        // TODO add your handling code here:
        // setColor(pnl_stock);
    }//GEN-LAST:event_pnl_stockMouseEntered

    private void pnl_stockMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_stockMouseExited
        // resetColor(pnl_stock);
    }//GEN-LAST:event_pnl_stockMouseExited

    private void pnl_stockMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_stockMousePressed
        // TODO add your handling code here:
        setColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
        resetColor_manage(pnl_dashboard);
    }//GEN-LAST:event_pnl_stockMousePressed

    private void pnl_salesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_salesMouseClicked
        // TODO add your handling code here:
        login.setVisible(false);
        Sales.setVisible(true);
        Stocks.setVisible(false);
        ItemList.setVisible(false);
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
        addaccount.setVisible(false);
        accountlist.setVisible(false);
        Supplier.setVisible(false);
        customer.setVisible(false);
        dashboard.setVisible(false);
        
        pnl_cus1.setVisible(false);
        jLabel60.setVisible(true);
         jLabel59.setVisible(true);
        s_add.setEnabled(false);
        refresh_cashier();
        totprice.setText(Double.toString(total_bprice()));
        totqty.setText(Integer.toString(total_bqty()));
        jButton5.setEnabled(true);
        s_cash.setText("");
        s_change.setText("");
        
         jLabel127.setVisible(false);
         jLabel105.setVisible(false);
    }//GEN-LAST:event_pnl_salesMouseClicked

    private void pnl_salesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_salesMouseEntered
        // TODO add your handling code here:
        // setColor(pnl_sales);
    }//GEN-LAST:event_pnl_salesMouseEntered

    private void pnl_salesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_salesMouseExited
        // TODO add your handling code here:
        //  resetColor(pnl_sales);
    }//GEN-LAST:event_pnl_salesMouseExited

    private void pnl_salesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_salesMousePressed
        // TODO add your handling code here:
        resetColor_manage(pnl_stock);
        setColor_manage(pnl_sales);
        resetColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
        resetColor_manage(pnl_dashboard);
    }//GEN-LAST:event_pnl_salesMousePressed

    private void pnl_transactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_transactionsMouseClicked
        // TODO add your handling code here:
        login.setVisible(false);
        Sales.setVisible(false);
        Stocks.setVisible(false);
        ItemList.setVisible(false);
        Transactions.setVisible(true);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
        addaccount.setVisible(false);
        accountlist.setVisible(false);
        Supplier.setVisible(false);
        customer.setVisible(false);
        panel1.setVisible(false);
        dashboard.setVisible(false);
        jLabel126.setText("View Item Return History");
        
        // customer search
                                panel1.setVisible(false);
                                //label
                                jLabel133.setVisible(true);
                                jLabel132.setVisible(true);
                                jLabel99.setVisible(true);
                                //textfield
                                d_id.setVisible(true);
                                d_name.setVisible(true);
                                d_des.setVisible(true);
        
        try{
        if(today.isSelected() && today.getText().equals("  TODAY'S PURCHASE")){
            String sql = "SELECT * FROM tbl_customer_purchase WHERE "
                    + "Date like ?  ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));
            
            }
        else if(today.isSelected() && today.getText().equals("  TODAY'S RETURNED")){
             String sql = "SELECT * FROM tbl_return_history WHERE "
                    + "Date like ?  ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));
            }else {
                
            if (today.getText().equals("  TODAY'S RETURNED")){
            refresh_purcshse_history();
            }else{
            refresh_purcshse_list();
            }
        }
        }catch(SQLException e){ JOptionPane.showMessageDialog(null,e);}
        
        
          if (jButton6.getText().equals("Switch Dump")){
        Dump_Sale.setVisible(true);
            Dump_list.setVisible(false);
            refresh_dump_sale();
             jButton15.setEnabled(true);
            jButton13.setText("Sell Report");
            d_qty1.setEnabled(false);
            dump_tprice.setText(Double.toString(total_price()));
            dump_tqty.setText(Integer.toString(total_qty()));
             
        }else{
            Dump_Sale.setVisible(false);
            Dump_list.setVisible(true);
             refresh_dump_item();
             jButton15.setEnabled(false);
            jButton13.setText("Dump Report");
            d_qty1.setEnabled(true);
        }
        resetColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        setColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
        resetColor_manage(pnl_dashboard);
    }//GEN-LAST:event_pnl_transactionsMouseClicked

    private void pnl_transactionsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_transactionsMouseEntered
        // TODO add your handling code here:
        // setColor(pnl_transactions);
    }//GEN-LAST:event_pnl_transactionsMouseEntered

    private void pnl_transactionsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_transactionsMouseExited
        // TODO add your handling code here:
        // resetColor(pnl_transactions);
    }//GEN-LAST:event_pnl_transactionsMouseExited

    private void pnl_logsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_logsMouseClicked
        // TODO add your handling code here:
        resetColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        setColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
        resetColor_manage(pnl_dashboard);
        if (acctype.getText().equals("User")){
            JOptionPane.showMessageDialog(null,"Invalid User Field!!!");
        }else{
            login.setVisible(false);
            Sales.setVisible(false);
            Stocks.setVisible(false);
            ItemList.setVisible(false);
            Transactions.setVisible(false);
            Logs.setVisible(true);
            Accounts.setVisible(false);
            Report.setVisible(false);
            addaccount.setVisible(false);
            accountlist.setVisible(false);
            Supplier.setVisible(false);
            customer.setVisible(false);
            dashboard.setVisible(false);
            refresh_logs();
        }
    }//GEN-LAST:event_pnl_logsMouseClicked

    private void pnl_logsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_logsMouseEntered
        // TODO add your handling code here:
        // setColor(pnl_logs);
    }//GEN-LAST:event_pnl_logsMouseEntered

    private void pnl_logsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_logsMouseExited
        // TODO add your handling code here:
        // resetColor(pnl_logs);
    }//GEN-LAST:event_pnl_logsMouseExited

    private void pnl_accountsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_accountsMouseClicked
        // TODO add your handling code here:
        if (acctype.getText().equals("Admin")){
            login.setVisible(false);
            Sales.setVisible(false);
            Stocks.setVisible(false);
            ItemList.setVisible(false);
            Transactions.setVisible(false);
            Logs.setVisible(false);
            Accounts.setVisible(true);
            Report.setVisible(false);
            addaccount.setVisible(false);
            accountlist.setVisible(false);
            Supplier.setVisible(false);
            customer.setVisible(false);
            dashboard.setVisible(false);

            resetColor_manage(pnl_stock);
            resetColor_manage(pnl_sales);
            resetColor_manage(pnl_sales);
            resetColor_manage(pnl_transactions);
            resetColor_manage(pnl_report);
            setColor_manage(pnl_accounts);
            resetColor_manage(pnl_logs);
            resetColor_manage(pnl_logout);
            resetColor_manage(pnl_dashboard);
        }else{
            login.setVisible(false);
            Sales.setVisible(false);
            Stocks.setVisible(false);
            ItemList.setVisible(false);
            Transactions.setVisible(false);
            Logs.setVisible(false);
            Accounts.setVisible(true);
            Report.setVisible(false);
            addaccount.setVisible(false);
            accountlist.setVisible(false);
            manageacc.setVisible(true);
            Supplier.setVisible(false);
            customer.setVisible(false);
            pnl_dashboard.setVisible(false);

            resetColor_manage(pnl_stock);
            resetColor_manage(pnl_sales);
            resetColor_manage(pnl_sales);
            resetColor_manage(pnl_transactions);
            resetColor_manage(pnl_report);
            setColor_manage(pnl_accounts);
            resetColor_manage(pnl_logs);
            resetColor_manage(pnl_logout);
            resetColor_manage(pnl_dashboard);

        }
    }//GEN-LAST:event_pnl_accountsMouseClicked

    private void pnl_accountsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_accountsMouseEntered
        // TODO add your handling code here:
        // setColor(pnl_accounts);
    }//GEN-LAST:event_pnl_accountsMouseEntered

    private void pnl_accountsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_accountsMouseExited
        // TODO add your handling code here:
        //resetColor(pnl_accounts);
    }//GEN-LAST:event_pnl_accountsMouseExited

    private void pnl_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_logoutMouseClicked
        // TODO add your handling code here:
        JFrame frame = new JFrame();
        String[] options = new String[2];
        options[0] = new String("Logout");
        options[1] = new String("Cancel");
        int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Countinue logout account?","Logout", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
         
        if(p==0){
            pnl_hide();
            txtusername.setText("");
            txtpassword.setText("");
            user.setText("--------------------");
            accid.setText("--------------------");
            acctype.setText("--------------------");
        }
        else{
            frame.dispose();
        }
       
    }//GEN-LAST:event_pnl_logoutMouseClicked

    private void pnl_logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_logoutMouseEntered
        // TODO add your handling code here:
        // setColor(pnl_logout);
    }//GEN-LAST:event_pnl_logoutMouseEntered

    private void pnl_logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_logoutMouseExited
        // TODO add your handling code here:
        //resetColor(pnl_logout);
    }//GEN-LAST:event_pnl_logoutMouseExited

    private void pnl_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_logoutMousePressed
        // TODO add your handling code here:

        resetColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        setColor_manage(pnl_logout);
        resetColor_manage(pnl_dashboard);
    }//GEN-LAST:event_pnl_logoutMousePressed

    private void pnl_reportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_reportMouseClicked
        // TODO add your handling code here:
        resetColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_transactions);
        setColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
        resetColor_manage(pnl_dashboard);
        
        if (acctype.getText().equals("User")){
            JOptionPane.showMessageDialog(null,"Invalid User Field!!!");
        }else{
            login.setVisible(false);
            Sales.setVisible(false);
            Stocks.setVisible(false);
            ItemList.setVisible(false);
            Transactions.setVisible(false);
            Logs.setVisible(false);
            Accounts.setVisible(false);
            Report.setVisible(true);
            addaccount.setVisible(false);
            accountlist.setVisible(false);
            dashboard.setVisible(false);
            refresh_sales();
            report_sales.setText(Double.toString(total_report_Sales()));
            report_item.setText(Integer.toString(total_sales_summary()));
            r_date.setDate(null);
            Daily_transation();
            daily.setSelected(true);
            monthly.setSelected(false);
            yearly.setSelected(false);
            rdel.setText("");
            Supplier.setVisible(false);
            customer.setVisible(false);

        }
    }//GEN-LAST:event_pnl_reportMouseClicked

    private void pnl_reportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_reportMouseEntered
        // TODO add your handling code here:
        // setColor(pnl_report);
    }//GEN-LAST:event_pnl_reportMouseEntered

    private void pnl_reportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_reportMouseExited
        // TODO add your handling code here:
        // resetColor(pnl_report);
    }//GEN-LAST:event_pnl_reportMouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO
            if (v_id.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please Select from Table to Void Item!!");
            }else{
                try{
                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_cashier WHERE Time = '" + v_id.getText() + "' ");
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        String pid = rs.getString("ProductID");
                        String pquan = rs.getString("Quantity");
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + pid + "'");
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            String add4 = rs.getString("Quantity");
                            int a, b, c;
                            a = Integer.parseInt(pquan);
                            b = Integer.parseInt(add4);
                            c = a + b;

                            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  Quantity=? WHERE ProductID='" + pid + "'");

                            pst.setInt(1, c);

                            int update = pst.executeUpdate();
                            if (update != 0) {
                                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_cashier WHERE Date ='"+v_date.getText()+"' and Time = '" + v_id.getText() + "' ");
                                int del = pst.executeUpdate();
                                if (del > 0) {
                                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_sales WHERE Date ='"+v_date.getText()+"' and Time = '" + v_id.getText() + "'");
                                   int del1 =  pst.executeUpdate();
                                   if(del1>0){
                                       pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_customer_purchase WHERE Date ='"+v_date.getText()+"' and Time = '" + v_id.getText() + "'");
                                   int del11 =  pst.executeUpdate();
                                   if(del11 >0){
                                    refresh_cashier();
                                    v_id.setText("");
                                    totprice.setText(Double.toString(total_price()));
                                    totqty.setText(Integer.toString(total_qty()));
                                }}}}}}
                            } catch(SQLException ex){
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        }
                   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbl_cashierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_cashierMouseClicked
        // TODO add your handling code here:
            int z = tbl_cashier.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tbl_cashier.getModel();
            v_date.setText(model.getValueAt(z, 6).toString());
            v_id.setText(model.getValueAt(z, 7).toString());
       
    }//GEN-LAST:event_tbl_cashierMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here
      try{
        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_cashier");
                int del = pst.executeUpdate();
                if (del > 0) {
                    refresh_cashier();
                    v_id.setText("");
                    totprice.setText(Double.toString(total_price()));
                    totqty.setText(Integer.toString(total_qty()));
                    s_cash.setText("");
                    s_change.setText("");
                    s_search.setText("");
                    jLabel59.setText("");
                    jButton14.setEnabled(false);
         jLabel127.setVisible(false);
         jLabel105.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Error Selection!!");
                }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }

        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void s_cashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_cashKeyReleased
        // TODO add your handling code here:
       
                Double a, b, c;
                a = Double.parseDouble(s_cash.getText());
                b = Double.parseDouble(totprice.getText());
                c = a - b;
                if (a==0){
                
                }else{
                s_change.setText(String.valueOf(c));
                jButton14.setEnabled(true);
                }
           
        
    }//GEN-LAST:event_s_cashKeyReleased

    private void s_barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_barcodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_barcodeKeyPressed

    private void s_barcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_barcodeKeyReleased
        // TODO add your handling code here:
        if(jLabel59.getText().equals("Regular")){
        
            try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + s_barcode.getText() + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                String add3 = rs.getString("ProductName");
                s_prd.setText(add3);
                String add5 = rs.getString("ProductDescription");
                s_des.setText(add5);
                String add7 = rs.getString("Quantity");
                s_stck.setText(add7);
                String add8 = rs.getString("OriginalPrice");
                s_price.setText(add8);  
                
            }else{
                // s_barcode.setText("");
                s_prd.setText("");
                s_des.setText("");
                s_price.setText("");
                s_qty.setText("");
                s_total.setText("");
                s_stck.setText("");
                s_add.setEnabled(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
            
        }else if(jLabel59.getText().equals("Walk In")){
        
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + s_barcode.getText() + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                String add3 = rs.getString("ProductName");
                s_prd.setText(add3);
                String add5 = rs.getString("ProductDescription");
                s_des.setText(add5);
                String add7 = rs.getString("Quantity");
                s_stck.setText(add7);
                String add8 = rs.getString("ValuesSRP");
                s_price.setText(add8); 
            }else{
                // s_barcode.setText("");
                s_prd.setText("");
                s_des.setText("");
                s_price.setText("");
                s_qty.setText("");
                s_total.setText("");
                s_stck.setText("");
                s_add.setEnabled(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        
        }else{
         try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + s_barcode.getText() + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                String add3 = rs.getString("ProductName");
                s_prd.setText(add3);
                String add5 = rs.getString("ProductDescription");
                s_des.setText(add5);
                String add7 = rs.getString("Quantity");
                s_stck.setText(add7);
                String add8 = rs.getString("ValuesSRP");
                s_price.setText(add8); 
            }else{
                // s_barcode.setText("");
                s_prd.setText("");
                s_des.setText("");
                s_price.setText("");
                s_qty.setText("");
                s_total.setText("");
                s_stck.setText("");
                s_add.setEnabled(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        
        }
        
    }//GEN-LAST:event_s_barcodeKeyReleased

    private void s_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_addActionPerformed
        // TODO add your handling code here:
        if (s_eq.getText().contains("-")){
            JOptionPane.showMessageDialog(null, "Not Enough Stock for this Transaction!");
        }else{
            try{
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_cashier WHERE ProductID = '" + s_barcode.getText() + "'");
                rs = pst.executeQuery();
                if (rs.next()) {
                    String qty = rs.getString("Quantity");
                    String tprice = rs.getString("TotalPrice");
                    String time = rs.getString("Time");
                    int pqty,cqty,totqtyy; double pprice,cprice,totpricee;
                    pqty = Integer.parseInt(qty);
                    cqty = Integer.parseInt(s_qty.getText());
                    totqtyy = pqty + cqty;
                    pprice = Double.parseDouble(tprice);
                    cprice = Double.parseDouble(s_total.getText());
                    totpricee = pprice + cprice;
                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_cashier SET  Quantity=?,TotalPrice=? WHERE Time='" + time + "'");

                    pst.setInt(1, totqtyy);
                    pst.setDouble(2, totpricee);

                    int update = pst.executeUpdate();
                    if (update != 0) {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_sales SET  Quantity=?,TotalPrice=? WHERE Time='" + time + "'");

                        pst.setInt(1, totqtyy);
                        pst.setDouble(2, totpricee);

                        int update1 = pst.executeUpdate();
                        if (update1 != 0) {
                            
                             pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_customer_purchase SET  Quantity=?,TotalAmount=? WHERE Time='" + time + "'");

                        pst.setInt(1, totqtyy);
                        pst.setDouble(2, totpricee);

                        int update111 = pst.executeUpdate();
                            if (update111 != 0) {
                            pst = (PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + s_barcode.getText() + "'");
                            rs = pst.executeQuery();
                            if (rs.next()) {
                                String qty1= rs.getString("Quantity");
                                int a, b, c;
                                a = Integer.parseInt(qty1);
                                b = Integer.parseInt(s_qty.getText());
                                c = a - b;
                                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  Quantity=? WHERE ProductID='" + s_barcode.getText() + "'");

                                pst.setInt(1, c);
                                int update11 = pst.executeUpdate();
                                if (update11 != 0) {

                                    refresh_cashier();
                                    s_barcode.setText("");
                                    s_prd.setText("");
                                    s_des.setText("");
                                    s_price.setText("");
                                    s_qty.setText("");
                                    s_total.setText("");
                                    s_stck.setText("");
                                    s_add.setEnabled(false);
                                    totprice.setText(Double.toString(total_price()));
                                    totqty.setText(Integer.toString(total_qty()));
                                }
                            }
                            }
                            }
                    }

                }else{

                    String sql1 = "INSERT INTO tbl_cashier VALUES (?,?,?,?,?,?,?,?)";
                    String sql = "INSERT INTO tbl_sales VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                    String sql11 = "INSERT INTO tbl_customer_purchase VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                    pst = (PreparedStatement) conn.prepareStatement(sql1);
                    pst.setString(1,s_barcode.getText());
                    pst.setString(2,s_prd.getText());
                    pst.setString(3, s_des.getText());
                    pst.setString(4,s_price.getText());
                    pst.setString(5,s_qty.getText());
                    pst.setString(6,s_total.getText());
                    pst.setString(7,s_date.getText());
                    pst.setString(8,s_time.getText());
                    pst.executeUpdate();
                    
                        pst = (PreparedStatement) conn.prepareStatement(sql);
                        pst.setString(1,s_barcode.getText());
                        pst.setString(2,s_prd.getText());
                        pst.setString(3, s_des.getText());
                        pst.setString(4,s_price.getText());
                        pst.setString(5,s_qty.getText());
                        pst.setString(6,s_total.getText());
                        pst.setString(7,s_date.getText());
                        pst.setString(8,s_time.getText());
                        pst.setString(9,s_month.getText());
                        pst.setString(10,s_year.getText());
                        pst.setString(11,user.getText());
                        pst.executeUpdate();
                    
                    
                            pst = (PreparedStatement) conn.prepareStatement(sql11);
                            pst.setString(1,s_search.getText());
                            pst.setString(2,jLabel59.getText());
                            pst.setString(3,s_barcode.getText());
                            pst.setString(4,s_prd.getText());
                            pst.setString(5,s_des.getText());
                            pst.setString(6,s_price.getText());
                            pst.setString(7,s_qty.getText());
                            pst.setString(8,s_total.getText());
                            pst.setString(9,s_date.getText());
                            pst.setString(10,s_time.getText());
                            pst.setString(11,user.getText());
                            pst.executeUpdate();
                             
                    refresh_cashier();
                    pst = (PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + s_barcode.getText() + "'");
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        String qty= rs.getString("Quantity");
                        int a, b, c;
                        a = Integer.parseInt(qty);
                        b = Integer.parseInt(s_qty.getText());
                        c = a - b;

                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  Quantity=? WHERE ProductID='" + s_barcode.getText() + "'");

                        pst.setInt(1, c);
                        int update = pst.executeUpdate();
                        if (update != 0) {
                            refresh_item();
                            s_barcode.setText("");
                            s_prd.setText("");
                            s_des.setText("");
                            s_price.setText("");
                            s_qty.setText("");
                            s_total.setText("");
                            s_stck.setText("");
                            s_add.setEnabled(false);

                            totprice.setText(Double.toString(total_price()));
                            totqty.setText(Integer.toString(total_qty()));
                        }

                    }

                }

            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }//GEN-LAST:event_s_addActionPerformed

    private void s_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_qtyKeyReleased
        // TODO add your handling code here:
        if (s_qty.getText().equals("")){
            s_total.setText("00");
            s_add.setEnabled(false);
        }else{
            float a, b, c, d, e, f;

            a = Float.parseFloat(s_price.getText());
            b = Float.parseFloat(s_qty.getText());
            c = a * b;

            s_total.setText(String.valueOf(c));
            s_add.setEnabled(true);

            d = Float.parseFloat(s_stck.getText());
            e = Float.parseFloat(s_qty.getText());
            f = d - e;
            s_eq.setText(String.valueOf(f));
        }
    }//GEN-LAST:event_s_qtyKeyReleased

    private void p_idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_idMouseClicked
        // TODO add your handling code here:
        if (p_id.getText().equals("[Click To Auto Generate ID]")){
             Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("hmmss");
                SimpleDateFormat st = new SimpleDateFormat("MMddyyyy");
            p_id.setText("ID"+st.format(d)+s.format(d));
        }else{}
    }//GEN-LAST:event_p_idMouseClicked

    private void p_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_idActionPerformed

    private void p_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_addActionPerformed
        // TODO add your handling code here:
        if(p_add.getText().equals("Add")){

            if (p_id.getText().equals("[Click To Auto Generate ID]")){
                JOptionPane.showMessageDialog(null,"Invalid Product ID! Please Check!");
            }else{
                if (p_image.getText().equals("Select Image")){
                    try{
                        if (acctype.getText().equals("Admin")){

                            String sql1 = "INSERT INTO tbl_items(ProductID,ProductName,ProductDescription,ProductCategory,Quantity,OriginalPrice,ValuesSRP,Sale,Supplier) VALUES (?,?,?,?,?,?,?,?,?)";

                            pst = (PreparedStatement) conn.prepareStatement(sql1);
                            pst.setString(1,p_id.getText());
                            pst.setString(2,p_name.getText());
                            pst.setString(3, p_des.getText());
                            pst.setString(4,p_cat.getText());
                            pst.setString(5,p_quan.getText());
                            pst.setString(6,p_org.getText());
                            pst.setString(7,p_val.getText());
                            pst.setString(8,p_sale.getText());
                            pst.setString(9,(String)p_sup.getSelectedItem());

                            int add = pst.executeUpdate();
                            if(add!=0){
                                refresh_item();
                                //item_clear();
                                empCount();
                                JOptionPane.showMessageDialog(null,"New Product Stored!!");

                            } else{
                                JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                            }

                        }else if(acctype.getText().equals("User")){

                            String sql1 = "INSERT INTO tbl_stock_request(ProductID,ProductName,ProductDescription,ProductCategory,Quantity,OriginalPrice,ValuesSRP,Sale,Supplier) VALUES (?,?,?,?,?,?,?,?,?)";

                            pst = (PreparedStatement) conn.prepareStatement(sql1);
                            pst.setString(1,p_id.getText());
                            pst.setString(2,p_name.getText());
                            pst.setString(3, p_des.getText());
                            pst.setString(4,p_cat.getText());
                            pst.setString(5,p_quan.getText());
                            pst.setString(6,p_org.getText());
                            pst.setString(7,p_val.getText());
                            pst.setString(8,p_sale.getText());
                            pst.setString(9,(String)p_sup.getSelectedItem());

                            int add = pst.executeUpdate();
                            if(add!=0){
                                refresh_item();
                                //item_clear();
                                empCount();
                                JOptionPane.showMessageDialog(null,"Wait for the Admins confirmation! This will be saved as Temporary Stock Confirmation.");

                            } else{
                                JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                            }

                        }

                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }else{
                    if (imgpath != null){
                        try{
                            if (acctype.getText().equals("Admin")){
                                String sql= "INSERT INTO tbl_items VALUES  (?,?,?,?,?,?,?,?,?,?)";
                                pst = (PreparedStatement) conn.prepareStatement(sql);
                                InputStream img = new FileInputStream(new File(imgpath));
                                pst.setString(1,p_id.getText());
                                pst.setString(2,p_name.getText());
                                pst.setString(3, p_des.getText());
                                pst.setString(4,p_cat.getText());
                                pst.setString(5,p_quan.getText());
                                pst.setString(6,p_org.getText());
                                pst.setString(7,p_val.getText());
                                pst.setString(8,p_sale.getText());
                                pst.setBlob(9,img); 
                                pst.setString(10,(String)p_sup.getSelectedItem());
                                
                                int add = pst.executeUpdate();
                                if(add!=0){
                                    refresh_item();
                                   // item_clear();
                                    empCount();
                                    JOptionPane.showMessageDialog(null,"New Product Stored!!");

                                } else{
                                    JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                                }

                            }else if (acctype.getText().equals("User")){

                                String sql= "INSERT INTO tbl_stock_request VALUES  (?,?,?,?,?,?,?,?,?,?)";
                                pst = (PreparedStatement) conn.prepareStatement(sql);
                                InputStream img = new FileInputStream(new File(imgpath));
                                pst.setString(1,p_id.getText());
                                pst.setString(2,p_name.getText());
                                pst.setString(3, p_des.getText());
                                pst.setString(4,p_cat.getText());
                                pst.setString(5,p_quan.getText());
                                pst.setString(6,p_org.getText());
                                pst.setString(7,p_val.getText());
                                pst.setString(8,p_sale.getText());
                                pst.setBlob(9,img);
                                pst.setString(10,(String)p_sup.getSelectedItem());
                                int add = pst.executeUpdate();
                                if(add!=0){
                                    refresh_item();
                                   // item_clear();
                                    empCount();
                                    
                                    JOptionPane.showMessageDialog(null,"Wait for the Admins confirmation! This will be saved as Temporary Stock Confirmation.");

                                } else{
                                    
                                    JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                                }
                            }
                        } catch(SQLException ex){
                            JOptionPane.showMessageDialog(null, ex);
                        }            catch (FileNotFoundException ex) {
                            Logger.getLogger(HOME.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }else{
                        
                        JOptionPane.showMessageDialog(null,"No Image Selected");
                    }
                    imgpath = null;
                }
            }
        }else if(p_add.getText().equals("Update")){
            if (p_cb.isSelected()){
           
                JFrame frame = new JFrame();
            String[] options = new String[4];
            options[0] = new String("Continue");
            options[1] = new String("Cancel");
            int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Supplier is selected to Update! Do you want to continue?","Warning", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(p==0){
                
                if (acctype.getText().equals("User")){
                JOptionPane.showMessageDialog(null,"Invalid User Field!!!");
            }else{
                if (p_image.getText().equals("Select Image")){
                    try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  ProductID=?, ProductName=?, ProductDescription=?, ProductCategory=?, "
                            + "Quantity=?, OriginalPrice=?, ValuesSRP=?, Sale=? ,Supplier=?WHERE ProductID='" + p_id.getText() + "'");

                        pst.setString(1, p_id.getText());
                        pst.setString(2, p_name.getText());
                        pst.setString(3, p_des.getText());
                        pst.setString(4, p_cat.getText());
                        pst.setString(5, p_quan.getText());
                        pst.setString(6, p_org.getText());
                        pst.setString(7, p_val.getText());
                        pst.setString(8, p_sale.getText());
                        pst.setString(9,(String)p_sup.getSelectedItem());

                        int update = pst.executeUpdate();
                        if (update != 0) {
                            refresh_item();
                            item_clear();
                            
                            JOptionPane.showMessageDialog(null,"Item Updated");
                            p_add.setText("Add");
                            p_cb.setVisible(false);
                        }
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }else{
                    try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  ProductID=?, ProductName=?, ProductDescription=?, ProductCategory=?, "
                            + "Quantity=?, OriginalPrice=?, ValuesSRP=?, Sale=? ,Image=?,Supplier=? WHERE ProductID='" + p_id.getText() + "'");
                        InputStream img = new FileInputStream(new File(imgpath));
                        pst.setString(1, p_id.getText());
                        pst.setString(2, p_name.getText());
                        pst.setString(3, p_des.getText());
                        pst.setString(4, p_cat.getText());
                        pst.setString(5, p_quan.getText());
                        pst.setString(6, p_org.getText());
                        pst.setString(7, p_val.getText());
                        pst.setString(8, p_sale.getText());
                        pst.setBlob(9, img);
                        pst.setString(10,(String)p_sup.getSelectedItem());
                        int update = pst.executeUpdate();
                        if (update != 0) {
                            refresh_item();
                            item_clear();
                            
                            JOptionPane.showMessageDialog(null,"Item Updated");
                            p_add.setText("Add");
                        }
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }               catch (FileNotFoundException ex) {
                        Logger.getLogger(HOME.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
                
            }else if(p==1){
            
                frame.dispose();
                
            }
                
            }else{
            
            if (acctype.getText().equals("User")){
                JOptionPane.showMessageDialog(null,"Invalid User Field!!!");
            }else{
                if (p_image.getText().equals("Select Image")){
                    try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  ProductID=?, ProductName=?, ProductDescription=?, ProductCategory=?, "
                            + "Quantity=?, OriginalPrice=?, ValuesSRP=?, Sale=? ,Supplier=?WHERE ProductID='" + p_id.getText() + "'");

                        pst.setString(1, p_id.getText());
                        pst.setString(2, p_name.getText());
                        pst.setString(3, p_des.getText());
                        pst.setString(4, p_cat.getText());
                        pst.setString(5, p_quan.getText());
                        pst.setString(6, p_org.getText());
                        pst.setString(7, p_val.getText());
                        pst.setString(8, p_sale.getText());
                        pst.setString(9,(String)p_sup.getSelectedItem());

                        int update = pst.executeUpdate();
                        if (update != 0) {
                            refresh_item();
                            item_clear();
                            
                            JOptionPane.showMessageDialog(null,"Item Updated");
                            p_add.setText("Add");
                            p_cb.setVisible(false);
                        }
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }else{
                    try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  ProductID=?, ProductName=?, ProductDescription=?, ProductCategory=?, "
                            + "Quantity=?, OriginalPrice=?, ValuesSRP=?, Sale=? ,Image=?,Supplier=? WHERE ProductID='" + p_id.getText() + "'");
                        InputStream img = new FileInputStream(new File(imgpath));
                        pst.setString(1, p_id.getText());
                        pst.setString(2, p_name.getText());
                        pst.setString(3, p_des.getText());
                        pst.setString(4, p_cat.getText());
                        pst.setString(5, p_quan.getText());
                        pst.setString(6, p_org.getText());
                        pst.setString(7, p_val.getText());
                        pst.setString(8, p_sale.getText());
                        pst.setBlob(9, img);
                        pst.setString(10,(String)p_sup.getSelectedItem());
                        int update = pst.executeUpdate();
                        if (update != 0) {
                            refresh_item();
                            item_clear();
                            
                            JOptionPane.showMessageDialog(null,"Item Updated");
                            p_add.setText("Add");
                        }
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }               catch (FileNotFoundException ex) {
                        Logger.getLogger(HOME.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
        }
    }//GEN-LAST:event_p_addActionPerformed

    private void p_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_clearActionPerformed
        // TODO add your handling code here:
        item_clear();
        p_cb.setVisible(false);
    }//GEN-LAST:event_p_clearActionPerformed

    private void p_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_imageMouseClicked
        // TODO add your handling code here:

        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files'
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image*","jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save jfilechooser
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            // image.setIcon(ResizeImage(path,null));
            p_image.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(p_image.getWidth(), p_image.getHeight(), Image.SCALE_DEFAULT)));
            imgpath = path;
            p_image.setText("");
        }else if(result  == JFileChooser.CANCEL_OPTION){
            
            JOptionPane.showMessageDialog(null,"No File Selected");
        }
    }//GEN-LAST:event_p_imageMouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        // TODO add your handling code here:
        if (acctype.getText().equals("Admin")){

            login.setVisible(false);
            Sales.setVisible(false);
            Stocks.setVisible(false);
            ItemList.setVisible(false);
            Transactions.setVisible(false);
            Report.setVisible(false);
            Logs.setVisible(false);
            addaccount.setVisible(true);
            accountlist.setVisible(false);
            manageacc.setVisible(false);
            //Accounts.setVisible(true);

        }else{
            
            JOptionPane.showConfirmDialog(null, "Invalid user access!");
        }
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
        // TODO add your handling code here:
        if (acctype.getText().equals("Admin")){

            login.setVisible(false);
            Sales.setVisible(false);
            Stocks.setVisible(false);
            ItemList.setVisible(false);
            Transactions.setVisible(false);
            Report.setVisible(false);
            Logs.setVisible(false);
            addaccount.setVisible(false);
            accountlist.setVisible(true);
            manageacc.setVisible(false);
            refresh_admin();
            refresh_user();
        }else{
            
            JOptionPane.showConfirmDialog(null, "Invalid user access!");

        }
    }//GEN-LAST:event_jLabel49MouseClicked

    private void jLabel68MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseClicked
        // TODO add your handling code here:
        login.setVisible(false);
        Sales.setVisible(false);
        Stocks.setVisible(false);
        ItemList.setVisible(false);
        Transactions.setVisible(false);
        Report.setVisible(false);
        Logs.setVisible(false);
        addaccount.setVisible(false);
        accountlist.setVisible(false);
        manageacc.setVisible(true);
    }//GEN-LAST:event_jLabel68MouseClicked

    private void jToolBar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToolBar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jToolBar2MouseClicked

    private void AIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AIDMouseClicked
        // TODO add your handling code here:
        if (AID.getText().equals("[Click to Generate ID]")){
             Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hmmss");
                SimpleDateFormat st = new SimpleDateFormat("MMddyyyy");
                AID.setText("ID"+st.format(d)+s.format(d));
        }else{

        }
    }//GEN-LAST:event_AIDMouseClicked

    private void aupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aupdateActionPerformed
        // TODO add your handling code here:
        if(aupdate.getText().equals("Save")){

            if(AID.getText().equals("[Click to Generate ID]")){

                JOptionPane.showMessageDialog(null, "Invalid Account ID!!");

            }else if(atype.getSelectedItem().equals("-Select Type-")){

               
                JOptionPane.showMessageDialog(null, "Invalid Account Account Type!!");

            }else{

                String typ = atype.getSelectedItem().toString();
                if(typ.equals("Admin")){
                    try {
                        String sql = "Insert into tbl_admin (AccountID, Name, Username, Password, Address, ContactNo, Type, Status, Q1, A1, Q2, A2, Q3, A3) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                        pst.setString(1, AID.getText());
                        pst.setString(2, name.getText());
                        pst.setString(3, Uname.getText());
                        pst.setString(4, pword.getText());
                        pst.setString(5, add.getText());
                        pst.setString(6, con.getText());
                        pst.setString(7, (String) atype.getSelectedItem());
                        pst.setString(8, "Active");
                        pst.setString(9, (String) q1.getSelectedItem());
                        pst.setString(10, a1.getText());
                        pst.setString(11, (String) q2.getSelectedItem());
                        pst.setString(12, a2.getText());
                        pst.setString(13, (String) q3.getSelectedItem());
                        pst.setString(14, a3.getText());
                        if(q1.getSelectedItem().equals("- Select Question -")){
                             
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else if(a1.getText().equals("")){
                            
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else if(q2.getSelectedItem().equals("- Select Question -")){
                           
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else if(a2.getText().equals("")){
                           
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else if(q3.getSelectedItem().equals("- Select Question -")){
                            
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else if(a3.getText().equals("")){
                           
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else{
                            if (rpword.getText().equals(rpword.getText())){

                                pst.execute();
                                JOptionPane.showMessageDialog(null, "New Admin Succesfully Save!");
                                
                                refresh_tbladmin();
                                // AID.setText("ID"+a_date.getText()+a_time.getText());
                                user_clear();
                            }else{
                                JOptionPane.showMessageDialog(null, "Incorrect Password Match!!");
                               
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } else if(typ.equals("User")) {

                    try {
                        String sql = "Insert into tbl_user (AccountID, Name, Username, Password, Address, ContactNo, Type,Status, Q1, A1, Q2, A2, Q3, A3) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                        pst.setString(1, AID.getText());
                        pst.setString(2, name.getText());
                        pst.setString(3, Uname.getText());
                        pst.setString(4, pword.getText());
                        pst.setString(5, add.getText());
                        pst.setString(6, con.getText());
                        pst.setString(7, (String) atype.getSelectedItem());
                        pst.setString(8, "Active");
                        pst.setString(9, (String) q1.getSelectedItem());
                        pst.setString(10, a1.getText());
                        pst.setString(11, (String) q2.getSelectedItem());
                        pst.setString(12, a2.getText());
                        pst.setString(13, (String) q3.getSelectedItem());
                        pst.setString(14, a3.getText());
                        if(q1.getSelectedItem().equals("- Select Question -")){
                            
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else if(a1.getText().equals("")){
                            
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else if(q2.getSelectedItem().equals("- Select Question -")){
                            
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else if(a2.getText().equals("")){
                           
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else if(q3.getSelectedItem().equals("- Select Question -")){
                           
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else if(a3.getText().equals("")){
                            
                            JOptionPane.showMessageDialog(null, "Personal Security Q&A Information is required Please Check!! ");
                        }else{
                            if (rpword.getText().equals(rpword.getText())){

                                pst.execute();
                                JOptionPane.showMessageDialog(null, "New User Succesfully Save!");
                                
                                refresh_tbluser();
                                //AID.setText("ID"+a_date.getText()+a_time.getText());
                                user_clear();
                            }else{
                                JOptionPane.showMessageDialog(null, "Incorrect Password Match!!");
                               
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                }else{
                    
                    JOptionPane.showMessageDialog(null, "Please Select UserType");

                }

            }

        }else{}
    }//GEN-LAST:event_aupdateActionPerformed

    private void aclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aclearActionPerformed
        // TODO add your handling code here:
        if(aclear.getText().equals("CANCEL")){
            user_clear();
            aupdate.setText("SAVE");
        } if(aclear.getText().equals("CLEAR")){
            user_clear();
        }
    }//GEN-LAST:event_aclearActionPerformed

    private void tbl_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_userMouseClicked
        // TODO add your handling code here:
        int a = tbl_user.getSelectedRow();
        DefaultTableModel model =  (DefaultTableModel) tbl_user.getModel();
        adminid.setText(model.getValueAt(a,0).toString());
        try {
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_user WHERE AccountID = '" + adminid.getText() + "'");
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {

                String add1 = rs.getString("Status");
                if (add1.equals("Active")){
                    uact.setText("Deactivate");
                }else if(add1.equals("Inactive")){
                    uact.setText("Activate");
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tbl_userMouseClicked

    private void tbl_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_adminMouseClicked
        // TODO add your handling code here:
        int a = tbl_admin.getSelectedRow();
        DefaultTableModel model =  (DefaultTableModel) tbl_admin.getModel();
        adminid.setText(model.getValueAt(a,0).toString());
        try {
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_admin WHERE AccountID = '" + adminid.getText() + "'");
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {

                String add1 = rs.getString("Status");
                if (add1.equals("Active")){
                    aact.setText("Deactivate");
                }else if(add1.equals("Inactive")){
                    aact.setText("Activate");
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tbl_adminMouseClicked

    private void aactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aactActionPerformed
        // TODO add your handling code here:
        if(adminid.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please Select Account First!");
        }else{

            if (aact.getText().equals("Activate")){
                JFrame frame = new JFrame();
                String[] options = new String[2];
                options[0] = new String("Activate");
                options[1] = new String("Cancel");
                int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Activating Account Might Access Aystem Application! Do you want to Continue?","Activate", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
                if(p==0){

                    try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_admin SET Status=?  WHERE AccountID='" + adminid.getText() + "'");

                        pst.setString(1, "Active");
                        int update = pst.executeUpdate();
                        if (update != 0) {
                            refresh_tbladmin();
                            adminid.setText("");
                            auclear.disable();
                            JOptionPane.showMessageDialog(null, "Account Activated");
                           
                            //  x1.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please Select Account to Deactivate");
                            
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }

                }else if (p==1){
                    auclear.enable();
                    adminid.setText("");
                }

            } else if (aact.getText().equals("Deactivate")){
                JFrame frame = new JFrame();
                String[] options = new String[2];
                options[0] = new String("Deactivate");
                options[1] = new String("Cancel");
                int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Deactivating Account Might Cause Login Error! Do You Want to Continue?","Deactivate", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
                if(p==0){

                    try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_admin SET Status=?  WHERE AccountID='" + adminid.getText() + "'");

                        pst.setString(1, "Inactive");
                        int update = pst.executeUpdate();
                        if (update != 0) {
                            refresh_tbladmin();
                            adminid.setText("");
                            auclear.disable();
                            JOptionPane.showMessageDialog(null, "Admin Account Deactivated");
                            
                            // x1.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please Select Account to Deactivate");
                           
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }else if(p==1){
                    auclear.enable();
                    adminid.setText("");
                }
            }
        }
    }//GEN-LAST:event_aactActionPerformed

    private void uactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uactActionPerformed
        // TODO add your handling code here:
        if(adminid.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please Select Account First!");
        }else{

            if (uact.getText().equals("Activate")){
                JFrame frame = new JFrame();
                String[] options = new String[2];
                options[0] = new String("Activate");
                options[1] = new String("Cancel");
                int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Activating Account Might Access System Application! Do you want to Continue?","Activate", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
                if(p==0){

                    try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_user SET Status=?  WHERE AccountID='" + adminid.getText() + "'");

                        pst.setString(1, "Active");
                        int update = pst.executeUpdate();
                        if (update != 0) {
                            refresh_tbluser();
                            adminid.setText("");
                            uuclear.disable();
                            JOptionPane.showMessageDialog(null, "Account Activated");
                            
                            //  x1.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please Select Account to Deactivate");
                            
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }

                }else if (p==1){
                    uuclear.enable();
                    adminid.setText("");
                }

            } else if (uact.getText().equals("Deactivate")){
                JFrame frame = new JFrame();
                String[] options = new String[2];
                options[0] = new String("Deactivate");
                options[1] = new String("Cancel");
                int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Deactivating Account Might Cause Login Error! Do You Want to Continue?","Deactivate", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
                if(p==0){

                    try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_user SET Status=?  WHERE AccountID='" + adminid.getText() + "'");

                        pst.setString(1, "Inactive");
                        int update = pst.executeUpdate();
                        if (update != 0) {
                            refresh_tbluser();
                            adminid.setText("");
                            uuclear.disable();
                            JOptionPane.showMessageDialog(null, "Account Deactivated");
                            
                            //x1.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please Select Account to Deactivate");
                            
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }else if(p==1){
                    uuclear.enable();
                    adminid.setText("");
                }
            }
        }
    }//GEN-LAST:event_uactActionPerformed

    private void uuclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uuclearActionPerformed
        // TODO add your handling code here:
        uuclear.disable();
        refresh_tbluser();
        adminid.setText("");
    }//GEN-LAST:event_uuclearActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        if (cnpass.getText().equals(crpass.getText())){
            try{

                pst=(com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_admin SET Password=? WHERE Username='"+cuname.getText()+"' and Password='"+ccpass.getText()+"'  and AccountID='"+accid.getText()+"'");

                pst.setString(1,cnpass.getText());

                int admin_update = pst.executeUpdate();

                if (admin_update!=0){
                    changepass_clear();
                    //view_acc_det();
                    
                    JOptionPane.showMessageDialog(null, "Admin Account Password Updated");

                }else{
                    pst=(com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_user SET Password=? WHERE Username='"+cuname.getText()+"' and Password='"+ccpass.getText()+"' and AccountID='"+accid.getText()+"'");

                    pst.setString(1,cnpass.getText());
                    int user_update = pst.executeUpdate();
                    if (user_update!=0){
                        changepass_clear();
                        //view_acc_det();
                        
                        JOptionPane.showMessageDialog(null, "User Account Password Updated");

                    }else{
                        
                        JOptionPane.showMessageDialog(null, "Please check Username/Password");
                    }

                }

            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else{ 
            
            JOptionPane.showMessageDialog(null, "Incorrect password Match");
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        try{

            pst=(com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_admin SET A1=?,A2=?,A3=? WHERE AccountID='"+accid.getText()+"' and Name='"+user.getText()+"'");

            pst.setString(1,na1.getText());
            pst.setString(2,na2.getText());
            pst.setString(3,na3.getText());

            int admin_update = pst.executeUpdate();

            if (admin_update!=0){
                changepass_clear();
               
                JOptionPane.showMessageDialog(null, "Admin Account Security Updated");
                // view_acc_det();
                clear_ans();
            }else{
                pst=(com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_user SET A1=?,A2=?,A3=? WHERE AccountID='"+accid.getText()+"' and Name='"+user.getText()+"'");

                pst.setString(1,na1.getText());
                pst.setString(2,na2.getText());
                pst.setString(3,na3.getText());
                int user_update = pst.executeUpdate();
                if (user_update!=0){
                    changepass_clear();
                    
                    JOptionPane.showMessageDialog(null, "User Account Security Updated");
                    //view_acc_det();
                    clear_ans();
                }else{
                    
                    JOptionPane.showMessageDialog(null, "Please check Username/Password");
                }

            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        DateFormat dff = new SimpleDateFormat("MMM-dd-yyyy");
        rdel.setText(dff.format(r_date.getDate()));
        try {
            String sql = "SELECT ProductID,ProductName,ProductDescription,Price,Quantity,TotalPrice,Date,Time,StaffName FROM tbl_sales WHERE "
            + "ProductID like ? or ProductName like ? or ProductDescription like ? or Price like ? or "
            + "Quantity like ? or TotalPrice like ? or Date like ? or Time like ? or StaffName like ?  ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + rdel.getText()+ "%");
            pst.setString(2, "%" + rdel.getText()+ "%");
            pst.setString(3, "%" + rdel.getText()+ "%");
            pst.setString(4, "%" + rdel.getText()+ "%");
            pst.setString(5, "%" + rdel.getText()+ "%");
            pst.setString(6, "%" + rdel.getText()+ "%");
            pst.setString(7, "%" + rdel.getText()+ "%");
            pst.setString(8, "%" + rdel.getText()+ "%");
            pst.setString(9, "%" + rdel.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_sales.setModel(DbUtils.resultSetToTableModel(rs));
            empCount();
            daily.setSelected(false);
            monthly.setSelected(false);
            yearly.setSelected(false);
            report_sales.setText(Double.toString(total_report_Sales()));
            report_item.setText(Integer.toString(total_sales_summary()));
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void yearlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearlyActionPerformed
        // TODO add your handling code here:
        if (yearly.isSelected()){
            /// monthly.setSelected(false);
            //yearly.setSelected(false);
            Yearly_transation();
            monthly.setSelected(false);
            daily.setSelected(false);
            r_date.setDate(null);
            rdel.setText("");
        }else{
            refresh_sales();
            report_item.setText("0.00");
            report_sales.setText("0.00");
            //report_item.setText(Integer.toString(total_sales_summary()));
            //report_sales.setText(Double.toString(total_report_Sales()));
        }
    }//GEN-LAST:event_yearlyActionPerformed

    private void dailyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dailyActionPerformed
        // TODO add your handling code here:
        if (daily.isSelected()){
            Daily_transation();
            monthly.setSelected(false);
            yearly.setSelected(false);
            r_date.setDate(null);
            rdel.setText("");
        }else{
            refresh_sales();
            report_item.setText("0.00");
            report_sales.setText("0.00");
        }
    }//GEN-LAST:event_dailyActionPerformed

    private void monthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthlyActionPerformed
        // TODO add your handling code here:
        if (monthly.isSelected()){
            /// monthly.setSelected(false);
            //yearly.setSelected(false);
            Monthly_transation();
            daily.setSelected(false);
            yearly.setSelected(false);
            r_date.setDate(null);
            rdel.setText("");
        }else{
            refresh_sales();
            report_item.setText("0.00");
            report_sales.setText("0.00");
        }
    }//GEN-LAST:event_monthlyActionPerformed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void tbl_itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_itemMouseClicked
        // TODO add your handling code here:
        // asd
        if (jLabel94.getText().equals("Pending request shown Below")){
             int za = tbl_item.getSelectedRow();
                DefaultTableModel dmodel = (DefaultTableModel) tbl_item.getModel();
                String id = (dmodel.getValueAt(za, 0).toString());
                  try{
                    pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT ProductName FROM tbl_stock_request WHERE ProductID = '" + id + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        String cat = rs.getString("ProductName");
                        jComboBox2.setSelectedItem(cat);
                         pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT ProductCategory FROM tbl_stock_request WHERE ProductID = '" + id + "'");
                    rs = (ResultSet) pst.executeQuery();
                        tbl_itemcategory.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                  }catch(SQLException e){}
                
            JFrame frame = new JFrame();
            String[] options = new String[4];
            options[0] = new String("Confirm Selected");
            options[1] = new String("Confirm All");
            options[2] = new String("Remove Request");
            options[3] = new String("Cancel");
            int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Confirmation Stock In Request","Item request", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(p==0){
                int z = tbl_item.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) tbl_item.getModel();
                p_id.setText(model.getValueAt(z, 0).toString());
                try{
                    String sql1 = "Insert into tbl_items Select * from tbl_stock_request where ProductID='" +  p_id.getText() + "'";
                    pst = (PreparedStatement) conn.prepareStatement(sql1);
                    //int n = pst.executeUpdate();
                   // if (n!=0){

                        JFrame frame1 = new JFrame();
                        String[] options1 = new String[2];
                        options1[0] = new String("Continue");
                        options1[1] = new String("Cancel");
                        int p1 = JOptionPane.showOptionDialog(frame1.getContentPane(),"Continue process?","Confirmation Stock Request", 1,JOptionPane.INFORMATION_MESSAGE,null,options1,null);
                        if (p1==0){
                            pst.executeUpdate();
                            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_stock_request  WHERE ProductID = '" + p_id.getText() + "'");
                            pst.executeUpdate();

                            count_request();
                            refresh_stock_request();
                           
                            JOptionPane.showMessageDialog(null, p_id.getText()+" stored");
                            p_id.setText("[Click To Auto Generate ID]");
                        }else if (p1==1){
                            frame1.dispose();
                        }
//                    }else{
//                        JOptionPane.showMessageDialog(null, "Error Process!!");
//                    }

                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }

            }else if (p==1){
                int z = tbl_item.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) tbl_item.getModel();
                p_id.setText(model.getValueAt(z, 0).toString());
                try{
                    String sql1 = "Insert into tbl_items Select * from tbl_stock_request";
                    pst = (PreparedStatement) conn.prepareStatement(sql1);
                   // int n = 
//                    if (n!=0){
                                                 JFrame frame2 = new JFrame();
                                    String[] options2 = new String[2];
                                    options2[0] = new String("Continue");
                                    options2[1] = new String("Cancel");
                                      int p2 = JOptionPane.showOptionDialog(frame2.getContentPane(),"Continue process?","Confirmation Stock Request", 0,JOptionPane.INFORMATION_MESSAGE,null,options2,null);
                                      if (p2==0){
                            pst.executeUpdate();
                            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_stock_request");
                            pst.executeUpdate();
                            refresh_item();
                            empCount();
                            count_request();
                            jLabel94.setText("");
                            JOptionPane.showMessageDialog(null,"New Items stored.");
                          
                            p_id.setText("[Click To Auto Generate ID]");
                                          }else if (p2==1){
                                                frame2.dispose();
                                                }
                    //}
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null,ex);
                }

            }else if (p==2){
                int z = tbl_item.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) tbl_item.getModel();
                p_id.setText(model.getValueAt(z, 0).toString());
                int z1 = tbl_item.getSelectedRow();
                TableModel model1 = tbl_item.getModel();
                p_id.setText(model1.getValueAt(z1, 0).toString());
                try {

                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_stock_request  WHERE ProductID = '" + p_id.getText() + "'");
                    JFrame frame3 = new JFrame();
                                    String[] options3 = new String[2];
                                    options3[0] = new String("Continue");
                                    options3[1] = new String("Cancel");
                                      int p3 = JOptionPane.showOptionDialog(frame3.getContentPane(),"Are you sure you want to remove stock in request?","Confirmation Stock Request", 0,JOptionPane.INFORMATION_MESSAGE,null,options3,null);
                                      if (p3==0){
                                          pst.executeUpdate();
                            refresh_stock_request();
                             count_request();
                            //empCount();
                          
                            JOptionPane.showMessageDialog(null, "Stock In request data Removed!");
                            //empCount();
                            //sdf
                            p_id.setText("[Click To Auto Generate ID]");
                                          }  else if (p3==1){
                                          frame3.dispose();
                                          }

                  
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            }else if (p==3){

                frame.dispose();

            }

        }else if (jLabel94.getText().equals("")){
            
            int za = tbl_item.getSelectedRow();
                DefaultTableModel dmodel = (DefaultTableModel) tbl_item.getModel();
                String id = (dmodel.getValueAt(za, 0).toString());
                  try{
                    pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT ProductName FROM tbl_items WHERE ProductID = '" + id + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        String cat = rs.getString("ProductName");
                        jComboBox2.setSelectedItem(cat);
                         pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT ProductCategory FROM tbl_items WHERE ProductID = '" + id + "'");
                    rs = (ResultSet) pst.executeQuery();
                        tbl_itemcategory.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                  }catch(SQLException e){}
                  
            JFrame frame = new JFrame();
            String[] options = new String[3];
            options[0] = new String("Update");
            options[1] = new String("Delete");
            options[2] = new String("Dump Item");
            int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Select Option","Item", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(p==0){
                int z = tbl_item.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) tbl_item.getModel();
                p_id.setText(model.getValueAt(z, 0).toString());
                p_des.setText(model.getValueAt(z, 1).toString());
                p_quan.setText(model.getValueAt(z, 2).toString());
                p_org.setText(model.getValueAt(z, 3).toString());
                p_val.setText(model.getValueAt(z, 4).toString());
                p_sale.setText(model.getValueAt(z, 5).toString());
               cus_gender.setSelectedItem((String)model.getValueAt(z, 6).toString());
               
                
                try{
                    pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + p_id.getText() + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        byte[] img = rs.getBytes("Image");
                        String supp = rs.getString("Supplier");
                        String cat = rs.getString("ProductCategory");
                        p_name.setText(cat);
                        String des = rs.getString("ProductDescription");
                        p_des.setText(des);
                        String pname = rs.getString("ProductName");
                        p_name.setText(pname); 
                         //String supp = rs.getString("ProductCategory");
                        ImageIcon image1 =  new ImageIcon(img);
                        Image im = image1.getImage();
                        Image myImage = im.getScaledInstance(p_image.getWidth(), p_image.getHeight(), Image.SCALE_SMOOTH );
                        ImageIcon newImage = new ImageIcon(myImage);
                        p_image.setIcon(newImage);
                        
                        // p_sup.removeAllItems();
                        p_sup.setSelectedItem(supp);
                        p_cb.setVisible(true);
                        p_image.setText("");

                    }else{
                        JOptionPane.showMessageDialog(null,"No Image Data!!");
                         p_image.setText(null);
                        p_image.setText("Select Image");
                        
                         try{
                    pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + p_id.getText() + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        String supp = rs.getString("Supplier"); 
                        //p_sup.removeAllItems();
                        p_sup.setSelectedItem(supp);
                        p_cb.setVisible(true);
                    } 
                        }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                        
                        
                    }
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"No Image Data!!");
                     p_image.setText(null);
                        p_image.setText("Select Image");
                }
                p_image.setText("Select Image");
                p_add.setText("Update");
                login.setVisible(false);
                Sales.setVisible(false);
                Stocks.setVisible(true);
                ItemList.setVisible(false);
                Transactions.setVisible(false);
                Logs.setVisible(false);
                Accounts.setVisible(false);
                refresh_supplier();

            }else if (p==1){
                int z = tbl_item.getSelectedRow();
                TableModel model = tbl_item.getModel();
                p_id.setText(model.getValueAt(z, 0).toString());
                if (acctype.getText().equals("User")){
                    
                    JOptionPane.showMessageDialog(null, "Invalid User Access!!!!");
                }else{
                    try {

                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_items  WHERE ProductID = '" + p_id.getText() + "'");
                        
                            
                        JFrame frame1 = new JFrame();
                        String[] options1 = new String[2];
                        options1[0] = new String("Continue");
                        options1[1] = new String("Cancel");
                        int p1 = JOptionPane.showOptionDialog(frame1.getContentPane(),"Are you sure you want to remove this product?","Delete Item", 1,JOptionPane.INFORMATION_MESSAGE,null,options1,null);
                        if (p1==0){
                            pst.executeUpdate();
                         refresh_item();
                            empCount();
                            JOptionPane.showMessageDialog(null, "Data Removed");
                            //empCount();
                            p_id.setText("[Click To Auto Generate ID]");
                        
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }else if (p==2){
             int z = tbl_item.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) tbl_item.getModel();
               String PrdID = model.getValueAt(z, 0).toString();
               //String qty = model.getValueAt(z, 4).toString();
              try{
               pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + PrdID + "'");
                rs = pst.executeQuery();
                        if (rs.next()) {
                        String pid = rs.getString("ProductID");
                        String pname = rs.getString("ProductName");
                        String pdes = rs.getString("ProductDescription");
                        String pcat = rs.getString("ProductCategory");
                        String pqty = rs.getString("Quantity");
                        String porgprice = rs.getString("OriginalPrice");
                        String pval = rs.getString("ValuesSRP");
                        String psup = rs.getString("Supplier");
                        //byte[] img = rs.getBytes("Image");
                          pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + PrdID + "'");
                rs = pst.executeQuery();
                             if (rs.next()) {
                              String res = JOptionPane.showInputDialog(this,"Input quantity to dump");
                              
                             int a  = Integer.parseInt(pqty);
                             int b  = Integer.parseInt(res);
                             int tot = a - b;
                             if(b > a){
                            
                             JOptionPane.showMessageDialog(null, "The Quantity Input is Beyond the Item's Stock value!");
                             }else{
                              pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_Items SET Quantity=? WHERE ProductID='" + PrdID + "'");
                              
                        pst.setInt(1, tot);

                        int update1 = pst.executeUpdate();
                        if (update1 != 0) {
                        
                          pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_dump WHERE ProductID = '" + PrdID + "'");
                rs = pst.executeQuery();
                             if (rs.next()) {
                            String qty = rs.getString("Quantity");
                            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_dump SET Quantity=? WHERE ProductID='" + PrdID + "'");
                              int c = b + Integer.parseInt(qty);
                        pst.setInt(1, c);

                        int update = pst.executeUpdate();
                        if (update != 0) {
                       
                        JOptionPane.showMessageDialog(null, res+" Item of "+PrdID+" has been stored to dump!");
                      
                             refresh_item();
                                empCount();
                        }
                        
                                 } else {
                                        
                            String sql1 = "INSERT INTO tbl_dump(ProductID,ProductName,ProductDescription"
                                    + ",ProductCategory,Quantity,OriginalPrice,ValuesSRP,Supplier,Date,Time) VALUES (?,?,?,?,?,?,?,?,?,?)";

                            pst = (PreparedStatement) conn.prepareStatement(sql1);
                            pst.setString(1,pid);
                            pst.setString(2,pname);
                            pst.setString(3,pdes);
                            pst.setString(4,pcat);
                            pst.setString(5,res);
                            pst.setString(6,porgprice);
                            pst.setString(7,pval);
                           // pst.setByte(8,img);
                             pst.setString(8,psup);
                             pst.setString(9,s_date.getText());
                             pst.setString(10,s_time.getText());

                            int add = pst.executeUpdate();
                            if(add!=0){
                                  refresh_item();
                            empCount();
                                 
                            
                            }

                                 }
                        
                        }}
                             }else{
                             
                             
                             }    
                        }
                  } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                
            }
            
            }
    }//GEN-LAST:event_tbl_itemMouseClicked

    private void tbl_itemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_itemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_itemKeyPressed

    private void i_search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i_search1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_i_search1ActionPerformed

    private void i_search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_i_search1KeyReleased
        // TODO add your handling code here:
        if(jLabel94.getText().equals("")){
            
  //Select * From tbl_items 
            try {
                String sql1 = "Select DISTINCT ProductCategory from tbl_items where ProductCategory like ?";
                String sql = "SELECT ProductID,ProductName,ProductDescription,ProductCategory,Quantity,OriginalPrice,ValuesSRP,"
                + "Sale,Supplier FROM tbl_items WHERE "
                + "ProductID like ? or ProductName like ? or ProductDescription like ? or Quantity like ? "
                + "or OriginalPrice like ? or ValuesSRP like ? or Sale like ? or Supplier like ? ";

                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + i_search1.getText() + "%"); 
                pst.setString(2, "%" + i_search1.getText() + "%");
                pst.setString(3, "%" + i_search1.getText() + "%");
                pst.setString(3, "%" + i_search1.getText() + "%");
                pst.setString(4, "%" + i_search1.getText() + "%");
                pst.setString(5, "%" + i_search1.getText() + "%");
                pst.setString(6, "%" + i_search1.getText() + "%");
                pst.setString(7, "%" + i_search1.getText() + "%");
                pst.setString(8, "%" + i_search1.getText() + "%");
                pst.setString(9, "%" + i_search1.getText() + "%");
                rs = (ResultSet) pst.executeQuery();
                tbl_item.setModel(DbUtils.resultSetToTableModel(rs));
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql1);
                pst.setString(1, "%" + i_search1.getText() + "%"); 
                rs = (ResultSet) pst.executeQuery();
                tbl_itemcategory.setModel(DbUtils.resultSetToTableModel(rs));
                empCount();
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }

//            or ProductName like ? or ProductDescription like ? or ProductCategory like ? or Quantity like ? "
//                + "or OriginalPrice like ? or ValuesSRP like ? or Sale like ? or Supplier like ? 
        }else{

            try {
                String sql1 = "Select DISTINCT ProductCategory from tbl_stock_request where ProductCategory like ?";
                String sql = "SELECT ProductID,ProductName,ProductDescription,Quantity,OriginalPrice,ValuesSRP,"
                + "Sale,Supplier FROM tbl_stock_request WHERE "
                + "ProductID like ? or ProductName like ? or ProductDescription like ? or Quantity like ? "
                + "or OriginalPrice like ? or ValuesSRP like ? or Sale like ? or Supplier like ? ";

                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + i_search1.getText() + "%");
                pst.setString(2, "%" + i_search1.getText() + "%");
                pst.setString(3, "%" + i_search1.getText() + "%");
                pst.setString(3, "%" + i_search1.getText() + "%");
                pst.setString(4, "%" + i_search1.getText() + "%");
                pst.setString(5, "%" + i_search1.getText() + "%");
                pst.setString(6, "%" + i_search1.getText() + "%");
                pst.setString(7, "%" + i_search1.getText() + "%");
                pst.setString(8, "%" + i_search1.getText() + "%");
                pst.setString(9, "%" + i_search1.getText() + "%");
                rs = (ResultSet) pst.executeQuery();
                tbl_item.setModel(DbUtils.resultSetToTableModel(rs));
                  pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql1);
                pst.setString(1, "%" + i_search1.getText() + "%"); 
                rs = (ResultSet) pst.executeQuery();
                tbl_itemcategory.setModel(DbUtils.resultSetToTableModel(rs));
                empCount();
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }

        }
    }//GEN-LAST:event_i_search1KeyReleased

    private void st_prdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_st_prdnewActionPerformed
        // TODO add your handling code here:
        p_sup.removeAllItems();
        //refresh_supplier();
        login.setVisible(false);
        Sales.setVisible(false);
        Stocks.setVisible(true);
        ItemList.setVisible(false);
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
        p_cb.setVisible(false);
        item_clear();
        p_id.setText("[Click To Auto Generate ID]");
        p_add.setText("Add");
        pnl_cat_sel.setVisible(false);
    }//GEN-LAST:event_st_prdnewActionPerformed

    private void requestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestMouseClicked
        // TODO add your handling code here:
        if (request.getBackground().equals(new java.awt.Color(0,204,102))){
            refresh_stock_request();
            jComboBox2.removeAllItems();
            category_drop();
            jLabel94.setText("Pending request shown Below");
        }else if (request.getBackground().equals(new java.awt.Color(255,255,255))){

        }
    }//GEN-LAST:event_requestMouseClicked

    private void ItemListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ItemListKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ItemListKeyPressed

    private void ItemListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ItemListKeyReleased
        // TODO add your handling code here:
          if(evt.getKeyCode()==KeyEvent.VK_P){
           try{
            Connection connn = MysqlConnection.ConnectDB();
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\items.jrxml");
            //String query = "SELECT * FROM tbl_items";
            JRDesignQuery jrquery = new JRDesignQuery();
            //jrquery.setText(query);
            JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
            jasperDesign.setQuery(jrquery);
            HashMap para= new HashMap();
            JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
            JasperPrintManager.printReport(jp, true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
          }
    }//GEN-LAST:event_ItemListKeyReleased

    private void pnl_stockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnl_stockKeyReleased
        // TODO add your handling code here:
    
    }//GEN-LAST:event_pnl_stockKeyReleased

    private void pnl_logsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnl_logsKeyReleased
        // TODO add your handling code here:
          if(evt.getKeyCode()==KeyEvent.VK_P){
          try{
            Connection connn = MysqlConnection.ConnectDB();
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\logs.jrxml");
            String query = "SELECT * FROM tbl_login_history";
            JRDesignQuery jrquery = new JRDesignQuery();
            jrquery.setText(query);
            JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
            jasperDesign.setQuery(jrquery);
            JasperPrint jp = JasperFillManager.fillReport(jr,null,connn);
            JasperPrintManager.printReport(jp, true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
          
          }
    }//GEN-LAST:event_pnl_logsKeyReleased

    private void LogsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LogsKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_P){
          try{
            Connection connn = MysqlConnection.ConnectDB();
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\logs.jrxml");
            String query = "SELECT * FROM tbl_login_history";
            JRDesignQuery jrquery = new JRDesignQuery();
            jrquery.setText(query);
            JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
            jasperDesign.setQuery(jrquery);
            JasperPrint jp = JasperFillManager.fillReport(jr,null,connn);
            JasperPrintManager.printReport(jp, true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
          
          }
    }//GEN-LAST:event_LogsKeyReleased

    private void tbl_loginhisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_loginhisKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_P){
          try{
            Connection connn = MysqlConnection.ConnectDB();
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\logs.jrxml");
            String query = "SELECT * FROM tbl_login_history";
            JRDesignQuery jrquery = new JRDesignQuery();
            jrquery.setText(query);
            JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
            jasperDesign.setQuery(jrquery);
            JasperPrint jp = JasperFillManager.fillReport(jr,null,connn);
            JasperPrintManager.printReport(jp, true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
          
          }
    }//GEN-LAST:event_tbl_loginhisKeyReleased

    private void tbl_itemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_itemKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_P){
           try{
            Connection connn = MysqlConnection.ConnectDB();
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\items.jrxml");
            String query = "SELECT * FROM tbl_items";
            JRDesignQuery jrquery = new JRDesignQuery();
            jrquery.setText(query);
            JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
            jasperDesign.setQuery(jrquery);
            HashMap para= new HashMap();
            JasperPrint jp = JasperFillManager.fillReport(jr,null,connn);
            JasperPrintManager.printReport(jp, true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
          }
    }//GEN-LAST:event_tbl_itemKeyReleased

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        if(jLabel1.getText().equals("+ New Supplier")){
            jLabel1.setText("Refresh");
         new Supplier().setVisible(true);
        }else{
            p_sup.removeAllItems();
            refresh_supplier();
        jLabel1.setText("+ New Supplier");
        }
       
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
        if(jButton4.getText().contains("Deactivate"))  {
        
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_supplier SET Status=?  WHERE SupplierID='" + jLabel10.getText() + "'");

            pst.setString(1, "Inactive");
                       int update = pst.executeUpdate();
            if (update != 0) {
              
                JOptionPane.showMessageDialog(null, "Account Deactivated");
                jLabel10.setText("");
                refresh_suppliers();
            } else {
               
                JOptionPane.showMessageDialog(null, "Please Select Account to Deactivate");
            }
        }else if(jButton4.getText().contains("Set Active")){
        
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_supplier SET Status=?  WHERE SupplierID='" + jLabel10.getText() + "'");

            pst.setString(1, "Active");
                       int update = pst.executeUpdate();
            if (update != 0) {
                
                JOptionPane.showMessageDialog(null, "Account Activated");
                jLabel10.setText("");
                refresh_suppliers();
            } else {
                
                JOptionPane.showMessageDialog(null, "Please Select Account to Activate");
            }
        }
          } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txtusernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusernameKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try{
                pst = (PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_admin WHERE Username= '" + txtusername.getText() + "' and Password= '" + txtpassword.getText() + "' ");
                rs = pst.executeQuery();

                if (rs.next()) {
                    String add3 = rs.getString("Status");
                    if(add3.contentEquals("Active")){
                        loggedin();
                    }else if(add3.contentEquals("Inactive")){
                       
                    JOptionPane.showMessageDialog(null, "Account deactivated");
                        
                    }
                }else{
                    pst = (PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_user WHERE Username= '" + txtusername.getText() + "' and Password= '" + txtpassword.getText() + "' ");
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        String add3 = rs.getString("Status");
                        if(add3.contentEquals("Active")){
                            loggedin();
                        }else if(add3.contentEquals("Inactive")){
                         JOptionPane.showMessageDialog(null, "Account deactivated");
                        }
                    }else{
                         JOptionPane.showMessageDialog(null, "Incorrect Username/Password Match!!!");
                    }
                }
            }catch(SQLException x) {
                JOptionPane.showMessageDialog(null, x);
            }

        }
    }//GEN-LAST:event_txtusernameKeyReleased

    private void tbl_supplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_supplierMouseClicked
        // TODO add your handling code here:
        
         JFrame frame = new JFrame();
            String[] options = new String[2];
            options[0] = new String("Update");
            options[1] = new String("Select to set Active/Inactive");
//              int yx = tbl_customer.getSelectedRow();
//        TableModel model = tbl_customer.getModel();
//        String name = model.getValueAt(yx, 1).toString();
            int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Select Option","Message", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(p==0){
            
                  pnl_supp_update.setVisible(true);
        int y = tbl_supplier.getSelectedRow();
        TableModel modell = tbl_supplier.getModel();
        sup_id.setText(modell.getValueAt(y, 0).toString());
        supp_name.setText(modell.getValueAt(y, 1).toString());
        supp_address.setText(modell.getValueAt(y, 2).toString());
        supp_contact.setText(modell.getValueAt(y, 3).toString());
        sup_email.setText(modell.getValueAt(y, 4).toString());
        sup_remarks.setText(modell.getValueAt(y, 5).toString());
                
            
            }else if (p==1){
         int y = tbl_supplier.getSelectedRow();
        TableModel modell = tbl_supplier.getModel();
        jLabel10.setText(modell.getValueAt(y, 0).toString());
        
           try {
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_supplier WHERE SupplierID = '" +jLabel10.getText()+ "'");
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {

               String add1 = rs.getString("Status");
               
               if (add1.contains("Inactive")){
               jButton4.setText("Set Active");
                 //tbl_cust.setEnabled(true);
               }else if (add1.contains("Active")){
               jButton4.setText("Deactivate");
                           }
            } 
                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
            }
    }//GEN-LAST:event_tbl_supplierMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         refresh_suppliers();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void supp_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supp_searchKeyReleased
        // TODO add your handling code here:
        try {
                String sql = "SELECT * FROM tbl_supplier WHERE "
                + "SupplierID like ? or Supplier like ? or Address like ? or ContactNumber like ? or Email like ? "
                + "or Remarks like ? or Status like ? ";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + supp_search.getText() + "%");
                pst.setString(2, "%" + supp_search.getText() + "%");
                pst.setString(3, "%" + supp_search.getText() + "%");
                pst.setString(3, "%" + supp_search.getText() + "%");
                pst.setString(4, "%" + supp_search.getText() + "%");
                pst.setString(5, "%" + supp_search.getText() + "%");
                pst.setString(6, "%" + supp_search.getText() + "%");
                pst.setString(7, "%" + supp_search.getText() + "%");
                rs = (ResultSet) pst.executeQuery();
                tbl_supplier.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }

    }//GEN-LAST:event_supp_searchKeyReleased

    private void p_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_cbActionPerformed
        // TODO add your handling code here:
        if (p_cb.isSelected()){
        refresh_supplier();
        }else{       
                try{
                    
                     pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID= '" + p_id.getText() + "' ");
            rs = pst.executeQuery();
            if (rs.next()) {
                String add3 = rs.getString("Supplier");
                p_sup.removeAllItems();
                p_sup.setSelectedItem(add3);
            }
         } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }
        
        }
         
    }//GEN-LAST:event_p_cbActionPerformed

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_jLabel57MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        this.setState(1);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void s_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_searchKeyReleased
        // TODO add your handling code here:
     if(s_search.getText().equals("")){
     refresh_customer();
     pnl_cus1.setVisible(false);
     jLabel60.setVisible(true);
         jLabel59.setVisible(true);
         jLabel59.setText("");
         jLabel127.setVisible(true);
         jLabel105.setVisible(true);
     }else{
         pnl_cus1.setVisible(true);
         jLabel60.setVisible(false);
         jLabel59.setVisible(false);
         jLabel127.setVisible(false);
         jLabel105.setVisible(false);
            try {
                String sql = "SELECT Fullname,CustomerStatus FROM tbl_customer WHERE Fullname like ? and CommitmentStatus= '" +"Active" + "' ";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + s_search.getText() + "%"); 
               //pst.setString(2, "%" + "Active" + "%");
                rs = (ResultSet) pst.executeQuery();
                tbl_cus.setModel(DbUtils.resultSetToTableModel(rs));
                 
                
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }
         
     }
        
    }//GEN-LAST:event_s_searchKeyReleased

    private void jLabel58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseClicked
        // TODO add your handling code here:
        new newcustomer().setVisible(true);
    }//GEN-LAST:event_jLabel58MouseClicked

    private void tbl_cusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_cusMouseClicked
        // TODO add your handling code here:
          int z = tbl_cus.getSelectedRow();
           DefaultTableModel model = (DefaultTableModel) tbl_cus.getModel();
                s_search.setText(model.getValueAt(z, 0).toString());
                jLabel59.setText(model.getValueAt(z, 1).toString());
                pnl_cus1.setVisible(false);
                 jLabel60.setVisible(true);
         jLabel59.setVisible(true);
         //String number = "0123456789";
             
             if(jLabel59.getText().equals("Walk In")){
             
                 try {
             pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + s_barcode.getText() + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                String add8 = rs.getString("ValuesSRP");
                s_price.setText(add8);
            s_qty.setText("");
            s_total.setText("");
            s_add.setEnabled(false);
            }else{}
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }
                 
             }else if(jLabel59.getText().equals("Regular")){
             
                    try {
             pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + s_barcode.getText() + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                String add8 = rs.getString("OriginalPrice");
                s_price.setText(add8);
            s_qty.setText("");
            s_total.setText("");
            s_add.setEnabled(false);
            }else{}
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }
             }
         
         
             
    }//GEN-LAST:event_tbl_cusMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
         try{
        if(jButton7.getText().contains("Deactivate"))  {
        
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_customer SET CommitmentStatus=?  WHERE CustomerID='" + jLabel75.getText() + "'");

            pst.setString(1, "Inactive");
                       int update = pst.executeUpdate();
            if (update != 0) {
                
                JOptionPane.showMessageDialog(null, "Account Deactivated");
                jLabel75.setText("");
                refresh_customerlist();
            } else {
               
                JOptionPane.showMessageDialog(null, "Please Select Account to Deactivate");
            }
        }else if(jButton7.getText().contains("Set Active")){
        
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_customer SET  CommitmentStatus=?  WHERE CustomerID='" + jLabel75.getText() + "'");

            pst.setString(1, "Active");
                       int update = pst.executeUpdate();
            if (update != 0) {
              
                JOptionPane.showMessageDialog(null, "Account Activated");
                jLabel75.setText("");
                refresh_customerlist();
            } else {
               
                JOptionPane.showMessageDialog(null, "Please Select Account to Activate");
            }
        }
          } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        refresh_customerlist();
        jLabel75.setText("");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jLabel62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseClicked
        // TODO add your handling code here:
        customerCount();
        refresh_customerlist();
        customer.setVisible(true);
        login.setVisible(false);
        Sales.setVisible(false);
        Stocks.setVisible(false);
        ItemList.setVisible(false);
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
        addaccount.setVisible(false);
        accountlist.setVisible(false);
        Supplier.setVisible(false);
        
        pnl_cus_update.setVisible(false);
    }//GEN-LAST:event_jLabel62MouseClicked

    private void tbl_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_customerMouseClicked
        // TODO add your handling code here:
         JFrame frame = new JFrame();
            String[] options = new String[2];
            options[0] = new String("Update");
            options[1] = new String("Select to set Active/Inactive");
             int yx = tbl_customer.getSelectedRow();
        TableModel model = tbl_customer.getModel();
        String name = model.getValueAt(yx, 1).toString();
            int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Select Option",""+name+"", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(p==0){
            
                pnl_cus_update.setVisible(true);
        int y = tbl_customer.getSelectedRow();
        TableModel modell = tbl_customer.getModel();
        cus_id.setText(modell.getValueAt(y, 0).toString());
        cus_name.setText(modell.getValueAt(y, 1).toString());
        cus_gender.setSelectedItem(modell.getValueAt(y, 2).toString());
        cus_age.setText(modell.getValueAt(y, 3).toString());
        cus_street.setText(modell.getValueAt(y, 4).toString());
        cus_city.setText(modell.getValueAt(y, 5).toString());
        cus_contact.setText(modell.getValueAt(y, 6).toString());
        cus_email.setText(modell.getValueAt(y, 7).toString());
        
                String stat = modell.getValueAt(y,8).toString();
                if (stat.equals("Walk In")){
                cus_walkin.setSelected(true);
                jLabel111.setText("Walk In");
                }else if(stat.equals("Regular")){
                     cus_regular.setSelected(true);
                     jLabel111.setText("Regular");
            }
                
                //tbl_customer.setEnabled(false);
            }else if(p==1){
            int y = tbl_customer.getSelectedRow();
        TableModel modell = tbl_customer.getModel();
        jLabel75.setText(modell.getValueAt(y, 0).toString());
        
           try {
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_customer WHERE CustomerID = '" +jLabel75.getText()+ "'");
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {

               String add1 = rs.getString("CommitmentStatus");
               
               if (add1.contains("Inactive")){
               jButton7.setText("Set Active");
                 //tbl_cust.setEnabled(true);
               }else if (add1.contains("Active")){
               jButton7.setText("Deactivate");
                           }
            } 
                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
            }
        
    }//GEN-LAST:event_tbl_customerMouseClicked

    private void jLabel100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MouseClicked
        // TODO add your handling code here:
        pnl_cus_update.setVisible(false);
        //tbl_customer.setEnabled(true);
       clear_customer();
    }//GEN-LAST:event_jLabel100MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        clear_customer();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
              try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_customer SET  Fullname=?, Gender=?, Age=?, "
                            + "Street=?, City=?, Contact=?, Email=?, CustomerStatus=? WHERE CustomerID='" + cus_id.getText() + "'");
                       
                        pst.setString(1, cus_name.getText());
                        pst.setString(2, (String)cus_gender.getSelectedItem());
                        pst.setString(3, cus_age.getText());
                        pst.setString(4, cus_street.getText());
                        pst.setString(5, cus_city.getText());
                        pst.setString(6, cus_contact.getText());
                        pst.setString(7, cus_email.getText());
                        pst.setString(8, jLabel111.getText());
                         int update = pst.executeUpdate();
                        if (update != 0) {
                            
                            JOptionPane.showMessageDialog(null,"Customer '"+cus_id.getText()+"' Updated!");
                            refresh_customerlist();
                             clear_customer();
                        }else{
                        
                        JOptionPane.showMessageDialog(null,"Customer '"+cus_id.getText()+"' Update Error!");
                        }
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    } 
        
//        pnl_cus_update.setVisible(false);

    }//GEN-LAST:event_jButton10ActionPerformed

    private void tbl_customerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_customerMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_customerMouseEntered

    private void jLabel102MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MouseClicked
        // TODO add your handling code here:
        pnl_supp_update.setVisible(false);
        clear_supplier();
    }//GEN-LAST:event_jLabel102MouseClicked

    private void supp_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supp_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supp_addressActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        clear_supplier();
        
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void tbl_customerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_customerMousePressed
        // TODO add your handling code here:
       pnl_cus_update.setVisible(false);
        tbl_customer.setEnabled(true);
        
         cus_id.setText("");
        cus_name.setText("");
        cus_age.setText("");
        cus_street.setText("");
        cus_city.setText("");
        cus_contact.setText("");
        cus_email.setText("");
        cus_walkin.setSelected(false);
        cus_regular.setSelected(false);
    }//GEN-LAST:event_tbl_customerMousePressed

    private void jLabel102MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MousePressed
        // TODO add your handling code here:
         pnl_supp_update.setVisible(false);
         sup_id.setText("");
        supp_name.setText("");
        supp_address.setText("");
        supp_contact.setText("");
        sup_remarks.setText("");
        sup_email.setText("");
    }//GEN-LAST:event_jLabel102MousePressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_supplier SET  Supplier=?, Address=?, ContactNumber=?, "
                            + "Email=?, Remarks=? WHERE SupplierID='" + sup_id.getText() + "'");
                       
                        pst.setString(1, supp_name.getText());
                        pst.setString(2, supp_address.getText());
                        pst.setString(3, supp_contact.getText());
                        pst.setString(4, sup_email.getText());
                        pst.setString(5, sup_remarks.getText());
                         int update = pst.executeUpdate();
                        if (update != 0) {
                           
                            JOptionPane.showMessageDialog(null,"Supplier ID '"+sup_id.getText()+"' Updated!");
                            refresh_suppliers();
                             clear_supplier();
                            
                           // p_add.setText("Add");
                        }else{
                       
                        JOptionPane.showMessageDialog(null,"Supplier ID '"+sup_id.getText()+"' Update Error!");
                        }
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    } 
    }//GEN-LAST:event_jButton11ActionPerformed

    private void cus_walkinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cus_walkinActionPerformed
        // TODO add your handling code here:
        jLabel111.setText("Walk In");
    }//GEN-LAST:event_cus_walkinActionPerformed

    private void cus_regularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cus_regularActionPerformed
        // TODO add your handling code here:
        jLabel111.setText("Regular");
    }//GEN-LAST:event_cus_regularActionPerformed

    private void tbl_supplierMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_supplierMousePressed
        // TODO add your handling code here:
        pnl_supp_update.setVisible(false);
    }//GEN-LAST:event_tbl_supplierMousePressed

    private void todayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayActionPerformed
        // TODO add your handling code here:
      try {
        if(today.getText().equals("  TODAY'S PURCHASE")){
         
            if(today.isSelected()){
            String sql = "SELECT * FROM tbl_customer_purchase WHERE "
                    + "Date like ?  ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));
            
            }
            else{
              
              refresh_purcshse_list();
            }
            
         }else {
                if(today.isSelected()){
               String sql = "SELECT * FROM tbl_return_history WHERE "
                    + "Date like ?   ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));
                }
                else{
                refresh_purcshse_history();
                }
        }
          } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_todayActionPerformed

    private void tbl_purchase_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_purchase_listMouseClicked
        // TODO add your handling code here:
        if (today.getText().equals("  TODAY'S PURCHASE")){
          int y = tbl_purchase_list.getSelectedRow();
        TableModel modell = tbl_purchase_list.getModel();
        String ProdID = modell.getValueAt(y,2).toString();
        String date = modell.getValueAt(y,8).toString();
        String time = modell.getValueAt(y,9).toString();
            JFrame frame = new JFrame();
            String[] options = new String[2];
            options[0] = new String("Return Purchase");
            options[1] = new String("Cancel");
            int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Select Option","Item Return", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(p==0){
                
                 try{
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + ProdID + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                
                String itemqty = rs.getString("Quantity");
               
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_customer_purchase WHERE ProductID = '" + ProdID + "' and Date ='"+date+"' and Time = '" + time + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                String customer = rs.getString("Customer");
                String customerstat = rs.getString("CustomerStatus");
                String prodid = rs.getString("ProductID");
                String prodname = rs.getString("ProductName");
                String proddes = rs.getString("ProductDescription");
                String price = rs.getString("Price");
                String cusqty = rs.getString("Quantity");
                String totalamount = rs.getString("TotalAmount");
                String date1 = rs.getString("Date");
                String time1 = rs.getString("Time");
                String staff = rs.getString("Staffname");
                if(cusqty.equals("1")){
            
                     int item,pur,total; 
                item = Integer.parseInt(itemqty);
                    pur = Integer.parseInt(cusqty);
                total = item + pur;
                
                 pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  Quantity=? WHERE ProductID='" + ProdID + "'");

                    pst.setInt(1, total);
                    int update = pst.executeUpdate();
                    if (update != 0) {
                        
                        String sql= "INSERT INTO tbl_return_history VALUES  (?,?,?,?,?,?,?,?,?,?,?,?)";
                                pst = (PreparedStatement) conn.prepareStatement(sql);
                                String reason = JOptionPane.showInputDialog(this,"State Reason");
                                pst.setString(1,customer);
                                pst.setString(2,customerstat);
                                pst.setString(3, prodid);
                                pst.setString(4, prodname);
                                pst.setString(5, proddes);
                                pst.setString(6, price);
                                pst.setString(7, cusqty);
                                pst.setString(8, totalamount);
                                pst.setString(9, s_date.getText());
                                pst.setString(10, s_time.getText());
                                pst.setString(11, staff);
                                pst.setString(12, reason);
                                 pst.executeUpdate();
                        
                         pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_customer_purchase WHERE ProductID='" + ProdID + "' and Date ='"+date+"' and Time = '" + time + "'");
                                   int del11 =  pst.executeUpdate();
                                   if(del11 >0){
                                   pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_sales WHERE ProductID='" + ProdID + "' and Date ='"+date+"' and Time = '" + time + "'");
                                   pst.executeUpdate();
                                        refresh_purcshse_list();
                    
                     JOptionPane.showMessageDialog(null, "Item Retuned!");
                   
                    
                                }
                   // String res = JOptionPane.showInputDialog(this,"Input quantity return");
                    
                    
                    }
                    
            }else{
            String[] options1 = new String[3];
            options1[0] = new String("Continue");
            options1[1] = new String("Input Quantity");
            options1[2] = new String("Cancel");
            int p1 = JOptionPane.showOptionDialog(frame.getContentPane(),"Multiple Quantity have found! Please Select Options","Item Return", 0,JOptionPane.INFORMATION_MESSAGE,null,options1,null);
            if(p1==0){
            
                 int item,pur,total; 
                item = Integer.parseInt(itemqty);
                    pur = Integer.parseInt(cusqty);
                total = item + pur;
                
                 pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  Quantity=? WHERE ProductID='" + ProdID + "'");

                    pst.setInt(1, total);
                    int update = pst.executeUpdate();
                    if (update != 0) {
                        
                        String sql= "INSERT INTO tbl_return_history VALUES  (?,?,?,?,?,?,?,?,?,?,?,?)";
                                pst = (PreparedStatement) conn.prepareStatement(sql);
                                String reason = JOptionPane.showInputDialog(this,"State Reason");
                                pst.setString(1,customer);
                                pst.setString(2,customerstat);
                                pst.setString(3, prodid);
                                pst.setString(4, prodname);
                                pst.setString(5, proddes);
                                pst.setString(6, price);
                                pst.setString(7, cusqty);
                                pst.setString(8, totalamount);
                                pst.setString(9, s_date.getText());
                                pst.setString(10, s_time.getText());
                                pst.setString(11, staff);
                                pst.setString(12, reason);
                                 pst.executeUpdate();
                        
                        
                          pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_customer_purchase WHERE ProductID='" + ProdID + "' and Date ='"+date+"' and Time = '" + time + "'");
                                   int del11 =  pst.executeUpdate();
                                   if(del11 >0){
                                   pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_sales WHERE ProductID='" + ProdID + "' and Date ='"+date+"' and Time = '" + time + "'");
                                   pst.executeUpdate();
                                        refresh_purcshse_list();
                       
                     JOptionPane.showMessageDialog(null, "Item Retuned!");
                    //jLabel126.setText("");
                    }}
            }else if (p1==1){
            
                
                String res = JOptionPane.showInputDialog(this,"Input quantity return");
                int q = Integer.parseInt(res);
                int pur1 = Integer.parseInt(cusqty);
                if (q>pur1){
                JOptionPane.showMessageDialog(null, "The input Quantity value is beyond the customers item purchased!");
                
                }else{
                     pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_sales WHERE ProductID = '" + ProdID + "' and Date ='"+date+"' and Time = '" + time + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                String st_price = rs.getString("TotalPrice");
                     int q1  = Integer.parseInt(res);
                    int item,pur,total ,sales_price,totalprice,salest_price; 
                item = Integer.parseInt(itemqty);
                    pur = Integer.parseInt(res);
                    sales_price = Integer.parseInt(price);
                    salest_price = Integer.parseInt(st_price);
                total = item + pur;
                totalprice = (pur*sales_price)-salest_price;
                 pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  Quantity=? WHERE ProductID='" + ProdID + "'");

                    pst.setInt(1, total);
                    int update = pst.executeUpdate();
                    if (update != 0) {
                        int a  = Integer.parseInt(res);
                        int b = Integer.parseInt(cusqty);
                        
                int total1 = b - a;
                 pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_customer_purchase SET  Quantity=?,TotalAmount WHERE ProductID='" + ProdID + "' and Date='" + date + "' and Time='" + time + "'");
                 
                    pst.setInt(1, total1);
                    pst.setInt(2, totalprice);
                    int update1 = pst.executeUpdate();
                    if (update1 != 0) {
                        
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_sales SET  Quantity=?,TotalPrice WHERE ProductID='" + ProdID + "' and Date='" + date + "' and Time='" + time + "'");
                        pst.setInt(1, total1);
                        pst.setInt(2, totalprice);
                        int next = pst.executeUpdate();
                        if (next!=0){
                         
                        String sql= "INSERT INTO tbl_return_history VALUES  (?,?,?,?,?,?,?,?,?,?,?,?)";
                                pst = (PreparedStatement) conn.prepareStatement(sql);
                                String reason = JOptionPane.showInputDialog(this,"State Reason");
                                pst.setString(1,customer);
                                pst.setString(2,customerstat);
                                pst.setString(3, prodid);
                                pst.setString(4, prodname);
                                pst.setString(5, proddes);
                                pst.setString(6, price);
                                pst.setString(7, res);
                                pst.setString(8, totalamount);
                                pst.setString(9, s_date.getText());
                                pst.setString(10, s_time.getText());
                                pst.setString(11, staff);
                                pst.setString(12, reason);
                                 pst.executeUpdate();
                        }else{
                         
                        String sql= "INSERT INTO tbl_return_history VALUES  (?,?,?,?,?,?,?,?,?,?,?,?)";
                                pst = (PreparedStatement) conn.prepareStatement(sql);
                                String reason = JOptionPane.showInputDialog(this,"State Reason");
                                pst.setString(1,customer);
                                pst.setString(2,customerstat);
                                pst.setString(3, prodid);
                                pst.setString(4, prodname);
                                pst.setString(5, proddes);
                                pst.setString(6, price);
                                pst.setString(7, res);
                                pst.setString(8, totalamount);
                                pst.setString(9, s_date.getText());
                                pst.setString(10, s_time.getText());
                                pst.setString(11, staff);
                                pst.setString(12, reason);
                                 pst.executeUpdate();
                        }
                       
                        
                        if (total1 == 0 ){
                        
                             pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_customer_purchase WHERE ProductID='" + ProdID + "' and Date ='"+date+"' and Time = '" + time + "'");
                                   int del11 =  pst.executeUpdate();
                                   if(del11 >0){
                                       pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_sales WHERE ProductID='" + ProdID + "' and Date ='"+date+"' and Time = '" + time + "'");
                                    pst.executeUpdate();
                                       
                                        refresh_purcshse_list();
                    
                     JOptionPane.showMessageDialog(null, "Item Retuned!");
                    //jLabel126.setText("");
                    
                                }
                            
                        }else{
                     refresh_purcshse_list();
                    
                     JOptionPane.showMessageDialog(null, "Item Retuned!");
                    //jLabel126.setText("");
                        }}}}}
                
                
            }else if (p1==2){frame.dispose();}
                    }}}
             } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    } 
                
                 
            }else if (p==1){
            frame.dispose();
            }
            
        }else{}
           
    }//GEN-LAST:event_tbl_purchase_listMouseClicked

    private void jLabel126MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel126MouseClicked
        // TODO add your handling code here:
        try{
            
        if(jLabel126.getText().equals("View Customer Purchase")){
            if(today.isSelected()){
         String sql = "SELECT * FROM tbl_customer_purchase WHERE "
                    + "Date like ?";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));
              today.setText("  TODAY'S PURCHASE");
         jLabel126.setText("View Item Return History");
         }else{
         refresh_purcshse_list();
         today.setText("  TODAY'S PURCHASE");
         jLabel126.setText("View Item Return History");
         
        }
         
        }else{
         
         if(today.isSelected()){
         String sql = "SELECT * FROM tbl_return_history WHERE "
                    + "Date like ?";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));
            today.setText("  TODAY'S RETURNED");
         jLabel126.setText("View Customer Purchase");
         }else
         {
         
        today.setText("  TODAY'S RETURNED");
          refresh_purcshse_history();
         jLabel126.setText("View Customer Purchase");
         }
         
        }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }  
    }//GEN-LAST:event_jLabel126MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
       refresh_dump_item();
        
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        try {
         if(jTextField2.getText().equals("")) {
             
             if (today.getText().equals("  TODAY'S PURCHASE")){
               refresh_purcshse_list();
             }else{
             refresh_purcshse_history();
             }
             
         }else{
            
            if(today.getText().equals("  TODAY'S PURCHASE")){
            String sql = "SELECT * FROM tbl_customer_purchase WHERE "
                + "Customer like ? or CustomerStatus like ? or ProductID like ? or ProductName like ? or ProductDescription like ? or Price like ? or Quantity like ? "
                + "or TotalAmount like ? or Date like ? or Time like ? or Staffname like ? ";

                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + jTextField2.getText() + "%"); 
                pst.setString(2, "%" + jTextField2.getText() + "%");
                pst.setString(3, "%" + jTextField2.getText() + "%");
                pst.setString(3, "%" + jTextField2.getText() + "%");
                pst.setString(4, "%" + jTextField2.getText() + "%");
                pst.setString(5, "%" + jTextField2.getText() + "%");
                pst.setString(6, "%" + jTextField2.getText() + "%");
                pst.setString(7, "%" + jTextField2.getText() + "%");
                pst.setString(8, "%" + jTextField2.getText() + "%");
                pst.setString(9, "%" + jTextField2.getText() + "%");
                pst.setString(10, "%" + jTextField2.getText() + "%");
                pst.setString(11, "%" + jTextField2.getText() + "%");
                
                rs = (ResultSet) pst.executeQuery();
                tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));
                today.setSelected(false);
            }else{
             String sql = "SELECT * FROM tbl_return_history WHERE "
                + "Customer like ? or CustomerStatus like ? or ProductID like ? or ProductName like ? or ProductDescription like ? or Price like ? or Quantity like ? "
                + "or TotalAmount like ? or Date like ? or Time like ? or Staffname like ? ";

                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + jTextField2.getText() + "%"); 
                pst.setString(2, "%" + jTextField2.getText() + "%");
                pst.setString(3, "%" + jTextField2.getText() + "%");
                pst.setString(3, "%" + jTextField2.getText() + "%");
                pst.setString(4, "%" + jTextField2.getText() + "%");
                pst.setString(5, "%" + jTextField2.getText() + "%");
                pst.setString(6, "%" + jTextField2.getText() + "%");
                pst.setString(7, "%" + jTextField2.getText() + "%");
                pst.setString(8, "%" + jTextField2.getText() + "%");
                pst.setString(9, "%" + jTextField2.getText() + "%");
                pst.setString(10, "%" + jTextField2.getText() + "%");
                pst.setString(11, "%" + jTextField2.getText() + "%");
                rs = (ResultSet) pst.executeQuery();
                tbl_purchase_list.setModel(DbUtils.resultSetToTableModel(rs));
                today.setSelected(false);
            }
         }
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }
        
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jLabel113MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel113MouseClicked
        // TODO add your handling code here:
            try{
            Connection connn = MysqlConnection.ConnectDB();
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\report1.jrxml");
           // String query = "SELECT * FROM tbl_items";
            JRDesignQuery jrquery = new JRDesignQuery();
            //jrquery.setText(query);
            JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
            jasperDesign.setQuery(jrquery);
            HashMap para= new HashMap();
             para.put("User", user.getText());
            JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
            JasperPrintManager.printReport(jp, true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jLabel113MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
         try{
            Connection connn = MysqlConnection.ConnectDB();
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\Sales_Report.jrxml");
           // String query = "SELECT * FROM tbl_items";
            JRDesignQuery jrquery = new JRDesignQuery();
            //jrquery.setText(query);
            JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
            jasperDesign.setQuery(jrquery);
            HashMap para= new HashMap();
             para.put("tqty", totqty.getText());
             para.put("tprice", totprice.getText());
             para.put("tcash", s_cash.getText());
             para.put("tchange", s_change.getText());
             para.put("customer", s_search.getText());
            JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
            JasperPrintManager.printReport(jp, true);
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_cashier");
                int del = pst.executeUpdate();
                if (del > 0) {
                    refresh_cashier();
                    v_id.setText("");
                    totprice.setText(Double.toString(total_price()));
                    totqty.setText(Integer.toString(total_qty()));
                    s_cash.setText("");
                    s_change.setText("");
                    s_search.setText("");
                    jLabel59.setText("");
                    jButton14.setEnabled(false);
         jLabel127.setVisible(true);
         jLabel105.setVisible(true);
                }else{
                   
                    JOptionPane.showMessageDialog(null, "Error Selection!!");
                }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (jButton6.getText().equals("Switch Dump")){
            Dump_Sale.setVisible(false);
            Dump_list.setVisible(true);
            jButton6.setText("Switch Sell");
            refresh_dump_item();
            jButton15.setEnabled(false);
            jButton13.setText("Dump Report");
            d_qty1.setEnabled(true);
        }else{
        Dump_Sale.setVisible(true);
            Dump_list.setVisible(false);
            jButton6.setText("Switch Dump");
            jButton15.setEnabled(true);
            jButton13.setText("Sell Report");
            d_qty1.setEnabled(false);
            today_sold.setSelected(true);
            
            try{
            String sql = "SELECT * FROM tbl_dump_sale WHERE Date like ?  ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_dump_sale.setModel(DbUtils.resultSetToTableModel(rs));
            dump_tprice.setText(Double.toString(total_dump_price()));
            dump_tqty.setText(Integer.toString(total_dump_qty()));
            }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tbl_dumpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dumpMouseClicked
        // TODO add your handling code here:
        
        if(jTextField6.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Please input Customer First!");
        }else{
                try{
                    pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_customer WHERE Fullname= '" + jTextField6.getText() + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        String stat = rs.getString("CustomerStatus");
                         int y = tbl_dump.getSelectedRow();
                        TableModel modell = tbl_dump.getModel();
                        if (stat.equals("Walk In")){
                            d_qty1.setText("");
                            d_price.setText("");
                            d_total.setText(""); 
                            d_id.setText(modell.getValueAt(y, 0).toString());
                            d_name.setText(modell.getValueAt(y, 1).toString());
                            d_des.setText(modell.getValueAt(y, 2).toString());
                            d_cat.setText(modell.getValueAt(y, 3).toString());
                            d_qty.setText(modell.getValueAt(y, 4).toString());
                            d_price.setText(modell.getValueAt(y, 6).toString());
                        }else if (stat.equals("Regular")){
                            d_qty1.setText("");
                            d_price.setText("");
                            d_total.setText(""); 
                            d_id.setText(modell.getValueAt(y, 0).toString());
                            d_name.setText(modell.getValueAt(y, 1).toString());
                            d_des.setText(modell.getValueAt(y, 2).toString());
                            d_cat.setText(modell.getValueAt(y, 3).toString());
                            d_qty.setText(modell.getValueAt(y, 4).toString());          
                            d_price.setText(modell.getValueAt(y, 5).toString());
                        }else{JOptionPane.showMessageDialog(null,"Error");}
                    }else{ JOptionPane.showMessageDialog(null,""+jTextField6.getText()+" not found on the Customer's List!");}
                }catch(SQLException e){ JOptionPane.showMessageDialog(null, e);}}
    }//GEN-LAST:event_tbl_dumpMouseClicked

    private void d_qty1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_d_qty1KeyReleased
        // TODO add your handling code here:
        if(d_qty1.getText().contains("qwertyuiopasdfghjklzxcvbnm!@#$%^&*()_+,./;[]`~<>?:{}")){
       
        JOptionPane.showMessageDialog(null,"Incorrect Integer Value!");
        d_price.setText("");
        }else{
            if (d_qty.getText().equals("")){
            }else{
                try{
                  pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_dump WHERE ProductID = '" + d_id.getText() + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                String qty = rs.getString("Quantity");
                if (d_qty1.getText().equals("")){
                d_qty.setText(qty);
                d_qty2.setText(qty);
                }else{
                int ab = Integer.parseInt(qty);
                int de= Integer.parseInt(d_qty1.getText());
                if (de>ab){
               
                JOptionPane.showMessageDialog(null, "The input Quantity value is beyond the Dump items quantity!");
                d_qty1.setText("");
                d_qty2.setText("");
                }else{
        int put_qty = Integer.parseInt(d_qty1.getText());
        //int dump_qty = Integer.parseInt(d_qty.getText());
        
        int right_qty = Integer.parseInt(d_qty2.getText());
        int b = Integer.parseInt(d_price.getText());
        
        double total_price = put_qty * b;
        int total_qty = right_qty - put_qty; 
        
        d_total.setText(String.valueOf(total_price));
        d_qty.setText(String.valueOf(total_qty));
        //d_qty2.setText(d_qty.getText());
            }}}
                }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
            }
        
        }
    }//GEN-LAST:event_d_qty1KeyReleased

    private void d_priceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_d_priceMouseClicked
        // TODO add your handling code here:
        if (acctype.getText().equals("Admin")){
                 if (d_id.getText().equals("")){
                 
                 }else{
//        int price = JOptionPane.YES_NO_OPTION;
//                String price1 = JOptionPane.showInputDialog(this,"Input Price",price);
//        if (price == JOptionPane.YES_OPTION){
        String price = JOptionPane.showInputDialog(this,"Input Price");
        if (price.equals("") || price.equals("0")){
        
        }else{
          JFrame frame = new JFrame();
            String[] options = new String[2];
            options[0] = new String("Update Price");
            options[1] = new String("Input Only");
            int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Do you want to update Price on this item?",""+d_name.getText()+"Item Price", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(p==0){
                    try {
                 pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_dump SET  ValuesSRP=? WHERE ProductID='" + d_id.getText() + "'");

                    pst.setString(1, price);
                    int update = pst.executeUpdate();
                    if (update != 0) {
                   
                    JOptionPane.showMessageDialog(null, ""+d_name.getText()+" Price Updated!");
                    refresh_dump_item();
                     d_price.setText(price);
                     if (d_qty1.getText().equals("")){
                     }else{
                     int a = Integer.parseInt(d_qty1.getText());
                     int b = Integer.parseInt(d_price.getText());
                     double c = a * b;
                     d_total.setText(String.valueOf(c));
                     }
                    }else{
                        
                        JOptionPane.showMessageDialog(null, "Error price update!");}
                     }catch(SQLException e){JOptionPane.showMessageDialog(null, e);}
            }else if(p==1){
                d_price.setText(price);
            frame.dispose();
            }else{
            
            }}
        //}else if (price == JOptionPane.NO_OPTION){}
      
           }
        }
    }//GEN-LAST:event_d_priceMouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
      dump_clear();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        if (jTextField6.getText().equals("")){
            panel1.setVisible(false);
                                //label
                                jLabel133.setVisible(true);
                                jLabel132.setVisible(true);
                                jLabel99.setVisible(true);
                                //textfield
                                d_id.setVisible(true);
                                d_name.setVisible(true);
                                d_des.setVisible(true);
        }else{
        try{
                           String sql = "SELECT Fullname,CustomerStatus FROM tbl_customer WHERE Fullname like ? and CommitmentStatus= '" +"Active" + "' ";
                                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                                pst.setString(1, "%" + jTextField6.getText() + "%"); 
                               //pst.setString(2, "%" + "Active" + "%");
                                rs = (ResultSet) pst.executeQuery();
                                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                                panel1.setVisible(true);
                                //label
                                jLabel133.setVisible(false);
                                jLabel132.setVisible(false);
                                jLabel99.setVisible(false);
                                //textfield
                                d_id.setVisible(false);
                                d_name.setVisible(false);
                                d_des.setVisible(false);
                                 } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }
        }
        
          if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        if(d_name.getText().equals("")){
            
           
     refresh_customer();
     pnl_cus1.setVisible(false);
     jLabel60.setVisible(true);
         jLabel59.setVisible(false);
         jLabel59.setText("");
         jLabel127.setVisible(true);
         jLabel105.setVisible(true);
    
         pnl_cus1.setVisible(false);
         jLabel60.setVisible(false);
         jLabel59.setVisible(false);
         jLabel127.setVisible(false);
         jLabel105.setVisible(false);
            
        }else{
        try{
                    pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_customer WHERE Fullname= '" + jTextField6.getText() + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        String stat = rs.getString("CustomerStatus");
                        pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_dump WHERE ProductID= '" + d_id.getText() + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        String val = rs.getString("ValuesSRP");
                        String org = rs.getString("OriginalPrice");
                         panel1.setVisible(false);
                               //label
                                jLabel133.setVisible(true);
                                jLabel132.setVisible(true);
                                jLabel99.setVisible(true);
                                //textfield
                                d_id.setVisible(true);
                                d_name.setVisible(true);
                                d_des.setVisible(true);
                        
                        if (stat.equals("Regular")){
                        d_price.setText(org);
                        //d_total.setText("0.00");
                         jLabel144.setForeground(Color.GREEN);
                         
                         if (d_qty1.getText().equals("")){
                         }else{
                             int put_qty = Integer.parseInt(d_qty1.getText());
                            int b = Integer.parseInt(d_price.getText());
                            double total_price = put_qty * b;
                            d_total.setText(String.valueOf(total_price));
                         }
                         
                        }else{
                        d_price.setText(val);
                        d_total.setText("0.00");
                         jLabel144.setForeground(Color.RED);
                         
                           if (d_qty1.getText().equals("")){
                         }else{
                             int put_qty = Integer.parseInt(d_qty1.getText());
                            int b = Integer.parseInt(d_price.getText());
                            double total_price = put_qty * b;
                            d_total.setText(String.valueOf(total_price));
                           }
                         
                        }
                    }
                    }else{
                    JOptionPane.showMessageDialog(null,""+jTextField6.getText()+" not found on the Customer's List!");
                    jLabel144.setForeground(Color.WHITE);
                    }
        }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
        }
          }
          
          try{
                    pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_customer WHERE Fullname= '" + jTextField6.getText() + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        
                         panel1.setVisible(false);
                               //label
                                jLabel133.setVisible(true);
                                jLabel132.setVisible(true);
                                jLabel99.setVisible(true);
                                //textfield
                                d_id.setVisible(true);
                                d_name.setVisible(true);
                                d_des.setVisible(true);
                        
                        String stat = rs.getString("CustomerStatus");
                        if (stat.equals("Regular")){
                            jLabel144.setForeground(Color.GREEN);
                        }else if (stat.equals("Walk In")){
                            jLabel144.setForeground(Color.RED);
                        }else{jLabel144.setForeground(Color.WHITE);}
                    }
        }catch(SQLException e){JOptionPane.showMessageDialog(null,e);} 
        
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
if (d_id.getText().equals("") && jTextField6.getText().equals("")){
}else{        
        try {
             pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_dump_sale WHERE ProductID= '" + d_id.getText() + "' and Customer='"+jTextField6.getText()+"' and Date='"+s_date.getText()+"'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        String qty = rs.getString("Quantity");
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_dump SET  Quantity=? WHERE ProductID='" + d_id.getText() + "'");

                    pst.setString(1, d_qty.getText());
                    int update = pst.executeUpdate();
                    if (update != 0) {
                    if(today_sold.isSelected()){
                                try{
                                    refresh_dump_item();
                                    int dump_qty = Integer.parseInt( d_qty1.getText());
                                    int sale_qty = Integer.parseInt(qty);
                                    int equal = dump_qty + sale_qty;

                                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_dump_sale SET  Quantity=? WHERE ProductID='" + d_id.getText() + "' and Customer='"+jTextField6.getText()+"'");
                                    pst.setInt(1, equal);
                                    int update1 = pst.executeUpdate();
                                    if (update1 != 0) {
                                        String sql = "SELECT * FROM tbl_dump_sale WHERE Date like ?  ";
                                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                                pst.setString(1, "%" + s_date.getText()+ "%");
                                rs = (ResultSet) pst.executeQuery();
                                tbl_dump_sale.setModel(DbUtils.resultSetToTableModel(rs));
                                     refresh_dump_item();
                                     dump_clear();
                                     dump_tprice.setText(Double.toString(total_price()));
                                     dump_tqty.setText(Integer.toString(total_qty()));
                                    }}catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
                                }else{
                                 refresh_dump_sale();
                                    refresh_dump_item();
                                    int dump_qty = Integer.parseInt( d_qty1.getText());
                                    int sale_qty = Integer.parseInt(qty);
                                    int equal = dump_qty + sale_qty;

                                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_dump_sale SET  Quantity=? WHERE ProductID='" + d_id.getText() + "' and Customer='"+jTextField6.getText()+"' and Date='"+s_date.getText()+"'");
                                    pst.setInt(1, equal);
                                    int update1 = pst.executeUpdate();
                                    if (update1 != 0) {
                                     refresh_dump_sale();
                                     refresh_dump_item();
                                     dump_clear();
                                     dump_tprice.setText(Double.toString(total_price()));
                                     dump_tqty.setText(Integer.toString(total_qty()));
                                    }}}
                    }else{
                     String sql1 = "INSERT INTO tbl_dump_sale VALUES (?,?,?,?,?,?,?,?,?,?)";

                            pst = (PreparedStatement) conn.prepareStatement(sql1);
                            pst.setString(1,jTextField6.getText());
                            pst.setString(2,d_id.getText());
                            pst.setString(3,d_name.getText());
                            pst.setString(4,d_des.getText());
                            pst.setString(5,d_cat.getText());
                            pst.setString(6,d_qty1.getText());
                            pst.setString(7,d_price.getText());
                            pst.setString(8,d_total.getText());
                            pst.setString(9,s_date.getText());
                            pst.setString(10,s_time.getText());
                           int add = pst.executeUpdate();
                            if(add!=0){
                                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_dump SET  Quantity=? WHERE ProductID='" + d_id.getText() + "'");

                    pst.setString(1, d_qty.getText());
                    int update = pst.executeUpdate();
                    if (update != 0) {
                                if(today_sold.isSelected()){
                                try{
                                String sql = "SELECT * FROM tbl_dump_sale WHERE Date like ?  ";
                                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                                pst.setString(1, "%" + s_date.getText()+ "%");
                                rs = (ResultSet) pst.executeQuery();
                                tbl_dump_sale.setModel(DbUtils.resultSetToTableModel(rs));
                                refresh_dump_item();
                                dump_clear();
                                dump_tprice.setText(Double.toString(total_price()));
                                dump_tqty.setText(Integer.toString(total_qty()));
                                }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
                                }else{
                                refresh_dump_sale();
                                refresh_dump_item();
                                dump_clear();
                                dump_tprice.setText(Double.toString(total_price()));
                                dump_tqty.setText(Integer.toString(total_qty()));
                                }
                    }
                            } else{
                                JOptionPane.showMessageDialog(null,"Error!");
                            }
                    }
        } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
}
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    int y = jTable1.getSelectedRow();
   TableModel modell = jTable1.getModel();
           String stat = modell.getValueAt(y, 1).toString();
        if (d_id.getText().equals("")){
               if (stat.equals("Regular")){
               jTextField6.setText(modell.getValueAt(y, 0).toString());
                jLabel144.setForeground(Color.GREEN);
                panel1.setVisible(false);
                   //label
                                jLabel133.setVisible(true);
                                jLabel132.setVisible(true);
                                jLabel99.setVisible(true);
                                //textfield
                                d_id.setVisible(true);
                                d_name.setVisible(true);
                                d_des.setVisible(true);
                }else{
               jTextField6.setText(modell.getValueAt(y, 0).toString());
                jLabel144.setForeground(Color.RED);
                panel1.setVisible(false);
                   //label
                                jLabel133.setVisible(true);
                                jLabel132.setVisible(true);
                                jLabel99.setVisible(true);
                                //textfield
                                d_id.setVisible(true);
                                d_name.setVisible(true);
                                d_des.setVisible(true);
                }
        }else{
               jTextField6.setText(modell.getValueAt(y, 0).toString());
               try{
                    pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_customer WHERE Fullname= '" + jTextField6.getText() + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        String stat1 = rs.getString("CustomerStatus");
                        pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_dump WHERE ProductID= '" + d_id.getText() + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        String val = rs.getString("ValuesSRP");
                        String org = rs.getString("OriginalPrice");
                         panel1.setVisible(false);
                               //label
                                jLabel133.setVisible(true);
                                jLabel132.setVisible(true);
                                jLabel99.setVisible(true);
                                //textfield
                                d_id.setVisible(true);
                                d_name.setVisible(true);
                                d_des.setVisible(true);
                        
                        if (stat1.equals("Regular")){
                        d_price.setText(org);
                        //d_total.setText("0.00");
                         jLabel144.setForeground(Color.GREEN);
                         
                         if (d_qty1.getText().equals("")){
                         }else{
                             int put_qty = Integer.parseInt(d_qty1.getText());
                            int b = Integer.parseInt(d_price.getText());
                            double total_price = put_qty * b;
                            d_total.setText(String.valueOf(total_price));
                         }
                         
                        }else{
                        d_price.setText(val);
                        d_total.setText("0.00");
                         jLabel144.setForeground(Color.RED);
                         
                           if (d_qty1.getText().equals("")){
                         }else{
                             int put_qty = Integer.parseInt(d_qty1.getText());
                            int b = Integer.parseInt(d_price.getText());
                            double total_price = put_qty * b;
                            d_total.setText(String.valueOf(total_price));
                           }
                         
                        }
                    }
                    }else{
                    JOptionPane.showMessageDialog(null,""+jTextField6.getText()+" not found on the Customer's List!");
                    jLabel144.setForeground(Color.WHITE);
                    }
        }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
               }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel131MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel131MouseClicked
        // TODO add your handling code here:
        if(today.getText().equals("  TODAY'S RETURNED")){
        try{
              pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_return_report");
                int del11 =  pst.executeUpdate();
                if(del11 >0){
                    }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
                if (today.isSelected() && jTextField2.getText().isEmpty()) {
                                try{      
                                  String sql1 = "INSERT INTO tbl_return_report Select * from tbl_return_history Where Date ='"+s_date.getText()+"' ";
                                pst = (PreparedStatement) conn.prepareStatement(sql1);
                                 int a = pst.executeUpdate();
                                  if (a!=0){
                                    try{ Connection connn = MysqlConnection.ConnectDB();
                                    JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\Item_return_report.jrxml");
                                   // String query = "SELECT * FROM tbl_items";
                                    JRDesignQuery jrquery = new JRDesignQuery();
                                    //jrquery.setText(query);
                                    JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                                    jasperDesign.setQuery(jrquery);
                                    HashMap para= new HashMap();
                                     para.put("Staffname", user.getText());
                                     para.put("Header", "DAILY CUSTOMER'S ITEM RETURN REPORT");
                                    JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
                                    JasperPrintManager.printReport(jp, true);
                                     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
                                  }
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null,e);
                                }
                    }else if (today.isSelected()==false && jTextField2.getText().isEmpty()) {
                     try{      
                                  String sql1 = "INSERT INTO tbl_return_report Select * from tbl_return_history";
                                pst = (PreparedStatement) conn.prepareStatement(sql1);
                                int a = pst.executeUpdate();
                                if (a!=0){
                                try{ Connection connn = MysqlConnection.ConnectDB();
                                    JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\Item_return_report.jrxml");
                                   // String query = "SELECT * FROM tbl_items";
                                    JRDesignQuery jrquery = new JRDesignQuery();
                                    //jrquery.setText(query);
                                    JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                                    jasperDesign.setQuery(jrquery);
                                    HashMap para= new HashMap();
                                     para.put("Staffname", user.getText());
                                     para.put("Header", "CUSTOMER'S ITEM RETURN REPORT");
                                    JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
                                    JasperPrintManager.printReport(jp, true);
                                     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
                                }
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null,e);
                                }
                    }else{
                    }
        }else {
            try{
              pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_customer_purchase_report");
              pst.executeUpdate();
                
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        if (today.isSelected() && jTextField2.getText().isEmpty()) {
                                try{      
                                  String sql1 = "INSERT INTO tbl_customer_purchase_report Select * from tbl_customer_purchase Where Date ='"+s_date.getText()+"' ";
                                pst = (PreparedStatement) conn.prepareStatement(sql1);
                                 int a = pst.executeUpdate();
                                  if (a!=0){
                                    try{ Connection connn = MysqlConnection.ConnectDB();
                                    JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\customer_purchase_report.jrxml");
                                   // String query = "SELECT * FROM tbl_items";
                                    JRDesignQuery jrquery = new JRDesignQuery();
                                    //jrquery.setText(query);
                                    JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                                    jasperDesign.setQuery(jrquery);
                                    HashMap para= new HashMap();
                                     //para.put("Staffname", user.getText());
                                     para.put("report", "Dialy Repot");
                                    JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
                                    JasperPrintManager.printReport(jp, true);
                                     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
                                  }
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null,e);
                                }
                    }else if (today.isSelected()==false && jTextField2.getText().isEmpty()) {
                     try{      
                                  String sql1 = "INSERT INTO tbl_customer_purchase_report Select * from tbl_customer_purchase";
                                pst = (PreparedStatement) conn.prepareStatement(sql1);
                                int a = pst.executeUpdate();
                                if (a!=0){
                                try{ Connection connn = MysqlConnection.ConnectDB();
                                    JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\customer_purchase_report.jrxml");
                                   // String query = "SELECT * FROM tbl_items";
                                    JRDesignQuery jrquery = new JRDesignQuery();
                                    //jrquery.setText(query);
                                    JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                                    jasperDesign.setQuery(jrquery);
                                    HashMap para= new HashMap();
                                     //para.put("Staffname", user.getText());
                                     para.put("report", "Purchase Report");
                                    JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
                                    JasperPrintManager.printReport(jp, true);
                                     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
                                }
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null,e);
                                }
                    }else{
                    }
        }
    }//GEN-LAST:event_jLabel131MouseClicked

    private void jLabel135MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel135MouseClicked
        // TODO add your handling code here:
        
        try{
              pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_item_sales_report");
                pst.executeUpdate();
               
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        if (daily.isSelected()){
                                try{      
                                  String sql1 = "INSERT INTO tbl_item_sales_report Select * from tbl_sales Where Date = '"+s_date.getText()+"'";
                                pst = (PreparedStatement) conn.prepareStatement(sql1);
                                 int a = pst.executeUpdate();
                                  if (a!=0){
                                    try{ Connection connn = MysqlConnection.ConnectDB();
                                    JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\item_sales_report.jrxml");
                                   // String query = "SELECT * FROM tbl_items";
                                    JRDesignQuery jrquery = new JRDesignQuery();
                                    //jrquery.setText(query);
                                    JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                                    jasperDesign.setQuery(jrquery);
                                    HashMap para= new HashMap();
//                                     para.put("Staffname", user.getText());
                                     para.put("Header", "DAILY PRODUCT SALES REPORT");
                                    JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
                                    JasperPrintManager.printReport(jp, true);
                                     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
                                  }else{
                                  JOptionPane.showMessageDialog(null,"error generating daily report ");
                                  }
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null,e);
                                }
        }else if(monthly.isSelected()){
        try{      
                                  String sql1 = "INSERT INTO tbl_item_sales_report Select * from tbl_sales Where Month = '"+s_month.getText()+"'";
                                pst = (PreparedStatement) conn.prepareStatement(sql1);
                                 int a = pst.executeUpdate();
                                  if (a!=0){
                                    try{ Connection connn = MysqlConnection.ConnectDB();
                                    JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\item_sales_report.jrxml");
                                   // String query = "SELECT * FROM tbl_items";
                                    JRDesignQuery jrquery = new JRDesignQuery();
                                    //jrquery.setText(query);
                                    JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                                    jasperDesign.setQuery(jrquery);
                                    HashMap para= new HashMap();
//                                     para.put("Staffname", user.getText());
                                     para.put("Header", "MONTHLY PRODUCT SALES REPORT");
                                    JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
                                    JasperPrintManager.printReport(jp, true);
                                     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
                                  }else{
                                   JOptionPane.showMessageDialog(null,"error generating montly report ");
                                  }
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null,e);
                                }
        
        }else if(yearly.isSelected()){
        try{      
                                  String sql1 = "INSERT INTO tbl_item_sales_report Select * from tbl_sales Where Year = '"+s_year.getText()+"' ";
                                pst = (PreparedStatement) conn.prepareStatement(sql1);
                                 int a = pst.executeUpdate();
                                  if (a!=0){
                                    try{ Connection connn = MysqlConnection.ConnectDB();
                                    JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\item_sales_report.jrxml");
                                   // String query = "SELECT * FROM tbl_items";
                                    JRDesignQuery jrquery = new JRDesignQuery();
                                    //jrquery.setText(query);
                                    JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                                    jasperDesign.setQuery(jrquery);
                                    HashMap para= new HashMap();
//                                     para.put("Staffname", user.getText());
                                     para.put("Header", "YEARLY PRODUCT SALES REPORT");
                                    JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
                                    JasperPrintManager.printReport(jp, true);
                                     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
                                  }else{
                                   JOptionPane.showMessageDialog(null,"error generating yearly report ");
                                  }
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null,e);
                                }
        
        }else{
        try{      
                                  String sql1 = "INSERT INTO tbl_item_sales_report Select * from tbl_sales";
                                pst = (PreparedStatement) conn.prepareStatement(sql1);
                                 int a = pst.executeUpdate();
                                  if (a!=0){
                                    try{ Connection connn = MysqlConnection.ConnectDB();
                                    JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\item_sales_report.jrxml");
                                   // String query = "SELECT * FROM tbl_items";
                                    JRDesignQuery jrquery = new JRDesignQuery();
                                    //jrquery.setText(query);
                                    JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                                    jasperDesign.setQuery(jrquery);
                                    HashMap para= new HashMap();
//                                     para.put("Staffname", user.getText());
                                     para.put("Header","PRODUCT SALES REPORT");
                                    JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
                                    JasperPrintManager.printReport(jp, true);
                                     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
                                  }else{
                                   JOptionPane.showMessageDialog(null,"error generating all product report ");
                                  }
                                }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null,e);
                                }            
        }
        
//        }else{
//        try{      
//                                  String sql1 = "INSERT INTO tbl_item_sales_report Select * from tbl_sales Where Date = '"+dff.format(r_date.getDate())+"'";
//                                pst = (PreparedStatement) conn.prepareStatement(sql1);
//                                 int a = pst.executeUpdate();
//                                  if (a!=0){
//                                    try{ Connection connn = MysqlConnection.ConnectDB();
//                                    JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\item_sales_report.jrxml");
//                                   // String query = "SELECT * FROM tbl_items";
//                                    JRDesignQuery jrquery = new JRDesignQuery();
//                                    //jrquery.setText(query);
//                                    JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
//                                    jasperDesign.setQuery(jrquery);
//                                    HashMap para= new HashMap();
////                                     para.put("Staffname", user.getText());
//                                     para.put("Header",dff.format(r_date.getDate())+" PRODUCT SALES REPORT");
//                                    JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
//                                    JasperPrintManager.printReport(jp, true);
//                                     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
//                                  }else{
//                                   JOptionPane.showMessageDialog(null,"error generating specifict date report ");
//                                  }
//                                }catch(SQLException e){
//                                        JOptionPane.showMessageDialog(null,e);
//                                }
//        
//        
//        }
                    
    }//GEN-LAST:event_jLabel135MouseClicked

    private void today_soldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_today_soldActionPerformed
        // TODO add your handling code here:
       
        if(today_sold.isSelected()){
            try{
            String sql = "SELECT * FROM tbl_dump_sale WHERE Date like ?  ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_dump_sale.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(SQLException e){
                                        JOptionPane.showMessageDialog(null,e);
                                }
            }else{
             refresh_dump_sale();
            }
        
    }//GEN-LAST:event_today_soldActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
              
        if (jButton13.getText().equals("Dump Report")){
         
        try{
            Connection connn = MysqlConnection.ConnectDB();
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\dump_report.jrxml");
           // String query = "SELECT * FROM tbl_items";
            JRDesignQuery jrquery = new JRDesignQuery();
            //jrquery.setText(query);
            JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
            jasperDesign.setQuery(jrquery);
            JasperPrint jp = JasperFillManager.fillReport(jr,null,connn);
            JasperPrintManager.printReport(jp, true);
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        }else{
            try{
              pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_dump_sale_report");
                int del11 =  pst.executeUpdate();
                if(del11 >0){
                    }
        }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);}
            
            if(today_sold.isSelected()) {
            try{
                String sql1 = "INSERT INTO tbl_dump_sale_report Select * from tbl_dump report Where Date ='"+s_date.getText()+"' ";
                pst = (PreparedStatement) conn.prepareStatement(sql1);
                 int a = pst.executeUpdate();
                  if (a!=0){
                        try{Connection connn = MysqlConnection.ConnectDB();
                        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\dump_sale_report.jrxml");
                        JRDesignQuery jrquery = new JRDesignQuery();
                        JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                        jasperDesign.setQuery(jrquery);
                        HashMap para= new HashMap();
                        para.put("Header","Daily Purchase Report");
                        para.put("Staffname",user.getText());
                        JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
                        JasperPrintManager.printReport(jp, true);
                        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
              }}catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);}
            }else{
                 try{
                String sql1 = "INSERT INTO tbl_dump_sale_report Select * from tbl_dump report";
                pst = (PreparedStatement) conn.prepareStatement(sql1);
                 int a = pst.executeUpdate();
                  if (a!=0){
                        try{Connection connn = MysqlConnection.ConnectDB();
                        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\dump_sale_report.jrxml");
                        JRDesignQuery jrquery = new JRDesignQuery();
                        JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                        jasperDesign.setQuery(jrquery);
                        HashMap para= new HashMap();
                        para.put("Header","Dump Purchase Report");
                        para.put("Staffname",user.getText());
                        JasperPrint jp = JasperFillManager.fillReport(jr,para,connn);
                        JasperPrintManager.printReport(jp, true);
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
              }}catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);}
              }}
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void pnl_dashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_dashboardMousePressed
        // TODO add your handling code here:
        setColor_manage(pnl_dashboard);
        resetColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
    }//GEN-LAST:event_pnl_dashboardMousePressed

    private void pnl_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_dashboardMouseClicked
        // TODO add your handling code here:
               
        login.setVisible(false);
        Sales.setVisible(false);
        Stocks.setVisible(false);
        ItemList.setVisible(false);
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
        addaccount.setVisible(false);
        accountlist.setVisible(false);
        Supplier.setVisible(false);
        customer.setVisible(false);
        dashboard.setVisible(true);
        dashboard();
             
    }//GEN-LAST:event_pnl_dashboardMouseClicked

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
       if (jButton6.getText().equals("Switch Sell")){
        try {
                String sql = "SELECT * FROM tbl_dump WHERE "
                + "ProductID like ? or ProductName like ? or ProductDescription like ? or ProductCategory like ? or Quantity like ? "
                + "or OriginalPrice like ? or ValuesSRP like ? or Supplier like ? or Date like ? or Time like ? ";

                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + jTextField3.getText() + "%"); 
                pst.setString(2, "%" + jTextField3.getText() + "%");
                pst.setString(3, "%" + jTextField3.getText() + "%");
                pst.setString(3, "%" + jTextField3.getText() + "%");
                pst.setString(4, "%" + jTextField3.getText() + "%");
                pst.setString(5, "%" + jTextField3.getText() + "%");
                pst.setString(6, "%" + jTextField3.getText() + "%");
                pst.setString(7, "%" + jTextField3.getText() + "%");
                pst.setString(8, "%" + jTextField3.getText() + "%");
                pst.setString(9, "%" + jTextField3.getText() + "%");
                pst.setString(10, "%" + jTextField3.getText() + "%");
                rs = (ResultSet) pst.executeQuery();
                tbl_dump.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }
       }else{
               if(jTextField3.getText().equals("")){
                     refresh_dump_sale();
            dump_tprice.setText(Double.toString(total_dump_price()));
            dump_tqty.setText(Integer.toString(total_dump_qty()));
               }else{
                today_sold.setSelected(false);
                 try {
                String sql = "SELECT * FROM tbl_dump_sale WHERE "
                + "Customer like ? or ProductID like ? or ProductName like ? or ProductDescription like ? or ProductCategory like ? or Quantity like ? "
                + "or Price like ? or TotalPrice like ? or Date like ? or Time like ? ";

                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + jTextField3.getText() + "%"); 
                pst.setString(2, "%" + jTextField3.getText() + "%");
                pst.setString(3, "%" + jTextField3.getText() + "%");
                pst.setString(3, "%" + jTextField3.getText() + "%");
                pst.setString(4, "%" + jTextField3.getText() + "%");
                pst.setString(5, "%" + jTextField3.getText() + "%");
                pst.setString(6, "%" + jTextField3.getText() + "%");
                pst.setString(7, "%" + jTextField3.getText() + "%");
                pst.setString(8, "%" + jTextField3.getText() + "%");
                pst.setString(9, "%" + jTextField3.getText() + "%");
                pst.setString(10, "%" + jTextField3.getText() + "%");
                rs = (ResultSet) pst.executeQuery();
                tbl_dump_sale.setModel(DbUtils.resultSetToTableModel(rs));
            dump_tprice.setText(Double.toString(total_dump_price()));
            dump_tqty.setText(Integer.toString(total_dump_qty()));
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }
               }
       }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jPanel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseClicked
         
    }//GEN-LAST:event_jPanel23MouseClicked

    private void jPanel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel29MouseClicked
     
    }//GEN-LAST:event_jPanel29MouseClicked

    private void jPanel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel35MouseClicked
       
    }//GEN-LAST:event_jPanel35MouseClicked

    private void jPanel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel31MouseClicked
      
    }//GEN-LAST:event_jPanel31MouseClicked

    private void jPanel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel25MouseClicked
      
    }//GEN-LAST:event_jPanel25MouseClicked

    private void jPanel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel32MouseClicked
        
    }//GEN-LAST:event_jPanel32MouseClicked

    private void jPanel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel33MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jPanel33MouseClicked

    private void tbl_itemcategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_itemcategoryMouseClicked
        // TODO add your handling code here:
       refresh_cat();
    }//GEN-LAST:event_tbl_itemcategoryMouseClicked

    private void tbl_itemcategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_itemcategoryKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_KP_DOWN){
        refresh_cat();
        }
    }//GEN-LAST:event_tbl_itemcategoryKeyPressed

    private void p_catMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_catMouseClicked
      
        pnl_cat_sel.setVisible(true);
        jComboBox1.removeAllItems();
        pnl_cat_search.setVisible(false);
        jCheckBox2.setSelected(true);
        jTextField4.setEditable(false);
        jTextField4.setText("");
        p_mes_header.setText("Product Category");
         try{
        String sql="SELECT ProductCategory FROM tbl_category  ";
        pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
        String name =rs.getString("ProductCategory");
        jComboBox1.addItem(name);
        }
        }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
        
    }//GEN-LAST:event_p_catMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
           if (acctype.getText().equals("User")){
       
        JOptionPane.showMessageDialog(null, "Invalid User Access!!");
        }else{
        refresh_suppliers();
        login.setVisible(false);
        Sales.setVisible(false);
        Stocks.setVisible(false);
        ItemList.setVisible(false);
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
        addaccount.setVisible(false);
        accountlist.setVisible(false);
        Supplier.setVisible(true);
        supplierCount();
        
         pnl_supp_update.setVisible(false);
        }
        
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jButton23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseClicked
        // TODO add your handling code here:
        if(jCheckBox1.isSelected())  {
        pnl_cat_sel.setVisible(false);
        p_cat.setText(jTextField4.getText());
        }else if(jCheckBox2.isSelected()){     
        pnl_cat_sel.setVisible(false);
        p_cat.setText((String) jComboBox1.getSelectedItem());
        }
    }//GEN-LAST:event_jButton23MouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        pnl_cat_sel.setVisible(false);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
       if(p_mes_header.getText().equals("Product Name")){
       
            pnl_cat_search.setVisible(false);
        String cat = JOptionPane.showInputDialog(this,"Input new product name");
        if (cat.equals("")){
        }else{
        try{
        String sql1 = "INSERT INTO tbl_category(ProductName) VALUES (?)";

            pst = (PreparedStatement) conn.prepareStatement(sql1);
            pst.setString(1,cat);
            int add = pst.executeUpdate();
            if(add!=0){
                jComboBox1.removeAllItems();
                String sql="SELECT ProductName FROM tbl_category  ";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                String name =rs.getString("ProductName");
                jComboBox1.addItem(name);
                }
            } else{}
        
        }catch(SQLException e){}
        }           
       
       }else if(p_mes_header.getText().equals("Product Description")){
       
            pnl_cat_search.setVisible(false);
        String cat = JOptionPane.showInputDialog(this,"Input new description");
        if (cat.equals("")){
        }else{
        try{
        String sql1 = "INSERT INTO tbl_category(ProductDescription) VALUES (?)";

            pst = (PreparedStatement) conn.prepareStatement(sql1);
            pst.setString(1,cat);
            int add = pst.executeUpdate();
            if(add!=0){
                jComboBox1.removeAllItems();
                String sql="SELECT ProductDescription FROM tbl_category  ";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                String name =rs.getString("ProductDescription");
                jComboBox1.addItem(name);
                }
            } else{}
        
        }catch(SQLException e){}
        }          
           
       
       }else if(p_mes_header.getText().equals("Product Category")){
       
           
           pnl_cat_search.setVisible(false);
        String cat = JOptionPane.showInputDialog(this,"Input new category");
        if (cat.equals("")){
        }else{
        try{
        String sql1 = "INSERT INTO tbl_category(ProductCategory) VALUES (?)";

            pst = (PreparedStatement) conn.prepareStatement(sql1);
            pst.setString(1,cat);
            int add = pst.executeUpdate();
            if(add!=0){
                jComboBox1.removeAllItems();
                String sql="SELECT ProductCategory FROM tbl_category  ";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                String name =rs.getString("ProductCategory");
                jComboBox1.addItem(name);
                }
            } else{}
        
        }catch(SQLException e){}
        }
       
       }else{}
       
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        if(p_mes_header.getText().equals("Product Name")){//select by description
        
            if (jTextField4.getText().equals("")){
        pnl_cat_search.setVisible(false);
        } else{   
            pnl_cat_search.setVisible(true);
        try {String sql = "Select DISTINCT ProductName from tbl_category where ProductName like ?";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" +jTextField4.getText()+ "%"); 
                rs = pst.executeQuery();
                jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {JOptionPane.showConfirmDialog(null, ex);}}
            
        }else if (p_mes_header.getText().equals("Product Description")){//select by description
         
            if (jTextField4.getText().equals("")){
        pnl_cat_search.setVisible(false);
        } else{   
            pnl_cat_search.setVisible(true);
        try {String sql = "Select DISTINCT ProductDescription from tbl_category where ProductDescription like ?";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" +jTextField4.getText()+ "%"); 
                rs = pst.executeQuery();
                jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {JOptionPane.showConfirmDialog(null, ex);}}
            
            
        }else if (p_mes_header.getText().equals("Product Category")){//select by category
        
        if (jTextField4.getText().equals("")){
        pnl_cat_search.setVisible(false);
        } else{   
            pnl_cat_search.setVisible(true);
        try {String sql = "Select DISTINCT ProductCategory from tbl_category where ProductCategory like ?";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" +jTextField4.getText()+ "%"); 
                rs = pst.executeQuery();
                jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {JOptionPane.showConfirmDialog(null, ex);}}
        }else{}
        
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()){
        jComboBox1.setEnabled(false);
       jTextField4.setEditable(true);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox2.isSelected()){
        jComboBox1.setEnabled(true);
       jTextField4.setEditable(false);
       pnl_cat_search.setVisible(false);
       jTextField4.setText("");
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jLabel141MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel141MouseClicked
        // TODO add your handling code here:
        try{
        if (jCheckBox1.isSelected()){
         pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_category  WHERE ProducCategory = '" + jTextField4.getText() + "'");
          pst.executeUpdate();
          jTextField4.setText("");
          jComboBox1.removeAllItems();
                String sql="SELECT ProductCategory FROM tbl_category  ";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                String name =rs.getString("ProductCategory");
                jComboBox1.addItem(name);
                }
    }else if (jCheckBox2.isSelected()){
        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_category  WHERE ProducCategory = '" + jComboBox1.getSelectedItem() + "'");
          pst.executeUpdate();
          jComboBox1.removeAllItems();
                String sql="SELECT ProductCategory FROM tbl_category  ";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                String name =rs.getString("ProductCategory");
                jComboBox1.addItem(name);
                }
        }
        }catch(SQLException e){}
    }//GEN-LAST:event_jLabel141MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int z = jTable2.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                jTextField4.setText(model.getValueAt(z, 0).toString());
                  pnl_cat_search.setVisible(false);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
       
     //jComboBox2.removeAllItems();
      category_drop();
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
//       if (jComboBox2.equals("")){
//       jComboBox2.removeAllItems();
//       }else{
//        try{
//        String sql="SELECT DISTINCT ProductCategory FROM tbl_category";
//        pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
//        rs = pst.executeQuery();
//        while(rs.next()){
//        
//        String name =rs.getString("ProductCategory");
//        jComboBox2.addItem(name);
//         AutoCompleteDecorator.decorate(jComboBox2);
//        }
//        }catch(Exception e){
//    JOptionPane.showMessageDialog(null, e);
//    } }
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void p_desMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_desMouseClicked
        // TODO add your handling code here:
        pnl_cat_sel.setVisible(true);
        jComboBox1.removeAllItems();
        pnl_cat_search.setVisible(false);
        jCheckBox2.setSelected(true);
        jTextField4.setEditable(false);
        jTextField4.setText("");
        p_mes_header.setText("Product Description");
         try{
        String sql="SELECT ProductDescription FROM tbl_category  ";
        pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
        String name =rs.getString("ProductDescription");
        jComboBox1.addItem(name);
        }
        }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
    }//GEN-LAST:event_p_desMouseClicked

    private void p_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_nameMouseClicked
        // TODO add your handling code here:
        pnl_cat_sel.setVisible(true);
        jComboBox1.removeAllItems();
        pnl_cat_search.setVisible(false);
        jCheckBox2.setSelected(true);
        jTextField4.setEditable(false);
        jTextField4.setText("");
        p_mes_header.setText("Product Name");
         try{
        String sql="SELECT ProductName FROM tbl_category  ";
        pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
        String name =rs.getString("ProductName");
        jComboBox1.addItem(name);
        }
        }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
    }//GEN-LAST:event_p_nameMouseClicked

    public void category_drop(){
        try{
            if (jLabel94.getText().equals("")){
            String sql="SELECT DISTINCT ProductName FROM tbl_items";
        pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
        String name =rs.getString("ProductName");
        jComboBox2.addItem(name);
        }
        
        String sqll = "SELECT DISTINCT ProductCategory FROM tbl_items where ProductName='"+jComboBox2.getSelectedItem()+"'";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sqll);
            rs = pst.executeQuery();
            tbl_itemcategory.setModel(DbUtils.resultSetToTableModel(rs));
            
            //for pending request view
            }else{
            
                String sql="SELECT DISTINCT ProductName FROM tbl_stock_request";
        pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
        String name =rs.getString("ProductName");
        jComboBox2.addItem(name);
        }
        
        String sqll = "SELECT DISTINCT ProductCategory FROM tbl_stock_request where ProductName='"+jComboBox2.getSelectedItem()+"'";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sqll);
            rs = pst.executeQuery();
            tbl_itemcategory.setModel(DbUtils.resultSetToTableModel(rs));
                
            
            }
        
        
        }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
    }
    public void refresh_cat(){
    if (jLabel94.getText().equals("Pending request shown Below")){
      
        int z = tbl_itemcategory.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) tbl_itemcategory.getModel();
                String cat = model.getValueAt(z, 0).toString();
        try {
            String sql = "SELECT ProductID,ProductDescription,Quantity,OriginalPrice,ValuesSRP,Sale,Supplier FROM tbl_stock_request where ProductCategory ='"+cat+"' ORDER BY ProductID DESC";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tbl_item.setModel(DbUtils.resultSetToTableModel(rs));
            empCount();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }else{
     int z = tbl_itemcategory.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) tbl_itemcategory.getModel();
                String cat = model.getValueAt(z, 0).toString();
        try {
            String sql = "SELECT ProductID,ProductDescription,Quantity,OriginalPrice,ValuesSRP,Sale,Supplier FROM tbl_items where ProductCategory ='"+cat+"' ORDER BY ProductID DESC";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tbl_item.setModel(DbUtils.resultSetToTableModel(rs));
            empCount();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
    
    public void setColor(JPanel panel)
{
panel.setBackground(new java.awt.Color(227,235,239));
}
public void resetColor(JPanel panel)
{
panel.setBackground(new java.awt.Color(255,255,255));
}
//set Permanent
public void setColor_manage(JPanel panel)
{
panel.setBackground(new java.awt.Color(185,185,185));
}
public void resetColor_manage(JPanel panel)
{
panel.setBackground(new java.awt.Color(255,255,255));
}
    
    
    
    
    public double total_report_Sales() {
        double total=0;
                for (int e=0; e<tbl_sales.getRowCount();e++)
                {
                    double amount= Double.parseDouble((String) tbl_sales.getValueAt( e, 5).toString());
                    total+=amount;
                }
                report_sales.setText(String.valueOf(total));
                d_sale_price.setText(String.valueOf(total));
        return total;
     }

 public int total_sales_summary() {
        int rowscount = tbl_sales.getRowCount();
        int sum = 0;
        for (int i = 0; i < rowscount; i++) {
            sum = sum + Integer.parseInt(tbl_sales.getValueAt(i, 4).toString());
        }
        return sum;
    }
 
 public int dashboard_return_qty() {
        int rowscount = tbl_purchase_list.getRowCount();
        int sum = 0;
        for (int i = 0; i < rowscount; i++) {
            sum = sum + Integer.parseInt(tbl_purchase_list.getValueAt(i, 6).toString());
        }
        return sum;
    }
 
 public int dashboard_dump_qty() {
        int rowscount = tbl_dump.getRowCount();
        int sum = 0;
        for (int i = 0; i < rowscount; i++) {
            sum = sum + Integer.parseInt(tbl_dump.getValueAt(i, 4).toString());
        }
        return sum;
    }
    public void Daily_transation(){
     try {
            String sql = "SELECT ProductID,ProductName,ProductDescription,Price,Quantity,TotalPrice,Date,Time,StaffName  FROM tbl_sales WHERE "
                    + "ProductID like ? or ProductName like ? or ProductDescription like ? or Price like ? or "
                    + "Quantity like ? or TotalPrice like ? or Date like ? or Time like ? or StaffName like ?  ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            pst.setString(2, "%" + s_date.getText()+ "%");
            pst.setString(3, "%" + s_date.getText()+ "%");
            pst.setString(4, "%" + s_date.getText()+ "%");
            pst.setString(5, "%" + s_date.getText()+ "%");
            pst.setString(6, "%" + s_date.getText()+ "%");
            pst.setString(7, "%" + s_date.getText()+ "%");
            pst.setString(8, "%" + s_date.getText()+ "%");
            pst.setString(9, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_sales.setModel(DbUtils.resultSetToTableModel(rs));
            empCount();
            
            d_sale_price.setText(Double.toString(total_report_Sales()));
            d_sales.setText(Integer.toString(total_sales_summary()));
             report_sales.setText(Double.toString(total_report_Sales()));
             report_item.setText(Integer.toString(total_sales_summary()));
            //total_report_Sales();
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    
    
    }
    public void Monthly_transation(){
     try {
            String sql = "SELECT ProductID,ProductName,ProductDescription,Price,Quantity,TotalPrice,Date,Time,StaffName FROM tbl_sales WHERE "
                   // + "ProductID like ? or ProductName like ? or "
                    //+ "Stocks like ? or price like ? or Description like ? or Date like ? or Time like ? or Month like ? or Year like ? or StaffName like ? ";
                    +"Month like ?";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_month.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_sales.setModel(DbUtils.resultSetToTableModel(rs));
            empCount();
             report_sales.setText(Double.toString(total_report_Sales()));
             report_item.setText(Integer.toString(total_sales_summary()));
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }
    public void Yearly_transation(){
     try {
            String sql = "SELECT ProductID,ProductName,ProductDescription,Price,Quantity,TotalPrice,Date,Time,StaffName FROM tbl_sales WHERE "
                  //  + "ProductID like ? or ProductName like ? or "
                   // + "Stocks like ? or price like ? or Description like ? or Date like ? or Time like ? or Month like ? or Year like ? or StaffName like ? ";
            +"Year like ?";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_year.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_sales.setModel(DbUtils.resultSetToTableModel(rs));
            empCount();
             report_sales.setText(Double.toString(total_report_Sales()));
             report_item.setText(Integer.toString(total_sales_summary()));
            //total_report_Sales();
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HOME4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HOME4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HOME4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HOME4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HOME4().setVisible(true);
          
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AID;
    private javax.swing.JPanel Accounts;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel Dump_Sale;
    private javax.swing.JPanel Dump_list;
    private javax.swing.JPanel HomeMenu;
    private javax.swing.JPanel ItemList;
    private javax.swing.JPanel Logs;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Report;
    private javax.swing.JPanel Sales;
    private javax.swing.JPanel Stocks;
    private javax.swing.JPanel Supplier;
    private javax.swing.JPanel Transactions;
    private javax.swing.JTextField Uname;
    private javax.swing.JTextField a1;
    private javax.swing.JTextField a2;
    private javax.swing.JTextField a3;
    private javax.swing.JButton aact;
    private javax.swing.JLabel accid;
    private javax.swing.JPanel accountlist;
    private javax.swing.JLabel acctype;
    private javax.swing.JButton aclear;
    private javax.swing.JTextField add;
    private javax.swing.JPanel addaccount;
    private javax.swing.JLabel address;
    private javax.swing.JLabel adminid;
    private javax.swing.JComboBox atype;
    private javax.swing.JButton auclear;
    private javax.swing.JButton aupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPasswordField ccpass;
    private javax.swing.JPasswordField cnpass;
    private javax.swing.JTextField con;
    private javax.swing.JLabel contact;
    private javax.swing.JLabel contact10;
    private javax.swing.JLabel contact11;
    private javax.swing.JLabel contact12;
    private javax.swing.JLabel contact13;
    private javax.swing.JLabel contact14;
    private javax.swing.JLabel contact16;
    private javax.swing.JLabel contact17;
    private javax.swing.JLabel contact19;
    private javax.swing.JLabel contact2;
    private javax.swing.JLabel contact20;
    private javax.swing.JLabel contact21;
    private javax.swing.JLabel contact23;
    private javax.swing.JLabel contact24;
    private javax.swing.JLabel contact3;
    private javax.swing.JLabel contact5;
    private javax.swing.JLabel contact6;
    private javax.swing.JLabel contact7;
    private javax.swing.JLabel contact8;
    private javax.swing.JLabel contact9;
    private javax.swing.JPasswordField crpass;
    private javax.swing.JTextField cuname;
    private javax.swing.JTextField cus_age;
    private javax.swing.JTextField cus_city;
    private javax.swing.JTextField cus_contact;
    private javax.swing.JLabel cus_count;
    private javax.swing.JTextField cus_email;
    private javax.swing.JComboBox<String> cus_gender;
    private javax.swing.JTextField cus_id;
    private javax.swing.JTextField cus_name;
    private javax.swing.JCheckBox cus_regular;
    private javax.swing.JTextField cus_street;
    private javax.swing.JCheckBox cus_walkin;
    private javax.swing.JPanel customer;
    private javax.swing.JLabel d_cat;
    private javax.swing.JLabel d_des;
    private javax.swing.JLabel d_dump;
    private javax.swing.JLabel d_dump_price;
    private javax.swing.JLabel d_dump_pur;
    private javax.swing.JLabel d_dump_qty;
    private javax.swing.JLabel d_id;
    private javax.swing.JLabel d_name;
    private javax.swing.JLabel d_prd;
    private javax.swing.JTextField d_price;
    private javax.swing.JLabel d_qty;
    private javax.swing.JTextField d_qty1;
    private javax.swing.JLabel d_qty2;
    private javax.swing.JLabel d_return;
    private javax.swing.JLabel d_sale_price;
    private javax.swing.JLabel d_sales;
    private javax.swing.JLabel d_supplier;
    private javax.swing.JLabel d_total;
    private javax.swing.JCheckBox daily;
    private javax.swing.JPanel dashboard;
    private javax.swing.JLabel dump_tprice;
    private javax.swing.JLabel dump_tqty;
    private javax.swing.JLabel hdgfd;
    private javax.swing.JTextField i_search1;
    private javax.swing.JLabel id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    public final javax.swing.JComboBox jComboBox1 = new javax.swing.JComboBox();
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
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
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
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
    private javax.swing.JPanel jPanel22;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JPanel login;
    private javax.swing.JPanel manageacc;
    private javax.swing.JCheckBox monthly;
    private javax.swing.JTextField na1;
    private javax.swing.JTextField na2;
    private javax.swing.JTextField na3;
    private javax.swing.JTextField name;
    private javax.swing.JLabel nq1;
    private javax.swing.JLabel nq2;
    private javax.swing.JLabel nq3;
    private javax.swing.JButton p_add;
    public static javax.swing.JTextField p_cat;
    private javax.swing.JCheckBox p_cb;
    private javax.swing.JButton p_clear;
    private javax.swing.JTextField p_des;
    private javax.swing.JTextField p_id;
    private javax.swing.JLabel p_image;
    private javax.swing.JLabel p_mes_header;
    private javax.swing.JTextField p_name;
    private javax.swing.JTextField p_org;
    private javax.swing.JTextField p_quan;
    private javax.swing.JTextField p_sale;
    private javax.swing.JComboBox<String> p_sup;
    private javax.swing.JTextField p_val;
    private java.awt.Panel panel1;
    private javax.swing.JLabel password;
    private javax.swing.JLabel password1;
    private javax.swing.JPanel pnl_accounts;
    private java.awt.Panel pnl_cat_search;
    private java.awt.Panel pnl_cat_sel;
    private java.awt.Panel pnl_cus;
    private java.awt.Panel pnl_cus1;
    private java.awt.Panel pnl_cus_update;
    private javax.swing.JPanel pnl_dashboard;
    private javax.swing.JPanel pnl_logout;
    private javax.swing.JPanel pnl_logs;
    private javax.swing.JPanel pnl_report;
    private javax.swing.JPanel pnl_sales;
    private javax.swing.JPanel pnl_stock;
    private java.awt.Panel pnl_supp_update;
    private javax.swing.JPanel pnl_transactions;
    private javax.swing.JLabel prd_count;
    private javax.swing.JPasswordField pword;
    private javax.swing.JComboBox q1;
    private javax.swing.JComboBox q2;
    private javax.swing.JComboBox q3;
    private com.toedter.calendar.JDateChooser r_date;
    private javax.swing.JLabel rdel;
    private javax.swing.JLabel report_item;
    private javax.swing.JLabel report_sales;
    private javax.swing.JPanel request;
    private javax.swing.JPasswordField rpword;
    private javax.swing.JButton s_add;
    private javax.swing.JTextField s_barcode;
    private javax.swing.JTextField s_cash;
    private javax.swing.JTextField s_change;
    private javax.swing.JLabel s_date;
    private javax.swing.JTextField s_des;
    private javax.swing.JLabel s_eq;
    private javax.swing.JLabel s_month;
    private javax.swing.JPanel s_pnlprd;
    private javax.swing.JTextField s_prd;
    private javax.swing.JTextField s_price;
    private javax.swing.JTextField s_qty;
    private javax.swing.JTextField s_search;
    private javax.swing.JTextField s_stck;
    private javax.swing.JLabel s_time;
    private javax.swing.JTextField s_total;
    private javax.swing.JLabel s_year;
    private javax.swing.JButton st_prdnew;
    private javax.swing.JLabel sup_count;
    private javax.swing.JTextField sup_email;
    private javax.swing.JTextField sup_id;
    private javax.swing.JTextArea sup_remarks;
    private javax.swing.JTextField supp_address;
    private javax.swing.JTextField supp_contact;
    private javax.swing.JTextField supp_name;
    private javax.swing.JTextField supp_search;
    private javax.swing.JTable tbl_admin;
    private javax.swing.JTable tbl_cashier;
    private javax.swing.JTable tbl_cus;
    private javax.swing.JTable tbl_customer;
    private javax.swing.JTable tbl_dump;
    private javax.swing.JTable tbl_dump_sale;
    private javax.swing.JTable tbl_item;
    private javax.swing.JTable tbl_itemcategory;
    private javax.swing.JTable tbl_loginhis;
    private javax.swing.JTable tbl_purchase_list;
    private javax.swing.JTable tbl_sales;
    private javax.swing.JTable tbl_supplier;
    private javax.swing.JTable tbl_user;
    private javax.swing.JCheckBox today;
    private javax.swing.JCheckBox today_sold;
    private javax.swing.JTextField totprice;
    private javax.swing.JTextField totqty;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    private javax.swing.JLabel type;
    private javax.swing.JComboBox type1;
    private javax.swing.JButton uact;
    private javax.swing.JLabel user;
    private javax.swing.JButton uuclear;
    private javax.swing.JLabel v_date;
    private javax.swing.JLabel v_id;
    private javax.swing.JCheckBox yearly;
    // End of variables declaration//GEN-END:variables
private void seticon() {
      setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Untitled-3.png")));
    }
}
