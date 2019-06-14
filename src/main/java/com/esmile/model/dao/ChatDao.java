package com.esmile.model.dao;

import com.esmile.model.dto.ChatDto;
import com.esmile.model.dto.UserDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
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
	
	public List<Map<String, Object>> list(JsonNode data) throws ParseException, IOException{
		// 引数指定なし
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from chat where chat_one = " + data.get("chat_one").asInt()				 
																						+ " AND chat_two = " +data.get("chat_two").asInt());
		for (Map<String, Object> map : list) {
			System.out.println(map.get("chat_one").toString());
			System.out.println(map.get("chat_two").toString());
			System.out.println(map.get("chat_data").toString());
		}

		return list;
	}
	
	public int send(JsonNode data, ChatDto get) throws ParseException, IOException {
		List<Map<String, Object>> req = jdbcTemplate.queryForList("select * from chat where chat_one = " + data.get("chat_one").asInt() 
																		+ " AND chat_two = " +data.get("chat_two").asInt());
		
		
		if(req.size() == 0)
		{				
			jdbcTemplate.update(
					"insert into chat(chat_one,chat_two,chat_data)"
							+ "values(?,?,'{\"chat_1\" : "+ get.getChat_Data() +"}')",
							get.getChat_Send(),get.getChat_Get());

		}else{
			//文字列をjsonに変換
			ObjectMapper dataJson = new ObjectMapper();
			JsonNode     object   = dataJson.readTree(req.get(0).get("chat_data").toString());
			
			int cnt = (object.size()+1);			
			
			jdbcTemplate.update(
					"UPDATE chat SET chat_data = JSON_INSERT(chat_data,'$.chat_"+ cnt +"', ?)"
							+ " WHERE chat_one = "+data.get("chat_one").asInt()
							+ " AND chat_two = "+data.get("chat_two").asInt(),get.getChat_Data());
		}
		return 0;
	}
	
	public int delete(JsonNode data,ChatDto get) throws ParseException{
		jdbcTemplate.update(
				"UPDATE chat SET chat_data = JSON_REMOVE(chat_data,'$."+ data.get("chat_key").asText() + "')"
						+ " WHERE chat_one = "+data.get("chat_one").asInt()
						+ " AND chat_two = "+data.get("chat_two").asInt());
		return 0;
	}
}