import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Deposite extends JFrame implements ActionListener
{
	JFrame change ; 
	JLabel amount , acntype , jl3 ;
	JPanel p1 , p2 ,p3 , p4; 
	JTextField ammount ;
	JTextField type ;
	JButton Reset , deposit , Exit, Tryagain , Back; 
	
	Deposite(){
		Changepg();
	}
	private void Changepg(){
		
		change =new JFrame();
		change.setBounds(100,100,1000,500);
		change.setTitle("Deposit");
		
		ammount=new JTextField(20);
		type=new JTextField(20);
		ammount.setBounds(10,10,10,10);
		type.setBounds(10,10,10,10);
		
	amount=new JLabel("Amount");
		acntype=new JLabel("Account Type");
		amount.setBounds(10,10,10,10);
		acntype.setBounds(10,10,10,10);
		
		Reset  = new JButton("Reset");
		deposit  = new JButton("Deposit");
		Exit = new JButton ("Logout");
		Back = new JButton ("Back");
		Reset.setBounds(10,10,10,10);
		deposit.setBounds(10,10,10,10);
		Exit.setBounds(10,10,10,10);
		Back.setBounds(10,10,10,10);
		Exit.addActionListener(this);
		Reset.addActionListener(this);
		deposit.addActionListener(this);
		Back.addActionListener(this);
		JLabel image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\deposit1.jpg"));
		 
		p1=new JPanel(new FlowLayout());
		p2=new JPanel(new FlowLayout());
		 
		p1.add(amount);
		p1.add(ammount);
		p1.add(acntype);
		p1.add(type);
		
		p2.add(deposit);
		p2.add(Reset);
		p2.add(Exit);
		p2.add(Back);
		p1.add(image);
		//new BorderLayout();
		change.add(p1, new BorderLayout().CENTER);
		change.add(p2,new BorderLayout().SOUTH);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		change.setLocation(dim.width/2-change.getSize().width/2, dim.height/2-change.getSize().height/2);
		change.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		change.setVisible(true);
	}
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == Reset)
		{
			ammount.setText("");
			type.setText("");
		}
		if(action.getSource() == Exit)
		{
			Session.cardno = "" ;  
			Session.pin = "" ;
			change.setVisible(false);
			new WelcomePage();
		}
		if(action.getSource() == deposit)
		{
			if(ammount.getText().equals("") || type.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
				ammount.setText(""); type.setText("");
			}
			else{
					int amnt = Integer.parseInt(ammount.getText());
					String typ = type.getText(); 
					cashdeposit(amnt , typ);
		}}
		if(action.getSource() == Back)
		{
			change.setVisible(false);
			new UserFunctionality();
		}
	}

	public void depositcash(int amount,String acctype) throws  MaximumbalanceException, AccountExceptions
	   {
		 List<String> l =new ArrayList<String>();
		 l =Transaction.transaction.get(Session.cardno);
		 
		//System.out.println(l.toArray()[0]);
		 if(l.toArray()[1].toString().equals(acctype))
		 {
			 int balance = Integer.parseInt(l.toArray()[0].toString()) ;
			 if(amount< 50000)
			 {
				 try{
					 Connetor connect = new Connetor();
					 balance += amount;
					 connect.stm = connect.conn.createStatement();
					 String query1 = "update account set balance =  " + balance + " where cardno = \"" + Session.cardno + "\" ;" ;
					 //String query2 = "insert into ";
					 connect.stm.executeUpdate(query1);// Updating the database.
					 l.set(0, Integer.toString(balance)) ;// Updating the hashmap.
					// Transaction table to be updated.
					 ammount.setText(""); type.setText("");
					 JOptionPane.showMessageDialog(null, "Deposit Successful !! , Available Balance = " + balance );
					 System.out.println(Transaction.transaction);
					 }
					 catch(Exception e)
					 	{
					 		System.out.println(e);
					 	}
			 }
			 else
			 {
				 
				 throw new MaximumbalanceException(amount);
			 }
		 }
		 else
		 {
			throw new  AccountExceptions( "Sorry, account type mismatches !");
		 }
	   }
	
	 public void cashdeposit (int bal,String type)
	  {
		try{
				depositcash(bal,type);
			}
		catch( MaximumbalanceException e){
			JOptionPane.showMessageDialog(null , "Cannot deposit " + e.getAmount() + " at one go." + "Limit = " + "Rs. 50000");
		}
		catch( AccountExceptions e){
			JOptionPane.showMessageDialog(null,"Account type does not match.");
			}
		}
	
}
class AccountExceptions extends Exception
{
	private String  detail;
	AccountExceptions(String a) 
	{
		detail = a; 
	}
public String toString() 
	{
	return detail;
	}
}

 class MaximumbalanceException extends Exception
{
   private int amount;
   public  MaximumbalanceException(int  amount)
   {
      this.amount = amount;
   } 
   public double getAmount()
   {
      return amount;
   }
}



