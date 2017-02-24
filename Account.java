import java.util.ArrayList ; 
import java.sql.* ; 
import java.util.*;
public class Account {

	private int account_number ; 
	private String type ; 
	private String bankname ; 
	private int balance  ;
	private int atmissuestatus ;
	private String card_number;
	public Account()
	{
		
	}
	public Account(int account_number,String type,String bankname,int balance, int atmissuestatus, String card_number )
	{
		this.account_number = account_number ;
		this.type = type;
		this.bankname = bankname ; 
		this.card_number = card_number ; 
		this.atmissuestatus = atmissuestatus ; 
		this.balance = balance ;
	}
	
	public String getCard_number() {
		return card_number;
	}
	public void setCardno(String card_number) {
		this.card_number = card_number;
	}
	public int getAccount_number() {
		return account_number;
	}
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getAtmissuestatus() {
		return atmissuestatus;
	}
	public void setAtmissuestatus(int atmissuestatus) {
		this.atmissuestatus = atmissuestatus;
	}
	public String toString()
	{
		return (this.account_number + " " + this.bankname + " " +this.balance + " " + this.atmissuestatus + " " + this.type);
	}

	 
}
