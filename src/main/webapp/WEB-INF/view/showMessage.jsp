<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
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
	<select id = "url">
	<option value = 'MainController'> 'MainController' </option>
	</select>
	
	<select id = "reqest">
	<option value = "{model:'user', action:'regist',dataObj}"> {model:'user', action:'regist',dataObj} </option>
	</select>
	
	<script type="text/javascript">
			var ajax_btn 	= document.getElementById('ajax_btn');
			ajax_btn.addEventListener('click', function() {
				dataObj = {
					first_name: "山田",
					last_name : "太郎"
				};
		    	//通信情報
		    	var urlList = document.getElementById("url").value;  //URL情報
		    	var requestData = document.getElementById("reqest").value;//受け渡しデータ
		    	//情報送信
		    	var reqList = new Request(urlList,{
		    	    method: 'post',
		    	    body : JSON.stringify(requestData),
		    	    headers : { 'Content-Type': 'application/json' }
		    	});

		    	//レスポンスを受取
		    	fetch(reqList).then(function(response){
		    	    response.json().then(function(json){
		    	    	console.log(requestData);
		    	    	console.log(json);
		    		});
		  		}); 
			});
		</script>
	</body>
</html>
