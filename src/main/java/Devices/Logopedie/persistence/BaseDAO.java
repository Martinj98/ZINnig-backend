package Devices.Logopedie.persistence;

import java.sql.*;  
public class BaseDAO{  
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		   Class.forName("com.mysql.jdbc.Driver");
		   String url = "jdbc:mysql://localhost:3306/zinnig";
		   String name = "root";
		   String password = "";

		   Connection con = DriverManager.getConnection(url, name, password);
		   return con;
		 }
}  