import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class Day1 {
    public static void main(String[] args)
    {
        System.out.println(findHighestProtein("inputs/input1"));
        System.out.println(findTopThree("inputs/input1"));
    }

    public static int findHighestProtein(String fileName)
    {
        int maxProtein = 0;
        int currentProtein = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                int currentVal = isInt(s.nextLine());
                if(currentVal>0) currentProtein+=currentVal;
                else {
                    maxProtein = Math.max(maxProtein, currentProtein);
                    currentProtein = 0;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return maxProtein;
    }

    public static int findTopThree(String fileName)
    {
        int maxProtein = 0;
        int secondHighest = 0;
        int thirdHighest = 0;
        int currentProtein = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                int currentVal = isInt(s.nextLine());
                if(currentVal>0) currentProtein+=currentVal;
                else {
                    if(currentProtein>maxProtein)
                    {
                        thirdHighest = secondHighest;
                        secondHighest = maxProtein;
                        maxProtein = currentProtein;
                    }
                    else if(currentProtein>secondHighest)
                    {
                        thirdHighest = secondHighest;
                        secondHighest = currentProtein;
                    }
                    else if(currentProtein> thirdHighest)
                    {
                        thirdHighest = currentProtein;
                    }
                    currentProtein = 0;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return maxProtein + secondHighest + thirdHighest;
    }

    public static int isInt(String val)
    {
        try{
            return Integer.parseInt(val);
        }
        catch(NumberFormatException e)
        {
            return -1;
        }
    }
}
