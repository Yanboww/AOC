package AOC2015.Day21;

public class Item {
    private final int cost;
    private final int damage;
    private final int defense;

    public Item(int cost, int damage, int defense){
        this.cost = cost;
        this.damage = damage;
        this.defense = defense;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }
}
