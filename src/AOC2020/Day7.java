package AOC2020;
import java.io.*;
import java.util.*;
public class Day7 {
    public static void main(String[] args){
        System.out.println(numberOfBagsWithGold("inputs/input.txt", 1));
        System.out.println(numberOfBagsWithGold("inputs/input.txt", 2));
    }

    public static int numberOfBagsWithGold(String fileName, int part)
    {
        File f = new File(fileName);
        HashMap<String,ArrayList<String>> bagsDict = new HashMap<>();
        HashMap<String,ArrayList<String>> bagsContainDict = new HashMap<>();
        try{
            Scanner  s = new Scanner(f);
            while(s.hasNextLine()){
                String line = s.nextLine();
                line = line.replace("bags","bag");
                getBagDict(line,bagsDict);
                getBagsContainDict(line,bagsContainDict);
            }

        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if (part == 1) return findGold(bagsDict);
        else return howManyInGold(bagsContainDict);
    }

    public static int findGold(HashMap<String,ArrayList<String>> bagsDict){
        HashSet<String> possible = new HashSet<>();
        ArrayList<String> queue = new ArrayList<>();
        queue.add("shiny gold bag");
        while(!queue.isEmpty()){
            String key = queue.get(0);
            if(bagsDict.containsKey(key)){
                ArrayList<String> results = bagsDict.get(key);
                for(String result : results){
                    possible.add(result);
                    queue.add(result);
                }
            }
            queue.remove(0);
        }
        return possible.size();
    }

    public static int howManyInGold(HashMap<String,ArrayList<String>> bagsContainsDict){
        int count = 0;
        ArrayList<String> queue = new ArrayList<>();
        queue.add("shiny gold bag");
        while(!queue.isEmpty()){
            String key = queue.get(0);
            if(bagsContainsDict.containsKey(key)){
                ArrayList<String> results = bagsContainsDict.get(key);
                for(String result: results){
                    int amount = Integer.parseInt(result.substring(0,result.indexOf(" ")));
                    count += amount;
                    for(int i = 0; i < amount; i++) queue.add(result.substring(result.indexOf(" ")+1));
                }
            }
            queue.remove(0);
        }
        return count;
    }

    public static void getBagsContainDict(String line, HashMap<String, ArrayList<String>> bagsContainsDict){
        String currentBag = line.substring(0,line.indexOf(" contain"));
        currentBag = currentBag.trim();
        line = line.substring(line.indexOf(" contain" )+ 9);
        ArrayList<String> possibleInside = new ArrayList<>();
        while(line.matches(".*\\d.*")){
            int space = line.indexOf(" ");
            int end = line.indexOf(",");
            if(end == -1) end = line.indexOf(".");
            possibleInside.add(line.substring(space-1,end));
            if(end+2 < line.length()-1) line = line.substring(end+2);
            else break;
        }
        for(String bag : possibleInside){
            bag = bag.trim();
            if(!bagsContainsDict.containsKey(currentBag)){
                ArrayList<String> fitIn = new ArrayList<>();
                bagsContainsDict.put(currentBag,fitIn);
            }
            bagsContainsDict.get(currentBag).add(bag);
        }
    }

    public static void getBagDict(String line, HashMap<String,ArrayList<String>> bagsDict){
        String currentBag = line.substring(0,line.indexOf(" contain"));
        line = line.substring(line.indexOf(" contain" )+ 9);
        ArrayList<String> possibleInside = new ArrayList<>();
        while(line.matches(".*\\d.*")){
            int space = line.indexOf(" ");
            int end = line.indexOf(",");
            if(end == -1) end = line.indexOf(".");
            possibleInside.add(line.substring(space,end));
            if(end+2 < line.length()-1) line = line.substring(end+2);
            else break;
        }
        for(String bag : possibleInside){
            bag = bag.trim();
            if(!bagsDict.containsKey(bag)){
                ArrayList<String> fitIn = new ArrayList<>();
                bagsDict.put(bag,fitIn);
            }
            bagsDict.get(bag).add(currentBag);
        }
    }

}
