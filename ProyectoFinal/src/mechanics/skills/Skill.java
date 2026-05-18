package mechanics.skills;

public abstract class Skill {

	protected String name;
	protected int manaCost;
	protected int minLevel;
	
	public Skill(String name, int manaCost, int minLevel) {
		this.name = name;
		this.manaCost = manaCost;
		this.minLevel = minLevel;
	}
	
	/**
	 * Executes the skill's effect. This method should be overridden by subclasses to define specific behavior for 
	 * different types of skills (e.g., damage, healing, buffs).
	 */
	public abstract void useSkill();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
