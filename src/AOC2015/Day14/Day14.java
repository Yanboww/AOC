package AOC2015.Day14;
import java.io.*;
import java.util.*;
public class Day14 {
    public static void main(String[] args){
        System.out.println(fastest("inputs/input.txt",1));
        System.out.println(fastest("inputs/input.txt",2));
    }

    public static int fastest(String fileName, int part){
        ArrayList<Reindeer> reindeer = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                String[] line = s.nextLine().split("\\s");
                reindeer.add(new Reindeer(Integer.parseInt(line[3]), Integer.parseInt(line[6]),
                        Integer.parseInt(line[13])));
            }
            s.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return longestDistance(reindeer);
        return highestPoints(reindeer);
    }

    public static int longestDistance(ArrayList<Reindeer> reindeer){
        int longest = 0;
        for(Reindeer deer: reindeer){
            int totalTime = deer.getBurstTime() + deer.getRestTime();
            int nearestMultiple = 2503/totalTime;
            int remaining = 2503 - nearestMultiple*totalTime;
            int distance = nearestMultiple*deer.getSpeed()*deer.getBurstTime();
            if(remaining-deer.getBurstTime() >= 0) distance+=deer.getBurstTime()*deer.getSpeed();
            else distance+= remaining * deer.getSpeed();
            if(distance > longest) longest = distance;
        }
        return longest;
    }

    public static int highestPoints(ArrayList<Reindeer> reindeer){
        int[] points = new int[reindeer.size()];
        for(int i = 0; i < 2503; i++){
            int longest = 0;
            ArrayList<Integer> indexes = new ArrayList<>();
            for(int j = 0; j < reindeer.size(); j++){
                int distance = reindeer.get(j).incrementSecond();
                if(distance > longest){
                    longest = distance;
                    indexes.clear();
                    indexes.add(j);
                }
                else if(distance == longest) indexes.add(j);
            }
            for(int val : indexes) points[val]++;
        }
        Arrays.sort(points);
        return points[points.length-1];
    }
}
