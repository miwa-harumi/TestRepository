package com.esmile.model.dao;

import com.esmile.model.dto.ChatDto;
import com.esmile.model.dto.UserDto;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class ChatDao {

	// ①JdbcTemplateの変数宣言
	private JdbcTemplate jdbcTemplate;

	// ②JdbcTemplateのsetter
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// ③検索処理
	/*
	
	public int chat() {
		int ret = jdbcTemplate.update("select * from chat", String.class);
		return ret;
	}
	*/

	// ③検索処理

	public List select() 
	{
		List ret = jdbcTemplate.queryForList("select * from chat", String.class);
		return ret;
	}
	
	public List<Map<String, Object>> chat() {

		// 引数指定なし
		List<Map<String, Object>> chat = jdbcTemplate.queryForList("select * from chat");
		for (Map<String, Object> map : chat) {
			System.out.println(map.get("chat_one").toString());
			System.out.println(map.get("chat_two").toString());
			System.out.println(map.get("chat_data").toString());
		}

		return chat;
	}
	
	//public int chat(ChatDto get) throws ParseException 
	//{
		//jdbcTemplate.queryForList("select * from chat");
		//jdbcTemplate.queryForList("select chat_one, chat_two, chat_data from chat", String.class);
		//jdbcTemplate.queryForList("select chat_one, chat_two, chat_data from chat", get.getChat_Send(),get.getChat_Get(),get.getChat_Data());
		
		//ResultSet rset;
		
		//jdbcTemplate.update("select chat_one, chat_two, chat_data from chat",
		//get.setChat_Send(rset.getInt("")),get.getChat_Get(),get.getChat_Data());
		
		/*
		// 送信されたJSONの取得
		BufferedReader buffer = new BufferedReader(ret);
				
		//文字列化したデータをすべて渡す
		String reqJson = buffer.readLine();
		*/
		
		//return 0;
	//}
}