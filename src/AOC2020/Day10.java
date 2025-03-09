package AOC2020;
import java.io.*;
import java.util.*;
public class Day10 {
    public static void main(String[] args) {
        System.out.println(findAdapterValue("inputs/input.txt",1));
        System.out.println(findAdapterValue("inputs/input.txt",2));
    }

    public static long findAdapterValue(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<Integer> inputs = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            inputs.add(0);
            while (s.hasNextLine()) inputs.add(s.nextInt());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        Collections.sort(inputs);
        inputs.add(inputs.get(inputs.size()-1)+3);
        if(part == 1) return calculateChain(inputs);
        else return calculatePossibleChain(inputs);
    }

    public static int calculateChain(ArrayList<Integer> inputs){
        int count1  = 0;
        int count3 = 0;
        for(int i = 0; i < inputs.size()-1; i++){
            int current = inputs.get(i);
            int next = inputs.get(i+1);
            if(next - current == 1) count1++;
            else if(next - current == 3) count3++;
        }
        return count1 * count3;
    }

    public static long calculatePossibleChain(ArrayList<Integer> inputs){
        inputs.remove(0);
        HashMap<Integer,Long> key = new HashMap<>();
        key.put(0,1L);
        for(int input : inputs){
            long diff1 = key.containsKey(input-1) ?  key.get(input-1) : 0;
            long diff2 = key.containsKey(input-2) ?  key.get(input-2) : 0;
            long diff3 = key.containsKey(input-3) ?  key.get(input-3) : 0;
        key.put(input,diff3+diff2+diff1);
        }
        return key.get(inputs.get(inputs.size()-1));
    }

    public static boolean calculateAllowed(ArrayList<Integer> inputs){
        for(int i = 0; i < inputs.size()-1; i++){
            int current = inputs.get(i);
            int next = inputs.get(i+1);
            if(next - current > 3) return false;
        }
        return true;
    }
}
