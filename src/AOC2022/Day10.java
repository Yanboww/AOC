package AOC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Day10 {
    public static void main(String[] args)
    {
        System.out.println(findSignalStrength("inputs/AOC2022Inputs/input10"));
        findSignalPrint("inputs/AOC2022Inputs/input10");
    }

    public static int findSignalStrength(String fileName)
    {
        int totalStrength = 0;
        int cycle = 0;
        int stop = 20;
        int x = 1;
        try{
            File f= new File(fileName);
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String[] command = s.nextLine().split(" ");
                if(command.length == 1) cycle++;
                else{
                    cycle++;
                    if(cycle==stop){
                        System.out.println(cycle + " " + x);
                        totalStrength+= x*cycle;
                        stop+=40;
                    }
                    cycle++;
                    if(cycle==stop){
                        System.out.println(cycle + " " + x);
                        totalStrength+= x*cycle;
                        stop+=40;
                    }
                    x+=Integer.parseInt(command[1]);
                }
                if(cycle==stop){
                    System.out.println(cycle + " " + x);
                    totalStrength+= x*cycle;
                    stop+=40;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return totalStrength;
    }

    public static void findSignalPrint(String fileName)
    {
        String[][] screen = new String[6][40];
        for(String[] row : screen)
        {
            Arrays.fill(row,".");
        }
        int cycle = -1;
        int x = 1;
        int currentRow = 0;
        try{
            File f= new File(fileName);
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                if (cycle == 39) {
                    cycle = -1;
                    if(currentRow!=5)
                    {
                        currentRow++;
                    }
                }
                String[] command = s.nextLine().split(" ");
                if(command.length == 1) cycle++;
                else{
                    cycle++;
                    if(x-1 == cycle || x+1 == cycle || cycle == x)
                    {
                        screen[currentRow][cycle] = "#";
                    }
                    cycle++;
                    if(x-1 == cycle || x+1 == cycle || cycle == x)
                    {
                        screen[currentRow][cycle] = "#";
                    }
                    x+=Integer.parseInt(command[1]);
                }
                if(x-1 == cycle || x+1 == cycle)
                {
                    screen[currentRow][cycle] = "#";
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        for(String[] row : screen)
        {
            System.out.println(Arrays.toString(row));
        }
    }

}
