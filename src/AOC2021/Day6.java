package AOC2021;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class Day6 {
    public static void main (String[] args)
    {
        System.out.println(findNumOfFish("inputs/AOC2021Inputs/input6"));
        System.out.println(findNumOfFishVer2("inputs/AOC2021Inputs/input6", 256));
    }

    public static int findNumOfFish(String fileName)
    {
        File f = new File(fileName);
        ArrayList<Integer> fishes = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            String[] temp = s.nextLine().split(",");
            for(String val : temp) fishes.add(Integer.parseInt(val));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        for(int i = 0; i < 80; i++)
        {
            oneDayCycle(fishes);
        }
        return fishes.size();
    }

    public static long findNumOfFishVer2(String fileName, int days)
    {
        File f = new File(fileName);
        long[]fishes = new long[9];
        try{
            Scanner s = new Scanner(f);
            String[] temp = s.nextLine().split(",");
            for(String val : temp) fishes[Integer.parseInt(val)]++;
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        for(int i = 0; i < days; i++) ver2Cycle(fishes);
        long sum = 0;
        for(long val : fishes) sum+=val;
        return sum;
    }

    private static void ver2Cycle(long[] fishes)
    {
        long fish0 = fishes[0];
        fishes[0] = fishes[1];
        fishes[1] = fishes[2];
        fishes[2] = fishes[3];
        fishes[3] = fishes[4];
        fishes[4] = fishes[5];
        fishes[5] = fishes[6];
        fishes[6] = fishes[7] + fish0;
        fishes[7] = fishes[8];
        fishes[8] = fish0;
    }

    private static void oneDayCycle(ArrayList<Integer> fishes)
    {
        for(int i = 0; i < fishes.size(); i++)
        {
            int val = fishes.get(i);
            if(val == 0)
            {
                fishes.add(9);
                fishes.set(i,6);
            }
            else fishes.set(i, val-1);
        }
        //System.out.println(fishes);
    }
}
