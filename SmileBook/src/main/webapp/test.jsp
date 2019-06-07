<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>タスク管理システム</title>
	</head>
	<body>
		<h1>タスク管理システム</h1>
		<form method="post">
			<div id ="TaskList">
				<input type ="hidden" id = "TaskContent"></input>
				<input type ="hidden" id = "TaskTime"></input>
			</div>
		</form>
	</body>
</html>