package mechanics.skills;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SkillRegistry {

	private static Map<String, Skill> skillMap = new HashMap<>();
	
	final static String ARCHIVE = "src/main/resources/skills.txt";
	
	public static void loadFromFile() {
	    try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVE))) {

	        String line;
	        while ((line = br.readLine()) != null) {

	            String[] data = line.split(";");

	            String id = data[0];
	            String name = data[1];
	            String description = data[2];
	            String type = data[3];
	            int level = Integer.parseInt(data[4]);
	            int mana = Integer.parseInt(data[5]);

	            if (type.equalsIgnoreCase("Damage")) {

	                int Damagebase = Integer.parseInt(data[6]);
	                double modAtt = Double.parseDouble(data[7]);
	                double modDex = Double.parseDouble(data[8]);
	                double modInt = Double.parseDouble(data[9]);

	                skillMap.put(id,
	                    new SkillDamage(id, name, description, type, level, mana,
	                                    Damagebase, modAtt, modDex, modInt));
	            }

	            else if (type.equalsIgnoreCase("Heal")) {

	                int healAmount = Integer.parseInt(data[6]);
	                double modInt = Double.parseDouble(data[7]);

	                skillMap.put(id, new SkillHealer(id, name, description, type, mana, level, healAmount, modInt));
	            }
	        }
	    
	    } catch (IOException e) {
	        System.out.println("Error de lectura de archivo: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }  
	}
	
	public static Skill get(String name) {
	    return skillMap.get(name);
	}
	
}
