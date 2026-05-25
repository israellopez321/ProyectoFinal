package model.items;

import model.character.Character;
import model.interfaces.Combatant;

public class Armor extends Item {

	private int defenseAmount;
	
	public Armor(String id, String name, String description, int cost, String type, int defenseAmount) {
		super(id, name, description, cost , type);
		this.defenseAmount = defenseAmount;
	}

	public int getDefenseAmount() {
		return defenseAmount;
	}

	@Override
	public void use(Character user, Combatant target) {
	    user.setArmor(this);
	    System.out.println(user.getName() + " equips " + name + "!");
	}
	
}
