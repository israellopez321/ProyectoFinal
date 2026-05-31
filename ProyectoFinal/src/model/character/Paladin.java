package model.character;

import mechanics.SkillRegistry;
import model.interfaces.Combatant;

/**
 * The Paladin class represents a character that is a holy warrior, combining physical strength with divine magic.
 * It extends the Character class and implements the Combatant interface, allowing it to engage in combat.
 */
public class Paladin extends Character {
	
	/**
	 * Constructor for the Paladin class, which initializes the character's attributes and equips starting items.
	 * @param name The name of the Paladin character.
	 */
	public Paladin(String name) {
		super(name, 28, 8, 7, 3, 4, 8, 2);
		equipItemById("Sword");
		equipItemById("Plate Armor");
		learnSkill();
	}

	@Override
	public void levelUp() {
		level++;
		experienceToNextLevel += 50;
		hpMax += 6;
		hp += 6;
		manaMax += 2;
		mana += 2;
		attack += 2;
		dexterity += 3;
		intelligence += 1;
		defense += 2;
		speed += 0;
		
		learnSkill();
	}
	
	@Override
	public String toString() {
			return "Paladin | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}

	@Override
	public void learnSkill() {
		
		 if (level >= 2 && !hasSkill("smite")) {
		        skills.add(SkillRegistry.get("smite"));
		        System.out.println(name + " has learned " + SkillRegistry.get("smite").getName() + "!");
		    }
		 
		 if (level >= 4 && !hasSkill("blessing")) {
		        skills.add(SkillRegistry.get("blessing"));
		        System.out.println(name + " has learned " + SkillRegistry.get("blessing").getName() + "!");
		    }
		
	}
	
	@Override
	public int attack(Combatant target) {
			
			int damage = attack + (Weapon != null ? Weapon.getAttackAmount() : 0);
			
			int damageTaken = target.takesDamage(damage);
			
			return damageTaken;
		}
	
}
