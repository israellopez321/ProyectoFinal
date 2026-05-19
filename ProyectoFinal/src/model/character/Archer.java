package model.character;

import model.interfaces.Combatant;

public class Archer extends Character {

	/**
	 * Constructor for the Archer class, which initializes the character's attributes.
	 * @param name
	 */
	public Archer(String name) {
		super(name, 24, 6, 6, 8, 3, 4, 6);
	}
	
	@Override
	public void levelUp() {
		level++;
		experienceToNextLevel += 50;
		hpMax += 5;
		hp += 5;
		manaMax += 2;
		mana += 2;
		attack += 2;
		dexterity += 3;
		intelligence += 1;
		defense += 1;
		speed += 2;
	}

	@Override
	public String toString() {
			return "Archer | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}
	
}
