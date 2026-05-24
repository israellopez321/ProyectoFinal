package model.inventory;

import java.util.ArrayList;

import model.items.Item;

public class Inventory {

	private ArrayList<Item> items; 
	
	public Inventory() {
		this.items = new ArrayList<>();
	}
	
	public void add(Item item) {
		this.items.add(item);
	}
	
	public void remove(Item item) {
		this.items.remove(item);
	}
	
	public boolean contains(Item item) {
		return this.items.contains(item);
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	
}
