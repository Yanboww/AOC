package AOC2025;
import java.io.*;
import java.util.*;
public class Day3 {
    public static void main(String[] args){
        System.out.println(jolts("inputs/input.txt",1));
        System.out.println(jolts("inputs/input.txt",2));
    }

    public static long jolts(String fileName, int part){
        ArrayList<String> inputs = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()) inputs.add(s.nextLine());
            s.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return maxJolts(inputs,2);
        return maxJolts(inputs,12);
    }

    public static long maxJolts(ArrayList<String> inputs, int size){
        long total = 0;
        for(String values : inputs){
            int len = values.length();
            StringBuilder maxJolt = new StringBuilder();
            while(maxJolt.length() < size){
                for(int i = 9; i >= 0; i--){
                    int index = values.indexOf(Integer.toString(i));
                    if(index!=-1 && maxJolt.length()+len-index>=size){
                        maxJolt.append(i);
                        if(index != values.length()-1) values = values.substring(index+1);
                        len = values.length();
                        break;
                    }
                }
            }
            total+=Long.parseLong(maxJolt.toString());
        }
        return total;
    }

}
