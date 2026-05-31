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
		setLevel(getLevel() + 1);
		setExperienceToNextLevel(getExperienceToNextLevel() + 50);
		setHpMax(getHpMax() + 4);
		setHp(getHpMax());
		setManaMax(getManaMax() + 10);
		setMana(getManaMax());
		setAttack(getAttack() + 3);
		setDefense(getDefense() + 2);
		setSpeed(getSpeed() + 2);

		learnSkill();
	}
	
	@Override
	public void learnSkill() {
		if (getLevel() >= 1 && !hasSkill("spark")) {
			getSkills().add(SkillRegistry.get("spark"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("spark").getName() + "!");
		}

		if (getLevel() >= 4 && !hasSkill("fireball")) {
			getSkills().add(SkillRegistry.get("fireball"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("fireball").getName() + "!");
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
		return "Mage | name: " + getName() + " | hp: " + getHp() + "/" + getHpMax() + " | mana: " + getMana() + "/" + getManaMax()
				+ " |  attack: " + getAttack() + " | dexterity: " + getDexterity() + " | intelligence: " + getIntelligence() + " | defense: " + getDefense() + " | speed: " + getSpeed() + " | level: " + getLevel()
				+ " | experience: " + getExperience() + "/" + getExperienceToNextLevel() + "|";
	}


	
}