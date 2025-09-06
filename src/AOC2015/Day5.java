package AOC2015;
import java.io.*;
import java.util.*;
public class Day5 {
    public static void main(String[] args){
        System.out.println(naughtyOrNice("inputs/input.txt",1));
        System.out.println(naughtyOrNice("inputs/input.txt",2));
    }

    public static int naughtyOrNice(String fileName, int part){
        File f = new File(fileName);
        int inputSize = 1000;
        if(fileName.contains("trial")) inputSize = 1;
        String[] inputs = new String[inputSize];
        try{
            Scanner s = new Scanner(f);
            int i = 0;
            while(s.hasNextLine()){
                inputs[i] = s.nextLine();
                i++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return countNice(inputs,part);
    }

    public static int countNice(String[] inputs, int part){
        int count = 0;
        for(String input : inputs){
            if(part == 1){
                if(input.matches(".*(\\w)(\\1).*") && countVowels(input) && desirable(input)){
                    count++;
                }
            }
            else{
                if(input.matches(".*(\\w)\\w(\\1).*") && input.matches(".*(\\w{2})\\w*(\\1).*")) count++;
            }
        }
        return count;
    }

    public static boolean countVowels(String input){
        String vowels = "aeiou";
        int count = 0;
        for(char letter : input.toCharArray()){
            if(vowels.contains(Character.toString(letter))) count++;
        }
        return count >= 3;
    }

    public static boolean desirable(String input){
        return !input.contains("ab") && !input.contains("cd") && !input.contains("pq") && !input.contains("xy");
    }
}
