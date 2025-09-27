package AOC2015;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Day8 {
    public static void main(String[] args){
        //System.out.println(memory("inputs/input.txt",1));
        System.out.println(memory("inputs/input.txt",2));
        //System.out.println(memory("inputs/trial",2));
    }

    public static int memory(String fileName, int part) {
        ArrayList<String> input = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(fileName));
            while (s.hasNextLine()) input.add(s.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        if (part == 1) return difference(input);
        return encode(input);
    }

    public static int difference(ArrayList<String> input){
        int characters = 0;
        int literals = 0;
        for(String line : input){
            characters += line.length();
            String stringActual = line.replaceAll("\\\\[\\\\\\\"]","!");
            literals += stringActual.replaceAll("\\\\x[a-f0-9]{2}",".").length()-2;
        }
        return characters - literals;
    }

    public static int encode(ArrayList<String> input){
        int character = 0;
        int encoded  = 0;
        for(String line : input){
            character+=line.length();
            encoded += line.replaceAll("[\\\\\\\"]","!!").length()+2;
        }
        return encoded - character;
    }
}
