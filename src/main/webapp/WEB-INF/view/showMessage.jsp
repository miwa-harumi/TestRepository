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
		<h2><c:out value="${name}" /></h2>
	<div id="ajax_data">
    	<input type="button" id="ajax_btn" value="Ajax通信テスト" /><br />
    	ajaxData：<span id="output_data"></span>
	</div>
	
	<script type="text/javascript">
			var ajax_btn 	= document.getElementById('ajax_btn');
			ajax_btn.addEventListener('click', function() {
		    	//通信情報
		    	var urlList = 'UserController/List';  //URL情報
		    	var requestData = {model:'company', action:'list'};//受け渡しデータ
		    	//情報送信
		    	var reqList = new Request(urlList,{
		    	    method: 'post',
		    	    body : JSON.stringify(requestData),
		    	    headers : { 'Content-Type': 'application/json' }
		    	});

		    	//レスポンスを受取
		    	fetch(reqList).then(function(response){
		    	    response.text().then(function(text){
		    	    	alert(requestData);
		    	    	alert(text);
		    		});
		  		}); 
			});
		</script>
	</body>
</html>
