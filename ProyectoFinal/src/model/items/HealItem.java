package model.items;

import model.character.Character;
import model.interfaces.Combatant;


public class HealItem extends Item{

	private int healAmount;
	
	public HealItem(String id, String name, String description, int cost, String type, int healAmount) {
		super(id, name, description, cost , type);
		this.healAmount = healAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}
	
	@Override
	public void use(Character user, Combatant target) {
	    int healed = user.takesHealing(healAmount);
	    System.out.println(user.getName() + " uses " + name + " and heals for " + healed + " HP!");
	}
	
	
	
}
