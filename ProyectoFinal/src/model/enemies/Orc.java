package model.enemies;

import model.interfaces.Combatant;

public class Orc extends Enemy{

	public Orc(String name, int level) {
		super(name, 40 + (level * 8), 3 + level, 8 + (level * 3), 5 + (level * 2), 3 + level, 10 + (level * 2), 12 + (level * 3));
	}

	@Override
	public int useSkill(Combatant target) {
		// Orc's skill: Power Strike 
		if (mana >= 7) {
			mana -= 7;
			
			int damage = Math.max(0, (int)(attack * 1.8)); // Temporary boost for the next attack
			int damageTaken = target.takesDamage(damage); 
			
			return damageTaken;
		}
		System.out.println("No tiene suficiente mana");
		return -1;		
	}

	@Override
	public int takesHealing(int healing) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "Orc | name:" + name + " | hp: " + hp + "/" + hpMax + " | attack: " + attack + " | defense: "
				+ defense + "| speed: " + speed + "|";
	}
	
}
