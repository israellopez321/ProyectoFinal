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
		setLevel(getLevel() + 1);
		setExperienceToNextLevel(getExperienceToNextLevel() + 50);
		setHpMax(getHpMax() + 5);
		setHp(getHp() + 5);
		setManaMax(getManaMax() + 2);
		setMana(getMana() + 2);
		setAttack(getAttack() + 2);
		setDexterity(getDexterity() + 3);
		setIntelligence(getIntelligence() + 1);
		setDefense(getDefense() + 1);
		setSpeed(getSpeed() + 2);

		learnSkill();
	}
	
	@Override
	public void learnSkill() {
		if (getLevel() >= 2 && !hasSkill("quick_stab")) {
			getSkills().add(SkillRegistry.get("quick_stab"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("quick_stab").getName() + "!");
		}

		if (getLevel() >= 4 && !hasSkill("poison_dagger")) {
			getSkills().add(SkillRegistry.get("poison_dagger"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("poison_dagger").getName() + "!");
		}
		
	}
	
	@Override
	public int attack(Combatant target) {
		int damage = getDexterity() + (getWeapon() != null ? getWeapon().getAttackAmount() : 0);

		int damageTaken = target.takesDamage(damage);

		return damageTaken;
		}
	
	@Override
	public String toString() {
		return "Rogue | name: " + getName() + " | hp: " + getHp() + "/" + getHpMax() + " | mana: " + getMana() + "/" + getManaMax()
				+ " |  attack: " + getAttack() + " | dexterity: " + getDexterity() + " | intelligence: " + getIntelligence() + " | defense: " + getDefense() + " | speed: " + getSpeed() + " | level: " + getLevel()
				+ " | experience: " + getExperience() + "/" + getExperienceToNextLevel() + "|";
	}

	

}
