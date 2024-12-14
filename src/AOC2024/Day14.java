package AOC2024;
import java.io.*;
import java.util.*;
public class Day14 {
    public static void main(String[] args) {
        System.out.println(findSafety("inputs/input.txt",1));
        System.out.println(findSafety("inputs/input.txt",2));
    }
    public static int findSafety(String fileName, int part)
    {
        File f = new File(fileName);
        int r = 103;
        int c = 101;
        if(fileName.contains("trial"))
        {
            r = 7;
            c = 11;
        }
        ArrayList<int[]> input = new ArrayList<>();
        int[][] map = new int[r][c];
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                String line = s.nextLine();
                int[] temp = parse(line);
                input.add(temp);
                if(part == 1)calc100SecMap(map,temp);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        if(part == 1) return calcScore(map);
        else{
            return calcInfinite(map,input);
        }
    }

    public static int[] parse(String line)
    {
        int[] temp = new int[4];
        temp[0] = Integer.parseInt(line.substring(line.indexOf("=")+1,line.indexOf(",")));
        temp[1] = Integer.parseInt(line.substring(line.indexOf(",")+1,line.indexOf(" ")));
        temp[2] = Integer.parseInt(line.substring(line.lastIndexOf("=")+1, line.lastIndexOf(",")));
        temp[3] = Integer.parseInt(line.substring(line.lastIndexOf(",")+1));
        return temp;
    }

    public static void calc100SecMap(int[][]map, int[] temp)
    {
        int rLength = map.length;
        int cLength = map[0].length;
        for(int i = 0; i < 100; i++)
        {
            temp[0] += temp[2];
            temp[1] += temp[3];
            if(temp[0] < 0) temp[0] += cLength;
            else if(temp[0]>=cLength) temp[0]-=cLength;
            if(temp[1] < 0) temp[1] += rLength;
            else if(temp[1]>=rLength)temp[1]-=rLength;
        }
        map[temp[1]][temp[0]]++;
    }

    public static int calcScore(int[][] map)
    {
        int midR = map.length/2;
        int midC = map[0].length/2;
        int count;
        int current = 0;
        for(int r= 0; r < midR; r++)
        {
            for(int c = 0; c < midC; c++)
            {
                current+=map[r][c];
            }
        }
        count=current;
        current = 0;
        for(int r = midR+1; r < map.length; r++)
        {
            for(int c = 0; c < midC; c++)
            {
                current+=map[r][c];
            }
        }
        count*=current;
        current = 0;
        for(int r= 0; r < midR; r++)
        {
            for(int c = midC+1; c < map[0].length; c++)
            {
                current+=map[r][c];
            }
        }
        count*=current;
        current = 0;
        for(int r = midR+1; r < map.length; r++)
        {
            for(int c = midC+1; c < map[0].length; c++)
            {
                current+=map[r][c];
            }
        }
        count*=current;
        return count;
    }

    public static int calcInfinite (int[][]map, ArrayList<int[]> input)
    {
        int lowest = -1;
        int sec = 0;
        for(int i = 1; i <= 10403; i++)
        {
            int[][] tempMap = new int[map.length][map[0].length];
            for(int[] temp : input)
            {
                calcVariableMap(tempMap, temp);
            }
            int val = calcScore(tempMap);
            if(lowest == -1 || lowest > val) {
                sec = i;
                lowest = val;
            }

        }
        return sec;
    }

    public static void calcVariableMap(int[][] map, int[] temp)
    {
        int rLength = map.length;
        int cLength = map[0].length;
        temp[0] += temp[2];
        temp[1] += temp[3];
        if(temp[0] < 0) temp[0] += cLength;
        else if(temp[0]>=cLength) temp[0]-=cLength;
        if(temp[1] < 0) temp[1] += rLength;
        else if(temp[1]>=rLength)temp[1]-=rLength;
        map[temp[1]][temp[0]]++;
    }

}
