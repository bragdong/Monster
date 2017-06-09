package livingThings;

import java.util.Random;

public abstract class LivingThings {
	private String name;
	private int health;
	private int attackPower;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public boolean isAlive() {
		if (this.health > 0) {
			return true;
		} else {
			return false;
		}
	}

	public abstract void attack(LivingThings creature);

	public abstract void takeDamage(int damage);

	public static void main(String[] args) {
		LivingThings[] creatureArray = new LivingThings[3];
		creatureArray[0] = new Ogre("Ogre", 10, 2);
		creatureArray[1] = new Rat("Rat", 5, 1);
		creatureArray[2] = new Goblin("Goblin", 8, 1);
		Hero superman = new Hero("Superman", 12, 2);
		superman.fight(creatureArray);
	}
}

// ************* Ogre ************************
class Ogre extends LivingThings {

	public Ogre(String name, int health, int attackPower) {
		this.setName(name);
		this.setHealth(health);
		this.setAttackPower(attackPower);
	}

	public void attack(LivingThings hero) {
		Random r = new Random();
		int damage = this.getAttackPower() * ((int) Math.ceil((r.nextDouble()) * 2));
		System.out.println(this.getName() + " attacks " + hero.getName() + " causing " + damage + " damage.");
		hero.takeDamage(damage);
	}

	public void takeDamage(int damage) {
		this.setHealth(this.getHealth() - damage);
	}

}

// ************** RAT ***********************
class Rat extends LivingThings {

	public Rat(String name, int health, int attackPower) {
		this.setName(name);
		this.setHealth(health);
		this.setAttackPower(attackPower);
	}

	public void attack(LivingThings hero) {
		Random r = new Random();
		int damage = this.getAttackPower() * ((int) Math.ceil((r.nextDouble()) * 2));
		System.out.println(this.getName() + " attacks " + hero.getName() + " causing " + damage + " damage.");
		hero.takeDamage(damage);
	}

	public void takeDamage(int damage) {
		this.setHealth(this.getHealth() - damage);
	}
}

// ******************* Goblin **************************
class Goblin extends LivingThings {

	public Goblin(String name, int health, int attackPower) {
		this.setName(name);
		this.setHealth(health);
		this.setAttackPower(attackPower);
	}

	public void attack(LivingThings hero) {
		Random r = new Random();
		int damage = this.getAttackPower() * ((int) Math.ceil((r.nextDouble()) * 2));
		System.out.println(this.getName() + " attacks " + hero.getName() + " causing " + damage + " damage.");
		hero.takeDamage(damage);
	}

	public void takeDamage(int damage) {
		this.setHealth(this.getHealth() - damage);
	}
}

// **************** HERO ******************************
class Hero extends LivingThings {

	public Hero(String name, int health, int attackPower) {
		this.setName(name);
		this.setHealth(health);
		this.setAttackPower(attackPower);
	}

	public void attack(LivingThings creature) {
		Random r = new Random();
		int damage = this.getAttackPower() * ((int) Math.ceil((r.nextDouble()) * 2));
		System.out.println(this.getName() + " attacks " + creature.getName() + " causing " + damage + " damage.");
		creature.takeDamage(damage);
	}

	public void takeDamage(int damage) {
		this.setHealth(this.getHealth() - damage);
	}

	public void fight(LivingThings[] creatureArray) {
		boolean gameOver = false;
		int creatureAliveCnt = creatureArray.length;

		while (!gameOver) { // Hero Attacks
			for (int i = 0; i < creatureArray.length; i++) {
				if (creatureArray[i].isAlive() && !gameOver) {
					this.attack(creatureArray[i]);
					if (creatureArray[i].isAlive()) {
						System.out.println(creatureArray[i].getName() + " remaining health is "
								+ creatureArray[i].getHealth() + "\n");
					} else {
						System.out.println(creatureArray[i].getName() + " is Dead!" + "\n");
						creatureAliveCnt--;
					}
				}

				if (creatureArray[i].isAlive() && !gameOver) { // Creature
																// Attacks
					creatureArray[i].attack(this);
					if (this.isAlive()) {
						System.out.println(this.getName() + " remaining health is " + this.getHealth() + "\n");
					} else {
						System.out.println(this.getName() + " is Dead!  Creatures Win!!!");
						gameOver = true;
					}
				}

				if (creatureAliveCnt == 0 && this.isAlive() && !gameOver) {
					System.out.println("All creatures are Dead! " + this.getName() + " Wins!!!!");
					gameOver = true;
				}
			}

		}
		System.out.println("Game Over!");
	}
}
