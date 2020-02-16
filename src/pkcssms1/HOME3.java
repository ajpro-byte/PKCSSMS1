/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkcssms1;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.proteanit.sql.DbUtils;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.design.JRDesignQuery;


/**
 *
 * @author User
 */
public class HOME3 extends javax.swing.JFrame {
Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Creates new form HOME3
     */
    public HOME3() {
        initComponents();
            conn = MysqlConnection.ConnectDB();
        pnl_hide();
        seticon();
        refresh_item();
        CurrentDate();
        empCount();
    }
public void setColor_req(JPanel panel)
{
panel.setBackground(new java.awt.Color(0,204,102));
}
public void resetColor_req(JPanel panel)
{
panel.setBackground(new java.awt.Color(255,255,255));
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
                SimpleDateFormat s = new SimpleDateFormat("h:mm:ss");
                SimpleDateFormat sid = new SimpleDateFormat("hmmss");
                s_time.setText(s.format(d));
                
                SimpleDateFormat st = new SimpleDateFormat("MM/dd/yyyy");
                SimpleDateFormat stid = new SimpleDateFormat("MMddyyyy");
                SimpleDateFormat stt = new SimpleDateFormat("MM/yyyy");
                SimpleDateFormat sttt = new SimpleDateFormat("yyyy");
                //yearnow.setText(sttt.format(d));
               // monthnow.setText(stt.format(d));
                s_date.setText(st.format(d));
                id.setText(sid.format(d)+stid.format(d));
                s_month.setText(stt.format(d));
                s_year.setText(sttt.format(d));
                
            }
        }).start();

    }
    private void empCount() {
        int row = tbl_item.getRowCount();
        prd_count.setText(String.valueOf(row));
    }
    
     public void refresh_item() {

        try {

            String sql = "SELECT ProductID,ProductName,ProductDescription,ProductCategory,Quantity,OriginalPrice,ValuesSRP,Sale FROM tbl_items";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_item.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
     public void refresh_stock_request() {

        try {

            String sql = "SELECT ProductID,ProductName,ProductDescription,ProductCategory,Quantity,OriginalPrice,ValuesSRP,Sale FROM tbl_stock_request";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_item.setModel(DbUtils.resultSetToTableModel(rs));

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
     
     public void refresh_admin() {

        try {

            String sql = "SELECT AccountID,Name,Username,Address,ContactNo,Status FROM tbl_admin";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_admin.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
     public void refresh_user() {

        try {

            String sql = "SELECT AccountID,Name,Username,Address,ContactNo,Status FROM tbl_user";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_user.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     public void refresh_basic() {

        try {

            String sql = "SELECT * FROM tbl_basic";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_cashier.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     public void refresh_basic_date() {

        try {

            String sql = "SELECT * FROM tbl_basic_date";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_basic_date.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     public void refresh_basic_report() {

        try {

            String sql = "SELECT * FROM tbl_basic_report";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_basic_report.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
      public void refresh_sales() {

        try {

            String sql = "SELECT ProductID,ProductName,ProductDescription,Price,Quantity,TotalPrice,Date,Time,StaffName FROM tbl_sales";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_sales.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
      public void refresh_transaction() {

        try {

            String sql = "SELECT ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice,Size,Color,TransactionDate,"
                    + "Time,PicupDate,PaymentMethod,Payment,Balance FROM tbl_transaction";
            pst = (PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_trans.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
       public void refresh_logs() {
        try {
            //DateAdded,Supplier,ProductID,Description,Category,Quantity,Value,Price,Sale
            String sql = "SELECT * FROM tbl_login_history";
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
        ItemList.setVisible(true);
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
         count_request();
         jLabel94.setText("");
          setColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
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
                  
                     
                         JOptionPane.showMessageDialog(null, "User Logged In!");
                         login.setVisible(false);
        HomeMenu.setVisible(true);
        Sales.setVisible(false);
        Stocks.setVisible(false);
        ItemList.setVisible(true);
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
        resetColor_req(request);
        jLabel85.setText("");
        //count_request();
        jLabel94.setText("");
         setColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_sales);
        resetColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
                          //new PriceChecker().setVisible(true);
                       // this.dispose();
                       

                                           } else {
                        JOptionPane.showMessageDialog(null, "Incorect ID or Password!", "WARNING!", JOptionPane.ERROR_MESSAGE);

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
     
     public int total_bqty() {
        int rowscount = tbl_cashier.getRowCount();
        int sum = 0;
        for (int i = 0; i < rowscount; i++) {
            sum = sum + Integer.parseInt(tbl_cashier.getValueAt(i, 2).toString());
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
     }
     public void sling_clear(){
         t_name.setText("");
         t_con.setText("");
         t_qty.setText("");
         t_price.setText("");
         t_total.setText("");
         t_size.setText("");
         t_color.setText("");
         t_pic.setDate(null);
         t_payment.setText("");
         t_bal.setText("");
         t_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(t_image.getWidth(), t_image.getHeight(), Image.SCALE_DEFAULT)));
         t_image.setText("Select Image");
     }
     public void mug_clear(){
         t1_name.setText("");
         t1_con.setText("");
         t1_qty.setText("");
         t1_price.setText("");
         t1_total.setText("");
         t1_size.setText("");
         t1_color.setText("");
         t1_pic.setDate(null);
         t1_payment.setText("");
         t1_bal.setText("");
         t1_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(t1_image.getWidth(), t1_image.getHeight(), Image.SCALE_DEFAULT)));
         t1_image.setText("Select Image");
     }
      public void folding_clear(){
         t2_name.setText("");
         t2_con.setText("");
         t2_qty.setText("");
         t2_price.setText("");
         t2_total.setText("");
         t2_size.setText("");
         t2_color.setText("");
         t2_pic.setDate(null);
         t2_payment.setText("");
         t2_bal.setText("");
         t2_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(t2_image.getWidth(), t2_image.getHeight(), Image.SCALE_DEFAULT)));
         t2_image.setText("Select Image");
     }
      public void payment_clear(){
        tp_name.setText("");
        tp_item.setText("");
        tp_qty.setText("0");
        tp_price.setText("0.00");
        tp_total.setText("0.00");
        tp_size.setText("");
        tp_color.setText("");
        tp_method.setText("");
        tp_balance.setText("");
        tp_cash.setText("");
        tp_tbal.setText("");
        tp_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(tp_image.getWidth(), tp_image.getHeight(), Image.SCALE_DEFAULT)));
      jButton4.setEnabled(false);
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        login = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        type1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        jLabel101 = new javax.swing.JLabel();
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
        Dashboard = new javax.swing.JPanel();
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
        s_cbprd = new javax.swing.JCheckBox();
        jLabel123 = new javax.swing.JLabel();
        s_price = new javax.swing.JTextField();
        s_add = new javax.swing.JButton();
        s_qty = new javax.swing.JTextField();
        s_prd = new javax.swing.JTextField();
        s_eq = new javax.swing.JLabel();
        s_pnlbasic = new javax.swing.JPanel();
        s_cbbasic = new javax.swing.JCheckBox();
        s_cat = new javax.swing.JComboBox();
        jLabel116 = new javax.swing.JLabel();
        s_catprice = new javax.swing.JTextField();
        jLabel124 = new javax.swing.JLabel();
        s_add2 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        s_bqty = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        s_btotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        v_id = new javax.swing.JLabel();
        s_year = new javax.swing.JLabel();
        s_month = new javax.swing.JLabel();
        Stocks = new javax.swing.JPanel();
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
        Transactions = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel125 = new javax.swing.JLabel();
        t_name = new javax.swing.JTextField();
        t_con = new javax.swing.JTextField();
        jLabel126 = new javax.swing.JLabel();
        t_qty = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        t_payment = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        t_total = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        t_size = new javax.swing.JTextField();
        jLabel130 = new javax.swing.JLabel();
        t_color = new javax.swing.JTextField();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        t_add = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        t_full = new javax.swing.JCheckBox();
        t_partial = new javax.swing.JCheckBox();
        t_price = new javax.swing.JTextField();
        jLabel153 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        t_image = new javax.swing.JLabel();
        t_bal = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel135 = new javax.swing.JLabel();
        t_pic = new com.toedter.calendar.JDateChooser();
        jPanel14 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        t1_name = new javax.swing.JTextField();
        t1_con = new javax.swing.JTextField();
        jLabel154 = new javax.swing.JLabel();
        t1_total = new javax.swing.JTextField();
        t1_color = new javax.swing.JTextField();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        t1_price = new javax.swing.JTextField();
        t1_size = new javax.swing.JTextField();
        jLabel157 = new javax.swing.JLabel();
        t1_qty = new javax.swing.JTextField();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        t1_add = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        t1_payment = new javax.swing.JTextField();
        t1_full = new javax.swing.JCheckBox();
        t1_partial = new javax.swing.JCheckBox();
        jLabel163 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        t1_image = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        t1_bal = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel171 = new javax.swing.JLabel();
        t1_pic = new com.toedter.calendar.JDateChooser();
        jPanel27 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        t2_name = new javax.swing.JTextField();
        t2_con = new javax.swing.JTextField();
        jLabel164 = new javax.swing.JLabel();
        t2_total = new javax.swing.JTextField();
        t2_color = new javax.swing.JTextField();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        t2_price = new javax.swing.JTextField();
        t2_size = new javax.swing.JTextField();
        jLabel167 = new javax.swing.JLabel();
        t2_qty = new javax.swing.JTextField();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        t2_payment = new javax.swing.JTextField();
        t2_full = new javax.swing.JCheckBox();
        t2_partial = new javax.swing.JCheckBox();
        jLabel173 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        t2_image = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        t2_bal = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jLabel177 = new javax.swing.JLabel();
        t2_pic = new com.toedter.calendar.JDateChooser();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_trans = new javax.swing.JTable();
        jLabel176 = new javax.swing.JLabel();
        tp_search = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        tp_image = new javax.swing.JLabel();
        tp_item = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        tp_name = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        tp_qty = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        tp_price = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        tp_total = new javax.swing.JLabel();
        tp_method = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        tp_pay = new javax.swing.JComboBox();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        tp_color = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        tp_size = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        tp_balance = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        tp_cash = new javax.swing.JTextField();
        tp_tbal = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        tp_time = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        Logs = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_loginhis = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
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
        jPanel18 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_basic_report = new javax.swing.JTable();
        jLabel76 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_basic_date = new javax.swing.JTable();
        basic_sales = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        rb_date = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel77 = new javax.swing.JLabel();
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
        jLabel99 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel11 = new javax.swing.JLabel();
        s_time = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        s_date = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        accid = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        acctype = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(2, 97, 139));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("P R I N T A   K O P Y A");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 410, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Costumer Service and Stocks Monitoring System");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 410, 30));

        jPanel25.setBackground(new java.awt.Color(16, 124, 172));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel25.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 38, 30));

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkcssms1/switcher_bottom_off.png"))); // NOI18N
        jLabel75.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel75MouseClicked(evt);
            }
        });
        jPanel25.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        jPanel2.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 80, 30));

        jPanel1.setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));
        login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setText("LOGIN");
        login.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 110, -1, -1));

        txtusername.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtusernameKeyReleased(evt);
            }
        });
        login.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 200, 310, 40));

        type1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        type1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "User" }));
        type1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type1ActionPerformed(evt);
            }
        });
        login.add(type1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, 310, 38));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        login.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 360, 140, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Forgot Password?");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        login.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 360, -1, 27));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setText("User Type:");
        login.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, -1, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel5.setText("Username:");
        login.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, -1, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel7.setText("Password:");
        login.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 249, -1, 30));
        login.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));
        login.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        txtpassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpasswordKeyReleased(evt);
            }
        });
        login.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, 310, 40));

        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PKA LOGO.png"))); // NOI18N
        login.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        jPanel1.add(login, "card2");

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
        pnl_stock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_stock.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 49, 70, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Stocks");
        pnl_stock.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 100, 30));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/full-items-inside-a-shopping-bag.png"))); // NOI18N
        pnl_stock.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 100, -1));

        Menu.add(pnl_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 100, 70));

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

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Sales");
        pnl_sales.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 41, 100, 30));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/shopping-cart.png"))); // NOI18N
        pnl_sales.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        Menu.add(pnl_sales, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 100, 70));

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

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Transactions");
        pnl_transactions.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, 90, 28));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/bankbook.png"))); // NOI18N
        pnl_transactions.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        Menu.add(pnl_transactions, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 110, 70));

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
        pnl_logs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_logs.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Logs");
        pnl_logs.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 90, 28));

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png24/list.png"))); // NOI18N
        pnl_logs.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, 4, 100, 40));

        Menu.add(pnl_logs, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 90, 70));

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

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Accounts");
        pnl_accounts.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 100, 28));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_accounts.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, -1));

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/settings.png"))); // NOI18N
        pnl_accounts.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 100, -1));

        Menu.add(pnl_accounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 100, 70));

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

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Logout");
        pnl_logout.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 90, 28));

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_logout.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, -1));

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/012-insert-memory-card.png"))); // NOI18N
        pnl_logout.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 4, -1, 40));

        Menu.add(pnl_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 90, 70));

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

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Report");
        pnl_report.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 100, 28));

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_report.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, -1));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png32/sale-report.png"))); // NOI18N
        pnl_report.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 40, -1));

        Menu.add(pnl_report, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 100, 70));

        HomeMenu.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 80));

        Dashboard.setLayout(new java.awt.CardLayout());

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
        jButton5.setText("Close Order");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Sales.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 450, 190, 30));

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
        Sales.add(s_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 280, 310, 60));

        jLabel121.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel121.setText("Cash:");
        Sales.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 250, 40, 20));

        jLabel122.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel122.setText("Change:");
        Sales.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 350, 70, 30));

        s_change.setEditable(false);
        s_change.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        s_change.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Sales.add(s_change, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 380, 310, 60));

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

        jPanel21.setBackground(new java.awt.Color(4, 13, 33));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel79.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("PRODUCT INFORMATION");
        jPanel21.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, -1, 41));

        s_pnlprd.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 380, -1));

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

        jPanel20.setBackground(new java.awt.Color(0, 0, 255));

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

        s_cbprd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_cbprdActionPerformed(evt);
            }
        });
        s_pnlprd.add(s_cbprd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel123.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel123.setText("Price");
        s_pnlprd.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, 30));

        s_price.setEditable(false);
        s_price.setBackground(new java.awt.Color(255, 255, 255));
        s_price.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_price.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        s_pnlprd.add(s_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 300, 30));

        s_add.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_add.setText("Add");
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

        Sales.add(s_pnlprd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 850, 230));

        s_pnlbasic.setBackground(new java.awt.Color(247, 247, 247));
        s_pnlbasic.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s_cbbasic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_cbbasicActionPerformed(evt);
            }
        });
        s_pnlbasic.add(s_cbbasic, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        s_cat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        s_cat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Document Print", "Xerox Long", "Xerox Short", "Sticker", "Tarpaulin", "Scan", "Layout" }));
        s_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_catActionPerformed(evt);
            }
        });
        s_pnlbasic.add(s_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 290, 40));

        jLabel116.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel116.setText("Qty");
        s_pnlbasic.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 130, 30));

        s_catprice.setEditable(false);
        s_catprice.setBackground(new java.awt.Color(255, 255, 255));
        s_catprice.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        s_catprice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s_pnlbasic.add(s_catprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 140, 30));

        jLabel124.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel124.setText("Choose Category");
        s_pnlbasic.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, 30));

        s_add2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        s_add2.setText("Add");
        s_add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_add2ActionPerformed(evt);
            }
        });
        s_pnlbasic.add(s_add2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 110, 30));

        jPanel22.setBackground(new java.awt.Color(0, 0, 255));

        jLabel80.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Basic Sales");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel80)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        s_pnlbasic.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 260, -1));

        s_bqty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        s_bqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s_bqtyKeyReleased(evt);
            }
        });
        s_pnlbasic.add(s_bqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 140, 30));

        jLabel134.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel134.setText("Price");
        s_pnlbasic.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 130, 30));

        s_btotal.setEditable(false);
        s_btotal.setBackground(new java.awt.Color(255, 255, 255));
        s_pnlbasic.add(s_btotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 150, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("=");
        s_pnlbasic.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 20, 30));

        Sales.add(s_pnlbasic, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 310, 230));

        v_id.setForeground(new java.awt.Color(255, 255, 255));
        Sales.add(v_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 100, 20));

        s_year.setForeground(new java.awt.Color(255, 255, 255));
        s_year.setText("jLabel12");
        Sales.add(s_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 480, -1, -1));

        s_month.setForeground(new java.awt.Color(255, 255, 255));
        s_month.setText("s_month");
        Sales.add(s_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, -1, -1));

        Dashboard.add(Sales, "card2");

        Stocks.setBackground(new java.awt.Color(255, 255, 255));
        Stocks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setText("ProductID");
        Stocks.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, 30));

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
        Stocks.add(p_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 260, 30));

        jPanel4.setBackground(new java.awt.Color(2, 97, 139));
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

        Stocks.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 300, 40));

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setText("Product Name");
        Stocks.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, 30));

        p_name.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_name.setText(" ");
        Stocks.add(p_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 260, 30));

        jLabel34.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel34.setText("ProductID");
        Stocks.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, 30));

        p_des.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_des.setText(" ");
        Stocks.add(p_des, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 260, 30));

        jLabel35.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel35.setText("ProductD Description");
        Stocks.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, 30));

        p_cat.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_cat.setText(" ");
        Stocks.add(p_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 260, 30));

        jLabel36.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel36.setText("Product Category");
        Stocks.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, 30));

        p_quan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_quan.setText(" ");
        Stocks.add(p_quan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 260, 30));

        jLabel37.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel37.setText("Quantity");
        Stocks.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 70, 30));

        p_org.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_org.setText(" ");
        Stocks.add(p_org, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 260, 30));

        jLabel38.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel38.setText("Orginal Price");
        Stocks.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, 30));

        p_val.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_val.setText(" ");
        Stocks.add(p_val, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 260, 30));

        jLabel39.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel39.setText("Values(SRP)");
        Stocks.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, 30));

        jLabel40.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel40.setText("Sale");
        Stocks.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, -1, 30));

        p_sale.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        p_sale.setText(" ");
        Stocks.add(p_sale, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 260, 30));

        p_add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        p_add.setText("Add");
        p_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_addActionPerformed(evt);
            }
        });
        Stocks.add(p_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, 150, -1));

        p_clear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        p_clear.setText("Clear");
        p_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_clearActionPerformed(evt);
            }
        });
        Stocks.add(p_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 100, -1));

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(2, 97, 139));
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
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, -1));

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

        Stocks.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 430, 400));

        id.setForeground(new java.awt.Color(255, 255, 255));
        Stocks.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 250, 20));

        Dashboard.add(Stocks, "card3");

        Transactions.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel125.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel125.setText("Client Name");
        jPanel3.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, 30));

        t_name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t_name.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel3.add(t_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 380, 30));

        t_con.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t_con.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel3.add(t_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 380, 30));

        jLabel126.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel126.setText("Contact Number");
        jPanel3.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, 30));

        t_qty.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t_qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_qty.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel3.add(t_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 80, 30));

        jLabel127.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel127.setText("Quantity");
        jPanel3.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, 30));

        t_payment.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t_payment.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        t_payment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_paymentMouseClicked(evt);
            }
        });
        t_payment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_paymentKeyReleased(evt);
            }
        });
        jPanel3.add(t_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 240, 30));

        jLabel128.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel128.setText("=");
        jPanel3.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 30, 30));

        t_total.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_total.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel3.add(t_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 180, 30));

        jLabel129.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel129.setText("Total");
        jPanel3.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, 30));

        t_size.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t_size.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_size.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel3.add(t_size, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 130, 30));

        jLabel130.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel130.setText("Size");
        jPanel3.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, 30));

        t_color.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t_color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_color.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel3.add(t_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 180, 30));

        jLabel132.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel132.setText("Color");
        jPanel3.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, 30));

        jLabel133.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel133.setText("Balance");
        jPanel3.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, -1, 30));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 520, 10));

        t_add.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t_add.setText("Add");
        t_add.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        t_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_addActionPerformed(evt);
            }
        });
        jPanel3.add(t_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, 160, 30));

        jButton7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton7.setText("Clear");
        jButton7.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 160, 30));

        jPanel19.setBackground(new java.awt.Color(2, 97, 139));

        jLabel50.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Sling Print Form");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addComponent(jLabel50)
                .addGap(193, 193, 193))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 520, 30));

        jLabel152.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel152.setText("Unit Price");
        jPanel3.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, 30));

        t_full.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        t_full.setText("   Full Payment");
        t_full.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_fullActionPerformed(evt);
            }
        });
        jPanel3.add(t_full, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 140, -1));

        t_partial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        t_partial.setText("   Partial  Payment");
        t_partial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_partialActionPerformed(evt);
            }
        });
        jPanel3.add(t_partial, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 160, -1));

        t_price.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t_price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_price.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        t_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_priceKeyReleased(evt);
            }
        });
        jPanel3.add(t_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 150, 30));

        jLabel153.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel153.setText("Pic-up Date");
        jPanel3.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, 30));

        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel33.setBackground(new java.awt.Color(2, 97, 139));
        jPanel33.setForeground(new java.awt.Color(0, 102, 51));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("DESIGN IMAGE");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jLabel57)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        jPanel32.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, -1));

        jPanel34.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        t_image.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        t_image.setText("Select Image");
        t_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51)));
        t_image.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        t_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_imageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(t_image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(t_image, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );

        jPanel32.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 39, 430, 360));

        jPanel3.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 430, 400));

        t_bal.setEditable(false);
        t_bal.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(t_bal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 240, 30));

        jButton6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton6.setText("Print Form ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 410, 160, 30));

        jLabel135.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel135.setText("Payment");
        jPanel3.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, -1, 30));
        jPanel3.add(t_pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 300, 30));

        jTabbedPane1.addTab("  Sling Print Form  ", jPanel3);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel35.setBackground(new java.awt.Color(2, 97, 139));

        jLabel59.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("MUG Print Form");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(216, Short.MAX_VALUE)
                .addComponent(jLabel59)
                .addGap(193, 193, 193))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 520, 30));

        t1_name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t1_name.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel14.add(t1_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 380, 30));

        t1_con.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t1_con.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel14.add(t1_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 380, 30));

        jLabel154.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel154.setText("Total");
        jPanel14.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, 30));

        t1_total.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t1_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1_total.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel14.add(t1_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 180, 30));

        t1_color.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t1_color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1_color.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel14.add(t1_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 180, 30));

        jLabel155.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel155.setText("Color");
        jPanel14.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, 30));

        jLabel156.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel156.setText("=");
        jPanel14.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 30, 30));

        t1_price.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t1_price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1_price.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        t1_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t1_priceKeyReleased(evt);
            }
        });
        jPanel14.add(t1_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 150, 30));

        t1_size.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t1_size.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1_size.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel14.add(t1_size, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 130, 30));

        jLabel157.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel157.setText("Size");
        jPanel14.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, 30));

        t1_qty.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t1_qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1_qty.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel14.add(t1_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 80, 30));

        jLabel158.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel158.setText("Quantity");
        jPanel14.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, 30));

        jLabel159.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel159.setText("Contact Number");
        jPanel14.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, 30));

        jLabel160.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel160.setText("Client Name");
        jPanel14.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, 30));

        jLabel162.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel162.setText("Pic-up Date");
        jPanel14.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, 30));

        t1_add.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t1_add.setText("Add");
        t1_add.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        t1_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1_addActionPerformed(evt);
            }
        });
        jPanel14.add(t1_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, 160, 30));

        jButton13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton13.setText("Clear");
        jButton13.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 160, 30));

        t1_payment.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t1_payment.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        t1_payment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t1_paymentKeyReleased(evt);
            }
        });
        jPanel14.add(t1_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 240, 30));

        t1_full.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        t1_full.setText("   Full Payment");
        t1_full.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1_fullActionPerformed(evt);
            }
        });
        jPanel14.add(t1_full, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 140, -1));

        t1_partial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        t1_partial.setText("   Partial  Payment");
        t1_partial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1_partialActionPerformed(evt);
            }
        });
        jPanel14.add(t1_partial, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 160, -1));

        jLabel163.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel163.setText("Balance");
        jPanel14.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, -1, 30));

        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel37.setBackground(new java.awt.Color(2, 97, 139));
        jPanel37.setForeground(new java.awt.Color(0, 102, 51));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("DESIGN IMAGE");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jLabel60)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        jPanel36.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, -1));

        jPanel38.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        t1_image.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t1_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        t1_image.setText("Select Image");
        t1_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51)));
        t1_image.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        t1_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t1_imageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(t1_image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(t1_image, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );

        jPanel36.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 39, 430, 360));

        jPanel14.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 430, 400));

        jLabel174.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel174.setText("Unit Price");
        jPanel14.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, 30));
        jPanel14.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 520, 10));

        t1_bal.setEditable(false);
        t1_bal.setBackground(new java.awt.Color(255, 255, 255));
        t1_bal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1_balActionPerformed(evt);
            }
        });
        jPanel14.add(t1_bal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 240, 30));

        jButton12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton12.setText("Print Form ");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 410, 160, 30));

        jLabel171.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel171.setText("Payment");
        jPanel14.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, -1, 30));
        jPanel14.add(t1_pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 300, 30));

        jTabbedPane1.addTab("  MUG Print Form  ", jPanel14);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel39.setBackground(new java.awt.Color(2, 97, 139));

        jLabel62.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Tshirt Print Form");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addComponent(jLabel62)
                .addGap(193, 193, 193))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel27.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 520, 30));

        t2_name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t2_name.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel27.add(t2_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 380, 30));

        t2_con.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t2_con.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel27.add(t2_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 380, 30));

        jLabel164.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel164.setText("Total");
        jPanel27.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, 30));

        t2_total.setEditable(false);
        t2_total.setBackground(new java.awt.Color(255, 255, 255));
        t2_total.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t2_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2_total.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel27.add(t2_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 180, 30));

        t2_color.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t2_color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2_color.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel27.add(t2_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 180, 30));

        jLabel165.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel165.setText("Color Of Shirt");
        jPanel27.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, 30));

        jLabel166.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel166.setText("=");
        jPanel27.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 30, 30));

        t2_price.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t2_price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2_price.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        t2_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t2_priceKeyReleased(evt);
            }
        });
        jPanel27.add(t2_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 150, 30));

        t2_size.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t2_size.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2_size.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel27.add(t2_size, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 130, 30));

        jLabel167.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel167.setText("TShirt Size");
        jPanel27.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, 30));

        t2_qty.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t2_qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2_qty.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel27.add(t2_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 80, 30));

        jLabel168.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel168.setText("Quantity");
        jPanel27.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, 30));

        jLabel169.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel169.setText("Contact Number");
        jPanel27.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, 30));

        jLabel170.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel170.setText("Client Name");
        jPanel27.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, 30));

        jLabel172.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel172.setText("Pic-up Date");
        jPanel27.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, 30));

        jButton14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton14.setText("Add");
        jButton14.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, 160, 30));

        jButton15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton15.setText("Clear");
        jButton15.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 160, 30));

        t2_payment.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        t2_payment.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        t2_payment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t2_paymentKeyReleased(evt);
            }
        });
        jPanel27.add(t2_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 240, 30));

        t2_full.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        t2_full.setText("   Full Payment");
        t2_full.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t2_fullActionPerformed(evt);
            }
        });
        jPanel27.add(t2_full, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 140, -1));

        t2_partial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        t2_partial.setText("   Partial  Payment");
        t2_partial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t2_partialActionPerformed(evt);
            }
        });
        jPanel27.add(t2_partial, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 160, -1));

        jLabel173.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel173.setText("Balance");
        jPanel27.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, -1, 30));

        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel41.setBackground(new java.awt.Color(2, 97, 139));
        jPanel41.setForeground(new java.awt.Color(0, 102, 51));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("DESIGN IMAGE");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jLabel63)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        jPanel40.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, -1));

        jPanel42.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        t2_image.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t2_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        t2_image.setText("Select Image");
        t2_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51)));
        t2_image.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        t2_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t2_imageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(t2_image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(t2_image, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );

        jPanel40.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 39, 430, 360));

        jPanel27.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 430, 400));

        jLabel175.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel175.setText("Unit Price");
        jPanel27.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, 30));
        jPanel27.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 520, 10));

        t2_bal.setEditable(false);
        t2_bal.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.add(t2_bal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 240, 30));

        jButton23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton23.setText("Print Form ");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 410, 160, 30));

        jLabel177.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel177.setText("Payment");
        jPanel27.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, -1, 30));
        jPanel27.add(t2_pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 300, 30));

        jTabbedPane1.addTab("  Tshirt Print Form  ", jPanel27);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_trans.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_trans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_transMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_transMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_trans);

        jPanel23.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1230, 190));

        jLabel176.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel176.setText("Search");
        jPanel23.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        tp_search.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tp_search.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        tp_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tp_searchKeyReleased(evt);
            }
        });
        jPanel23.add(tp_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 380, 30));

        tp_image.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tp_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tp_image.setText("No Image");
        tp_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tp_image, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tp_image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        jPanel23.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 260, 210));

        tp_item.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel23.add(tp_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 130, 20));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Item:");
        jPanel23.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, -1, -1));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Costumer Name: ");
        jPanel23.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 130, -1));

        tp_name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel23.add(tp_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 240, 20));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("Quantity:");
        jPanel23.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, -1));

        tp_qty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tp_qty.setText("0");
        jPanel23.add(tp_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, 130, 20));

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setText("Unit Price:");
        jPanel23.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, -1, -1));

        tp_price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tp_price.setText("0.00");
        jPanel23.add(tp_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 120, 20));

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel93.setText("Total Price:");
        jPanel23.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, -1, -1));

        tp_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tp_total.setText("0.00");
        jPanel23.add(tp_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, 120, 20));

        tp_method.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel23.add(tp_method, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 260, 220, 20));

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel96.setText("Pay Method");
        jPanel23.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 7, 100, 30));

        tp_pay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tp_pay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Full Payment", "Partial Payment" }));
        tp_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tp_payActionPerformed(evt);
            }
        });
        jPanel23.add(tp_pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 250, 30));

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel97.setText("Balance:");
        jPanel23.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 290, 100, 40));

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel98.setText("Color:");
        jPanel23.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, -1, -1));

        tp_color.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel23.add(tp_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, 120, 20));

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel100.setText("Size:");
        jPanel23.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, -1, -1));

        tp_size.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel23.add(tp_size, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 120, 20));

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel102.setText("Payment Method:");
        jPanel23.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, 130, -1));

        tp_balance.setEditable(false);
        tp_balance.setBackground(new java.awt.Color(255, 255, 255));
        tp_balance.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tp_balance.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel23.add(tp_balance, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, 270, 40));

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel103.setText("Cash Recieved:");
        jPanel23.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 340, 140, 40));

        tp_cash.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tp_cash.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp_cash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tp_cashKeyReleased(evt);
            }
        });
        jPanel23.add(tp_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 340, 270, 40));

        tp_tbal.setEditable(false);
        tp_tbal.setBackground(new java.awt.Color(255, 255, 255));
        tp_tbal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tp_tbal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel23.add(tp_tbal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 390, 270, 40));

        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel104.setText("Total Balance:");
        jPanel23.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, 140, 40));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 440, 120, -1));

        tp_time.setForeground(new java.awt.Color(255, 255, 255));
        tp_time.setText("jLabel42");
        jPanel23.add(tp_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 130, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 440, 140, -1));

        jTabbedPane1.addTab("   Payment   ", jPanel23);

        javax.swing.GroupLayout TransactionsLayout = new javax.swing.GroupLayout(Transactions);
        Transactions.setLayout(TransactionsLayout);
        TransactionsLayout.setHorizontalGroup(
            TransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1240, Short.MAX_VALUE)
        );
        TransactionsLayout.setVerticalGroup(
            TransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        Dashboard.add(Transactions, "card4");

        Logs.setBackground(new java.awt.Color(255, 255, 255));
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
        jScrollPane6.setViewportView(tbl_loginhis);

        Logs.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 950, 430));

        jLabel65.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel65.setText("Account Logs");
        Logs.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 40));

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer(1).png"))); // NOI18N
        jLabel105.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel105.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel105MouseClicked(evt);
            }
        });
        Logs.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 40, 30));

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
        aupdate.setText("Save");
        aupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aupdateActionPerformed(evt);
            }
        });
        addaccount.add(aupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 410, 130, 30));

        aclear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
        jButton18.setText("Save");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        manageacc.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 100, 30));

        jButton19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton19.setText("Clear");
        manageacc.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 110, 30));

        jButton20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton20.setText("Clear");
        manageacc.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 330, 110, 30));

        jButton21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton21.setText("Save");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        manageacc.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 100, 30));

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
        jPanel17.add(report_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 430, 60, -1));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel72.setText("TOTAL ITEM");
        jPanel17.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 430, -1, -1));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel73.setText("TOTAL SALES");
        jPanel17.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 430, -1, -1));

        report_sales.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        report_sales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        report_sales.setText("00");
        jPanel17.add(report_sales, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 430, 170, -1));

        rdel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel17.add(rdel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 190, 10));
        jPanel17.add(r_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 20, 300, 30));

        jTabbedPane2.addTab("   Sales Report   ", jPanel17);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_basic_report.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tbl_basic_report);

        jPanel18.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 750, 360));

        jLabel76.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel76.setText("SALES REPORT");
        jPanel18.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 30));

        jButton24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton24.setText("Filter");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, 30));

        tbl_basic_date.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_basic_date.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_basic_dateMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_basic_date);

        jPanel18.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 460, 360));

        basic_sales.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        basic_sales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        basic_sales.setText("0.00");
        jPanel18.add(basic_sales, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 70, 170, 30));

        jLabel81.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel81.setText("BASIC SALES REPORT");
        jPanel18.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 30));

        jLabel82.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel82.setText("PREVIEW");
        jPanel18.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, -1, 30));

        jLabel83.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel83.setText("TOTAL SALES:");
        jPanel18.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 70, -1, 30));

        rb_date.setForeground(new java.awt.Color(255, 255, 255));
        jPanel18.add(rb_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 110, 20));

        jDateChooser1.setDateFormatString("MM/dd/yyyy");
        jPanel18.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 320, 30));

        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jPanel18.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 100, 20));

        jTabbedPane2.addTab("   Basic Sales Report   ", jPanel18);

        Report.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 500));

        Dashboard.add(Report, "card7");

        ItemList.setBackground(new java.awt.Color(255, 255, 255));
        ItemList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        tbl_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_itemMouseClicked(evt);
            }
        });
        tbl_item.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_itemKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_item);

        ItemList.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1220, 460));

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
        ItemList.add(i_search1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 450, 30));

        st_prdnew.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        st_prdnew.setText("+ Add New Product");
        st_prdnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                st_prdnewActionPerformed(evt);
            }
        });
        ItemList.add(st_prdnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 240, 30));

        prd_count.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        prd_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prd_count.setText("00");
        ItemList.add(prd_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 20, 90, 30));

        jLabel45.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel45.setText("Search");
        ItemList.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, 30));

        jLabel46.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel46.setText("Count List:");
        ItemList.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 20, -1, 30));

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

        ItemList.add(request, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 10, 10));
        ItemList.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 20, 10));

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ItemList.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 200, 30));

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ItemList.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 110, 30));

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer(1).png"))); // NOI18N
        jLabel99.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel99MouseClicked(evt);
            }
        });
        ItemList.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 20, 40, 30));

        Dashboard.add(ItemList, "card8");

        HomeMenu.add(Dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1240, 520));

        jPanel1.add(HomeMenu, "card3");

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setRollover(true);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Time:  ");
        jToolBar1.add(jLabel11);

        s_time.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        s_time.setText("00:00:00");
        jToolBar1.add(s_time);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("  Date:  ");
        jToolBar1.add(jLabel13);

        s_date.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        s_date.setText("dd/mm/yyyy");
        jToolBar1.add(s_date);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("  AccountID:  ");
        jToolBar1.add(jLabel15);

        accid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        accid.setText("---------------------");
        jToolBar1.add(accid);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("  User's Name:  ");
        jToolBar1.add(jLabel17);

        user.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        user.setText("--------------------");
        jToolBar1.add(user);

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel107.setText("  Account Type:  ");
        jToolBar1.add(jLabel107);

        acctype.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        acctype.setText("--------------------");
        jToolBar1.add(acctype);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 1240, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 597, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel75MouseClicked
        // TODO add your handling code here:
        this.setState(1);
    }//GEN-LAST:event_jLabel75MouseClicked

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
                        JOptionPane.showMessageDialog(null, "Incorrect Username/Password!!!");
                    }
                }
            }catch(SQLException x) {
                JOptionPane.showMessageDialog(null, x);
            }

        }
    }//GEN-LAST:event_txtusernameKeyReleased

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
                    JOptionPane.showMessageDialog(null, "Incorrect Username/Password!!!");
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
                        JOptionPane.showMessageDialog(null, "Incorrect Username/Password!!!");
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
        refresh_item();
        empCount();
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
        s_cbprd.setSelected(false);

        s_cbbasic.setSelected(true);
        s_cat.setEnabled(true);
        s_catprice.setEditable(true);
        s_barcode.setEditable(false);
        s_qty.setEditable(false);

        s_add.setEnabled(false);
        s_add2.setEnabled(true);

        refresh_basic();
        totprice.setText(Double.toString(total_bprice()));
        totqty.setText(Integer.toString(total_bqty()));
        jButton5.setEnabled(true);
        s_cash.setText("");
        s_change.setText("");
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

        resetColor_manage(pnl_stock);
        resetColor_manage(pnl_sales);
        setColor_manage(pnl_transactions);
        resetColor_manage(pnl_report);
        resetColor_manage(pnl_accounts);
        resetColor_manage(pnl_logs);
        resetColor_manage(pnl_logout);
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

            resetColor_manage(pnl_stock);
            resetColor_manage(pnl_sales);
            resetColor_manage(pnl_sales);
            resetColor_manage(pnl_transactions);
            resetColor_manage(pnl_report);
            setColor_manage(pnl_accounts);
            resetColor_manage(pnl_logs);
            resetColor_manage(pnl_logout);
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

            resetColor_manage(pnl_stock);
            resetColor_manage(pnl_sales);
            resetColor_manage(pnl_sales);
            resetColor_manage(pnl_transactions);
            resetColor_manage(pnl_report);
            setColor_manage(pnl_accounts);
            resetColor_manage(pnl_logs);
            resetColor_manage(pnl_logout);

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
            refresh_sales();
            report_sales.setText(Double.toString(total_report_Sales()));
            report_item.setText(Integer.toString(total_sales_summary()));
            r_date.setDate(null);
            Daily_transation();
            daily.setSelected(true);
            monthly.setSelected(false);
            yearly.setSelected(false);
            rdel.setText("");
            refresh_basic_date();
            daily_basic();

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
        if (s_cbprd.isSelected()){
            if (v_id.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please Select from Table to Void Item!!");
            }else{
                try{
                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_cashier WHERE Time = '" + v_id.getText() + "'");
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
                                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_cashier WHERE Time = '" + v_id.getText() + "'");
                                int del = pst.executeUpdate();
                                if (del > 0) {
                                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_sales WHERE Time = '" + v_id.getText() + "'");
                                    pst.executeUpdate();
                                    refresh_cashier();
                                    v_id.setText("");
                                    totprice.setText(Double.toString(total_price()));
                                    totqty.setText(Integer.toString(total_qty()));
                                }}}}
                            } catch(SQLException ex){
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        }
                    }else if (s_cbbasic.isSelected()){
                        if (v_id.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Please Select from Table to Void Item!!");
                        }else{
                            try {
                                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_basic WHERE Time = '" + v_id.getText() + "'");
                                int del = pst.executeUpdate();
                                if (del > 0) {
                                    refresh_basic();
                                    v_id.setText("");
                                    totprice.setText(Double.toString(total_price()));
                                    totqty.setText(Integer.toString(total_qty()));
                                }else{
                                    JOptionPane.showMessageDialog(null, "Error Selection!!");
                                }
                            } catch(SQLException ex){
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        }}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbl_cashierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_cashierMouseClicked
        // TODO add your handling code here:
        if (s_cbprd.isSelected()){
            int z = tbl_cashier.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tbl_cashier.getModel();
            v_id.setText(model.getValueAt(z, 7).toString());
        }else if (s_cbbasic.isSelected()){
            int z = tbl_cashier.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tbl_cashier.getModel();
            v_id.setText(model.getValueAt(z, 5).toString());
        }
    }//GEN-LAST:event_tbl_cashierMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here
        if (s_cbbasic.isSelected()){
            try{
                String sql1 = "INSERT INTO tbl_basic_date VALUES (?,?,?,?)";
                pst = (PreparedStatement) conn.prepareStatement(sql1);
                pst.setString(1,(String)jLabel80.getText());
                pst.setString(2,totqty.getText());
                pst.setString(3,totprice.getText());
                pst.setString(4,s_date.getText());
                pst.executeUpdate();
                String sq = "INSERT INTO tbl_basic_report Select * from tbl_basic";
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sq);
                pst.executeUpdate();
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_basic");
                pst.executeUpdate();
                refresh_basic();

            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if (s_cbprd.isSelected()){

            try {
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_cashier");
                int del = pst.executeUpdate();
                if (del > 0) {
                    refresh_cashier();
                    v_id.setText("");
                    totprice.setText(Double.toString(total_price()));
                    totqty.setText(Integer.toString(total_qty()));
                    s_cash.setText("");
                    s_change.setText("");
                    jButton5.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Error Selection!!");
                }
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void s_cashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_cashKeyReleased
        // TODO add your handling code here:
        if (s_cbprd.isSelected()){
            if (s_cash.getText().equals("")){
                s_change.setText("0.00");
            }else{
                Double a, b, c;
                a = Double.parseDouble(s_cash.getText());
                b = Double.parseDouble(totprice.getText());
                c = a - b;
                s_change.setText(String.valueOf(c));
                jButton5.setEnabled(true);
            }
        }
    }//GEN-LAST:event_s_cashKeyReleased

    private void s_barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_barcodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_barcodeKeyPressed

    private void s_barcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_barcodeKeyReleased
        // TODO add your handling code here:
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
    }//GEN-LAST:event_s_barcodeKeyReleased

    private void s_cbprdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_cbprdActionPerformed
        // TODO add your handling code here:
        if(s_cbprd.isSelected()){
            s_cbbasic.setSelected(false);
            s_barcode.setEditable(true);
            s_qty.setEditable(true);

            s_cat.setEnabled(false);
            s_catprice.setEditable(false);

            s_add.setEnabled(true);
            s_add2.setEnabled(false);

            s_add.setEnabled(false);
            s_bqty.setEditable(false);
            s_btotal.setEditable(false);
            refresh_cashier();
            totprice.setText(Double.toString(total_price()));
            totqty.setText(Integer.toString(total_qty()));
            jButton5.setEnabled(false);
            s_cash.setText("");
            s_change.setText("");
        }else {
            s_cbbasic.setSelected(true);
            s_barcode.setEditable(false);
            s_qty.setEditable(false);

            s_cat.setEnabled(true);
            s_catprice.setEditable(true);
            s_bqty.setEditable(true);
            s_btotal.setEditable(true);

            s_add.setEnabled(false);
            s_add2.setEnabled(true);
            refresh_basic();
            totprice.setText(Double.toString(total_bprice()));
            totqty.setText(Integer.toString(total_bqty()));
            jButton5.setEnabled(true);
            s_cash.setText("");
            s_change.setText("");
        }
    }//GEN-LAST:event_s_cbprdActionPerformed

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

                }else{

                    String sql1 = "INSERT INTO tbl_cashier VALUES (?,?,?,?,?,?,?,?)";
                    String sql = "INSERT INTO tbl_sales VALUES (?,?,?,?,?,?,?,?,?,?,?)";

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

    private void s_cbbasicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_cbbasicActionPerformed
        // TODO add your handling code here:
        if(s_cbbasic.isSelected()){

            s_cbprd.setSelected(false);

            s_cat.setEnabled(true);
            s_catprice.setEditable(true);
            s_barcode.setEditable(false);
            s_qty.setEditable(false);
            s_bqty.setEditable(true);
            s_btotal.setEditable(true);

            s_add.setEnabled(false);
            s_add2.setEnabled(true);
            refresh_basic();
            totprice.setText(Double.toString(total_bprice()));
            totqty.setText(Integer.toString(total_bqty()));
            jButton5.setEnabled(true);
            s_cash.setText("");
            s_change.setText("");
        }else {

            s_cbprd.setSelected(true);
            s_barcode.setEditable(true);
            s_qty.setEditable(true);

            s_cat.setEnabled(false);
            s_catprice.setEditable(false);

            s_add.setEnabled(false);
            s_add2.setEnabled(false);

            s_bqty.setEditable(false);
            s_btotal.setEditable(false);
            refresh_cashier();
            totprice.setText(Double.toString(total_price()));
            totqty.setText(Integer.toString(total_qty()));
            jButton5.setEnabled(false);
            s_cash.setText("");
            s_change.setText("");
        }
    }//GEN-LAST:event_s_cbbasicActionPerformed

    private void s_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_catActionPerformed
        // TODO add your handling code here:
        if (s_cat.getSelectedItem().equals("Document Print")){
            s_catprice.setText("1.00");
        } else if (s_cat.getSelectedItem().equals("Xerox Long")){
            s_catprice.setText("1.50");
        }else if (s_cat.getSelectedItem().equals("Xerox Short")){
            s_catprice.setText("1.00");
        }else if (s_cat.getSelectedItem().equals("Sticker")){
            s_catprice.setText("10.00");
        }else if (s_cat.getSelectedItem().equals("Tarpaulin")){
            s_catprice.setEditable(true);
        }else if (s_cat.getSelectedItem().equals("Scan")){
            s_catprice.setEditable(true);
        }else if (s_cat.getSelectedItem().equals("layout")){
            s_catprice.setEditable(true);
        }
    }//GEN-LAST:event_s_catActionPerformed

    private void s_add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_add2ActionPerformed
        // TODO add your handling code here:
        try{
            String sql1 = "INSERT INTO tbl_basic VALUES (?,?,?,?,?,?)";

            pst = (PreparedStatement) conn.prepareStatement(sql1);
            pst.setString(1,(String)s_cat.getSelectedItem());
            pst.setString(2,s_catprice.getText());
            pst.setString(3,s_bqty.getText());
            pst.setString(4,s_btotal.getText());
            pst.setString(5, s_date.getText());
            pst.setString(6,s_time.getText());
            int add = pst.executeUpdate();
            if(add!=0){
                refresh_basic();
                s_catprice.setEditable(false);
                s_catprice.setText("");
                s_bqty.setText("");
                s_btotal.setText("");
                totprice.setText(Double.toString(total_bprice()));
                totqty.setText(Integer.toString(total_bqty()));
            }

        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_s_add2ActionPerformed

    private void s_bqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_bqtyKeyReleased
        // TODO add your handling code here:
        if (s_bqty.getText().equals("")){

        }else{
            Double a, c; int b;
            a = Double.parseDouble(s_catprice.getText());
            b = Integer.parseInt(s_bqty.getText());
            c = a * b;
            s_btotal.setText(String.valueOf(c));
        }
    }//GEN-LAST:event_s_bqtyKeyReleased

    private void p_idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_idMouseClicked
        // TODO add your handling code here:
        if (p_id.getText().equals("[Click To Auto Generate ID]")){
            p_id.setText("ID"+id.getText());
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

                            String sql1 = "INSERT INTO tbl_items(ProductID,ProductName,ProductDescription,ProductCategory,Quantity,OriginalPrice,ValuesSRP,Sale) VALUES (?,?,?,?,?,?,?,?)";

                            pst = (PreparedStatement) conn.prepareStatement(sql1);
                            pst.setString(1,p_id.getText());
                            pst.setString(2,p_name.getText());
                            pst.setString(3, p_des.getText());
                            pst.setString(4,p_cat.getText());
                            pst.setString(5,p_quan.getText());
                            pst.setString(6,p_org.getText());
                            pst.setString(7,p_val.getText());
                            pst.setString(8,p_sale.getText());

                            int add = pst.executeUpdate();
                            if(add!=0){
                                refresh_item();
                                item_clear();
                                empCount();
                                JOptionPane.showMessageDialog(null,"New Product Stored!!");

                            } else{
                                JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                            }

                        }else if(acctype.getText().equals("User")){

                            String sql1 = "INSERT INTO tbl_stock_request(ProductID,ProductName,ProductDescription,ProductCategory,Quantity,OriginalPrice,ValuesSRP,Sale) VALUES (?,?,?,?,?,?,?,?)";

                            pst = (PreparedStatement) conn.prepareStatement(sql1);
                            pst.setString(1,p_id.getText());
                            pst.setString(2,p_name.getText());
                            pst.setString(3, p_des.getText());
                            pst.setString(4,p_cat.getText());
                            pst.setString(5,p_quan.getText());
                            pst.setString(6,p_org.getText());
                            pst.setString(7,p_val.getText());
                            pst.setString(8,p_sale.getText());

                            int add = pst.executeUpdate();
                            if(add!=0){
                                refresh_item();
                                item_clear();
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
                             String sql= "INSERT INTO tbl_items VALUES  (?,?,?,?,?,?,?,?,?)";
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
                            int add = pst.executeUpdate();
                            if(add!=0){
                                refresh_item();
                                item_clear();
                                empCount();
                                JOptionPane.showMessageDialog(null,"New Product Stored!!");

                            } else{
                                JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                            }
                            
                            }else if (acctype.getText().equals("User")){
                            
                            
                                String sql= "INSERT INTO tbl_stock_request VALUES  (?,?,?,?,?,?,?,?,?)";
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
                            int add = pst.executeUpdate();
                            if(add!=0){
                                refresh_item();
                                item_clear();
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
            if (acctype.getText().equals("User")){
                JOptionPane.showMessageDialog(null,"Invalid User Field!!!");
            }else{
                if (p_image.getText().equals("Select Image")){
                    try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  ProductID=?, ProductName=?, ProductDescription=?, ProductCategory=?, "
                            + "Quantity=?, OriginalPrice=?, ValuesSRP=?, Sale=?WHERE ProductID='" + p_id.getText() + "'");

                        pst.setString(1, p_id.getText());
                        pst.setString(2, p_name.getText());
                        pst.setString(3, p_des.getText());
                        pst.setString(4, p_cat.getText());
                        pst.setString(5, p_quan.getText());
                        pst.setString(6, p_org.getText());
                        pst.setString(7, p_val.getText());
                        pst.setString(8, p_sale.getText());

                        int update = pst.executeUpdate();
                        if (update != 0) {
                            refresh_item();
                            item_clear();
                            JOptionPane.showMessageDialog(null,"Item Updated");
                            p_add.setText("Add");
                        }
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }else{
                    try {
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_items SET  ProductID=?, ProductName=?, ProductDescription=?, ProductCategory=?, "
                            + "Quantity=?, OriginalPrice=?, ValuesSRP=?, Sale=? ,Image=? WHERE ProductID='" + p_id.getText() + "'");
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
    }//GEN-LAST:event_p_addActionPerformed

    private void p_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_clearActionPerformed
        // TODO add your handling code here:
        item_clear();
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

    private void t_paymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_paymentMouseClicked

    }//GEN-LAST:event_t_paymentMouseClicked

    private void t_paymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_paymentKeyReleased
        // TODO add your handling code here:
        if (t_payment.getText().equals("")){
            t_bal.setText(t_total.getText());
        }else{
            Double a, b, c;
            a = Double.parseDouble(t_total.getText());
            b = Double.parseDouble(t_payment.getText());
            c = a - b;
            t_bal.setText(String.valueOf(c));
        }
    }//GEN-LAST:event_t_paymentKeyReleased

    private void t_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_addActionPerformed
        // TODO add your handling code here:

        if (t_image.getText().equals("Select Image")){
            try{

                if (t_partial.isSelected()){
                    String sql1 = "INSERT INTO tbl_transaction(ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice"
                    + ",Size,Color,TransactionDate,Time,PicupDate,PaymentMethod,Payment,Balance,Month,Year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pst = (PreparedStatement) conn.prepareStatement(sql1);
                    pst.setString(1,t_name.getText());
                    pst.setString(2,t_con.getText());
                    pst.setString(3,"Sling");
                    pst.setString(4,t_qty.getText());
                    pst.setString(5,t_price.getText());
                    pst.setString(6,t_total.getText());
                    pst.setString(7,t_size.getText());
                    pst.setString(8,t_color.getText());
                    pst.setString(9, s_date.getText());
                    pst.setString(10,s_time.getText());
                    pst.setString(11,((JTextField)t_pic.getDateEditor().getUiComponent()).getText());
                    pst.setString(12,"Partial Payment");
                    pst.setString(13,t_payment.getText());
                    pst.setString(14,t_bal.getText());
                    pst.setString(15, s_month.getText());
                    pst.setString(16, s_year.getText());
                    int add = pst.executeUpdate();
                    if(add!=0){
                        sling_clear();
                    } else{
                        JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                    }
                }else if (t_full.isSelected()){
                    String sql1 = "INSERT INTO tbl_transaction(ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice"
                    + ",Size,Color,TransactionDate,Time,PicupDate,PaymentMethod,Payment,Balance,Month,Year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pst = (PreparedStatement) conn.prepareStatement(sql1);
                    pst.setString(1,t_name.getText());
                    pst.setString(2,t_con.getText());
                    pst.setString(3,"Sling");
                    pst.setString(4,t_qty.getText());
                    pst.setString(5,t_price.getText());
                    pst.setString(6,t_total.getText());
                    pst.setString(7,t_size.getText());
                    pst.setString(8,t_color.getText());
                    pst.setString(9, s_date.getText());
                    pst.setString(10,s_time.getText());
                    pst.setString(11,((JTextField)t_pic.getDateEditor().getUiComponent()).getText());
                    pst.setString(12,"Full Payment");
                    pst.setString(13,t_payment.getText());
                    pst.setString(14,t_bal.getText());
                    pst.setString(15, s_month.getText());
                    pst.setString(16, s_year.getText());
                    int add = pst.executeUpdate();
                    if(add!=0){
                        sling_clear();
                    } else{
                        JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                    }
                }

            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if (t_image.getText().equals("")){
            if (imgpath != null){
                try{
                    if (t_partial.isSelected()){
                        String sql1 = "INSERT INTO tbl_transaction VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        pst = (PreparedStatement) conn.prepareStatement(sql1);
                        InputStream img = new FileInputStream(new File(imgpath));
                        pst.setString(1,t_name.getText());
                        pst.setString(2,t_con.getText());
                        pst.setString(3,"Sling");
                        pst.setString(4,t_qty.getText());
                        pst.setString(5,t_price.getText());
                        pst.setString(6,t_total.getText());
                        pst.setString(7,t_size.getText());
                        pst.setString(8,t_color.getText());
                        pst.setString(9, s_date.getText());
                        pst.setString(10,s_time.getText());
                        pst.setString(11,((JTextField)t_pic.getDateEditor().getUiComponent()).getText());
                        pst.setString(12,"Partial Payment");
                        pst.setString(13,t_payment.getText());
                        pst.setString(14,t_bal.getText());
                        pst.setString(15, s_month.getText());
                        pst.setString(16, s_year.getText());
                        pst.setBlob(17,img);
                        int add = pst.executeUpdate();
                        if(add!=0){
                            sling_clear();
                        } else{
                            JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                        }
                    }else if (t_full.isSelected()){
                        String sql1 = "INSERT INTO tbl_transaction VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        pst = (PreparedStatement) conn.prepareStatement(sql1);
                        InputStream img = new FileInputStream(new File(imgpath));
                        pst.setString(1,t_name.getText());
                        pst.setString(2,t_con.getText());
                        pst.setString(3,"Sling");
                        pst.setString(4,t_qty.getText());
                        pst.setString(5,t_price.getText());
                        pst.setString(6,t_total.getText());
                        pst.setString(7,t_size.getText());
                        pst.setString(8,t_color.getText());
                        pst.setString(9,s_date.getText());
                        pst.setString(10,s_time.getText());
                        pst.setString(11,((JTextField)t_pic.getDateEditor().getUiComponent()).getText());
                        pst.setString(12,"Full Payment");
                        pst.setString(13,t_payment.getText());
                        pst.setString(14,t_bal.getText());
                        pst.setString(15, s_month.getText());
                        pst.setString(16, s_year.getText());
                        pst.setBlob(17,img);
                        int add = pst.executeUpdate();
                        if(add!=0){
                            sling_clear();
                        } else{
                            JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                        }
                    }

                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }       catch (FileNotFoundException ex) {
                    Logger.getLogger(HOME.class.getName()).log(Level.SEVERE, null, ex);
                }
            }imgpath = null;
        }
    }//GEN-LAST:event_t_addActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        sling_clear();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void t_fullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_fullActionPerformed
        // TODO add your handling code here:
        if (t_full.isSelected()){
            t_partial.setSelected(false);
            t_payment.setText(t_total.getText());
            t_bal.setText("0.00");
        }else {
            t_partial.setSelected(true);
            t_payment.setText("");
        }
    }//GEN-LAST:event_t_fullActionPerformed

    private void t_partialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_partialActionPerformed
        // TODO add your handling code here:
        if (t_partial.isSelected()){
            t_full.setSelected(false);
            t_payment.setText("");
        }else {
            t_full.setSelected(true);
            t_payment.setText(t_total.getText());
            t_bal.setText("0.00");
        }
    }//GEN-LAST:event_t_partialActionPerformed

    private void t_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_priceKeyReleased
        // TODO add your handling code here:
        if (t_price.getText().equals("")){
            t_total.setText("0.00");
        }else{
            Double a, b, c;
            a = Double.parseDouble(t_qty.getText());
            b = Double.parseDouble(t_price.getText());
            c = a * b;
            t_total.setText(String.valueOf(c));
        }
    }//GEN-LAST:event_t_priceKeyReleased

    private void t_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_imageMouseClicked
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
            t_image.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(t_image.getWidth(), t_image.getHeight(), Image.SCALE_DEFAULT)));
            imgpath = path;
            t_image.setText("");
        }else if(result  == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null,"No File Selected");
        }
    }//GEN-LAST:event_t_imageMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try{
            if (t_partial.isSelected()){
                String file ="C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\transaction_form.jrxml";
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con = DriverManager.getConnection("jdbc:Mysql://localhost:3306/db_pos","root","1234");
                //conn = (Connection) MySqlConnect.ConnectDB();
                InputStream input = new FileInputStream(new File(file));
                JasperDesign jasperDesign = JRXmlLoader.load(input);
                JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                //JasperReport jr = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream(file));
                HashMap para= new HashMap();
                para.put("ClientName", t_name.getText());
                para.put("Contact", t_con.getText());
                para.put("Item", "Sling Print");
                para.put("Size",t_size.getText() );
                para.put("Color",t_color.getText());
                para.put("Qty",t_qty.getText());
                para.put("Price",  t_price.getText());
                para.put("Method", t_partial.getText());
                para.put("Payment", t_payment.getText());
                para.put("Balance", t_bal.getText());
                para.put("Trans",s_date.getText());
                para.put("picup",((JTextField)t_pic.getDateEditor().getUiComponent()).getText());
                para.put("Total", t_total.getText());
                JasperPrint jp = JasperFillManager.fillReport(jr,para,new JREmptyDataSource());
                //                JasperPrint impressao = JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream(file), para, new JREmptyDataSource());
                //                JasperExportManager.exportReportToPdf(impressao);
                //                JasperPrintManager.printReport(impressao, true);
                JasperPrintManager.printReport(jp, true);
                //JasperViewer.viewReport(jp);
            }else if(t_full.isSelected()){
                String file ="C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\transaction_form.jrxml";
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con = DriverManager.getConnection("jdbc:Mysql://localhost:3306/db_pos","root","1234");
                //conn = (Connection) MySqlConnect.ConnectDB();
                InputStream input = new FileInputStream(new File(file));
                JasperDesign jasperDesign = JRXmlLoader.load(input);
                JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                //JasperReport jr = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream(file));
                HashMap para= new HashMap();
                para.put("ClientName", t_name.getText());
                para.put("Contact", t_con.getText());
                para.put("Item", "Sling Print");
                para.put("Size",t_size.getText() );
                para.put("Color",t_color.getText());
                para.put("Qty",t_qty.getText());
                para.put("Price",  t_price.getText());
                para.put("Total", t_total.getText());
                para.put("Method", t_full.getText());
                para.put("Payment", t_payment.getText());
                para.put("Balance", t_bal.getText());
                para.put("Trans",s_date.getText());
                para.put("picup",((JTextField)t_pic.getDateEditor().getUiComponent()).getText());
                JasperPrint jp = JasperFillManager.fillReport(jr,para,new JREmptyDataSource());
                JasperPrintManager.printReport(jp, true);
                // JasperViewer.viewReport(jp);

            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void t1_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t1_priceKeyReleased
        // TODO add your handling code here:
        if (t1_price.getText().equals("")){
            t1_total.setText("0.00");
        }else{
            Double a, b, c;
            a = Double.parseDouble(t1_qty.getText());
            b = Double.parseDouble(t1_price.getText());
            c = a * b;
            t1_total.setText(String.valueOf(c));
        }
    }//GEN-LAST:event_t1_priceKeyReleased

    private void t1_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1_addActionPerformed
        // TODO add your handling code here:
        if (t1_image.getText().equals("Select Image")){
            try{

                if (t1_partial.isSelected()){
                    String sql1 = "INSERT INTO tbl_transaction(ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice"
                    + ",Size,Color,TransactionDate,Time,PicupDate,PaymentMethod,Payment,Balance,Month,Year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pst = (PreparedStatement) conn.prepareStatement(sql1);
                    pst.setString(1,t1_name.getText());
                    pst.setString(2,t1_con.getText());
                    pst.setString(3,"Mug");
                    pst.setString(4,t1_qty.getText());
                    pst.setString(5,t1_price.getText());
                    pst.setString(6,t1_total.getText());
                    pst.setString(7,t1_size.getText());
                    pst.setString(8,t1_color.getText());
                    pst.setString(9,s_date.getText());
                    pst.setString(10,s_time.getText());
                    pst.setString(11,((JTextField)t1_pic.getDateEditor().getUiComponent()).getText());
                    pst.setString(12,"Partial Payment");
                    pst.setString(13,t1_payment.getText());
                    pst.setString(14,t1_bal.getText());
                    pst.setString(15, s_month.getText());
                    pst.setString(16, s_year.getText());
                    int add = pst.executeUpdate();
                    if(add!=0){
                        mug_clear();
                    } else{
                        JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                    }
                }else if (t1_full.isSelected()){
                    String sql1 = "INSERT INTO tbl_transaction(ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice"
                    + ",Size,Color,TransactionDate,Time,PicupDate,PaymentMethod,Payment,Balance,Month,Year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pst = (PreparedStatement) conn.prepareStatement(sql1);
                    pst.setString(1,t1_name.getText());
                    pst.setString(2,t1_con.getText());
                    pst.setString(3,"Mug");
                    pst.setString(4,t1_qty.getText());
                    pst.setString(5,t1_price.getText());
                    pst.setString(6,t1_total.getText());
                    pst.setString(7,t1_size.getText());
                    pst.setString(8,t1_color.getText());
                    pst.setString(9,s_date.getText());
                    pst.setString(10,s_time.getText());
                    pst.setString(11,((JTextField)t1_pic.getDateEditor().getUiComponent()).getText());
                    pst.setString(12,"Full Payment");
                    pst.setString(13,t1_payment.getText());
                    pst.setString(14,t1_bal.getText());
                    pst.setString(15, s_month.getText());
                    pst.setString(16, s_year.getText());
                    int add = pst.executeUpdate();
                    if(add!=0){
                        mug_clear();
                    } else{
                        JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                    }
                }

            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if (t1_image.getText().equals("")){
            if (imgpath != null){
                try{
                    if (t1_partial.isSelected()){
                        String sql1 = "INSERT INTO tbl_transaction VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        pst = (PreparedStatement) conn.prepareStatement(sql1);
                        InputStream img = new FileInputStream(new File(imgpath));
                        pst.setString(1,t1_name.getText());
                        pst.setString(2,t1_con.getText());
                        pst.setString(3,"Mug");
                        pst.setString(4,t1_qty.getText());
                        pst.setString(5,t1_price.getText());
                        pst.setString(6,t1_total.getText());
                        pst.setString(7,t1_size.getText());
                        pst.setString(8,t1_color.getText());
                        pst.setString(9,s_date.getText());
                        pst.setString(10,s_time.getText());
                        pst.setString(11,((JTextField)t1_pic.getDateEditor().getUiComponent()).getText());
                        pst.setString(12,"Partial Payment");
                        pst.setString(13,t1_payment.getText());
                        pst.setString(14,t1_bal.getText());
                        pst.setString(15, s_month.getText());
                        pst.setString(16, s_year.getText());
                        pst.setBlob(17,img);
                        int add = pst.executeUpdate();
                        if(add!=0){
                            mug_clear();
                        } else{
                            JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                        }
                    }else if (t1_full.isSelected()){
                        String sql1 = "INSERT INTO tbl_transaction VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        pst = (PreparedStatement) conn.prepareStatement(sql1);
                        InputStream img = new FileInputStream(new File(imgpath));
                        pst.setString(1,t1_name.getText());
                        pst.setString(2,t1_con.getText());
                        pst.setString(3,"Mug");
                        pst.setString(4,t1_qty.getText());
                        pst.setString(5,t1_price.getText());
                        pst.setString(6,t1_total.getText());
                        pst.setString(7,t1_size.getText());
                        pst.setString(8,t1_color.getText());
                        pst.setString(9, s_date.getText());
                        pst.setString(10,s_time.getText());
                        pst.setString(11,((JTextField)t1_pic.getDateEditor().getUiComponent()).getText());
                        pst.setString(12,"Full Payment");
                        pst.setString(13,t1_payment.getText());
                        pst.setString(14,t1_bal.getText());
                        pst.setString(15, s_month.getText());
                        pst.setString(16, s_year.getText());
                        pst.setBlob(17,img);
                        int add = pst.executeUpdate();
                        if(add!=0){
                            mug_clear();
                        } else{
                            JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                        }
                    }

                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }       catch (FileNotFoundException ex) {
                    Logger.getLogger(HOME.class.getName()).log(Level.SEVERE, null, ex);
                }
            }imgpath = null;
        }
    }//GEN-LAST:event_t1_addActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        mug_clear();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void t1_paymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t1_paymentKeyReleased
        // TODO add your handling code here:
        if (t1_payment.getText().equals("")){
            t1_bal.setText(t1_total.getText());
        }else{
            Double a, b, c;
            a = Double.parseDouble(t1_total.getText());
            b = Double.parseDouble(t1_payment.getText());
            c = a - b;
            t1_bal.setText(String.valueOf(c));
        }
    }//GEN-LAST:event_t1_paymentKeyReleased

    private void t1_fullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1_fullActionPerformed
        // TODO add your handling code here:
        if (t1_full.isSelected()){
            t1_partial.setSelected(false);
            t1_payment.setText(t1_total.getText());
            t1_bal.setText("0.00");
        }else{
            t1_partial.setSelected(true);
            t1_payment.setText("");
        }
    }//GEN-LAST:event_t1_fullActionPerformed

    private void t1_partialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1_partialActionPerformed
        // TODO add your handling code here:
        if (t1_partial.isSelected()){
            t1_full.setSelected(false);
            t1_payment.setText("");
        }else{
            t1_full.setSelected(true);
            t1_payment.setText(t1_total.getText());
            t1_bal.setText("0.00");
        }
    }//GEN-LAST:event_t1_partialActionPerformed

    private void t1_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t1_imageMouseClicked
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
            t1_image.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(t1_image.getWidth(), t1_image.getHeight(), Image.SCALE_DEFAULT)));
            imgpath = path;
            t1_image.setText("");
        }else if(result  == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null,"No File Selected");
        }
    }//GEN-LAST:event_t1_imageMouseClicked

    private void t1_balActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1_balActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1_balActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        try{
            if (t1_partial.isSelected()){
                String file ="C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\transaction_form.jrxml";
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con = DriverManager.getConnection("jdbc:Mysql://localhost:3306/db_inventory","root","1234");
                //conn = (Connection) MySqlConnect.ConnectDB();
                InputStream input = new FileInputStream(new File(file));
                JasperDesign jasperDesign = JRXmlLoader.load(input);
                JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                //JasperReport jr = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream(file));
                HashMap para= new HashMap();
                para.put("ClientName", t1_name.getText());
                para.put("Contact", t1_con.getText());
                para.put("Item", "Mug Print");
                para.put("Size",t1_size.getText() );
                para.put("Color",t1_color.getText());
                para.put("Qty",t1_qty.getText());
                para.put("Price",  t1_price.getText());
                para.put("Total", t1_total.getText());
                para.put("Method", t1_partial.getText());
                para.put("Payment", t1_payment.getText());
                para.put("Balance", t1_bal.getText());
                para.put("Trans",s_date.getText());
                para.put("picup",((JTextField)t1_pic.getDateEditor().getUiComponent()).getText());
                JasperPrint jp = JasperFillManager.fillReport(jr,para,new JREmptyDataSource());
                JasperPrintManager.printReport(jp, true);
                //JasperViewer.viewReport(jp);
            }else if(t1_full.isSelected()){
                String file ="C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\transaction_form.jrxml";
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con = DriverManager.getConnection("jdbc:Mysql://localhost:3306/db_inventory","root","1234");
                //conn = (Connection) MySqlConnect.ConnectDB();
                InputStream input = new FileInputStream(new File(file));
                JasperDesign jasperDesign = JRXmlLoader.load(input);
                JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                //JasperReport jr = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream(file));
                HashMap para= new HashMap();
                para.put("ClientName", t1_name.getText());
                para.put("Contact", t1_con.getText());
                para.put("Item", "Sling Print");
                para.put("Size",t1_size.getText() );
                para.put("Color",t1_color.getText());
                para.put("Qty",t1_qty.getText());
                para.put("Price",  t1_price.getText());
                para.put("Total", t1_total.getText());
                para.put("Method", t1_full.getText());
                para.put("Payment", t1_payment.getText());
                para.put("Balance", t1_bal.getText());
                para.put("Trans",s_date.getText());
                para.put("picup",((JTextField)t1_pic.getDateEditor().getUiComponent()).getText());
                JasperPrint jp = JasperFillManager.fillReport(jr,para,new JREmptyDataSource());
                JasperPrintManager.printReport(jp, true);
                //JasperViewer.viewReport(jp);
            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void t2_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t2_priceKeyReleased
        // TODO add your handling code here:
        if (t2_price.getText().equals("")){
            t2_total.setText("0.00");
        }else{
            Double a, b, c;
            a = Double.parseDouble(t2_qty.getText());
            b = Double.parseDouble(t2_price.getText());
            c = a * b;
            t2_total.setText(String.valueOf(c));
        }
    }//GEN-LAST:event_t2_priceKeyReleased

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        if (t2_image.getText().equals("Select Image")){
            try{

                if (t2_partial.isSelected()){
                    String sql1 = "INSERT INTO tbl_transaction(ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice"
                    + ",Size,Color,TransactionDate,Time,PicupDate,PaymentMethod,Payment,Balance,Month,Year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pst = (PreparedStatement) conn.prepareStatement(sql1);
                    pst.setString(1,t2_name.getText());
                    pst.setString(2,t2_con.getText());
                    pst.setString(3,"Tshirt");
                    pst.setString(4,t2_qty.getText());
                    pst.setString(5,t2_price.getText());
                    pst.setString(6,t2_total.getText());
                    pst.setString(7,t2_size.getText());
                    pst.setString(8,t2_color.getText());
                    pst.setString(9, s_date.getText());
                    pst.setString(10,s_time.getText());
                    pst.setString(11,((JTextField)t2_pic.getDateEditor().getUiComponent()).getText());
                    pst.setString(12,"Partial Payment");
                    pst.setString(13,t2_payment.getText());
                    pst.setString(14,t2_bal.getText());
                    pst.setString(15, s_month.getText());
                    pst.setString(16, s_year.getText());
                    int add = pst.executeUpdate();
                    if(add!=0){
                        folding_clear();
                    } else{
                        JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                    }
                }else if (t2_full.isSelected()){
                    String sql1 = "INSERT INTO tbl_transaction(ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice"
                    + ",Size,Color,TransactionDate,Time,PicupDate,PaymentMethod,Payment,Balance,Month,Year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pst = (PreparedStatement) conn.prepareStatement(sql1);
                    pst.setString(1,t2_name.getText());
                    pst.setString(2,t2_con.getText());
                    pst.setString(3,"Tshirt");
                    pst.setString(4,t2_qty.getText());
                    pst.setString(5,t2_price.getText());
                    pst.setString(6,t2_total.getText());
                    pst.setString(7,t2_size.getText());
                    pst.setString(8,t2_color.getText());
                    pst.setString(9,s_date.getText());
                    pst.setString(10,s_time.getText());
                    pst.setString(11,((JTextField)t2_pic.getDateEditor().getUiComponent()).getText());
                    pst.setString(12,"Full Payment");
                    pst.setString(13,t2_payment.getText());
                    pst.setString(14,t2_bal.getText());
                    pst.setString(15, s_month.getText());
                    pst.setString(16, s_year.getText());
                    int add = pst.executeUpdate();
                    if(add!=0){
                        folding_clear();
                    } else{
                        JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                    }
                }

            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if (t2_image.getText().equals("")){
            if (imgpath != null){
                try{
                    if (t2_partial.isSelected()){
                        String sql1 = "INSERT INTO tbl_transaction VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        pst = (PreparedStatement) conn.prepareStatement(sql1);
                        InputStream img = new FileInputStream(new File(imgpath));
                        pst.setString(1,t2_name.getText());
                        pst.setString(2,t2_con.getText());
                        pst.setString(3,"Tshirt");
                        pst.setString(4,t2_qty.getText());
                        pst.setString(5,t2_price.getText());
                        pst.setString(6,t2_total.getText());
                        pst.setString(7,t2_size.getText());
                        pst.setString(8,t2_color.getText());
                        pst.setString(9,s_date.getText());
                        pst.setString(10,s_time.getText());
                        pst.setString(11,((JTextField)t2_pic.getDateEditor().getUiComponent()).getText());
                        pst.setString(12,"Partial Payment");
                        pst.setString(13,t2_payment.getText());
                        pst.setString(14,t2_bal.getText());
                        pst.setString(15, s_month.getText());
                        pst.setString(16, s_year.getText());
                        pst.setBlob(17,img);
                        int add = pst.executeUpdate();
                        if(add!=0){
                            folding_clear();
                        } else{
                            JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                        }
                    }else if (t2_full.isSelected()){
                        String sql1 = "INSERT INTO tbl_transaction VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        pst = (PreparedStatement) conn.prepareStatement(sql1);
                        InputStream img = new FileInputStream(new File(imgpath));
                        pst.setString(1,t2_name.getText());
                        pst.setString(2,t2_con.getText());
                        pst.setString(3,"Tshirt");
                        pst.setString(4,t2_qty.getText());
                        pst.setString(5,t2_price.getText());
                        pst.setString(6,t2_total.getText());
                        pst.setString(7,t2_size.getText());
                        pst.setString(8,t2_color.getText());
                        pst.setString(9, s_date.getText());
                        pst.setString(10,s_time.getText());
                        pst.setString(11,((JTextField)t2_pic.getDateEditor().getUiComponent()).getText());
                        pst.setString(12,"Full Payment");
                        pst.setString(13,t2_payment.getText());
                        pst.setString(14,t2_bal.getText());
                        pst.setString(15, s_month.getText());
                        pst.setString(16, s_year.getText());
                        pst.setBlob(17,img);
                        int add = pst.executeUpdate();
                        if(add!=0){
                            folding_clear();
                        } else{
                            JOptionPane.showMessageDialog(null,"Failed to Store!! Please Check");
                        }
                    }

                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }       catch (FileNotFoundException ex) {
                    Logger.getLogger(HOME.class.getName()).log(Level.SEVERE, null, ex);
                }
            }imgpath = null;
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        folding_clear();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void t2_paymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t2_paymentKeyReleased
        // TODO add your handling code here:
        if (t2_payment.getText().equals("")){
            t2_bal.setText(t2_total.getText());
        }else{
            Double a, b, c;
            a = Double.parseDouble(t2_total.getText());
            b = Double.parseDouble(t2_payment.getText());
            c = a - b;
            t2_bal.setText(String.valueOf(c));
        }
    }//GEN-LAST:event_t2_paymentKeyReleased

    private void t2_fullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2_fullActionPerformed
        // TODO add your handling code here:
        if (t2_full.isSelected()){
            t2_partial.setSelected(false);
            t2_payment.setText(t2_total.getText());
            t2_bal.setText("0.00");
        }else {
            t2_partial.setSelected(true);
            t2_payment.setText("");
        }
    }//GEN-LAST:event_t2_fullActionPerformed

    private void t2_partialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2_partialActionPerformed
        // TODO add your handling code here:
        if (t2_partial.isSelected()){
            t2_full.setSelected(false);
            t2_payment.setText("");
        }else{
            t2_full.setSelected(true);
            t2_payment.setText(t2_total.getText());
            t2_bal.setText("0.00");
        }
    }//GEN-LAST:event_t2_partialActionPerformed

    private void t2_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t2_imageMouseClicked
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
            t2_image.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(t2_image.getWidth(), t2_image.getHeight(), Image.SCALE_DEFAULT)));
            imgpath = path;
            t2_image.setText("");
        }else if(result  == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null,"No File Selected");
        }
    }//GEN-LAST:event_t2_imageMouseClicked

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        try{
            if (t2_partial.isSelected()){
                String file ="C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\transaction_form.jrxml";
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con = DriverManager.getConnection("jdbc:Mysql://localhost:3306/db_inventory","root","1234");
                //conn = (Connection) MySqlConnect.ConnectDB();
                InputStream input = new FileInputStream(new File(file));
                JasperDesign jasperDesign = JRXmlLoader.load(input);
                JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                //JasperReport jr = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream(file));
                HashMap para= new HashMap();
                para.put("ClientName", t2_name.getText());
                para.put("Contact", t2_con.getText());
                para.put("Item", "Tshirt Print");
                para.put("Size",t2_size.getText() );
                para.put("Color",t2_color.getText());
                para.put("Qty",t2_qty.getText());
                para.put("Price",  t2_price.getText());
                para.put("Total", t2_total.getText());
                para.put("Method", t2_partial.getText());
                para.put("Payment", t2_payment.getText());
                para.put("Balance", t2_bal.getText());
                para.put("Trans",s_date.getText());
                para.put("picup",((JTextField)t2_pic.getDateEditor().getUiComponent()).getText());
                JasperPrint jp = JasperFillManager.fillReport(jr,para,new JREmptyDataSource());
                JasperPrintManager.printReport(jp, true);
                //JasperViewer.viewReport(jp);
            }else if(t2_full.isSelected()){
                String file ="C:\\Users\\User\\Documents\\NetBeansProjects\\PKCSSMS1\\src\\report\\transaction_form.jrxml";
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con = DriverManager.getConnection("jdbc:Mysql://localhost:3306/db_inventory","root","1234");
                //conn = (Connection) MySqlConnect.ConnectDB();
                InputStream input = new FileInputStream(new File(file));
                JasperDesign jasperDesign = JRXmlLoader.load(input);
                JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
                //JasperReport jr = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream(file));
                HashMap para= new HashMap();
                para.put("ClientName", t2_name.getText());
                para.put("Contact", t2_con.getText());
                para.put("Item", "Tshirt Print");
                para.put("Size",t2_size.getText() );
                para.put("Color",t2_color.getText());
                para.put("Qty",t2_qty.getText());
                para.put("Price",  t2_price.getText());
                para.put("Total", t2_total.getText());
                para.put("Method", t2_full.getText());
                para.put("Payment", t2_payment.getText());
                para.put("Balance", t2_bal.getText());
                para.put("Trans",s_date.getText());
                para.put("picup",((JTextField)t2_pic.getDateEditor().getUiComponent()).getText());
                JasperPrint jp = JasperFillManager.fillReport(jr,para,new JREmptyDataSource());
                JasperPrintManager.printReport(jp, true);
                //JasperViewer.viewReport(jp);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void tbl_transMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transMouseClicked
        // TODO add your handling code here:
        int z = tbl_trans.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tbl_trans.getModel();
        tp_name.setText(model.getValueAt(z, 0).toString());
        tp_item.setText(model.getValueAt(z, 2).toString());
        tp_qty.setText(model.getValueAt(z, 3).toString());
        tp_price.setText(model.getValueAt(z, 4).toString());
        tp_total.setText(model.getValueAt(z, 5).toString());
        tp_size.setText(model.getValueAt(z, 6).toString());
        tp_color.setText(model.getValueAt(z, 7).toString());
        tp_time.setText(model.getValueAt(z, 9).toString());
        tp_method.setText(model.getValueAt(z, 11).toString());
        tp_balance.setText(model.getValueAt(z, 13).toString());

        try{
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_transaction WHERE Time= '" + tp_time.getText() + "'");
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()){
                byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(tp_image.getWidth(), tp_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                tp_image.setIcon(newImage);

            }else{
                //                JOptionPane.showMessageDialog(null,"No Image Data!!");
                tp_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(tp_image.getWidth(), tp_image.getHeight(), Image.SCALE_DEFAULT)));
            }
        }catch (Exception ex) {
            //            JOptionPane.showMessageDialog(null,"No Image Data!!");
            tp_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(tp_image.getWidth(), tp_image.getHeight(), Image.SCALE_DEFAULT)));
        }
    }//GEN-LAST:event_tbl_transMouseClicked

    private void tbl_transMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_transMouseEntered

    private void tp_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tp_searchKeyReleased
        // TODO add your handling code here:
        if (tp_pay.getSelectedItem().equals("Full Payment")){
            try{
                String sql = "SELECT ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice,Size,Color,TransactionDate,"
                + "Time,PicupDate,PaymentMethod,Payment,Balance FROM tbl_transaction WHERE "
                // + "ProductID like ? or ProductName like ? or "
                //+ "Stocks like ? or price like ? or Description like ? or Date like ? or Time like ? or Month like ? or Year like ? or StaffName like ? ";
                +"ClientName like ? or ItemName like ? or Size like ? or Color like ? or PaymentMethod like ?";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + tp_search.getText()+ "%");
                pst.setString(2, "%" + tp_search.getText()+ "%");
                pst.setString(3, "%" + tp_search.getText()+ "%");
                pst.setString(4, "%" + tp_search.getText()+ "%");
                pst.setString(5, "%" + "Full Payment"+ "%");

                rs = (ResultSet) pst.executeQuery();
                tbl_trans.setModel(DbUtils.resultSetToTableModel(rs));

            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if (tp_pay.getSelectedItem().equals("Partial Payment")){

            try{
                String sql = "SELECT ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice,Size,Color,TransactionDate,"
                + "Time,PicupDate,PaymentMethod,Payment,Balance FROM tbl_transaction WHERE "
                // + "ProductID like ? or ProductName like ? or "
                //+ "Stocks like ? or price like ? or Description like ? or Date like ? or Time like ? or Month like ? or Year like ? or StaffName like ? ";
                +"ClientName like ? or ItemName like ? or Size like ? or Color like ? or PaymentMethod like ?";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + tp_search.getText()+ "%");
                pst.setString(2, "%" + tp_search.getText()+ "%");
                pst.setString(3, "%" + tp_search.getText()+ "%");
                pst.setString(4, "%" + tp_search.getText()+ "%");
                pst.setString(5, "%" + "Partial Payment"+ "%");

                rs = (ResultSet) pst.executeQuery();
                tbl_trans.setModel(DbUtils.resultSetToTableModel(rs));
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }

        }else if (tp_pay.getSelectedItem().equals("All")){
            try{
                String sql = "SELECT ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice,Size,Color,TransactionDate,"
                + "Time,PicupDate,PaymentMethod,Payment,Balance FROM tbl_transaction WHERE "
                // + "ProductID like ? or ProductName like ? or "
                //+ "Stocks like ? or price like ? or Description like ? or Date like ? or Time like ? or Month like ? or Year like ? or StaffName like ? ";
                +"ClientName like ? or ItemName like ? or Size like ? or Color like ?";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + tp_search.getText()+ "%");
                pst.setString(2, "%" + tp_search.getText()+ "%");
                pst.setString(3, "%" + tp_search.getText()+ "%");
                pst.setString(4, "%" + tp_search.getText()+ "%");

                rs = (ResultSet) pst.executeQuery();
                tbl_trans.setModel(DbUtils.resultSetToTableModel(rs));
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_tp_searchKeyReleased

    private void tp_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tp_payActionPerformed
        // TODO add your handling code here:
        if (tp_pay.getSelectedItem().equals("Full Payment")){
            try{
                String sql = "SELECT ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice,Size,Color,TransactionDate,"
                + "Time,PicupDate,PaymentMethod,Payment,Balance FROM tbl_transaction WHERE "
                // + "ProductID like ? or ProductName like ? or "
                //+ "Stocks like ? or price like ? or Description like ? or Date like ? or Time like ? or Month like ? or Year like ? or StaffName like ? ";
                +"PaymentMethod like ?";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + "Full Payment"+ "%");
                rs = (ResultSet) pst.executeQuery();
                tbl_trans.setModel(DbUtils.resultSetToTableModel(rs));

            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if (tp_pay.getSelectedItem().equals("Partial Payment")){

            try{
                String sql = "SELECT ClientName,ContactNumber,ItemName,Quantity,UnitPrice,TotalPrice,Size,Color,TransactionDate,"
                + "Time,PicupDate,PaymentMethod,Payment,Balance FROM tbl_transaction WHERE "
                // + "ProductID like ? or ProductName like ? or "
                //+ "Stocks like ? or price like ? or Description like ? or Date like ? or Time like ? or Month like ? or Year like ? or StaffName like ? ";
                +"PaymentMethod like ?";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + "Partial Payment"+ "%");
                rs = (ResultSet) pst.executeQuery();
                tbl_trans.setModel(DbUtils.resultSetToTableModel(rs));

            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }

        }else {
            refresh_transaction();
        }
    }//GEN-LAST:event_tp_payActionPerformed

    private void tp_cashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tp_cashKeyReleased
        // TODO add your handling code here:
        if (tp_cash.getText().equals("")){
            tp_tbal.setText("0.00");
            jButton4.setEnabled(false);
        }else{
            Double a, b, c;
            a = Double.parseDouble(tp_balance.getText());
            b = Double.parseDouble(tp_cash.getText());
            c = a - b;
            tp_tbal.setText(String.valueOf(c));
            if (tp_balance.getText().equals("0.00")){
                jButton4.setEnabled(false);
            }else  if (tp_balance.getText().equals("0.0")){
                jButton4.setEnabled(false);
            }else{
                jButton4.setEnabled(true);
            }
        }
    }//GEN-LAST:event_tp_cashKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        payment_clear();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
            pst=(com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE tbl_transaction SET Balance=? WHERE Time='"+tp_time.getText()+"'   ");

            pst.setString(1,tp_tbal.getText());
            int user_update = pst.executeUpdate();
            if (user_update!=0){
                payment_clear();
                refresh_transaction();
                jButton4.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null, "Error Update!!!");
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        refresh_transaction();
        jButton4.setEnabled(false);
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jLabel105MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel105MouseClicked
        // TODO add your handling code here:
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
            jLabel10.setText("");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jLabel105MouseClicked

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
            AID.setText("ID"+id.getText());
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
                            JOptionPane.showMessageDialog(null, "Account Deactivated");
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
        }else{ JOptionPane.showMessageDialog(null, "Incorrect password Match");}
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
        DateFormat dff = new SimpleDateFormat("MM/dd/yyyy");
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

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        DateFormat dff = new SimpleDateFormat("MM/dd/yyyy");
        jLabel77.setText(dff.format( jDateChooser1.getDate()));
        try {
            String sql = "SELECT * FROM tbl_basic_date WHERE "
            + "  Date like ?";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + jLabel77.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            tbl_basic_date.setModel(DbUtils.resultSetToTableModel(rs));
            //            empCount();
            //            daily.setSelected(false);
            //            report_sales.setText(Double.toString(total_report_Sales()));
            //            report_item.setText(Integer.toString(total_sales_summary()));
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void tbl_basic_dateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_basic_dateMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        JFrame frame = new JFrame();
        String[] options = new String[2];
        options[0] = new String("Review Sales");
        options[1] = new String("Cancel");
        int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Review","Basic Sales Report", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
        if(p==0){
            int z = tbl_basic_date.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tbl_basic_date.getModel();
            rb_date.setText(model.getValueAt(z, 3).toString());
            basic_sales.setText(model.getValueAt(z, 2).toString());
            try {
                //String sql = "SELECT ProductName,ProductCost,Quantity,Total,Date,Time FROM tbl_basic_report WHERE "
                String sql = "SELECT * FROM tbl_basic_report WHERE Date like ? ";
                // + "ProductID like ? or ProductName like ? or "
                //+ "Stocks like ? or price like ? or Description like ? or Date like ? or Time like ? or Month like ? or Year like ? or StaffName like ? ";
                //+"Date like ?";
                pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "%" + rb_date.getText()+ "%");
                rs = (ResultSet) pst.executeQuery();
                rb_date.setText("");
                tbl_basic_report.setModel(DbUtils.resultSetToTableModel(rs));
                //            tbl_sales.setModel(DbUtils.resultSetToTableModel(rs));
                //            empCount();
                //             report_sales.setText(Double.toString(total_report_Sales()));
                //             report_item.setText(Integer.toString(total_sales_summary()));
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }

        }else if (p==1){
            frame.setVisible(false);
            rb_date.setText("");
            basic_sales.setText("0.00");

            refresh_basic_report();

        }
    }//GEN-LAST:event_tbl_basic_dateMouseClicked

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        // TODO add your handling code here:
        daily_basic();
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void tbl_itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_itemMouseClicked
        // TODO add your handling code here:
        // asd
        if (jLabel94.getText().equals("Pending request shown Below")){
            
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
                    int n = pst.executeUpdate();
                      
                    if (n!=0){
                       
                JFrame frame1 = new JFrame();
            String[] options1 = new String[2];
            options1[0] = new String("Continue");
            options1[1] = new String("Cancel");
              int p1 = JOptionPane.showOptionDialog(frame1.getContentPane(),"Confirmation Stock Request","Continue process?", 1,JOptionPane.INFORMATION_MESSAGE,null,options1,null);
              if (p1==0){
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_stock_request  WHERE ProductID = '" + p_id.getText() + "'");
                        pst.executeUpdate();

                        count_request();
                        refresh_stock_request();
                        JOptionPane.showMessageDialog(null, p_id.getText()+" stored successfully!!");
                        p_id.setText("[Click To Auto Generate ID]");
              }else if (p1==1){
              frame1.dispose();
              }
                    }else{
                    JOptionPane.showMessageDialog(null, "Error Process!!");
                    }

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
                    int n = pst.executeUpdate();
                    if (n!=0){
//                         JFrame frame2 = new JFrame();
//            String[] options2 = new String[2];
//            options2[0] = new String("Continue");
//            options2[1] = new String("Cancel");
//              int p2 = JOptionPane.showOptionDialog(frame2.getContentPane(),"Confirmation Stock Request","Continue process?", 0,JOptionPane.INFORMATION_MESSAGE,null,options2,null);
//              if (p2==0){    
                        
                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_stock_request");
                        pst.executeUpdate();

                        refresh_item();
                        empCount();
                        count_request();
                        jLabel94.setText("");
                        JOptionPane.showMessageDialog(null,"New Items stored successfully!!");
                        p_id.setText("[Click To Auto Generate ID]");
//              }else if (p2==1){
//                    frame2.dispose();
//                    }
                    }
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
                        int del = pst.executeUpdate();
                        if (del > 0) {
                            
//                             JFrame frame3 = new JFrame();
//            String[] options3 = new String[2];
//            options3[0] = new String("Continue");
//            options3[1] = new String("Cancel");
//              int p3 = JOptionPane.showOptionDialog(frame3.getContentPane(),"Confirmation Stock Request","Are you sure you want to remove stock in request?", 0,JOptionPane.INFORMATION_MESSAGE,null,options3,null);
//              if (p3==0){
               refresh_stock_request();
                            //empCount();
                            JOptionPane.showMessageDialog(null, "Stock request data Removed succesffuly!");
                            //empCount();
                            //sdf
                            p_id.setText("[Click To Auto Generate ID]");
//              }  else if (p3==1){
//              frame3.dispose();
//              }          
             

                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                
                

               

            }else if (p==3){
            
                 frame.dispose();
                
                
            }

        }else if (jLabel94.getText().equals("")){
            JFrame frame = new JFrame();
            String[] options = new String[2];
            options[0] = new String("Update");
            options[1] = new String("Delete");
            int p = JOptionPane.showOptionDialog(frame.getContentPane(),"Select Option","Item", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(p==0){
                int z = tbl_item.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) tbl_item.getModel();
                p_id.setText(model.getValueAt(z, 0).toString());
                p_name.setText(model.getValueAt(z, 1).toString());
                p_des.setText(model.getValueAt(z, 2).toString());
                p_cat.setText(model.getValueAt(z, 3).toString());
                p_quan.setText(model.getValueAt(z, 4).toString());
                p_org.setText(model.getValueAt(z, 5).toString());
                p_val.setText(model.getValueAt(z, 6).toString());
                p_sale.setText(model.getValueAt(z, 7).toString());

                try{
                    pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement("SELECT * FROM tbl_items WHERE ProductID = '" + p_id.getText() + "'");
                    rs = (ResultSet) pst.executeQuery();
                    if (rs.next()){
                        byte[] img = rs.getBytes("Image");
                        ImageIcon image1 =  new ImageIcon(img);
                        Image im = image1.getImage();
                        Image myImage = im.getScaledInstance(p_image.getWidth(), p_image.getHeight(), Image.SCALE_SMOOTH );
                        ImageIcon newImage = new ImageIcon(myImage);
                        p_image.setIcon(newImage);
                        p_image.setText("");

                    }else{
                        JOptionPane.showMessageDialog(null,"No Image Data!!");
                        p_image.setText("Select Image");
                    }
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"No Image Data!!");
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

            }else if (p==1){
                int z = tbl_item.getSelectedRow();
                TableModel model = tbl_item.getModel();
                p_id.setText(model.getValueAt(z, 0).toString());
                if (acctype.getText().equals("User")){
                    JOptionPane.showMessageDialog(null, "Invalid User Access!!!!");
                }else{
                    try {

                        pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM tbl_items  WHERE ProductID = '" + p_id.getText() + "'");
                        int del = pst.executeUpdate();
                        if (del > 0) {
                            refresh_item();
                            empCount();
                            JOptionPane.showMessageDialog(null, "Data Removed");
                            //empCount();
                            p_id.setText("[Click To Auto Generate ID]");

                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }}
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

            try {
                String sql = "SELECT ProductID,ProductName,ProductDescription,ProductCategory,Quantity,OriginalPrice,ValuesSRP,"
                + "Sale FROM tbl_items WHERE "
                + "ProductID like ? or ProductName like ? or ProductDescription like ? or ProductCategory like ? or Quantity like ? "
                + "or OriginalPrice like ? or ValuesSRP like ? or Sale like ? ";

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
                rs = (ResultSet) pst.executeQuery();
                tbl_item.setModel(DbUtils.resultSetToTableModel(rs));
                empCount();
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }

        }else{

            try {
                String sql = "SELECT ProductID,ProductName,ProductDescription,ProductCategory,Quantity,OriginalPrice,ValuesSRP,"
                + "Sale FROM tbl_stock_request WHERE "
                + "ProductID like ? or ProductName like ? or ProductDescription like ? or ProductCategory like ? or Quantity like ? "
                + "or OriginalPrice like ? or ValuesSRP like ? or Sale like ? ";

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
                rs = (ResultSet) pst.executeQuery();
                tbl_item.setModel(DbUtils.resultSetToTableModel(rs));
                empCount();
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }

        }

    }//GEN-LAST:event_i_search1KeyReleased

    private void st_prdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_st_prdnewActionPerformed
        // TODO add your handling code here:
        login.setVisible(false);
        Sales.setVisible(false);
        Stocks.setVisible(true);
        ItemList.setVisible(false);
        Transactions.setVisible(false);
        Logs.setVisible(false);
        Accounts.setVisible(false);
        Report.setVisible(false);
        item_clear();
        p_id.setText("[Click To Auto Generate ID]");
        p_add.setText("Add");
    }//GEN-LAST:event_st_prdnewActionPerformed

    private void requestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestMouseClicked
        // TODO add your handling code here:
        if (request.getBackground().equals(new java.awt.Color(0,204,102))){
            refresh_stock_request();
            jLabel94.setText("Pending request shown Below");
        }else if (request.getBackground().equals(new java.awt.Color(255,255,255))){

        }
    }//GEN-LAST:event_requestMouseClicked

    private void jLabel99MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel99MouseClicked
        // TODO add your handling code here:
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
    }//GEN-LAST:event_jLabel99MouseClicked
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
panel.setBackground(new java.awt.Color(236,242,245));
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
        return total;
     }
public double total_basic_Sales() {
        double total=0;
                for (int e=0; e<tbl_basic_report.getRowCount();e++)
                {
                    double amount= Double.parseDouble((String) tbl_basic_report.getValueAt( e, 3).toString());
                    total+=amount;
                }
                basic_sales.setText(String.valueOf(total));
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
 public void daily_basic(){
 try {
            //String sql = "SELECT ProductName,ProductCost,Quantity,Total,Date,Time FROM tbl_basic_report WHERE "
            String sql = "SELECT * FROM tbl_basic_report WHERE Date like ? ";
                   // + "ProductID like ? or ProductName like ? or "
                    //+ "Stocks like ? or price like ? or Description like ? or Date like ? or Time like ? or Month like ? or Year like ? or StaffName like ? ";
                    //+"Date like ?";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + s_date.getText()+ "%");
            rs = (ResultSet) pst.executeQuery();
            rb_date.setText("");
            tbl_basic_report.setModel(DbUtils.resultSetToTableModel(rs));
             basic_sales.setText(Double.toString(total_basic_Sales()));
             
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
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
            java.util.logging.Logger.getLogger(HOME3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HOME3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HOME3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HOME3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HOME3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AID;
    private javax.swing.JPanel Accounts;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel HomeMenu;
    private javax.swing.JPanel ItemList;
    private javax.swing.JPanel Logs;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Report;
    private javax.swing.JPanel Sales;
    private javax.swing.JPanel Stocks;
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
    private javax.swing.JLabel basic_sales;
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
    private javax.swing.JCheckBox daily;
    private javax.swing.JLabel hdgfd;
    private javax.swing.JTextField i_search1;
    private javax.swing.JLabel id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
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
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
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
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
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
    private javax.swing.JTextField p_cat;
    private javax.swing.JButton p_clear;
    private javax.swing.JTextField p_des;
    private javax.swing.JTextField p_id;
    private javax.swing.JLabel p_image;
    private javax.swing.JTextField p_name;
    private javax.swing.JTextField p_org;
    private javax.swing.JTextField p_quan;
    private javax.swing.JTextField p_sale;
    private javax.swing.JTextField p_val;
    private javax.swing.JLabel password;
    private javax.swing.JLabel password1;
    private javax.swing.JPanel pnl_accounts;
    private javax.swing.JPanel pnl_logout;
    private javax.swing.JPanel pnl_logs;
    private javax.swing.JPanel pnl_report;
    private javax.swing.JPanel pnl_sales;
    private javax.swing.JPanel pnl_stock;
    private javax.swing.JPanel pnl_transactions;
    private javax.swing.JLabel prd_count;
    private javax.swing.JPasswordField pword;
    private javax.swing.JComboBox q1;
    private javax.swing.JComboBox q2;
    private javax.swing.JComboBox q3;
    private com.toedter.calendar.JDateChooser r_date;
    private javax.swing.JLabel rb_date;
    private javax.swing.JLabel rdel;
    private javax.swing.JLabel report_item;
    private javax.swing.JLabel report_sales;
    private javax.swing.JPanel request;
    private javax.swing.JPasswordField rpword;
    private javax.swing.JButton s_add;
    private javax.swing.JButton s_add2;
    private javax.swing.JTextField s_barcode;
    private javax.swing.JTextField s_bqty;
    private javax.swing.JTextField s_btotal;
    private javax.swing.JTextField s_cash;
    private javax.swing.JComboBox s_cat;
    private javax.swing.JTextField s_catprice;
    private javax.swing.JCheckBox s_cbbasic;
    private javax.swing.JCheckBox s_cbprd;
    private javax.swing.JTextField s_change;
    private javax.swing.JLabel s_date;
    private javax.swing.JTextField s_des;
    private javax.swing.JLabel s_eq;
    private javax.swing.JLabel s_month;
    private javax.swing.JPanel s_pnlbasic;
    private javax.swing.JPanel s_pnlprd;
    private javax.swing.JTextField s_prd;
    private javax.swing.JTextField s_price;
    private javax.swing.JTextField s_qty;
    private javax.swing.JTextField s_stck;
    private javax.swing.JLabel s_time;
    private javax.swing.JTextField s_total;
    private javax.swing.JLabel s_year;
    private javax.swing.JButton st_prdnew;
    private javax.swing.JButton t1_add;
    private javax.swing.JTextField t1_bal;
    private javax.swing.JTextField t1_color;
    private javax.swing.JTextField t1_con;
    private javax.swing.JCheckBox t1_full;
    private javax.swing.JLabel t1_image;
    private javax.swing.JTextField t1_name;
    private javax.swing.JCheckBox t1_partial;
    private javax.swing.JTextField t1_payment;
    private com.toedter.calendar.JDateChooser t1_pic;
    private javax.swing.JTextField t1_price;
    private javax.swing.JTextField t1_qty;
    private javax.swing.JTextField t1_size;
    private javax.swing.JTextField t1_total;
    private javax.swing.JTextField t2_bal;
    private javax.swing.JTextField t2_color;
    private javax.swing.JTextField t2_con;
    private javax.swing.JCheckBox t2_full;
    private javax.swing.JLabel t2_image;
    private javax.swing.JTextField t2_name;
    private javax.swing.JCheckBox t2_partial;
    private javax.swing.JTextField t2_payment;
    private com.toedter.calendar.JDateChooser t2_pic;
    private javax.swing.JTextField t2_price;
    private javax.swing.JTextField t2_qty;
    private javax.swing.JTextField t2_size;
    private javax.swing.JTextField t2_total;
    private javax.swing.JButton t_add;
    private javax.swing.JTextField t_bal;
    private javax.swing.JTextField t_color;
    private javax.swing.JTextField t_con;
    private javax.swing.JCheckBox t_full;
    private javax.swing.JLabel t_image;
    private javax.swing.JTextField t_name;
    private javax.swing.JCheckBox t_partial;
    private javax.swing.JTextField t_payment;
    private com.toedter.calendar.JDateChooser t_pic;
    private javax.swing.JTextField t_price;
    private javax.swing.JTextField t_qty;
    private javax.swing.JTextField t_size;
    private javax.swing.JTextField t_total;
    private javax.swing.JTable tbl_admin;
    private javax.swing.JTable tbl_basic_date;
    private javax.swing.JTable tbl_basic_report;
    private javax.swing.JTable tbl_cashier;
    private javax.swing.JTable tbl_item;
    private javax.swing.JTable tbl_loginhis;
    private javax.swing.JTable tbl_sales;
    private javax.swing.JTable tbl_trans;
    private javax.swing.JTable tbl_user;
    private javax.swing.JTextField totprice;
    private javax.swing.JTextField totqty;
    private javax.swing.JTextField tp_balance;
    private javax.swing.JTextField tp_cash;
    private javax.swing.JLabel tp_color;
    private javax.swing.JLabel tp_image;
    private javax.swing.JLabel tp_item;
    private javax.swing.JLabel tp_method;
    private javax.swing.JLabel tp_name;
    private javax.swing.JComboBox tp_pay;
    private javax.swing.JLabel tp_price;
    private javax.swing.JLabel tp_qty;
    private javax.swing.JTextField tp_search;
    private javax.swing.JLabel tp_size;
    private javax.swing.JTextField tp_tbal;
    private javax.swing.JLabel tp_time;
    private javax.swing.JLabel tp_total;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    private javax.swing.JLabel type;
    private javax.swing.JComboBox type1;
    private javax.swing.JButton uact;
    private javax.swing.JLabel user;
    private javax.swing.JButton uuclear;
    private javax.swing.JLabel v_id;
    private javax.swing.JCheckBox yearly;
    // End of variables declaration//GEN-END:variables
private void seticon() {
      setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("PKALOGO.png")));
    }
}
