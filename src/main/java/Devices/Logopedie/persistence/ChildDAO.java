package Devices.Logopedie.persistence;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Devices.Logopedie.model.Child;

public class ChildDAO extends BaseDAO {

	private List<Child> selectChild(String query) {
		List<Child> results = new ArrayList<Child>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
                int id = dbResultSet.getInt("id");
                int logopedistid = dbResultSet.getInt("logopedistid");
                String username = dbResultSet.getString("username");
                String password = dbResultSet.getString("password");
                String email = dbResultSet.getString("email");
                int phonenumber = dbResultSet.getInt("phonenumber");
                int child_notifications = dbResultSet.getInt("child_notifications");

				Child x = new Child(id, logopedistid, username, password, email, phonenumber, child_notifications);
				results.add(x);
			}
		} catch (SQLException | ClassNotFoundException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public List<Child> findAll() {
		return selectChild("SELECT * from child");
	}

	public List<Child> findOwn(int id) {
		return selectChild("SELECT * FROM child WHERE logopedistid = " + id);
	}

	public List<Child> selectSpecificChild(int id) {
		return selectChild("SELECT * from child WHERE id = " + id);
	}

	public boolean createChild(Child child) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "INSERT INTO child VALUES(null, " + child.getLogopedistid() + ", '" + child.getUsername() + "', '" + child.getPassword() + "', '" + child.getEmail() + "', " + child.getPhonenumber() + ", " + child.getChild_notifications() + ")";
			stmt.executeUpdate(query); 
			
		} catch (SQLException | ClassNotFoundException sqle ) {
			sqle.printStackTrace();
		}
		return true;
	}


}
