package twitter4j.examples.friendsandfollowers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class QueueAtDB extends PostDB {
	private String tableName = "\"currentQueue\"";
	private Statement stmt = null;

	public QueueAtDB() {
		this.stmt = getStatement();
	}

	public LinkedList<Long> getQueueDB() {
		LinkedList<Long> queueDB = null;
		statement();
		String sql = "SELECT " + tableName + ".\"userID\" FROM \"UserGraph\"." + tableName + ";";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			queueDB = new LinkedList<Long>();
			while (rs.next()) {
				Long userID = rs.getLong(1);
				// System.out.println(userID);
				queueDB.add(userID);
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
			stmt.executeUpdate(sql);
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
			stmt.executeUpdate(sql);
			super.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}
