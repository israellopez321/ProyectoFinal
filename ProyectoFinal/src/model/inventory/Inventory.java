package model.inventory;

import java.util.ArrayList;

import model.items.Item;
import exceptions.InsufficientStockException;

/**
 * The Inventory class manages a collection of items for a character. 
 * It allows adding and removing items, as well as retrieving the current inventory. Each item is stored in an InventorySlot, which keeps track of the item and its quantity.
 */
public class Inventory {

	private ArrayList<InventorySlot> items = new ArrayList<>(); 
	
	/**
	 * Adds an item to the inventory. If the item already exists in the inventory, it increases the quantity by one. Otherwise, it creates a new inventory slot for the item with a quantity of one.
	 * @param item The item to be added to the inventory.
	 */
	public void add(Item item) {
	    boolean found = false;

	    for (InventorySlot slot : items) {
	        if (slot.getItem().getClass() == item.getClass()) {
	            slot.add(1);
	            found = true;
	            break;
	        }
	    }
	    
	    if (!found) {
	        items.add(new InventorySlot(item, 1));
	    }
	}

	/**
	 * Removes one quantity of the specified item from the inventory. If the quantity of the item reaches zero, it is removed from the inventory list.
	 * @param item The item to be removed from the inventory.
	 */
	public void remove(Item item) throws InsufficientStockException {
		InventorySlot slotToRemove = null;
		boolean found = false;

		for (InventorySlot slot : items) {
			if (slot.getItem().getClass() == item.getClass()) {
				found = true;
				if (slot.getQuantity() <= 0) {
					throw new InsufficientStockException("No stock for item: " + item.getName());
				}
				slot.remove(1);
				if (slot.getQuantity() <= 0) {
					slotToRemove = slot;
				}
				break;
			}
		}

		if (!found) {
			throw new InsufficientStockException("Item not found in inventory: " + item.getName());
		}

		if (slotToRemove != null) {
			items.remove(slotToRemove);
		}
	}

	// Getter
	public ArrayList<InventorySlot> getItems() {
		return items;
	}
	
}
