package AOC2021;
import java.io.*;
import java.util.*;
public class Day12 {
    private final static ArrayList<String> combo = new ArrayList<>();
    public static void main(String[] args){
        System.out.println(cave("inputs/input.txt",1));
        System.out.println(cave("inputs/input.txt",2));
    }

    public static int cave(String fileName,int part){
        combo.clear();
        File f = new File(fileName);
        HashMap<String,ArrayList<String>> map =  new HashMap<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                String[] line = s.nextLine().split("-");
                if(map.containsKey(line[0])) map.get(line[0]).add(line[1]);
                else{
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(line[1]);
                    map.put(line[0],temp);
                }
                if(map.containsKey(line[1])) map.get(line[1]).add(line[0]);
                else{
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(line[0]);
                    map.put(line[1],temp);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) findPaths(map,"start","start");
        else findMorePaths(map,"start","start");
        return combo.size();
    }

    public static void findPaths(HashMap<String, ArrayList<String>> map, String current, String path){
        if(path.contains("end")){
            combo.add(path);
            return;
        }
        for(String direction : map.get(current)){
            boolean capital = isBig(direction);
            if(capital) findPaths(map,direction,path+","+direction);
            else{
                if(!path.contains(direction)) findPaths(map,direction,path+","+direction);
            }

        }
    }

    public static void findMorePaths(HashMap<String,ArrayList<String>> map, String current, String path){
        if(path.contains("end")){
            combo.add(path);
            return;
        }
        boolean smallRepeated = isRepeated(path);
        for(String direction : map.get(current)){
            boolean capital = isBig(direction);
            if(capital) findMorePaths(map,direction,path+","+direction);
            else{
                if(!path.contains(direction) || !smallRepeated ) {
                    if(!direction.equals("start")) findMorePaths(map, direction, path + "," + direction);
                }
            }

        }
    }

    public static boolean isBig(String letter){
        String cap = letter.toUpperCase();
        return letter.equals(cap);
    }

    public static boolean isRepeated(String path){
        String[] pathArr = path.split(",");
        HashSet<String> map = new HashSet<>();
        for(String cav : pathArr){
            if(!isBig(cav)){
                if(map.contains(cav)) return true;
                map.add(cav);
            }
        }
        return false;
    }

}
