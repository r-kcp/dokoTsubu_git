package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/dokotsubu_db?useSSL=false&characterEncoding=UTF-8";
	private final String DB_USER = "root";
	private final String DB_PASS = "0629";
	
	static {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	//つぶやき一覧を取得
	public List<Mutter> findAll(){
		
		List<Mutter> list = new ArrayList<>();
		
		
		//DBに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT id, user_name, text, create_at FROM mutter ORDER BY id DESC";
			//SQL文をDBに届けるインスタンスを取得
			PreparedStatement ps = conn.prepareStatement(sql);
			//ResultSetインスタンスにSQL文の結果が格納される
			ResultSet rs = ps.executeQuery();
			
			//結果表に格納されたレコードを取得
			while(rs.next()) {
				Mutter m = new Mutter(
					rs.getInt("id"),
					rs.getString("user_name"),
					rs.getString("text"),
					rs.getTimestamp("create_at")
				);
				list.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	public boolean create(Mutter mutter) {
		
		 
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "INSERT INTO mutter(user_name, text) VALUES (?, ?)"; //プレイスホルダーを入れておく
			//SQL文をDBに届けるインスタンスを取得
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//プレイスホルダーの1つめと2つめにそれぞれ値を挿入
			ps.setString(1, mutter.getUserName());
			ps.setString(2, mutter.getText());
			
			//SQL実行
			ps.executeUpdate();
			
			return true;			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
