package AOC2020;
import java.io.*;
import java.util.*;
public class Day15 {
    public static void main(String[] args){
        System.out.println(recite("inputs/input.txt",2020));
        System.out.println(recite("inputs/input.txt",30000000));
    }

    public static long recite(String fileName,int stop){
        File f = new File(fileName);
        HashMap<String,Integer> history = new HashMap<>();
        int count = 1;
        String current = "";
        try{
            Scanner s = new Scanner(f);
            String[] line = s.nextLine().split(",");
            for(String number: line){
                history.put(number,count);
                count++;
                current = number;
            }
            count--;
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return reciteTo(history,current,count,stop);
    }

    public static int reciteTo (HashMap<String,Integer> history, String prev, int count, int stop){
        while(count < stop){
            if(history.containsKey(prev) &&  history.get(prev) != count){
                int lastIndex = history.get(prev);
                history.put(prev,count);
                prev = Integer.toString(count-lastIndex);
            }
            else{
                history.put(prev,count);
                prev = "0";
            }
            count++;
        }
        return Integer.parseInt(prev);
    }
}
