package model.character;

import model.interfaces.Combatant;

public class Warrior extends Character implements Combatant {

	/**
	 * Constructor Initializes the warrior with the given name and default attributes.
	 * @param name
	 */
	public Warrior(String name) {
		super(name, 30, 5, 8, 4, 2 , 7, 3);
	}

	@Override
	public void levelUp() {
		level++;
		experienceToNextLevel += 50;
		hpMax += 7;
		hp += 7;
		manaMax += 1;
		mana += 1;
		attack += 2;
		dexterity += 1;
		intelligence += 0;
		defense += 2;
		speed += 1;
	}
	

	@Override
	public String toString() {
			return "Warrior | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}

}
