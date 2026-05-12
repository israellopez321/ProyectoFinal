package main;

import model.character.Warrior;
import model.enemies.Goblin;
import model.character.Character;
import model.enemies.Enemy;

public class Main {
	
	public static void main(String[] args) {
		
		  Character firstCharacter = new Warrior("Ares");
		  Enemy firstGoblin = new Goblin("GoblinW");
		  while (firstCharacter.isAlive() && firstGoblin.isAlive()) {
		      int d = firstCharacter.attack(firstGoblin);
		      System.out.println("Goblin HP: " + firstGoblin.getHp());
		      if (firstGoblin.isAlive()){
		      firstGoblin.attack(firstCharacter);
		      System.out.println("Goblin hace " + d);
		      System.out.println("Ares HP: " + firstCharacter.getHp());
		      }
		  }


	}
}
