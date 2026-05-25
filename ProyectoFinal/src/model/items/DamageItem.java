package model.items;

import model.character.Character;
import model.interfaces.Combatant;


public class DamageItem extends Item{

	private int damageAmount;
	
	public DamageItem(String id, String name, String description,int cost , String type, int damageAmount) {
		super(id, name, description, cost, type);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}
	
	@Override
	public void use(Character user, Combatant target) {
	    int dmg = target.takesDamage(damageAmount);
	    System.out.println(user.getName() + " uses " + name + " on " + target.getName() + " for " + dmg + " damage!");
	}
	
}
