package AOC2015;
import java.io.*;
import java.util.*;
public class Day25 {
    public static void main(String[] args){
        System.out.println(findCode("inputs/input.txt"));
    }

    public static long findCode(String fileName){
        int row, column;
        try{
            Scanner s = new Scanner(new File(fileName));
            String line = s.nextLine();
            row = Integer.parseInt(line.substring(line.indexOf("row")+4,line.lastIndexOf(",")));
            column = Integer.parseInt(line.substring(line.indexOf("column")+7,line.length()-1));
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            return -1;
        }
        int newRow = row+column-2;
        int totalTimes = (newRow*(newRow+1))/2 + column;
        long val = 20151125;
        for(int i = 1; i < totalTimes; i++) val = (val * 252533) % 33554393;
        return val;
    }
}
