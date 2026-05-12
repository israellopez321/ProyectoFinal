package model.character;

import java.util.Objects;

import model.interfaces.Combatant;

/**
 * Abstract class representing a character in the game. It contains common attributes and methods for all characters.
 * @author Israel López
 *
 */
public abstract class Character implements Combatant {
	
	protected String name;
	protected int hp;
	protected int hpMax;
	protected int mana;
	protected int manaMax;
	protected int attack;
	protected int defense;
	protected int speed;
	protected int level;
	protected int experience;
	protected int experienceToNextLevel;
	protected boolean isDefending = false;
	
	/**
	 * Constructor Initializes the character with the given parameters.
	 * @param name
	 * @param hp
	 * @param hpMax
	 * @param mana
	 * @param manaMax
	 * @param attack
	 * @param defense
	 * @param speedS
	 */
	public Character(String name, int hp, int hpMax, int mana, int manaMax, int attack, int defense, int speed) {
		this.name = name;
		this.hp = hp;
		this.hpMax = hpMax;
		this.mana = mana;
		this.manaMax = manaMax;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.level = 1;
		this.experience = 0;
		this.experienceToNextLevel = 100;
	}

	// Getters and setters for the character attributes
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getManaMax() {
		return manaMax;
	}

	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	@Override
	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	@Override
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	// Implementation of Combatant interface methods
	
	@Override
	/**
	 * Method to check if he is alive
	 */
	public boolean isAlive() {
		return hp > 0;
	}
	
	@Override
	public int attack(Combatant target) {
		
		int damageTaken = target.takesDamage(this.attack);
		return damageTaken;
	}
	
	@Override
	public void defend() {
		
		isDefending = true;
		
	}
	
	@Override
	public int takesDamage(int damage) {
		
		int damageTaken = Math.max(0, damage - defense);
		
		damageTaken = isDefending ? damageTaken / 2 : damageTaken;
		
		this.hp = Math.max(0, this.hp - damageTaken);
		
		isDefending = false;
		
		return damageTaken;
	}
	
	/**
	 * Method to gain experience and handle level up if experience exceeds the threshold.
	 * @param exp
	 */
	public void gainExperience(int exp) {
		this.experience += exp;
		while (this.experience >= this.experienceToNextLevel) {
			this.experience -= this.experienceToNextLevel;
			levelUp();
		}
	}
	
	@Override
	public abstract int useSkill(Combatant target);
	
	public abstract void levelUp();

	@Override
	public int hashCode() {
		return Objects.hash(attack, defense, experience, experienceToNextLevel, hp, hpMax, level, mana, manaMax, name,
				speed);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		return  Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Character | name: " + name + " | hp: " + hp + "/" + hpMax + " | mana: " + mana + "/" + manaMax
				+ " |  attack: " + attack + "| defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}
	
	
	

}
