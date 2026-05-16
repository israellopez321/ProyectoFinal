package model.character;

import model.interfaces.Combatant;

public class Mage extends Character implements Combatant {

	/**
	 * Constructor for the Mage class, which initializes the character's attributes.
	 * @param name
	 */
	public Mage(String name) {
		super(name,60, 60, 30, 30, 12, 4, 10);
	}

	@Override
	public void levelUp() {
		level++;
		experienceToNextLevel += 50;
		hpMax += 15;
		hp = hpMax;
		manaMax += 10;
		mana = manaMax;
		attack += 3;
		defense += 2;
		speed += 2;
	}
	
	@Override
	public int useSkill(Combatant target) {
		if (mana < 15) {
			return -1;
		}

		mana -= 15;
		int damage = attack + 7; // magic burst
		int applied = target.takesDamage(damage);
		return applied;
	}
	
}