package com.esmile.model.dao;

import com.esmile.model.dto.FriendDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class FriendDao {

	// JdbcTemplateの変数宣言
	private JdbcTemplate jdbcTemplate;

	// JdbcTemplateのsetter
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// フレンドとのチャットを表示する
	public List<Map<String, Object>> chat_list(JsonNode data, FriendDto dto) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select * from user LEFT JOIN friend ON friend.friend_id = user.user_id WHERE friend.user_id = "
						+ data.get("user_id").asInt());
		for (Map<String, Object> map : list) {
			if (map.get("user_id") == null) {
				break;
			}
			System.out.println(map.get("user_id").toString());
			System.out.println(map.get("friend_id").toString());
			System.out.println(map.get("status").toString());
			System.out.println(map.get("last_name").toString());
			System.out.println(map.get("first_name").toString());
		}
		return list;
	}

	// フレンドのリストAPI(申請中のものも表示される)
	public List<Map<String, Object>> friend_list(JsonNode data, FriendDto dto) {
		// userとfriendデータベースをjoinし、friend.statusが0(友達)のみを表示する
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select * from user LEFT JOIN friend ON friend.friend_id = user.user_id WHERE friend.user_id = "
						+ data.get("user_id").asInt() + " AND friend.status = '0'");
		for (Map<String, Object> map : list) {
			if (map.get("user_id") == null) {
				break;
			}
			System.out.println(map.get("user_id").toString());
			System.out.println(map.get("friend_id").toString());
			System.out.println(map.get("status").toString());
			System.out.println(map.get("last_name").toString());
			System.out.println(map.get("first_name").toString());
		}
		return list;
	}

	// フレンド申請を送る処理
	public int send(FriendDto dto) {
		try {
			// 送った方は１(reqestで送られてきた1)、送られた方は2が入るようになる
			jdbcTemplate.update("insert into friend(user_id,friend_id,status)" + "values(?,?,?),(?,?,'2')",
					dto.getFriend_User(), dto.getFriend_Friend(), dto.getFriend_Status(), dto.getFriend_Friend(),
					dto.getFriend_User());
		} catch (ArithmeticException e) {
			return 1;
		}
		return 0;
	}

	// フレンドの許可/不許可API
	public int request(JsonNode data, FriendDto dto) {
		try {
			if (dto.getFriend_Status() == 0) {
				//送られてきたstatusが0(許可)の場合両方のユーザのstatusを0(友達)にする
				String update = "update friend set status = " + dto.getFriend_Status()
						+ " WHERE user_id = ? AND friend_id = ?";
				jdbcTemplate.update(update, data.get("user_id").asInt(), data.get("friend_id").asInt());
				jdbcTemplate.update(update, data.get("friend_id").asInt(), data.get("user_id").asInt());
			} else if (dto.getFriend_Status() == 3) {
				//送られてきたstatusが3(拒否)の場合両方のfriendデータベースのレコードの削除
				String deny = "delete from friend WHERE user_id = ? AND friend_id = ?";
				jdbcTemplate.update(deny, data.get("user_id").asInt(), data.get("friend_id").asInt());
				jdbcTemplate.update(deny, data.get("friend_id").asInt(), data.get("user_id").asInt());
			}
		} catch (ArithmeticException e) {
			return 1;
		}
		return 0;
	}

	//申請を取りやめるAPI
	public int cancel(JsonNode data, FriendDto dto) throws ParseException {
		try {
			String delete = "delete from friend WHERE user_id = ? AND friend_id = ?";
			jdbcTemplate.update(delete, data.get("user_id").asInt(), data.get("friend_id").asInt());
			jdbcTemplate.update(delete, data.get("friend_id").asInt(), data.get("user_id").asInt());
		} catch (ArithmeticException e) {
			return 1;
		}
		return 0;

	}

	//フレンドが切れたときフレンドとのチャットを削除するAPI
	public int chat_delete(JsonNode data, FriendDto dto) throws SQLException {

		//user_idとfriend_idを比べ番号が若いものがuser_idになるようにソート
		int user_id = data.get("chat_one").asInt();
		int friend_id = data.get("chat_two").asInt();
		int sort = 0;
		if (user_id > friend_id) {
			sort = user_id;
			user_id = friend_id;
			friend_id = sort;
			sort = 0;
		}

		jdbcTemplate.update("delete FROM chat WHERE chat_one = "
						+ user_id + " AND chat_two = " + friend_id);		
		return 0;
	}

	//フレンドを切る処理
	public int delete(JsonNode data, FriendDto dto) throws SQLException {
		try {

			String delete = "delete from friend WHERE user_id = ? AND friend_id = ?";
			jdbcTemplate.update(delete, data.get("user_id").asInt(), data.get("friend_id").asInt());
			jdbcTemplate.update(delete, data.get("friend_id").asInt(), data.get("user_id").asInt());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		SQLException e;
		return 0;
	}
}