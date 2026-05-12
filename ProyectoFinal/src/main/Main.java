package main;

import model.character.Warrior;
import model.enemies.Goblin;
import model.character.Character;
import model.enemies.Enemy;

public class Main {
	
	public static void main(String[] args) {
		
		  Character firstCharacter = new Warrior("Ares");
		  Enemy firstGoblin = new Goblin("GoblinW");
		  int turno = 1;
		  while (firstCharacter.isAlive() && firstGoblin.isAlive()) {
			  System.out.println("Turno: " + turno);
		      System.out.println(firstCharacter.getName() + " ataca y hace " + firstCharacter.attack(firstGoblin) + " de daño.");
		      System.out.println("Goblin HP: " + firstGoblin.getHp());
		      if (firstGoblin.isAlive()){
		      System.out.println(firstGoblin.getName() + " ataca y hace " + firstGoblin.attack(firstCharacter) + " de daño.");
		      System.out.println("Ares HP: " + firstCharacter.getHp());
		      turno++;
		      }	else {
		    	  System.out.println("Goblin ha sido derrotado!");
		      }
		  }


	}
}
