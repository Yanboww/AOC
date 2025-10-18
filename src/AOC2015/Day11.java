package AOC2015;
import java.io.*;
import java.util.*;
public class Day11 {
    public static void main(String[] args){
        System.out.println(nextPassword("inputs/input.txt",1));
        System.out.println(nextPassword("inputs/input.txt",2));
    }
    public static String nextPassword(String filename, int part){
        String input ="";
        HashMap<String,String> increment = new HashMap<>();
        setDict(increment);
        try{
            Scanner s = new Scanner(new File(filename));
            input = s.nextLine();
            s.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return findNewPass(input, increment);
        return findNewPass(findNewPass(input,increment),increment);
    }

    public static void setDict(HashMap<String,String> hashmap){
        hashmap.put("a","b");hashmap.put("b","c");hashmap.put("c","d");
        hashmap.put("d","e");hashmap.put("e","f");hashmap.put("f","g");
        hashmap.put("g","h");hashmap.put("h","i");hashmap.put("i","j");
        hashmap.put("j","k");hashmap.put("k","l");hashmap.put("l","m");
        hashmap.put("m","n");hashmap.put("n","o");hashmap.put("o","p");
        hashmap.put("p","q");hashmap.put("q","r");hashmap.put("r","s");
        hashmap.put("s","t");hashmap.put("t","u");hashmap.put("u","v");
        hashmap.put("v","w");hashmap.put("w","x");hashmap.put("x","y");
        hashmap.put("y","z");hashmap.put("z","a");
    }

    public static String findNewPass(String input, HashMap<String,String> dict){
        String possiblePass = incrementPass(input,dict);
        while(isNotValidPass(possiblePass)){
            possiblePass = incrementPass(possiblePass, dict);
        }
        return possiblePass;
    }


    public static String incrementPass(String input, HashMap<String,String> dict){
        StringBuilder possible = new StringBuilder();
        StringBuilder ending = new StringBuilder();
        for(int i = input.length()-1; i >= 0; i--){
            String current = String.valueOf(input.charAt(i));
            String replacement = dict.get(current);
            if(!replacement.equals("a")){
                possible.append(input, 0, i).append(replacement).append(ending);
                break;
            }
            else{
                ending.insert(0, replacement);
            }
        }
        return possible.toString();
    }

    public static boolean isNotValidPass(String potentialPass){
        if(potentialPass.matches(".*[iol].*")) return true;
        if(!potentialPass.matches(".*(abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz).*")){
            return true;
        }
        return !potentialPass.matches(".*(.)\\1.*(?!\\1)(.)\\2");
    }

}
