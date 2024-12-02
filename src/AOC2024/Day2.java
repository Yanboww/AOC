package AOC2024;
import java.io.*;
import java.util.*;
public class Day2 {
    public static void main(String[] args)
    {
        System.out.println(findSafe("inputs/AOC2024Inputs/day2", 1));
        System.out.println(findSafe("inputs/AOC2024Inputs/day2", 2));
    }

    public static int findSafe(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<String[]> inputs = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                inputs.add(s.nextLine().split("\\s+"));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        int count = 0;
        int count2 = 0;
        for(String[] line : inputs)
        {
            if(isSafe(line)) count++;
            if(isSafe2(line)) count2++;
        }
        if(part == 1 ) return count;
        return count2;
    }

    public static boolean isSafe(String[] line)
    {
        int prev = Integer.parseInt(line[0]);
        boolean increase =  Integer.parseInt(line[0]) < Integer.parseInt(line[1]);
        for(int i = 1; i < line.length; i++)
        {
            int val = Integer.parseInt(line[i]);
            if(val < prev && increase) return false;
            if(val > prev && !increase) return false;
            int diff = Math.abs(val-prev);
            if(diff < 1 || diff > 3) return false;
            prev = val;
        }
        return true;
    }

    public static boolean isSafe2(String[] line)
    {
        for(int i = 0; i < line.length; i++)
        {
            String[] temp = new String[line.length-1];

            int index = 0;
            for(int k = 0; k < line.length; k++)
            {
                if(k != i) {
                    temp[index] = line[k];
                    index++;
                }
            }
            if(isSafe(temp)) return true;
        }
        return false;
    }
}
