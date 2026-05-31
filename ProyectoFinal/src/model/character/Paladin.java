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
		setLevel(getLevel() + 1);
		setExperienceToNextLevel(getExperienceToNextLevel() + 50);
		setHpMax(getHpMax() + 6);
		setHp(getHp() + 6);
		setManaMax(getManaMax() + 2);
		setMana(getMana() + 2);
		setAttack(getAttack() + 2);
		setDexterity(getDexterity() + 3);
		setIntelligence(getIntelligence() + 1);
		setDefense(getDefense() + 2);
		setSpeed(getSpeed() + 0);

		learnSkill();
	}
	
	@Override
	public String toString() {
			return "Paladin | name: " + getName() + " | hp: " + getHp() + "/" + getHpMax() + " | mana: " + getMana() + "/" + getManaMax()
				+ " |  attack: " + getAttack() + " | dexterity: " + getDexterity()  + " | intelligence: " + getIntelligence() + " | defense: " + getDefense() + " | speed: " + getSpeed() + " | level: " + getLevel()
				+ " | experience: " + getExperience() + "/" + getExperienceToNextLevel() + "|";
	}

	@Override
	public void learnSkill() {
		if (getLevel() >= 2 && !hasSkill("smite")) {
			getSkills().add(SkillRegistry.get("smite"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("smite").getName() + "!");
		}

		if (getLevel() >= 4 && !hasSkill("blessing")) {
			getSkills().add(SkillRegistry.get("blessing"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("blessing").getName() + "!");
		}
		
	}
	
	@Override
	public int attack(Combatant target) {
		int damage = getAttack() + (getWeapon() != null ? getWeapon().getAttackAmount() : 0);

		int damageTaken = target.takesDamage(damage);

		return damageTaken;
		}
	
}
