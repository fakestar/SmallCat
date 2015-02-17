package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

import com.fasterxml.jackson.databind.JsonNode;

import creare.sc.models.Room;

/**
 * @author 0505177
 *
 */
public class RoomController extends Controller {

	// ブラウザ用
	public static Result streamJs(final String room) {

		String user = session("user");

		return ok(views.js.stream.render(room, user));
	}

	/**
	 * Handle the websocket.
	 */
	public static WebSocket<JsonNode> ws(final String room) {
		final String user = session("user");
		return new WebSocket<JsonNode>() {
			@Override
			public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out){
				// ハンドシェイクが完了した時点で実行される処理
				try {
					// TODO Room取得処理
					// String roomに合わせたRoomオブジェクトを取得
					// Join the room.
					Room.join(user, in, out);
				} catch (Exception ex) {
					// throws exception
					ex.printStackTrace();
				}
			}
		};
	}
}