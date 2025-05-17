package AOC2019;
import java.io.*;
import java.util.*;
public class Day1 {
    public static void main(String[] args){
        System.out.println(fuelModule("inputs/input.txt",1));
        System.out.println(fuelModule("inputs/input.txt",2));
    }

    public static int fuelModule(String file, int part){
        File f = new File(file);
        ArrayList<Integer> value = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) value.add(s.nextInt());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return total(value);
        return recursiveTotal(value);
    }

    public static int total(ArrayList<Integer> values){
        int total = 0;
        for(int val : values) total+= val/3-2;
        return total;
    }

    public static int recursiveTotal(ArrayList<Integer> values){
        if(values.isEmpty()) return 0;
        ArrayList<Integer> fuelCost = new ArrayList<>();
        int total = 0;
        for(int val : values){
            int cost = val/3-2;
            if(cost > 0){
                fuelCost.add(cost);
                total+=cost;
            }
        }
        total+=recursiveTotal(fuelCost);
        return total;
    }
}
