package AOC2021;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day1 {
    public static void main(String[] args) {
        System.out.println(countIncrease("inputs/AOC2021Inputs/input1"));
        System.out.println(countIncrease2("inputs/AOC2021Inputs/input1"));
    }
    public static int countIncrease(String fileName){
        File f = new File(fileName);
        int counter = 0;
        try{
            Scanner s = new Scanner(f);
            int prev = Integer.parseInt(s.nextLine());
            while(s.hasNextLine())
            {
                int current = Integer.parseInt(s.nextLine());
                if(prev < current) counter++;
                prev = current;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("file not found");
        }

        return counter;
    }
    public static int countIncrease2(String fileName){
        File f = new File(fileName);
        int counter = 0;
        int[] group = new int[1998];
        try{
            int[] storage = new int[2000];
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine())
            {
                storage[index] = Integer.parseInt(s.nextLine());
                index++;
            }
            for(int i = 0; i < group.length; i++)
            {
                group[i] = storage[i] + storage[i+1] + storage[i+2];
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("file not found");
        }
        int prev = group[0];
        for(int i = 1; i < group.length; i++)
        {
            int current = group[i];
            if(prev < current) counter++;
            prev = current;
        }
        return counter;
    }

}
