package com.esmile.service;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.esmile.model.dao.FriendDao;
import com.esmile.model.dto.FriendDto;
import com.esmile.model.dto.UserDto;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class FriendService extends BaseService 
{
	// コンストラクタ
	public FriendService() 
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
		FriendDao dao = context.getBean(FriendDao.class);

		if (this.action.equals("send")) 
		{
			FriendDto dto = new FriendDto();
			dto.setFriend_User(this.data.get("user_id").asInt());
			dto.setFriend_Friend(this.data.get("friend_id").asInt());
			dto.setFriend_Status(this.data.get("status").asInt());
			
			int res = dao.send(dto);
			result = this.mapper.writeValueAsString(res);
		}
		
		if (this.action.equals("reqest")) 
		{
			FriendDto dto = new FriendDto();
			dto.setFriend_Status(this.data.get("status").asInt());
			int res = dao.reqest(data,dto);
			result = this.mapper.writeValueAsString(res);
		}		

		if (this.action.equals("chat_delete")) 
		{
			FriendDto dto = new FriendDto();
			int res = dao.chat_delete(data,dto);
			result = this.mapper.writeValueAsString(res);
		}
		
		if (this.action.equals("delete")) 
		{
			FriendDto dto = new FriendDto();
			int res = dao.delete(data,dto);
			result = this.mapper.writeValueAsString(res);
		}
		
		if (this.action.equals("cancel")) 
		{
			FriendDto dto = new FriendDto();
			int res = dao.cancel(data,dto);
			result = this.mapper.writeValueAsString(res);
		}
		
		if (this.action.equals("friend_list")) {
			FriendDto dto = new FriendDto();
			List<Map<String, Object>> res = dao.friend_list(data,dto);
			result = this.mapper.writeValueAsString(res);
		}
		
		if (this.action.equals("chat_list")) {
			FriendDto dto = new FriendDto();
			List<Map<String, Object>> res = dao.chat_list(data,dto);
			result = this.mapper.writeValueAsString(res);
		}
		return result;
	}
}
