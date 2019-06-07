package com.esmile.service;

import org.springframework.stereotype.Service;
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
		
		if(this.action.equals("regist")) {
			result = this.mapper.writeValueAsString(this.data);
		}

		return result;
	}
}
