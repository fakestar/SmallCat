package creare.sc.models.form;

import creare.sc.models.entity.Player;


public class PlayerResponse extends DefaultResponse {

	private Player result;

	public PlayerResponse() {

	}

	public PlayerResponse(Player player) {
		this.result = player;
	}

	public Player getResult() {
		return result;
	}

	public void setResult(Player result) {
		this.result = result;
	}


}
