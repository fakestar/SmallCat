package creare.sc.models;

import java.util.ArrayList;
import java.util.List;

import play.Logger;

public abstract class GameCharacter {

	public String name;
	public int hp;
	public int attack;
	public int defense;
	public int speed;

	private List<GameEventListener> listenerList = new ArrayList<GameEventListener>();

	private GameCharacter target;
	private int weight;

	public GameCharacter(String name, int hp, int attack, int defense, int speed) {
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;

		this.refresh();
	}

	public void damaged(int attack) {
		int damage = 1;
		if(this.defense < attack) {
			damage = attack - this.defense;
		}
		this.hp = this.hp - damage;

		if(this.hp <= 0) {

			for(GameEventListener listener: listenerList) {
				listener.actionPerformed(new PlayerLoseEvent());
			}
		}

	}
	public void refresh() {
		weight = (int) (Math.random() * 4);
	}

	protected void setTarget(GameCharacter target) {
		this.target = target;
	}

	public GameCharacter getTarget() {
		return target;
	}

	public void attacked(GameCharacter target) {
		target.damaged(this.attack);
		Logger.debug("attack " + name + " >> " + target.name + " [" + target.hp + "]");
	}

	public int getActionSpeed() {

		return this.speed + weight;
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



}