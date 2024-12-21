package AOC2024;
import java.io.*;
import java.util.*;
public class Day20 {
    public static void main(String[] args)
    {
        System.out.println(findCheats("inputs/input.txt"));
        //System.out.println(findCheats("inputs/trial"));
    }

    public  static  int findCheats(String fileName)
    {
        File f = new File(fileName);
        int length = 141;
        if(fileName.contains("trial")) length = 15;
        String[][] map = new String[length][];
        HashMap<String,Integer> dict = new HashMap<>();
        ArrayList<String> nodes = new ArrayList<>();
        int[] start = new int[2];
        int[] end = new int[2];
        ArrayList<String> possible = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                String[] temp = new String[line.length()];
                for(int i =0; i < line.length(); i++){
                    temp[i] = line.substring(i,i+1);
                    if(temp[i].equals("S")) start = new int[]{index,i};
                    else if(temp[i].equals("E")) end = new int[]{index,i};
                    else if(temp[i].equals(".")) nodes.add(index+","+i);
                    else if(temp[i].equals("#") && index != 0 && index != map.length-1 && i != 0 && i != line.length()-1) possible.add(index+","+i);
                }
                map[index] = temp;
                index++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        int steps = Day18.lowestScoreCalc(map,start,end,dict,nodes);
        int count = 0;
        for(String coordinate : possible)
        {
            String[] cord = coordinate.split(",");
            System.out.println(coordinate);
            dict.clear();
            nodes.clear();
            String[][] tempMap = new String[map.length][map[0].length];
            for(int i = 0; i < map.length; i++)
            {
                System.arraycopy(map[i],0,tempMap[i],0,tempMap[i].length);
            }
            tempMap[Integer.parseInt(cord[0])][Integer.parseInt(cord[1])] = ".";
            fillNodes(nodes,tempMap);
            if(steps - Day18.lowestScoreCalc(tempMap,start,end,dict,nodes ) >= 100) count++;
        }
        return count;
    }

    public static void fillNodes(ArrayList<String> nodes, String[][] map)
    {
        for(int r = 0; r < map.length; r++){
            for(int c = 0; c < map[0].length; c++)
            {
                if(map[r][c].equals(".")) nodes.add(r+","+c);
            }
        }
    }
}
