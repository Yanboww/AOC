package AOC2016;
import java.io.*;
import java.util.*;
public class Day16 {
    public static void main(String[] args){
        System.out.println(dragonChecksum("inputs/input.txt",1));
        System.out.println(dragonChecksum("inputs/input.txt",2));
    }

    public static String dragonChecksum(String fileName, int part){
        String input;
        int diskLength = 272;
        if(part == 2) diskLength = 35651584;
        try{
            Scanner s = new Scanner(new File(fileName));
            input = s.nextLine();
            s.close();
        } catch (FileNotFoundException e){
            return "failed";
        }
        return fillDisk(input, diskLength);
    }

    public static String fillDisk(String input, int length){
        while(input.length() < length){
            String a = input;
            char[] temp = new StringBuilder(input).reverse().toString().toCharArray();
            StringBuilder b = new StringBuilder();
            for(char val : temp){
                if (val == '1') {
                    b.append("0");
                } else {
                    b.append("1");
                }
            }
            input = a+"0"+b;
        }
        return checksum(input.substring(0,length));
    }

    public static String checksum(String input){
        StringBuilder result = new StringBuilder();
        while(result.isEmpty() || result.length() % 2 == 0){
            result.setLength(0);
            for(int i = 0; i < input.length(); i+=2){
                if(input.charAt(i) == input.charAt(i+1)) result.append("1");
                else result.append("0");
            }
            input = result.toString();
        }
        return input;
    }
}
