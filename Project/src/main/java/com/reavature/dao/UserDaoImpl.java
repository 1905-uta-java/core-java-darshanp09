package com.reavature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.reavature.Util.ConnectionUtil;
import com.reavature.driver.User;

public class UserDaoImpl {

	public List<User> getUers() {
		List<User> users = new ArrayList<>();
		
		String sql = "SELECT * FROM USERS";
		
		try(Connection con = ConnectionUtil.HardCodedConnection();
			Statement s= con.createStatement();
			ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				double deposit = rs.getDouble(5);
				users.add(new User(id,name,email,password,deposit));
				
			}
			
		}catch(Exception e) {
			
			
			System.out.println("Error : "+e.getMessage());
		}
		
		return users;
	}

	public User getUserById(int Userid) {
		User uRef = null;
		
		String sql = "SELECT * FROM USERS WHERE USERID=?";
		
		try(Connection con = ConnectionUtil.HardCodedConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, Userid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				double deposit = rs.getDouble(5);
			
				uRef = new User(id,name,email,password,deposit);	
			}
			
		}catch(Exception e ) {
			System.out.println("Error : "+e.getMessage());
		}
		return uRef;
	}

	public int CreateUser(User uRef) {
		int UserCreated=0;
		
		String sql = "INSERT INTO USERS VALUES(?,?,?,?,?)";
		
		try(Connection con = ConnectionUtil.HardCodedConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, uRef.getUserid());
			ps.setString(2, uRef.getName());
			ps.setString(3, uRef.getEmail());
			ps.setString(4, uRef.getPassword());
			ps.setDouble(5, uRef.getDeposit());
			
			UserCreated = ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		
		return UserCreated;
	}

	public boolean AuthenticatedUser(String email,String Password) {
		User uRef;
		boolean auth = true;
		
		String sql="SELECT * FROM USERS WHERE USEREMAIL=? AND USERPASSWORD = ?";
		
		try(Connection con = ConnectionUtil.HardCodedConnection();
			PreparedStatement ps  = con.prepareStatement(sql)) {
			ps.setString(1, email );
			ps.setString(2, Password);
			ResultSet rs = ps.executeQuery();
			
			auth= rs.next();
			System.out.println("Auth : "+auth);
			
		}catch(Exception e ) {
			
			System.out.println("Error: "+e.getMessage());
		}
		
		
		return auth;
		
	}
	
	public void processbatch() {
		
		//String sql = "UPDATE USERS SET  "
		
		
	}
	
	/*public void processbatch(double balance ,String email1,String email2) {
	
	String sql = "UPDATE USERS SET USERDEPOSIT = USERDEPOSIT-'"+balance+"' WHERE USEREMAIL='"+email1+"' ";
	String sql1 = "UPDATE USERS SET USERDEPOSIT = USERDEPOSIT+'"+balance+"' WHERE USEREMAIL='"+email2+"' ";
	
	try(Connection con = ConnectionUtil.HardCodedConnection();
		Statement sRef = con.createStatement()) {
		con.setAutoCommit(false);
		sRef.addBatch(sql);
		sRef.addBatch(sql1);
		
		sRef.executeBatch();
		con.commit();
		
	}catch(Exception e) {
		System.out.println("Error: "+e.getMessage());
		try {
			Connection con = ConnectionUtil.HardCodedConnection();
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}*/


	public double takeBalance(String email, double trans) {
	User uRef = null;
	int UserUpdated=0;

	String sql = "UPDATE USERS SET USERDEPOSIT=USERDEPOSIT -'"+trans+"' WHERE USEREMAIL='"+email+"'";

	try(Connection con = ConnectionUtil.HardCodedConnection();
 	Statement ps = con.createStatement()){
	UserUpdated = ps.executeUpdate(sql);
	
	}catch(Exception e) {
	System.out.println("Error: "+e.getMessage());
	}


	return UserUpdated;
	}
	public double GIVEBalance(String email1, double trans) {
	User uRef = null;
	int UserUpdated=0;

	String sql = "UPDATE USERS SET USERDEPOSIT=USERDEPOSIT +'"+trans+"' WHERE USEREMAIL='"+email1+"'";

	try(Connection con = ConnectionUtil.HardCodedConnection();
	Statement ps = con.createStatement()){
	
	UserUpdated = ps.executeUpdate(sql);
	System.out.println("Thank You! Have a Great Day.");
	
	}catch(Exception e) {
	System.out.println("Error: "+e.getMessage());
	}


	return UserUpdated;
	}
	
	public int updateDepartment(String email, double deposit) {
		User uRef = null;
		int UserUpdated=0;
		
		String sql = "UPDATE USERS SET USERDEPOSIT=? WHERE USEREMAIL=?";
		
		try(Connection con = ConnectionUtil.HardCodedConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			ps.setDouble(1, deposit);
			ps.setString(2, email);
			
			UserUpdated = ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		
		return UserUpdated;
	}
	
	public int AfterupdateDepartment(double balance, String email) {
		User uRef = null;
		int UserUpdated=0;
		
		String sql = "UPDATE USERS SET USERDEPOSIT=? WHERE USEREMAIL=?";
		
		try(Connection con = ConnectionUtil.HardCodedConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			ps.setDouble(1, balance);
			ps.setString(2, email);
			
			UserUpdated = ps.executeUpdate();
			
		}catch(Exception e) {
			
			System.out.println("Error: "+e.getMessage());
		}
		
		
		return UserUpdated;
	}
	
	public int getBalance(String Email) {
		User uRef = null;
		int i = 0;
		String sql = "select USERDEPOSIT FROM USERS WHERE USEREMAIL=?";
		try(Connection con = ConnectionUtil.HardCodedConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setString(1, uRef.getEmail());
			
			i = ps.executeUpdate();
			
		}catch(Exception e){
			
			System.out.println("Error: "+e.getMessage());
		}
		return i;
	}

	public int deleteDepartment(int id) {
		int Deleteduser =0;
		
		String sql = "DELETE FROM USERS WHERE USERID=?";
		
		
		try(Connection con = ConnectionUtil.HardCodedConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			Deleteduser = ps.executeUpdate();
	
		}catch(Exception e ) {
			System.out.println("Error : "+e.getMessage());
		}
		return Deleteduser;
	}

}
