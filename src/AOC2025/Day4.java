package AOC2025;
import java.io.*;
import java.util.*;
public class Day4 {
    public static void main(String[] args){
        System.out.println(validRolls("inputs/input.txt",1));
        System.out.println(validRolls("inputs/input.txt",2));
    }

    public static int validRolls(String fileName, int part){
        int length = 138;
        if(fileName.contains("trial")) length = 10;
        String[][] input = new String[length][];
        try{
            Scanner s = new Scanner(new File(fileName));
            int index = 0;
            while(s.hasNextLine()){
                input[index] = s.nextLine().split("(?!^)");
                index++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return validRolls(input,part);
    }

    public static int validRolls(String[][] input, int part){
        String[][] copiedArr = copyArr(input);
        int count = 0;
        int loopCount = -1;
        while(loopCount!=0){
            loopCount=0;
            for(int r = 0; r < input.length; r++){
                for(int c = 0; c < input.length; c++){
                    if(input[r][c].equals("@")){
                        if(part == 1) copiedArr[r][c] = ".";
                        int surroundings = findSurroundings(input,r,c);
                        if(surroundings<4){
                            loopCount++;
                            copiedArr[r][c]=".";
                        }
                    }
                }
            }
            input = copyArr(copiedArr);
            count+=loopCount;
        }
        return count;
    }

    public static String[][] copyArr(String[][] arr){
        String[][] copied = new String[arr.length][arr.length];
        for(int i = 0; i < arr.length; i++){
            System.arraycopy(arr[i],0,copied[i],0,arr.length);
        }
        return copied;
    }

    public static int findSurroundings(String[][] input, int r, int c){
        int surroundings = 0;
        if(r!=0){
            if(input[r-1][c].equals("@")) surroundings++;
            if(c!=0 && input[r-1][c-1].equals("@")) surroundings++;
            if(c!=input.length-1 && input[r-1][c+1].equals("@")) surroundings++;
        }
        if(r!=input.length-1){
            if(input[r+1][c].equals("@")) surroundings++;
            if(c!=0 && input[r+1][c-1].equals("@")) surroundings++;
            if(c!=input.length-1 && input[r+1][c+1].equals("@")) surroundings++;
        }
        if(c!=0){
            if(input[r][c-1].equals("@")) surroundings++;
        }
        if(c!=input.length-1){
            if(input[r][c+1].equals("@")) surroundings++;
        }
        return surroundings;
    }
}
