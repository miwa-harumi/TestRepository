package com.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.DAO.TaskDAO;
import com.DTO.TaskDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TaskController
{
	private JsonNode reqestdata;
	private String action;
	private TaskDAO modelobj;
	
	public TaskController(String action,JsonNode data)
	{
		this.reqestdata = data;
		this.action = action;
		this.modelobj = new TaskDAO();
	}

	public String executeAction() throws SQLException, IOException 
	{
		String result = "";
		ObjectMapper mapper = new ObjectMapper();
		
		// 文字列の比較(if[model]==("task")では取ってくることができない)
		//insertのとき
		if (this.action.equals("insert"))
		{
			int res = this.modelobj.insert(this.reqestdata);
			
			//JSONに変換
			result = mapper.writeValueAsString(res);
		}
		
		//listのとき
		if (this.action.equals("list"))
		{
			List<TaskDTO> res = this.modelobj.findAll(this.reqestdata);
			
			//JSONに変換
			result = mapper.writeValueAsString(res);
		}
		
		//deleteのとき
		if (this.action.equals("delete"))
		{
			int res = this.modelobj.delete(this.reqestdata);
			
			//JSONに変換
			result = mapper.writeValueAsString(res);
		}
		
		//updateのとき
		if (this.action.equals("update"))
		{
			int res = this.modelobj.update(this.reqestdata);
			
			//JSONに変換
			result = mapper.writeValueAsString(res);
		}
		
		return result;
	}
}