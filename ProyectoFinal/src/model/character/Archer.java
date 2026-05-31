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
	public String toString() {
			return "Archer | name: " + getName() + " | hp: " + getHp() + "/" + getHpMax() + " | mana: " + getMana() + "/" + getManaMax()
				+ " |  attack: " + getAttack() + " | dexterity: " + getDexterity()  + " | intelligence: " + getIntelligence() + " | defense: " + getDefense() + " | speed: " + getSpeed() + " | level: " + getLevel()
				+ " | experience: " + getExperience() + "/" + getExperienceToNextLevel() + "|";
	}

	@Override
	public void learnSkill() {
		if (getLevel() >= 2 && !hasSkill("piercing_shot")) {
			getSkills().add(SkillRegistry.get("piercing_shot"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("piercing_shot").getName() + "!");
		}

		if (getLevel() >= 4 && !hasSkill("rain_of_arrows")) {
			getSkills().add(SkillRegistry.get("rain_of_arrows"));
			System.out.println(getName() + " has learned " + SkillRegistry.get("rain_of_arrows").getName() + "!");
		}
		
	}
	
	@Override
	public int attack(Combatant target) {
		int damage = getDexterity() + (getWeapon() != null ? getWeapon().getAttackAmount() : 0);

		int damageTaken = target.takesDamage(damage);

		return damageTaken;
		}
	
}
