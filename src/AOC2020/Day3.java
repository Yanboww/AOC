package AOC2020;
import java.io.*;
import java.util.*;
public class Day3 {
    public static void main(String[] args){
        System.out.println(encounteredTrees("inputs/input.txt",1));
        System.out.println(encounteredTrees("inputs/input.txt",2));
    }

    public static int encounteredTrees(String fileName, int part){
        File f = new File(fileName);
        int length = 323;
        if(fileName.contains("trial")) length = 11;
        String[][] map = new String[length][];
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine())
            {
                map[index] = s.nextLine().split("(?!^)");
                index++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return findTrees(map,1,3);
        return findProductTree(map);
    }

    public static int findProductTree(String[][] map){
        int product = 1;
        int[] row = new int[]{1,1,1,1,2};
        int[] column = new int[]{1,3,5,7,1};
        for(int i = 0; i < column.length; i++){
            product*= findTrees(map,row[i],column[i]);
        }
        return product;
    }

    public static int findTrees(String[][] map, int row, int column){
        int nextR = row;
        int nextC = column;
        int count = 0;
        while(nextR < map.length){
            if(nextC > map[nextR].length-1) nextC -= map[nextR].length;
            if(map[nextR][nextC].equals("#")) count++;
            nextR+=row;
            nextC+=column;
        }
        return count;
    }
}
