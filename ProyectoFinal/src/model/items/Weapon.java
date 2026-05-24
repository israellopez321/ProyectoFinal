package model.items;

import model.character.Character;

public class Weapon extends Item {

	private int attackAmount;
	
	public Weapon(String id, String name, String description, int cost, String type, int attackAmount) {
		super(id, name, description, cost , type);
		this.attackAmount = attackAmount;
	}

	public int getAttackAmount() {
		return attackAmount;
	}

	@Override
	public void use(Character user) {
		 user.setWeapon(this);		
	}

}
