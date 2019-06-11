package com.esmile.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BaseService {
	protected String   		action;	//アクション受取用
	protected JsonNode 		data;		//本体データ受取用
	protected ObjectMapper	mapper;	//json変換用 
	
	//コンストラクタ
	public BaseService() {
		this.mapper = new ObjectMapper();
	}
	
	//セットデータ
	public void SetData(String aciton,JsonNode data) {
		this.action = aciton;
		this.data   = data; 
	};
	
	public void SetDataList(String aciton) {
		this.action = aciton;
	};
	
	//モデル振り分け
	public String ActionState() throws Exception {
		String result = null;	
		
		return result;
	}
}
