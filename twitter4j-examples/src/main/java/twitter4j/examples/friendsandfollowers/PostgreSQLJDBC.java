package twitter4j.examples.friendsandfollowers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public class PostgreSQLJDBC {
	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://10.98.137.19:5432/capstone", "alex_jd", "123");

			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			/*
			 * String sql =
			 * "INSERT INTO TestTable (ID,NAME,AGE,ADDRESS,SALARY) " +
			 * "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
			 * stmt.executeUpdate(sql);
			 */

			/*
			 * String sql =
			 * "insert into \"UserGraph\".\"testTable\" values ('user1', 1234,'{15,389}','{45812,14534}');"
			 * ; stmt.executeUpdate(sql);
			 */

			/*
			 * String sql =
			 * "UPDATE \"UserGraph\".\"testTable\" set \"followersIDs\"[5:8] = \'{10220,652,4522,22}\'WHERE \"userID\" = 123;"
			 * ; stmt.executeUpdate(sql); //System.out.println("Yes, I'm here");
			 * c.commit();
			 */

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM \"UserGraph\".\"userTable\" where \"userID\" = 2529767186;");
			while (rs.next()) {
				System.out.println(rs.getString("userName"));
				System.out.println(rs.getLong("userID"));
				java.sql.Array followersIDs = rs.getArray(3);// Column number 3,
																// type
																// sql.Array
				System.out.println(followersIDs);
				Object[] str_followersIDs = (Object[]) followersIDs.getArray();
				// System.out.println(str_followersIDs);
				// Set<Long> followersSet = new HashSet<Long>();
				Set<Long> followersSet = new TreeSet<Long>();
				for (int i = 0; i < str_followersIDs.length; i++) {
					Long temp = (Long) str_followersIDs[i];
					followersSet.add((Long) str_followersIDs[i]);
					// System.out.println(str_followersIDs[i]);
					System.out.println(temp);
				}
				System.out.println(followersSet);

			}

			/*
			 * sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
			 * "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
			 * stmt.executeUpdate(sql);
			 */
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
}
