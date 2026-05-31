package model.character;

import mechanics.SkillRegistry;
import model.interfaces.Combatant;

/**
 * The Mage class represents a character that specializes in magic. It extends the Character class 
 * and implements the Combatant interface.
 */
public class Mage extends Character implements Combatant {

	/**
	 * Constructor for the Mage class, which initializes the character's attributes.
	 * @param name
	 */
	public Mage(String name) {
		super(name,18, 16, 2, 3, 9, 5, 4);
		equipItemById("Staff");
		equipItemById("Robe");
		learnSkill();
	}

	@Override
	public void levelUp() {
		level++;
		experienceToNextLevel += 50;
		hpMax += 4;
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

	    if (level >= 1 && !hasSkill("spark")) {
	        skills.add(SkillRegistry.get("spark"));
	        System.out.println(name + " has learned " + SkillRegistry.get("spark").getName() + "!");
	    }
	    
	    if (level >= 4 && !hasSkill("fireball")) {
	        skills.add(SkillRegistry.get("fireball"));
	        System.out.println(name + " has learned " + SkillRegistry.get("fireball").getName() + "!");
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
			return "Mage | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}


	
}