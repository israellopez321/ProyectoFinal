package model.items;

import model.interfaces.Combatant;

public class HealItem extends Item{

	private int healAmount;
	
	public HealItem(String id, String name, String description, int cost, int quantity, String type, int healAmount) {
		super(id, name, description, cost , quantity, type);
		this.healAmount = healAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}
	
	@Override
	public void use(Combatant user) {
		user.takesHealing(healAmount);	
	}
	
	
	
}
