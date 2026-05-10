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
	 */
	public Character(String name, int hp, int hpMax, int mana, int manaMax, int attack, int defense, int speed) {
		super();
		this.name = name;
		this.hp = hp;
		this.hpMax = hpMax;
		this.mana = mana;
		this.manaMax = manaMax;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
	}

	//GETTERS AND SETTERS
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	publlic void 
	
	
	

}
