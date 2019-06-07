<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>タスク管理システム</title>
		<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/css/task_style.css">
	</head>
	<body>
		<div class = "css__title-insert">
			<h1>タスク管理システム</h1>
		</div>
		<form>
			<!--タスク登録-->
			<div class = "p__center">
				<span>
					<input type="hidden" id="TaskID"></input>
					<input type="text" id="TaskTitle" class="css__form" name="insert" maxlength=21 placeholder="タイトルを入力"></input>
					<button type="button" id="Insert" class="css__insert">登　録</button>
				</span>
			</div>
		</form>

		<form method="post">
			<div class="css__box_parent" id ="TaskList">
				<input type ="hidden" id = "TaskContent"></input>
				<input type ="hidden" id = "TaskTime"></input>
			</div>
		</form>
		<!--jsの読み取り-->
		<script src="./js/insert.js"></script>
		<script src="./js/list.js"></script>
	</body>
</html>