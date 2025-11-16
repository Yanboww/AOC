package AOC2015;
import java.io.*;
import java.util.*;
public class Day18 {
    public static void main(String[] args){
        System.out.println(lightsOn("inputs/input.txt",1));
        System.out.println(lightsOn("inputs/input.txt",2));
    }

    public static int lightsOn(String fileName, int part){
        int dimensions = 100;
        if(fileName.contains("trial")) dimensions = 6;
        String[][] grid = new String[dimensions][];
        try{
            Scanner s = new Scanner(new File(fileName));
            int index = 0;
            while(s.hasNextLine()){
                grid[index] = s.nextLine().split("(?!^)");
                index++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return simulateSteps(grid, 100, part);
    }

    public static int simulateSteps(String[][] grid, int steps, int part){
        String[][] gridCopy = new String[grid.length][grid.length];
        if(part == 2){
            int dimensions = grid.length;
            grid[0][0] = "#"; grid[0][dimensions-1] = "#";
            grid[dimensions-1][0] = "#";grid[dimensions-1][dimensions-1] = "#";
        }
        for(int i = 0; i < steps; i++){
            for(int c = 0; c < grid.length; c++) gridCopy[c] = grid[c].clone();
            changeArr(grid,gridCopy,part);
        }
        return countLightsOn(grid);
    }

    public static void changeArr(String[][] grid, String[][] copy, int part){
        int dimensions = grid.length;
        for(int r = 0; r < dimensions; r++){
            for(int c = 0; c < dimensions; c++){
                if(part == 2){
                    if(r == 0 && c == 0) continue;
                    else if(r == 0 && c == dimensions-1) continue;
                    else if(r == dimensions-1 && c == 0) continue;
                    else if(r == dimensions-1 && c == dimensions-1) continue;
                }
                String current = copy[r][c];
                int count = 0;
                for(int r1 = r-1; r1 <= r+1; r1++){
                    if(r1 < 0 || r1 >= dimensions) continue;
                    for(int c1 = c -1; c1 <= c+1; c1++){
                        if(c1 < 0 || c1 >= dimensions) continue;
                        if(r1 != r || c1 != c){
                            if(copy[r1][c1].equals("#")) count++;
                        }
                    }
                }
                if(current.equals("#") && (count != 2 && count != 3)) grid[r][c] = ".";
                else if(current.equals(".") && count == 3) grid[r][c] = "#";
            }
        }
    }

    public static int countLightsOn(String[][] grid){
        int count = 0;
        for(String[] row : grid){
            for(String element : row){
                if(element.equals("#")) count++;
            }
        }
        return  count;
    }
}
