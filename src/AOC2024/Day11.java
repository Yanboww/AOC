package AOC2024;
import java.io.*;
import java.util.*;
public class Day11 {
    public static HashMap<String,Long> cache = new HashMap<>();
    public static void main(String[] args) {
        System.out.println(findStoneBlink("inputs/input.txt",1));
        System.out.println(findStoneBlink("inputs/input.txt",2));
    }

    public static Long findStoneBlink(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<String> stones = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            String[] temp = s.nextLine().split("\\s+");
            stones.addAll(Arrays.asList(temp));

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        if(part == 1)
        {
            for(int i = 0; i < 25; i++)
            {
                updateListStone(stones);
            }
            return (long)stones.size();
        }
        Long count = 0L;
        for(String stone : stones)
        {
            count += updateCountStone(stone, 0);
        }
        return count;
    }

    public static void updateListStone(ArrayList<String> stones)
    {
        for(int i = 0; i < stones.size();i++)
        {
            String val = stones.get(i);
            int length = val.length();
            if(val.equals("0")) stones.set(i,"1");
            else if(length%2==0){
                int mid = val.length()/2-1;
                String firstHalf = val.substring(0,mid+1);
                String secondHalf = val.substring(mid+1);
                stones.set(i,cleanSolution(firstHalf));
                stones.add(i+1,cleanSolution(secondHalf));
                i++;
            }
            else{
                stones.set(i,Long.toString(Long.parseLong(val)*2024));
            }

        }
    }

    public static String cleanSolution(String word)
    {
        while(word.length() > 1 && word.charAt(0) == '0')
        {
            word = word.substring(1);
        }
        return word;
    }

    public static Long updateCountStone(String stone, int iteration)
    {
        if(iteration == 75) return 1L;
        else if(cache.containsKey(stone+"|"+iteration)) return cache.get(stone+"|"+iteration);
        Long count = 0L;
        if(stone.equals("0")) count = updateCountStone("1",iteration+1);
        else if(stone.length()%2==0)
        {
            int mid = stone.length()/2;
            count = updateCountStone(cleanSolution(stone.substring(0,mid)), iteration + 1);
            count += updateCountStone(cleanSolution(stone.substring(mid)),iteration+1);
        }
        else count=updateCountStone(Long.toString(Long.parseLong(stone)*2024),iteration+1);
        cache.put(stone+"|"+iteration,count);
        return count;
    }
}
