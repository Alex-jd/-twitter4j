package twitter4j.examples.friendsandfollowers;

import java.util.ArrayList;

import twitter4j.*;

public class TestGetFriends {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*User user = mTwitter.showUser(9999L);

		user.getStatusesCount() - returns the tweets count posted by user
		user.getFriendsCount() - followed users
		user.getFollowersCount() - followed users
		*/
		try {
            // get friends
			Twitter twitter = new TwitterFactory().getInstance();
			ArrayList<User> listFriends = new ArrayList<User>();
			ArrayList<User> listFollowers = new ArrayList<User>();
            long cursor = -1;
            PagableResponseList<User> pagableFollowings;
            do {
                pagableFollowings = twitter.getFriendsList(twitter.getId(), cursor);
                for (User user : pagableFollowings) {
                    listFriends.add(user); // ArrayList<User>
                    System.out.println("friends " + user);
                    System.out.println("followers " + user.getFollowersCount() );
                }
            } while ((cursor = pagableFollowings.getNextCursor()) != 0);
            /*
            // get followers
            cursor = -1;
            PagableResponseList<User> pagableFollowers;
            do {
                pagableFollowers = twitter.getFollowersList(twitter.getId(), cursor);
                for (User user : pagableFollowers) {
                    listFollowers.add(user); // ArrayList<User>
                    System.out.println("followers " + user);
                }
            } while ((cursor = pagableFollowers.getNextCursor()) != 0);
             */
        } catch (TwitterException e) {
            //printError(e);
        }

	}

}
