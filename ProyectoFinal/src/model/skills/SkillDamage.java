package model.skills;

import model.character.Character;
import model.interfaces.Combatant;

public class SkillDamage extends Skill {

	private int baseDamage;
	private double modAtt;
	private double modDex;
	private double modInt;
	
	/**
	 * Constructor for the SkillDamage class, which initializes the skill's attributes.
	 * @param name
	 * @param description
	 * @param manaCost
	 * @param minLevel
	 * @param damage
	 * @param modAtt
	 * @param modDex
	 * @param modInt
	 */
	public SkillDamage(String id, String name, String description, String type, int manaCost, int minLevel, int damage, double modAtt, double modDex, double modInt) {
		super(id, name, description, type, manaCost, minLevel );
		this.baseDamage = damage;
		this.modAtt = modAtt;
		this.modDex = modDex;
		this.modInt = modInt;
	}

	//GETTERS
	public int getDamageAmount() {
		return baseDamage;
	}

	public double getAttMod() {
		return modAtt;
	}
	
	public double getDexMod() {
		return modDex;
	}
	
	public double getIntMod() {
		return modInt;
	}
	
	/**
	 * Calculates the total damage of the skill based on the base damage and the attacker's attributes.
	 * @param user The character using the skill.
	 * @return The total damage to be applied to the target.
	 */
	public int calculateDamage(Character user) {
		double totalDamage = baseDamage;
		totalDamage += user.getAttack() * modAtt;
		totalDamage += user.getDexterity() * modDex;
		totalDamage += user.getIntelligence() * modInt;
		return (int) totalDamage;
	}
	
	@Override
	public void useSkill(Character user, Combatant target) {
		
		if (user.getMana() < manaCost) {
			System.out.println("Not enough mana to use " + name);
			return;  
		}
		
		user.setMana(user.getMana() - manaCost);
		int damage = calculateDamage(user);
		target.takesDamage(damage);
	} // 

}
