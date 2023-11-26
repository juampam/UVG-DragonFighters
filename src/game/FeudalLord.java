package game;
import java.util.Random;

public class FeudalLord extends Enemy {
    private Random random = new Random();

    public FeudalLord() {
        super("Kenki no Ō", 150, 25, 0,"Nani?");
    }

    public void useSamuraiSkill(Combatant target) {
        int skillDamage = random.nextInt(10) + 20; // Random damage between 20 and 29
        target.receiveDamage(skillDamage);
        System.out.println("Feudal Lord used a samurai skill on " + target.getName() + " for " + skillDamage + " damage.");
    }}

