package AOC2021;
import java.io.*;
import java.util.*;
public class Day15 {
    public static void main(String[] args){
        System.out.println(findPath("inputs/input.txt",1));
        System.out.println(findPath("inputs/input.txt",2));
    }

    public static int findPath(String filName, int part){
        File f = new File(filName);
        int length = 100;
        if(filName.contains("trial")) length = 10;
        String[][] map = new String[length][];
        HashMap<String,Integer> danger = new HashMap<>();
        ArrayList<String> queue = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine()){
                String[] line = s.nextLine().split("(?!^)");
                map[index] = line;
                index++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) {
            HashSet<String> visited = generateCoordinate(map);
            return pathFind(map, danger, queue, visited);
        }
        String[][] trueMap = findTrueMap(map);
        HashSet<String> visited = generateCoordinate(trueMap);
        return pathFind(trueMap,danger,queue,visited);
    }

    public static int pathFind(String[][] map, HashMap<String,Integer> danger, ArrayList<String> queue, HashSet<String> notVisited){
        queue.add("0,0");
        danger.put("0,0",0);
        while(!queue.isEmpty()){
            String coordinate = queue.get(0);
            String[] coordinateArr = coordinate.split(",");
            int[] coordinateIntArr = new int[]{Integer.parseInt(coordinateArr[0]),Integer.parseInt(coordinateArr[1])};
            queue.remove(0);
            notVisited.remove(coordinate);
            int prevScore = danger.get(coordinate);
            if(coordinateIntArr[0] > 0){
                String temp = (coordinateIntArr[0]-1)+","+coordinateIntArr[1];
                int dangerScore = Integer.parseInt(map[coordinateIntArr[0]-1][coordinateIntArr[1]]);
                if(!danger.containsKey(temp) || danger.get(temp) > dangerScore+prevScore){
                    if(danger.containsKey(temp) && danger.get(temp) > dangerScore+prevScore) queue.add(temp);
                    danger.put(temp,dangerScore+prevScore);
                }
                if(notVisited.contains(temp)){
                    queue.add(temp);
                    notVisited.remove(temp);
                }
            }
            if(coordinateIntArr[0] < map.length-1){
                String temp = (coordinateIntArr[0]+1)+","+coordinateIntArr[1];
                int dangerScore = Integer.parseInt(map[coordinateIntArr[0]+1][coordinateIntArr[1]]);
                if(!danger.containsKey(temp) || danger.get(temp) > dangerScore+prevScore){
                    if(danger.containsKey(temp) && danger.get(temp) > dangerScore+prevScore) queue.add(temp);
                    danger.put(temp,dangerScore+prevScore);
                }
                if(notVisited.contains(temp)){
                    queue.add(temp);
                    notVisited.remove(temp);
                }
            }
            if(coordinateIntArr[1] < map[0].length-1){
                String temp = coordinateIntArr[0]+","+(coordinateIntArr[1]+1);
                int dangerScore = Integer.parseInt(map[coordinateIntArr[0]][coordinateIntArr[1]+1]);
                if(!danger.containsKey(temp) || danger.get(temp) > dangerScore+prevScore){
                    if(danger.containsKey(temp) && danger.get(temp) > dangerScore+prevScore) queue.add(temp);
                    danger.put(temp,dangerScore+prevScore);
                }
                if(notVisited.contains(temp)){
                    queue.add(temp);
                    notVisited.remove(temp);
                }
            }
            if(coordinateIntArr[1] > 0){
                String temp = coordinateIntArr[0]+","+(coordinateIntArr[1]-1);
                int dangerScore = Integer.parseInt(map[coordinateIntArr[0]][coordinateIntArr[1]-1]);
                if(!danger.containsKey(temp) || danger.get(temp) > dangerScore+prevScore){
                    if(danger.containsKey(temp) && danger.get(temp) > dangerScore+prevScore) queue.add(temp);
                    danger.put(temp,dangerScore+prevScore);
                }
                if(notVisited.contains(temp)){
                    queue.add(temp);
                    notVisited.remove(temp);
                }
            }
        }
        return danger.getOrDefault((map.length-1)+","+(map[0].length-1),-1);
    }

    public static HashSet<String> generateCoordinate(String[][] map){
        HashSet<String> coordinate = new HashSet<>();
        for(int r = 0; r < map.length; r++){
            for(int c = 0; c < map[r].length; c++){
                coordinate.add(r+","+c);
            }
        }
        return coordinate;
    }

    public static String[][] findTrueMap(String[][] map){
        String[][] trueMap = new String[map.length*5][map[0].length*5];
        int changeR = 0;
        int changeC = 0;
        int rOffset = 0;
        int cOffset = 0;
        for(int i = 0; i < 25; i++){
            for(int r = 0; r < map.length; r++){
                for(int c = 0; c < map[r].length; c++){
                    int newVal = Integer.parseInt(map[r][c])+changeC+changeR;
                    if(newVal >= 10) newVal -= 9;
                    trueMap[r+rOffset][c+cOffset] = Integer.toString(newVal);
                }
            }
            cOffset += map[0].length;
            changeC++;
            if(cOffset > map[0].length * 4){
                changeC = 0;
                changeR++;
                rOffset += map.length;
                cOffset = 0;
            }
        }
        return trueMap;
    }
}
