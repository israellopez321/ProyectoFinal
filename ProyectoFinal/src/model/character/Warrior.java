package model.character;

import model.interfaces.Combatant;

public class Warrior extends Character implements Combatant {

	/**
	 * Constructor Initializes the warrior with the given name and default attributes.
	 * @param name
	 */
	public Warrior(String name) {
		super(name, 100, 100, 30, 30, 18, 8, 6);
	}

	@Override
	public void levelUp() {
		level++;
		experience = 0;
		experienceToNextLevel += 50;
		hpMax += 20;
		hp = hpMax;
		manaMax += 5;
		mana = manaMax;
		attack += 4;
		defense += 3;
		speed += 1;
	}
	
	@Override
	public void useSkill(Combatant target) {
		if (mana >= 10) {
			mana -= 10;
			attack += 5; // Temporary boost for the next attack
		}
	}

}
