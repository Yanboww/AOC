package Day11;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Day11 {
    public static void main(String[] args)
    {
        System.out.println(findProductTwoMonkey("inputs/input11"));
        System.out.println(findProductTwoMonkeyPart2("inputs/input11"));
    }

    public static int findProductTwoMonkey(String fileName)
    {
        File f = new File(fileName);
        Monkey[] monkeys = new Monkey[8];
        int count = 0;
        try{
            Scanner s = new Scanner(f);
            String[] items = new String[1];
            String operation ="";
            int test=0;
            int trueDestination=0;
            int falseDestination=0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                if(line.equals("next"))
                {
                    monkeys[count] = new Monkey(operation,test,trueDestination,falseDestination,count);
                    for(String item : items)
                    {
                        monkeys[count].addItem(Integer.parseInt(item.trim()));
                    }
                    count++;
                }
                else if(line.contains("Starting")) items = line.substring(line.indexOf(":")+2).split(",");
                else if(line.contains("Operation")) operation = line.substring(line.indexOf("old")+4);
                else if(line.contains("Test")) test = Integer.parseInt(line.substring(line.indexOf("by")+3));
                else if(line.contains("true"))trueDestination = Integer.parseInt(line.substring(line.lastIndexOf("y")+2));
                else if(line.contains("false"))falseDestination = Integer.parseInt(line.substring(line.lastIndexOf("y")+2));

            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        int rounds = 0;
        while(rounds < 20)
        {
            for(Monkey monkey : monkeys)
            {
                for(int i = 0; i < monkey.getItems().size(); i++)
                {
                    long[] result = monkey.removeItem();
                    i--;
                    monkeys[(int)result[0]].addItem(result[1]);
                }
            }
            rounds++;
        }
        return findProductOfTwo(monkeys);
    }

    public static int findProductOfTwo(Monkey[] monkeys)
    {
        int highest = 0;
        int secondHighest = 0;
        for(Monkey monkey : monkeys)
        {
            int time = monkey.getTimesWithItems();
            if(time > highest)
            {
                secondHighest = highest;
                highest = time;
            }
            else if (time > secondHighest) secondHighest = time;
        }
        return highest*secondHighest;
    }

    public static long findProductTwoMonkeyPart2(String fileName)
    {
        File f = new File(fileName);
        Monkey[] monkeys = new Monkey[8];
        int count = 0;
        int mod = 1;
        try{
            Scanner s = new Scanner(f);
            String[] items = new String[1];
            String operation ="";
            int test=0;
            int trueDestination=0;
            int falseDestination=0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                if(line.equals("next"))
                {
                    monkeys[count] = new Monkey(operation,test,trueDestination,falseDestination,count);
                    for(String item : items)
                    {
                        monkeys[count].addItem(Integer.parseInt(item.trim()));
                    }
                    mod*=test;
                    count++;
                }
                else if(line.contains("Starting")) items = line.substring(line.indexOf(":")+2).split(",");
                else if(line.contains("Operation")) operation = line.substring(line.indexOf("old")+4);
                else if(line.contains("Test")) test = Integer.parseInt(line.substring(line.indexOf("by")+3));
                else if(line.contains("true"))trueDestination = Integer.parseInt(line.substring(line.lastIndexOf("y")+2));
                else if(line.contains("false"))falseDestination = Integer.parseInt(line.substring(line.lastIndexOf("y")+2));

            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        int rounds = 0;
        while(rounds < 10000)
        {
            for(Monkey monkey : monkeys)
            {
                for(int i = 0; i < monkey.getItems().size(); i++)
                {
                    long[] result = monkey.removeItem2(mod);
                    i--;
                    monkeys[(int)result[0]].addItem(result[1]);
                }
            }
            rounds++;
        }
        return findProductOfTwo2(monkeys);
    }

    public static long findProductOfTwo2(Monkey[] monkeys)
    {
        int highest = 0;
        int secondHighest = 0;
        for(Monkey monkey : monkeys)
        {
            int time = monkey.getTimesWithItems();
            System.out.println(time);
            if(time > highest)
            {
                secondHighest = highest;
                highest = time;
            }
            else if (time > secondHighest) secondHighest = time;
        }
        return (long)highest*(long)secondHighest;
    }

}
