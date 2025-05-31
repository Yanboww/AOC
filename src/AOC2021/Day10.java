package AOC2021;
import java.io.*;
import java.util.*;

public class Day10 {
    public static void main(String[] args){
        System.out.println(syntax("inputs/input.txt",1));
        System.out.println(syntax("inputs/input.txt",2));
    }

    public static long syntax(String fileName,int part)
    {
        File f = new File(fileName);
        ArrayList<String> syntax = new ArrayList<>();
        HashMap<String,String> tags = new HashMap<>();
        tags.put("(",")"); tags.put("[","]"); tags.put("{","}"); tags.put("<",">");
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) syntax.add(s.nextLine());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return checkError(syntax,tags);
        return autoComplete(syntax,tags);
    }

    public static int checkError(ArrayList<String> syntax, HashMap<String,String> tags){
        int score = 0;
        HashMap<String,Integer> scores = new HashMap<>();
        scores.put(")",3); scores.put("]",57); scores.put("}",1197); scores.put(">",25137);
        for(String line : syntax){
            ArrayList<String> symbols = new ArrayList<>();
            for(int i = 0; i < line.length(); i++) {
                String tag = line.substring(i,i+1);
                if(tags.containsKey(tag)) symbols.add(tag);
                else{
                    if(tags.get(symbols.get(symbols.size()-1)).equals(tag)) symbols.remove(symbols.size()-1);
                    else{
                        score += scores.get(tag);
                        break;
                    }
                }
            }
        }
        return score;
    }
    public static long autoComplete(ArrayList<String> syntax, HashMap<String,String> tags){
        filter(syntax,tags);
        HashMap<String,Integer> scores = new HashMap<>();
        scores.put(")",1); scores.put("]",2); scores.put("}",3); scores.put(">",4);
        ArrayList<Long> score = new ArrayList<>();
        for(String line : syntax){
            long currentScore = 0;
            ArrayList<String> symbols = new ArrayList<>();
            for(int i = 0; i < line.length();i++){
                String tag = line.substring(i,i+1);
                if(tags.containsKey(tag)) symbols.add(tag);
                else if(tags.get(symbols.get(symbols.size()-1)).equals(tag)) symbols.remove(symbols.size()-1);
            }
            for(int i = symbols.size()-1; i >= 0;i--){
                String tag = tags.get(symbols.get(i));
                currentScore*=5;
                currentScore+=scores.get(tag);
            }
            score.add(currentScore);
        }
        Collections.sort(score);
        return score.get(score.size()/2);
    }

    public static void filter(ArrayList<String> arr, HashMap<String,String> tags){
        for(int j = 0; j < arr.size(); j++) {
            String line = arr.get(j);
            ArrayList<String> symbols = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                String tag = line.substring(i, i + 1);
                if (tags.containsKey(tag)) symbols.add(tag);
                else {
                    if (tags.get(symbols.get(symbols.size() - 1)).equals(tag)) symbols.remove(symbols.size() - 1);
                    else {
                        arr.remove(j);
                        j--;
                        break;
                    }
                }
            }
        }
    }
}
