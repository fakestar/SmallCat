package creare.sc.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import play.Logger;
import creare.sc.models.Stage.Floor;

/**
 * とりあえず放置型RPG
 *
 * @author 0505177
 *
 */
public class Game {

	// Stage

	public void exec() {
		List<Player> playerList = new ArrayList<Player>();
		Player player = new Player("player1", 100, 10, 25, 10, 18);

		playerList.add(player);

		Stage stage = new Stage("testStage1");

		Logger.debug("stage start. (stage name is " + stage.getName() + ")");

		for(int i = 0; i < stage.getMaxFloorCount(); i++) {
			Logger.debug("floor=" + i);

			Floor foolr = stage.getFloor(i);

			for(int j = 0; j < foolr.getMaxBattleCount(); j++) {
				// 戦闘になるかの判定
				if(foolr.isEncounter()) {
					List<Enemy> enemyList = stage.getEnemyList();

					Logger.debug("battle start...");

					// 戦闘処理
					if(this.battle(playerList, enemyList)){
						Logger.debug("player win.");
					} else {
						Logger.debug("player lose.");
						return;
					}

				}
			}

			// 次の階に進む
			stage.nextFloor();
		}

		Logger.debug("stage completion achievement.");

	}


	private boolean battle(List<Player> playerList, List<Enemy> enemyList) {

		int turn = 1;

		List<GameCharacter> list = new ArrayList<GameCharacter>();
		list.addAll(playerList);
		list.addAll(enemyList);

		while(true) {
			Logger.debug("turn " + turn);

			//行動順でソート
			Collections.sort(list, comparator);

			for(GameCharacter gameCharacter : list) {
				if(!gameCharacter.isDead()) {
					Logger.debug("  " + gameCharacter.name + " action. (cost:" + gameCharacter.getActionSpeed() + ")");

					if(gameCharacter instanceof Player) {
						GameCharacter target = gameCharacter.getTarget(enemyList);
						// 攻撃を行う
						gameCharacter.attack(target);

						if(target.isDead()) {
							enemyList.remove(target);
						}

						if(enemyList.isEmpty()) {
							return true;
						}
					 } else {
						// 誰を攻撃するかを決める
						GameCharacter target = gameCharacter.getTarget(playerList);
						// 攻撃を行う
						gameCharacter.attack(target);

						if(target.isDead()) {
							return false;
						}
					}

					gameCharacter.refresh();
				}
			}

			turn++;
		}
	}

	Comparator<GameCharacter> comparator = new Comparator<GameCharacter>() {

		@Override
		public int compare(GameCharacter o1, GameCharacter o2) {

			return o2.getActionSpeed()- o1.getActionSpeed();
		}

	};
}
