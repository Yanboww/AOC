package AOC2021;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args)
    {
        System.out.println(findEnergyCost("inputs/AOC2021Inputs/input7", 1));
        System.out.println(findEnergyCost("inputs/AOC2021Inputs/input7",2));
    }

    public static int findEnergyCost(String fileName, int type)
    {
        File f = new File(fileName);
        int [] coordinates = new int[0];
        try{
            Scanner s = new Scanner(f);
            String[] temp = s.nextLine().split("\\W");
            coordinates = convertArrayMod(temp);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        return findLowest(coordinates, type);
    }

    public static int findLowest(int[] coordinates, int type)
    {
        int start = coordinates[coordinates.length-1];
        int end = coordinates[coordinates.length-2];
        int lowest = calcEnergyCost(start,coordinates, type);
        for(int i = start+1; i <= end; i++)
        {
            int cost = calcEnergyCost(i,coordinates, type);
            if(cost < lowest) lowest = cost;
        }
        return lowest;
    }

    public static int[] convertArrayMod(String[] array)
    {
        int[] result  = new int[array.length+2];
        int max = Integer.parseInt(array[0]);
        int min = Integer.parseInt(array[0]);
        for(int i = 0; i < array.length; i++){
            result[i] = Integer.parseInt(array[i]);
            if(result[i]>max) max = result[i];
            else if(result[i]<min) min = result[i];
        }
        result[result.length-1] = min;
        result[result.length-2] = max;
        return result;
    }

    public static int calcEnergyCost(int destination, int[] arr, int type)
    {
        int cost = 0;
        if(type == 1)  for(int i = 0; i < arr.length-2; i++) cost+= Math.abs(destination-arr[i]);
        else{
            for(int i = 0; i < arr.length-2; i++){
                int currentCost= Math.abs(destination-arr[i]);
                while(currentCost > 0){
                    cost += currentCost;
                    currentCost--;
                }
            }
        }
        return cost;
    }

}
