package AOC2016;
import java.io.*;
import java.util.*;
public class Day12 {
    public static void main(String[] args){
        System.out.println(decode("inputs/input.txt",1));
        System.out.println(decode("inputs/input.txt",2));
    }

    public static int decode(String fileName, int part){
        ArrayList<String> input = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()) input.add(s.nextLine());
            s.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return runCode(input, part);
    }

    public static int runCode(ArrayList<String> input, int part){
        HashMap<String,Integer> dict = new HashMap<>();
        dict.put("a",0); dict.put("b",0); dict.put("c",0); dict.put("d",0);
        if(part == 2) dict.put("c",1);
        for(int i = 0; i < input.size(); i++){
            String[] line = input.get(i).split("\\s");
            switch (line[0]){
                case "cpy":
                   try{
                       dict.put(line[2],Integer.parseInt(line[1]));
                   } catch (NumberFormatException e){
                       dict.put(line[2],dict.get(line[1]));
                   }
                   break;
                case "inc":
                    dict.put(line[1], dict.get(line[1])+1);
                    break;
                case "dec":
                    dict.put(line[1],dict.get(line[1])-1);
                    break;
                case "jnz":
                    if(dict.getOrDefault(line[1],1) != 0) i += Integer.parseInt(line[2]) - 1;
                    break;
            }
        }
        return dict.get("a");
    }
}
