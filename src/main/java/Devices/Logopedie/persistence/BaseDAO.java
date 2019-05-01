package Devices.Logopedie.persistence;

import java.sql.*;  
public class BaseDAO{  
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   String url = "jdbc:mysql://localhost:3306/zinnig?serverTimezone=CET";
		   String name = "root";
		   String password = "root";

		   Connection con = DriverManager.getConnection(url, name, password);
		   return con;
		 }
}  