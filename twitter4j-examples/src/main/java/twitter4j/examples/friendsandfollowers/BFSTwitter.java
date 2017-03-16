package twitter4j.examples.friendsandfollowers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

public class BFSTwitter {
	private int depthOfSearch = 0;
	private LinkedList<Long> exploreQueue;
	private TreeSet<Long> visited;

	private QueueAtDB queueDB;
	private GetFromDB getDB;

	public BFSTwitter(Long startNode, int depthOfSearch) {
		this.depthOfSearch = depthOfSearch;
		queueDB = new QueueAtDB();
		getDB = new GetFromDB();
		if (startNode == 0) {
			exploreQueue = queueDB.getQueueDB();
		} else {
			exploreQueue = new LinkedList<Long>();
			exploreQueue.add(startNode);
		}
		if (getDB.getVisited() == null) {
			visited = new TreeSet<Long>();
		} else {
			visited = getDB.getVisited();
		}

	}

	public void startBFS() {
		while (visited.size() < depthOfSearch) {
			Long currentNode = exploreQueue.remove();// get the curretUser from
														// queue
			System.out.println("Get the currenNode " + currentNode);
			queueDB.delFromQueueDB(currentNode);// Delete currenUser from queue
												// DB
			visited.add(currentNode);// add currentUser to visited
										// and to queue DB
			ListFollowersAndFriends list = new ListFollowersAndFriends(currentNode);
			getDB.setFollowersAndFriends(list.getName(), currentNode, list.getEdgesPostDBString("followers"),
					list.getEdgesPostDBString("friends"));
			ArrayList<Long> followers = getDB.getFollowersByID(currentNode);
			int arraySize = followers.size();
			int count = 0;
			if (arraySize >= 11) {
				while (count < 10) {
					Long newNode = getAndCheckRandomFollowers(followers, arraySize);
					System.out.println("Get the newNode (arryaSize >= 11) " + newNode);
					exploreQueue.add(newNode);
					queueDB.addToQueueDB(newNode);
					count++;
				}
			} else
				while (count < arraySize) {
				Long newNode = followers.get(count);
				System.out.println("Get the newNode (arraySize < 11) " + newNode);
					if (!(visited.contains(newNode) && exploreQueue.contains(newNode))) {
						exploreQueue.add(newNode);
						queueDB.addToQueueDB(newNode);
					}
				count++;
			}

		}

	}

	private Long getAndCheckRandomFollowers(ArrayList<Long> followers, int arraySize) {
		Long followerID = followers.get(getRandomIndex(arraySize - 1));
		if (visited.contains(followerID) || exploreQueue.contains(followerID)) {
			followerID = followers.get(getRandomIndex(arraySize - 1));
		}
		return followerID;
	}

	private int getRandomIndex(int streamSize) {
		Random r = new Random();
		return r.nextInt(streamSize);
	}

}
