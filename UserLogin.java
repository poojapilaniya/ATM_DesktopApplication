
import javax.swing.*;

import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class UserLogin extends JFrame implements ActionListener{
		
		//Session s = new Session();
		static Map<String , String> userlogin; 
	    static Connetor connect;
	    static {
	    	userlogin =new HashMap<String,String> ();
	    	connect=new Connetor();
	    	try{
	    		
	    		connect.stm=connect.conn.createStatement();
	    		String query ="select * from atmcard ;";
	    		ResultSet rs;
	    		rs = connect.stm.executeQuery(query);
	    		AtmCard c=new AtmCard();
	    		while(rs.next()){
	    			c.setCardno(rs.getString("cardno"));
	    			c.setPin(rs.getString("pin"));
	    			userlogin.put(c.getCardno(),c.getPin());
	    			
	    		}
	    		System.out.println(userlogin);
	    	}
	    	catch(Exception e){
	    		System.out.println("Exception occured");
	    	}
	    }
	 
	    JFrame login , Error ; 
		JLabel l1 , l2 , l3 ;
		JPanel p1 , p2 ,p3 , p4; 
		JTextField cardno ;
		JPasswordField password ; 
		JButton Reset , Proceed , Exit, Tryagain , exit ;
		UserLogin(){
			Loginpage();
		}
		private void Errorbox()
		{
			Error = new JFrame("Error");
			Error.setSize(500,500);
			l3 = new JLabel("The credentials you have entered are wrong.");
			p3 = new JPanel();
			p4 = new JPanel();
			p3.add(l3);
			Tryagain = new JButton("Try Again");
			exit = new JButton("Exit");
			p4.add(Tryagain);
			p4.add(exit);
			Error.add(p3,new BorderLayout().CENTER);
			Error.add(p4 , new BorderLayout().SOUTH);
			Tryagain.addActionListener(this);
			exit.addActionListener(this);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			Error.setLocation(dim.width/2-Error.getSize().width/2, dim.height/2-Error.getSize().height/2);
			Error.setDefaultCloseOperation(EXIT_ON_CLOSE);
			Error.setVisible(true) ;
		}
		private void Loginpage(){
			login = new JFrame ("Welcome User");
			login.setSize(1000,500);
			
			cardno = new JTextField(20);
			password = new JPasswordField(20);
			cardno.setBounds(5,5,5,5);
			password.setBounds(5,5,5,5);
			l1 = new JLabel("ATM Card_NO");
			l2 = new JLabel("Password"); 
			Reset  = new JButton("Reset");
			Proceed  = new JButton("Proceed");
			Exit = new JButton ("Exit");
			l1.setBounds(5,5,5,5);
			l2.setBounds(5,5,5,5);
			Reset.setBounds(5,5,5,5);
			Proceed.setBounds(5,5,5,5);
			Exit.setBounds(5,5,5,5);
			JLabel image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\user.jpg"));
			 
			
			Exit.addActionListener(this);
			Reset.addActionListener(this);
			Proceed.addActionListener(this);
			p1 = new JPanel( new FlowLayout());
			p2 = new JPanel(new FlowLayout());
			p1.add(l1);
			p1.add(cardno);
			p1.add(l2);
			p1.add(password);
			p2.add(Reset);
			p2.add(Proceed);
			p2.add(Exit);
			p1.add(image);
			login.add(p1,new BorderLayout().CENTER);
			login.add(p2,new BorderLayout().SOUTH);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			login.setLocation(dim.width/2-login.getSize().width/2, dim.height/2-login.getSize().height/2);
			login.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
			login.setVisible(true);
		}
		public void actionPerformed(ActionEvent action) {
			if(action.getSource() == Reset)
			{
				cardno.setText("");
				password.setText("");
			}
			if(action.getSource() == Exit)
			{
				login.setVisible(false);
				new WelcomePage();
			}
			if(action.getSource() == Proceed)
			{	
				//System.out.println(userlogin);
				
				Session.cardno = cardno.getText();
				Session.pin = String.valueOf(password.getPassword());
				if(Session.cardno.equals("") || Session.pin.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
					cardno.setText(""); password.setText("");
				}
				else
					{verify(Session.cardno,Session.pin);}
				
			}
			if(action.getSource() == Tryagain)
			{
				Error.setVisible(false);
				Loginpage();
			}
			if(action.getSource() == exit)
			{
				Error.setVisible(false);
				new WelcomePage();
			}
		}
		
		void verify(String cardno , String password)
		{
			try{
			
		
			if(userlogin.get(cardno).equals(password))
			{
					login.setVisible(false) ; 
					new UserFunctionality();
			}
			else
			{
				login.setVisible(false);
				Errorbox();
				//System.out.println("not exist");
			}
		}
			catch(Exception e)
			{
				login.setVisible(false);
				Errorbox() ; 
				//System.out.println(e);
			}
		}		
}
