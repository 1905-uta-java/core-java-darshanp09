package com.reavature.main;

import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

import com.reavature.Util.ConnectionUtil;
import com.reavature.dao.UserDao;
import com.reavature.dao.UserDaoImpl;
import com.reavature.driver.User;

public class banking {
	
	long account_number;
	String name;
	static double balance=100;
	static double amt;
	int ampt=0;
	double min_bal;
	
	
	
	public void depositAmount( double amt) {
		this.amt=amt;
		System.out.println("Enter a deposit amaount : ");
		balance+=amt;
		
		System.out.println("Deposit Successfull of Amount : "+amt);
	}
	
	
	
	public void withdrawBalance(double amt) throws IOException {
		
		this.amt =amt;
		
		balance-=amt;
		if(balance<min_bal) {
			
			ampt++;
			
			balance+=balance;
			
			System.out.println("Withdraw failer !!! of Amount : \u0024"+amt);
			
			if(ampt==3) {
				
				IOException io = new IOException("Illegal Attempts !!!!");
				throw io;
				
			}
			
		}else {
		System.out.println();
		System.out.println("Withdraw Successfully !! \u0024"+amt);	
		System.out.println();
		System.out.println("Balance is:  \u0024"+balance);
		
		System.out.println();
		System.out.println("Thank You! Have a Great Day.");
		System.out.println();
		
		}
	}
	
	
	
	public static void main(String[] args) {
		
		banking bRef = new banking();
		boolean auth = false;
		int with = 0;
		int Attempt = 0;
		int again=0;
		String name="";
		String email="";
		String password="";
		
		
		
		
		//System.out.println("-------Banking Started------");
		//bRef.depositAmount(1000);
		
		/*try {
		for(int i =0;i<100;i++) {
			
			bRef.withdrawBalance(200);
		}
		} catch (IOException e) {

			System.out.println();
			System.out.println("Error : "+e.getMessage());
			
		}
		
		System.out.println();
		
		System.out.println("-------Banking finished------");*/
		
		//User uRef = new User(2,"JOHN","JOHN@EXAMPLE.COM","Password");
		
		UserDaoImpl ud = new UserDaoImpl();
		
		User Uref = null;
		//System.out.println(ud.CreateUser(uRef));
		
		/*try {

			Connection c = ConnectionUtil.HardCodedConnection();
			System.out.println(c.getMetaData().getDriverName());
		}catch(Exception e) {
			System.out.println("Error : "+e.getMessage());
		}*/
		
		
		
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Press Input based on number.");
		System.out.println();
		System.out.println("[1] Existing User. or [2] New User. ?");
		int value = sc.nextInt();
		if(value==1) {
			while(auth!=true){
				Attempt++;
				
			System.out.println("Enter email-Address : ");
			email = sc.next();
	
			
			System.out.println("Enter password : ");
			password = sc.next();
			
			auth = ud.AuthenticatedUser(email,password);
			
			if(Attempt==3) {
				ArithmeticException io = new ArithmeticException("Illegal Attempt !!!");
				throw io;
			}
			}
			if(auth) {
				
				System.out.println("[1] Deposit. [2] Withdraw [3]Transfer.");
				int check = sc.nextInt();
				
				if(check==3) {
					System.out.println("Enter email of transferee: ");
					String email1 = sc.next();
					System.out.println("Enter amaount to transfer:");
					double trans = sc.nextDouble();
					ud.takeBalance(email, trans);
					ud.GIVEBalance(email1, trans);
					System.out.println("Amount is Transfred of : \u0024"+trans);
				}
				if(check==1) {
					System.out.println();
					System.out.println("Enter a Amount for Deposit: ");
					amt = sc.nextDouble();
					bRef.depositAmount(amt);
					ud.updateDepartment(email, amt);
					System.out.println();
					System.out.println("Press 00 to Withdraw Amount: ");
					with = sc.nextInt();
				}if(with==00){
					
					System.out.println();
					System.out.println("Enter Amount to Withdraw: ");
					amt = sc.nextDouble();
					try {
						bRef.withdrawBalance(amt);
						ud.AfterupdateDepartment(balance, email);
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}else {
					while(with!=00) {
						System.out.println("Enter Input Again: ");
						with=sc.nextInt();
					}
					System.out.println("Enter Amount to Withdraw: ");
					amt = sc.nextDouble();
					try {
						bRef.withdrawBalance(amt);
						ud.AfterupdateDepartment(balance, email);
						System.out.println();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(check==2){
					
					System.out.println();
					System.out.println("Enter Amount to Withdraw: ");
					amt = sc.nextDouble();
					try {
						bRef.withdrawBalance(amt);
						ud.AfterupdateDepartment(balance, email);
						System.out.println();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
								
			}else {
				System.out.println("404 BAD INPUT !!!!");
			}
			
		}if(value==2) {
			
			System.out.println("Enter your name: ");
			name = sc.next();
			System.out.println("Enter email Address: ");
			email = sc.next();
			System.out.println("Enter Password : ");
			password= sc.next();
			User uRef= new User(3,name,email,password,amt);
			System.out.println(ud.CreateUser(uRef));
			
			System.out.println();
			System.out.println("Successfully Registered!!!");
			
			System.out.println();
			System.out.println("Press 0 to Log-in:");
			int zero = sc.nextInt();
			
			if(zero==0) {
			while(auth!=true) {
				Attempt++;
			System.out.println("Enter user name : ");
					
			email = sc.next();
					
			System.out.println("Enter password : ");
					
			String password1 = sc.next();

			auth = ud.AuthenticatedUser(email,password1);
			if(Attempt==3) {
				ArithmeticException io = new ArithmeticException("Illegal Attempt !!!");
				throw io;
			}
			}
			if(auth) {

				System.out.println("[1] Deposit. [2] Withdraw");
				int check = sc.nextInt();
				
				if(check==1) {
					System.out.println();
					System.out.println("Enter a Amount for Deposit: ");
					amt = sc.nextDouble();
					bRef.depositAmount(amt);
					ud.updateDepartment(email, amt);
					System.out.println();
					System.out.println("Press 0 to Withdraw Amount: ");
					with = sc.nextInt();
				}if(with==0){
					
					System.out.println();
					System.out.println("Enter Amount to Withdraw: ");
					amt = sc.nextDouble();
					try {
						bRef.withdrawBalance(amt);
						ud.AfterupdateDepartment(balance, email);
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}else {
					while(with!=0) {
						System.out.println("Enter Input Again: ");
						with=sc.nextInt();
					}
					System.out.println("Enter Amount to Withdraw: ");
					amt = sc.nextDouble();
					try {
						bRef.withdrawBalance(amt);
						ud.AfterupdateDepartment(balance, email);
						System.out.println();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(check==2){
					
					System.out.println();
					System.out.println("Enter Amount to Withdraw: ");
					amt = sc.nextDouble();
					try {
						bRef.withdrawBalance(amt);
						ud.AfterupdateDepartment(balance, email);
						System.out.println();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}		
			}else {
				System.out.println("404 BAD INPUT !!!!");
			}
				
		}
			
	}

}

}
