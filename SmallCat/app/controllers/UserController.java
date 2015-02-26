package controllers;

import java.util.List;

import play.libs.F.Option;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import creare.sc.models.entity.Player;
import creare.sc.models.form.PlayerResponse;
import creare.sc.models.service.PlayerService;

public class UserController extends Controller {

	/**
	 * localhost:9000/players/1.json
	 *
	 * 指定したPlayerIDのデータをJSONで返却する。
	 *
	 * @param playerId
	 * @return
	 */
	public static Result getPlayer(Long playerId) {
		// JSONフォーマット用のレスポンス
		PlayerResponse result = new PlayerResponse();

		// PlayerIDを指定して取得
		PlayerService service = new PlayerService();
		Option<Player> ops = service.findById(playerId);

		if(ops.isDefined()) {
			//Optionの中身が存在する場合
			result.setCode(1);
			result.setStatus("OK");
			result.setMessage(null);
			result.setResult(ops.get());

			return ok(Json.toJson(result));
		}

		result.setCode(99);
		result.setStatus("NG");
		result.setMessage("player not found.(id=" + playerId + ")");

		return badRequest(Json.toJson(result));
	}

	public static Result createPlayer() {

		return TODO;
	}

	public static Result getFriend(Long playerId, Long friendId) {

		// JSONフォーマット用のレスポンス
		PlayerResponse result = new PlayerResponse();

		// PlayerIDを指定して取得
		PlayerService service = new PlayerService();
		Option<Player> ops = service.findById(playerId);

		if(ops.isDefined()) {
			//Optionの中身が存在する場合
			List<Player> friendList = ops.get().friends;
			return ok(Json.toJson(friendList));
		}

		return badRequest(Json.toJson(""));
	}
}