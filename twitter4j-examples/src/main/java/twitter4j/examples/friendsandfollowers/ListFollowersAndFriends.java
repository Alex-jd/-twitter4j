package twitter4j.examples.friendsandfollowers;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class ListFollowersAndFriends extends GetGraph {

	public ListFollowersAndFriends(Long userID) {
		this.userID = userID;
		this.followers = new LinkedList<Long>();
		this.friends = new LinkedList<Long>();
		getTotalFollowers();
	}

	public void getTotalFollowers() {
		try {
			IDs idfoll;
			Twitter tw = new TwitterFactory().getInstance();
			User currentUser = tw.showUser(userID);// main string
			userName = currentUser.getName();
			int timeWait = 62;
			// System.out.println("user " + u.getName());
			long cursor = 0;
			if (!currentUser.isProtected()) {
				cursor = -1;
				do {
					idfoll = tw.getFollowersIDs(userID, cursor);
					System.out.println("Waiting for time " + timeWait);
					TimeUnit.SECONDS.sleep(timeWait);
					for (Long edge : idfoll.getIDs()) {
						addFollower(edge);
					}
					cursor = idfoll.getNextCursor();
					System.out.println("cursros " + cursor);
				} while (idfoll.hasNext());
				cursor = -1;
				do {
					idfoll = tw.getFriendsIDs(userID, cursor);
					System.out.println("Waiting for time " + timeWait);
					TimeUnit.SECONDS.sleep(timeWait);
					for (Long edge : idfoll.getIDs()) {
						addFriend(edge);
					}
					cursor = idfoll.getNextCursor();
					System.out.println("cursros " + cursor);
				} while (idfoll.hasNext());
			} else
				System.out.println(userID + " is protected");
		} catch (TwitterException e) {
			e.printStackTrace();
		} catch (InterruptedException ie) {
		}
	}

}
