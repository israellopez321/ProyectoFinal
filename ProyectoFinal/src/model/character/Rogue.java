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
	
	@Override
	public int useSkill(Combatant target) {
		if (mana < 8) {
			return -1;
		}

		mana -= 8;
		int damage = attack + 6; // backstab
		int applied = target.takesDamage(damage);
		return applied;
	}
	
	@Override
	public String toString() {
			return "Rogue | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}

}
