package model.items;

import model.character.Character;


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
	public void use(Character user) {
		user.takesHealing(healAmount);	
	}
	
	
	
}
