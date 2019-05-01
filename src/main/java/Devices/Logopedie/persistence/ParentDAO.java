package Devices.Logopedie.persistence;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Devices.Logopedie.model.Parent;

public class ParentDAO extends BaseDAO {
    private List<Parent> selectParent(String query) {
        List<Parent> results = new ArrayList<Parent>();
        try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                int id = dbResultSet.getInt("id");
                int Childid = dbResultSet.getInt("Childid");
                String email = dbResultSet.getString("email");
                String username = dbResultSet.getString("username");
                String password = dbResultSet.getString("password");
                int phonenumber = dbResultSet.getInt("phonenumber");
                int parent_notifications = dbResultSet.getInt("parent_notifications");

                Parent x = new Parent(id, Childid, email, username, password, phonenumber, parent_notifications);
                results.add(x);
            }
        } catch (SQLException | ClassNotFoundException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    public List<Parent> findAll() {
        return selectParent("SELECT * FROM parent");
    }

    public List<Parent> findChildParents(int Childid){
        return selectParent("SELECT * from parent WHERE Childid = " + Childid);
    }

    public boolean createParent(Parent parent) {
        try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            String query = "INSERT INTO parent VALUES(null, " + parent.getChildid() + ", '" + parent.getEmail() + "', '" + parent.getUsername() + "', '" + parent.getPassword() + "', " + parent.getPhonenumber() + ", " + parent.getParent_notifications() + ")";
            stmt.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException sqle) {
            sqle.printStackTrace();
        }
        return true;
    }

}