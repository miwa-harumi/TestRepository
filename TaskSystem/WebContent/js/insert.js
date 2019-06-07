//登録毎に更新をする
function doReload()
{ 
    // reloadメソッドによりページをリロード
    window.location.reload();
}
//登録api
document.getElementById('Insert').addEventListener("click", function(e)
{	
	var task_title = document.getElementById("TaskTitle").value;
	var task_content = document.getElementById("TaskContent").value;
	
	var urlList = 'Servle';	
	
	//オブジェクトの中身
	var obj = 
	{
		title : task_title,
		content : task_content
	}
	
	//タイトルが未入力の場合エラーとなる
	if(task_title.trim() == "")
	{
		alert("タイトルを入力してください");
		return;
	}
	
	//リクエストデータ
	var requestData = {
		model : 'task',
		action : 'insert',
		obj
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
			//戻り値によって成功と失敗を判断させる
			if(data == 1)
			{
				alert(task_title + "を登録しました。");
				doReload();
			}else{
				alert(task_title + "を登録できません。");
			}
		});
	});
});
