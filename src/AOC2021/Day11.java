package AOC2021;
import java.io.*;
import java.util.*;
public class Day11 {
    public static void main(String[] args){
        System.out.println(flashes("inputs/input.txt",1));
        System.out.println(flashes("inputs/input.txt",2));
    }

    public static int flashes(String fileName, int part){
        File f = new File(fileName);
        int[][] octopus = new int[10][];
        try{
            Scanner s = new Scanner(f);
            int rowNum = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                int[] row = new int[line.length()];
                for(int i = 0; i < line.length(); i++) row[i] = Integer.parseInt(line.substring(i,i+1));
                octopus[rowNum] = row;
                rowNum++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return simulateFlashes(octopus);
        return sync(octopus);
    }

    public static int simulateFlashes(int[][] octopus){
        int sum = 0;
        for(int i = 0; i < 100; i++){
            flash(octopus);
            sum+= resetFlashes(octopus);
        }
        return sum;
    }

    public static int sync(int[][] octopus){
        int reset = 0;
        int steps = 0;
        while(reset != 100){
            flash(octopus);
            reset = resetFlashes(octopus);
            steps++;
        }
        return steps;
    }

    public static void flash(int[][] octopus){
        for(int r = 0; r < octopus.length; r++){
            for(int c = 0; c < octopus[r].length; c++) {
                octopus[r][c] = octopus[r][c]+1;
            }
        }
        while(contains(octopus,10)){
            for(int r = 0; r < octopus.length; r++){
                for(int c = 0; c < octopus.length; c++){
                    int level = octopus[r][c];
                    if(level == 10){
                        if(r != 0){
                            if(octopus[r-1][c]!=10)octopus[r-1][c] = octopus[r-1][c]+1;
                            if(c != 0 && octopus[r-1][c-1] != 10) octopus[r-1][c-1] = octopus[r-1][c-1]+1;
                            if(c != octopus.length-1 && octopus[r-1][c+1]!=10) octopus[r-1][c+1] = octopus[r-1][c+1]+1;
                        }
                        if(r != octopus.length-1){
                            if(octopus[r+1][c]!=10)octopus[r+1][c] = octopus[r+1][c]+1;
                            if(c != 0 && octopus[r+1][c-1]!=10) octopus[r+1][c-1] = octopus[r+1][c-1]+1;
                            if(c != octopus.length-1&&octopus[r+1][c+1]!=10) octopus[r+1][c+1] = octopus[r+1][c+1]+1;
                        }
                        if(c != 0 && octopus[r][c-1]!=10)  octopus[r][c-1] = octopus[r][c-1]+1;
                        if(c != octopus.length-1 && octopus[r][c+1]!=10) octopus[r][c+1] = octopus[r][c+1]+1;
                        octopus[r][c] = level+1;
                    }
                }
            }
        }
    }

    public static int resetFlashes(int[][] octopus){
        int count = 0;
        for(int r = 0; r < octopus.length; r++){
            for(int c = 0; c < octopus.length; c++){
                if(octopus[r][c]>10){
                    count++;
                    octopus[r][c] = 0;
                }
            }
        }
        return count;
    }

    public static boolean contains(int[][] arr, int target){
        for (int[] row : arr) {
            for(int val : row){
                if(val == target) return true;
            }
        }
        return false;
    }
}
