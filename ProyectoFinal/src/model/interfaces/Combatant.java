package model.interfaces;

public interface Combatant {

	/**
	 * Attack a target. The method must return cause to the target
	 * @param target
	 * @return
	 */
	int attack(Combatant target);
	
	/**
	* The combatant takes incoming damage; You must reduce it with your defense, 
	* upgrade your life points and return the actual damage received.
	* 
	* @param incomingDamage The damage that the combatant is about to receive before applying defense and hp reduction.
    * @return The real damage received
	*/
	void defend();
	
	/**
	 * Check if the combatant is still alive
	 * @return
	 */
	boolean isAlive();
	
	/**
	 * The combatant takes incoming damage; You must reduce it with your defense, 
	 * upgrade your life points and return the actual damage received.
	 * 
	 * @param incomingDamage The damage that the combatant is about to receive before applying defense and hp reduction.
	 * @return The real damage received
	 */
	public int takesDamage(int damage);
	
	public int takesHealing(int healing);
	
	int getSpeed();
	
	int getHp();
	
	int getDefense();
	
	public default String getName() {
		return "";
	
	};
}