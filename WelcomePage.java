import javax.swing.*;
import java.io.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class WelcomePage extends JFrame implements ActionListener{

	JFrame welcome ; 
	JButton admin,user;
	JLabel image;
	public WelcomePage()
	{
		welcome = new JFrame("LNMIIT ATM SERVICE");
		JPanel p1 = new JPanel ( new BorderLayout());
		welcome.setSize(600,600);
		admin = new JButton("Admin");
		user = new JButton("User");
	admin.setBounds(15,15,15,15);
	user.setBounds(15,15,15,15);
	
		 image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\wel2.jpg"));
		
		p1.add(admin,BorderLayout.WEST);
		p1.add(user,BorderLayout.EAST);
	
		//image.setBounds(100,100,100,100);
		//p1.setBounds(100,100,100,100);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		welcome.setLocation(dim.width/2-welcome.getSize().width/2, dim.height/2-welcome.getSize().height/2);
		welcome.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//JLabel l1 = new JLabel("Welcome");
		//panel_label.add(l1);
		
	//	Object color;
		p1.setSize(100, 100);
		//setBackgrund(color.LightGray);
		p1.add(image);
		welcome.add(p1,BorderLayout.CENTER);
		//welcome.pack();
		
		welcome.setVisible(true);
		admin.addActionListener(this);
		user.addActionListener(this);
	
	}
	public void actionPerformed(ActionEvent e){  
		if(e.getSource()== admin)
		{
			welcome.setVisible(false);
		    new AdminLogin(); 
		}
		if(e.getSource()== user)
		{
			welcome.setVisible(false);  
			new UserLogin();
		}
		}  
}
