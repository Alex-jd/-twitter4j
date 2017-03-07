package twitter4j.examples.friendsandfollowers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;


import twitter4j.*;

public class ListOfFriendOfMyFriends {
	
	public HashMap<String,LinkedList<Long>> getTotalFollowers(long user) {
		try {
			IDs idfoll;
			Twitter tw = new TwitterFactory().getInstance();
			User u = tw.showUser(user);//TODO trova il modo di elminare questa richiesta
			TotalFollowersList result= new TotalFollowersList(u.getName());
			//System.out.println("user " + u.getName()); 
			long cursor=-1;
			if(!u.isProtected()) {
				do {	
					//idfoll = tw.getFollowersIDs(user);
					idfoll = tw.getFollowersIDs(user, cursor);
					System.out.println("Yes, I'm here");
					//System.out.println("Wait... 60sec");
					TimeUnit.SECONDS.sleep(1); 
					for (Long edge : idfoll.getIDs())	{
						//System.out.println("IDs " + edge);
						result.addFollower(edge);
					}	
					cursor = idfoll.getNextCursor();
					System.out.println("cursros " + cursor);
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
		catch (InterruptedException ie) {
			return null;
		}
	}
	public class TotalFollowersList {
		private LinkedList<Long> followers;
		private LinkedList<Long> friends;
		private HashMap<String,LinkedList<Long>> follAndFriends;
		private String userName = "";
		//public Iterator it;
		
		/*public TotalFollowersList(String userName) {
			//super();
			this.followers = followers;
		}*/
		
		public TotalFollowersList(String userName) {
			//super();
			this.userName = userName;
			this.followers = new LinkedList<Long>();
			this.friends = new LinkedList<Long>();
			this.follAndFriends = new HashMap<String,LinkedList<Long>>();
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
		
		public void addFriend(Long edge)	{
			this.friends.addLast(edge);
		}
		
		public HashMap<String,LinkedList<Long>> getFollowers() {
			follAndFriends.put(userName + "_fl", followers);
			follAndFriends.put(userName + "_fr", friends);
			return follAndFriends;
		}
	}
	

}
