package model.items;

import model.character.Character;


public class DamageItem extends Item{

	private int damageAmount;
	
	public DamageItem(String id, String name, String description,int cost , int quantity, String type, int damageAmount) {
		super(id, name, description, cost , quantity, type);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}
	
	@Override
	public void use(Character user) {
		user.takesDamage(damageAmount);	
	}
	
}
