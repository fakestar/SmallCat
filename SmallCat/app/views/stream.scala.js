@(room: String, user: String)

$(function() {

	// ws://localhost:9000/ws/defaultへのソケットを作成
	var chatSocket = new WebSocket("@routes.RoomController.ws(room).webSocketURL(request)")

	var sendMessage = function() {
		// メッセージをJSON形式で送信
		chatSocket.send(JSON.stringify({
			"@@type": $("#command").val(),
			id: '@user',
			text: $("#value").val()
		}))

		// 実行コマンドを表示
		var el = $('<div class="request"><p></p></div>')
		$("p", el).text(">> " +  $("#command").val() + ":" + $("#value").val())
		$(el).addClass('me')
		$('#request').append(el)

		// メッセージのテキストエリアを空文字でクリア
		$("#value").val('')
	}

	var receiveEvent = function(event) {
		// データ受信
		var data = JSON.parse(event.data)

		// Handle errors
		if(data.error) {
			chatSocket.close()
			$("#onError span").text(data.error)
			$("#onError").show()
			return
		} else {
			//正常時
			$("#onChat").show()
		}

		// Create the message element
		var el = $('<div class="message"><p></p></div>')
		$("span", el).text(data.id)
		$("p", el).text(data.id + ">>" + data.kind + ":" + data.message)
		$(el).addClass(data.kind)
		if(data.id == '@user') $(el).addClass('me')
		$('#messages').append(el)

		// Update the members list
		$("#members").html('')
		$(data.members).each(function() {
			var li = document.createElement('li');
			li.textContent = this;
			$("#members").append(li);
		})
	}

	var handleReturnKey = function(e) {
		// Enter押下時
		if(e.charCode == 13 || e.keyCode == 13) {
			e.preventDefault()
			sendMessage()
		}
	}

	// Enter押下でメッセージ送信
	$("#value").keypress(handleReturnKey)
	// receiveEventをonmessageに設定
	chatSocket.onmessage = receiveEvent

})
