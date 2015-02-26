package creare.sc.models;



public class Enemy extends GameCharacter {

	public Enemy(String name, int hp, int attack, int defense, int speed) {
		super(name, hp, attack, defense, speed);
	}

	@Override
	public String toString() {
		return "Enemy [name=" + name + ", hp=" + hp + ", attack=" + attack + ", defense=" + defense + "]";
	}

}