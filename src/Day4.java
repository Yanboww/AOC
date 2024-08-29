import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day4 {
    public static void main(String[] args)
    {
        System.out.println(findOverlap("inputs/input4"));
        System.out.println(findOverlapAtAll("inputs/input4"));

    }

    public static int findOverlap(String fileName)
    {
        int overlaps = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String[] pair = s.nextLine().split(",");
                String first = pair[0];
                String second = pair[1];
                int firstLo = Integer.parseInt(first.substring(0,first.indexOf("-")));
                int firstHi = Integer.parseInt(first.substring(first.indexOf("-")+1));
                int secondLo = Integer.parseInt(second.substring(0,second.indexOf("-")));
                int secondHi = Integer.parseInt(second.substring(second.indexOf("-")+1));
                if(firstLo<=secondLo && firstHi>= secondHi) overlaps++;
                else if(secondLo<= firstLo && secondHi>=firstHi) overlaps++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return overlaps;
    }
    public static int findOverlapAtAll(String fileName)
    {
        int overlaps = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String[] pair = s.nextLine().split(",");
                String first = pair[0];
                String second = pair[1];
                int firstLo = Integer.parseInt(first.substring(0,first.indexOf("-")));
                int firstHi = Integer.parseInt(first.substring(first.indexOf("-")+1));
                int secondLo = Integer.parseInt(second.substring(0,second.indexOf("-")));
                int secondHi = Integer.parseInt(second.substring(second.indexOf("-")+1));
                if(firstHi<=secondHi && firstHi>=secondLo) overlaps++;
                else if(firstLo<= secondHi && firstLo >= secondLo) overlaps++;
                else if(secondLo <= firstHi && secondLo >= firstLo) overlaps++;
                else if(secondHi <= firstHi && secondHi >= firstLo) overlaps++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return overlaps;
    }

}
