// Combatant.java
package game;

import java.util.ArrayList;

public class Combatant {
    protected String name;
    protected int life;
    protected int attackPoints;
    protected int inventoryCapacity;
    protected String phrases;
    protected ArrayList<Item> inventory;

    public Combatant(String name, int life, int attackPoints, int inventoryCapacity,String phrases) {
        this.name = name;
        this.life = life;
        this.attackPoints = attackPoints;
        this.inventoryCapacity = inventoryCapacity;
        this.inventory = new ArrayList<>();
        this.phrases = phrases;
    }
    public int getInventorySize() {
        return inventory.size();
    }

    public Item getItem(int index) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.get(index);
        } else {
            System.out.println("Invalid index. Returning null.");
            return null;
        }
    }
    public void attack(Combatant target) {
        int damage = this.attackPoints;
        target.receiveDamage(damage);
        System.out.println(this.name + " attacked " + target.getName() + " for " + damage + " damage.");
    }

    public void useItem(Item item, Combatant target) {
        item.useItem(target);
    }

    public void receiveDamage(int damage) {
        this.life -= damage;
        System.out.println(this.name + " received " + damage + " damage. Remaining life: " + this.life);

        if (this.life <= 0) {
            System.out.println(this.name + " has been defeated!");
        }
    }

    public void addItem(Item item) {
        if (inventory.size() < inventoryCapacity) {
            inventory.add(item);
            System.out.println(this.name + " added " + item.getName() + " to the inventory.");
        } else {
            System.out.println(this.name + "'s inventory is full. Cannot add " + item.getName() + ".");
        }
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }
     public int getAttackPoints() {
        return attackPoints;
    }
    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
