package main;

import mechanics.SkillRegistry;
import mechanics.TurnManager;
import model.character.Character;
import model.character.Mage;
import model.enemies.Enemy;
import model.enemies.Goblin;
import model.interfaces.Combatant;
import model.skills.Skill;
import model.skills.SkillDamage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // 1) Cargar skills
        SkillRegistry.loadSkill();

        Mage testMage = new Mage("TestMage");
        
        testMage.levelUp(); // para asegurarnos de que aprende Fireball
        
        System.out.println(testMage.getSkills()); // para verificar que no tiene habilidades al inicio
        
        // 2) Obtener Fireball (por id tal como está en tu archivo); si no existe, crear uno de prueba
        Skill fire = SkillRegistry.get("Fireball");
        if (fire == null) {
            fire = new SkillDamage("Fireball", "Fireball", "Bola de fuego", "Damage",
                                   10, 2, 30, 0.0, 0.0, 0.6);
        }

        // 3) Crear combatientes
        ArrayList<Character> allies = new ArrayList<>();
        Mage mage = new Mage("Gandalf");
        allies.add(mage);

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Goblin("G1", 1));

        TurnManager tm = new TurnManager(allies, enemies);
        Random rnd = new Random();

        System.out.println("Orden inicial (descendente por speed):");
        for (Combatant c : tm.getTurnOrder()) System.out.println("  - " + c);

        int round = 1;
        while (!tm.isCombatOver()) {
            System.out.println("\n--- Ronda " + (round++) + " ---");
            Combatant current = tm.nextCombatant();
            if (current == null) break;
            if (!current.isAlive()) continue;

            List<Combatant> possibleTargets = new ArrayList<>();
            if (current instanceof Character) {
                for (Enemy e : tm.getEnemies()) if (e.isAlive()) possibleTargets.add(e);
            } else {
                for (Character a : tm.getAllies()) if (a.isAlive()) possibleTargets.add(a);
            }
            if (possibleTargets.isEmpty()) continue;

            Combatant target = possibleTargets.get(rnd.nextInt(possibleTargets.size()));

            // Detalles previos
            int targetHpBefore = target.getHp();
            int userManaBefore = (current instanceof Mage) ? ((Mage) current).getMana() : -1;

            if (current instanceof Mage) {
                Mage m = (Mage) current;
                if (m.getMana() >= fire.getManaCost()) {
                    System.out.println(m.getName() + " intenta usar " + fire.getName() + " sobre " + target.getName());
                    // si es SkillDamage, mostrar estimación basada en atributos
                    if (fire instanceof SkillDamage) {
                        int estimated = ((SkillDamage) fire).calculateDamage(m);
                        System.out.println("  - Estimación de daño (sin defensas): " + estimated);
                    }
                    // Ejecutar skill
                    fire.useSkill(m, target);

                    int targetHpAfter = target.getHp();
                    int actualDamage = Math.max(0, targetHpBefore - targetHpAfter);
                    System.out.println("  - Daño real aplicado: " + actualDamage);
                    System.out.println("  - Mana: " + userManaBefore + " -> " + m.getMana() + " (coste " + fire.getManaCost() + ")");
                } else {
                    int dmg = m.attack(target);
                    System.out.println(m.getName() + " no tiene mana suficiente y ataca normalmente, daño: " + dmg);
                }
            } else {
                int dmg = current.attack(target);
                System.out.println(current.getName() + " ataca a " + target.getName() + " y hace " + dmg + " de daño.");
            }

            System.out.println("Estado objetivo: " + target.getName() + " HP=" + target.getHp());

            if (!target.isAlive()) {
                System.out.println(target.getName() + " ha muerto.");
                if (target instanceof Character) tm.getAllies().remove((Character) target);
                else tm.getEnemies().remove((Enemy) target);
                tm.buildTurnOrder();
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