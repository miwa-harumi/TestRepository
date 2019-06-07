//タスク一覧表示
document.addEventListener("DOMContentLoaded", function() 
{		
	var urlList = 'Servle';	
	
	//リクエストデータ
	var requestData = {
		model : 'task',
		action : 'list'
	};
	
	// リクエストの準備
	var reqList = new Request(urlList, {
		method : 'post',
		body : JSON.stringify(requestData),
		headers : {
			'Content-Type' : 'application/json'
		}
	});
	
	fetch(reqList).then(function(response) {
		if (!response.ok) {
			// error処理を追加
			alert("エラー発生");
		}
		response.json().then(function(data)		
		{
			//リスト作成
			for(var _data of data)
			{
				var Cell = 
					"<div class='css__box'>" +
						"<textarea class=\"css__box_title\" id='TaskBoxTitle_"+ _data.id + "'>" + _data.title + "</textarea>" +
						"<div class=\"css__box_time\">" +
							"<p class=\"css__fontsize\" id=\"TaskTime\">" + _data.time + "</p>" +
						"</div>" +
						"<textarea class=\"css__box_textbox\" id='TaskBoxContent_" + _data.id + "' placeholder=\"内容を入力\">" + _data.content + "</textarea>" +
						"<button type=\"button\" class=\"css__update\" onclick=\"update(" + _data.id + ");\">編 集</button>" +
						"<button type=\"button\" class=\"css__delete\" onclick=\"destroy(" + _data.id +");\">削 除</button>" +				
					"</div>";
								
				//抽出したデータをセル内に書き込み	
				document.getElementById ("TaskList").innerHTML += Cell;				
			}
		});
	});	
});

//削除、編集毎に更新をする
function doReload()
{ 
    // reloadメソッドによりページをリロード
    window.location.reload();
}

//削除api
function destroy(id)
{
	//受け取ったIDをオブジェクト用に変換
	var task_id = id;
	var task_title = document.getElementById("TaskBoxTitle_" + id).value;
	
	// URL情報
	var urlList = 'Servle';
	
	//オブジェクトの中身
	obj = 
	{
		id : task_id		
	}
	
	//リクエストデータ
	var requestData = 
	{
		model : 'task',
		action : 'delete',
		obj
	};		
		
	// リクエストの準備
	var reqList = new Request(urlList, 
	{
		method : 'post',
		body : JSON.stringify(requestData),
		headers : 
		{
			'Content-Type' : 'application/json'
		}
	});
		
	fetch(reqList).then(function(response) 
	{
		if (!response.ok) 
		{
			// error処理
			alert("削除にエラーが発生しました");
		}
		
		response.json().then(function(data) 
		{	
			//戻り値によって成功と失敗を判断させる
			if(data == 1)
			{
				alert("タイトル「" + task_title + "」を削除しました。");
				//list();
				doReload();
			}else{
				alert("タイトル「" + task_title + "」を削除できませんでした。");
			}
		});
	});
}

//編集api
function update(id)
{
	//受け取ったデータをオブジェクトに変換する
	var task_id = id;
	var task_title = document.getElementById("TaskBoxTitle_" + id).value;
	var task_content = document.getElementById("TaskBoxContent_" + id).value;

	// URL情報
	var urlList = 'Servle';
	
	//オブジェクトの中身
	obj = 
	{
		id 		 : task_id,
		title 	 : task_title,
		content : task_content
	}
	
	//タイトルが未入力の場合エラーとなる
	if(task_title.trim() == "")
	{
		alert("タイトルを入力してください");
		return;
	}
	
	//リクエストデータ
	var requestData = 
	{
		model  : 'task',
		action : 'update',
		obj
	};
	
	// リクエストの準備
	var reqList = new Request(urlList, 
	{
		method  : 'post',
		body    : JSON.stringify(requestData),
		headers : 
		{
			'Content-Type' : 'application/json'
		}
	});
	
	fetch(reqList).then(function(response) 
	{
		if (!response.ok) 
		{
			// error処理を追加
			alert("編集にエラーが発生しました");
		}
		
		response.json().then(function(data) 
		{	
			//戻り値によって成功と失敗を判断させる
			if(data == 1)
			{
				alert("タイトル「" + task_title + "」を編集しました。");
				doReload();
			}else{
				//失敗時
				alert("タイトル「" + task_title + "」を編集できません。");
			}
		});
	});
}