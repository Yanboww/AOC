package AOC2015;
import java.io.*;
import java.util.*;

public class Day7 {
    public static void main(String[] args){
        int partA = wiring("inputs/input.txt",1,0);
        System.out.println(partA);
        System.out.println(wiring("inputs/input.txt",2,partA));
    }

    public static int wiring(String fileName, int part,int override){
        File f = new File(fileName);
        HashMap<String,Integer> dict = new HashMap<>();
        ArrayList<String[]> inputs = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                String input = s.nextLine().replace("-> ","");
                String[] inputArr = input.split("\\s");
                if(inputArr.length == 2 && isInt(inputArr[0])){
                    dict.put(inputArr[1],Integer.parseInt(inputArr[0]));
                }
                else inputs.add(inputArr);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1){
            applyOperator(inputs, dict);
            return dict.get("a");
        }
        dict.put("b",override);
        applyOperator(inputs, dict);
        return dict.get("a");
    }

    public static void applyOperator(ArrayList<String[]> inputs, HashMap<String,Integer> dict){
       while(!inputs.isEmpty()){
           String[] input = inputs.get(0);
           if(input.length == 3){
               if(!dict.containsKey(input[1])){
                   inputs.remove(0);
                   inputs.add(input);
                   continue;
               }
               dict.put(input[2],65535-dict.get(input[1]));
           }
           else if(input.length == 2){
               if(!dict.containsKey(input[0])){
                   inputs.remove(0);
                   inputs.add(input);
                   continue;
               }
               dict.put(input[1],dict.get(input[0]));
           }
           else{
               if(falseTest(dict, input[0],input[2])){
                   inputs.remove(0);
                   inputs.add(input);
                   continue;
               }
               int x;
               if(isInt(input[0])) x = Integer.parseInt(input[0]);
               else x = dict.get(input[0]);
               int y;
               if(isInt(input[2])) y = Integer.parseInt(input[2]);
               else y = dict.get(input[2]);
               int result = switch (input[1]) {
                   case "AND" -> x & y;
                   case "OR" -> x | y;
                   case "LSHIFT" -> x << Integer.parseInt(input[2]);
                   case "RSHIFT" -> x >> Integer.parseInt(input[2]);
                   default -> 0;
               };
               if(result == -1) {
                   System.out.println("error");
                   return;
               }
               dict.put(input[3],result);
           }
           inputs.remove(0);
       }
    }

    public static boolean isInt(String val){
        try{
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean falseTest(HashMap<String,Integer> dict, String x, String y){
        if(isInt(x)){
            if(isInt(y)) return false;
            else return !dict.containsKey(y);
        }
        else if(isInt(y)){
            if(isInt(x)) return false;
            else return !dict.containsKey(x);
        }
        else return !dict.containsKey(x) || !dict.containsKey(y);
    }

}
