package AOC2016;
import java.util.*;
import java.io.*;
public class Day18 {
    public static void main(String[] args){
        System.out.println(safeTiles("inputs/input.txt",1));
        System.out.println(safeTiles("inputs/input.txt",2));
    }

    public static int safeTiles(String fileName, int part){
        char[] input;
        int safe = 0;
        try{
            Scanner s = new Scanner(new File(fileName));
            input = s.nextLine().toCharArray();
            for(char val : input) if(val == '.') safe++;
        } catch (FileNotFoundException e){
            System.out.println("Not found");
            return -1;
        }
        int rows = 40;
        if(part == 2) rows = 400000;
        return countSafe(input,safe,rows);
    }

    public static int countSafe(char[] input, int safe, int rows){
        for(int i = 1; i < rows; i++){
            char[] temp = new char[input.length];
            for(int j = 0; j < temp.length; j++){
                boolean l,r;
                l = j == 0 || input[j-1] == '.';
                r = j == temp.length-1 || input[j+1] == '.';
                temp[j] = l ^ r ? '^' : '.';
                if(temp[j] == '.') safe++;
            }
            input = temp;
        }
        return safe;
    }
}
