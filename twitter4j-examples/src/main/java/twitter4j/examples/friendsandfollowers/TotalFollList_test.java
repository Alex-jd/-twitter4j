package twitter4j.examples.friendsandfollowers;

public class TotalFollList_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long userID = 2529767186L;
		String userName = "test";

		GetFromDB testGet = new GetFromDB();
		System.out.println(testGet.getFollowersByID(userID));
		System.out.println(testGet.isUserID(userID));
		testGet.getVisited();
		testGet.closeConnect();

		QueueAtDB testQueue = new QueueAtDB();
		testQueue.addToQueueDB(userID);
		System.out.println(testQueue.getQueueDB());
		testQueue.delFromQueueDB(userID);
		System.out.println(testQueue.getQueueDB());
		testQueue.closeConnect();

		/*
		 * ListFollowersAndFriends list = new ListFollowersAndFriends(userID);
		 * 
		 * System.out.println("userName " + list.getName()); System.out.println(
		 * "followers " + list.getEdgesPostDBString("followers"));
		 * System.out.println("friends " +
		 * list.getEdgesPostDBString("friends"));
		 */

		/*
		 * Connection c = null; Statement stmt = null; try {
		 * Class.forName("org.postgresql.Driver"); c =
		 * DriverManager.getConnection(
		 * "jdbc:postgresql://10.98.137.19:5432/capstone", "alex_jd",
		 * "Nheg{jvzrf"); c.setAutoCommit(false); System.out.println(
		 * "Opened database successfully"); stmt = c.createStatement();
		 * 
		 * String sql = "insert into \"UserGraph\".\"userTable\" values ('" +
		 * list.getName() + "'," + userID + ",'{" +
		 * list.getEdgesPostDBString("followers") + "}','{" +
		 * list.getEdgesPostDBString("friends") + "}');"; // System.out.println(
		 * "sql request " + sql); stmt.executeUpdate(sql);
		 * 
		 * stmt.close(); c.commit(); c.close(); System.out.println(
		 * "Write to DB is success.");
		 * 
		 * } catch (Exception e) { e.printStackTrace();
		 * System.err.println(e.getClass().getName() + ": " + e.getMessage());
		 * System.exit(0); }
		 */
	}

}
