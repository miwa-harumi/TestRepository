package com.esmile.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.esmile.model.dao.UserDao;
import com.esmile.model.dto.UserDto;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class UserService extends BaseService {
	
	//コンストラクタ
	public UserService() {
		super();
	}
	
	//セットデータ
	@Override
	public void SetData(String aciton,JsonNode data) {
		this.action = aciton;
		this.data   = data; 
	};
		
	//モデル振り分け
	@Override
	public String ActionState() throws Exception {
		String result = null;
		
    	 //コンテキスト取得
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-config.xml");
        
         //インスタンスの生成
        UserDao dao = context.getBean(UserDao.class);
        
		if(this.action.equals("regist")) 
		{
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

		return result;
	}
}