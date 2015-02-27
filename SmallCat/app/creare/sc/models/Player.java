package creare.sc.models;




/**
 * プレイヤー
 *
 */
public class Player extends GameCharacter {

	public Player(String name, int maxHp, int maxMp, int atk, int def, int dex) {
		super(name, maxHp, maxMp, atk, def, dex);
	}

	@Override
	public void action() {
		// 何をするのかを決める
		String content = this.getContent();
		switch (content) {
			case "ATTACK":
				// 攻撃
				// 　→敵単体
				GameCharacter target = this.getTargetCharacter();
				this.attack(target);
				break;

			default:
				// 魔法
				// 　→敵単体
				// 　→敵全体
				// 　→味方単体
				// 　→味方全体
				break;
		}

	}

	 private GameCharacter getTargetCharacter() {
		 return null;
	 }

	 private String getContent() {
		 return "ATTACK";
	 }
}
