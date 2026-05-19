package mechanics.skills;

import model.character.Character;
import model.interfaces.Combatant;

public abstract class Skill {

	protected String id;
	protected String name;
	protected int manaCost;
	protected int minLevel;
	protected String description;
	protected String type;
	
	
	public Skill(String id, String name, String type,  String description, int manaCost, int minLevel) {
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
	 * Executes the skill's effect. This method should be overridden by subclasses to define specific behavior for 
	 * different types of skills (e.g., damage, healing, buffs).
	 */
	public abstract void useSkill(Character user, Combatant target);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
