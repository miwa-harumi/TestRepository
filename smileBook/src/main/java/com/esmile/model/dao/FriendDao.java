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

	// ①JdbcTemplateの変数宣言
	private JdbcTemplate jdbcTemplate;

	// ②JdbcTemplateのsetter
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Map<String, Object>> friend_list(JsonNode data, FriendDto dto) {
		//List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from friend LEFT JOIN user ON friend.user_id = user.user_id");
		//List<Map<String, Object>> list = jdbcTemplate.queryForList("select friend.user_id, friend.friend_id, friend.status, user.last_name, user.first_name from friend LEFT JOIN user ON friend.friend_id = user.user_id WHERE user.user_id = " + data.get("user_id").asInt());
		//List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from friend LEFT JOIN user ON friend.user_id = user.user_id WHERE user.user_id");
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user LEFT JOIN friend ON friend.friend_id = user.user_id WHERE friend.user_id = " + data.get("user_id").asInt());
		for(Map<String, Object> map : list)
		{
			if(map.get("user_id") == null)
			{
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

	public List<Map<String, Object>> chat_list(JsonNode data, FriendDto dto) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user LEFT JOIN friend ON friend.friend_id = user.user_id WHERE friend.user_id = " + data.get("user_id").asInt() + " AND friend.status = '0'");
		for(Map<String, Object> map : list)
		{
			if(map.get("user_id") == null)
			{
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
	
	public int send(FriendDto dto) {
		try {
		jdbcTemplate.update("insert into friend(user_id,friend_id,status)" 
				 + "values(?,?,?),(?,?,'2')", dto.getFriend_User(), dto.getFriend_Friend(),dto.getFriend_Status(),
				 	 dto.getFriend_Friend(),dto.getFriend_User());
		}catch (ArithmeticException e) {		
			return 1;
		}
			return 0;		
	}

	public int reqest(JsonNode data,FriendDto dto) {
		try {
		if(dto.getFriend_Status() == 0)
		{
			String update = "update friend set status = "+ dto.getFriend_Status() +" WHERE user_id = ? AND friend_id = ?";
			jdbcTemplate.update(update, data.get("user_id").asInt(), data.get("friend_id").asInt());
			jdbcTemplate.update(update, data.get("friend_id").asInt(), data.get("user_id").asInt());
		}else if(dto.getFriend_Status() == 3)
		{
			String deny = "delete from friend WHERE user_id = ? AND friend_id = ?";
			jdbcTemplate.update(deny, data.get("user_id").asInt(), data.get("friend_id").asInt());
			jdbcTemplate.update(deny, data.get("friend_id").asInt(), data.get("user_id").asInt());
		}
		}catch (ArithmeticException e) {
			return 1;
		}
		return 0;
	}
	
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
	
	public int chat_delete(JsonNode data, FriendDto dto) throws SQLException {
		
		int user_id = data.get("user_id").asInt();
		int friend_id = data.get("friend_id").asInt();
		int sort = 0;
		if(user_id > friend_id)
		{
			sort = user_id;
			user_id = friend_id;
			friend_id = sort;
			sort = 0;
		}
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from chat LEFT JOIN friend ON friend.user_id = chat.chat_one AND friend.friend_id = chat.chat_two WHERE chat.chat_one = "+ user_id + " AND chat.chat_two = "+ friend_id  + " AND friend.status = '0'");
		for(Map<String, Object> map : list)
		{
			if(map.get("user_id") == null)
			{
				break;
			}
			System.out.println(map.get("user_id").toString());
			System.out.println(map.get("friend_id").toString());
			System.out.println(map.get("status").toString());
			if(map.get("chat_data") != null)
			{
				System.out.println(map.get("chat_data").toString());
			}

		}
		return 0;
	}
	
	public int delete(JsonNode data, FriendDto dto) throws SQLException {
		try {
		
		String delete = "delete from friend WHERE user_id = ? AND friend_id = ?";
		jdbcTemplate.update(delete, data.get("user_id").asInt(), data.get("friend_id").asInt());
		jdbcTemplate.update(delete, data.get("friend_id").asInt(), data.get("user_id").asInt());
		} catch (RuntimeException e) {
		      e.printStackTrace();
		}
//		}catch (SQLException e){
//		}
		SQLException e;
		return 0;
	}
}