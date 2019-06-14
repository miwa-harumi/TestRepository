package com.esmile.model.dao;

import com.esmile.model.dto.ChatDto;
import com.esmile.model.dto.FriendDto;
import com.esmile.model.dto.UserDto;
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

	// ③検索処理
	public List select() {
		List ret = jdbcTemplate.queryForList("select * from friend", String.class);
		return ret;
	}

	public List<Map<String, Object>> list(JsonNode data) {
		// 引数指定なし
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from friend WHERE user_id = "+ data.get("user_id").asInt() +"");
		for (Map<String, Object> map : list) {
			System.out.println(map.get("user_id").toString());
			System.out.println(map.get("friend_id").toString());
			System.out.println(map.get("status").toString());
		}

		return list;
	}

	public int send(FriendDto dto) {
		jdbcTemplate.update("insert into friend(user_id,friend_id,status)" 
							 + "values(?,?,?)", dto.getFriend_User(), dto.getFriend_Friend(),dto.getFriend_Status());
		
		jdbcTemplate.update("insert into friend(user_id,friend_id,status)"
				 + "values(?,?,'2')", dto.getFriend_Friend(),dto.getFriend_User());
		return 0;
	}

	public int reqest(JsonNode data,FriendDto dto) {
		if(dto.getFriend_Status() == 0)
		{
			jdbcTemplate.update("update friend set status = "+ dto.getFriend_Status() +" WHERE user_id = "+ data.get("user_id").asInt() + " AND friend_id = " + data.get("friend_id").asInt());
			jdbcTemplate.update("update friend set status = "+ dto.getFriend_Status() +" WHERE user_id = "+ data.get("friend_id").asInt() + " AND friend_id = " + data.get("user_id").asInt());
		}else if(dto.getFriend_Status() == 3)
		{
			jdbcTemplate.update("delete from friend WHERE user_id = "+ data.get("user_id").asInt() + " AND friend_id = " + data.get("friend_id").asInt());
			jdbcTemplate.update("delete from friend WHERE user_id = "+ data.get("friend_id").asInt() + " AND friend_id = " + data.get("user_id").asInt());
		}
		return 0;
	}
	
	public int delete(JsonNode data, FriendDto dto) throws ParseException {
		jdbcTemplate.update("delete from friend WHERE user_id = "+ data.get("user_id").asInt() + " AND friend_id = " + data.get("friend_id").asInt());
		jdbcTemplate.update("delete from friend WHERE user_id = "+ data.get("friend_id").asInt() + " AND friend_id = " + data.get("user_id").asInt());		
		return 0;
	}
}