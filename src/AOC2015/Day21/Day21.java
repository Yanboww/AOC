package AOC2015.Day21;
import java.io.*;
import java.util.*;

public class Day21 {
    public static void main(String[] args){
        System.out.println(cheapestWin("inputs/input.txt",1));
        System.out.println(cheapestWin("inputs/input.txt",2));
    }

    public static int cheapestWin(String fileName, int part){
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        items.add(new ArrayList<>()); items.add(new ArrayList<>());
        items.add(new ArrayList<>());
        int bossHp = 0;
        int bossDamage = 0;
        int bossDefense = 0;
        try {
            Scanner s = new Scanner(new File("src/AOC2015/Day21/Shop.txt"));
            int index = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()) index++;
                else if(!line.contains(":")){
                    items.get(index).add(line);
                }

            }
            s = new Scanner(new File(fileName));
            for(int i = 0; i < 3; i++){
                String line = s.nextLine();
                int val = Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
                switch (i){
                    case 0: bossHp=val; break;
                    case 1: bossDamage=val; break;
                    case 2: bossDefense=val; break;
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        ArrayList<Item> weapon = parseShop(items.get(0));
        ArrayList<Item> armor = parseShop(items.get(1));
        ArrayList<Item> ring = parseShop(items.get(2));
        return combination(weapon,armor,ring,bossHp,bossDamage,bossDefense, part);
    }

    public static ArrayList<Item> parseShop(ArrayList<String> input){
        ArrayList<Item> result = new ArrayList<>();
        for(String line : input) {
            String[] lineArr = line.split("\\s+");
            result.add(new Item(Integer.parseInt(lineArr[1]),Integer.parseInt(lineArr[2]), Integer.parseInt(lineArr[3])));
        }
        return result;
    }

    public static int combination(ArrayList<Item> weapon, ArrayList<Item> armor, ArrayList<Item> ring, int hp, int damage, int defense, int part){
        int minCost = -1;
        int maxCost = -1;
        for (Item item : weapon) {
            for (int j = -1; j < armor.size(); j++) {
                for (int k = -1; k < ring.size(); k++) {
                    for (int l = -1; l < ring.size(); l++) {
                        int currentCost = item.getCost();
                        int currentHp = 100;
                        int currentDamage = item.getDamage();
                        int currentDefense = 0;
                        if (j != -1) {
                            currentCost += armor.get(j).getCost();
                            currentDefense += armor.get(j).getDefense();
                        }
                        if (k != -1) {
                            currentCost += ring.get(k).getCost();
                            currentDamage += ring.get(k).getDamage();
                            currentDefense += ring.get(k).getDefense();
                        }
                        if (l != k && l != -1) {
                            currentCost += ring.get(l).getCost();
                            currentDamage += ring.get(l).getDamage();
                            currentDefense += ring.get(l).getDefense();
                        }
                        if (wouldWin(currentHp, currentDamage, currentDefense, hp, damage, defense)) {
                            if (minCost < 0) minCost = currentCost;
                            else if (minCost > currentCost) minCost = currentCost;
                        }
                        else{
                            if(maxCost < currentCost) maxCost = currentCost;
                        }
                    }
                }
            }
        }
        if(part == 1) return minCost;
        return maxCost;
    }

    public static boolean wouldWin(int playerHp, int playerDamage, int playerDefense, int bossHp, int bossDamage, int bossDefense){
        playerDamage = Math.max(playerDamage - bossDefense, 1);
        bossDamage = Math.max(bossDamage - playerDefense, 1);
        int playerHits = bossHp/playerDamage;
        if(bossHp%playerDamage!=0)playerHits++;
        int bossHits = playerHp/bossDamage;
        if(playerHp%bossDamage!=0) bossHits++;
        return playerHits <= bossHits;
    }

}
