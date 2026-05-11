package main;

import model.character.Warrior;
import model.enemies.Goblin;
import model.character.Character;
import model.character.Mage;
import model.enemies.Enemy;

public class Main {
	
	public static void main(String[] args) {
		
		  Character p = new Mage("Ares");
		  Enemy e = new Goblin("GoblinW");
		  while (p.isAlive() && e.isAlive()) {
		      int d = p.attack(e);
		      System.out.println("Ares hace " + d);
		      System.out.println("Goblin HP: " + e.getHp());
		      if (!e.isAlive()) break;
		      d = e.attack(p);
		      System.out.println("Goblin hace " + d);
		      System.out.println("Ares HP: " + p.getHp());
		  }


	}
}
