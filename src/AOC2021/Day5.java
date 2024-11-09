package AOC2021;

import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Arrays;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args){
        System.out.println(findOverlap("inputs/AOC2021Inputs/input5"));
        System.out.println(findOverlapDiagonal("inputs/AOC2021Inputs/input5"));
    }
    public static int findOverlap(String fileName){
        File f = new File(fileName);
        int[][] grid = new int[1000][1000];
        //int[][] grid = new int[10][10];
        int[][] lines = new int[500][4];
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] separate = line.split(" -> ");
                String[] one = separate[0].split(",");
                String[] two = separate[1].split(",");
                lines[index][0] = Integer.parseInt(one[0]);
                lines[index][1] = Integer.parseInt(one[1]);
                lines[index][2] = Integer.parseInt(two[0]);
                lines[index][3] = Integer.parseInt(two[1]);
                index++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        findLines(grid, lines);
        return countOverlap(grid);
    }
    public static int findOverlapDiagonal(String fileName){
        File f = new File(fileName);
        int[][] grid = new int[1000][1000];
        //int[][] grid = new int[10][10];
        int[][] lines = new int[500][4];
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] separate = line.split(" -> ");
                String[] one = separate[0].split(",");
                String[] two = separate[1].split(",");
                lines[index][0] = Integer.parseInt(one[0]);
                lines[index][1] = Integer.parseInt(one[1]);
                lines[index][2] = Integer.parseInt(two[0]);
                lines[index][3] = Integer.parseInt(two[1]);
                index++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        findLinesDiagonal(grid, lines);
        return countOverlap(grid);
    }
    private static void findLines(int[][] grid, int[][] lines){
        for(int[] row : lines){
            if(row[0] == row[2])
            {
                int c = row[0];
                int min = Math.min(row[1],row[3]);
                int max = Math.max(row[1],row[3]);
                for(int i = min; i <= max; i++)
                {
                    grid[i][c]++;
                }
            }
            else if(row[1] == row[3]){
                int r = row[1];
                int min = Math.min(row[0],row[2]);
                int max = Math.max(row[0],row[2]);
                for(int i = min; i <= max; i++)
                {
                    grid[r][i]++;
                }
            }
        }
    }
    private static void findLinesDiagonal(int[][] grid, int[][] lines)
    {
        for(int[] row : lines){
            if(row[0] == row[2])
            {
                int c = row[0];
                int min = Math.min(row[1],row[3]);
                int max = Math.max(row[1],row[3]);
                for(int i = min; i <= max; i++)
                {
                    grid[i][c]++;
                }
            }
            else if(row[1] == row[3]){
                int r = row[1];
                int min = Math.min(row[0],row[2]);
                int max = Math.max(row[0],row[2]);
                for(int i = min; i <= max; i++)
                {
                    grid[r][i]++;
                }
            }
            else{
                boolean xLess = row[0] < row[2];
                boolean yLess = row[1] < row[3];
                if(xLess == yLess)
                {
                    int minX = Math.min(row[0],row[2]);
                    int maxX = Math.max(row[0],row[2]);
                    int minY = Math.min(row[1],row[3]);
                    for(int i = minX; i <= maxX; i++)
                    {
                        grid[minY][i]++;
                        minY++;
                    }
                }
                else if(xLess){
                    int r = row[1];
                    for(int c = row[0]; c <= row[2]; c++)
                    {
                        grid[r][c]++;
                        r--;
                    }
                }
                else
                {
                    int r = row[1];
                    for(int c = row[0]; c >= row[2]; c--)
                    {
                        grid[r][c]++;
                        r++;
                    }
                }

            }
        }
    }

    private static int countOverlap(int[][] grid){
        int overlap = 0;
        for(int[] row : grid){
            for(int val : row)
            {
                if(val>1) overlap++;
            }
        }
        return overlap;
    }



}
