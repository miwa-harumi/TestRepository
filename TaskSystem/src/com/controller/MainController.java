package com.controller;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//検索をかけるときにはhttp://localhost:8080/tomcat_test/Servleで検索する
//また↓の@WebServlet("/Servle")はURLを指定するために必要
@WebServlet("/Servle")
public class MainController extends HttpServlet
{	
	private static final long serialVersionUID = 1L;	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{		
		resp.setContentType("text/html; charset=utf-8");
		
		// JSP呼び出し用のプログラム
		String view = "/jsp/TaskSystem.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	 {
		//コンソール等で表示するときに使用するoutの宣言
		PrintWriter out = resp.getWriter();

		// 送信されたJSONの取得
		BufferedReader buffer = new BufferedReader(req.getReader());
		
		//文字列化したデータをすべて渡す
		String reqJson = buffer.readLine();

		// オブジェクトをJson文字列に変更
		ObjectMapper mapper = new ObjectMapper();
       
		//JSONファイルのデータを木構造データとしたときの根ノードを、JsonNode 型のオブジェクトとして読み込む。
		JsonNode root = mapper.readTree(reqJson);
		
		// ヘッダ情報などセット
		resp.setContentType("application/json");
		resp.setHeader("Cache-Control", "nocache");
		resp.setCharacterEncoding("utf-8");
	
		String result = "";		
				
		//文字列の比較(if[model]==("task")では取ってくることができない)
		if(root.get("model").asText().equals("task")) {
			TaskController task = new TaskController(root.get("action").asText(),root.get("obj"));
			try {
				result = task.executeAction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		out.println(result);
     }	
}