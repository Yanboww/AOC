package AOC2025;
import java.io.*;
import java.util.*;
public class Day7 {
    public static void main(String[] args){
        System.out.println(teleport("inputs/input.txt",1));
        System.out.println(teleport("inputs/input.txt",2));
    }

    public static long teleport(String fileName, int part){
        int len = 142;
        if(fileName.contains("trial")) len = 16;
        String[][] map = new String[len][];
        int startIndex = 0;
        try {
            Scanner s = new Scanner(new File(fileName));
            int index = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                map[index] = line.split("(?!^)");
                if(index == 0) startIndex = line.indexOf("S");
                index++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return timeLines(map,startIndex,part);
    }

    public static long timeLines(String[][] map, int start, int part){
        PriorityQueue<String> queue = new PriorityQueue<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer("0,2,"+start);
        map[0][start] = "1";
        int count = 0;
        while(!queue.isEmpty()){
            int[] coordinate = Arrays.stream(queue.poll().split(",")).mapToInt(Integer::parseInt).toArray();
            coordinate[1] =coordinate[2];
            if(coordinate[0] < map.length){
                String value = map[coordinate[0]][coordinate[1]];
                if(value.equals("^")){
                    count++;
                    long numberVal = Long.parseLong(map[coordinate[0]-1][coordinate[1]]);
                    if(coordinate[1] > 0 && !map[coordinate[0]][coordinate[1]-1].equals("^")){
                        String coordinate1 = Integer.toString(coordinate[0]);
                        String coordinate2 = Integer.toString((coordinate[1]-1));
                        if(coordinate1.length()<2) coordinate1 = "00"+coordinate1;
                        else if(coordinate1.length()<3) coordinate1 = "0"+coordinate1;
                        if(coordinate2.length()<2) coordinate2 = "00"+coordinate2;
                        else if(coordinate2.length()<3) coordinate2 = "0"+coordinate2;
                        String next = coordinate1+",2,"+coordinate2;
                        String left = map[coordinate[0]][coordinate[1]-1];
                        long currentVal = 0;
                        if(!left.equals(".")) currentVal = Long.parseLong(left);
                        map[coordinate[0]][coordinate[1]-1] = Long.toString(currentVal+numberVal);
                        if(!visited.contains(next)){
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                    if(coordinate[1] < map.length && !map[coordinate[0]][coordinate[1]+1].equals("^")){
                        String coordinate1 = Integer.toString(coordinate[0]);
                        String coordinate2 = Integer.toString(coordinate[1]+1);
                        if(coordinate1.length()<2) coordinate1 = "00"+coordinate1;
                        else if(coordinate1.length()<3) coordinate1 = "0"+coordinate1;
                        if(coordinate2.length()<2) coordinate2 = "00"+coordinate2;
                        else if(coordinate2.length()<3) coordinate2 = "0"+coordinate2;
                        String next = coordinate1+",2,"+coordinate2;
                        String right = map[coordinate[0]][coordinate[1]+1];
                        long currentVal = 0;
                        if(!right.equals(".")) currentVal = Long.parseLong(right);
                        map[coordinate[0]][coordinate[1]+1] = Long.toString(currentVal+numberVal);
                        if(!visited.contains(next)){
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
                else{
                    if(coordinate[0] < map.length-1){
                        long currentVal = 0;
                        long valueInt = Long.parseLong(value);
                        String bottom = map[coordinate[0]+1][coordinate[1]];
                        if(!bottom.equals("^") && !bottom.equals(".")) currentVal = Long.parseLong(bottom);
                        if(!bottom.equals("^"))map[coordinate[0]+1][coordinate[1]]  = Long.toString(valueInt+currentVal);
                        String coordinate1 = Integer.toString(coordinate[0]+1);
                        String coordinate2 = Integer.toString(coordinate[1]);
                        if(coordinate1.length()<2) coordinate1 = "00"+coordinate1;
                        else if(coordinate1.length()<3) coordinate1 = "0"+coordinate1;
                        if(coordinate2.length()<2) coordinate2 = "00"+coordinate2;
                        else if(coordinate2.length()<3) coordinate2 = "0"+coordinate2;
                        String next = coordinate1+",2,"+coordinate2;
                        if(bottom.equals("^")) next = coordinate1+",1,"+coordinate2;
                        if(!visited.contains(next)){
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
            }
        }
        if(part == 1) return count;
        return calcLastRow(map);
    }

    public static long calcLastRow(String[][] map){
        int last = map.length-1;
        long count = 0;
        for(int i = 0; i < map[last].length; i++){
            String val = map[last][i];
            if(!val.equals(".")) count += Long.parseLong(val);
        }
        return count;
    }

}
