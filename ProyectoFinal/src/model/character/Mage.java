package model.character;

import mechanics.skills.SkillRegistry;
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
		
		learnSkill();
	}
	
	@Override
	public void learnSkill() {

	    if (level >= 2 && !hasSkill("fireball")) {
	        skills.add(SkillRegistry.get("fireball"));
	        System.out.println(name + " has learned " + SkillRegistry.get("fireball").getName() + "!");
	    }

	}
	
	
	@Override
	public String toString() {
			return "Mage | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}


	
}