package twitter4j.examples.friendsandfollowers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class TotalFollList_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long userID = 368379261L;
		String userName = "test";
		
		ListOfFriendOfMyFriends list = new ListOfFriendOfMyFriends();
		HashMap<String,LinkedList<Long>>userList = list.getTotalFollowers(userID);
		int numberOfUsers = 0;
		for (Long userId : userList.keySet() ) {
			System.out.println("userIds " + userId);
			numberOfUsers++;
		}
		System.out.println("TotalUsers " + numberOfUsers);
		
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	        Class.forName("org.postgresql.Driver");
	        c = DriverManager.getConnection("jdbc:postgresql://10.98.137.19:5432/capstone","alex_jd", "123");
	        c.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        stmt = c.createStatement();
	        
	        String sql = "insert into \"UserGraph\".\"testTable\" values ('" + userName + "',"+ userID +",'{15,389}','{45812,14534}');";

	        stmt.executeUpdate(sql); 
	        //rs.close(); 
	        stmt.close(); 
	        c.close(); 
		
	    }
	    catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }

	}

}
                       
                                                                     