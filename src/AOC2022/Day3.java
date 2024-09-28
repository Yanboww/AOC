package AOC2022;

import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
public class Day3 {
     static HashMap<String, Integer> priorityList = new HashMap<>();
    public static void main(String[] args)
    {
        setPriority();
        System.out.println(findPriority("inputs/AOC2022Inputs/input3"));
        System.out.println(findBadgePriority("inputs/AOC2022Inputs/input3"));
    }

    public static void setPriority()
    {
        String values = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < values.length(); i++)
        {
            priorityList.put(values.substring(i,i+1),i+1);
        }
    }


    public static int findPriority(String fileName)
    {
        int priority = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                HashSet<String> cache = new HashSet<>();
                String currentHaySack = s.nextLine();
                int mid = currentHaySack.length()/2;
                String firstHalf = currentHaySack.substring(0,mid);
                String secondHalf = currentHaySack.substring(mid);
                for(int i = 0; i < firstHalf.length(); i++) {
                    String letter = firstHalf.substring(i, i + 1);
                    if (!cache.contains(letter)) {
                        cache.add(letter);
                        if (secondHalf.contains(letter)) {
                            priority += priorityList.get(letter);
                            break;
                        }
                    }

                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return priority;
    }

    public static int findBadgePriority(String fileName)
    {
        int priority = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                HashSet<String> cache = new HashSet<>();
                String group1 = s.nextLine();
                String group2 = s.nextLine();
                String group3 = s.nextLine();
                for(int i = 0;  i < group1.length(); i++)
                {
                    String letter = group1.substring(i,i+1);
                    if(!cache.contains(letter))
                    {
                        cache.add(letter);
                        if(group2.contains(letter) && group3.contains(letter)) priority+=priorityList.get(letter);
                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return priority;
    }


}

