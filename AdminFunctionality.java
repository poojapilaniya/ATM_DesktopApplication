import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminFunctionality  implements ActionListener{

		 JFrame Function ;
	     JButton Block , Issue , View, CreateAccount  ,Exit ; 
	     JPanel p1 ; 
	     
	     AdminFunctionality(){
		 
	     Function = new JFrame("Welcome Admin" );
		 Function.setSize(500,500);
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 Function.setLocation(dim.width/2-Function.getSize().width/2, dim.height/2-Function.getSize().height/2);
	     Function.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 Block = new JButton("Block ATM Card");
		 Block.setBounds(10,10,10,10);
		 Block.addActionListener(this);
		 Issue = new JButton("Issue new ATM Card");
		 Issue.setBounds(10,10,10,10);
		 Issue.addActionListener(this);
		 
		 CreateAccount = new JButton("Create Account");
		 CreateAccount.setBounds(10,10,10,10);
		 CreateAccount.addActionListener(this);
		 
		 View = new JButton("View Daily Transaction");
		 View.setBounds(10,10,10,10);
		 View.addActionListener(this);
		JLabel image = new JLabel(new ImageIcon("C:\\Users\\sudheer pilania\\Pictures\\amf.jpg"));
		 
		 Exit = new JButton("Logout");
		 Exit.setBounds(10,10,10,10);
		 Exit.addActionListener(this);
		 p1 = new JPanel(new FlowLayout());
		JPanel p2 = new JPanel(new FlowLayout());
		 p1.add(Block);
		 p1.add(Issue);
		 p1.add(CreateAccount);
		 //p1.add(View);
		 p2.add(Exit);
		 p1.add(image);
		 Function.add(p1,BorderLayout.CENTER);
		 Function.add(p2,BorderLayout.SOUTH);
		 Function.setVisible(true);
	 }

	
		public void actionPerformed(ActionEvent action) {
			
			if(action.getSource() == Exit)
			{
				Function.setVisible(false) ;
				new WelcomePage();
			}
			if(action.getSource() == View){}
			if(action.getSource() == CreateAccount){
				Function.setVisible(false);
				new CreateAccount() ;
			}
			
			if(action.getSource() == Issue){
				Function.setVisible(false) ; 
				new IssueCard();
				
			}
			
			if(action.getSource() == Block)
			{
				Function.setVisible(false);
				new Blockcard();
			}
			
		}

}

