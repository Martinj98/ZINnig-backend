package	Devices.Logopedie.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Devices.Logopedie.model.Country;

public class CountryDAO extends BaseDAO {

	private List<Country> selectCountry(String query) {
		List<Country> results = new ArrayList<Country>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				String name = dbResultSet.getString("name");

				Country x = new Country(id, name);
				results.add(x);
			}
		} catch (SQLException | ClassNotFoundException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public List<Country> findAll() {
		return selectCountry("SELECT * from countries");
	}


}
