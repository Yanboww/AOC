package AOC2020;
import java.io.*;
import java.util.*;


public class Day12 {
    private final static  String[] arr = new String[]{"N","E","S","W"};
    private final static  HashMap<String,Integer> index = new HashMap<>();
    public static void main(String[] args){
        index.put("N",0);
        index.put("E",1);
        index.put("S",2);
        index.put("W",3);
        System.out.println(findDistance("inputs/input.txt",1));
        System.out.println(findDistance("inputs/input.txt",2));
    }

    public static int findDistance(String fileName, int part){
        File f = new File(fileName);
        ArrayList<String> commands = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) commands.add(s.nextLine());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return findManhattanDistance(commands);
        return wayPointDistance(commands);
    }

    public static int findManhattanDistance(ArrayList<String> commands){
        HashMap<String,Integer> traveled = new HashMap<>();
        traveled.put("N",0);
        traveled.put("S",0);
        traveled.put("W",0);
        traveled.put("E",0);
        String current = "E";
        for(String command : commands){
            String type = command.substring(0,1);
            int movement = Integer.parseInt(command.substring(1));
            if(type.equals("F")) traveled.put(current,traveled.get(current)+movement);
            else if(type.equals("R") || type.equals("L")) current = findNewDirection(current,type, movement);
            else traveled.put(type,traveled.get(type)+movement);
        }
        return Math.abs(traveled.get("N")-traveled.get("S")) + Math.abs(traveled.get("W")-traveled.get("E"));
    }

    public static int wayPointDistance(ArrayList<String> commands){
        String[] wayPoint = new String[]{"E", "10","N","1"};
        HashMap<String,Integer> traveled = new HashMap<>();
        traveled.put("N",0);
        traveled.put("S",0);
        traveled.put("W",0);
        traveled.put("E",0);
        for(String command: commands){
            String type = command.substring(0,1);
            int movement = Integer.parseInt(command.substring(1));
            if(type.equals("F")){
                for(int i = 0; i < movement; i++){
                    traveled.put(wayPoint[0],traveled.get(wayPoint[0])+Integer.parseInt(wayPoint[1]));
                    traveled.put(wayPoint[2],traveled.get(wayPoint[2])+Integer.parseInt(wayPoint[3]));
                }
            }
            else if(type.equals("R") || type.equals("L")){
                wayPoint[0] = findNewDirection(wayPoint[0],type,movement);
                wayPoint[2] = findNewDirection(wayPoint[2],type,movement);
            }
            else{
                String first = wayPoint[0];
                int val1 = Integer.parseInt(wayPoint[1]);
                String second = wayPoint[2];
                int val2 = Integer.parseInt(wayPoint[3]);
                if(horizontal(second) == horizontal(type)){
                    if(isOpposite(second,type)) val2-=movement;
                    else val2 += movement;
                    wayPoint[3] = Integer.toString(val2);
                }
                else{
                    if(isOpposite(first,type)) val1-=movement;
                    else val1+=movement;
                    wayPoint[1] = Integer.toString(val1);
                }
            }
        }
        return Math.abs(traveled.get("N")-traveled.get("S")) + Math.abs(traveled.get("W")-traveled.get("E"));
    }

    public static boolean isOpposite (String first, String second){
        if(first.equals("W") && second.equals("E")) return true;
        else if (first.equals("E") && second.equals("W")) return true;
        else if (first.equals("N") && second.equals("S")) return true;
        else return first.equals("S") && second.equals("N");
    }

    public static boolean horizontal ( String direction ){
        if(direction.equals("W")) return true;
        else return direction.equals("E");
    }
    public static String findNewDirection(String current, String modification, int amount){
        int nextIndex = index.get(current);
        int modifyBy = amount/90;
        for(int i = 0; i < modifyBy; i++){
            if(modification.equals("R")){
                nextIndex ++;
                if(nextIndex > 3) nextIndex = 0;
            }
            else{
                nextIndex --;
                if(nextIndex < 0) nextIndex = 3;
            }
        }
        return arr[nextIndex];
    }
}
