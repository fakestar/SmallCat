package creare.sc.models;

import java.util.ArrayList;
import java.util.List;

import play.Logger;

public abstract class GameCharacter {

	public final String name;
	public final int maxHp;
	public final int maxMp;
	public final int atk;
	public final int def;
	public final int dex;

	private int hp;
	private int mp;

	private List<GameEventListener> listenerList = new ArrayList<GameEventListener>();

	private int weight;

	public GameCharacter(String name, int maxHp, int maxMp, int atk, int def, int dex) {
		this.name = name;
		this.maxHp = maxHp;
		this.maxMp = maxMp;
		this.atk = atk;
		this.def = def;
		this.dex = dex;

		this.hp = maxHp;
		this.mp = maxMp;
		this.refresh();
	}

	public int getHp() {
		return hp;
	}

	public int getMp() {
		return mp;
	}

	public boolean isDead() {
		if(0 < hp) {
			return false;
		} else {
			return true;
		}
	}

	public abstract void action();

	public <T extends GameCharacter> GameCharacter getTarget(List<T> list) {
		// 攻撃対象の決定
		int target = (int) (Math.random() * list.size());

		return list.get(target);
	}

	public void attack(GameCharacter target) {

		Logger.debug("  attack " + name + " >> " + target.name + " [" + target.hp + "/" + target.maxHp + "]");
		target.damaged(this.atk);
	}

	private void damaged(int atk) {
		// ダメージ計算
		int damage = (int)(atk + Math.random() * (atk -this.def)) - def;
		// 最低保障ダメージ
		if(damage <= 0) {
			damage = 1;
		}

		this.hp = this.hp - damage;

		Logger.debug("    " + name + " " + damage + "damage. "+ name + " [" + hp + "/" + maxHp + "]");

		// 死亡時
		if(this.isDead()) {
			Logger.debug("    " + name + " is done.");

			for(GameEventListener listener: listenerList) {
				listener.actionPerformed(new PlayerLoseEvent());
			}
		}
	}

	public void refresh() {
		weight = (int) (Math.random() * (this.dex / 4));
	}

	public int getActionSpeed() {

		return this.dex + weight;
	}

	public void addListener(GameEventListener listener) {
		listenerList.add(listener);
	}

	public void removeListener(GameEventListener listener) {
		listenerList.remove(listener);
	}

	public GameEventListener[] getListener() {
		return listenerList.toArray(new GameEventListener[listenerList.size()]);
	}

	@Override
	public String toString() {
		return "[name=" + name + ", hp=" + hp + ", atk=" + atk + ", def=" + def + "]";
	}

}