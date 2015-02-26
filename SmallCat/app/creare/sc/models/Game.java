package creare.sc.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
		Player player = new Player("player1", 100, 25, 10, 20);

		Stage stage = new Stage("testStage1");

		Logger.debug("stage start. (stage name is " + stage.getName() + ")");

		for(int i = 0; i < stage.getMaxFloorCount(); i++) {
			Logger.debug("floor=" + i);

			Floor foolr = stage.getFloor(i);
			for(int j = 0; j < foolr.getMaxBattleCount(); j++) {
				// 戦闘になるかの判定
				if(foolr.isEncounter()) {
					// 戦闘処理

					List<Enemy> enemyList = stage.getEnemyList();
					Logger.debug("battle start...");
					if(this.battle(player, enemyList)){
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


	private boolean battle(Player player, List<Enemy> enemyList) {

		int turn = 1;

		List<GameCharacter> list = new ArrayList<GameCharacter>();
		list.add(player);
		list.addAll(enemyList);

		player.setEnemyList(enemyList);

		for(GameCharacter enemy : enemyList) {
			enemy.setTarget(player);
		}

		while(true) {
			Logger.debug("turn " + turn);

			//行動順でソート
			Collections.sort(list, comparator);

			Iterator<GameCharacter> iterator = list.iterator();
			while(iterator.hasNext()) {
				GameCharacter gameCharacter = iterator.next();
				// 誰を攻撃するかを決める
				GameCharacter target = gameCharacter.getTarget();
				// 攻撃を行う
				gameCharacter.attacked(target);

				if(target.hp <= 0) {
					if(target instanceof Player) {
						return false;
					} else {
						enemyList.remove(target);
						iterator.remove();
					}
				}
			}

//			for(GameCharacter gameCharacter : list) {
//				// 誰を攻撃するかを決める
//				GameCharacter target = gameCharacter.getTarget();
//				// 攻撃を行う
//				gameCharacter.attacked(target);
//
//				if(target.hp <= 0) {
//					if(target instanceof Player) {
//						return false;
//					} else {
//						enemyList.remove(target);
//						list.remove(target);
//					}
//				}
//			}

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
