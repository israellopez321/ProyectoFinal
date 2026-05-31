package mechanics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.items.Item;
import model.items.Weapon;
import model.items.Armor;
import model.items.DamageItem;
import model.items.HealItem;


public class ItemRegistry {

private static Map<String, Item> itemMap = new HashMap<>();
	
	final static String ARCHIVE = "src/main/resource/Items.txt";
	
	public static void loadItems() {
	    try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVE))) {

	        String line;
	        while ((line = br.readLine()) != null) {

	            String[] data = line.split(";");

	            String id = data[0];
	            String name = data[1];
	            String description = data[2];
	            int cost = Integer.parseInt(data[3]);
	            String type = data[4];	

	            if (type.equalsIgnoreCase("Damage")) {

	                int Damage = Integer.parseInt(data[5]);

	                itemMap.put(id,
	                    new DamageItem(id, name, description, cost, type, Damage));
	            }

	            else if (type.equalsIgnoreCase("Heal")) {

	                int heal = Integer.parseInt(data[5]);
	                

	             
	                itemMap.put(id, new HealItem(id, name, description, cost, type, heal));
	         
	            } else if(type.equalsIgnoreCase("Weapon")) {
	            	
	            	int damage = Integer.parseInt(data[5]);
	            	
	            	itemMap.put(id, new Weapon(id, name, description, cost, type, damage));
	            	
	            } else if(type.equalsIgnoreCase("Armor")) {
	            	
	            	int defense = Integer.parseInt(data[5]);
	            	
	            	itemMap.put(id, new Armor(id, name, description, cost, type, defense));
	            	
	            }
	        }
	    
	    } catch (IOException e) {
	        System.out.println("ERROR: " + e.getMessage());
	    } catch (Exception e) {
	       System.out.println("ERROR: " + e.getMessage());
	    }  
	}
	
	public static Item get(String name) {
	    return itemMap.get(name);
	}
	
	/**
	 */
	public static Collection<Item> getConsumableItems() {
	    return itemMap.values().stream()
	        .filter(item -> item instanceof HealItem || item instanceof DamageItem)
	        .collect(java.util.stream.Collectors.toList());
	}
	
}
