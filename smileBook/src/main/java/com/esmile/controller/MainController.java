package com.esmile.controller;


import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esmile.service.ChatService;
import com.esmile.service.FriendService;
import com.esmile.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller 
public class MainController {
	
	//サービス
	@Autowired
	private UserService UserService;
	
	@Autowired
	private ChatService ChatService;
	
	@Autowired
	private FriendService FriendService;
	
	//テスト用
	@RequestMapping("show")
	public String index() {
		return "/showMessage";
	}
	
	//サービス振り分け
	@RequestMapping(value = "MainController",method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public String SortingService(HttpServletRequest request) throws Exception {
		//受け取ったデータを文字列に変換
		BufferedReader buffer     = new BufferedReader(request.getReader());
		String		 	 dataString = buffer.readLine();
		 
		//文字列をjsonに変換
		ObjectMapper dataJson = new ObjectMapper();
		JsonNode     object   = dataJson.readTree(dataString);
	
		//データを抽出
		String   model   = object.get("model").asText();
		String   action  = object.get("action").asText();
		JsonNode dataObj = object.get("dataObj");
		 
		 //受信データ作成
		 String response="";
			
		 //モデル判別
		 if(model.equals("user")){ 
			 UserService.SetData(action,dataObj);
			 response  = UserService.ActionState();
		 }		 
		
		 if(model.equals("chat")){ 
			 ChatService.SetData(action,dataObj);
			 response  = ChatService.ActionState();
		 }	
		 
		 if(model.equals("friend")){ 
			 FriendService.SetData(action,dataObj);
			 response  = FriendService.ActionState();
		 }
		 
		return response;
	}
}
