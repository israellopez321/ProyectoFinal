package model.interfaces;

public interface TakesDamage {
	
	/**
	 * The combatant takes incoming damage; You must reduce it with your defense, 
	 * upgrade your life points and return the actual damage received.
	 * 
	 * @param incomingDamage The damage that the combatant is about to receive before applying defense and hp reduction.
	 * @return The real damage received
	 */
	public int takeDamage(int damage);
	
}
