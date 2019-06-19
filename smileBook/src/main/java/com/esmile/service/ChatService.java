package com.esmile.service;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.esmile.model.dao.ChatDao;
import com.esmile.model.dto.ChatDto;
import com.esmile.model.dto.UserDto;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ChatService extends BaseService 
{
	// コンストラクタ
	public ChatService() 
	{
		super();
	}

	// セットデータ
	@Override
	public void SetData(String aciton, JsonNode data) 
	{
		this.action = aciton;
		this.data = data;
	};

	// モデル振り分け
	@Override
	public String ActionState() throws Exception 
	{
		String result = null;

		// コンテキスト取得
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-config.xml");

		// インスタンスの生成
		ChatDao dao = context.getBean(ChatDao.class);

		//チャットを送る
		if (this.action.equals("send")) 
		{
			ChatDto dto = new ChatDto();
			dto.setChat_Send(this.data.get("chat_one").asInt());
			dto.setChat_Get(this.data.get("chat_two").asInt());
			dto.setChat_Data(this.data.get("chat_data").asText());
			
			int res = dao.send(data,dto);
			result = this.mapper.writeValueAsString(res);
		}
		
		//チャットを削除する
		if (this.action.equals("delete")) 
		{
			ChatDto dto = new ChatDto();
			dto.setChat_Data(this.data.get("chat_data").asText());
			int res = dao.delete(data,dto);
			result = this.mapper.writeValueAsString(res);
		}
		
		//チャットのリスト
		if (this.action.equals("list")) {
			List<Map<String, Object>> res = dao.list(data);
			result = this.mapper.writeValueAsString(res);
		}
		return result;
	}
}
