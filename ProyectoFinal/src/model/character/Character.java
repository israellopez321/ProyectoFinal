package model.character;

import java.util.ArrayList;
import java.util.Objects;

import mechanics.ItemRegistry;
import model.interfaces.Combatant;
import model.items.Armor;
import model.items.Item;
import model.items.Weapon;
import model.skills.Skill;

/**
 * Abstract class representing a character in the game. It contains common 
 * attributes and methods for all characters.
 * @author Israel López
 *
 */
public abstract class Character implements Combatant {
	
	private String name;
	private int hp;
	private int hpMax;
	private int mana;
	private int manaMax;
	private int attack;
	private int dexterity;
	private int intelligence;
	private int defense;
	private int speed;
	private int level;
	private int experience;
	private int experienceToNextLevel;
	private boolean isDefending = false;
	private ArrayList<Skill> skills = new ArrayList<>();
	private Weapon Weapon;
	private Armor Armor;

	
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
	public Character(String name, int hp, int mana, int attack, int dexterity, int intelligence , int defense, int speed) {
		this.name = name;
		this.hp = hp;
		this.hpMax = hp;
		this.mana = mana;
		this.manaMax = mana;
		this.attack = attack;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.defense = defense;
		this.speed = speed;
		this.level = 1;
		this.experience = 0;
		this.experienceToNextLevel = 100 + level * 50;
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
	
	public int getDexterity() {
		return dexterity;
	}
    
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	
	public int getIntelligence() {
		return intelligence;
	}
    
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
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
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public int getExperienceToNextLevel() {
		return experienceToNextLevel;
	}
	
	public void setExperienceToNextLevel(int experienceToNextLevel) {
		this.experienceToNextLevel = experienceToNextLevel;
	}
	
	public ArrayList<Skill> getSkills() {
		return skills;
	}
	
	public void setWeapon(Weapon weapon) {
		this.Weapon = weapon;
	}
	
	public Weapon getWeapon() {
		return Weapon;
	}
	
	public void setArmor(Armor armor) {
		this.Armor = armor;
	}
	
	public Armor getArmor() {
		return Armor;
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
	public void defend() {
		isDefending = true;
		
	}
	
	/**
	 * Method to check if the character has a skill with the given id
	 * @param id
	 * @return
	 */
	public boolean hasSkill(String id) {
	    return skills.stream().anyMatch(s -> s.getId().equals(id));
	}

	
	@Override
	public int takesDamage(int damage) {
		
		int damageTaken = Math.max(1, damage - defense - (Armor != null ? Armor.getDefenseAmount() : 0));
		
		damageTaken = isDefending ? damageTaken / 2 : damageTaken;
		
		this.hp = Math.max(1, this.hp - damageTaken);
		
		isDefending = false;
		
		return damageTaken;
	}
	
	@Override
	public int takesHealing(int healing) {
		int actualHealing = Math.min(healing, hpMax - hp);
		this.hp += actualHealing;
		return actualHealing;
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
	
	/**
	 * Method to equip an item by its id. It checks the type of the item and equips it accordingly.
	 * @param itemId
	 */
	public void equipItemById(String itemId) {
	    Item item = ItemRegistry.get(itemId);
	    if (item instanceof Weapon) setWeapon((Weapon) item);
	    else if (item instanceof Armor) setArmor((Armor) item);
	}
	
	/**
	 * Abstract method to learn a new skill. Each character class learns skills differently
	 * @param skill
	 */
	public abstract void learnSkill();
	
	/**
	 * Abstract method to handle leveling up. Each character class upgrades its attributes differently when leveling up.
	 */
	public abstract void levelUp();

	@Override
	public abstract int attack(Combatant target);
	
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
				+ " |  attack: " + attack + " | dexterity: " + dexterity  + " | intelligence: " + intelligence + " | defense: " + defense + " | speed: " + speed + " | level: " + level
				+ " | experience: " + experience + "/" + experienceToNextLevel + "|";
	}
	
	
	
	
	

}
