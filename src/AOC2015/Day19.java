package AOC2015;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Day19 {
    public static void main(String[] args){
        System.out.println(medicine("inputs/input.txt",1));
        System.out.println(medicine("inputs/input.txt",2));
    }

    public static int medicine(String fileName, int part){
        HashMap<String, String> dict = new HashMap<>();
        String input = "";
        try {
            Scanner s = new Scanner(new File(fileName));
            boolean dictOver = false;
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()) dictOver = true;
                else if(dictOver) input = line;
                else{
                    String[] lineArr = line.split(" ");
                    if(dict.containsKey(lineArr[0])){
                        dict.put(lineArr[0], dict.get(lineArr[0])+" "+lineArr[2]);
                    }
                    else dict.put(lineArr[0],lineArr[2]);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return oneReplacement(dict, input);
        return createMedicine(dict, input);
    }

    public static int oneReplacement(HashMap<String, String> dict, String input){
        HashSet<String> molecules = new HashSet<>();
        for(String key : dict.keySet()){
            String[] options = dict.get(key).split(" ");
            Pattern p = Pattern.compile(key);
            Matcher m = p.matcher(input);
            while(m.find()){
                for(String option: options){
                    int start = m.start();
                    int end = m.end();
                    molecules.add(input.substring(0,start) + option + input.substring(end));
                }
            }
        }
        return molecules.size();
    }

    public static int createMedicine(HashMap<String,String> dict, String input){
        int steps = 0;
        while(!input.equals("e")){
            for(String key : dict.keySet()){
                String[] options = dict.get(key).split(" ");
                for(String option : options){
                    if(input.contains(option)){
                        input = input.substring(0,input.lastIndexOf(option))+key+input.substring(input.lastIndexOf(option)+option.length());
                        steps++;
                    }
                }
            }
        }
        return steps;
    }
}
