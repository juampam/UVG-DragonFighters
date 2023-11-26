package game;

public class Item {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item(String name) {
        this.name = name;
    }

    public void useItem(Combatant target) {
        System.out.println(name + " used on " + target.getName());
    }
}

