package AOC2019;
import java.io.*;
import java.util.*;

public class Day3 {
    public static void main(String[] args){
        System.out.println(intersection("inputs/input.txt",1));
        System.out.println(intersection("inputs/input.txt",2));
    }

    public static  int intersection(String fileName, int part){
        File f = new File(fileName);
        String[] firstWire = new String[0];
        String[] secondWire = new String[0];
        try {
            Scanner s = new Scanner(f);
            firstWire = s.nextLine().split(",");
            secondWire = s.next().split(",");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return intersectionDist(firstWire,secondWire);
        return intersectionSteps(firstWire,secondWire);
    }

    public static int intersectionDist(String[] first, String[] second){
        HashMap<String,Integer> coordinates1 = findCoordinates(first);
        ArrayList<String> common = findCommon(coordinates1,second);
        return findShortest(common);
    }

    public static int intersectionSteps(String[] first, String[] second){
        HashMap<String,Integer> coordinates1 = findCoordinates(first);
        ArrayList<String> common = findCommon(coordinates1,second);
        return findShortestSteps(common,coordinates1);
    }

    public static HashMap<String,Integer> findCoordinates(String[] directions){
        int x = 0;
        int y = 0;
        int steps = 0;
        HashMap<String,Integer> coordinates = new HashMap<>();
        for(String direction : directions){
            char orientation = direction.charAt(0);
            int amount = Integer.parseInt(direction.substring(1));
            for(int i = 0; i < amount; i++){
                steps ++;
                if(orientation == 'R') x++;
                else if(orientation == 'L') x--;
                else if(orientation == 'U') y++;
                else y--;
                String coordinate = x+","+y;
                if(!coordinates.containsKey(coordinate)) coordinates.put(coordinate,steps);
            }

        }
        return coordinates;
    }

    public static int findShortestSteps(ArrayList<String> coordinate2, HashMap<String,Integer> coordinate1){
        String[] current = coordinate2.get(0).split(",");
        int steps = Integer.parseInt(current[2]) + coordinate1.get(current[0]+","+current[1]);
        for(int i = 1; i < coordinate2.size(); i++){
            current = coordinate2.get(i).split(",");
            int currentSteps = Integer.parseInt(current[2]) + coordinate1.get(current[0]+","+current[1]);
            if(currentSteps < steps) steps = currentSteps;
        }
        return steps;
    }

    public static ArrayList<String> findCommon(HashMap<String,Integer> one, String[] directions){
        int x = 0;
        int y = 0;
        int steps = 0;
        ArrayList<String> coordinates = new ArrayList<>();
        for(String direction : directions){
            char orientation = direction.charAt(0);
            int amount = Integer.parseInt(direction.substring(1));
            for(int i = 0; i < amount; i++){
                steps++;
                if(orientation == 'R') x++;
                else if(orientation == 'L') x--;
                else if(orientation == 'U') y++;
                else y--;
                String currentCoordinate = x+","+y;
                if(one.containsKey(currentCoordinate)) coordinates.add(currentCoordinate+","+steps);
            }

        }
        return coordinates;
    }

    public static int findShortest(ArrayList<String> common){
        String[] coordinate = common.get(0).split(",");
        int distance = Math.abs(Integer.parseInt(coordinate[0]))+Math.abs(Integer.parseInt(coordinate[1]));
        for(int i = 1; i < common.size(); i++){
            coordinate = common.get(i).split(",");
            int currentDistance = Math.abs(Integer.parseInt(coordinate[0]))+Math.abs(Integer.parseInt(coordinate[1]));
            if(distance > currentDistance) distance = currentDistance;
        }
        return distance;
    }

}
