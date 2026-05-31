package model.inventory;

import model.items.Item;

/**
 * Class representing a slot in the inventory. It contains an item and its quantity.
 */
public class InventorySlot {

	private Item item;
	private int quantity;
	
	
	/** * Constructs an InventorySlot with the specified item and quantity.
	 * @param item
	 * @param quantity
	 */
	public InventorySlot(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	// Getters
	
	public Item getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Adds the specified quantity of items to the slot.
	 * @param quantity
	 */
	public void add(int quantity) {
		this.quantity += quantity;
	}
	
	/**
	 * Removes the specified quantity of items from the slot. If the quantity becomes negative, it is set to zero.
	 * @param quantity
	 */
	public void remove(int quantity) {
		this.quantity -= quantity;
		if (this.quantity < 0) {
			this.quantity = 0;
		}
	}
	
}
