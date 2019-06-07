package comon;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

//DBコネクト用
public class Connect
{
	private String url = "jdbc:mysql://localhost/task?useUnicode=true&characterEncoding=utf8";
	private String user = "root";
	private String password = "harukun4423";
	private Connection con;
	
	public Connect() throws SQLException
	{
		// JDBCドライバ読み込み
		try {
			// PostgreSQLドライバの読み込み
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url,user,password);
			//sql文を実行するためのオブジェクト生成
		} catch (Exception e) {			
		}		
	}
	
	public Connection close() throws SQLException
	{
		con.close();
		return this.con;
	}
	
	public Connection getData()
	{
		return this.con;
	}

}