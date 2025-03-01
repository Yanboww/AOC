package AOC2020;
import javax.management.InstanceNotFoundException;
import java.util.*;
import java.io.*;
public class Day9 {
    private static long result = 0;
    public static void main(String[] args){
        System.out.println(findFirstError("inputs/input.txt",1));
        System.out.println(findFirstError("inputs/input.txt",2));
    }

    public static Long findFirstError(String fileName, int part)
    {
        File f = new File(fileName);
        int preambleSize = 25;
        if(fileName.contains("trial")) preambleSize = 5;
        ArrayList<Long> inputs = new ArrayList<>();
        ArrayList<Long> prev = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            int counter = 0;
            while(s.hasNextLine()){
                long currentNum = s.nextLong();
                if(counter < preambleSize) prev.add(currentNum);
                if(part == 2 || counter >=preambleSize) inputs.add(currentNum);
                counter++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 2) return findWeakness(inputs);
        result = findErrorNum(inputs,prev);
        return result;
    }

    public static Long findErrorNum(ArrayList<Long> inputs, ArrayList<Long> prev){
        for(Long num : inputs){
            if(!isPossible(prev,num)) return num;
            prev.remove(0);
            prev.add(num);
        }
        return inputs.get(inputs.size()-1);
    }

    public static boolean isPossible(ArrayList<Long> prev, Long num){
        for(int i = 0; i < prev.size(); i++){
            for(int j = i+1; j < prev.size(); j++){
                long x = prev.get(i);
                long y = prev.get(j);
                if(x != y && x+y == num) return true;
            }
        }
        return false;
    }

    public static long findWeakness(ArrayList<Long> inputs){
        ArrayList<Long> continuous = new ArrayList<>();
        long sum = 0;
        boolean done = false;
        for(int i = 0; i < inputs.size(); i ++){
            if(done) break;
            else if(inputs.get(i)==result) continue;
            for(int j = i; j < inputs.size(); j++){
                long currentVal = inputs.get(j);
                sum+=currentVal;
                continuous.add(currentVal);
                if(sum == result){
                    done = true;
                    break;
                }
                else if (sum > result) {
                    sum = 0;
                    continuous.clear();
                    break;
                }
            }
        }
        Collections.sort(continuous);
        return continuous.get(0) + continuous.get(continuous.size()-1);
    }
}

