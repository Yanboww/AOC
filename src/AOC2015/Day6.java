package AOC2015;
import java.io.*;
import java.util.*;
public class Day6 {
    public static void main(String[] args){
        System.out.println(lightShow("inputs/input.txt",1));
        System.out.println(lightShow("inputs/input.txt",2));
    }

    public static long lightShow(String fileName, int part){
        File f = new File(fileName);
        int[][] grid = new int[1000][1000];
        ArrayList<String> instruction = new ArrayList<>();
        try {
            Scanner s  = new Scanner(f);
            while(s.hasNextLine()) instruction.add(s.nextLine());
            s.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return lightUp(grid,instruction,part);
    }

    public static long lightUp(int[][] grid, ArrayList<String> instructions, int part){
        for(String instruction : instructions){
            instruction = instruction.replace(" through","");
            instruction = instruction.replace("turn ","");
            String[] instructionArr = instruction.split(" ");
            String action = instructionArr[0];
            int[] lo = Arrays.stream(instructionArr[1].split(",")).mapToInt(Integer::parseInt).toArray();
            int[] hi = Arrays.stream(instructionArr[2].split(",")).mapToInt(Integer::parseInt).toArray();
            for(int r = lo[0]; r <= hi[0]; r++){
                for(int c = lo[1]; c <= hi[1]; c++){
                    if(action.equals("toggle")){
                        if(part == 1){
                            if(grid[r][c] == 0) grid[r][c] = 1;
                            else grid[r][c] = 0;
                        }
                        else{
                            grid[r][c] +=2;
                        }
                    }
                    else if(action.equals("off")){
                        if(part == 1) grid[r][c] = 0;
                        else{
                            grid[r][c] -= 1;
                            if(grid[r][c] < 0) grid[r][c] = 0;
                        }
                    }
                    else {
                        if(part == 1) grid[r][c] = 1;
                        else grid[r][c] += 1;
                    }
                }
            }
        }
        return countOn(grid);
    }

    public static long countOn(int[][] grid){
        long count = 0;
        for(int[] row : grid){
            for(int val : row){
                count+=val;
            }
        }
        return count;
    }

}
