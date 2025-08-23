package AOC2015;
import java.io.*;
import java.util.*;
public class Day2 {
    public static void main(String[] args){
        System.out.println(wrapping("inputs/input.txt",1));
        System.out.println(wrapping("inputs/input.txt",2));
    }

    public static int wrapping(String fileName, int part){
        File f = new File(fileName);
        ArrayList<String[]> dimensions = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) dimensions.add(s.nextLine().split("x"));
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return calculateSurface(dimensions);
        return calculateRibbon(dimensions);
    }

    public static int calculateSurface(ArrayList<String[]> dimensions){
        int sum = 0;
        for(String[] dimension : dimensions){
            int l = Integer.parseInt(dimension[0]); int w = Integer.parseInt(dimension[1]);
            int h = Integer.parseInt(dimension[2]);
            int side1 = l * h; int side2 = l * w; int side3 = w * h;
            sum+= 2 * (side1 + side2 + side3) + Math.min(Math.min(side1,side2),side3);
        }
        return sum;
    }

    public static int calculateRibbon(ArrayList<String[]> dimensions){
        int sum = 0;
        for(String[] dimension : dimensions){
            int l = Integer.parseInt(dimension[0]); int w = Integer.parseInt(dimension[1]);
            int h = Integer.parseInt(dimension[2]);
            int side1 = 2 * l + 2 * h; int side2 = 2 * l + 2 * w; int side3 = 2 * w + 2 * h;
            sum += Math.min(Math.min(side1,side2),side3) + l * h * w;
        }
        return sum;
    }

}
