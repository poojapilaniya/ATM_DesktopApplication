import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import javax.swing.*;


class Transfer  extends JFrame implements ActionListener
{
	JFrame change ; 
	JLabel l1 , l2 , l3,l4 ;
	JPanel p1 , p2 ,p3 , p4; 
	JTextField ammount ;
	JTextField type ;
	JTextField acc_no;
	JButton Reset , transfer , Exit, Tryagain , Back; 
	
	Transfer()
	{
		transpg();
	}
private void transpg()
{
	change =new JFrame();
	change.setBounds(100,100,1000,500);
	change.setTitle("Transfer");
	
	ammount=new JTextField(20);
	type=new JTextField(20);
	acc_no= new JTextField(20);
	type.setBounds(10,10,10,10);
	acc_no.setBounds(10,10,10,10);
	
	l1=new JLabel("Amount");
	l2=new JLabel("Account Type");
	l4=new JLabel("Account no");
    l1.setBounds(10,10,10,10);
	l2.setBounds(10,10,10,10);
	l4.setBounds(10,10,10,10);
	Reset  = new JButton("Reset");
	transfer  = new JButton("transfer");
	Exit = new JButton ("Logout");
	Back = new JButton ("Back");
	Reset.setBounds(10,10,10,10);
	transfer.setBounds(10,10,10,10);
	Exit.setBounds(10,10,10,10);
	Back.setBounds(10,10,10,10);
	Exit.addActionListener(this);
	Reset.addActionListener(this);
	transfer.addActionListener(this);
	Back.addActionListener(this);
	
	p1=new JPanel(new FlowLayout());
	p2=new JPanel(new FlowLayout());
	JLabel image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\transfer.jpg"));
	p1.add(l1);
	p1.add(ammount);
	p1.add(l2);
	p1.add(type);
	p1.add(l4);
	p1.add(acc_no);
	
	p2.add(transfer);
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
public void actionPerformed(ActionEvent action)
{
		if(action.getSource() == Reset)
		{
			ammount.setText("");
			type.setText("");
			acc_no.setText("");
		}
		if(action.getSource() == Exit)
		{
			Session.cardno = "" ;  
			Session.pin = "" ;
			change.setVisible(false);
			new WelcomePage();
		}
		if(action.getSource() == transfer)
		{
			if(ammount.getText().equals("") || type.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
				ammount.setText(""); type.setText("");
			}
			else
			{
				int amnt = Integer.parseInt(ammount.getText());
				String acctype = type.getText();
				int accno =Integer.parseInt(acc_no.getText());
				change(amnt,acctype,accno);
			}
		}
		if(action.getSource() == Back)
		{
			change.setVisible(false);
			new UserFunctionality();
		}
	}

  	public void change (int bal,String type, int accno)
	{
		try{
			transfercash(bal,type,accno);
			}
		catch(InsufficientbalanceException e)
		{
			JOptionPane.showMessageDialog(null, "Sorry, but you are short of Rs. " + e.getAmount()+" You cannot transfer the entered amount");
		
		}
		catch( AccountException e)
		{
			JOptionPane.showMessageDialog(null, e.toString() );
		}
		catch(AccountNoException e)
		{
			JOptionPane.showMessageDialog(null,"Account number entered by you doesn't exist. Please provide valid account number");
		}
	}
	
	private void transfercash(int amount, String type2, int accno) throws InsufficientbalanceException, AccountException,AccountNoException
	{
		List<String> l =new ArrayList<String>();
		l =Transaction.transaction.get(Session.cardno);
		if(Blockcard.blocking.containsKey(Integer.toString(accno)))
		{
			if( l.toArray()[1].toString().equals(type2) ) 
			{
				int balance = Integer.parseInt(l.toArray()[0].toString()) ;
				if(amount< (balance-500))
				{
					try{
						 Connetor connect = new Connetor();
						 balance -= amount;
						 connect.stm = connect.conn.createStatement();
						 String query1 = "update account set balance =  " + balance + " where cardno = \"" + Session.cardno + "\" ;" ;
						 l.set(0, Integer.toString(balance)) ;
						 
						 String query2 = "update account set balance = balance + " + amount + " where accountno = " + accno + " ;" ;
						 connect.stm.executeUpdate(query1);
						 connect.stm.executeUpdate(query2);
						 //System.out.println(query1);
						 //System.out.println(query2);
						 String query3 = "select cardno from account where accountno =" + accno + " ;" ;
						 ResultSet rs ; 
						 String card = ""; 
						 rs =connect.stm.executeQuery(query3);
						 while(rs.next())
						 {
							 card = Integer.toString(rs.getInt("cardno"));
						 }
						 if(card!=null)
						 { 
							l =Transaction.transaction.get(card);
						 	int current = Integer.parseInt(l.get(0));
						 	current = current + amount ; 
						 	l.set(0, Integer.toString(balance));
						 }
						 
						 ammount.setText(""); type.setText("");
						 JOptionPane.showMessageDialog(null, "Transfer Successful !! , Available Balance = Rs. " + balance );
						 //System.out.println(Transaction.transaction);
						 }
						 catch(Exception e)
						 	{
						 		System.out.println(e);
						 	}
				}
				else
				{
					int  needs = amount - balance;
					throw new InsufficientbalanceException(needs);
				}
			
			}
			else
			{
				throw new  AccountException( "Sorry, account type mismatches !");
			}
		}
		else
		{
				throw new AccountNoException("account number enetred by you is incorrect !");
		}
		
	}
	
}

class AccountNoException extends Exception
{
	private String  detail;
	AccountNoException(String a) 
	{
		detail = a; 
	}
public String toString() 
	{
	return detail;
	}
}
