package model.character;

import model.interfaces.Combatant;

public class Cleric extends Character {
	
	public Cleric(String name) {
		super(name, 20, 14, 3, 3, 8, 4, 3);
	}
	
	@Override
	public void levelUp() {
		level++;
		experienceToNextLevel += 50;
		hpMax += 4;
		hp += 4;
		manaMax += 4;
		mana += 4;
		attack += 1;
		dexterity += 0;
		intelligence += 3;
		defense += 1;
		speed += 1;
	}
	

}
