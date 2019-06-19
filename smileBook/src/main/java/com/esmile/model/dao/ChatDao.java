package com.esmile.model.dao;

import com.esmile.model.dto.ChatDto;
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

public class ChatDao {

	// JdbcTemplateの変数宣言
	private JdbcTemplate jdbcTemplate;

	// JdbcTemplateのsetter
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	

	//チャットリストAPI
	public List<Map<String, Object>> list(JsonNode data) {
		// 引数指定なし
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from chat where chat_one = "
				+ data.get("chat_one").asInt() + " AND chat_two = " + data.get("chat_two").asInt());
		for (Map<String, Object> map : list) {
			System.out.println(map.get("chat_one").toString());
			System.out.println(map.get("chat_two").toString());
			System.out.println(map.get("chat_data").toString());
		}
		return list;
	}

	//チャットを送るAPI
	public int send(JsonNode data, ChatDto get) {

		//id番号が若いものがchat_oneに来るようにソートする
		int chat_one = get.getChat_Send();
		int chat_two =  get.getChat_Get();
		int sort = 0;
		if(chat_one > chat_two )
		{
			sort = chat_one;
			chat_one = chat_two;
			chat_two = sort;
			sort = 0;
		}
		
		//id番号が若いものがchat_oneに来るようにソートする
		int getchat_one = data.get("chat_one").asInt();
		int getchat_two = data.get("chat_two").asInt();
		if(getchat_one > getchat_two)
		{
			sort = getchat_one;
			getchat_one = getchat_two;
			getchat_two = sort;
			sort = 0;
		}
		
		//チャットを送るときすでに繋がっていないかを検索
		List<Map<String, Object>> req = jdbcTemplate.queryForList("select * from chat where chat_one = "
				+ getchat_one + " AND chat_two = " + getchat_two);
		try {			
			if (req.size() == 0) {
				
				//チャットを送って繋がっていなかった場合初めてのチャット
				jdbcTemplate.update("insert into chat(chat_one,chat_two,chat_data)" + "values(?,?,'{\"chat_1\" : "
						+ get.getChat_Data() + "}')",chat_one, chat_two);

			} else {
				//チャットを送ってすでに繋がっていた場合chat_dataに横並びに追加していく
				// 文字列をjsonに変換
				ObjectMapper dataJson = new ObjectMapper();
				JsonNode object = dataJson.readTree(req.get(0).get("chat_data").toString());

				//Keyの設定
				int cnt = (object.size() + 1);

				jdbcTemplate.update("UPDATE chat SET chat_data = JSON_INSERT(chat_data,'$.chat_" + cnt + "', ?)"
						+ " WHERE chat_one = " + getchat_one + " AND chat_two = "
						+ getchat_two, get.getChat_Data());
			}
		} catch (IOException e) {
			return 1;
		}
		return 0;
	}

	//指定のチャットを削除するAPI
	public int delete(JsonNode data, ChatDto get) throws ParseException {
		try {
			//消したいチャットのKey値をリクエストし、リクエストされたチャットが削除される
			jdbcTemplate.update("UPDATE chat SET chat_data = JSON_REMOVE(chat_data,'$." + data.get("chat_key").asText()
					+ "')" + " WHERE chat_one = " + data.get("chat_one").asInt() + " AND chat_two = "
					+ data.get("chat_two").asInt());
		} catch (ArithmeticException e) {
			return 1;
		}
		return 0;
	}
}