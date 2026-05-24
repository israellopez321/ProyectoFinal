package model.inventory;

import java.util.ArrayList;

import model.items.Item;

public class Inventory {

	private ArrayList<InventorySlot> items = new ArrayList<>(); 
	
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

	
	public void remove(Item item) {
	    InventorySlot slotToRemove = null;

	    for (InventorySlot slot : items) {
	        if (slot.getItem().getClass() == item.getClass()) {
	            slot.remove(1);
	            if (slot.getQuantity() <= 0) {
	                slotToRemove = slot;
	            }
	            break;
	        }
	    }

	    if (slotToRemove != null) {
	        items.remove(slotToRemove);
	    }
	}

	public ArrayList<InventorySlot> getItems() {
		return items;
	}
	
}
