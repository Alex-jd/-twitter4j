package twitter4j.examples.friendsandfollowers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class QueueAtDB extends PostDB {
	public String className = this.getClass().getName();
	private String tableName = "\"currentQueue\"";

	public QueueAtDB() {
		super.connectToDB();
	}

	public LinkedList<Long> getQueueDB() {
		LinkedList<Long> queueDB = null;
		statement();
		String sql = "SELECT " + tableName + ".\"userID\"  FROM \"UserGraph\"." + tableName + ";";
		try {
			ResultSet rs = super.stmt.executeQuery(sql);
			while (rs.next()) {
				Long userID = rs.getLong(1);
				queueDB = new LinkedList<Long>();
				queueDB.add(userID);
				System.out.println(userID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return queueDB;
	}

	public boolean addToQueueDB(Long userID) {
		try {
			String sql = "insert into \"UserGraph\"." + tableName + " values ('" + userID + "');";
			super.stmt.executeUpdate(sql);
			super.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean delFromQueueDB(Long userID) {
		try {
			String sql = "delete from \"UserGraph\"." + tableName + " where \"userID\"= '" + userID + "';";
			super.stmt.executeUpdate(sql);
			super.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}
