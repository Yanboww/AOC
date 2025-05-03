package AOC2019;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Day4 {
    public static void main(String[] args){
        System.out.println(password("inputs/input.txt",1));
        System.out.println(password("inputs/input.txt",2));
    }

    public static int password(String fileName, int part){
        File f = new File(fileName);
        int start = 0;
        int end = 0;
        try {
            Scanner s = new Scanner(f);
            String[] line = s.nextLine().split("-");
            start = Integer.parseInt(line[0]);
            end = Integer.parseInt(line[1]);
        }
        catch (FileNotFoundException e){
            System.out.println("file not found");
        }
        return possiblePass(start, end, part);
    }

    public static int possiblePass(int start, int end, int part){
        int count = 0;
        for(int i = start; i <= end; i++){
            boolean increasing = checkIncreasing(i);
            if(!increasing) continue;
            boolean doubleDigit = checkDouble(i, part);
            if(doubleDigit) count++;
        }
        return count;
    }

    public static boolean checkIncreasing(int val){
        char[] arr = Integer.toString(val).toCharArray();
        for(int i = 0; i < arr.length-1; i++){
            int current = arr[i];
            int next = arr[i+1];
            if(current > next) return false;
        }
        return true;
    }

    public static boolean checkDouble(int val, int part){
        String valStr = Integer.toString(val);
        if(part == 1) return valStr.matches("\\d*(\\d)\\1\\d*");
        Pattern p = Pattern.compile("(\\d)\\1+");
        Matcher m = p.matcher(valStr);
        while(m.find()){
            if(m.group().length() == 2) return true;
        }
        return false;
    }
}
