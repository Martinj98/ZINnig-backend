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
				String child_photo = dbResultSet.getString("child_photo");
				int munten = dbResultSet.getInt("munten");
				String huiswerk = dbResultSet.getString("huiswerk");

				Child x = new Child(id, logopedistid, username, password, email, phonenumber, child_notifications, child_photo, munten, huiswerk);
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
			String query = "INSERT INTO child VALUES(null, " + child.getLogopedistid() + ", '" + child.getUsername()
					+ "', '" + child.getPassword() + "', '" + child.getEmail() + "', " + child.getPhonenumber() + ", "
					+ child.getChild_notifications() + ", " + null + ", " + child.getMunten() + ", " + child.getHuiswerk() + ")";
			stmt.executeUpdate(query);

		} catch (SQLException | ClassNotFoundException sqle) {
			sqle.printStackTrace();
		}
		return true;
	}

	public boolean updatePhoto(int id, String photo) {
		

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			//byte[] decodedByte = Base64.getDecoder().decode(photo.getBytes());
			//Blob b = new SerialBlob(photo.getBytes());
			String query = "UPDATE child SET child_photo = '" + photo + "' WHERE id = " + id;
			stmt.executeUpdate(query); 
			
		} catch (SQLException | ClassNotFoundException | IllegalArgumentException sqle) {
			sqle.printStackTrace();
		}
		return true;
	}

	public String findPhoto(int id) {
		String results = null;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT child_photo FROM child WHERE id = " + id;
			ResultSet dbResult = stmt.executeQuery(query); 

			while (dbResult.next()) {
				String child_photo = dbResult.getString("child_photo");
				results = child_photo;
			}

			
			
		} catch (SQLException | ClassNotFoundException | IllegalArgumentException sqle) {
			sqle.printStackTrace();
		}

		return results;

	}

	public String login(String username, String password) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT username, password FROM child WHERE username = '" + username + "'";
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

	public int findId(String username) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT id FROM child WHERE username = '" + username + "'";
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

	public Boolean uploadScore(Score score) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM level_child WHERE Levelid = " + score.getLevelid() + " AND Childid = " + score.getChildid();
			ResultSet dbResultSet = stmt.executeQuery(query);

			if (dbResultSet.next()) {
				System.out.println("User updated their level score");
				try (Connection con2 = super.getConnection()) {
					Statement stmt2 = con2.createStatement();
					String query2 = "UPDATE level_child SET score_goed = '" + score.getScore_goed() + "', score_fout = '" + score.getScore_fout() + "', progress = " + score.getProgress() + " WHERE Levelid = " + score.getLevelid() + " AND Childid = " + score.getChildid();
					stmt2.executeUpdate(query2);
		
				} catch (SQLException | ClassNotFoundException sqle) {
					sqle.printStackTrace();
				}
			} else {
				System.out.println("User completed a new level");
				try (Connection con3 = super.getConnection()) {
					Statement stmt3 = con3.createStatement();
					String query3 = "INSERT INTO level_child VALUES (" + score.getLevelid() + "," + score.getChildid() + ", " + score.getLandid() + ", '" + score.getScore_goed() + "', '" + score.getScore_fout() + "', " + score.getProgress() + ")";
					stmt3.executeUpdate(query3);
		
				} catch (SQLException | ClassNotFoundException sqle) {
					sqle.printStackTrace();
				}
			}
		} catch (SQLException | ClassNotFoundException sqle ) {
			sqle.printStackTrace();
		} 
		return true;
		}

	public List<Score> selectVoortgang(int id) {
		List<Score> results = new ArrayList<Score>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM level_child WHERE Childid = " + id;
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int levelid = dbResultSet.getInt("Levelid");
				int childid = dbResultSet.getInt("Childid");
				int landid = dbResultSet.getInt("Landid");
				String score_goed = dbResultSet.getString("score_goed");
				String score_fout = dbResultSet.getString("score_fout");
				int progress = dbResultSet.getInt("progress");

				Score x = new Score(levelid, childid, landid, score_goed, score_fout, progress);
				results.add(x);
			}
		} catch (SQLException | ClassNotFoundException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public List<GameType> selectGametype(int id) {
		List<GameType> results = new ArrayList<GameType>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM level WHERE id = " + id;
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int result_id = dbResultSet.getInt("Game_typeid");
				try (Connection con2 = super.getConnection()) {
					Statement stmt2 = con2.createStatement();
					String query2 = "SELECT * FROM game_type WHERE id = " + result_id;
					ResultSet dbResultSet2 = stmt2.executeQuery(query2);

					while (dbResultSet2.next()) {
						int levelid = dbResultSet2.getInt("id");
						String name = dbResultSet2.getString("name");

						GameType g = new GameType(levelid, name);
						results.add(g);
					}

				} catch (SQLException | ClassNotFoundException sqle ) {
					sqle.printStackTrace();
				}
			}
		} catch (SQLException | ClassNotFoundException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public List<Land> selectLevelLand(int id) {
		List<Land> results = new ArrayList<Land>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM land WHERE id = " + id;
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int landid = dbResultSet.getInt("id");
				String category = dbResultSet.getString("category");
				String name = dbResultSet.getString("name");
				String description = dbResultSet.getString("description");

				Land e = new Land(landid, category, name, description);
				results.add(e);
			}
		} catch (SQLException | ClassNotFoundException sqle ) {
			sqle.printStackTrace();
		}
		return results;		
	}

	public List<Level> selectLevel(int id) {
		List<Level> results = new ArrayList<Level>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM level WHERE id = " + id;
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int levelid = dbResultSet.getInt("id");
				int game_typeid = dbResultSet.getInt("Game_typeid");
				int niveau = dbResultSet.getInt("niveau");

				Level e = new Level(levelid, game_typeid, niveau);
				results.add(e);
			}
		} catch (SQLException | ClassNotFoundException sqle ) {
			sqle.printStackTrace();
		}
		return results;	
	}

	public List<Integer> selectLevelWords(int id) {
		List<Integer> results = new ArrayList<Integer>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM level_has_woorden WHERE level_id = " + id;
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int woordid = dbResultSet.getInt("Woorden_id");
				results.add(woordid);
			}
		} catch (SQLException | ClassNotFoundException sqle ) {
			sqle.printStackTrace();
		}
		return results;
	}

	public List<Woord> selectGoodWords(List<Integer> idlist) {
		List<Woord> results = new ArrayList<Woord>();
		for (Integer id : idlist) {
			try (Connection con = super.getConnection()) {
				Statement stmt = con.createStatement();
				String query = "SELECT * FROM woorden WHERE id = " + id;
				ResultSet dbResultSet = stmt.executeQuery(query);
	
				while (dbResultSet.next()) {
					int woordid = dbResultSet.getInt("id");
					String woord = dbResultSet.getString("woord");
					String type = dbResultSet.getString("type");
					String moeilijkheidsgraad = dbResultSet.getString("moeilijkheidsgraad");
	
					Woord w = new Woord(woordid, woord, type, moeilijkheidsgraad);
					results.add(w);
				}
	
			} catch (SQLException | ClassNotFoundException sqle ) {
				sqle.printStackTrace();
			}
		}
		return results;
	}

	public boolean updateMunten(int count, int id) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM child WHERE id = " + id;
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int munten = dbResultSet.getInt("munten") + count;
				try (Connection con2 = super.getConnection()) {
					Statement stmt2 = con2.createStatement();
					String query2 = "UPDATE child SET munten = " + munten + " WHERE id = " + id;
					stmt2.executeUpdate(query2);
				} catch (SQLException | ClassNotFoundException sqle ) {
					sqle.printStackTrace();
				}
			}
		} catch (SQLException | ClassNotFoundException sqle ) {
			sqle.printStackTrace();
		}
		return true;
	}

	}
