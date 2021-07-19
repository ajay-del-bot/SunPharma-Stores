package com.pharma;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class Details {

	private JFrame frame;
	private JPanel panelDist;
	private JPanel panelDrug;
	private JPanel panelPat;
	private JPanel panelDoc;
	private JLayeredPane layeredPane;
	private JButton btnDrug_1;
	private JLabel lblDrugName;
	private JLabel lblCostPrice;
	private JLabel lblStock;
	private JLabel lblDiscount;
	private JLabel lblMrp;
	private JLabel lblLocation;
	private JLabel lblType;
	private JButton btnSubmit;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTable table;
	private JTextField DrugName;
	private JTextField CostPrice;
	private JTextField Stock;
	private JTextField Discount;
	private JTextField Mrp;
	private JTextField Type;
	private JTextField Location;
	private JTextField Expiry;
	private JLabel lblExpiry;
	private JLabel lblNewLabel;
	private JLabel lblContact;
	private JLabel lblSpecialization;
	private JLabel lblContact_1;
	private JTextField DocName;
	private JTextField Contact;
	private JTextField Special;
	private JTextField HName;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	private JButton btnAdd;
	private JButton btnUpdate_1;
	private JButton btnDelete_1;
	private JLabel lblContact_2;
	private JLabel lblContact_3;
	private JTextField ContDist;
	private JTextField EmailDist;
	private JButton btnAdd_1;
	private JButton btnAdd_2;
	private JButton btnAdd_3;
	private JTable table_2;
	private JScrollPane scrollPane_2;
	private JTextField PatID;
	private JTextField DocID;
	private JTextField DrugID;
	private JTextField Quant;
	private JTable table_3;
	private JTextField TotalCost;
	private JTextField PatName;
	private JTextField PatAddr;
	private JTextField PatCont;
	private JTextField PatEmail;
	private JTable table_4;
	private JButton btnPurchase;
	private JLabel lblDistributorId;
	private JLabel lblDrugId_1;
	private JLabel lblQuantity_1;
	private JTextField DistID;
	private JTextField DrugID2;
	private JTextField Quant2;
	private JTable table_5;
	private JScrollPane scrollPane_5;
	private JButton btnSubmit_2;
	private JButton btnSubmit_3;
	private JButton btnSubmit_4;
	private JButton btnSubmit_5;
	private JButton btnSubmit_6;
	private JButton btnSubmit_1_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Details window = new Details();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
    public void reset(){
        //centre.removeAll();
        DrugName.setText("");
        Stock.setText("");
        Expiry.setText("");
        //Company_Id.setText("");
        CostPrice.setText("");
        Mrp.setText("");
        Discount.setText("");
        Location.setText("");
        Type.setText("");
        //output.setText("Output");
    }
    
	protected void deleteQuery() {
		// TODO Auto-generated method stub
		Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
		
		if(table.getSelectedRowCount()==1)
		{
			String type = tblModel.getValueAt(table.getSelectedRow(), 6).toString();
			String id = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
		        statement = connect.createStatement();
		        preparedStatement = connect.prepareStatement("delete from drug1 where drug_id='" + id +"'");
		        preparedStatement.executeUpdate();
		        resultSet = statement.executeQuery("SELECT * FROM Drug1 WHERE Type='"+type+"'");
	            if(!resultSet.isBeforeFirst()){
	                preparedStatement = connect.prepareStatement("delete from Drug2 where type=?");
	                preparedStatement.setString(1,type);
	                preparedStatement.executeUpdate();
	            }
	            
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			tblModel.removeRow(table.getSelectedRow());
				        
		}
		else {
			if(table.getRowCount()==0)
				JOptionPane.showMessageDialog(null, this, "Table is Empty", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, this, "Please select a row", JOptionPane.ERROR_MESSAGE);
		}
		
	}
    
    protected void updateQuery() {
		// TODO Auto-generated method stub
    	Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
		if(table.getSelectedRowCount()==1)
		{
			String name = DrugName.getText();
			String costPrice = CostPrice.getText();
			String stock = Stock.getText();
			String price = Mrp.getText();
			String discount = Discount.getText();
			String type = Type.getText();
			String location = Location.getText();
			String expiry = Expiry.getText();
			
			String tblID = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
			tblModel.setValueAt(name, table.getSelectedRow(), 1);
			tblModel.setValueAt(costPrice, table.getSelectedRow(), 2);
			tblModel.setValueAt(stock, table.getSelectedRow(), 3);
			tblModel.setValueAt(price, table.getSelectedRow(), 4);
			tblModel.setValueAt(discount, table.getSelectedRow(), 5);
			tblModel.setValueAt(type, table.getSelectedRow(), 6);
			tblModel.setValueAt(location, table.getSelectedRow(), 7);
			tblModel.setValueAt(expiry, table.getSelectedRow(), 8);		
			
			 try {
				Class.forName("com.mysql.jdbc.Driver");
		        connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
		        statement = connect.createStatement();
		        preparedStatement = connect.prepareStatement("Update Drug1 set name= ?, cost_price=?, stock=?, Mrp=?, discount=?, type=?, expiry=? where drug_ID=?");
		        preparedStatement.setString(1, name);
		        preparedStatement.setInt(2, Integer.parseInt(costPrice));
		        preparedStatement.setInt(3, Integer.parseInt(stock));
		        preparedStatement.setInt(4, Integer.parseInt(price));
		        preparedStatement.setInt(5, Integer.parseInt(discount));
		        preparedStatement.setString(6, type);
		        preparedStatement.setString(7, expiry);
		        preparedStatement.setString(8, tblID);
		        preparedStatement.executeUpdate();
		        
		        //resultSet = statement.executeQuery("SELECT * FROM Drug2 WHERE Type='"+type+"'");
	            
	                preparedStatement = connect.prepareStatement("update drug2 set location=? where type=?");
	                preparedStatement.setString(1, location);
	                preparedStatement.setString(2,type);
	                preparedStatement.executeUpdate();
	            
		        
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
	}
	
	public void runQuery(){
    	Integer sale_id;
    	Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Drug1 ORDER BY Drug_ID DESC LIMIT 1");
            if(!resultSet.isBeforeFirst()){
                sale_id = 1;
            }else{
                resultSet.next();
                sale_id=Integer.parseInt(resultSet.getString("Drug_ID").substring(4))+1;
            }
            
            if(DrugName.getText().equals("") || Mrp.getText().equals("") || Stock.getText().equals("") || CostPrice.getText().equals("") || Type.getText().equals("") || Location.getText().equals("") || Expiry.getText().equals("") || Discount.getText().equals("")) {
            	JOptionPane.showMessageDialog(null, this, "Enter all details",JOptionPane.ERROR_MESSAGE);
            }
            else {
            
            preparedStatement = connect.prepareStatement("INSERT INTO Drug1 Values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(4, "Drug"+sale_id.toString());
            preparedStatement.setString(1, Type.getText());
            preparedStatement.setInt(3, Integer.parseInt(Stock.getText()));
            preparedStatement.setString(5, DrugName.getText());
            preparedStatement.setInt(2, Integer.parseInt(CostPrice.getText()));
            preparedStatement.setInt(6, Integer.parseInt(Discount.getText()));
            preparedStatement.setInt(7, Integer.parseInt(Mrp.getText()));
           // preparedStatement.setString(8, Company_Id.getText());
            preparedStatement.setString(8, Expiry.getText());
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("SELECT * FROM Drug2 WHERE Type='"+Type.getText()+"'");
            if(!resultSet.isBeforeFirst()){
                preparedStatement = connect.prepareStatement("INSERT INTO Drug2 Values(?,?)");
                preparedStatement.setString(1,Type.getText());
                preparedStatement.setString(2,Location.getText());
                preparedStatement.executeUpdate();
            }
           
            	resultSet = statement.executeQuery("SELECT * FROM Drug1 natural join Drug2");
            	DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
            	//table.setModel(new DefaultTableModel(null, new String[] {"DrugID", "Name", "Quantity", "Price", "Location"}));
            	while(tblModel.getRowCount()>0)
            	{
            		for(int i=0;i<tblModel.getRowCount();i++)
            			tblModel.removeRow(i);
            	}
            	while(resultSet.next()){
     	        	tblModel.addRow(new Object[]{resultSet.getString("Drug_ID"),resultSet.getString("Name"), resultSet.getString("Cost_Price"), resultSet.getString("Stock"),resultSet.getString("MRP"), resultSet.getString("Discount"), resultSet.getString("Type"), resultSet.getString("Location"), resultSet.getString("Expiry")});
     	        }
            	reset();

            
            }
            
            //output.setText("Success");
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
            

            
        }catch(Exception e){
        	//output.setText("Failure");
            e.printStackTrace();
        }
        

        //ddrug both tables to be updated

    }
	
	public void resetDoc(){
        
        DocName.setText("");
        Contact.setText("");
        Special.setText("");
        HName.setText("");
       
    }
	
	public void resetDist() {
		ContDist.setText("");
		EmailDist.setText("");
	}
	
	protected void addDoc() {
		// TODO Auto-generated method stub
		Integer doc_id;
    	Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Doctor ORDER BY Doctor_ID DESC LIMIT 1");
            if(!resultSet.isBeforeFirst()){
                doc_id = 1;
            }else{
                resultSet.next();
                doc_id = Integer.parseInt(resultSet.getString("Doctor_ID").substring(4))+1;
            }
            preparedStatement = connect.prepareStatement("INSERT INTO Doctor Values(?,?,?,?,?)");
            preparedStatement.setString(1, "Doct"+doc_id.toString());
            preparedStatement.setLong(2, Long.parseLong(Contact.getText()));
            preparedStatement.setString(3, DocName.getText());
            preparedStatement.setString(4, Special.getText());
            preparedStatement.setString(5, HName.getText());
            preparedStatement.executeUpdate();
            
            resultSet = statement.executeQuery("SELECT * FROM Doctor");
        	DefaultTableModel tblModel = (DefaultTableModel) table_1.getModel();
        	//table.setModel(new DefaultTableModel(null, new String[] {"DrugID", "Name", "Quantity", "Price", "Location"}));
        	while(tblModel.getRowCount()>0)
        	{
        		for(int i=0;i<tblModel.getRowCount();i++)
        			tblModel.removeRow(i);
        	}
        	while(resultSet.next()){
 	        	tblModel.addRow(new Object[]{resultSet.getString("Doctor_ID"),resultSet.getString("Name"), resultSet.getString("Contact"), resultSet.getString("Specialisation"),resultSet.getString("Hospital")});
 	        }
        	resetDoc();
        	
        	
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
            
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
		
	}
	
	protected void updateDoc() {
		Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		DefaultTableModel tblModel = (DefaultTableModel)table_1.getModel();
		if(table_1.getSelectedRowCount()==1)
		{
			String name = DocName.getText();
			String contact = Contact.getText();
			String special = Special.getText();
			String hname = HName.getText();

			
			String tblID = tblModel.getValueAt(table_1.getSelectedRow(), 0).toString();
			tblModel.setValueAt(name, table_1.getSelectedRow(), 1);
			tblModel.setValueAt(contact, table_1.getSelectedRow(), 2);
			tblModel.setValueAt(special, table_1.getSelectedRow(), 3);
			tblModel.setValueAt(hname, table_1.getSelectedRow(), 4);
	
			
			 try {
				Class.forName("com.mysql.jdbc.Driver");
		        connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
		        statement = connect.createStatement();
		        preparedStatement = connect.prepareStatement("Update Doctor set name= ?, contact=?, specialisation=?, Hospital=? where Doctor_id=?");
		        preparedStatement.setString(1, name);
		        preparedStatement.setLong(2, Long.parseLong(contact));
		        preparedStatement.setString(3, special);
		        preparedStatement.setString(4, hname);

		        preparedStatement.setString(5, tblID);
		        preparedStatement.executeUpdate();
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		}
		
		
	}
	
	protected void delDoc() {
		
		Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		DefaultTableModel tblModel = (DefaultTableModel)table_1.getModel();
		
		if(table_1.getSelectedRowCount()==1)
		{
			
			String id = tblModel.getValueAt(table_1.getSelectedRow(), 0).toString();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
		        statement = connect.createStatement();
		        preparedStatement = connect.prepareStatement("delete from doctor where doctor_id='" + id +"'");
		        preparedStatement.executeUpdate();
		       

	            
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			tblModel.removeRow(table_1.getSelectedRow());
				        
		}
		else {
			if(table_1.getRowCount()==0)
				JOptionPane.showMessageDialog(null, this, "Table is Empty", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, this, "Please select a row", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void addDist() {
		Integer id;
    	Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Distributor ORDER BY Distributor_ID DESC LIMIT 1");
            if(!resultSet.isBeforeFirst()){
                id = 1;
            }else{
                resultSet.next();
                id = Integer.parseInt(resultSet.getString("Distributor_id").substring(4))+1;
            }
            preparedStatement = connect.prepareStatement("INSERT INTO Distributor Values(?,?,?)");
            preparedStatement.setString(1, "Dist"+id.toString());
            preparedStatement.setLong(2, Long.parseLong(ContDist.getText()));
            preparedStatement.setString(3, EmailDist.getText());
            preparedStatement.executeUpdate();
            
            resultSet = statement.executeQuery("SELECT * FROM Distributor");
        	DefaultTableModel tblModel = (DefaultTableModel) table_2.getModel();
        	//table.setModel(new DefaultTableModel(null, new String[] {"DrugID", "Name", "Quantity", "Price", "Location"}));
        	while(tblModel.getRowCount()>0)
        	{
        		for(int i=0;i<tblModel.getRowCount();i++)
        			tblModel.removeRow(i);
        	}
        	while(resultSet.next()){
 	        	tblModel.addRow(new Object[]{resultSet.getString("Distributor_ID"),resultSet.getString("Contact"), resultSet.getString("Email")});
 	        }
        	resetDist();
        	
        	
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
            
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	
	protected void updateDist() {
		Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		DefaultTableModel tblModel = (DefaultTableModel)table_2.getModel();
		if(table_2.getSelectedRowCount()==1)
		{
			String contact = ContDist.getText();
			String email = EmailDist.getText();
	

			
			String tblID = tblModel.getValueAt(table_2.getSelectedRow(), 0).toString();
			tblModel.setValueAt(contact, table_2.getSelectedRow(), 1);
			tblModel.setValueAt(email, table_2.getSelectedRow(), 2);

	
			
			 try {
				Class.forName("com.mysql.jdbc.Driver");
		        connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
		        statement = connect.createStatement();
		        preparedStatement = connect.prepareStatement("Update Distributor set contact= ?, email=? where Distributor_id=?");
		        preparedStatement.setLong(1, Long.parseLong(contact));
		        preparedStatement.setString(2, email);


		        preparedStatement.setString(3, tblID);
		        preparedStatement.executeUpdate();
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		}
	}
	
	protected void delDist() {
		Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		DefaultTableModel tblModel = (DefaultTableModel)table_2.getModel();
		
		if(table_2.getSelectedRowCount()==1)
		{
			
			String id = tblModel.getValueAt(table_2.getSelectedRow(), 0).toString();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
		        statement = connect.createStatement();
		        preparedStatement = connect.prepareStatement("delete from distributor where distributor_id='" + id +"'");
		        preparedStatement.executeUpdate();
		       

	            
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			tblModel.removeRow(table_2.getSelectedRow());
				        
		}
		else {
			if(table_2.getRowCount()==0)
				JOptionPane.showMessageDialog(null, this, "Table is Empty", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, this, "Please select a row", JOptionPane.ERROR_MESSAGE);
		}
	}
	
    public void resetPat(){
       
        PatName.setText("");
        PatCont.setText("");
        PatAddr.setText("");
        PatEmail.setText("");
        
    }
	

	
	protected void addPat() {
		Integer id;
    	Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Patient ORDER BY Patient_id DESC LIMIT 1");
            if(!resultSet.isBeforeFirst()){
                id = 1;
            }else{
                resultSet.next();
                id = Integer.parseInt(resultSet.getString("Patient_id").substring(4))+1;
            }
            preparedStatement = connect.prepareStatement("INSERT INTO Patient Values(?,?,?,?,?)");
            preparedStatement.setString(1, "Cust"+id.toString());
            preparedStatement.setString(2, PatName.getText());
            preparedStatement.setString(3, PatAddr.getText());
            preparedStatement.setString(4, PatCont.getText());
            preparedStatement.setString(5, PatEmail.getText());
            preparedStatement.executeUpdate();
            
            resultSet = statement.executeQuery("SELECT * FROM Patient");
        	DefaultTableModel tblModel = (DefaultTableModel) table_4.getModel();
        	//table.setModel(new DefaultTableModel(null, new String[] {"DrugID", "Name", "Quantity", "Price", "Location"}));
        	while(tblModel.getRowCount()>0)
        	{
        		for(int i=0;i<tblModel.getRowCount();i++)
        			tblModel.removeRow(i);
        	}
        	while(resultSet.next()){
 	        	tblModel.addRow(new Object[]{resultSet.getString("Patient_id"), resultSet.getString("Name"), resultSet.getString("Address"), resultSet.getString("Contact"), resultSet.getString("Email")});
 	        }
        	resetPat();
        	
        	
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
            
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
	protected void updatePat() {
		
		Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		DefaultTableModel tblModel = (DefaultTableModel)table_4.getModel();
		if(table_4.getSelectedRowCount()==1)
		{
			String name = PatName.getText();
			String contact = PatCont.getText();
			String email = PatEmail.getText();
			String addr = PatAddr.getText();
	

			
			String tblID = tblModel.getValueAt(table_4.getSelectedRow(), 0).toString();
			tblModel.setValueAt(name, table_4.getSelectedRow(), 1);
			tblModel.setValueAt(addr, table_4.getSelectedRow(), 2);
			tblModel.setValueAt(contact, table_4.getSelectedRow(), 3);
			tblModel.setValueAt(email, table_4.getSelectedRow(), 4);

	
			
			 try {
				Class.forName("com.mysql.jdbc.Driver");
		        connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
		        statement = connect.createStatement();
		        preparedStatement = connect.prepareStatement("Update Patient set name =?, address=?, contact= ?, email=? where Patient_id=?");
		        preparedStatement.setString(1, name);
		        preparedStatement.setString(2, addr);
		        preparedStatement.setString(3, contact);
		        preparedStatement.setString(4, email);
		        preparedStatement.setString(5, tblID);
		        
		        preparedStatement.executeUpdate();
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		}
	}
	
	protected void delPat() {
		
		Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		DefaultTableModel tblModel = (DefaultTableModel)table_4.getModel();
		
		if(table_4.getSelectedRowCount()==1)
		{
			
			String id = tblModel.getValueAt(table_4.getSelectedRow(), 0).toString();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
		        statement = connect.createStatement();
		        preparedStatement = connect.prepareStatement("delete from patient where patient_id='" + id +"'");
		        preparedStatement.executeUpdate();
		       

	            
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			tblModel.removeRow(table_4.getSelectedRow());
				        
		}
		else {
			if(table_4.getRowCount()==0)
				JOptionPane.showMessageDialog(null, this, "Table is Empty", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, this, "Please select a row", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void getDrugID() {
		
		 	JFrame meds=new JFrame();
	        JLabel heading = new JLabel("Drug Table");
	        heading.setFont(new Font("Verdana",1,30));
	        meds.getContentPane().add(heading,BorderLayout.NORTH);
	        DefaultTableModel model1 = new DefaultTableModel();
	        model1.addColumn("DrugId");
	        model1.addColumn("Name");
	        model1.addColumn("Stock");
	        model1.addColumn("Price");
	        model1.addColumn("Location");
	        model1.addColumn("Expiry");
	        JTable tablemeds=new JTable(model1);
	        Connection connect = null;
	        Statement statement = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        
			tablemeds.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel tblModel = (DefaultTableModel)tablemeds.getModel();
					String tblID = tblModel.getValueAt(tablemeds.getSelectedRow(), 0).toString();
					DrugID.setText(tblID);
					DrugID2.setText(tblID);
				}
			});
			
		
	        
	        //DefaultTableModel mod=(DefaultTableModel)table.getModel();
	        try{	
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
				statement = connect.createStatement();
				resultSet = statement.executeQuery("SELECT * FROM Drug1 NATURAL JOIN Drug2");
		        while(resultSet.next()){
		        	model1.addRow(new Object[]{resultSet.getString("Drug_ID"),resultSet.getString("Name"),resultSet.getString("Stock"),resultSet.getString("MRP"),resultSet.getString("Location"),resultSet.getString("Expiry")});
		        }
				if (resultSet != null) {
	                resultSet.close();
				}
		        if (statement != null) {
		                statement.close();
		        }
		        if (connect != null) {
		                connect.close();
		        }
	        }catch(Exception e){
	        	System.out.println(e);
	        }
	        meds.getContentPane().add( new JScrollPane( tablemeds ), BorderLayout.CENTER );
	        meds.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        meds.setSize(new Dimension(600,600));//600
	        meds.setVisible(true);
	}
	
	protected void getDocID() {
		// TODO Auto-generated method stub
		
		JFrame meds=new JFrame();
        JLabel heading = new JLabel("Doctor Table");
        heading.setFont(new Font("Verdana",1,30));
        meds.getContentPane().add(heading,BorderLayout.NORTH);
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("DoctorID");
        model1.addColumn("Name");
        model1.addColumn("Contact");
        model1.addColumn("Specialization");
        model1.addColumn("Hospital Name");
        
        JTable tabledoc = new JTable(model1);
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        tabledoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)tabledoc.getModel();
				String tblID = tblModel.getValueAt(tabledoc.getSelectedRow(), 0).toString();
				DocID.setText(tblID);
			}
		});
		
	
        
        //DefaultTableModel mod=(DefaultTableModel)table.getModel();
        try{	
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Doctor");
	        while(resultSet.next()){
	        	model1.addRow(new Object[]{resultSet.getString("Doctor_ID"),resultSet.getString("Name"),resultSet.getString("Contact"),resultSet.getString("Specialisation"),resultSet.getString("Hospital")});
	        }
			if (resultSet != null) {
                resultSet.close();
			}
	        if (statement != null) {
	                statement.close();
	        }
	        if (connect != null) {
	                connect.close();
	        }
        }catch(Exception e){
        	System.out.println(e);
        }
        meds.getContentPane().add( new JScrollPane( tabledoc ), BorderLayout.CENTER );
        meds.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        meds.setSize(new Dimension(600,600));//600
        meds.setVisible(true);
		
	}
	
	
	protected void getDistID() {
		// TODO Auto-generated method stub
		
		JFrame meds=new JFrame();
        JLabel heading = new JLabel("Distributor Table");
        heading.setFont(new Font("Verdana",1,30));
        meds.getContentPane().add(heading,BorderLayout.NORTH);
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("DistributorID");
        model1.addColumn("Contact");
        model1.addColumn("Email");
       
        
        JTable tabledist = new JTable(model1);
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        tabledist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)tabledist.getModel();
				String tblID = tblModel.getValueAt(tabledist.getSelectedRow(), 0).toString();
				DistID.setText(tblID);
			}
		});
		
	
        
        //DefaultTableModel mod=(DefaultTableModel)table.getModel();
        try{	
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Distributor");
	        while(resultSet.next()){
	        	model1.addRow(new Object[]{resultSet.getString("Distributor_id"),resultSet.getString("Contact"),resultSet.getString("Email")});
	        }
			if (resultSet != null) {
                resultSet.close();
			}
	        if (statement != null) {
	                statement.close();
	        }
	        if (connect != null) {
	                connect.close();
	        }
        }catch(Exception e){
        	System.out.println(e);
        }
        meds.getContentPane().add( new JScrollPane( tabledist ), BorderLayout.CENTER );
        meds.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        meds.setSize(new Dimension(600,600));//600
        meds.setVisible(true);
		
	}
	
	
	protected void getPatID() {
		// TODO Auto-generated method stub
		JFrame meds=new JFrame();
        JLabel heading = new JLabel("Patient Table");
        heading.setFont(new Font("Verdana",1,30));
        meds.getContentPane().add(heading,BorderLayout.NORTH);
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("PatientID");
        model1.addColumn("Name");
        model1.addColumn("Address");
        model1.addColumn("Contact");
        model1.addColumn("Email");
        
        JTable tablepat = new JTable(model1);
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        tablepat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)tablepat.getModel();
				String tblID = tblModel.getValueAt(tablepat.getSelectedRow(), 0).toString();
				PatID.setText(tblID);
			}
		});
		
	
        
        
        try{	
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Patient");
	        while(resultSet.next()){
	        	model1.addRow(new Object[]{resultSet.getString("Patient_ID"),resultSet.getString("Name"),resultSet.getString("address"),resultSet.getString("contact"),resultSet.getString("email")});
	        }
			if (resultSet != null) {
                resultSet.close();
			}
	        if (statement != null) {
	                statement.close();
	        }
	        if (connect != null) {
	                connect.close();
	        }
        }catch(Exception e){
        	e.printStackTrace();
        }
        meds.getContentPane().add( new JScrollPane( tablepat ), BorderLayout.CENTER );
        meds.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        meds.setSize(new Dimension(600,600));//600
        meds.setVisible(true);
		
	}
	
	protected void resetPur() {
		
	        DistID.setText("");
	        DrugID2.setText("");
	        Quant2.setText("");
	        DefaultTableModel tblModel = (DefaultTableModel) table_5.getModel();
        	//table.setModel(new DefaultTableModel(null, new String[] {"DrugID", "Name", "Quantity", "Price", "Location"}));
        	while(tblModel.getRowCount()>0)
        	{
        		for(int i=0;i<tblModel.getRowCount();i++)
        			tblModel.removeRow(i);
        	}
	        

	}
	
	protected void resetSale() {
		    DocID.setText("");
	        DrugID.setText("");
	        PatID.setText("");
	        Quant.setText("");
	        TotalCost.setText("");
	        DefaultTableModel tblModel = (DefaultTableModel) table_3.getModel();
     	//table.setModel(new DefaultTableModel(null, new String[] {"DrugID", "Name", "Quantity", "Price", "Location"}));
     	while(tblModel.getRowCount()>0)
     	{
     		for(int i=0;i<tblModel.getRowCount();i++)
     			tblModel.removeRow(i);
     	}
	}
	
	protected void addtoCart()
	{
		String id = DrugID.getText();
    	if(!id.equals("")){
    		ArrayList<String> numdata = new ArrayList<String>();
            //perform query and store result in these arrays
            Connection connect = null;
            Statement statement = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try{	
    			Class.forName("com.mysql.jdbc.Driver");
    			connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
    			statement = connect.createStatement();
    			DefaultTableModel model = (DefaultTableModel) table_3.getModel();
    			resultSet = statement.executeQuery("SELECT * FROM Drug1 WHERE Stock>="+ Quant.getText() +" AND Drug_ID='"+id+"'");
    			if(!resultSet.isBeforeFirst()){
    				//add warning no such data
    			}
    			else{
	    	        while(resultSet.next()){
	    	        	double cost=Double.parseDouble(resultSet.getString("MRP"))*(1-Double.parseDouble(resultSet.getString("Discount"))/100)*Double.parseDouble(Quant.getText());
	    	        	model.addRow(new Object[]{resultSet.getString("Drug_ID"),resultSet.getString("Name"),Quant.getText(),cost});
	    	        	if(!TotalCost.getText().equals("")){
	    	        		cost=cost+Double.parseDouble(TotalCost.getText());
	    	        	}
	    	        	TotalCost.setText(Double.toString(cost));
	    	        }
    	        }
    			if (resultSet != null) {
                    resultSet.close();
    			}
    	        if (statement != null) {
    	                statement.close();
    	        }
    	        if (connect != null) {
    	                connect.close();
    	        }
            }catch(Exception e){
            	System.out.println(e);
            }

    	}
	}
	
	protected void addtoCartDist() {
		String id = DrugID2.getText();
    	if(!id.equals("")){
    		
            Connection connect = null;
            Statement statement = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try{	
    			Class.forName("com.mysql.jdbc.Driver");
    			connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
    			statement = connect.createStatement();
    			DefaultTableModel model = (DefaultTableModel) table_5.getModel();
    			resultSet = statement.executeQuery("SELECT * FROM Drug1 WHERE Drug_ID='"+ id + "'");
    			if(!resultSet.isBeforeFirst()){
    				//add warning no such data
    			}
    			else{
	    	        while(resultSet.next()){
	    	        	model.addRow(new Object[]{resultSet.getString("Drug_id"), resultSet.getString("Name"), Quant2.getText()});
	    	        }
    	        }
    			if (resultSet != null) {
                    resultSet.close();
    			}
    	        if (statement != null) {
    	                statement.close();
    	        }
    	        if (connect != null) {
    	                connect.close();
    	        }
            }catch(Exception e){
            	e.printStackTrace();
            }
     
    	}
	}
	
	protected void addPurchase() {
		Integer sale_id = 1;
    	Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DefaultTableModel model = (DefaultTableModel)table_5.getModel();
        try{	
        	Class.forName("com.mysql.jdbc.Driver");
 			connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
 			
 			statement = connect.createStatement();
 			resultSet = statement.executeQuery("SELECT * FROM Purchase ORDER BY Purchase_ID DESC LIMIT 1");
 			if(!resultSet.isBeforeFirst()){
                sale_id = 1;
            }else{
                resultSet.next();
                sale_id=Integer.parseInt(resultSet.getString("Purchase_ID").substring(3))+1;
            }
 	        
            resultSet = statement.executeQuery("SELECT * FROM Distributor WHERE Distributor_ID='"+ DistID.getText()+"'");
            if(!resultSet.isBeforeFirst()){
				//popup and add data
			}
            else{
	            preparedStatement = connect.prepareStatement("INSERT INTO Purchase Values(curdate()"+",?)");
	 	        preparedStatement.setString(1, "Pur"+sale_id.toString());
	            preparedStatement.executeUpdate();
	 			System.out.println("Updated");
	            System.out.println(model.getRowCount());
	            for (int i = 0; i < model.getRowCount(); i++) {
					String drugid = (String)model.getValueAt(i,0);
					Integer Quantity=Integer.parseInt((String)model.getValueAt(i,2));
		 	        preparedStatement = connect.prepareStatement("INSERT INTO Supplies Values("+Quantity.toString()+",?,?,?)");
		 	        preparedStatement.setString(1, "Pur"+sale_id.toString());
		            preparedStatement.setString(2,DistID.getText());
		            preparedStatement.setString(3,drugid);
		            preparedStatement.executeUpdate();
					preparedStatement = connect.prepareStatement("UPDATE Drug1 SET Stock = Stock + "+Quantity.toString()+" WHERE Drug_ID=?");
					preparedStatement.setString(1,drugid);
					preparedStatement.executeUpdate();
				}
	            JOptionPane.showMessageDialog(null, this, "Added Successfully", JOptionPane.INFORMATION_MESSAGE); 
            }
 			if (resultSet != null) {
                 resultSet.close();
 			}
 	        if (statement != null) {
 	                statement.close();
 	        }
 	        if (connect != null) {
 	                connect.close();
 	        }
 	       
         }catch(Exception e){
         	e.printStackTrace();
         }
	}
	
	protected void addSale()
	{
		Integer sale_id = 1;
    	Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DefaultTableModel model = (DefaultTableModel)table_3.getModel();
        try{	
        	Class.forName("com.mysql.jdbc.Driver");
 			connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/ajay_31151", "root", "123456");
 			statement = connect.createStatement();
 			resultSet = statement.executeQuery("SELECT * FROM Sells ORDER BY Sale_ID DESC LIMIT 1");
 			if(!resultSet.isBeforeFirst()){
                sale_id = 1;
            }else{
                resultSet.next();
                sale_id=Integer.parseInt(resultSet.getString("Sale_ID").substring(4))+1;
            }
 			resultSet = statement.executeQuery("SELECT * FROM Doctor WHERE Doctor_ID='" + DocID.getText()+"'");
 			System.out.println("Updated");
 			
            if(!resultSet.isBeforeFirst()){
				Exception e = new Exception();
				throw e;
			}
            resultSet = statement.executeQuery("SELECT * FROM Patient WHERE Patient_ID='" + PatID.getText()+"'");
            if(!resultSet.isBeforeFirst()){
            	Exception e = new Exception();
				throw e;
			}

            
            System.out.println(model.getRowCount());
            for (int i = 0; i < model.getRowCount(); i++) {
				String drugid = (String)model.getValueAt(i,0);
				Integer Quantity=Integer.parseInt((String)model.getValueAt(i,2));
	 	        preparedStatement = connect.prepareStatement("INSERT INTO Sells Values(?,?,?,"+ Quantity.toString() + ", curdate())");
	 	        preparedStatement.setString(1, "Sale"+sale_id.toString());
	            preparedStatement.setString(2, PatID.getText());
	            preparedStatement.setString(3, drugid);
	            preparedStatement.executeUpdate();
	            
				preparedStatement = connect.prepareStatement("INSERT INTO Prescribes Values(?,?,?)");
				preparedStatement.setString(1, PatID.getText());
	            preparedStatement.setString(2, DocID.getText());
	            preparedStatement.setString(3, drugid);
	            preparedStatement.executeUpdate();
	            
				preparedStatement = connect.prepareStatement("UPDATE Drug1 SET Stock = Stock - "+ Quantity.toString() +" WHERE Drug_ID=?");
				preparedStatement.setString(1, drugid);
				preparedStatement.executeUpdate();
				
			}
            JOptionPane.showMessageDialog(null, this, "Added Successfully", JOptionPane.INFORMATION_MESSAGE); 
            System.out.println("Donee");
 			if (resultSet != null) {
                 resultSet.close();
 			}
 	        if (statement != null) {
 	                statement.close();
 	        }
 	        if (connect != null) {
 	                connect.close();
 	        }
         }catch(Exception e){
         	e.printStackTrace();
         }
	}

	
	public Details() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 995, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(12, 58, 953, 406);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
	
		panelDoc = new JPanel();
		panelDoc.setBackground(new Color(255, 255, 255));
		layeredPane.add(panelDoc, "name_2956158719700");
		panelDoc.setLayout(null);
		
		lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(33, 33, 72, 22);
		panelDoc.add(lblNewLabel);
		
		lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContact.setBounds(33, 88, 72, 22);
		panelDoc.add(lblContact);
		
		lblSpecialization = new JLabel("Specialization");
		lblSpecialization.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecialization.setBounds(33, 146, 94, 22);
		panelDoc.add(lblSpecialization);
		
		lblContact_1 = new JLabel("Hospital Name");
		lblContact_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContact_1.setBounds(33, 202, 94, 22);
		panelDoc.add(lblContact_1);
		
		DocName = new JTextField();
		DocName.setBounds(149, 34, 116, 22);
		panelDoc.add(DocName);
		DocName.setColumns(10);
		
		Contact = new JTextField();
		Contact.setColumns(10);
		Contact.setBounds(149, 89, 116, 22);
		panelDoc.add(Contact);
		
		Special = new JTextField();
		Special.setColumns(10);
		Special.setBounds(149, 147, 116, 22);
		panelDoc.add(Special);
		
		HName = new JTextField();
		HName.setColumns(10);
		HName.setBounds(149, 203, 116, 22);
		panelDoc.add(HName);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(384, 13, 557, 366);
		panelDoc.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setBackground(new Color(255, 255, 255));
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)table_1.getModel();
				String tblID = tblModel.getValueAt(table_1.getSelectedRow(), 0).toString();
				String tblName = tblModel.getValueAt(table_1.getSelectedRow(), 1).toString();
				String tblContact = tblModel.getValueAt(table_1.getSelectedRow(), 2).toString();
				String tblSpecial = tblModel.getValueAt(table_1.getSelectedRow(), 3).toString();
				String tblHname = tblModel.getValueAt(table_1.getSelectedRow(), 4).toString();

				DocName.setText(tblName);
				Contact.setText(tblContact);
				Special.setText(tblSpecial);
				HName.setText(tblHname);
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DoctorID", "Name", "Contact", "Specialization", "Hospital Name"
			}
		));
		table_1.getColumnModel().getColumn(3).setPreferredWidth(107);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(105);
		scrollPane_1.setViewportView(table_1);
		
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.addDoc();
			}
		});
		btnAdd.setBounds(8, 290, 97, 25);
		panelDoc.add(btnAdd);
		
		btnUpdate_1 = new JButton("UPDATE");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.updateDoc();
			}
		});
		btnUpdate_1.setBounds(138, 290, 97, 25);
		panelDoc.add(btnUpdate_1);
		
		btnDelete_1 = new JButton("DELETE");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.delDoc();
			}
		});
		btnDelete_1.setBounds(269, 290, 97, 25);
		panelDoc.add(btnDelete_1);
		
		lblNewLabel_1 = new JLabel("New label");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/sunn4.png"));
		lblNewLabel_1.setIcon(img);
		lblNewLabel_1.setBounds(87, 52, 175, 174);
		panelDoc.add(lblNewLabel_1);
		

		
		panelPat = new JPanel();
		panelPat.setBackground(new Color(255, 255, 255));
		layeredPane.add(panelPat, "name_2960908325800");
		panelPat.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(36, 45, 68, 16);
		panelPat.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(36, 98, 68, 16);
		panelPat.add(lblAddress);
		
		JLabel lblContact_4 = new JLabel("Contact");
		lblContact_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContact_4.setBounds(36, 157, 68, 16);
		panelPat.add(lblContact_4);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(36, 221, 68, 16);
		panelPat.add(lblEmail);
		
		PatName = new JTextField();
		PatName.setBounds(124, 43, 116, 22);
		panelPat.add(PatName);
		PatName.setColumns(10);
		
		PatAddr = new JTextField();
		PatAddr.setColumns(10);
		PatAddr.setBounds(124, 96, 116, 22);
		panelPat.add(PatAddr);
		
		PatCont = new JTextField();
		PatCont.setColumns(10);
		PatCont.setBounds(124, 155, 116, 22);
		panelPat.add(PatCont);
		
		PatEmail = new JTextField();
		PatEmail.setColumns(10);
		PatEmail.setBounds(124, 219, 116, 22);
		panelPat.add(PatEmail);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(372, 29, 569, 364);
		panelPat.add(scrollPane_4);
		
		table_4 = new JTable();
		table_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)table_4.getModel();
				String tblID = tblModel.getValueAt(table_4.getSelectedRow(), 0).toString();
				String tblName = tblModel.getValueAt(table_4.getSelectedRow(), 1).toString();
				String tblAddress = tblModel.getValueAt(table_4.getSelectedRow(), 2).toString();
				String tblContact = tblModel.getValueAt(table_4.getSelectedRow(), 3).toString();
				String tblEmail = tblModel.getValueAt(table_4.getSelectedRow(), 4).toString();

				PatName.setText(tblName);
				PatCont.setText(tblContact);
				PatAddr.setText(tblAddress);
				PatEmail.setText(tblEmail);
				
			}
		});
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PatientID", "Name", "Address", "Contact", "Email"
			}
		));
		scrollPane_4.setViewportView(table_4);
		
		JButton btnAdd_4 = new JButton("ADD");
		btnAdd_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.addPat();
			}
		});
		btnAdd_4.setBounds(12, 320, 97, 25);
		panelPat.add(btnAdd_4);
		
		JButton btnAdd_4_1 = new JButton("UPDATE");
		btnAdd_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.updatePat();
			}
		});
		btnAdd_4_1.setBounds(134, 320, 97, 25);
		panelPat.add(btnAdd_4_1);
		
		JButton btnAdd_4_2 = new JButton("DELETE");
		btnAdd_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.delPat();
			}
		});
		btnAdd_4_2.setBounds(263, 320, 97, 25);
		panelPat.add(btnAdd_4_2);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(img);
		lblNewLabel_2.setBounds(88, 74, 175, 174);
		panelPat.add(lblNewLabel_2);
		
		panelDrug = new JPanel();
		panelDrug.setBackground(new Color(255, 255, 255));
		layeredPane.add(panelDrug, "name_2964249747400");
		panelDrug.setLayout(null);
		
		lblDrugName = new JLabel("Drug Name");
		lblDrugName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrugName.setBounds(12, 13, 77, 28);
		panelDrug.add(lblDrugName);
		
		lblCostPrice = new JLabel("Cost Price");
		lblCostPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostPrice.setBounds(12, 54, 77, 28);
		panelDrug.add(lblCostPrice);
		
		lblStock = new JLabel("Stock");
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStock.setBounds(12, 95, 77, 28);
		panelDrug.add(lblStock);
		
		lblDiscount = new JLabel("Discount");
		lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiscount.setBounds(12, 136, 77, 28);
		panelDrug.add(lblDiscount);
		
		lblMrp = new JLabel("MRP");
		lblMrp.setHorizontalAlignment(SwingConstants.CENTER);
		lblMrp.setBounds(12, 177, 77, 28);
		panelDrug.add(lblMrp);
		
		lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocation.setBounds(12, 261, 77, 28);
		panelDrug.add(lblLocation);
		
		lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setBounds(12, 220, 77, 28);
		panelDrug.add(lblType);
		
		btnSubmit = new JButton("ADD");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.runQuery();
			}
		});
		btnSubmit.setBounds(12, 339, 97, 25);
		panelDrug.add(btnSubmit);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.updateQuery();
			}
		});
		btnUpdate.setBounds(131, 339, 97, 25);
		panelDrug.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.deleteQuery();
			}
		});
		btnDelete.setBounds(251, 339, 97, 25);
		panelDrug.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				String tblID = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
				String tblName = tblModel.getValueAt(table.getSelectedRow(), 1).toString();
				String tblCostPrice = tblModel.getValueAt(table.getSelectedRow(), 2).toString();
				String tblStock = tblModel.getValueAt(table.getSelectedRow(), 3).toString();
				String tblPrice = tblModel.getValueAt(table.getSelectedRow(), 4).toString();
				String tblDiscount = tblModel.getValueAt(table.getSelectedRow(), 5).toString();
				String tblExpiry = tblModel.getValueAt(table.getSelectedRow(), 8).toString();
				String tblType = tblModel.getValueAt(table.getSelectedRow(), 6).toString();
				String tblLocation = tblModel.getValueAt(table.getSelectedRow(), 7).toString();
				DrugName.setText(tblName);
				Stock.setText(tblStock);
				Mrp.setText(tblPrice);
				CostPrice.setText(tblCostPrice);
				Discount.setText(tblDiscount);
				Expiry.setText(tblExpiry);
				Type.setText(tblType);
				Location.setText(tblLocation);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DrugID", "Name", "CostPrice", "Quantity", "Price", "Discount", "Type", "Location", "Expiry"
			}
		));
		scrollPane.setViewportView(table);
		
		

		scrollPane.setBounds(360, 20, 581, 373);
		panelDrug.add(scrollPane);
		
		
		
		DrugName = new JTextField();
		DrugName.setBounds(122, 16, 146, 22);
		panelDrug.add(DrugName);
		DrugName.setColumns(10);
		
		CostPrice = new JTextField();
		CostPrice.setColumns(10);
		CostPrice.setBounds(122, 57, 146, 22);
		panelDrug.add(CostPrice);
		
		Stock = new JTextField();
		Stock.setColumns(10);
		Stock.setBounds(122, 98, 146, 22);
		panelDrug.add(Stock);
		
		Discount = new JTextField();
		Discount.setColumns(10);
		Discount.setBounds(122, 139, 146, 22);
		panelDrug.add(Discount);
		
		Mrp = new JTextField();
		Mrp.setColumns(10);
		Mrp.setBounds(122, 180, 146, 22);
		panelDrug.add(Mrp);
		
		Type = new JTextField();
		Type.setColumns(10);
		Type.setBounds(122, 223, 146, 22);
		panelDrug.add(Type);
		
		Location = new JTextField();
		Location.setColumns(10);
		Location.setBounds(122, 264, 146, 22);
		panelDrug.add(Location);
		
		Expiry = new JTextField();
		Expiry.setColumns(10);
		Expiry.setBounds(122, 304, 146, 22);
		panelDrug.add(Expiry);
		
		lblExpiry = new JLabel("Expiry");
		lblExpiry.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpiry.setBounds(12, 298, 77, 28);
		panelDrug.add(lblExpiry);
		
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(img);
		lblNewLabel_3.setBounds(86, 78, 175, 174);
		panelDrug.add(lblNewLabel_3);
		
		panelDist = new JPanel();
		panelDist.setBackground(new Color(255, 255, 255));
		layeredPane.add(panelDist, "name_2968074196300");
		panelDist.setLayout(null);
		
		lblContact_2 = new JLabel("Contact");
		lblContact_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContact_2.setBounds(25, 52, 115, 29);
		panelDist.add(lblContact_2);
		
		lblContact_3 = new JLabel("Email");
		lblContact_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContact_3.setBounds(25, 112, 115, 29);
		panelDist.add(lblContact_3);
		
		ContDist = new JTextField();
		ContDist.setBounds(114, 56, 148, 22);
		panelDist.add(ContDist);
		ContDist.setColumns(10);
		
		EmailDist = new JTextField();
		EmailDist.setColumns(10);
		EmailDist.setBounds(114, 116, 148, 22);
		panelDist.add(EmailDist);
		
		btnAdd_1 = new JButton("ADD");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.addDist();
			}
		});
		btnAdd_1.setBounds(25, 251, 97, 25);
		panelDist.add(btnAdd_1);
		
		btnAdd_2 = new JButton("UPDATE");
		btnAdd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.updateDist();
			}
		});
		btnAdd_2.setBounds(154, 251, 97, 25);
		panelDist.add(btnAdd_2);
		
		btnAdd_3 = new JButton("DELETE");
		btnAdd_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.delDist();
			}
		});
		btnAdd_3.setBounds(286, 251, 97, 25);
		panelDist.add(btnAdd_3);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(417, 33, 524, 360);
		panelDist.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)table_2.getModel();
				String tblID = tblModel.getValueAt(table_2.getSelectedRow(), 0).toString();
				String tblEmail = tblModel.getValueAt(table_2.getSelectedRow(), 2).toString();
				String tblContact = tblModel.getValueAt(table_2.getSelectedRow(), 1).toString();


				ContDist.setText(tblContact);
				EmailDist.setText(tblEmail);
			}
		});
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DistID", "Contact", "Email"
			}
		));
		scrollPane_2.setViewportView(table_2);
		
		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(img);
		lblNewLabel_4.setBounds(95, 75, 175, 174);
		panelDist.add(lblNewLabel_4);
		
		JPanel panelPurchase = new JPanel();
		panelPurchase.setBackground(new Color(255, 255, 255));
		layeredPane.add(panelPurchase, "name_42519209005300");
		panelPurchase.setLayout(null);
		
		JLabel lblPatientId = new JLabel("Patient ID");
		lblPatientId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatientId.setBounds(45, 38, 71, 16);
		panelPurchase.add(lblPatientId);
		
		JLabel lblDoctorId = new JLabel("Doctor ID");
		lblDoctorId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDoctorId.setBounds(45, 91, 71, 16);
		panelPurchase.add(lblDoctorId);
		
		JLabel lblDrugId = new JLabel("Drug ID");
		lblDrugId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDrugId.setBounds(45, 142, 71, 16);
		panelPurchase.add(lblDrugId);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(45, 194, 71, 16);
		panelPurchase.add(lblQuantity);
		
		PatID = new JTextField();
		PatID.setBounds(128, 36, 116, 22);
		panelPurchase.add(PatID);
		PatID.setColumns(10);
		
		DocID = new JTextField();
		DocID.setColumns(10);
		DocID.setBounds(128, 89, 116, 22);
		panelPurchase.add(DocID);
		
		DrugID = new JTextField();
		DrugID.setColumns(10);
		DrugID.setBounds(128, 140, 116, 22);
		panelPurchase.add(DrugID);
		
		Quant = new JTextField();
		Quant.setColumns(10);
		Quant.setBounds(128, 192, 116, 22);
		panelPurchase.add(Quant);
		
		JButton btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.addSale();
			}
		});
		btnSubmit_1.setBounds(32, 298, 97, 25);
		panelPurchase.add(btnSubmit_1);
		
		JButton btnSubmit_1_1 = new JButton("Add to Cart");
		btnSubmit_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.addtoCart();
			}
		});
		btnSubmit_1_1.setBounds(282, 298, 97, 25);
		panelPurchase.add(btnSubmit_1_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(428, 30, 473, 306);
		panelPurchase.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DrugID", "Name", "Quantity", "Price"
			}
		));
		scrollPane_3.setViewportView(table_3);
		
		JLabel lblTotalBill = new JLabel("Total Bill:");
		lblTotalBill.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalBill.setBounds(417, 354, 122, 22);
		panelPurchase.add(lblTotalBill);
		
		TotalCost = new JTextField();
		TotalCost.setBackground(Color.LIGHT_GRAY);
		TotalCost.setBounds(528, 355, 116, 22);
		panelPurchase.add(TotalCost);
		TotalCost.setColumns(10);
		
		JButton btnGetPatid = new JButton("Get PatID");
		btnGetPatid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.getPatID();
			}
		});
		btnGetPatid.setBounds(256, 35, 97, 25);
		panelPurchase.add(btnGetPatid);
		
		JButton btnGetDocid = new JButton("Get DocID");
		btnGetDocid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.getDocID();
			}
		});
		btnGetDocid.setBounds(256, 88, 97, 25);
		panelPurchase.add(btnGetDocid);
		
		JButton btnGetDrugid = new JButton("Get DrugID");
		btnGetDrugid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.getDrugID();
			}
		});
		btnGetDrugid.setBounds(256, 139, 97, 25);
		panelPurchase.add(btnGetDrugid);
		
		btnSubmit_1_2 = new JButton("Reset");
		btnSubmit_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.resetSale();
			}
		});
		btnSubmit_1_2.setBounds(159, 298, 97, 25);
		panelPurchase.add(btnSubmit_1_2);
		
		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(img);
		lblNewLabel_5.setBounds(114, 71, 175, 174);
		panelPurchase.add(lblNewLabel_5);
		
		JPanel panelBuy = new JPanel();
		panelBuy.setBackground(new Color(255, 255, 255));
		layeredPane.add(panelBuy, "name_34302250715200");
		panelBuy.setLayout(null);
		
		lblDistributorId = new JLabel("Distributor ID");
		lblDistributorId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDistributorId.setBounds(31, 47, 97, 25);
		panelBuy.add(lblDistributorId);
		
		lblDrugId_1 = new JLabel("Drug ID");
		lblDrugId_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDrugId_1.setBounds(31, 110, 97, 25);
		panelBuy.add(lblDrugId_1);
		
		lblQuantity_1 = new JLabel("Quantity");
		lblQuantity_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantity_1.setBounds(31, 185, 97, 25);
		panelBuy.add(lblQuantity_1);
		
		DistID = new JTextField();
		DistID.setBounds(140, 49, 116, 22);
		panelBuy.add(DistID);
		DistID.setColumns(10);
		
		DrugID2 = new JTextField();
		DrugID2.setColumns(10);
		DrugID2.setBounds(140, 112, 116, 22);
		panelBuy.add(DrugID2);
		
		Quant2 = new JTextField();
		Quant2.setColumns(10);
		Quant2.setBounds(140, 187, 116, 22);
		panelBuy.add(Quant2);
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(412, 31, 529, 351);
		panelBuy.add(scrollPane_5);
		
		table_5 = new JTable();
		table_5.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Drug ID", "Drug Name", "Quantity"
			}
		));
		table_5.getColumnModel().getColumn(0).setPreferredWidth(79);
		table_5.getColumnModel().getColumn(1).setPreferredWidth(79);
		scrollPane_5.setViewportView(table_5);
		
		btnSubmit_2 = new JButton("Submit");
		btnSubmit_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.addPurchase();
			}
		});
		btnSubmit_2.setBounds(31, 278, 97, 25);
		panelBuy.add(btnSubmit_2);
		
		btnSubmit_3 = new JButton("Add to Cart");
		btnSubmit_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.addtoCartDist();
			}
		});
		btnSubmit_3.setBounds(279, 278, 97, 25);
		panelBuy.add(btnSubmit_3);
		
		btnSubmit_4 = new JButton("Get DistID");
		btnSubmit_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.getDistID();
			}
		});
		btnSubmit_4.setBounds(268, 48, 97, 25);
		panelBuy.add(btnSubmit_4);
		
		btnSubmit_5 = new JButton("Get DrugID");
		btnSubmit_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.getDrugID();
			}
		});
		btnSubmit_5.setBounds(268, 111, 97, 25);
		panelBuy.add(btnSubmit_5);
		
		btnSubmit_6 = new JButton("Reset");
		btnSubmit_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details.this.resetPur();
			}
		});
		btnSubmit_6.setBounds(152, 278, 97, 25);
		panelBuy.add(btnSubmit_6);
		
		lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(img);
		lblNewLabel_6.setBounds(110, 85, 175, 174);
		panelBuy.add(lblNewLabel_6);
		
		JButton btnDrug = new JButton("Drug");
		btnDrug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelDrug);
			}
		});
		btnDrug.setBounds(62, 13, 97, 25);
		frame.getContentPane().add(btnDrug);
		
		JButton btnDoctor = new JButton("Doctor");
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelDoc);
			}
		});
		btnDoctor.setBounds(193, 13, 97, 25);
		frame.getContentPane().add(btnDoctor);
		
		JButton btnPatient = new JButton("Patient");
		btnPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelPat);
			}
		});
		btnPatient.setBounds(337, 13, 97, 25);
		frame.getContentPane().add(btnPatient);
		
		JButton btnDistributor = new JButton("Distributor");
		btnDistributor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelDist);
			}
		});
		btnDistributor.setBounds(489, 13, 97, 25);
		frame.getContentPane().add(btnDistributor);
		
		JButton btnSale = new JButton("Sale");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelPurchase);
			}
		});
		btnSale.setBounds(648, 13, 97, 25);
		frame.getContentPane().add(btnSale);
		
		btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DrugID2.setText("");
				switchPanels(panelBuy);
			}
		});
		btnPurchase.setBounds(802, 13, 97, 25);
		frame.getContentPane().add(btnPurchase);
		
		JButton btnBack = new JButton("<--");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HomePage home = new HomePage();
		        home.main(null);
			}
		});
		btnBack.setBounds(-4, 13, 51, 25);
		frame.getContentPane().add(btnBack);
	}
}
