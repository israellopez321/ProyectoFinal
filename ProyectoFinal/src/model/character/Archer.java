package model.character;

import mechanics.SkillRegistry;
import model.interfaces.Combatant;

/**
 * The Archer class represents a character that specializes in ranged attacks. It extends the Character class 
 * and implements the Combatant interface.
 */
public class Archer extends Character {

	/**
	 * Constructor for the Archer class, which initializes the character's attributes.
	 * @param name
	 */
	public Archer(String name) {
		super(name, 24, 6, 6, 8, 3, 4, 6);
		equipItemById("Bow");
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
	public String toString() {
			return "Archer | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}

	@Override
	public void learnSkill() {
		
			    if (level >= 2 && !hasSkill("piercing_shot")) {
	        skills.add(SkillRegistry.get("piercing_shot"));
	        System.out.println(name + " has learned " + SkillRegistry.get("piercing_shot").getName() + "!");
	    }
	    
	    if (level >= 4 && !hasSkill("rain_of_arrows")) {
	        skills.add(SkillRegistry.get("rain_of_arrows"));
	        System.out.println(name + " has learned " + SkillRegistry.get("rain_of_arrows").getName() + "!");
	    }
		
	}
	
	@Override
	public int attack(Combatant target) {
			
			int damage = dexterity + (Weapon != null ? Weapon.getAttackAmount() : 0);
			
			int damageTaken = target.takesDamage(damage);
			
			return damageTaken;
		}
	
}
