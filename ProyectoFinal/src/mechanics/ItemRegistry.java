package mechanics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.items.Item;
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
	            int quantity = Integer.parseInt(data[4]);
	            String type = data[5];

	            if (type.equalsIgnoreCase("Damage")) {

	                int Damage = Integer.parseInt(data[6]);

	                itemMap.put(id,
	                    new DamageItem(id, name, description, cost, quantity, type, Damage));
	            }

	            else if (type.equalsIgnoreCase("Heal")) {

	                int heal = Integer.parseInt(data[6]);
	                

	                itemMap.put(id, new HealItem(id, name, description, cost, quantity, type, heal));
	            }
	        }
	    
	    } catch (IOException e) {
	        System.out.println("Error de lectura de archivo: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }  
	}
	
	public static Item get(String name) {
	    return itemMap.get(name);
	}
	
}
