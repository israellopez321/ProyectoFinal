package model.skills;

import model.character.Character;
import model.interfaces.Combatant;

/**
 * SkillHealer is a subclass of Skill that represents healing abilities. 
 * It calculates the healing amount based on a base value and the user's 
 * intelligence.
 */
public class SkillHealer extends Skill {

	private int healAmount;
	private double modInt;
	
	/**
	 * Constructor for SkillHealer.
	 * @param id Unique identifier for the skill.
	 * @param name Name of the skill.
	 * @param description Description of the skill's effect.
	 * @param type Type of the skill (e.g., "heal").
	 * @param minLevel Minimum level required to use the skill.
	 * @param manaCost Mana cost to use the skill.
	 * @param healAmount Base healing amount of the skill.
	 * @param modInt Modifier for intelligence that affects healing.
	 */
	public SkillHealer(String id, String name, String description,String type, int minLevel, int manaCost, int healAmount, double modInt) {
		super(id, name, description, type , manaCost, minLevel);
		this.healAmount = healAmount;
		this.modInt = modInt;
	}

	// Getters
	
	public int getHealAmount() {
		return healAmount;
	}
	
	public double getIntMod() {
		return modInt;
	}

	/**
	 * Calculates the total healing amount based on the base heal and the user's intelligence.
	 * @param healer The character using the healing skill.
	 * @return The total healing amount.
	 */
	public int calculateHeal(Character healer) {
		double totalHeal = healAmount;
		totalHeal += healer.getIntelligence() * modInt;
		return (int) totalHeal;
	}
	
	
	@Override
	public boolean useSkill(Character user , Combatant target) {
		if (user.getMana() < manaCost) {
			System.out.println("Not enough mana to use " + name);
			return false;
		}

		user.setMana(user.getMana() - manaCost);
		int healAmount = calculateHeal(user);
		target.takesHealing(healAmount);
		return true;
	}

}
