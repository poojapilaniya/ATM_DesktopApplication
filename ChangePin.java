import javax.swing.*;

import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class ChangePin extends JFrame implements ActionListener 
{	
	JFrame change ; 
	JLabel l1 , l2 , l3 ;
	JPanel p1 , p2 ,p3 , p4; 
	JPasswordField newpassd; ;
	JPasswordField oldpassd ;
	JButton Reset , changee , Exit, Tryagain , Back; 

	ChangePin(){
		//System.out.println(Session.cardno);
		Changepg();
	}
	
	
	private void Changepg(){
		
		change =new JFrame();
		change.setBounds(100,100,1000,500);
		change.setTitle("CHANGE PIN  ");
		
		newpassd=new JPasswordField(20);
		oldpassd=new JPasswordField(20);
		newpassd.setBounds(10,10,10,10);
		oldpassd.setBounds(10,10,10,10);
		
		l1=new JLabel("Old Pin");
		l2=new JLabel("New Pin");
		l1.setBounds(10,10,10,10);
		l2.setBounds(10,10,10,10);
		Reset  = new JButton("Reset");
		changee  = new JButton("Change Pin");
		Exit = new JButton ("Logout");
		Back = new JButton ("Back");
		Reset.setBounds(10,10,10,10);
		changee.setBounds(10,10,10,10);
		Exit.setBounds(10,10,10,10);
		Back.setBounds(10,10,10,10);
		Exit.addActionListener(this);
		Reset.addActionListener(this);
		changee.addActionListener(this);
		Back.addActionListener(this);
		
		p1=new JPanel(new FlowLayout());
		p2=new JPanel(new FlowLayout());
		JLabel image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\changepin.jpg"));
		 
		p1.add(l1);
		p1.add(oldpassd);
		p1.add(l2);
		p1.add(newpassd);
		
		p2.add(changee);
		p2.add(Reset);
		p2.add(Exit);
		p2.add(Back);
		p1.add(image);
		change.add(p1,new BorderLayout().CENTER);
		change.add(p2,new BorderLayout().SOUTH);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		change.setLocation(dim.width/2-change.getSize().width/2, dim.height/2-change.getSize().height/2);
		change.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		change.setVisible(true);
	}
	
public void actionPerformed(ActionEvent action) {
	if(action.getSource() == Reset)
	{
		oldpassd.setText("");
		newpassd.setText("");
	}
	if(action.getSource() == Exit)
	{
		Session.cardno = "" ;  
		Session.pin = "" ;
		change.setVisible(false);
		new WelcomePage();
	}
	if(action.getSource() == changee)
	{
		String old  =String.valueOf(oldpassd.getPassword());
		String newpd = String.valueOf(newpassd.getPassword());
		if(old.equals("") || newpd.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
			oldpassd.setText(""); newpassd.setText("");
		}
		else
			{changepassd(old,newpd);}
	}
	if(action.getSource() == Back)
	{
		change.setVisible(false);
		new UserFunctionality();
	}
}

 void changepassd(String old,String newpin){
 		 
 		//System.out.println(card);
 		if(UserLogin.userlogin.get(Session.cardno).equals(old))
 		{
	 		
	 		try{
	 			Connetor connect;
		 		connect=new Connetor();
	 			connect.stm=connect.conn.createStatement();
	 			String query ="update atmcard set pin = \"" + newpin + "\" where cardno = \"" + Session.cardno + "\" ; ";
	 			//System.out.println(query);
	 			connect.stm.executeUpdate(query);
	 			//System.out.println(query);
	 			UserLogin.userlogin.put(Session.cardno,newpin ) ;//Updating the hashmap with newpin .
	 			JOptionPane.showMessageDialog(null, "Pin Successfully Changed.") ;
	 			oldpassd.setText("");
	 			newpassd.setText("");
	 			
	 		}
	 		catch(Exception e){
	 			System.out.println(e);
	 		}	
 	
 		}
 		else
 		{
 			JOptionPane.showMessageDialog(null, "Old pin is incorrect.") ;
 			oldpassd.setText("");
			newpassd.setText("");
 		}
 	}
	 
}
