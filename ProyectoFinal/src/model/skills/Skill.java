package model.skills;

import model.character.Character;
import model.interfaces.Combatant;

/**
 * Abstract class representing a skill that characters can learn and use in combat. 
 * Each skill has an ID, name, mana cost, minimum level requirement, description, 
 * and type. The useSkill method must be implemented by subclasses to define 
 * the specific behavior of the skill when used in combat.
 */
public abstract class Skill {

	protected String id;
	protected String name;
	protected int manaCost;
	protected int minLevel;
	protected String description;
	protected String type;
	
	/**
	 * Constructor for the Skill class.
	 * @param id Unique identifier for the skill.
	 * @param name Name of the skill.
	 * @param type Type of the skill (e.g., "damage", "heal", "buff").
	 * @param description Description of the skill's effect.
	 * @param manaCost Mana cost to use the skill.
	 * @param minLevel Minimum level required to use the skill.
	 */
	public Skill(String id, String name, String type,  String description, int manaCost, int minLevel) {
		this.id = id;
		this.name = name;
		this.manaCost = manaCost;
		this.minLevel = minLevel;
		this.description = description;
		this.type = type;
	}
	
	//GETTERS
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	/**
	 * Executes the skill's effect. This method should be overridden by subclasses 
	 * to define specific behavior for different types of skills (e.g., damage, healing, buffs).
	 */
	/**
	 * Executes the skill's effect. Returns true if the skill was successfully used (e.g., enough mana), false otherwise.
	 */
	public abstract boolean useSkill(Character user, Combatant target);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
