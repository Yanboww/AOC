package AOC2020;
import java.io.*;
import java.util.*;
public class Day15 {
    public static void main(String[] args){
        //System.out.println(recite("inputs/input.txt",1));
        System.out.println(recite("inputs/input.txt",2));
        //System.out.println(recite("inputs/trial",2));
    }

    public static long recite(String fileName,int part){
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
        if(part == 1) return the2020th(history,current,count);
        return the30Millionth(history,current,count);
    }

    public static int the2020th(HashMap<String,Integer> history, String prev, int count){
        while(count < 2020){
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

    public static int the30Millionth(HashMap<String,Integer> history, String prev, int count){
        while(count < 30000000){
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
