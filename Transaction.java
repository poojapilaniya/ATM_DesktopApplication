
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import  javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;



public class Transaction extends JFrame implements ActionListener
{
	
	
	 static HashMap<String,List<String>> transaction ;
	 static Connetor connect;
	 static{
	 	try{
		transaction=new HashMap<String , List<String>> ();
	   	
	   	connect=new Connetor();
	   	List<String> list = new ArrayList<String>();
	   	
	 
	   		connect.stm=connect.conn.createStatement();
	   		String query ="select account.cardno,account.balance,account.type from account join atmcard on atmcard.cardno=account.cardno ;";
	   		ResultSet rs;
	   		rs = connect.stm.executeQuery(query);
	   		Account acc =new Account();
	   		while(rs.next()){
	   			 list = new ArrayList<String>(); 
	   			 acc.setBalance( rs.getInt("balance"));
	   			 acc.setCardno(rs.getString("cardno"));
	   			 acc.setType(rs.getString("type"));
	   			 list.add(Integer.toString(acc.getBalance()));
	   			 list.add(acc.getType());
	   			 transaction.put(acc.getCard_number(),list );
	   		}
	   		//List<String> l =new ArrayList<String>();
	   		//l = transaction.get("254879526");
	   		System.out.println(transaction);
	   		//System.out.println(l.toArray()[0].toString().length());*/
	   	}
	   	catch(Exception e){
	   		System.out.println(e);
	   	}
	   }
	   
	
	JFrame change ; 
	JLabel l1 , l2 , l3 ;
	JPanel p1 , p2 ,p3 , p4; 
	JTextField ammount ;
	JTextField type ;
	JButton Reset , withdraw , Exit, Tryagain , Back; 
	//private int amount;
	//private String account;
	//Transaction trns=new Transaction();
	Transaction()
	{
		//Account.createhm();
		Changepg();
	}
	
	private void Changepg(){
				
				change =new JFrame();
				change.setBounds(100,100,1000,500);
				change.setTitle("Withdraw");
				
				ammount=new JTextField(20);
				type=new JTextField(20);
				ammount.setBounds(10,10,10,10);
				type.setBounds(10,10,10,10);
				
				l1=new JLabel("Amount");
				l2=new JLabel("Account Type");
				l1.setBounds(10,10,10,10);
				l2.setBounds(10,10,10,10);
				
				Reset  = new JButton("Reset");
				withdraw  = new JButton("Withdraw");
				Exit = new JButton ("Logout");
				Back = new JButton ("Back");
				Reset.setBounds(10,10,10,10);
				withdraw.setBounds(10,10,10,10);
				Exit.setBounds(10,10,10,10);
				Back.setBounds(10,10,10,10);
				
				Exit.addActionListener(this);
				Reset.addActionListener(this);
				withdraw.addActionListener(this);
				Back.addActionListener(this);
				JLabel image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\banken.jpg"));
				 
				p1=new JPanel(new FlowLayout() );
				p2=new JPanel(new FlowLayout());
				 
				p1.add(l1);
				p1.add(ammount);
				p1.add(l2);
				p1.add(type);
				
				p2.add(withdraw);
				p2.add(Reset);
				p2.add(Exit);
				p2.add(Back);
				p1.add(image);
				//new BorderLayout();
				change.add(p1,BorderLayout.CENTER);
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
				if(action.getSource() == withdraw)
				{
					if(ammount.getText().equals("") || type.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
						ammount.setText(""); type.setText("");
					}
					else{
							int amnt = Integer.parseInt(ammount.getText());
							String typ = type.getText(); 
							change(amnt , typ);
				}
				}
				if(action.getSource() == Back)
				{
					change.setVisible(false);
					new UserFunctionality();
				}
			}	
 public void withdraw(int amount,String acctype) throws InsufficientbalanceException, AccountException
	   {
	 
		 List<String> l =new ArrayList<String>();
 		 l =transaction.get(Session.cardno);
 		//System.out.println(l.toArray()[0]);
		 if(l.toArray()[1].toString().equals(acctype))
		 {
			 int balance = Integer.parseInt(l.toArray()[0].toString()) ;
			 if(amount< (balance-500))
			 {
				 try{
				 balance -= amount;
				 connect.stm = connect.conn.createStatement();
				 String query1 = "update account set balance =  " + balance + " where cardno = \"" + Session.cardno + "\" ;" ;
				 
				 connect.stm.executeUpdate(query1);// Updating the database.
				 l.set(0, Integer.toString(balance)) ;// Updating the transaction hashmap.
				// Transaction table to be updated.
				 ammount.setText(""); type.setText("");
				 JOptionPane.showMessageDialog(null, "Withdraw Successful !! , Available Balance = " + balance );
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
 
	
  public void change (int bal,String type)
  {
	try{
			withdraw(bal,type);
		}
	catch(InsufficientbalanceException e){
		JOptionPane.showMessageDialog(null, "Sorry, but you are short of Rs. " + e.getAmount());
		
	}
	catch( AccountException e){
		JOptionPane.showMessageDialog(null, e.toString() );
		}
	}

 }


class AccountException extends Exception
{
	private String  detail;
	AccountException(String a) 
	{
		detail = a; 
	}
public String toString() 
	{
	return detail;
	}
}

 class InsufficientbalanceException extends Exception
{
   private int amount;
   public InsufficientbalanceException(int  amount)
   {
      this.amount = amount;
   } 
   public double getAmount()
   {
      return amount;
   }
}
