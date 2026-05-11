package model.enemies;

import java.util.Objects;

import model.interfaces.Combatant;

/**
 * The Enemy class represents a generic enemy in the game. It serves as a base class for specific enemy
 * types such as Goblin, Orc, and Slime.
 */ 
public abstract class Enemy implements Combatant {


	    protected String name;  
	    protected int hp;
	    protected int hpMax;
	    protected int attack;
	    protected int defense;
	    protected int speed;
	    protected int goldReward;
	    protected int expReward;
	    protected int mana;
	    
	    
		public Enemy(String name, int hp, int hpMax,int mana, int attack, int defense, int speed, int goldReward,
				int expReward) {
			this.name = name;
			this.hp = hp;
			this.hpMax = hpMax;
			this.mana = mana;
			this.attack = attack;
			this.defense = defense;
			this.speed = speed;
			this.goldReward = goldReward;
			this.expReward = expReward;
		}

		// Getters and setters for the enemy attributes
		
		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}

		@Override
		public int getHp() {
			return hp;
		}


		public void setHp(int hp) {
			this.hp = hp;
		}


		public int getHpMax() {
			return hpMax;
		}


		public void setHpMax(int hpMax) {
			this.hpMax = hpMax;
		}

		
		public int getAttack() {
			return attack;
		}


		public void setAttack(int attack) {
			this.attack = attack;
		}

		@Override
		public int getDefense() {
			return defense;
		}


		public void setDefense(int defense) {
			this.defense = defense;
		}

		@Override
		public int getSpeed() {
			return speed;
		}


		public void setSpeed(int speed) {
			this.speed = speed;
		}


		public int getGoldReward() {
			return goldReward;
		}


		public void setGoldReward(int goldReward) {
			this.goldReward = goldReward;
		}


		public int getExpReward() {
			return expReward;
		}


		public void setExpReward(int expReward) {
			this.expReward = expReward;
		}

		@Override
		public int hashCode() {
			return Objects.hash(attack, defense, expReward, goldReward, hp, hpMax, name, speed);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Enemy other = (Enemy) obj;
			return Objects.equals(name, other.name);
		}

		@Override
		public String toString() {
			return "Enemy | name:" + name + " | hp: " + hp + "/" + hpMax + " | attack: " + attack + " | defense: "
					+ defense + "| speed: " + speed + "|";
		}
		
		// Implementation of Combatant interface methods
		
		@Override
		public boolean isAlive() {
			if (hp <= 0) {
				System.out.println(name + " ha muerto");; // Ensure hp doesn't go below 0
			}
			
			return hp > 0;
		}
		
		@Override
		public int attack(Combatant target) {
			return target.defend(this.attack);
		}
	    
	    @Override
	    public int defend(int incomingDamage) {
	        int damageReceived = Math.max(0, incomingDamage - this.defense);
	        this.hp = Math.max(0, this.hp - damageReceived);
	        return damageReceived;
	    }
		
		public abstract void useSkill(Combatant target);
		
}
