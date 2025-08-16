package AOC2021;
import java.io.*;
import java.util.*;
public class Day22 {
    public static void main(String[] args){
        //System.out.println(initialization("inputs/input.txt",1));
        System.out.println(initialization("inputs/trial" ,1));
    }

    public static long initialization(String fileName, int part){
        File f = new File(fileName);
        ArrayList<String> inputs = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) inputs.add(s.nextLine());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return limited(inputs);
        return 0;
    }

    public static long limited(ArrayList<String> inputs){
        int[][][] space = new int[101][101][101];
        for(String input : inputs){
            String[] command = input.split(" ");
            String[] range = command[1].split(",");
            int[] x = convertRange(range[0],1);
            int[] y = convertRange(range[1],1);
            int[] z = convertRange(range[2],1);
            for(int d = z[0]; d <= z[1]; d++){
                for(int r = y[0]; r <= y[1]; r++){
                    for(int c = x[0]; c <= x[1]; c++){
                        if(command[0].equals("on")) space[d][c][r] = 1;
                        else space[d][c][r] = 0;
                    }
                }
            }
        }
        return count(space);
    }

    public static int[] convertRange(String range,int part){
        int lo = Integer.parseInt(range.substring(range.indexOf("=")+1,range.indexOf(".")));
        int hi = Integer.parseInt(range.substring(range.lastIndexOf(".")+1));
        if(part == 1){
            if(lo < -50) lo = -50;
            if(hi > 50) hi = 50;
        }
        lo += 50;
        hi += 50;
        return new int[]{lo,hi};
    }

    public static long count(int[][][] space){
        int count = 0;
        for(int[][] layer : space){
            for(int[] row : layer){
               for(int val : row){
                   count+=val;
               }
            }
        }
        return count;
    }
}
