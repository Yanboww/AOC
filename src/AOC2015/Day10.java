package AOC2015;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Day10 {
    public static void main(String[] args){
        System.out.println(literal("inputs/input.txt",1));
        System.out.println(literal("inputs/input.txt",2));
    }

    public static int literal(String fileName,int part){
        String input ="";
        try{

            Scanner s = new Scanner(new File(fileName));
            input = s.nextLine();
            s.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return iterate(input,40);
        return iterate(input,50);
    }

    public static int iterate(String input, int times){
        Pattern p = Pattern.compile("(\\d)(\\1)*");
        for(int i = 0; i < times; i++){
            StringBuilder newInput = new StringBuilder();
            Matcher m = p.matcher(input);
            while(m.find()){
                String match = m.group();
                newInput.append(match.length()).append(match.charAt(0));
            }
            input = newInput.toString();
        }
        return input.length();
    }

}
