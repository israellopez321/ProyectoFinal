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
		setLevel(getLevel() + 1);
		setExperienceToNextLevel(getExperienceToNextLevel() + 50);
		setHpMax(getHpMax() + 4);
		setHp(getHp() + 4);
		setManaMax(getManaMax() + 4);
		setMana(getMana() + 4);
		setAttack(getAttack() + 1);
		setDexterity(getDexterity() + 0);
		setIntelligence(getIntelligence() + 3);
		setDefense(getDefense() + 1);
		setSpeed(getSpeed() + 1);

		learnSkill();
	}

	@Override
	public String toString() {
			return "Cleric | name: " + getName() + " | hp: " + getHp() + "/" + getHpMax() + " | mana: " + getMana() + "/" + getManaMax()
				+ " |  attack: " + getAttack() + " | dexterity: " + getDexterity()  + " | intelligence: " + getIntelligence() + " | defense: " + getDefense() + " | speed: " + getSpeed() + " | level: " + getLevel()
				+ " | experience: " + getExperience() + "/" + getExperienceToNextLevel() + "|";
	}

	@Override
	public void learnSkill() {
		if (getLevel() >= 1 && !hasSkill("divine_heal")) {
			getSkills().add(SkillRegistry.get("divine_heal"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("divine_heal").getName() + "!");
		}

		if (getLevel() >= 4 && !hasSkill("holy_light")) {
			getSkills().add(SkillRegistry.get("holy_light"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("holy_light").getName() + "!");
		}
		
	}
	
	@Override
	public int attack(Combatant target) {
		int damage = getAttack() + (getWeapon() != null ? getWeapon().getAttackAmount() : 0);

		int damageTaken = target.takesDamage(damage);

		return damageTaken;
		}
	
}

