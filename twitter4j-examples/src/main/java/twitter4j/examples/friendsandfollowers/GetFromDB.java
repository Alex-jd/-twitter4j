package twitter4j.examples.friendsandfollowers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;

public class GetFromDB extends PostDB {
	private String tableName = "\"userTable\"";
	private Statement stmt = null;

	public GetFromDB() {
		this.stmt = getStatement();
	}

	public TreeSet<Long> getVisited() {
		TreeSet<Long> visited = null;
		statement();
		String sql = "SELECT " + tableName + ".\"userID\"  FROM \"UserGraph\"." + tableName + ";";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Long userID = rs.getLong(1);
				System.out.println(userID);
				visited = new TreeSet<Long>();
				visited.add(userID);
				//System.out.println(userID);
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
			isUser = stmt.executeQuery(sql).next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUser;
	}

	public ArrayList<Long> getFollowersByID(Long userID) {
		ArrayList<Long> followers = null;
		statement();
		String sql = "SELECT * FROM \"UserGraph\"." + tableName + " where \"userID\" =" + userID + ";";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				java.sql.Array followersIDs = rs.getArray(3);
				Object[] arrayFollowersIDs = (Object[]) followersIDs.getArray();
				followers = new ArrayList<Long>();
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

	public void setFollowersAndFriends(String userName, Long userID, String followers, String friends) {
		statement();
		String sql = "insert into \"UserGraph\"." + tableName + " values ('" + userName + "', " + userID + ",'{"
				+ followers + "}','{" + friends + "}');";
		try {
			stmt.executeUpdate(sql);
			super.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
