package Devices.Logopedie.persistence;

import java.sql.Array;
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

// Models
import Devices.Logopedie.model.Woord;
import Devices.Logopedie.model.Child;
import Devices.Logopedie.model.GameType;
import Devices.Logopedie.model.Land;
import Devices.Logopedie.model.Level;
import Devices.Logopedie.model.Score;

public class WoordDAO extends BaseDAO {

	private List<Woord> selectWoord(String query) {
		List<Woord> results = new ArrayList<Woord>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				String woord = dbResultSet.getString("woord");
				String type = dbResultSet.getString("type");
				String moeilijkheidsgraad = dbResultSet.getString("moeilijkheidsgraad");

				Woord x = new Woord(id, woord, type, moeilijkheidsgraad);
				results.add(x);
			}
		} catch (SQLException | ClassNotFoundException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public List<Woord> findAll() {
		return selectWoord("SELECT * from woorden");
	}

	}
