package game;

public class Enemy extends Combatant {
    public Enemy(String name, int life, int attackPoints, int inventoryCapacity,String phrases) {
        super(name, life, attackPoints, inventoryCapacity, phrases);
    }

   public void attack(Combatant target) {
        int damage = this.attackPoints;
        target.receiveDamage(damage);
        System.out.println(this.name + " attacked " + target.getName() + " for " + damage + " damage.");
    }

    public void receiveDamage(int damage) {
        this.life -= damage;
        System.out.println(this.name + " received " + damage + " damage. Remaining life: " + this.life);

        if (this.life <= 0) {
            System.out.println(this.name + " has been defeated!");
        }
    }

    // Other methods and properties

    // Override this method in subclasses if needed
    public void useSamuraiSkill(Combatant target) {
        // Default behavior (no skill for generic enemies)
        System.out.println(this.name + " attempted to use a samurai skill, but nothing happened.");
    }

}
