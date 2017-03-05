package twitter4j.examples.friendsandfollowers;

import java.util.LinkedList;

public class TotalFollList_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ListOfFriendOfMyFriends list = new ListOfFriendOfMyFriends();
		LinkedList<Long> userList = list.getTotalFollowers(368379261L);
		int numberOfUsers = 0;
		
		for (Long userId : userList ) {
			System.out.println("userIds " + userId);
			numberOfUsers++;
		}
		
		System.out.println("TotalUsers " + numberOfUsers);

	}

}
