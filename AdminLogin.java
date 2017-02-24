

//import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class AdminLogin extends JFrame implements ActionListener{
	
   // private static final String This = null;
	static HashMap<String , String> adminlogin; 
    static Connetor connect;
    static{ 
    	 adminlogin = new HashMap<String , String>();
		 connect  = new Connetor();
    	 //System.out.println("here");
		try{
			
			
			connect.stm = connect.conn.createStatement();
			String sql1  = "select * from admin ;"; 
			ResultSet rs;
			rs = connect.stm.executeQuery(sql1);
			Admin a = new Admin ();
			while(rs.next()){
				
				a.setAdminid(rs.getString("adminid"));
				a.setPassword(rs.getString("password"));
				adminlogin.put(a.getAdminid(), a.getPassword());
				
			}
	}
		catch(Exception e){
			System.out.println("Exception Occured.");
		}
	}

	JFrame login , Error ; 
	JLabel adminid, pass, wrong ,image;
	JPanel p1 , p2 ,p3 , p4; 
	JTextField id ;
	JPasswordField password ; 
	JButton Reset , Proceed , Exit, Tryagain , exit ;
	AdminLogin()
	{
		
		LoginPage();
		
	}
	private void Errorbox()
	{
		Error = new JFrame("Error");
		
		wrong = new JLabel("The credentials you have entered are wrong.");
		p3 = new JPanel(new BorderLayout());
		p4 = new JPanel(new BorderLayout());
		p3.add(wrong,BorderLayout.CENTER);
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
		Error.setSize(500,500);
		Error.setVisible(true) ;
	}
	private void  LoginPage() 
	{
		login = new JFrame("Welcome Admin");
	
		login.setSize(1000,500);
		
		id = new JTextField(20);
		password = new JPasswordField(20);
		id.setBounds(10,10,10,10);
		password.setBounds(10,10,10,10);
		adminid = new JLabel("Admin ID");
		pass = new JLabel("Password"); 
		adminid.setBounds(10,10,10,10);
		pass.setBounds(10,10,10,10);
		
	//image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\wel2.jpg"));
		Reset  = new JButton("Reset");
		Proceed  = new JButton("Proceed");
		Exit = new JButton ("Exit");
		Reset.setBounds(2,2,2,2);
		Proceed.setBounds(2,2,2,2);
		Exit.setBounds(2,2,2,2);
		 image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\adminl.jpg"));
			
		
		p1 = new JPanel( new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		p1.add(adminid);
		p1.add(id);
		p1.add(pass);
		p1.add(password);
		p2.add(Reset);
		p2.add(Proceed);
		p2.add(Exit);
		
		
		
		login.add(p1,new BorderLayout().CENTER);
		login.add(p2,new BorderLayout().SOUTH);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		login.setLocation(dim.width/2-login.getSize().width/2, dim.height/2-login.getSize().height/2);
		login.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p1.add(image);
		
		login.setVisible(true);
		Exit.addActionListener(this);
		Reset.addActionListener(this);
		Proceed.addActionListener(this);
	}

	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == Reset)
		{
			id.setText("");
			password.setText("");
		}
		if(action.getSource() == Exit)
		{
			login.setVisible(false);
			new WelcomePage();
		}
		if(action.getSource() == Proceed)
		{
			String adminid  = "" ; 
			String adminpassword = "" ; 
			adminid  = id.getText() ; 
			adminpassword = String.valueOf(password.getPassword());
			if(adminid.equals("") || adminpassword.equals(""))
			{
		
				JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
				id.setText("");
				password.setText("");
			}
			else
				{verify(adminid , adminpassword);}
		}
		if(action.getSource() == Tryagain)
		{
			Error.setVisible(false);
			LoginPage();
		}
		if(action.getSource() == exit)
		{
			Error.setVisible(false);
			new WelcomePage();
		}
	}
	
	void verify(String adminid , String adminpassword)
	{
		try{
		
		if(adminlogin.get(adminid).equals(adminpassword))
		{
				login.setVisible(false) ; 
				new AdminFunctionality();
		}
		else
		{
			//int count=0;
			//if(count<3){
			login.setVisible(false);
			Errorbox();
			
	
			//count++;
			///}
			//else
			//{
				//JOptionPane.showMessageDialog(null, "you have done 3 times wrong" );
		//}
		}
	}
		catch(Exception e)
		{
			login.setVisible(false);
			Errorbox() ; 
		}
	}
	
}
