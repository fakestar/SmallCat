package creare.sc.models;

import java.util.List;

public class CharacterUnit {

	private List<GameCharacter> unit;

	public CharacterUnit(List<GameCharacter> unit) {
		this.unit = unit;
	}

	public void addCharacter(GameCharacter gameCharacter) {
		this.unit.add(gameCharacter);
	}
}
