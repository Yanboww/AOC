package AOC2015.Day22;
import java.io.*;
import java.util.*;
public class Day22 {

    public static ArrayList<Spell> spells = new ArrayList<>();
    public static final PriorityQueue<Integer> manaCosts = new PriorityQueue<>();
    public static void main(String[] args){
        spells.add(new Spell(53,4,0,0,0,0,0));
        spells.add(new Spell(73,2,2,0,0,0,0));
        spells.add(new Spell(113,0,0,6,7,0,0));
        spells.add(new Spell(173,0,0,6,0,3,0));
        spells.add(new Spell(229,0,0,5,0,0,101));

        System.out.println(beatBoss("inputs/input.txt",1));
        manaCosts.clear();
        System.out.println(beatBoss("inputs/input.txt",2));
    }

    public static int beatBoss(String fileName, int part){
        int[] bossStats = new int[2];
        try{
            Scanner s = new Scanner(new File(fileName));
            for(int i = 0; i < 2; i++){
                String line = s.nextLine();
                bossStats[i] = Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        playerTurn(bossStats[0], bossStats[1], 50, 500, 0,0,0,0, part);
        if(!manaCosts.isEmpty()) return manaCosts.poll();
        return -1;
    }

    public static void playerTurn(int bossHp, int bossDamage, int playerHp, int playerMana, int cost, int dT, int daT, int mT, int part){
        if(!manaCosts.isEmpty() && manaCosts.peek() <= cost) return;
        if(part == 2) playerHp--;
        if(playerHp <= 0) return;
        if(dT > 0){
            dT--;
        }
        if(daT > 0){
            bossHp -= 3;
            daT--;
        }
        if(mT > 0){
            playerMana += 101;
            mT--;
        }

        if(bossHp <= 0){
            manaCosts.offer(cost);
            return;
        }
        else if(playerMana < 53){
            return;
        }
        for(Spell spell : spells){
            int eventTurns = spell.getEventTurns();
            if(eventTurns > 0){
                int newDT = dT;
                int newDaT = daT;
                int newMT = mT;
                int newCost = cost;
                int newMana = playerMana;
                if(spell.getEventDefense() > 0){
                    if(dT == 0 && playerMana >= spell.getCost()){
                        newDT = spell.getEventTurns();
                        newCost += spell.getCost();
                        newMana -= spell.getCost();
                        bossTurn(bossHp,bossDamage,playerHp, newMana,newCost, newDT, newDaT, newMT,part);
                    }
                }
                else if(spell.getEventDamage() > 0){
                    if(daT == 0 && playerMana >= spell.getCost()){
                        newDaT = spell.getEventTurns();
                        newCost += spell.getCost();
                        newMana -= spell.getCost();
                        bossTurn(bossHp,bossDamage,playerHp, newMana,newCost, newDT, newDaT, newMT,part);
                    }
                }
                else{
                    if(mT == 0 && playerMana >= spell.getCost()){
                        newMT = spell.getEventTurns();
                        newCost += spell.getCost();
                        newMana -= spell.getCost();
                        bossTurn(bossHp,bossDamage,playerHp, newMana,newCost, newDT, newDaT, newMT,part);
                    }
                }
            }
            else{
                if(playerMana >= spell.getCost()){
                    bossTurn(bossHp - spell.getDamage(), bossDamage, playerHp + spell.getHeal(), playerMana-spell.getCost(), cost+spell.getCost(), dT, daT, mT, part);
                }
            }
        }
    }

    public static void bossTurn(int bossHp, int bossDamage, int playerHp, int playerMana, int cost, int dT, int daT, int mT, int part){
        int playerDefense;
        if(dT > 0){
            playerDefense = 7;
            dT--;
        }
        else{
            playerDefense = 0;
        }
        if(daT > 0){
            bossHp -= 3;
            daT--;
        }
        if(mT > 0){
            playerMana += 101;
            mT--;
        }
        if(bossHp <= 0) manaCosts.offer(cost);
        else{
            int damageDealt = Math.max(1,bossDamage-playerDefense);
            playerTurn(bossHp,bossDamage, playerHp-damageDealt,playerMana,cost,dT,daT,mT,part);
        }
    }

}
