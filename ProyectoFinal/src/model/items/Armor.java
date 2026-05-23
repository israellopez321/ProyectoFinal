package model.items;

import model.character.Character;

public class Armor extends Item {

	private int defenseAmount;
	
	public Armor(String id, String name, String description, int cost, int quantity, String type, int defenseAmount) {
		super(id, name, description, cost , quantity, type);
		this.defenseAmount = defenseAmount;
	}

	public int getDefenseAmount() {
		return defenseAmount;
	}

	@Override
	public void use(Character user) {
		user.setArmor(this);
	}
	
}
