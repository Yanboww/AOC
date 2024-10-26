package AOC2021;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Day3 {
    public static void main(String[] args)
    {
        System.out.println(calcPowerConsumption("inputs/AOC2021Inputs/input3"));
        System.out.println(calcLifeSupport("inputs/AOC2021Inputs/input3"));
    }
    public static int calcPowerConsumption(String fileName)
    {
        int size = 1000;
        if(fileName.contains("trial")) size = 12;
        File f = new File(fileName);
        String[][] info = new String[size][];
        try{
            Scanner s = new Scanner(f);
            int count = 0;
            while(s.hasNextLine())
            {
                info[count] = s.nextLine().split("(?!^)");
                count++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        int[] binary = new int[info[0].length];
       /* for(String[] line : info)
        {
            System.out.println(Arrays.toString(line));
        }**/
        findMostCommon(info,binary);
        int gamma = calcGamma(binary);
        int epsilon = calcEpsilon(binary);
        return gamma * epsilon;
    }
    public static int calcLifeSupport(String fileName)
    {
        int size = 1000;
        if(fileName.contains("trial")) size = 12;
        File f = new File(fileName);
        String[][] info = new String[size][];
        try{
            Scanner s = new Scanner(f);
            int count = 0;
            while(s.hasNextLine())
            {
                info[count] = s.nextLine().split("(?!^)");
                count++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        int O = findO(info);
        int CO2 = findCO2(info);
        return O * CO2;
    }
    public static int findO(String[][] info)
    {
        ArrayList<String[]> remaining = new ArrayList<>();
        int count1 = 0;
        int count0 = 0;
        for(String[] data : info)
        {
            if(data[0].equals("1")) count1++;
            else count0++;
        }
        if(count1 > count0)
        {
            for(String[] data : info)
            {
                if(data[0].equals("1")) remaining.add(data);
            }
        }
        else
        {
            for(String[] data : info)
            {
                if(data[0].equals("0")) remaining.add(data);
            }
        }
        remaining = findOHelper(remaining,1);
        return calcBinary(remaining.get(0));
    }
    private static ArrayList<String[]> findOHelper(ArrayList<String[]> remaining, int c)
    {
       /* for(String[] test : remaining)
        {
            System.out.print(Arrays.toString(test) +" ");
        }**/
        if(remaining.size() == 1) return remaining;
        ArrayList<String[]> remaining2 = new ArrayList<>();
        int count1 = 0;
        int count0 = 0;
        for(String[] data : remaining)
        {
            if(data[c].equals("1")) count1++;
            else count0++;
        }
        if(count0 > count1)
        {
            for(String[] data : remaining)
            {
                if(data[c].equals("0")) remaining2.add(data);
            }
        }
        else{
            for(String[] data : remaining)
            {
                if(data[c].equals("1")) remaining2.add(data);
            }
        }
        remaining = remaining2;
        remaining = findOHelper(remaining,c+1);
        return remaining;
    }
    public static int findCO2(String[][] info)
    {
        ArrayList<String[]> remaining = new ArrayList<>();
        int count1 = 0;
        int count0 = 0;
        for(String[] data : info)
        {
            if(data[0].equals("1")) count1++;
            else count0++;
        }
        if(count1 < count0)
        {
            for(String[] data : info)
            {
                if(data[0].equals("1")) remaining.add(data);
            }
        }
        else
        {
            for(String[] data : info)
            {
                if(data[0].equals("0")) remaining.add(data);
            }
        }
        remaining = findCO2Helper(remaining,1);
        return calcBinary(remaining.get(0));
    }
    private static ArrayList<String[]> findCO2Helper(ArrayList<String[]> remaining, int c)
    {
        if(remaining.size() == 1) return remaining;
        ArrayList<String[]> remaining2 = new ArrayList<>();
        int count1 = 0;
        int count0 = 0;
        for(String[] data : remaining)
        {
            if(data[c].equals("1")) count1++;
            else count0++;
        }
        if(count1 < count0)
        {
            for(String[] data : remaining)
            {
                if(data[c].equals("1")) remaining2.add(data);
            }
        }
        else
        {
            for(String[] data : remaining)
            {
                if(data[c].equals("0")) remaining2.add(data);
            }
        }
        remaining = remaining2;
        remaining = findCO2Helper(remaining,c+1);
        return remaining;
    }
    public static void findMostCommon(String[][] original, int[] result)
    {
        int resultIndex = 0;
        for(int c = 0; c < original[0].length; c++)
        {
            int count1 = 0;
            int count0 = 0;
            for(String[] data : original)
            {
                if(data[c].equals("1")) count1++;
                else count0++;
            }
            if(count1 > count0) result[resultIndex] = 1;
            else result[resultIndex] = 0;
            resultIndex++;
        }
    }
    public static int calcGamma(int[] binary)
    {
        int power = binary.length-1;
        int total = 0;
        for(int i : binary )
        {
            if(i == 1) total+= (int)Math.pow(2,power);
            power--;
        }
        return total;
    }
    public static int calcBinary(String[] binary)
    {
        int power = binary.length-1;
        int total = 0;
        for(String i : binary )
        {
            if(i.equals("1")) total+= (int)Math.pow(2,power);
            power--;
        }
        return total;
    }
    public static int calcEpsilon(int[] binary)
    {
        int power = binary.length-1;
        int total = 0;
        for(int i : binary )
        {
            if(i == 0) total+= (int)Math.pow(2,power);
            power--;
        }
        return total;
    }

}
