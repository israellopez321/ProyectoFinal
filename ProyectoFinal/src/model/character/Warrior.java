package model.character;

import mechanics.SkillRegistry;
import model.interfaces.Combatant;

/**
 * The Warrior class represents a melee combatant character with high attack and defense.
 * It extends the Character class and implements the Combatant interface.
 */
public class Warrior extends Character implements Combatant {

	/**
	 * Constructor Initializes the warrior with the given name and default attributes.
	 * @param name
	 */
	public Warrior(String name) {
		super(name, 30, 5, 8, 4, 2 , 7, 3);
		equipItemById("Sword");
		equipItemById("Plate Armor");
		learnSkill();
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
		
		learnSkill();
	}
	
	@Override
	public void learnSkill() {
		
		if (level >= 2 && !hasSkill("power_strike")) {
	        skills.add(SkillRegistry.get("power_strike"));
	        System.out.println(name + " has learned " + SkillRegistry.get("power_strike").getName() + "!");
	    }
		
		if (level >= 4 && !hasSkill("shield_slam")) {
	        skills.add(SkillRegistry.get("shield_slam"));
	        System.out.println(name + " has learned " + SkillRegistry.get("shield_slam").getName() + "!");
	    }
		
	}
	
	@Override
	public int attack(Combatant target) {
			
			int damage = attack + (Weapon != null ? Weapon.getAttackAmount() : 0);
			
			int damageTaken = target.takesDamage(damage);
			
			return damageTaken;
		}

	@Override
	public String toString() {
			return "Warrior | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}

	

}
