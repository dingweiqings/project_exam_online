package gao.java;
import java.sql.*;
public class DBconn {
	private Connection conn=null;
	public Connection getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_exam?useUnicode=true&characterEncoding=utf-8","root","root");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
