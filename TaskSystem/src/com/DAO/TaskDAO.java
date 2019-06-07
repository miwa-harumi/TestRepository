package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DTO.TaskDTO;
import comon.Connect;
import com.fasterxml.jackson.databind.JsonNode;

public class TaskDAO {

	//登録SQL
	public int insert(JsonNode data) throws SQLException{
		
		//コネクション接続コンストラクタをインスタンスする
		Connect con = new Connect();
		Connection connection = con.getData();
		
		//insertの発行
		String sql = "INSERT INTO task values(0, ?, ?, CURRENT_TIMESTAMP)";
		
		// sql文を実行するためのオブジェクト生成
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		//入力したタイトルを挿入させる(内容は初期時未入力)
		stmt.setString(1, data.get("title").asText());
		stmt.setString(2, data.get("content").asText());
		
		// sql文の実行結果を取得（データベースからの値）		
		int rse = stmt.executeUpdate();
		
		//開放
		con.close();
		stmt.close();
		
		return rse;
	}

	//タスク一覧SQL
	public List<TaskDTO> findAll(JsonNode data) {
		// DTOクラスのインスタンス格納用
		List<TaskDTO> Task_DTO = new ArrayList<>();

		// データベースへの接続
		try{			
			//コネクション接続コンストラクタ
			Connect con = new Connect();
			Connection connection = con.getData();
			
			//sql文を実行するためのオブジェクト生成
			Statement stmt = connection.createStatement();

			//SELECT文の発行
			String sql = "SELECT * FROM task ORDER BY time DESC";

			//sql文の実行結果を取得（データベースからの値）
			ResultSet rset = stmt.executeQuery(sql);

			//データベースから取得した値がある間、
			while (rset.next()) 
			{
				// TaskDTOクラスのインスタンスを生成
				TaskDTO dto = new TaskDTO();

				// idをセット
				dto.setId(rset.getInt("id"));

				// titleをセット
				dto.setTitle(rset.getString("title"));

				// contentをセット
				dto.setContent(rset.getString("content"));

				// timeをセット
				dto.setTime(rset.getString("time"));

				// インスタンスを格納
				Task_DTO.add(dto);
			}			
			//開放する
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return Task_DTO;
	}
	
	//削除SQL
	public int delete(JsonNode data) throws SQLException
	{		
		//コネクション接続コンストラクタ
		Connect con = new Connect();
		Connection connection = con.getData();
		
		//deleteの発行
		String sql = "DELETE FROM task WHERE id = ?";
		
		// sql文を実行するためのオブジェクト生成
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		//削除するデータのID
		stmt.setString(1, data.get("id").asText());
		
		// sql文の実行結果を取得（データベースからの値）		
		int rse = stmt.executeUpdate();
		
		//開放する
		con.close();
		stmt.close();
		
		return rse;
	}

	//編集SQL
	public int update(JsonNode data) throws SQLException{
		
		//コネクション接続コンストラクタ
		Connect con = new Connect();
		Connection connection = con.getData();
		
		//updateの発行
		String sql = "UPDATE task SET title = ? , content = ? WHERE id = ?";
		
		// sql文を実行するためのオブジェクト生成
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		//入力したタイトルと内容にする
		stmt.setString(1, data.get("title").asText());
		stmt.setString(2, data.get("content").asText());
		
		//編集するデータのID
		stmt.setString(3, data.get("id").asText());
		
		// sql文の実行結果を取得（データベースからの値）		
		int rse = stmt.executeUpdate();
		
		//開放する
		con.close();
		stmt.close();
		
		return rse;
	}
}
