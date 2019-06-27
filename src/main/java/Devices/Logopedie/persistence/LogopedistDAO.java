package	Devices.Logopedie.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import Devices.Logopedie.model.Logopedist;

public class LogopedistDAO extends BaseDAO {

	private List<Logopedist> selectLogopedist(String query) {
		List<Logopedist> results = new ArrayList<Logopedist>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
                int id = dbResultSet.getInt("id");
                int praktijk_id = dbResultSet.getInt("praktijk_id");
                String email = dbResultSet.getString("email");
                String password = dbResultSet.getString("password");
                String username = dbResultSet.getString("username");
                int phonenumber = dbResultSet.getInt("phonenumber");

				Logopedist x = new Logopedist(id, praktijk_id, email, password, username, phonenumber);
				results.add(x);
			}
		} catch (SQLException | ClassNotFoundException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public List<Logopedist> findAll() {
		return selectLogopedist("SELECT * from logopedist");
	}

	public int findId(String username) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT id FROM logopedist WHERE username = '" + username + "'";
			ResultSet dbResultSet = stmt.executeQuery(query);

			if(dbResultSet.next()){
				int dbId = dbResultSet.getInt("id");
				return dbId;
			} else {
				return 0;
			}
		} catch (SQLException | ClassNotFoundException sqle ) {
			sqle.printStackTrace();
			System.out.println("A unknown person tried to log in");
			return 0;
		}
	}

	public List<Logopedist> findLogopedist(int id) {
		return selectLogopedist("SELECT * FROM logopedist WHERE id = " + id);
	}

	public String login(String username, String password) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT username, password FROM logopedist WHERE username = '" + username + "'";
			ResultSet dbResultSet = stmt.executeQuery(query);

			if(dbResultSet.next()){
				String dbUsername = dbResultSet.getString("username");
				String dbPassword = dbResultSet.getString("password");

				if (dbPassword.equals(password)) {
					System.out.println("Authorisation complete");
					System.out.println(dbUsername + " is now logged in");

					Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

					String jws = Jwts.builder().setSubject(dbUsername).signWith(key).compact();

					System.out.println(jws);

					return jws;
				} else {
					System.out.println("Authorisation failed");
					System.out.println(dbUsername + " tried to log in");
					return "Authorisation failed";
				}
			} else {
				System.out.println("A unknown person tried to log in");
				return "Authorisation failed";
			}
		} catch (SQLException | ClassNotFoundException sqle ) {
			sqle.printStackTrace();
			System.out.println("A unknown person tried to log in");
			return "Authorisation failed";
		}
		
		

	}


}
