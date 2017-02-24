/*import  javax.swing.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class Withdrawl implements ActionListener
	 
	Withdrawl()
	{
		nxtpage();
	}
	private void nxtpage(){
		
		JFrame withdrw =new JFrame();
         withdrw.setBounds(200,200,1000,500);
		withdrw.setTitle("Withdraw");
		
		JTextField amountt=new JTextField(30);
		 JTextField type=new JTextField(20);
		
		JLabel amountl = new JLabel("Amount");
		JLabel acntype=new JLabel("Account Type");
		
		JButton Reset  = new JButton("Reset");
		JButton withdraw  = new JButton("Withdraw");
		JButton Exit = new JButton ("Exit");
		JButton Back = new JButton ("Back");
		
		Exit.addActionListener(this);
		Reset.addActionListener(this);
		withdraw.addActionListener(this);
		Back.addActionListener(this);
		
		JPanel p1=new JPanel(); //p1 use for amount,accont type
		JPanel p2=new JPanel(); //p1 use for buttons
		 
		p1.add(amountt);   
		p1.add(amountl);
		p1.add(acntype);
		p1.add(type);
		
		p2.add(withdraw);
		p2.add(Reset);
		p2.add(Exit);
		p2.add(Back);
		
		withdrw.add(p1,new BorderLayout().CENTER);
		withdrw.add(p2,new BorderLayout().SOUTH);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		withdrw.setLocation(dim.width/2-withdrw.getSize().width/2, dim.height/2-withdrw.getSize().height/2);
		withdrw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		withdrw.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == Reset)
		{
			amount1.setText("");
			acnttype.setText("");
		}
		if(action.getSource() == Exit)
		{
			Cardpin.cardno = "" ;  
			Cardpin.pin = "" ;
			withdrw.setVisible(false);
			new WelcomePage();
		}
		if(action.getSource() == withdraw)
		{
			if(amountt.equals("") || type.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
				amountt.setText(""); 
                     type.setText("");
			}
			else{
					int amnt = Integer.parseInt(amount.getText());
					String typ = type.getText(); 
					//change(amnt , typ);
		}
		if(action.getSource() == Back)
		{
			change.setVisible(false);
			new UserFunctionality();
		}
	}

	}	
}

*/