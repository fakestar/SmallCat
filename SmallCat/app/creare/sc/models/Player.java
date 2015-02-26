package creare.sc.models;

import java.util.List;



/**
 * プレイヤー
 *
 */
public class Player extends GameCharacter {

	private List<Enemy> enemyList;

	public Player(String name, int hp, int attack, int defense, int speed) {
		super(name, hp, attack, defense, speed);
	}

	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	@Override
	public GameCharacter getTarget() {
		int target = (int) (Math.random() * enemyList.size());
		return enemyList.get(target);
	}

	@Override
	public String toString() {
		return "Player [hp=" + hp + ", attack=" + attack + ", defense=" + defense + "]";
	}

}
