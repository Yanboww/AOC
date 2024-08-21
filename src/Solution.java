import java.util.ArrayList;
import java.util.HashMap;
class Solution {
    public static HashMap<String, Integer> storage = new HashMap<>();
    public static String frequencySort(String s) {
        ArrayList<String> letters = new ArrayList<>();
        for(int i = 0; i < s.length(); i++)
        {
            String letter = s.substring(i,i+1);
            if(!storage.containsKey(letter))
            {
                storage.put(letter,1);
                letters.add(letter);
            }
            else{
                storage.replace(letter,storage.get(letter)+1);
            }
        }
        sort(letters);
        System.out.println(letters);
        return generateSolution(letters);
    }

    public static void sort(ArrayList<String> letters)
    {
        helper(letters, new String[letters.size()],0, letters.size()-1);
    }

    public static void helper(ArrayList<String> letters, String[] temp, int lo, int hi)
    {
        if(lo < hi)
        {
            int mid = (lo+hi)/2;
            helper(letters, temp, lo, mid);
            helper(letters, temp, mid+1, hi);
            merge(letters, temp, lo, mid, hi);
        }
    }

    public static void merge(ArrayList<String> letters, String[] temp, int lo, int mid, int hi)
    {
        int start = lo;
        int middle = mid+1;
        int i = lo;
        while(lo <= mid && middle <= hi)
        {
            System.out.println(i);
            if(storage.get(letters.get(start)) > storage.get(letters.get(middle)))
            {
                temp[i] = letters.get(start);
                System.out.println(letters.get(start));
                start++;
            }
            else{
                temp[i] = letters.get(middle);
                middle++;
            }
            if(i < hi) i++;
        }
        while(start<=mid)
        {
            temp[i] = letters.get(start);
            start++;
            i++;
        }
        while(middle <= hi)
        {
            temp[i] = letters.get(middle);
            middle++;
            i++;
        }
        for(int a = lo; a <= hi; a++)
        {
            letters.set(a,temp[a]);
        }
    }

    public static String generateSolution(ArrayList<String> letters)
    {
        String solution = "";
        for(String letter : letters)
        {
            for(int i = 0; i < storage.get(letter); i++)
            {
                solution+=letter;
            }
        }
        return solution;
    }
}
