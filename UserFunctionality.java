

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class UserFunctionality implements ActionListener {
	JFrame function ;

	
	 JButton cashwdr , cashtnf ,cashdep,chngpin,balance,ministmt ,Exit ; 
     JPanel p1 ;
     
     UserFunctionality(){
    	 
    	  function =new JFrame("Welcome User");
    	  function.setSize(1000,500);
    	  p1 =new JPanel(new GridLayout());
    JPanel p2 =new JPanel(new FlowLayout());
    	 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		 function.setLocation(dim.width/2-function.getSize().width/2, dim.height/2-function.getSize().height/2);
 	     function.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		 cashwdr = new JButton("CASH WITHDRAWAL");
 		 cashwdr.setBounds(2,2,2,2);
 		 cashwdr.addActionListener(this);
 		 cashtnf = new JButton("CASH TRANSFER");
 		 cashtnf.setBounds(2,2,2,2);
 		 cashtnf.addActionListener(this);
 		 cashdep = new JButton("CASH DEPOSIT");
 		 cashdep.setBounds(2,2,2,2);
 		 cashdep.addActionListener(this);
 		 balance = new JButton("BALANCE ENQUIRY");
 		 balance.setBounds(2,2,2,2);
 		 balance.addActionListener(this);
 		 ministmt = new JButton("MINI STAEMENT");
 		 ministmt.setBounds(2,2,2,2);
 		 ministmt.addActionListener(this);
 		 chngpin = new JButton("CHANGE PIN");
 		 chngpin.setBounds(2,2,2,2);
 		 chngpin.addActionListener(this);
 		 Exit = new JButton("Logout");
 		 Exit.setBounds(2,2,2,2);
		 Exit.addActionListener(this);
		 JLabel image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\wel2.jpg"));
		 
 		 
 		 p1.add(cashwdr);
 		 p1.add(cashtnf);
 		 p1.add(cashdep);
 		 p1.add(balance);
 		 //jp1.add(ministmt);
 		 p1.add(chngpin);
 		 p2.add(Exit); 
 		 p1.add(image);
 		 function.add(p1,BorderLayout.CENTER);
 		function.add(p2,BorderLayout.SOUTH);
 		 function.setVisible(true);
     }
	
     public void actionPerformed(ActionEvent action){
    	 if(action.getSource() == Exit)
			{
				function.setVisible(false) ;
				new WelcomePage();
			}
			if(action.getSource() == cashwdr){
				function.setVisible(false) ;
				new Transaction();
			}
			
			if(action.getSource() == cashdep){
				function.setVisible(false);
				new Deposite();
				
			}
			
			if(action.getSource() == chngpin)
			{
				function.setVisible(false);
			//	System.out.println(Session.cardno);
				//System.out.println(Session.pin);
				 new ChangePin();
			}
			if(action.getSource() == cashtnf){
				
				function.setVisible(false);
				new Transfer();
				
			}
			
			if(action.getSource() == balance){
				//function.setVisible(false);
				new BalanceEnquiry();
				
			}
			
			
    	 
     }
}

