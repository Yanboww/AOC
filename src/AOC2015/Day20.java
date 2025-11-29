package AOC2015;
import java.io.*;
import java.util.*;
public class Day20 {
    public static void main(String[] args){
        System.out.println(presents("inputs/input.txt",1));
        System.out.println(presents("inputs/input.txt",2));
    }

    public static int presents(String fileName, int part){
        int input = 0;
        try{
            Scanner s = new Scanner(new File(fileName));
            input = s.nextInt();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) findMinHouses(input,10,part);
        return findMinHouses(input,11,part);
    }

    public static int findMinHouses(int input, int presents, int part){
        HashMap<Integer,Integer> dict = new HashMap<>();
        int count = 0;
        int currentHouse = 0;
        while (currentHouse < input){
            count++;
            currentHouse = 0;
            for(int i = 1; i <= Math.sqrt(count); i++){
                if(count%i == 0){
                    int pair = count/i;
                    if(part == 1 || dict.getOrDefault(i,0) < 50){
                        currentHouse+=i*presents;
                        dict.put(i,dict.getOrDefault(i,0)+1);
                    }
                    if(pair != i && (part == 1 || dict.getOrDefault(pair,0) < 50)){
                        currentHouse+=(count/i)*presents;
                        dict.put(pair,dict.getOrDefault(pair,0)+1);
                    }
                }
            }
        }
        return count;
    }

}
