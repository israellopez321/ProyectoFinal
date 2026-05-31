package model.character;

import mechanics.SkillRegistry;
import model.interfaces.Combatant;

/**
 * The Cleric class represents a character that specializes in healing and support. It extends the Character class 
 * and implements the Combatant interface.
 */
public class Cleric extends Character {
	
	/**
	 * Constructor for the Cleric class, which initializes the character's attributes and equips starting items.
	 * @param name The name of the Cleric character.
	 */
	public Cleric(String name) {
		super(name, 20, 14, 3, 3, 8, 4, 3);
		
		equipItemById("Staff");
		equipItemById("Robe");
		learnSkill();
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
		
		learnSkill();
	}

	@Override
	public String toString() {
			return "Cleric | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}

	@Override
	public void learnSkill() {
		
			    if (level >= 1 && !hasSkill("divine_heal")) {
	        skills.add(SkillRegistry.get("divine_heal"));
	        System.out.println(name + " has learned " + SkillRegistry.get("divine_heal").getName() + "!");
	    }
	    
	    if (level >= 4 && !hasSkill("holy_light")) {
	        skills.add(SkillRegistry.get("holy_light"));
	        System.out.println(name + " has learned " + SkillRegistry.get("holy_light").getName() + "!");
	    }
		
	}
	
	@Override
	public int attack(Combatant target) {
			
			int damage = attack + (Weapon != null ? Weapon.getAttackAmount() : 0);
			
			int damageTaken = target.takesDamage(damage);
			
			return damageTaken;
		}
	
}

