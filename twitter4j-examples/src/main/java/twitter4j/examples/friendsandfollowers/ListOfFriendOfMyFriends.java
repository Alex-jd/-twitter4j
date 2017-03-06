package twitter4j.examples.friendsandfollowers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import twitter4j.*;

public class ListOfFriendOfMyFriends {
	
	public LinkedList<Long> getTotalFollowers(long user) {
		try {
			IDs idfoll;
			Twitter tw = new TwitterFactory().getInstance();
			TotalFollowersList result= new TotalFollowersList();
			User u = tw.showUser(user);//TODO trova il modo di elminare questa richiesta
			long cursor=-1;
			if(!u.isProtected()) {
			do {	
				//idfoll = tw.getFollowersIDs(user, cursor, 5);
				idfoll = tw.getFollowersIDs(user, cursor);
				List<User> users=tw.getFriendsList(user, cursor);
				System.out.println(users);
				System.out.println("Yes, I'm here");
				for (Long edge : idfoll.getIDs())	{
					//System.out.println("IDs " + edge);
					result.addFollower(edge);
					}	
				cursor = idfoll.getNextCursor();
				}
			while(idfoll.hasNext());
			return result.getFollowers();
			}	
			else System.out.println(user+" è protetto.");
			return null;
		}
		catch (TwitterException e) 
			{	
				e.printStackTrace();
				return null;
			}
	}
	public class TotalFollowersList {
		private LinkedList<Long> followers;
		//public Iterator it;
		
		public TotalFollowersList(LinkedList<Long> followers) {
			super();
			this.followers = followers;
		}
		
		public TotalFollowersList() {
			super();
			this.followers= new LinkedList<Long>();
		}	
		
		/*public static followersList clone(followersList list) {	
			followersList clone= new followersList();
			Long l;
			clone.followers= (LinkedList<Long>) list.followers.clone();
			return clone;
		}*/
		
		public void addFollower(Long edge)	{
			this.followers.addLast(edge);
		}
		
		public LinkedList<Long> getFollowers() {
			return followers;
		}
	}
	

}
