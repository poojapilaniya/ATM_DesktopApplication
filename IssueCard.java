import java.util.*;
import java.util.List;
import javax.swing.*;

import java.sql.ResultSet ; 
import java.text.*;
import java.util.Date;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class IssueCard implements ActionListener {
	
	IssueCard()
	{
		display() ; 
		
	}
	
	JFrame Block , Error, Success , Issue ,Allready;
	JTextField account,id ;
	JPanel p1 ,p2,p3,p4; 
	JLabel l1 , l2 , l3 ;
	JButton back , proceed , reset , ok  ; 
	
	void Errorbox()
	{
		Error = new JFrame("Error");
		Error.setSize(500,500);
		p3 = new JPanel();
		ok = new JButton("Okay");
		ok.addActionListener(this);
		l3 = new JLabel("The values you entered do not match.");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Error.setLocation(dim.width/2-Error.getSize().width/2, dim.height/2-Error.getSize().height/2);
	    Error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p3.add(l3);
		p3.add(ok);
		Error.add(p3);
		Error.setVisible(true);
		
	}
	void display()
	{
		
		Issue = new JFrame("Issue ATM Card");
		Issue.setSize(1000,500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Issue.setLocation(dim.width/2-Issue.getSize().width/2, dim.height/2-Issue.getSize().height/2);
	    Issue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		account  = new JTextField(20);
		id = new JTextField(20);
		l1 = new JLabel("Enter account number");
		id.setBounds(10,10,10,10);
		l1.setBounds(10,10,10,10);
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel( new FlowLayout());
		l2 = new JLabel("Enter associated user id");
		back = new JButton("Back");
		proceed = new JButton("Proceed");
		reset = new JButton("Reset");
		l2.setBounds(10,10,10,10);
		back.setBounds(10,10,10,10);
		proceed.setBounds(10,10,10,10);
		reset.setBounds(10,10,10,10);
		JLabel image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\issueatm.jpg"));

		back.addActionListener(this);
		proceed.addActionListener(this);
		reset.addActionListener(this);
		p2.add(back);
		p2.add(proceed);
		p2.add(reset);
		p1.add(l1);
		p1.add(account);
		p1.add(l2);
		p1.add(id);
		p1.add(image);
		Issue.add(p1, new BorderLayout().CENTER);
		Issue.add(p2, new BorderLayout().SOUTH);
		Issue.setVisible(true);
		
	}
	Random r  = new Random();
	int generateatm()
	{
		 return (r.nextInt(900000000) + 100000000);
	}
	int generatepin()
	{
		
		return (r.nextInt(9000) + 1000) ; 
	}
	int generatecvv()
	{
		return (r.nextInt(900) + 100) ;
	}
	String generateexpirydate()
	{
		Date today = new Date();
		Date changed  = new Date();
		Calendar c   = Calendar.getInstance() ; 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(dateFormat.format(today));
		today = c.getTime() ; 
		c.add(Calendar.YEAR, 10) ;
		changed = c.getTime() ; 
		return (dateFormat.format(changed));
		
	}
	void verify(String userid , String accountno)
	{
		try{
				
			if(Blockcard.blocking.get(accountno).equals(userid))
				{
				//System.out.println("found");
						try{
							
							Connetor connect  = new Connetor();
							connect.stm = connect.conn.createStatement() ; 
							String already = "select * from atmcard where accountno = " + accountno + " ; " ;
							ResultSet rs  = connect.stm.executeQuery(already);
							if(!rs.next())
						{		
							AtmCard a = new AtmCard();
							a.setPin(Integer.toString(generatepin()));
							a.setCvv(generatecvv()) ;
							a.setBlockstatus(0);
							a.setCardno(Integer.toString(generateatm()));
							String Query2 = "insert into atmcard values( \"" + a.getCardno() + "\" , \"" + a.getPin() + "\", " + a.getCvv() + ", \"" + generateexpirydate() + "\" ," + a.getBlockstatus() + ", " + accountno + "  ); " ; 					
							String Query3 = "update account set atmissuestatus =  1 where accountno = " + accountno + " ;" ; 
							String Query1 = "update account set cardno = \"" + a.getCardno() + "\" where accountno = " + accountno + " ;" ;  
							
							String Query4 = "select * from account where cardno =  \"" + a.getCardno() + "\" ;" ;
							
							connect.stm.executeUpdate(Query2) ; 
							connect.stm.executeUpdate(Query3) ;
							connect.stm.executeUpdate(Query1);
							Account acc = new Account() ; 
							ResultSet rs1 = connect.stm.executeQuery(Query4);
							System.out.println(a.getCardno());
							while(rs1.next())
							{
								acc.setType(rs1.getString("type"));
								acc.setBalance(rs1.getInt("balance"));
							}
							
							UserLogin.userlogin.put(a.getCardno(),a.getPin()); // Updating the hashmap.
							List<String> l = new ArrayList<String> (); 
							
							l.add(Integer.toString(acc.getBalance()));
							l.add(acc.getType());
							Transaction.transaction.put(a.getCardno(),l); // Updating transaction hashmap.
							JOptionPane.showMessageDialog(null, "Card Successfully issued. Card Number : " + a.getCardno() + " Pin = " + a.getPin());
							id.setText(""); account.setText("");
						}	
							else{
								JOptionPane.showMessageDialog(null, "Card already issued to the User.") ;
								id.setText(""); account.setText("");
								
							} 
							//System.out.println(Query2);
							//System.out.println(Query3);
							//System.out.println(Query1);
						}
						
						catch(Exception e){
							//verify(userid , accountno) ; 
							System.out.println(e);
						}
				}
			else{
				Errorbox();
			}
		}
		
		catch(Exception e)
		{
				
			Errorbox();
		}
		
		
	}

	//@Override
	public void actionPerformed(ActionEvent action) {
		
		if(action.getSource() == reset)
		{
			id.setText("");
			account.setText("");
		}
		if(action.getSource() == back)
		{
			    Issue.setVisible(false);
				new AdminFunctionality();
		}
		if(action.getSource() == proceed)
		{
			String userid = id.getText() ;
			String accountno = account.getText();
			if(userid.equals("") || accountno.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
				id.setText(""); account.setText("");
			}
			else
				{verify(userid , accountno);}
			
		}
		if(action.getSource() == ok )
		{
			Error.setVisible(false);
			Issue.setVisible(false);
			display();
		}	
	}
}
