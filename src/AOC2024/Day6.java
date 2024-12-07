package AOC2024;
import java.util.*;
import java.io.*;
public class Day6 {
    static HashSet <int[]> steps = new HashSet<>();
    public static void main(String[] args)
    {
        System.out.println(countGuardStep("inputs/AOC2024Inputs/day6", 1));
        System.out.println(countGuardStep("inputs/AOC2024Inputs/day6", 2));
    }

    public static int countGuardStep(String fileName, int part)
    {
        File f = new File(fileName);
        int length = 130;
        if(fileName.contains("trial")) length = 10;
        String[][] map = new String[length][];
        int[] start = new int[2];
        try{
            Scanner s = new Scanner(f);
            int row = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                String[] temp = new String[line.length()];
                for(int i = 0; i < temp.length; i++)
                {
                    String letter = line.substring(i,i+1);
                    temp[i] = letter;
                    if(letter.equals("^")){
                        start[0] = row;
                        start[1] = i;
                    }
                }
                map[row] = temp;
                row++;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        int[] start2 = new int[]{start[0],start[1]};
        if(part == 1)return countSteps(map, start);
        return findRecursion(map, start2);
    }

    public static int countSteps(String[][] map, int[] start)
    {
        String dir = "^";
        while(start[0] < map.length-1 && start[0] > 0 && start[1] < map[start[0]].length-1 && start[1] > 0)
        {
            if(dir.equals("^"))
            {
                int newRow = start[0]-1;
                if(map[newRow][start[1]].equals("#"))
                {
                    dir = ">";
                }
                else{
                    start[0]--;
                    map[newRow+1][start[1]] ="X";
                }
            }
            else if(dir.equals(">"))
            {
                int newCol = start[1]+1;
                if(map[start[0]][newCol].equals("#"))
                {
                    dir = "v";
                }
                else{
                    start[1]++;
                    map[start[0]][newCol-1] ="X";
                }
            }
            else if(dir.equals("v"))
            {
                int newRow = start[0]+1;
                if(map[newRow][start[1]].equals("#"))
                {
                    dir = "<";
                }
                else{
                    start[0]++;
                    map[newRow-1][start[1]] ="X";
                }
            }
            else {
                int newCol = start[1]-1;
                if(map[start[0]][newCol].equals("#"))
                {
                    dir = "^";
                }
                else{
                    start[1]--;
                    map[start[0]][newCol+1] ="X";
                }
            }
        }
        steps.add(start);
        return countX(map);
    }
    private static int countX(String[][] map)
    {
        int count = 1;
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {
                if(map[r][c].equals("X")) {
                    count++;
                    steps.add(new int[]{r,c});
                }

            }
        }
        return count;
    }

    public static int findRecursion(String[][] map, int[] start)
    {
        Iterator<int[]> pastSteps = steps.iterator();
        int count = 0;
        while(pastSteps.hasNext())
        {
            int[] re = pastSteps.next();
            map[re[0]][re[1]] = "#";
            if(isRecurse(map,start)) count++;
            map[re[0]][re[1]] = ".";
        }
        return count;
    }

    public static boolean isRecurse(String[][] map, int[] start1)
    {
        int[] start = new int[]{start1[0],start1[1]};
        HashSet<String> pastData = new HashSet<>();
        String dir = "^";
        pastData.add(start[0] + ","+ start[1]+","+dir);
        while(start[0] < map.length-1 && start[0] > 0 && start[1] < map[start[0]].length-1 && start[1] > 0)
        {
            if(dir.equals("^"))
            {
                int newRow = start[0]-1;
                if(map[newRow][start[1]].equals("#"))
                {
                    dir = ">";
                }
                else{
                    start[0]--;
                }
            }
            else if(dir.equals(">"))
            {
                int newCol = start[1]+1;
                if(map[start[0]][newCol].equals("#"))
                {
                    dir = "v";
                }
                else{
                    start[1]++;
                }
            }
            else if(dir.equals("v"))
            {
                int newRow = start[0]+1;
                if(map[newRow][start[1]].equals("#"))
                {
                    dir = "<";
                }
                else{
                    start[0]++;
                }
            }
            else {
                int newCol = start[1]-1;
                if(map[start[0]][newCol].equals("#"))
                {
                    dir = "^";
                }
                else{
                    start[1]--;
                }
            }
            if(pastData.contains(start[0] + ","+ start[1]+","+dir)) return true;
            pastData.add(start[0] + ","+ start[1]+","+dir);
        }
        return false;
    }
}
