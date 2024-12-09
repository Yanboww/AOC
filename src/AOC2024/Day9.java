package AOC2024;
import java.io.*;
import java.util.*;
public class Day9 {
    static HashMap<String,Integer> memory = new HashMap<>();
    public static void main(String[] args) {
        System.out.println(findCompressedSum("inputs/input.txt", 1));
        System.out.println(findCompressedSum("inputs/input.txt", 2));
    }

    public static Long findCompressedSum(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<String> input = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            String line = s.nextLine();
            int current = 0;
            boolean space = false;
            for(int i = 0; i < line.length(); i++)
            {
                int val = Integer.parseInt(line.substring(i,i+1));
                if(!space)
                {
                    for(int k = 0; k < val; k++)
                    {
                        input.add(Integer.toString(current));
                        memory.put(Integer.toString(current),val);
                    }
                    space = true;
                    current++;
                }
                else{
                    for(int k = 0; k < val; k++)
                    {
                        input.add(".");
                    }
                    space = false;
                }
            }

        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part ==1) return rearrangeList(input);
        return rearrangeListBlock(input);
    }

    public static Long rearrangeList(ArrayList<String> input)
    {
        for(int i = input.size()-1; i >= 0; i--)
        {
            String val = input.get(i);
            if(!val.equals("."))
            {
                int index = input.indexOf(".");
                if(index > i) break;
                input.set(index,val);
                input.set(i,".");
            }
        }
        return calcSum(input);
    }

    public static Long rearrangeListBlock(ArrayList<String> input)
    {
        HashSet<String> old = new HashSet<>();
        for(int i = input.size()-1; i >= 0; i--)
        {
            String val = input.get(i);
            if(!val.equals(".") && !old.contains(val))
            {
                old.add(val);
                int amountNeeded = memory.get(val);
                int index = findMemoryBlock(input,amountNeeded);
                if(index == -1) continue;
                if(index > i) continue;
                int tempI = i;
                for(int k = 0; k < amountNeeded; k++)
                {
                    input.set(index,val);
                    input.set(tempI,".");
                    index++;
                    tempI--;
                }
            }
        }
        return calcSum(input);
    }

    public static Long calcSum(ArrayList<String> input)
    {
        long sum = 0L;
        for(int i = 0; i < input.size(); i++)
        {
            String word = input.get(i);
            if(word.equals(".")) continue;
            long val = Long.parseLong(word);
            sum += (val*i);
        }
        return sum;
    }

    public static int findMemoryBlock(ArrayList<String> input, int needed)
    {
        int count = 0;
        for(int i = 0; i < input.size(); i++)
        {
            String val = input.get(i);
            if(val.equals(".") && i+needed-1 < input.size())
            {
                for(int k = i; k < i + needed; k++)
                {
                    String val2 = input.get(k);
                    if(!val2.equals(".")) break;
                    else count++;
                }
                if(count == needed) return i;
                else count = 0;
            }
        }
        return -1;
    }
}
