<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<title>test</title>
</head>
<body>
	<div id="ajax_data">
		<input type="button" id="ajax_btn" value="Ajax通信テスト" /><br />
		ajaxData：<span id="output_data"></span>
	</div>
	<select id="url">
		<option value='MainController'>'MainController'</option>
	</select>

	<!-- 	<select id = "reqest"> -->
	<!-- 	<option value = "{model:'user', action:'regist',dataObj}"> {model:'user', action:'regist',dataObj} </option> -->
	<!-- 	<option value = "{model:'user', action:'chat'}"> {model:'user', action:'chat'} </option> -->
	<!-- 	</select> -->

	<script type="text/javascript">
		var ajax_btn 	= document.getElementById('ajax_btn');
		ajax_btn.addEventListener('click', function() {
			
			
			/*
			//会社一覧API用
			dataObj =
			{
				company_id: "2",
			};
			*/
			
			//フレンドAPI用
			/*
			dataObj =
			{
				user_id: "2",
				friend_id : "3",
				status : "0"
			};
			*/
			
			//chatAPI用
			chat_chat =
			{
				"user_id" : "3",
				"content" : "Right to indian",
				"send_date" : "2019-06-16 11:07:45"
			};
			
			dataObj =
			{
				chat_one: "2",
				chat_two : "3",
				chat_data : JSON.stringify(chat_chat),
				chat_key : "chat_2"
			};
			
			/*
			//ユーザーAPI用
			dataObj = 
			{
				first_name: "gundam",
				last_name : "master",
				phone_number:"123456789",
				mail:"dakaraomaeha@ahonano.da",
				sex:"0",
				birthdate : "2306-06-16",
				birthplace : "neohonkon",
				introduction : "sekihatennkyou",
				skill : "ryuuhatouhouhuhai",
				open_range : "1",
				password:"kingofheart",
				icon_image : "domon.png",
				header_image : "kuron.png",
				company_id : "1"
			};
			*/
		   	//通信情報
		   	var urlList = document.getElementById("url").value;  //URL情報
		   	//requestパターン
		   	//friend : send / friend_list / chat_list / request / cancel / chat_delete / delete
		   	//chat : list / send / delete
		   	//company : list 
		   	var requestData = {model:'friend', action:'chat_delete',dataObj}; //受け渡しデータ
		   	//情報送信
		   	var reqList = new Request(urlList,
		   	{
		   	    method: 'post',
		   	    body : JSON.stringify(requestData),
		   	    headers : { 'Content-Type': 'application/json' }
		   	});

		    //レスポンスを受取
		    fetch(reqList).then(function(response)
		    {
		        response.json().then(function(json)
		        {
		        	console.log(requestData);
		        	console.log(json);
		    	});
		  	}); 
		});
		</script>
</body>
</html>
