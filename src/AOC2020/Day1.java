package AOC2020;
import java.io.*;
import java.util.*;
public class Day1 {
    public static void main(String[] args) {
        System.out.println(findReport("inputs/input.txt", 1));
        System.out.println(findReport("inputs/input.txt", 2));
    }

    public static long findReport(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<Integer> inputs = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) inputs.add(s.nextInt());
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        if(part == 1) return product2020(inputs);
        return product2020Part2(inputs);
    }

    public static int product2020(ArrayList<Integer> inputs)
    {
        for(int i = 0; i < inputs.size(); i++)
        {
            for(int j = i+1; j < inputs.size(); j++){
                int first = inputs.get(i);
                int second = inputs.get(j);
                if(first+second==2020) return first*second;
            }
        }
        return -1;
    }

    public static long product2020Part2(ArrayList<Integer> inputs)
    {
        for(int i = 0; i < inputs.size(); i++)
        {
            for(int j = i+1; j < inputs.size(); j++){
               for(int k = j+1; k < inputs.size(); k++)
               {
                   int first = inputs.get(i);
                   int second = inputs.get(j);
                   int third = inputs.get(k);
                   if(first+second+third==2020) return (long) first*second*third;
               }
            }
        }
        return -1;
    }
}
