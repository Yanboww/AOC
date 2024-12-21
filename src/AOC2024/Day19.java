package AOC2024;
import java.io.*;
import java.util.*;
public class Day19 {
    public static HashSet<String> cache = new HashSet<>();
    public static HashMap<String,Long> dict = new HashMap<>();
    public static long count = 0;
    public static void main(String[] args)
    {
        System.out.println(findPossible("inputs/input.txt",1));
        System.out.println(findPossible("inputs/input.txt",2));
    }

    public static long findPossible(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<String> combos = new ArrayList<>();
        ArrayList<String> requests = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            boolean wants = false;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                if(line.isEmpty()) wants = true;
                else if(wants) requests.add(line);
                else{
                    String[] temp = line.split("\\W+");
                    combos.addAll(Arrays.asList(temp));
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        count = 0;
        for(String request : requests)
        {
            if(part == 1) check(request,combos);
            else count+= check2(request,combos);
        }
        return count;
    }

    public static boolean check(String request, ArrayList<String> combo)
    {
        if(cache.contains(request)){
            count++;
            return true;
        }
        else if(request.isEmpty()){
            count++;
            return true;
        }
        else{
            boolean canAdd = false;
            for(String possible : combo)
            {
                if(request.startsWith(possible)){
                    int index = possible.indexOf(possible) + possible.length();
                    canAdd = check(request.substring(index),combo);
                }
                if(canAdd) break;
            }
            if(canAdd){
                cache.add(request);
                return true;
            }
        }
        return false;
    }

    public static long check2(String request, ArrayList<String> combo)
    {
        long total = 0;
        if(dict.containsKey(request)){
            return dict.get(request);
        }
        else if(request.isEmpty()){
            return 1;
        }
        else{
            for(String possible : combo)
            {
                if(request.startsWith(possible)){
                    int index = possible.indexOf(possible) + possible.length();
                    total += check2(request.substring(index),combo);
                }
            }
            if(cache.contains(request)) dict.put(request,total);
        }
        return total;
    }
}
