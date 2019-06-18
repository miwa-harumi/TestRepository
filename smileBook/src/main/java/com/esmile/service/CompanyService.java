package com.esmile.service;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.esmile.model.dao.CompanyDao;
import com.esmile.model.dao.FriendDao;
import com.esmile.model.dto.FriendDto;
import com.esmile.model.dto.UserDto;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class CompanyService extends BaseService 
{
	// コンストラクタ
	public CompanyService() 
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
		CompanyDao dao = context.getBean(CompanyDao.class);		
		
		if (this.action.equals("list")) {
			List<Map<String, Object>> res = dao.list(data);
			result = this.mapper.writeValueAsString(res);
		}
		return result;
	}
}
