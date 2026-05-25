package mechanics;

import java.util.ArrayList;
import model.enemies.Enemy;
import model.enemies.Goblin;
import model.enemies.Slime;
import model.enemies.Orc;

public class Dungeon {

	public ArrayList<Enemy> generateFloors(int floor){
		
		ArrayList<Enemy> enemies = new ArrayList<>();
		
		if (floor == 1) {
			enemies.add(new Slime("Slime1", 1));
			enemies.add(new Slime("Slime2", 1));
			enemies.add(new Slime("Slime3", 1));
			
		}else if (floor == 2) {
			enemies.add(new Goblin("Goblin", 2));
			enemies.add(new Slime("Slime1", 2));
			enemies.add(new Slime("Slime2", 2));
			
		} else if (floor == 3) {
			enemies.add(new Orc("Orc", 2));
		
		} else if (floor == 4) {
			enemies.add(new Orc("Orc", 2));
			enemies.add(new Goblin("Goblin", 3));
			
		} else if (floor == 5) {
			enemies.add(new Orc("Orc", 3));
			enemies.add(new Goblin("Goblin", 4));
			enemies.add(new Slime("Slime", 4));
		
		} else if (floor == 6) {
			enemies.add(new Orc("Orc1", 4));
			enemies.add(new Orc("Orc2", 4));
			enemies.add(new Goblin("Goblin", 5));
		
		} else if (floor == 7) {
			System.out.println("¡Has terminado el calabozo! Enhorabuena por tu victoria.");
		}
		
		return enemies;
	}
	
	
}
