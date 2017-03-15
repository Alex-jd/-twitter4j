package twitter4j.examples.friendsandfollowers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

public class GetFromDB extends PostDB {
	public String className = "GetFromDB";
	private String tableName = "\"userTable\"";

	public GetFromDB() {
		super.connectToDB();
	}

	public TreeSet<Long> getVisited() {
		TreeSet<Long> visited = null;
		statement();
		String sql = "SELECT " + tableName + ".\"userID\"  FROM \"UserGraph\"." + tableName + ";";
		try {
			ResultSet rs = super.stmt.executeQuery(sql);
			while (rs.next()) {
				Long userID = rs.getLong(1);
				visited = new TreeSet<Long>();
				visited.add(userID);
				System.out.println(userID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return visited;
	}

	public boolean isUserID(Long userID) {
		boolean isUser = false;
		statement();
		String sql = "SELECT " + tableName + ".\"userID\"  FROM \"UserGraph\"." + tableName + " where \"userID\" ="
				+ userID + ";";
		try {
			isUser = super.stmt.executeQuery(sql).next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUser;
	}

	public TreeSet<Long> getFollowersByID(Long userID) {
		TreeSet<Long> followers = null;
		statement();
		String sql = "SELECT * FROM \"UserGraph\"." + tableName + " where \"userID\" =" + userID + ";";
		try {
			ResultSet rs = super.stmt.executeQuery(sql);
			while (rs.next()) {
				java.sql.Array followersIDs = rs.getArray(3);
				Object[] arrayFollowersIDs = (Object[]) followersIDs.getArray();
				followers = new TreeSet<Long>();
				for (int i = 0; i < arrayFollowersIDs.length; i++) {
					followers.add((Long) arrayFollowersIDs[i]);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return followers;
	}

}
