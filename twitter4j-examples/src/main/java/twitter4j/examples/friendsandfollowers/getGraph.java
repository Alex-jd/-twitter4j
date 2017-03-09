package twitter4j.examples.friendsandfollowers;

import java.util.LinkedList;

public abstract class getGraph {

	public Long userID = 0L;
	public LinkedList<Long> followers;
	public LinkedList<Long> friends;
	public String userName = "";

	public String getEdgesPostDBString(String trigger) {
		if (trigger == "followers") {
			return convertLnkToPostDBString(followers);
		}
		return convertLnkToPostDBString(friends);
	}

	private String convertLnkToPostDBString(LinkedList<Long> lnkList) {
		String postgresStr = "";
		if (!lnkList.isEmpty()) {
			for (Long i : lnkList) {
				postgresStr = postgresStr + "," + i;
			}
			postgresStr = postgresStr.substring(1); // get the string where
													// first simbol is not a
													// comma.
		}
		return postgresStr;
	}

	public void addFollower(Long edge) {
		this.followers.addLast(edge);
	}

	public void addFriend(Long edge) {
		this.friends.addLast(edge);
	}

	public String getName() {
		return userName;
	}

}
