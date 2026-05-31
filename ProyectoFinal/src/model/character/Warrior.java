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
		setLevel(getLevel() + 1);
		setExperienceToNextLevel(getExperienceToNextLevel() + 50);
		setHpMax(getHpMax() + 7);
		setHp(getHp() + 7);
		setManaMax(getManaMax() + 1);
		setMana(getMana() + 1);
		setAttack(getAttack() + 2);
		setDexterity(getDexterity() + 1);
		setIntelligence(getIntelligence() + 0);
		setDefense(getDefense() + 2);
		setSpeed(getSpeed() + 1);

		learnSkill();
	}
	
	@Override
	public void learnSkill() {
		if (getLevel() >= 2 && !hasSkill("power_strike")) {
			getSkills().add(SkillRegistry.get("power_strike"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("power_strike").getName() + "!");
		}

		if (getLevel() >= 4 && !hasSkill("shield_slam")) {
			getSkills().add(SkillRegistry.get("shield_slam"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("shield_slam").getName() + "!");
		}
		
	}
	
	@Override
	public int attack(Combatant target) {
		int damage = getAttack() + (getWeapon() != null ? getWeapon().getAttackAmount() : 0);

		int damageTaken = target.takesDamage(damage);

		return damageTaken;
		}

	@Override
	public String toString() {
		return "Warrior | name: " + getName() + " | hp: " + getHp() + "/" + getHpMax() + " | mana: " + getMana() + "/" + getManaMax()
				+ " |  attack: " + getAttack() + " | dexterity: " + getDexterity() + " | intelligence: " + getIntelligence() + " | defense: " + getDefense() + " | speed: " + getSpeed() + " | level: " + getLevel()
				+ " | experience: " + getExperience() + "/" + getExperienceToNextLevel() + "|";
	}

	

}
