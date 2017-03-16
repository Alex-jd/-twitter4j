package twitter4j.examples.friendsandfollowers;

public class TotalFollList_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long userID = 2529767186L;
		// Long userID = 809511570829152256L;
		String userName = "test";

		BFSTwitter testFirst = new BFSTwitter(userID, 10);
		testFirst.startBFS();

		GetFromDB testGet = new GetFromDB();
		System.out.println("GetFollwersByID " + testGet.getFollowersByID(userID));
		System.out.println("Get user ID " + testGet.isUserID(userID));
		System.out.println("getVisited " + testGet.getVisited());
		testGet.closeConnect();
		// System.out.println(testGet.getState());

		QueueAtDB testQueue = new QueueAtDB();
		// testQueue.addToQueueDB(userID);
		// System.out.println(testQueue.getState());
		System.out.println("GetQueueDB " + testQueue.getQueueDB());
		// System.out.println(testQueue.getState());
		// testQueue.delFromQueueDB(userID);
		// System.out.println(testQueue.getState());
		// System.out.println(testQueue.getQueueDB());
		// System.out.println(testQueue.getState());
		testQueue.closeConnect();
		// System.out.println(testQueue.getState());

	}

}
