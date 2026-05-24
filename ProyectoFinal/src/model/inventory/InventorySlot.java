package model.inventory;

import model.items.Item;

public class InventorySlot {

	private Item item;
	private int quantity;
	
	public InventorySlot(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	
	
}
