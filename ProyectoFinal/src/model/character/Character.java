package model.character;



/**
 * Abstract class representing a character in the game. It contains common attributes and methods for all characters.
 * @author Israel López
 *
 */
public abstract class Character {
	
	protected String name;
	protected int hp;
	protected int hpMax;
	protected int mana;
	protected int manaMax;
	protected int attack;
	protected int defense;
	protected int speed;
	protected int level;
	
	/**
	 * Constructor Initializes the character with the given parameters.
	 * @param name
	 * @param hp
	 * @param hpMax
	 * @param mana
	 * @param manaMax
	 * @param attack
	 * @param defense
	 * @param speed
	 * @param level
	 */
	public Character(String name, int hp, int hpMax, int mana, int manaMax, int attack, int defense, int speed, int level) {
		super();
		this.name = name;
		this.hp = hp;
		this.hpMax = hpMax;
		this.mana = mana;
		this.manaMax = manaMax;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.level = level;
	}

	// Getters and setters for the character attributes
	public String getName() {
		return name;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	
	public boolean isAlive() {
		return hp > 0;
	}
	
	
	
	
	

	
	

}
