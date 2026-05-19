package model.character;

import model.interfaces.Combatant;

public class Rogue extends Character {
	
	public Rogue(String name) {
		super(name, 22, 6, 5, 8, 3, 3, 8);
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
	
}
