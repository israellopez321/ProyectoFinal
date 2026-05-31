package model.character;

import mechanics.SkillRegistry;
import model.interfaces.Combatant;

/**
 * The Rogue class represents a character that specializes in dexterity and speed. 
 * Rogues are agile and can deal high damage with their attacks, but they have lower defense and health compared to other classes.
 */
public class Rogue extends Character {
	
	/**
	 * Constructor for the Rogue class, which initializes the character's attributes.
	 * @param name
	 */
	public Rogue(String name) {
		super(name, 22, 6, 5, 8, 3, 3, 8);
		equipItemById("Dagger");
		equipItemById("Leather Armor");
		learnSkill();
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
		
		learnSkill();
	}
	
	@Override
	public void learnSkill() {
		
			if (level >= 2 && !hasSkill("quick_stab")) {
		        skills.add(SkillRegistry.get("quick_stab"));
		        System.out.println(name + " has learned " + SkillRegistry.get("quick_stab").getName() + "!");
		    }
		    
		    if (level >= 4 && !hasSkill("poison_dagger")) {
		        skills.add(SkillRegistry.get("poison_dagger"));
		        System.out.println(name + " has learned " + SkillRegistry.get("poison_dagger").getName() + "!");
		    }
		
	}
	
	@Override
	public int attack(Combatant target) {
			
			int damage = dexterity + (Weapon != null ? Weapon.getAttackAmount() : 0);
			
			int damageTaken = target.takesDamage(damage);
			
			return damageTaken;
		}
	
	@Override
	public String toString() {
			return "Rogue | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}

	

}
