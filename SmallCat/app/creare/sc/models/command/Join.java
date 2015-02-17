package creare.sc.models.command;

import play.mvc.WebSocket;

import com.fasterxml.jackson.databind.JsonNode;

public class Join implements Command {

	public final String id;
	public final WebSocket.Out<JsonNode> channel;

	public Join(String id, WebSocket.Out<JsonNode> channel) {
		this.id = id;
		this.channel = channel;
	}

	@Override
	public void exec() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
