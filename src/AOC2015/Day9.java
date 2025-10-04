package AOC2015;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Day9 {
    public static void main(String[] args){
        System.out.println(shortestDist("inputs/input.txt",1));
        System.out.println(shortestDist("inputs/input.txt",2));
    }

    public static int shortestDist(String fileName, int part){
        HashMap<String, Integer> dict = new HashMap<>();
        HashSet<String> locations = new HashSet<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                String[] line = s.nextLine().split(" ");
                dict.put(line[0]+" "+line[2],Integer.parseInt(line[4]));
                dict.put(line[2]+" "+line[0],Integer.parseInt(line[4]));
                locations.add(line[0]);locations.add(line[2]);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return findPossible(dict, locations, "",part);
    }

    public static int findPossible (HashMap<String, Integer> dict, HashSet<String> locations, String path, int part){
        String[] pathArr = path.split(" ");
        if(pathArr.length == locations.size()+1){
            int score = 0;
            for(int i = 2; i < pathArr.length; i++){
                score+=dict.get(pathArr[i-1]+" "+pathArr[i]);
            }
            return score;
        }
        else{
            PriorityQueue<Integer> results;
            if(part == 1) results = new PriorityQueue<>();
            else results = new PriorityQueue<>(Comparator.reverseOrder());
            for(String nextDest : locations){
                if(!path.contains(nextDest)){
                    results.offer(findPossible(dict,locations,path+" "+nextDest,part));
                }
            }
            if(results.peek() == null) return -1;
            return results.poll();
        }
    }
}
