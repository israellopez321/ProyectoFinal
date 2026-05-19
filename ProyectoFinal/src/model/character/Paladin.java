package model.character;

import model.interfaces.Combatant;

public class Paladin extends Character {
	
	public Paladin(String name) {
		super(name, 28, 8, 7, 3, 4, 8, 2);
	}

	@Override
	public void levelUp() {
		level++;
		experienceToNextLevel += 50;
		hpMax += 6;
		hp += 6;
		manaMax += 2;
		mana += 2;
		attack += 2;
		dexterity += 3;
		intelligence += 1;
		defense += 2;
		speed += 0;
	}
	

}
