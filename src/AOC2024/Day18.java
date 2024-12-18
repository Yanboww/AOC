package AOC2024;
import java.io.*;
import java.util.*;
public class Day18 {
    public static void main(String[] args) {
        System.out.println(shortestSteps("inputs/input.txt",1));
        //brute force
        System.out.println(shortestSteps("inputs/input.txt",2) +" index of breakage");
    }

    public static int shortestSteps(String fileName, int part)
    {
        File f = new File(fileName);
        int length = 71;
        if(fileName.contains("trial")) length = 7;
        String[][] map = new String[length][length];
        for(String[] temp : map)
        {
            Arrays.fill(temp,".");
        }
        ArrayList<String> coordinates = new ArrayList<>();
        HashMap<String,Integer> dict = new HashMap<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                coordinates.add(s.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        int[] start = new int[]{0,0};
        int[] end = new int[]{length-1,length-1};
        int count = 1024;
        if(fileName.contains("trial")) count =12;
        for(int i = 0; i < count; i++)
        {
            String[] temp = coordinates.get(i).split(",");
            map[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = "#";
        }
        ArrayList<String> nodes = new ArrayList<>();
        for(int r = 0; r < map.length; r++)
        {
            for(int c =0; c < map.length; c++)
            {
                if(map[r][c].equals(".")) nodes.add(r+","+c);
            }
        }
        if(part ==1 ){
            return lowestScoreCalc(map,start,end,dict,nodes);
        }
        else{
            for(int i = count; i < coordinates.size(); i+=1)
            {
                String[] temp = coordinates.get(i).split(",");
                map[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = "#";
                dict.clear();
                nodes = new ArrayList<>();
                for(int r = 0; r < map.length; r++)
                {
                    for(int c =0; c < map.length; c++)
                    {
                        if(map[r][c].equals(".")) nodes.add(r+","+c);
                    }
                }
                if(lowestScoreCalc(map,start,end,dict,nodes) == -1){
                    System.out.println(Arrays.toString(temp));
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lowestScoreCalc(String[][] map, int[] start, int[] end, HashMap<String,Integer> dict, ArrayList<String> nodes)
    {
        ArrayList<int[]> queue = new ArrayList<>();
        queue.add(start);
        while (!queue.isEmpty()){
            int[] cord = queue.get(0);
            queue.remove(0);
            String temp2 = cord[0]+","+cord[1];
            int index = nodes.indexOf(temp2);
            if(index != -1) {
                nodes.remove(index);
            }
            int score = 0;
            if(dict.containsKey(temp2)) score = dict.get(temp2);
            if(cord[1]>0 && !map[cord[0]][cord[1]-1].equals("#")){
                String temp = cord[0]+","+(cord[1]-1);
                if(!dict.containsKey(temp) || dict.get(temp) > score+1) dict.put(temp,score+1);
                if(nodes.contains(temp)){
                    queue.add(new int[]{cord[0],cord[1]-1});
                    nodes.remove(temp);
                }
            }
            if(cord[1] < map.length-1 && !map[cord[0]][cord[1]+1].equals("#"))
            {
                String temp = cord[0]+","+(cord[1]+1);
                if(!dict.containsKey(temp) || dict.get(temp) > score+1) dict.put(temp,score+1);
                if(nodes.contains(temp)){
                    queue.add(new int[]{cord[0],cord[1]+1});
                    nodes.remove(temp);
                }
            }
            if(cord[0] < map.length-1 && !map[cord[0]+1][cord[1]].equals("#"))
            {
                String temp = cord[0]+1+","+(cord[1]);
                if(!dict.containsKey(temp) || dict.get(temp) > score+1) dict.put(temp,score+1);
                if(nodes.contains(temp)){
                    queue.add(new int[]{cord[0]+1,cord[1]});
                    nodes.remove(temp);
                }
            }
            if(cord[0] > 0 && !map[cord[0]-1][cord[1]].equals("#"))
            {
                String temp = cord[0]-1+","+(cord[1]);
                if(!dict.containsKey(temp) || dict.get(temp) > score+1) dict.put(temp,score+1);
                if(nodes.contains(temp)){
                    queue.add(new int[]{cord[0]-1,cord[1]});
                    nodes.remove(temp);
                }
            }
            //if(dict.containsKey(end[0]+","+end[1])) System.out.println(dict.get(end[0]+","+end[1]));
        }
        if(!dict.containsKey(end[0]+","+end[1])) return -1;
        return dict.get(end[0]+","+end[1]);
    }

}
