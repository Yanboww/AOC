package AOC2015.Day22;

public class Spell {
    private final int cost;
    private final int damage;
    private final int heal;
    private final int eventTurns;
    private final int eventDefense;
    private final int eventDamage;
    private final int eventMana;
    private int turnsLeft;

    public Spell(int cost, int damage, int heal, int eventTurns, int eventDefense, int eventDamage, int eventMana){
        this.cost = cost;this.damage = damage;this.heal = heal;
        this.eventTurns = eventTurns; this.eventDefense = eventDefense;
        this.eventDamage = eventDamage; this.eventMana = eventMana;
        turnsLeft = 0;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getHeal() {
        return heal;
    }

    public int getEventTurns() {
        return eventTurns;
    }

    public int getEventDefense() {
        return eventDefense;
    }

    public int getEventDamage() {
        return eventDamage;
    }

    public int getEventMana() {
        return eventMana;
    }

    public void useEvent(){
        turnsLeft = eventTurns;
    }

    public void endEvent(){
        turnsLeft = 0;
    }

    public boolean eventActive(){
        return turnsLeft > 0;
    }

    public void decrementTurn(){
        turnsLeft--;
    }

}
