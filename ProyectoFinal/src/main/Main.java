package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.character.Warrior;
import model.character.Mage;
import model.character.Character;
import model.enemies.Goblin;
import model.enemies.Enemy;
import model.interfaces.Combatant;
import mechanics.TurnManager;

public class Main {
    public static void main(String[] args) {
        // Crear aliados
        ArrayList<Character> allies = new ArrayList<>();
        allies.add(new Warrior("Ares"));   // speed 6
        allies.add(new Mage("Merlin"));    // speed 10

        // Crear enemigos
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Goblin("G1"));     // speed 20
        enemies.add(new Goblin("G2"));
        enemies.add(new Goblin("G4"));
        enemies.add(new Goblin("G3"));
        enemies.add(new Goblin("G5"));
        enemies.add(new Goblin("G6"));
        enemies.add(new Goblin("G7"));

        // Crear TurnManager
        TurnManager tm = new TurnManager(allies, enemies);
        Random rnd = new Random();

        System.out.println("Orden inicial (descendente por speed):");
        for (Combatant c : tm.getTurnOrder()) {
            System.out.println("  - " + c);
        }

        System.out.println("\nComienzo del combate:");
        // Bucle principal: hasta que isCombatOver() sea true
        int round = 1;
        while (!tm.isCombatOver()) {
            System.out.println("\n--- Ronda " + (round++) + " ---");
            Combatant current = tm.nextCombatant();

            if (current == null) {
                System.out.println("No hay combatientes disponibles (turnOrder vacío).");
                break;
            }

            // Si current está muerto, lo saltamos (seguridad)
            if (!current.isAlive()) {
                System.out.println(current.getName() + " está muerto. Saltando.");
                continue;
            }

            // Elegimos lista de objetivos opuestos
            List<Combatant> possibleTargets = new ArrayList<>();
            if (current instanceof Character) {
                // objetivos = enemigos vivos
                for (Enemy e : tm.getEnemies()) if (e.isAlive()) possibleTargets.add(e);
            } else {
                // current es Enemy -> objetivos = aliados vivos
                for (Character a : tm.getAllies()) if (a.isAlive()) possibleTargets.add(a);
            }

            if (possibleTargets.isEmpty()) {
                // quizá acabó el combate justo ahora
                System.out.println("No hay objetivos vivos para " + current.getName());
                continue;
            }

            // Seleccionamos target aleatorio simple
            Combatant target = possibleTargets.get(rnd.nextInt(possibleTargets.size()));

            // Ejecutar ataque (podrías usar useSkill() según la clase)
            int damage = current.attack(target);

            System.out.println(current.getName() + " ataca a " + target.getName() + " y hace " + damage + " de daño."
                    + " (HP restante: " + target.getHp() + ")");

            // Si muere, eliminarlo de la lista correspondiente y reconstruir orden
            if (!target.isAlive()) {
                System.out.println(target.getName() + " ha muerto.");
                if (target instanceof Character) {
                    tm.getAllies().remove((Character) target);
                } else {
                    tm.getEnemies().remove((Enemy) target);
                }
                tm.buildTurnOrder(); // actualiza turnOrder y resetea turnIndex
            }        
        }

        System.out.println("\nCombate finalizado.");
        boolean anyAllyAlive = tm.getAllies().stream().anyMatch(Combatant::isAlive);
        boolean anyEnemyAlive = tm.getEnemies().stream().anyMatch(Combatant::isAlive);

        if (anyAllyAlive && !anyEnemyAlive) System.out.println("¡Los aliados han ganado!");
        else if (!anyAllyAlive && anyEnemyAlive) System.out.println("¡Los enemigos han ganado!");
        else System.out.println("Empate o ambos bandos eliminados.");
    }
}
