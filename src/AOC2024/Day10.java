package AOC2024;
import java.io.*;
import java.util.*;
public class Day10 {
    public static void main(String[] args)
    {
        System.out.println(findScores("inputs/input.txt",1));
        System.out.println(findScores("inputs/input.txt",2));
        //System.out.println(findScores("inputs/trial",2));
    }

    public static int findScores (String fileName, int part)
    {
        File f = new File(fileName);
        int length = 55;
        if(fileName.contains("trial")) length = 8;
        int[][] map = new int[length][];
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                int[] temp = new int[line.length()];
                for(int i = 0; i < line.length(); i++)
                {
                    temp[i] = Integer.parseInt(line.substring(i,i+1));
                }
                map[index] = temp;
                index++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        if(part == 1) return(findTrailScores(map,1));
        return findTrailScores(map,2);
    }

    public static int findTrailScores(int[][] map, int part)
    {
        int count = 0;
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {
                if(part == 1)
                {
                    if(map[r][c] == 0) count+=findScore(map, r, c);
                }
                else{
                    if(map[r][c] == 0) count+=findScore2(map, r, c);
                }
            }
        }
        return  count;
    }

    public static int findScore(int[][] map , int r, int c)
    {
        HashSet<String> endTrials = new HashSet<>();
        goUp(endTrials,map,r,c);
        goDown(endTrials,map,r,c);
        goLeft(endTrials,map,r,c);
        goRight(endTrials,map,r,c);
        return endTrials.size();
    }
    public static int findScore2(int[][] map , int r, int c)
    {
        HashSet<String> endTrials = new HashSet<>();
        goUp2(endTrials,map,r,c,"");
        goDown2(endTrials,map,r,c,"");
        goLeft2(endTrials,map,r,c,"");
        goRight2(endTrials,map,r,c,"");
        return endTrials.size();
    }

    public static void goUp(HashSet<String> endTrials, int[][] map, int r, int c)
    {
        int currentVal = map[r][c];
        if(r > 0 && map[r-1][c]-currentVal == 1)
        {
            goDown(endTrials,map,r-1,c);
            goUp(endTrials,map,r-1,c);
            goRight(endTrials,map,r-1,c);
            goLeft(endTrials,map,r-1,c);
        }
        if(currentVal==9) {
            String temp = r+","+c;
            endTrials.add(temp);
        }
    }

    public static void goDown(HashSet<String> endTrials, int[][] map, int r, int c)
    {
        int currentVal = map[r][c];
        if(r < map.length-1 && map[r+1][c]-currentVal == 1)
        {
            goDown(endTrials,map,r+1,c);
            goUp(endTrials,map,r+1,c);
            goRight(endTrials,map,r+1,c);
            goLeft(endTrials,map,r+1,c);
        }
        if(currentVal==9) {
            String temp = r+","+c;
            endTrials.add(temp);
        }
    }

    public static void goLeft(HashSet<String> endTrials, int[][] map, int r, int c)
    {
        int currentVal = map[r][c];
        if(c > 0 && map[r][c-1]-currentVal == 1)
        {
            goDown(endTrials,map,r,c-1);
            goUp(endTrials,map,r,c-1);
            goRight(endTrials,map,r,c-1);
            goLeft(endTrials,map,r,c-1);
        }
        if(currentVal==9) {
            String temp = r+","+c;
            endTrials.add(temp);
        }
    }

    public static void goRight(HashSet<String> endTrials, int[][] map, int r, int c)
    {
        int currentVal = map[r][c];
        if(c < map[r].length-1 && map[r][c+1]-currentVal == 1)
        {
            goDown(endTrials,map,r,c+1);
            goUp(endTrials,map,r,c+1);
            goRight(endTrials,map,r,c+1);
            goLeft(endTrials,map,r,c+1);
        }
        if(currentVal==9) {
            String temp = r+","+c;
            endTrials.add(temp);
        }
    }

    public static void goUp2(HashSet<String> endTrials, int[][] map, int r, int c, String temp)
    {
        int currentVal = map[r][c];
        temp += r+","+c +" ";
        if(r > 0 && map[r-1][c]-currentVal == 1)
        {
            goDown2(endTrials,map,r-1,c, temp);
            goUp2(endTrials,map,r-1,c, temp);
            goRight2(endTrials,map,r-1,c, temp);
            goLeft2(endTrials,map,r-1,c, temp);
        }
        if(currentVal==9) {
            endTrials.add(temp);
        }
    }

    public static void goDown2(HashSet<String> endTrials, int[][] map, int r, int c, String temp)
    {
        int currentVal = map[r][c];
        temp += r+","+c +" ";
        if(r < map.length-1 && map[r+1][c]-currentVal == 1)
        {
            goDown2(endTrials,map,r+1,c, temp);
            goUp2(endTrials,map,r+1,c, temp);
            goRight2(endTrials,map,r+1,c, temp);
            goLeft2(endTrials,map,r+1,c, temp);
        }
        if(currentVal==9) {
            endTrials.add(temp);
        }
    }

    public static void goLeft2(HashSet<String> endTrials, int[][] map, int r, int c, String temp)
    {
        int currentVal = map[r][c];
        temp += r+","+c +" ";
        if(c > 0 && map[r][c-1]-currentVal == 1)
        {
            goDown2(endTrials,map,r,c-1, temp);
            goUp2(endTrials,map,r,c-1,temp);
            goRight2(endTrials,map,r,c-1, temp);
            goLeft2(endTrials,map,r,c-1,temp);
        }
        if(currentVal==9) {
            endTrials.add(temp);
        }
    }

    public static void goRight2(HashSet<String> endTrials, int[][] map, int r, int c, String temp)
    {
        int currentVal = map[r][c];
        temp += r+","+c +" ";
        if(c < map[r].length-1 && map[r][c+1]-currentVal == 1)
        {
            goDown2(endTrials,map,r,c+1,temp);
            goUp2(endTrials,map,r,c+1,temp);
            goRight2(endTrials,map,r,c+1,temp);
            goLeft2(endTrials,map,r,c+1,temp);
        }
        if(currentVal==9) {
            endTrials.add(temp);
        }
    }


}
