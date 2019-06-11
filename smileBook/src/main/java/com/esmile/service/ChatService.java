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
public class ChatService extends BaseService {
	
	//コンストラクタ
	public ChatService() {
		super();
	}
	
	//セットデータ
	@Override
	public void SetDataList(String aciton) {
		this.action = aciton;
	};
		
	//モデル振り分け
	@Override
	public String ActionState() throws Exception {
		String result = null;
		
    	 //コンテキスト取得
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-config.xml");
        
         //インスタンスの生成
        ChatDao dao = context.getBean(ChatDao.class);
        
        if(this.action.equals("regist")) {
			
			UserDto dto = new UserDto();
			dto.setFirst_Name  (this.data.get("first_name").asText());
			dto.setLast_Name   (this.data.get("last_name").asText());
			dto.setPhone_Number(this.data.get("phone_number").asText());
			dto.setMail	     (this.data.get("mail").asText());
			dto.setSex         (this.data.get("sex").asInt());
			dto.setPassword    (this.data.get("password").asText());
			dto.setCompany_Id  (this.data.get("company_id").asInt());
			
			int res = dao.regist(dto);
			result  = this.mapper.writeValueAsString(res);
		}
        
		if(this.action.equals("list")) 
		{
			List<Map<String, Object>> res = dao.chat();
			result  = this.mapper.writeValueAsString(res);
		}
		return result;
	}
}
