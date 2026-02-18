package AOC2016;
import java.io.*;
import java.util.*;
public class Day1 {
    public static void main(String[] args){
        System.out.println(shortestPath("Inputs/input.txt",1));
        System.out.println(shortestPath("Inputs/input.txt",2));
        //System.out.println(shortestPath("Inputs/trial",2));
    }

    public static int shortestPath(String fileName, int part){
        String[] directions;
        try{
            Scanner s = new Scanner(new File(fileName));
            directions = s.nextLine().replace(",","").split("\\s");
        } catch (FileNotFoundException e){
            System.out.println("file not found");
            return -1;
        }
        return shortest(directions, part);
    }

    public static int shortest(String[] directions, int part){
        HashSet<String> visited = new HashSet<>();
        String orientation = "N";
        int vertical = 0, horizontal = 0;
        visited.add(horizontal+","+vertical);
        for(String dir : directions){
            String turn = dir.charAt(0)+"";
            int distance = Integer.parseInt(dir.substring(1));
            orientation = nextOrientation(orientation,turn);
            int move = 1;
            if(orientation.equals("N") || orientation.equals("S")){
                if(orientation.equals("S")) move = -1;
                for(int i = 0; i < distance; i++){
                    vertical+=move;
                    if(part == 2 && visited.contains(horizontal+","+vertical)){
                        return Math.abs(vertical)+Math.abs(horizontal);
                    }
                    visited.add(horizontal+","+vertical);
                }
            }
            else{
                if(orientation.equals("E")) move = -1;
                for(int i = 0; i < distance; i++){
                    horizontal+=move;
                    if(part == 2 && visited.contains(horizontal+","+vertical)){
                        return Math.abs(vertical)+Math.abs(horizontal);
                    }
                    visited.add(horizontal+","+vertical);
                }
            }
        }
        return Math.abs(vertical)+Math.abs(horizontal);
    }

    public static String nextOrientation(String current, String turn){
        String orientations = "NESW";
        int index;
        if(turn.equals("R")) index = (orientations.indexOf(current)+1)%orientations.length();
        else index = (orientations.indexOf(current)-1 >= 0) ? orientations.indexOf(current)-1 : orientations.length()-1;
        return orientations.charAt(index)+"";
    }
}
