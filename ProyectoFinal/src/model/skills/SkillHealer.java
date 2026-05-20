package model.skills;

import model.character.Character;
import model.interfaces.Combatant;

public class SkillHealer extends Skill {

	private int healAmount;
	private double modInt;
	
	public SkillHealer(String id, String name, String description,String type, int minLevel, int manaCost, int healAmount, double modInt) {
		super(id, name, description, type , manaCost, minLevel);
		this.healAmount = healAmount;
		this.modInt = modInt;
	}

	public int getHealAmount() {
		return healAmount;
	}
	
	public double getIntMod() {
		return modInt;
	}

	public int calculateHeal(Character healer) {
		double totalHeal = healAmount;
		totalHeal += healer.getIntelligence() * modInt;
		return (int) totalHeal;
	}
	
	
	@Override
	public void useSkill(Character user , Combatant target) {
		if (user.getMana() < manaCost) {
			System.out.println("Not enough mana to use " + name);
			return;  
		}
		
		user.setMana(user.getMana() - manaCost);
		int healAmount = calculateHeal(user);
		target.takesHealing(healAmount);
	}

}
