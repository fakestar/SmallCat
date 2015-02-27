package creare.sc.models;

import java.util.ArrayList;
import java.util.List;

public class Stage {

	private String name;

	public Stage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getMaxFloorCount() {
		return 2;
	}

	public void nextFloor() {

	}

	public List<Enemy> getEnemyList() {
		List<Enemy> list = new ArrayList<Enemy>();

		list.add(new Enemy("mob1", 30, 10, 15, 12, 18));
		list.add(new Enemy("mob2", 20, 10, 12, 18, 15));

		return list;
	}

	public Floor getFloor(int i) {
		return new Floor();
	}
	public class Floor {
		public int getMaxBattleCount(){
			return 3;
		}

		public boolean isEncounter() {
			int encounter = (int) (Math.random() * 2);
			boolean bool = false;
			if(0 < encounter) {
				bool = true;
			}
			return bool;
		}
	}
}
