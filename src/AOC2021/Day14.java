package AOC2021;
import java.io.*;
import java.util.*;
public class Day14 {
    public static void main(String[] args){
        System.out.println(polymerInsertion("inputs/input.txt",1));
        System.out.println(polymerInsertion("inputs/input.txt",2));
    }

    public static long polymerInsertion(String fileName, int part){
        File f = new File(fileName);
        HashMap<String,String> dict = new HashMap<>();
        HashMap<String,Long> count = new HashMap<>();
        ArrayList<String> possible = new ArrayList<>();
        String start = "";
        try{
            Scanner s = new Scanner(f);
            boolean isStart = false;
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()) isStart = true;
                else if (isStart) {
                    String[] temp = line.split("\\W");
                    dict.put(temp[0],temp[temp.length-1]);
                    count.put(temp[0],0L);
                    possible.add(temp[0]);
                }
                else{
                    start = line;
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return simulateInsertions(start,dict,10);
        return refactoredInsertions(start,dict, count, possible, 40);
    }

    public static int simulateInsertions(String start, HashMap<String,String> dict, int times){
        HashMap<String,Integer> count = new HashMap<>();
        ArrayList<String> letters = new ArrayList<>();
        for(int i = 0; i < start.length(); i++){
            String letter = start.substring(i,i+1);
            if(count.containsKey(letter)) count.put(letter, count.get(letter)+1);
            else {
                count.put(letter, 0);
                letters.add(letter);
            }
        }
        for(int i = 0; i < times; i++){
            start = insert(start,dict,count,letters);
        }
        return findResult(count,letters);
    }

    public static long refactoredInsertions(String start, HashMap<String, String> dict, HashMap<String,Long> count, ArrayList<String> possible, int times){
        for(int i = 0; i < start.length()-1; i++){
            String temp = start.substring(i,i+2);
            if(count.containsKey(temp)) count.put(temp,count.get(temp)+1);
        }
        for(int i = 0; i < times; i++){
            insert(dict,count,possible);
        }
        return findResult(count);
    }

    public static String insert(String start, HashMap<String,String> dict, HashMap<String,Integer> count, ArrayList<String> letters){
        for(int i = 0; i < start.length()-1; i++){
            String current = start.substring(i,i+2);
            if(dict.containsKey(current)){
                String additional = dict.get(current);
                start = start.substring(0,i+1)+additional+start.substring(i+1);
                if(count.containsKey(additional)) count.put(additional,count.get(additional)+1);
                else {
                    count.put(additional, 0);
                    letters.add(additional);
                }
                i++;
            }
        }
        return start;
    }

    public static void insert(HashMap<String,String> dict, HashMap<String,Long> count, ArrayList<String> possible){
        HashMap<String,Long> temp = new HashMap<>();
        for(String combo : possible){
            long numberOf = count.get(combo);
            if(numberOf > 0){
                String mat1 = combo.charAt(0) + dict.get(combo);
                String mat2 = dict.get(combo) + combo.charAt(1);
                if(count.containsKey(mat1)){
                    if(temp.containsKey(mat1)) temp.put(mat1,numberOf+temp.get(mat1));
                    else temp.put(mat1,numberOf);
                }
                if(count.containsKey(mat2)){
                    if(temp.containsKey(mat2)) temp.put(mat2,numberOf+temp.get(mat2));
                    else temp.put(mat2,numberOf);
                }
                count.put(combo,0L);
            }
        }
        for(String combo : possible){
            if(temp.containsKey(combo)) count.put(combo,temp.get(combo));
        }
    }

    public static int findResult(HashMap<String,Integer> count, ArrayList<String> letters){
        int max = count.get(letters.get(0));
        int min = count.get(letters.get(0));
        for(String letter : letters){
            int currentCount = count.get(letter);
            if(currentCount > max) max = currentCount;
            else if(currentCount < min) min = currentCount;
        }
        return max - min;
    }

    public static long findResult(HashMap<String,Long> count){
        HashMap<Character,Long> letterCount = new HashMap<>();
        char placeHolderLetter = 'a';
        for(String combo: count.keySet()){
            long currentCount = count.get(combo);
            if(currentCount > 0){
                //System.out.println(combo+": " + currentCount);
                char letter1 = combo.charAt(1);
                placeHolderLetter = letter1;
                if(letterCount.containsKey(letter1)) letterCount.put(letter1,currentCount+letterCount.get(letter1));
                else letterCount.put(letter1,currentCount);
            }
        }
        long max = letterCount.get(placeHolderLetter);
        char maxLetter = placeHolderLetter;
        long min = letterCount.get(placeHolderLetter);
        char minLetter = placeHolderLetter;
        for(char letter : letterCount.keySet()){
            long currentCount = letterCount.get(letter);
            if(currentCount > max ){
                max = currentCount;
                maxLetter = letter;
            }
            if(currentCount < min){
                min = currentCount;
                minLetter = letter;
            }
        }
        System.out.println("max: " + maxLetter + " " + max);
        System.out.println("min: " + minLetter + " " + min);
        return max - min;
    }
}
