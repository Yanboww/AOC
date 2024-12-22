package AOC2024;
import java.io.*;
import java.util.*;
public class Day22 {
    private static  ArrayList<ArrayList<String>> diff = new ArrayList<>();
    public static void main(String[] args)
    {
        System.out.println(findSumOfSecret("inputs/input.txt",1));
        System.out.println(findSumOfSecret("inputs/input.txt",2));
    }
    public static long findSumOfSecret(String fileName, int part)
    {
        diff.clear();
        File f = new File(fileName);
        long total = 0;
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                total+=find2000(Long.parseLong(line));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        if(part == 2) return findMaxBanana();
        return total;
    }
    public static long find2000(long num)
    {
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < 2000; i++)
        {
            long prev = num%10;
            num = findSecret(num);
            temp.add((num%10) +"," + ((num%10)-prev));
        }
        temp.remove(temp.size()-1);
        diff.add(temp);
        return num;
    }
    public static long findMaxBanana()
    {
        HashMap<String,Long> dict = new HashMap<>();
        for(ArrayList<String> temp : diff)
        {
            calcSequence(temp,dict);
        }
        long max = 0;
        for(Long val : dict.values()) if(val>max) max = val;
        return max;
    }
    public static void calcSequence(ArrayList<String> temp, HashMap<String,Long> dict)
    {
        ArrayList<String> seq = new ArrayList<>();
        ArrayList<String> explored = new ArrayList<>();
        for(String diff : temp){
            String difference = diff.substring(diff.indexOf(",")+1);
            if(seq.size() < 4) seq.add(difference);
            if(seq.size() == 4){
                String tempSeq = "";
                for(String seqDiff : seq) tempSeq+=seqDiff+",";
                if(!explored.contains(tempSeq))
                {
                    long val = Long.parseLong(diff.substring(0,diff.indexOf(",")));
                    if(!dict.containsKey(tempSeq)) dict.put(tempSeq,val);
                    else dict.put(tempSeq,dict.get(tempSeq)+val);
                    explored.add(tempSeq);
                }
                seq.remove(0);
            }
        }
    }
    public static long findSecret(long num){
        num ^= (num*64);
        num %= 16777216;
        num ^= (num/32);
        num %= 16777216;
        num ^= (num*2048);
        num %= 16777216;
        return num;
    }
}
